/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Simulacro;
import java.time.LocalDateTime;
import Sesion.Residente;

/**
 * Clase que contiene la información del registro creado al ingresar.
 * @author Valeria Barzola
 */
public class RegistroIngreso {
    private LocalDateTime fingreso;
    private double duracionIngreso;
    private String tipoIngreso;
    private String nomResidente;
    private String mz;
    private String villa;
    private String nomVisitante;
    private String matricula;

    
   /**
    * Metodo para obtener la fecha de ingreso.
    * @return la fecha de ingreso.
    */
    public LocalDateTime getFIngreso() {
        return fingreso;
    }

    /**
     * Metodo para obtener el tiempo que duró el proceso de ingreso.
     * @return tiempo transcurrido en el ingreso.
     */
    public double getTiempoIngreso() {
        return duracionIngreso;
    }

    /**
     * Metodo para obtener el tipo de ingreso (Residente peaton, residente vehiculo, visitante)
     * @return tipo de ingreso.
     */
    public String getTipoIngreso() {
        return tipoIngreso;
    }

    /**
     * Metodo para obtener el nombre del residente que accedió a la ciudadela, o el residente a visitar.
     * @return nombre del residente
     */
    public String getNomResidente() {
        return nomResidente;
    }

    /**
     * Metodo para obtener la manzana a ingresar.
     * @return manzana de la casa.
     */
    public String getMz() {
        return mz;
    }

    /**
     * Metodo para obtener la villa a ingresar. 
     * @return villa de la casa.
     */
    public String getVilla() {
        return villa;
    }
      

    /**
     * Metodo para obtener el nombre del visitante que ingresa
     * @return nomobre del visitante
     */
    public String getNomVisitante() {
        return nomVisitante;
    }

    
    /**
     * Metodo para obtener la matricula del vehiculo del residente que ingresa.
     * @return matricula del residente.
     */
    public String getMatricula() {
        return matricula;
    }
    
    /**
     * Constructor para crear el registro de Ingreso de un peaton que es Residente
     * @param fingreso
     * @param duracionIngreso
     * @param residente 
     */
    public RegistroIngreso(LocalDateTime fingreso, double duracionIngreso, Residente residente){
        this.fingreso=fingreso;
        this.duracionIngreso=duracionIngreso;
        this.tipoIngreso="Residente.peaton";
        nomResidente= residente.getNombre();
        mz=residente.getCasa().getManzana();
        villa=residente.getCasa().getVilla();
        
        
    }
    
    /**
     * Constructor para crear el registro de Ingreso de un vehiculo Residente
     * @param fingreso
     * @param duracionIngreso
     * @param residente
     * @param matricula 
     */
    public RegistroIngreso(LocalDateTime fingreso, double duracionIngreso,Residente residente, String matricula){
        this(fingreso,duracionIngreso,residente);
        this.tipoIngreso="Residente.vehiculo";
        this.matricula=matricula;
    }
    
    /**
     * Constructor para crear el registro de Ingreso de un visitante
     * @param nomVisitante
     * @param fingreso
     * @param duracionIngreso
     * @param residente 
     */
    public RegistroIngreso(String nomVisitante, LocalDateTime fingreso, double duracionIngreso,Residente residente){
        this(fingreso,duracionIngreso,residente);
        this.tipoIngreso ="Visitante";
        this.nomVisitante=nomVisitante;                
        
    } 
    
}
