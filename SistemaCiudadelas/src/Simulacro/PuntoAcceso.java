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
/**
 *
 * @author Alex Velez
 */
public abstract class PuntoAcceso {
    protected Ciudadela ciudadela;
    
    
    public abstract RegistroIngreso comprobarAcceso();
    public PuntoAcceso(Ciudadela ciudadela){
        this.ciudadela = ciudadela;
    }
    
    //funcion que permita calcular el tiempo en segundos entre dos instantes.
    public double CalcularTiempo(LocalTime t1, LocalTime t2  ){
        Duration duracion = Duration.between(t1,t2);
        return duracion.getSeconds();                                     
    }
    
    public Residente ObtenerResidente(String nombre, String manzana, String villa){
        for (Residente r : ciudadela.getResidentes()){
        if(r.getNombre().equals(nombre) && r.getCasa().getManzana().equals(manzana) 
                && r.getCasa().getVilla().equals(villa)){
                return r;
        }
        }        
        return null;
    }
    
    //Sobrecarga de metodos. Se puede acceder a un residente desde distintos atrbutos
    public Residente ObtenerResidente(String numCedula, String PinAcceso ){
        for (Residente r : ciudadela.getResidentes()){
            if (r.getID().equals(numCedula)&& r.getPinAcceso().equals(PinAcceso)) {
                return r;                
            }
        }
        return null;
    }
    
    //Sobrecarga del metodo. Obtener el residente desde su vehiculo
    public Residente ObtenerResidente(String matricula){
        for (Residente r : ciudadela.getResidentes()){
            if (r.getVehiculos().contains(matricula)){
                return r;                
            }
        }
        return null;

    }
    

    public Residente ObtenerResidenteporCodigo(String codigo){
        for(Residente r: ciudadela.getResidentes()){
            for(CodigoAcceso c: r.getCodigosAcceso())
                if (c.getCodigo().equals(codigo)) {
                    return r;
                    
                }
            
        }
        return null;
    }
    
    public Visitante ObtenerVisitante(String codigo){
        for(Residente r: ciudadela.getResidentes()){
            for(Visitante v: r.getVisitantes()){
                if (v.getCodigoAcceso().getCodigo().equals(codigo)) {
                    return v;
                    
                }
            }
        }
        return null;
        
    }
    
}
    
        
