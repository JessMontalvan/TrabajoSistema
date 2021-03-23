/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplobdoo;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jssmontalvan
 */
public class Controlador extends Conexion{
     public Controlador() {

    }

    public boolean insertarPersona(int id, String nombre, String apellido) {
        Persona persona = new Persona(nombre, apellido, id);
        return this.Insertar(persona);
    }

    public boolean actualizarPersona(int id, String nombre, String apellido) {
        Persona personaNueva = new Persona(nombre, apellido, id);        
        return this.Actualizar(personaNueva);
    }

    public DefaultTableModel personas() {
        String titulos[] = {"Id", "Nombre", "Apellido"};
        DefaultTableModel dtm = new DefaultTableModel(null, titulos);
        Persona persona = null;
        Persona[] p = this.Consultar(persona);
        if (p != null) {
            for (Persona per : p) {
                Object[] cli = new Object[3];
                cli[0] = per.getId();
                cli[1] = per.getNombre();
                cli[2] = per.getApellido();
                dtm.addRow(cli);
            }
        }
        return dtm;
    }

    public boolean eliminarPersona(int id) {
        if (id > 0) {
            Persona persona = new Persona(null, null, id);
            return this.Eliminar(persona);
        } else {
            return false;
        }
    }
}
