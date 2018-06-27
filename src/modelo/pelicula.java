/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author LN710Q
 */
public class pelicula {

    public int getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(int idMovie) {
        this.idMovie = idMovie;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public boolean getEn_proyeccion() {
        return en_proyeccion;
    }

    public void setEn_proyeccion(boolean en_proyeccion) {
        this.en_proyeccion = en_proyeccion;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }
    
     public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }
    
    private int idMovie;
    private String nombre;
    private String director;
    private String pais;
    private boolean en_proyeccion;
    private int anio;
    private String clasificacion;

    public pelicula() {
    }

    public pelicula(String nombre) {
        this.nombre = nombre;
    }

    public pelicula(String nombre, String director, String pais,String clasificacion, int anio, boolean en_proyeccion) {
        this.nombre = nombre;
        this.director = director;
        this.pais = pais;
        this.en_proyeccion = en_proyeccion;
        this.anio = anio;
        this.clasificacion = clasificacion;
    }

    public pelicula(int idMovie, String nombre, String director, String pais,String clasificacion, int anio, boolean en_proyeccion) {
        this.idMovie = idMovie;
        this.nombre = nombre;
        this.director = director;
        this.pais = pais;
        this.en_proyeccion = en_proyeccion;
        this.anio = anio;
        this.clasificacion = clasificacion;
    }
    
    
    
    
    
}
