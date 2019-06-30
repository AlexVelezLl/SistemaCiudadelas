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
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;
import utilities.Validaciones;
/**
 *
 * @author Alex Velez
 */
public class AdministradorDeCiudadela extends Usuario{
    private final String nombre, id, correo;
    private final LocalDateTime fInicio,fFin;
    private final Scanner sc;

    public AdministradorDeCiudadela(String nombre, String identificacion, String correo, LocalDateTime fInicio,LocalDateTime fFin){
    super();
    this.nombre = nombre;
    this.id = identificacion;
    this.correo = correo;
    this.fInicio = fInicio;
    this.fFin = fFin;
    sc = new Scanner(System.in);
    }
    public AdministradorDeCiudadela(String nombre, String identificacion, String correo, LocalDateTime fInicio,LocalDateTime fFin,String username, String password){
        super(username,password);
        this.nombre = nombre;
        this.id = identificacion;
        this.correo = correo;
        this.fInicio = fInicio;
        this.fFin = fFin;
        sc = new Scanner(System.in);
    }
    public String getNombre() {
        return nombre;
    }

    @Override
    public String getId() {
        return id;
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
        String nomArchivo= "ReporteVisitas";
        LocalDateTime today= LocalDateTime.now(); 
        nomArchivo= nomArchivo+ today.getYear()+ today.getMonth()+ today.getDayOfMonth()+"-"+ today.getHour()+today.getMinute()+ today.getSecond()+".csv";
        try {
            File directorio=new File("Reportes"); 
            directorio.mkdir();
            FileWriter fw = new FileWriter("Reportes/"+nomArchivo);
            fw.append("Fecha Ingreso,TipoDeIngreso,Nombre del residente,Manzana,Villa,Nombre del visitante, Matricula\n");
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
            JOptionPane.showMessageDialog(null,"Su ha generado el reporte con exito! Su nombre es \"" + nomArchivo+"\" y se encuentra en la carpeta reportes");   
            
        }catch (IOException e){
            e.printStackTrace();
        }
       
    }
    public void registrarResidente(SistemaCiudadelas sist){
        ArrayList<Ciudadela> ciudadelas = sist.getCiudadelas();
        String nombreR, correoR, idR, telefono,mz,villa,pinAcceso,matricula,prop;
        Vehiculo v;
        String [] pin;
        boolean pinValido;
        Ciudadela c = getMineCiud(ciudadelas);
        idR = Validaciones.ValidarId(sist.getUsuarios(),"Residente");
        System.out.print("Ingrese el nombre del residente: ");
        nombreR = sc.nextLine();
        System.out.print("Ingrese el correo del residente: ");
        correoR = sc.nextLine();
        System.out.print("Ingrese el telefono del residente: ");
        telefono = sc.nextLine();
        JOptionPane.showMessageDialog(null,"Para la ciuadela "+c.getNombre()+" tenmos las siguientes casas disponibles: ");
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
                JOptionPane.showMessageDialog(null,"Casa incorrecta, por favor elija una casa de entre las casas disponibles");
            }
        }while(casaResidente==null);
        do{
            pinValido=false;
            System.out.println("Ingrese su pin de Acceso(4 Digitos)");
            pinAcceso = sc.nextLine();
            if(pinAcceso.length()==4){
                pinValido = true;
                pin = pinAcceso.split("");
                for(String digito:pin){
                    pinValido = pinValido & (Validaciones.isNumeric(digito)||digito.equals("0"));
                }
            }
            if(!pinValido){
                JOptionPane.showMessageDialog(null,"Pin invalido, por favor asegurese de ingrese un pin de 4 digitos ");
            }
        }while (pinValido);
        do{
            System.out.print("Ingrese la matricula de su vehiculo: ");
            matricula = sc.nextLine();
            if(!Validaciones.matriculaValida(matricula,getMineCiud(sist.getCiudadelas()))){
                JOptionPane.showMessageDialog(null,"La matricula que usted ingreso es incorrecta, o esta siendo usada por otro residente, por favor ingrese una matricula valida");
            }
        }while(!Validaciones.matriculaValida(villa,getMineCiud(sist.getCiudadelas())));
        System.out.print("Ingrese el nombre del propietario del vehiculo: ");
        prop = sc.nextLine();
        v = new Vehiculo(matricula,prop);
        Residente resid = new Residente(nombreR,correoR,idR,telefono,casaResidente,pinAcceso,v);
        casaResidente.setResidente(resid);
        sist.agregarUsuario(resid);
        JOptionPane.showMessageDialog(null,"El residente se ha creado con exito, sus credenciales son: "+resid+"Y se le ha enviado a su e-mail");
    }
    private Ciudadela getMineCiud(ArrayList<Ciudadela> ciuds){
        for(Ciudadela c : ciuds){
            if(c.getAdm().equals(this)){
                return c;
            }
        }
        return null;
    }
}
