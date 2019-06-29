/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sesion;
import Sistema.Usuario;
import Sistema.*;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.ArrayList;
import Simulacro.RegistroIngreso;
import java.io.FileWriter;
import java.io.IOException;
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
        crearArchivoCSV(registros);
       
    }
    public void registrarResidente(SistemaCiudadelas sist){
        ArrayList<Ciudadela> ciudadelas = sist.getCiudadelas();
        String nombreR, correoR, id, telefono,mz,villa;
        Casa casa;
        Ciudadela c = getMineCiud(ciudadelas);
        System.out.print("Ingrese el nombre del residente: ");
        nombreR = sc.nextLine();
        System.out.print("Ingrese el correo del residente: ");
        correoR = sc.nextLine();
        System.out.print("Ingrese el id del residente: ");
        id = sc.nextLine();
        System.out.print("Ingrese el telefono del residente: ");
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
            System.out.print("Manzana: ");
            mz = sc.nextLine();
            System.out.print("Villa: ");
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
        System.out.println("El residente se ha creado con exito, sus credenciales son: "+resid);
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
    
    private void crearArchivoCSV(ArrayList <RegistroIngreso> registros){
        String nomArchivo= "RegistroVisitas";
        LocalDateTime today= LocalDateTime.now(); 
        nomArchivo= nomArchivo+ today.getYear()+ today.getMonth()+ today.getDayOfMonth()+ today.getHour()+today.getMinute()+ today.getSecond()+".csv";
        try {
            FileWriter fw = new FileWriter(nomArchivo);
            fw.append("Fecha Ingreso,TipoDeIngreso,Nombre del residente,Manzana,Villa,Nombre del visitante, Matricula");
            for(RegistroIngreso r: registros){
                String lineaCSV ="";
                switch(r.getTipoIngreso()){
                    case "Residente.Peaton":
                            lineaCSV = r.getFIngreso()+",Residente,"+r.getNomResidente()+","+r.getMz()+","+r.getVilla()+",-,-\n";
                        break;
                    case "Residente.Vehiculo":
                        lineaCSV = r.getFIngreso()+",Residente,"+r.getNomResidente()+","+r.getMz()+","+r.getVilla()+",-,"+r.getMatricula()+"\n";
                        break;
                    case "Visitante":
                        lineaCSV = r.getFIngreso()+",Residente,"+r.getNomResidente()+","+r.getMz()+","+r.getVilla()+","+r.getNomVisitante()+",-\n";       
                        break;
                }
                fw.append(lineaCSV);
            }
            fw.flush();
            fw.close();
            System.out.println("Su archivo se ha creato y se llama: " + nomArchivo);   
            
        }catch (IOException e){
            e.printStackTrace();
        }
        
      
    }
}
