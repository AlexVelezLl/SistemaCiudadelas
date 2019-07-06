/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Simulacro;
import Sistema.*;
import java.time.Duration;
import java.time.LocalTime;
import Sesion.Residente;
import utilities.Persona;
/**
 * Clase usada para definir el comportamiento basico de punto acceso
 * @author Valeria Barzola
 * @author Alex Velez
 */
public abstract class PuntoAcceso {
    protected Ciudadela ciudadela;

    /**
     * Metodo abstracto. Permitirá el acceso a la ciudadela, creando un registro del ingreso.
     * @return un objeto RegistroIngreso con la informacion del ingreso realizado.
     */
    public abstract RegistroIngreso comprobarAcceso();
    
    /**
     * Constructor de un punto de acceso de una ciudadela
     * @param ciudadela Objeto Ciudadela a la cual pertenecerá el Punto de Acceso.
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
     * Metodo para obtener un residente con los parámetros nombre, manzana y villa.
     * @param nombre String nombre con el nombre del residente
     * @param manzana String manzana con la manzana de la casa del residente a ingresar/visitar.
     * @param villa String villa con la villa de la casa del residente a ingresar/visitar.
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
     * @param numCedula String con el numero de cedula del Residente
     * @param PinAcceso String con el pin de acceso del residente.
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
     * @param matricula String con la mtricula del vehiculo del residente.
     * @return el residente con el atributo especificado
     */
    public Residente ObtenerResidente(String matricula){
        for (Residente r : ciudadela.getResidentes()){
            for(Vehiculo v: r.getVehiculos()){
                if(v.getMatricula().equals(matricula)){
                    return r;
                } 
            }
        }
        return null;
    }
    
    /**
     * Metodo para obtener un residente por el codigo
     * @param codigo String con el codigo de acceso pertenecientes a un residente.
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
     * @param codigo String con el codigo por el cual se accederá al visitante
     * @return  visitante con el atributo especificado
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
    
        
