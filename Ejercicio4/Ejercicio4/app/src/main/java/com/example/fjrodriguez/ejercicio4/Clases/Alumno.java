package com.example.fjrodriguez.ejercicio4.Clases;

/**
 * Created by fjrodriguez on 18/10/17.
 */

public class Alumno {

    //Attributes
    private String nombre;
    private int edad;
    private int imagen;

    //Constructors
    public Alumno() { }
    public Alumno(String nombre,int edad,int imagen) {
        this.nombre		= nombre;
        this.edad		= edad;
        this.imagen= imagen;
    }

    //Methods
    @Override
    public String toString() {
        return	"Nombre: \t"	+this.nombre+		"\n"+
                "Edad: \t\t"	+this.edad+			"\n"+
                "imagen: \t"	+this.imagen+		"\n";
    }

    //Gets and Sets
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }
    public void setEdad(int imagen) {
        this.edad = imagen;
    }
    public int getImagen() {
        return imagen;
    }
    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

}