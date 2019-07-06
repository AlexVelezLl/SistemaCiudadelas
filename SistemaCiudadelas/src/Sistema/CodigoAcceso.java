/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sistema;
import java.time.LocalDateTime; 

/**
 *Clase que modela a un codigo Acceso
 * @author joangie
 */
public class CodigoAcceso {
    private LocalDateTime fechaIngreso; 
    private boolean used; 
    private String codigo; 
    /**
     * Constructor vacio de CodigoAcceso
     */
    public CodigoAcceso(){
    
    }
/**
 * Constructor del codigo de acceso
 * @param fechaIngreso LocalDateTime Fecha de ingreso para el codigo de acceso
 * @param codigo String con el codigo 
 */
    public CodigoAcceso(LocalDateTime fechaIngreso, String codigo){
        this.fechaIngreso = fechaIngreso;
        this.used = false;
        this.codigo = codigo;
    }
/**
 * Metodo que cambiara el estado de uso del codigo de acceso
 * @param used 
 */
    public void setUsed(boolean used) {
        this.used = used;
    }
/**
 * Metodo que obtendra la fecha de ingreso 
 * @return fechaIngreso LocalDateTime de la fecha de ingreso
 */
    public LocalDateTime getFechaIngreso() {
        return fechaIngreso;
    }
/**
 * Metodo que obtiene el codigo
 * @return codigo String codigo de acceso
 */
    public String getCodigo() {
        return codigo;
    }
/**
 * Metodo que obtiene si el codigo ya ha sido usado
 * @return boolean true si ya ha sido usado y false si no ha sido usado
 */
    public boolean isUsed() {
        return used;
    }
    
    
    
    
}
