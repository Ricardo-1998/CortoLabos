/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import dao.CineDao;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelo.pelicula;


/**
 *
 * @author LN710Q
 */
public class Consulta extends JFrame{
    public JLabel  lblNombre, lblDirector, lblPais, lblClasificacion, lblAnio, lblEn_Proyeccion;
    public JTextField nombre, director, pais, anio;
    public JComboBox Clasificacion;
    
    ButtonGroup En_Proyeccion = new ButtonGroup();
    public JRadioButton si;
    public JRadioButton no;
    
    public JTable resultados;
    
    public JPanel table;
    public JButton buscar, eliminar, insertar,actualizar;
    private static final int ANCHOC = 130, ALTOC = 30;
    
    DefaultTableModel tm;
    
    public Consulta(){
        super("Cine");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        agregarLabels();
        formulario();
        llenarTabla();
        Container container = getContentPane();
        //container.add(lblCodigo);
        container.add(lblNombre);
        container.add(lblDirector);
        container.add(lblPais);
        container.add(lblClasificacion);
        container.add(lblAnio);
        container.add(lblEn_Proyeccion);
        //container.add(codigo);
        container.add(nombre);
        container.add(director);
        container.add(pais);
        container.add(anio);
        container.add(Clasificacion);
        container.add(si);
        container.add(no);
        container.add(buscar);
        container.add(insertar);
        container.add(actualizar);
        container.add(eliminar);
        
        container.add(table);
        setSize(600,600);
        eventos();
        
    }
    
    public final void agregarLabels(){
        lblNombre =  new JLabel("Nombre: ");
        lblDirector = new JLabel("Director: ");
        lblPais = new JLabel("País: ");
        lblClasificacion = new JLabel("Clasificacion: ");
        lblAnio = new JLabel("Año: ");
        lblEn_Proyeccion = new JLabel("En proyeccion: ");
        lblNombre.setBounds(10, 10, ANCHOC, ALTOC);
        lblDirector.setBounds(300, 10, ANCHOC, ALTOC);
        lblPais.setBounds(300, 60, ANCHOC, ALTOC);
        lblClasificacion.setBounds(10, 60, ANCHOC, ALTOC);
        lblAnio.setBounds(10, 100, ANCHOC, ALTOC);
        lblEn_Proyeccion.setBounds(10, 140, ANCHOC, ALTOC);
            
    }
    // public JTextField nombre, director, pais, anio;
    public final void formulario(){
        nombre= new JTextField();
        Clasificacion = new JComboBox();
        director = new JTextField();
        pais = new JTextField();
        anio = new JTextField();
        si = new JRadioButton("si", true);
        no= new JRadioButton("no");
        
        resultados = new JTable();
        buscar=new JButton("Buscar");
        insertar=new JButton("insertar");
        eliminar=new JButton("eliminar");
        actualizar=new JButton("actualizar");
        
        
        table = new JPanel();
        
        Clasificacion.addItem("G");
        Clasificacion.addItem("PG");
        Clasificacion.addItem("14A");
        Clasificacion.addItem("18A");
        Clasificacion.addItem("R");
        Clasificacion.addItem("A");
        
        En_Proyeccion = new ButtonGroup();
        En_Proyeccion.add(si);
        En_Proyeccion.add(no);
       
        
        nombre.setBounds(140, 10, ANCHOC, ALTOC);
        director.setBounds(430, 10, ANCHOC, ALTOC);
        pais.setBounds(430, 60, ANCHOC, ALTOC);
        Clasificacion.setBounds(140, 60, ANCHOC, ALTOC);
        anio.setBounds(140, 100, ANCHOC, ALTOC);
        si.setBounds(140, 140, 50, ALTOC);
        no.setBounds(210, 140, 50, ALTOC);
        
        
        
        buscar.setBounds(400, 210, ANCHOC, ALTOC);
        insertar.setBounds(10, 210, ANCHOC, ALTOC);
        actualizar.setBounds(150, 210, ANCHOC, ALTOC);
        eliminar.setBounds(300, 210, ANCHOC, ALTOC);
        resultados = new JTable();
        table.setBounds(10, 250, 500, 200);
        table.add(new JScrollPane(resultados));
    }
    
    
    public void llenarTabla(){
        tm = new DefaultTableModel(){
            @Override
            public Class<?> getColumnClass(int column){
                switch(column){
                    case 0:
                        return String.class;
                    case 1:
                        return String.class;
                    case 2:
                        return String.class;
                    case 3:
                        return String.class;
                    case 4:
                        return int.class;
                    default:
                        return Boolean.class;
                }
            }
        };
        tm.addColumn("Nombre");
        tm.addColumn("Director");
        tm.addColumn("País");
        tm.addColumn("Clasificacion");
        tm.addColumn("Año");
        tm.addColumn("En proyeccion");
        
        CineDao fd = new CineDao();
        ArrayList<pelicula> peliculas = fd.readAll();
        
        for(pelicula fi : peliculas){
            tm.addRow(new Object[]{fi.getNombre(), fi.getDirector(), fi.getPais(), fi.getClasificacion(), fi.getAnio(), fi.getEn_proyeccion()});
        }
        resultados.setModel(tm);
    }
    
    public void eventos(){
        insertar.addActionListener(new ActionListener(){
          

            @Override
            public void actionPerformed(ActionEvent e) {
                CineDao fd = new CineDao();
                pelicula f = new pelicula(nombre.getText(), director.getText(),pais.getText(),Clasificacion.getSelectedItem().toString(),Integer.parseInt(anio.getText()), true);
                if(no.isSelected()){f.setEn_proyeccion(false);}
                if(fd.create(f)){
                    JOptionPane.showMessageDialog(null, "pelicula registrado con exito");
                    limpiarCampos();
                    llenarTabla();
                } else{
                    JOptionPane.showMessageDialog(null, "Ocurrio un proble al momento de crear el giltro");
                }
            }
        });
        
        actualizar.addActionListener(new ActionListener(){
          

            @Override
            public void actionPerformed(ActionEvent e) {
                CineDao fd = new CineDao();
                pelicula f;
                f = new pelicula(nombre.getText(), director.getText(),pais.getText(),Clasificacion.getSelectedItem().toString(),Integer.parseInt(anio.getText()),true);
                if(no.isSelected()){f.setEn_proyeccion(false);}
                if(fd.update(f)){
                    JOptionPane.showMessageDialog(null, "pelicula actualizada con exito");
                    limpiarCampos();
                    llenarTabla();
                } else{
                    JOptionPane.showMessageDialog(null, "Ocurrio un proble al momento de modificar la pelicula");
                }
            }
        });
        
        eliminar.addActionListener(new ActionListener(){
          

            @Override
            public void actionPerformed(ActionEvent e) {
                CineDao fd = new CineDao();
                if(fd.delete(nombre.getText())){
                    JOptionPane.showMessageDialog(null, "pelicula eliminada con exito");
                    limpiarCampos();
                    llenarTabla();
                }else{
                    JOptionPane.showMessageDialog(null, "Ocurrio un proble al momento de eliminar la pelicula");
                }
            }
        });
        //nombre.getText(), director.getText(),pais.getText(), true, Integer.parseInt(anio.getText()) ,Clasificacion.getSelectedItem().toString()
        buscar.addActionListener(new ActionListener(){
          

            @Override
            public void actionPerformed(ActionEvent e) {
                CineDao fd = new CineDao();
                pelicula f = fd.read(nombre.getText());
                if(f==null){
                    JOptionPane.showMessageDialog(null, "pelicula no encontrada");
                    
                }else{
                    nombre.setText(f.getNombre());
                    Clasificacion.setSelectedItem(f.getClasificacion());
                    anio.setText(Integer.toString(f.getAnio()));
                    director.setText(f.getDirector());
                    pais.setText(f.getPais());
                }if(f.getEn_proyeccion()){
                    si.setSelected(true);
                }else{
                    no.setSelected(true);
                }
            }
        });
        
        
        
    }
    
    public void limpiarCampos(){
        nombre.setText("");
        Clasificacion.setSelectedItem("item 1");
        director.setText("");
        pais.setText("");
        anio.setText("");
        
    }

}
