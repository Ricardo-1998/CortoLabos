/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import conexion.Conexion;
import interfaces.metodos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.pelicula;
/**
 *
 * @author LN710Q
 */
public class CineDao implements metodos<pelicula>{
    
    private static final String SQL_INSERT = "INSERT INTO movie(nombre,director,pais,clasificacion,anio,en_proyeccion) VALUES (?,?,?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE movie SET director = ?, pais = ?,clasificacion = ?,anio = ?, en_proyeccion = ? WHERE nombre=?";
    private static final String SQL_DELETE = "DELETE FROM movie WHERE nombre=?";
    private static final String SQL_READ = "SELECT * FROM movie WHERE nombre=?";
    private static final String SQL_READALL = "SELECT * FROM movie";
    private static final Conexion con = Conexion.conectar();
    
    @Override
    public boolean create(pelicula g) {
        //nos servira para preparar la consulta de insert
        PreparedStatement ps;
        try{
            ps = con.getCnx().prepareStatement(SQL_INSERT);
            ps.setString(1, g.getNombre());
            ps.setString(2, g.getDirector());
            ps.setString(3, g.getPais());
            ps.setString(4, g.getClasificacion());
            ps.setInt(5, g.getAnio());
            ps.setBoolean(6, true);
            if(ps.executeUpdate() > 0){
                return true;
            }
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
            Logger.getLogger(CineDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.cerrarConexion();
        }
        return false;
    }

    @Override
    public boolean delete(Object key) {
        PreparedStatement ps;
        try{
            ps = con.getCnx().prepareStatement(SQL_DELETE);
            ps.setString(1, key.toString());
            
            if(ps.executeUpdate() > 0){
                return true;
            }
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
            Logger.getLogger(CineDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.cerrarConexion();
        }
        return false;  
    }

    @Override
    public boolean update(pelicula c) {
        PreparedStatement ps;
        try{
            System.out.println(c.getNombre());
            ps = con.getCnx().prepareStatement(SQL_UPDATE);
            ps.setString(1, c.getNombre());
            ps.setString(2, c.getDirector());
            ps.setString(3, c.getPais());
            ps.setString(4, c.getClasificacion());
            ps.setInt(5, c.getAnio());
            ps.setBoolean(6, c.getEn_proyeccion());
           
            if(ps.executeUpdate() > 0){
                return true;
            }
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
            Logger.getLogger(CineDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.cerrarConexion();
        }
        return false;
        
    }

    @Override
    public pelicula read(Object key) {
        pelicula f = null;
        PreparedStatement ps;
        ResultSet rs;
        try{
            ps = con.getCnx().prepareStatement(SQL_READ);
            ps.setString(1, key.toString());
            
            rs = ps.executeQuery();
            
            while (rs.next()){
                f = new pelicula(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getBoolean(7));
            }
            rs.close();
        } catch(SQLException ex){
            System.out.println(ex.getMessage());
            Logger.getLogger(CineDao.class.getName()).log(Level.SEVERE,null,ex);
        
        } finally{con.cerrarConexion();}
        return f;
    }

    @Override
    public ArrayList<pelicula> readAll() {
        ArrayList<pelicula> all = new ArrayList();
        Statement s;
        ResultSet rs;
        try{
            s= con.getCnx().prepareStatement(SQL_READALL);
            rs= s.executeQuery(SQL_READALL);
            while(rs.next()){
                all.add(new pelicula(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getBoolean(7)));
                
            }
            rs.close();
        } catch(SQLException ex){
            Logger.getLogger(CineDao.class.getName()).log(Level.SEVERE, null,ex);
        } finally{con.cerrarConexion();}
        return all;
    }
    
}
