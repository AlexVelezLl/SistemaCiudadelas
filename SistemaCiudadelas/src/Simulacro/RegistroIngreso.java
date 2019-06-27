/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Simulacro;
import java.time.LocalDate;
import Sesion.Residente;

/**
 *
 * @author Alex Velez
 */
public class RegistroIngreso {
    private LocalDate fingreso;
    private double duracionIngreso;
    private String tipoingreso;
    private String nomResidente;
    private String mz;
    private String villa;
    private String nomVisitante;
    private String matricula;
    
    
    

    //Constructor para crear el registro de Ingreso de un peaton que es Residente
    public RegistroIngreso(LocalDate fingreso, double duracionIngreso, Residente residente){
        this.fingreso=fingreso;
        this.duracionIngreso=duracionIngreso;
        this.tipoingreso="Residente.peaton";
        nomResidente= residente.getNombre();
        mz=residente.getCasa().getManzana();
        villa=residente.getCasa().getVilla();
        
        
    }
    
    //Constructor para crear el registro de Ingreso de un vehiculo Residente
    public RegistroIngreso(LocalDate fingreso, double duracionIngreso,Residente residente, String matricula){
        this(fingreso,duracionIngreso,residente);
        this.tipoingreso="Residente.vehiculo";
        this.matricula=matricula;
    }
    
    //Constructor para crear el registro de Ingreso de un visitante
    public RegistroIngreso(String nomVisitante, LocalDate fingreso, double duracionIngreso,Residente residente){
        this(fingreso,duracionIngreso,residente);
        this.tipoingreso ="Visitante";
        this.nomVisitante=nomVisitante;                
        
    } 
    
}
