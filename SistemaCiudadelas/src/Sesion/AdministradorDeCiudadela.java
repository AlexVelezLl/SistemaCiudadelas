/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sesion;
import Sistema.*;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.ArrayList;
import Simulacro.RegistroIngreso;
/**
 *
 * @author Alex Velez
 */
public class AdministradorDeCiudadela extends Usuario{
    private String nombre, identificacion, correo;
    private LocalDateTime fInicio,fFin;
    private Scanner sc;

    public AdministradorDeCiudadela(String nombre, String identificacion, String correo, LocalDateTime fInicio,LocalDateTime fFin){
    super();
    this.nombre = nombre;
    this.identificacion = identificacion;
    this.correo = correo;
    this.fInicio = fInicio;
    this.fFin = fFin;
    sc = new Scanner(System.in);
    }
    public String getNombre() {
        return nombre;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public String getCorreo() {
        return correo;
    }

    public LocalDateTime getfInicio() {
        return fInicio;
    }

    public LocalDateTime getfFin() {
        return fFin;
    }
    
    public void generarReporteVisitas(ArrayList<Ciudadela> ciuds){
        Ciudadela c = getMineCiud(ciuds);
        ArrayList<RegistroIngreso> registros = c.getIngresos();
        for (RegistroIngreso r: registros){
            System.out.print("Fecha de Ingreso: "+r.getFIngreso());
            switch(r.getTipoIngreso()){
                case "Residente.Peaton":
                    System.out.print(", RESIDENTE, Nombre del Residente: "+r.getNomResidente()+", Mz: "+r.getMz()+", Villa:"+r.getVilla());
                    break;
                case "Residente.Vehiculo":
                    System.out.print(", RESIDENTE, Nombre del Residente: "+r.getNomResidente()+", Mz: "+r.getMz()+", Villa:"+r.getVilla()+", Numero de matricula: "+r.getMatricula());
                    break;
                case "Visitante":
                    System.out.print(", VISITANTE, Nombre del Residente: "+r.getNomResidente()+", Mz: "+r.getMz()+", Villa:"+r.getVilla()+", Nombre del visitante: "+r.getNomVisitante());
                    break;
            }
        }
    }
    public void registrarResidente(SistemaCiudadelas sist){
        ArrayList<Ciudadela> ciudadelas = sist.getCiudadelas();
        String nombreR, correoR, id, telefono,mz,villa;
        Casa casa;
        Ciudadela c = getMineCiud(ciudadelas);
        System.out.println("Ingrese el nombre del residente: ");
        nombreR = sc.nextLine();
        System.out.println("Ingrese el correo del residente: ");
        correoR = sc.nextLine();
        System.out.println("Ingrese el id del residente: ");
        id = sc.nextLine();
        System.out.println("Ingrese el telefono del residente: ");
        telefono = sc.nextLine();
        System.out.println("Para la ciuadela "+c.getNombre()+" tenmos las siguientes casas disponibles: ");
        for (Casa ca:c.getCasas()){
            if(ca.getResidente()==null){
                System.out.println("Casa de la Manzana "+ca.getManzana()+", villa " + ca.getVilla());
            }
        }
        Casa casaResidente=null;
        do{
            System.out.println("Que casa desea elegir?: ");
            System.out.println("Manzana: ");
            mz = sc.nextLine();
            System.out.println("Villa: ");
            villa = sc.nextLine();            
            for(Casa newCasa:c.getCasas()){
                if(newCasa.getManzana().equals(mz)&&newCasa.getVilla().equals(villa)&&newCasa!=null){
                    casaResidente =newCasa;
                }
            }
            if(casaResidente==null){
                System.out.println("Casa incorrecta, por favor elija una casa de entre las casas disponibles");
            }
        }while(casaResidente==null);
        Residente resid = new Residente(nombreR,correoR,id,telefono,casaResidente);
        casaResidente.setResidente(resid);
        sist.agregarUsuario(resid);
        System.out.println("El residente se ha creado con exito, sus credenciales son: ");
        System.out.println("Username: "+resid.getUsername());
        System.out.println("Password: "+resid.getPassword());
    }
    private Ciudadela getMineCiud(ArrayList<Ciudadela> ciuds){
        for(Ciudadela c : ciuds){
            if(c.getAdm().equals(this)){
                return c;
            }
        }
        return null;
    }
    @Override
    public boolean equals(Object obj){
        if(obj!=null&&obj instanceof AdministradorDeCiudadela){
            if(((AdministradorDeCiudadela)obj).getIdentificacion().equals(identificacion)&& ((AdministradorDeCiudadela)obj).getCorreo().equals(correo)){
                return true;
            }
        }
        return false;
    }
    
}
