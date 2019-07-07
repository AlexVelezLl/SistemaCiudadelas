/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sistema;
import Sesion.Residente;
import utilities.*;

/**
 *Clase que se modela como un visitante
 * @author joangie
 */
public class Visitante implements Persona{
    private String nombre;
    private String id;
    private String correo;
    private Residente residenteAVisitar;
    private CodigoAcceso codigoAcceso; 
    
    /**
     * Constructor vacio de visitante
     */
    public Visitante(){
    }
    
    /**
     * Constructor de visitante
     * @param nombre String con el nombre de visitante
     * @param id String con el id de visitante
     * @param correo String con el correo de visitante 
     * @param codigoAcceso Objeto tipo CodigoAcceso del visitante
     * @param residenteAVisitar Objeto de tipo Residente al que visitara el visitante
     */
    public Visitante(String nombre, String id, String correo, CodigoAcceso codigoAcceso, Residente residenteAVisitar) {

        this.nombre = nombre;
        this.id = id;
        this.correo = correo;
        this.residenteAVisitar = residenteAVisitar;
        this.codigoAcceso = codigoAcceso;
    }

    /**
     * Constructor de visitante sin correo
     * @param nombre String con el nombre de visitante
     * @param id String con el id del visitante
     * @param cod CodigoAcceso del visitante
     * @param residenteAVisitar 
     */
    
        public Visitante(String nombre, String id,CodigoAcceso cod,Residente residenteAVisitar) {
            this.nombre = nombre;
            this.id = id;
            this.residenteAVisitar = residenteAVisitar;
            this.codigoAcceso= cod; 
            this.correo= null; 
        }
/**
 * Metodo que permite cambiar correo de visitante
 * @param correo 
 */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

/**
 * Metodo que permite obtener el nombre del visitante
 * @return nombre String con el nombre de visitante
 */
    public String getNombre() {
        return nombre;
    }
/**
 * Metodo que permite obtener el id del visitante
 * @return id String con el id de visitante
 */
    public String getId() {
        return id;
    }

/**
 * Metodo que permite obtener el correo del visitante
 * @return correo String con el correo de visitante
 */
    public String getCorreo() {
        return correo;
    }
    
/**
 * Metodo que permite obtener el Residente al que visitara el visitante
 * @return residente Residente que sera visitado
 */

    public Residente getResidenteAVisitar() {
        return residenteAVisitar;
    }
/**
 * Metodo que permite obtener el codigo de Acceso del visitante
 * @return codigoAcceso CodigoAcceso del visitante 
 */
    public CodigoAcceso getCodigoAcceso() {
        return codigoAcceso;
    }
/**
 * Metodo que permite cambiar el codigo de acceso
 * @param codigoAcceso Codigoacceso por el que sera cambiado
 */
    public void setCodigoAcceso(CodigoAcceso codigoAcceso) {
        this.codigoAcceso = codigoAcceso;
    }
    
    /**
     * Metodo que muestra por pantalla la informacion del visitante
     * @return String que contendra nombre,id,correo, residente a visitar, codigo de acceso
     */
      public String toString() {
        return "Nombre: " + nombre + ", id: " + id + ", Correo: " + correo + ", Residente a visitar: " + residenteAVisitar.getNombre() + codigoAcceso;
    }
    
}

