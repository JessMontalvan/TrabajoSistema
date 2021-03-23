/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplobdoo;

/**
 *
 * @author jssmontalvan
 */
public class Persona {
    private String Nombre;
    private String Apellido;
    private int Id;

    public Persona(String Nombre, String Apellido, int Id) {
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.Id = Id;
    }

    public Persona() {
    }
    

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    @Override
    public String toString() {
        return "Persona{" + "Nombre=" + Nombre + ", Apellido=" + Apellido + ", Id=" + Id + '}';
    }
    
}
