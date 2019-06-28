/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sistema;
import Sesion.Residente;

/**
 *
 * @author joangie
 */
public class Visitante {
    private String nombre;
    private String id;
    private String correo;
    private Residente residenteAVisitar;
    private CodigoAcceso codigoAcceso; 
    
    
    public Visitante(){
    
    }
    
    public Visitante(String nombre, String id, String correo, CodigoAcceso codigoAcceso, Residente residenteAVisitar) {

        this.nombre = nombre;
        this.id = id;
        this.correo = correo;
        this.residenteAVisitar = residenteAVisitar;
        this.codigoAcceso = codigoAcceso;
    }

    public Visitante(String nombre, String id, String correo,Residente residenteAVisitar) {

        this.nombre = nombre;
        this.id = id;
        this.correo = correo;
        this.residenteAVisitar = residenteAVisitar;
        this.codigoAcceso= null; 
    }

    public String getNombre() {
        return nombre;
    }

    public String getId() {
        return id;
    }

    public String getCorreo() {
        return correo;
    }

    public Residente getResidenteAVisitar() {
        return residenteAVisitar;
    }

    public CodigoAcceso getCodigoAcceso() {
        return codigoAcceso;
    }

    public void setCodigoAcceso(CodigoAcceso codigoAcceso) {
        this.codigoAcceso = codigoAcceso;
    }
      public String toString() {
        return "nombre: " + nombre + ", id: " + id + ", correo: " + correo + ", residenteAVisitar: " + residenteAVisitar + ", codigoAcceso: " + codigoAcceso;
    }
    
}
