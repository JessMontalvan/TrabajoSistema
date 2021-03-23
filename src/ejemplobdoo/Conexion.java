/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplobdoo;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.ext.DatabaseClosedException;
import com.db4o.ext.DatabaseReadOnlyException;

/**
 *
 * @author jssmontalvan
 */
public class Conexion {
    private ObjectContainer oc;

    private void open() {
        //CREAMOS LA CONEXION Y EL ARCHIVO QUE ALMACENARÁ LOS DATOS
        this.oc = Db4o.openFile("C:\\xampp\\htdocs\\BDOO\\src\\bdoo\\database.yap");
    }

    public boolean Insertar(Persona objeto) {
        try {
            //BUSCAMOS SI EXISTE EL OBJETO, SI NO INSERTAMOS EL OBJETO RECIBIDO EN LA BASE DE DATOS
            this.open();
            oc.set(objeto);
            this.oc.close();
            return true;
        } catch (DatabaseClosedException | DatabaseReadOnlyException e) {
            System.out.println("bdoo.Controlador.insertarPersona() : " + e);
            return false;
        }
    }

    public boolean Actualizar(Persona objeto) {
        try {
            //BUSCAMOS SI EXISTE EL OBJETO, SI ES ASÍ LO ACTUALIZAMOS EN LA BASE DE DATOS
            this.open();
            ObjectSet resultados = this.oc.get(new Persona(null, null, objeto.getId()));
            if (resultados.size() > 0) {                
                Persona resultado = (Persona) resultados.next();
                resultado.setNombre(objeto.getNombre());
                resultado.setApellido(objeto.getApellido());
                this.oc.set(resultado);
                this.oc.close();
                return true;
            } else {
                this.oc.close();
                return false;
            }
        } catch (DatabaseClosedException | DatabaseReadOnlyException e) {
            System.out.println("bdoo.Controlador.insertarPersona() : " + e);
            return false;
        }
    }

    public Persona buscarPersona(Persona objeto) {
        this.open();   
        Persona encontrado = null;
        ObjectSet resultados = this.oc.get(objeto);
        if (resultados.hasNext()) {
            encontrado = (Persona) resultados.next();
        }
        return encontrado;
    }

    public Persona[] Consultar(Persona objeto) {
        try {
            //CONSULTAMOS LOS OBJETOS ALMACENADOS EN LA BASE DE DATOS Y LOS RETORNAMOS EN UN ARREGLO DE TIPO Persona
            Persona[] personas = null;
            this.open();
            ObjectSet resultados = this.oc.get(objeto);
            int i = 0;
            if (resultados.hasNext()) {
                personas = new Persona[resultados.size()];
                while (resultados.hasNext()) {
                    personas[i] = (Persona) resultados.next();
                    i++;
                }
            }
            this.oc.close();
            return personas;
        } catch (DatabaseClosedException | DatabaseReadOnlyException e) {
            System.out.println("bdoo.Controlador.insertarPersona() : " + e);
            return null;
        }
    }

    public boolean Eliminar(Persona objeto) {
        try {
            //CONSULTAMOS LOS OBJETOS ALMACENADOS EN LA BASE DE DATOS Y SI EXISTE UNA COINCIDENCIA LA ELIMINAMOS            
            this.open();
            ObjectSet resultados = this.oc.get(objeto);
            if (resultados.size() > 0) {
                Persona persona = (Persona) resultados.next();
                this.oc.delete(persona);
                this.oc.close();
                return true;
            } else {
                this.oc.close();
                return false;
            }
        } catch (DatabaseClosedException | DatabaseReadOnlyException e) {
            System.out.println("bdoo.Controlador.insertarPersona() : " + e);
            return false;
        }
    }
    }
