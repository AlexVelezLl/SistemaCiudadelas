/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Simulacro;
import Sistema.Ciudadela;
import java.time.Duration;
import java.time.LocalTime;
import Sesion.Residente;
import Sistema.CodigoAcceso;
import Sistema.Visitante;
import utilities.Persona;
/**
 * Clase usada para definir el comportamiento basico de punto acceso
 * @author Valeria Barzola
 * @author Alex Velez
 */
public abstract class PuntoAcceso {
    protected Ciudadela ciudadela;
    
    /**
     * Metodo abstracto
     */
    public abstract RegistroIngreso comprobarAcceso();
    
    /**
     * Constructor de un punto de acceso de una ciudadela
     * @param ciudadela 
     */
    public PuntoAcceso(Ciudadela ciudadela){
        this.ciudadela = ciudadela;
    }
    /**
     * Metodo que calcula el tiempo entre dos instantes.
     * @param t1 tiempo 1
     * @param t2 tiempo 2
     * @return tiempo transcurrido
     */  
    public double CalcularTiempo(LocalTime t1, LocalTime t2  ){
        Duration duracion = Duration.between(t1,t2);
        return duracion.getSeconds();                                     
    }
    
    
    /**
     * Metodo que busca un residente
     * @param nombre
     * @param manzana
     * @param villa
     * @return el residente con los atributos especificados
     */
    public Residente ObtenerResidente(String nombre, String manzana, String villa){
        for (Residente r : ciudadela.getResidentes()){
        if(r.getNombre().equals(nombre) && r.getCasa().getManzana().equals(manzana) 
                && r.getCasa().getVilla().equals(villa)){
                return r;
        }
        }        
        return null;
    }
    
    /**
     * Sobrecarga de metodo. Obtiene el residente desde su id, numero de cedula
     * @param numCedula
     * @param PinAcceso
     * @return residente con los atributos especificados
     */
    public Residente ObtenerResidente(String numCedula, String PinAcceso ){
        for (Residente r : ciudadela.getResidentes()){
            if (r.getId().equals(numCedula)&& r.getPinAcceso().equals(PinAcceso)) {
                return r;                
            }
        }
        return null;
    }
    
    
    /**
     * Sobrecarga del metodo. Obtiente el residente desde su vehiculo    
     * @param matricula
     * @return el residente con el atributo especificado
     */
    public Residente ObtenerResidente(String matricula){
        for (Residente r : ciudadela.getResidentes()){
            if (r.getVehiculos().contains(matricula)){
                return r;                
            }
        }
        return null;

    }
    
    /**
     * Metodo para obtener un residente por el codigo
     * @param codigo
     * @return el residente con el atributo especificado
     */
    public Residente ObtenerResidenteporCodigo(String codigo){
        for(Residente r: ciudadela.getResidentes()){
            for(CodigoAcceso c: r.getCodigosAcceso())
                if (c.getCodigo().equals(codigo)) {
                    return r;
                    
                }
            
        }
        return null;
    }
    
    
    /**
     * Metodo para obtener el visitante por el codigo 
     * @param codigo
     * @return el visitante con el atributo especificado
     */
    public Visitante ObtenerVisitante(String codigo){
        for(Residente r: ciudadela.getResidentes()){
            for(Persona v: r.getVisitantes()){
                if (((Visitante)v).getCodigoAcceso().getCodigo().equals(codigo)) {
                    return ((Visitante)v);
                    
                }
            }
        }
        return null;
        
    }
    
}
    
        
