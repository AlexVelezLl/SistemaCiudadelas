/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sesion;
import Sistema.Usuario;
import Sistema.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random; 
import java.util.Scanner;
import javax.swing.JOptionPane;
import utilities.*;
/**
 *
 * @author Alex Velez
 */
public class Residente extends Usuario{
    
private String nombre;
    private String correo;
    private String id;
    private String pinAcceso;
    private String telefono;
    private Casa casa;
    private ArrayList<Persona> visitantes;
    private ArrayList<Vehiculo> vehiculos;
    private ArrayList<CodigoAcceso> codigosAcceso;
    private Scanner sc;
    private Mailer mail; 

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

@Override
    public String getId() {
        return id;
    }

    public String getPinAcceso() {
        return pinAcceso;
    }

    public String getTelefono() {
        return telefono;
    }

    public Casa getCasa() {
        return casa;
    }

    public ArrayList<Persona> getVisitantes() {
        return visitantes;
    }

    public ArrayList<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public ArrayList<CodigoAcceso> getCodigosAcceso() {
        return codigosAcceso;
    }

    public Residente(){
    }

    public Residente(String nombre, String correo, String ID, String telefono, Casa casa,String pinAcceso,Vehiculo vehiculo) {
        this.nombre = nombre;
        this.correo = correo;
        this.id = ID;
        this.telefono = telefono;
        this.casa = casa;
        this.pinAcceso = pinAcceso;
        visitantes = new ArrayList<>() ;
        vehiculos = new ArrayList<>();
        vehiculos.add(vehiculo);
        codigosAcceso = new ArrayList<>();
        sc = new Scanner(System.in);
        mail= new Mailer(); 
            
        
    }
    public Residente(String nombre, String correo, String ID, String telefono, Casa casa,String pinAcceso,Vehiculo vehiculo,String username, String password) {
        super(username,password);
        this.nombre = nombre;
        this.correo = correo;
        this.id = ID;
        this.telefono = telefono;
        this.casa = casa;
        visitantes = new ArrayList<>() ;
        vehiculos = new ArrayList<>();
        vehiculos.add(vehiculo);
        codigosAcceso = new ArrayList<>();
        this.pinAcceso = pinAcceso;
        sc = new Scanner(System.in);
        mail= new Mailer(); 

        
    }
    
    private CodigoAcceso generarCodigoAcceso(){
        LocalDateTime fIngreso;
        do{
            fIngreso= Validaciones.consultarFecha();
            if(fIngreso.isBefore(LocalDateTime.now())){
                JOptionPane.showMessageDialog(null,"Usted ha ingresado una fecha pasada, por favor ingrese una fecha valida");
            }
        }while(fIngreso.isBefore(LocalDateTime.now()));
        String cod= "";
        Random rnd=new Random();
        boolean codUnic;
        do{
            codUnic = true;
            for(int i=0;i<4;i++){
                cod +=(char)(rnd.nextInt(26)+65);
            }
            for(int i=0;i<4;i++){
                cod +=Integer.toString((int)(rnd.nextInt(9)));
            }
            for (CodigoAcceso a: codigosAcceso){
                String code= a.getCodigo();
                if(cod.equals(code)){
                    codUnic = false;
                }
            }
        }while(!codUnic);
        return new CodigoAcceso(fIngreso,cod);
    }
    
        

    public void registrarVisitante(){
        String idV, nomV, correoV;
        Residente residenteV;
        CodigoAcceso c;
        idV = Validaciones.ValidarId(visitantes, "Visitante");
        System.out.print("Nombre: ");
        nomV= sc.nextLine();
        System.out.print("Correo: ");
        correoV= sc.nextLine(); 
        residenteV= this;
        c = generarCodigoAcceso();
        visitantes.add(new Visitante(nomV,idV,correoV,c,residenteV));
        String mensaje= "Saludos, estimado "+this.nombre+"/n"+"El codigo que se ha generado para su visita es el siguiente: "+c.getCodigo()+"/n"+"Equipo Sistema Ciudadelas"; 
        mail.enviarCorreo(correo, mensaje);
        JOptionPane.showMessageDialog(null,"Se ha registrado al visitante exitosamente, el codigo de acceso ha sido enviado a su correo");
    }
    public void registrarVisitante(String nombre, String cedula){
        String correoV;
        Residente residenteV; 
        residenteV= this;
        LocalDateTime fIngreso= LocalDateTime.now();
        
        String cod= "";
        Random rnd=new Random();
        boolean codUnic;
        do{
            codUnic = true;
            for(int i=0;i<4;i++){
                cod +=(char)(rnd.nextInt(26)+65);
            }
            for(int i=0;i<4;i++){
                cod +=Integer.toString((int)(rnd.nextInt(9)));
            }
            for (CodigoAcceso a: codigosAcceso){
                String code= a.getCodigo();
                if(cod.equals(code)){
                    codUnic = false;
                }
            }
        }while(!codUnic);
        
        CodigoAcceso codi= new CodigoAcceso(fIngreso,cod);
    
        visitantes.add(new Visitante(nombre,cedula,codi,residenteV));
        String mensaje= "Saludos, estimado "+this.nombre+"/n"+"El codigo que se ha generado para su visita es el siguiente: "+codi.getCodigo()+"/n"+"Equipo Sistema Ciudadelas"; 
        mail.enviarCorreo(residenteV.getCorreo(), mensaje); 
        
        
    }
       
    
    public void registrarVehiculo(ArrayList<Ciudadela> ciuds){
        Ciudadela ciud = getMineCiud(ciuds);
        String matricula,prop;
        do{
            System.out.print("Por favor, ingrese la matricula de su vehiculo: ");  
            matricula = sc.nextLine();
            if(!Validaciones.matriculaValida(matricula, ciud)){
                JOptionPane.showMessageDialog(null,"La matricula que usted ingreso es incorrecta, o esta siendo usada por otro residente, por favor ingrese una matricula valida");
            }
        }while(!Validaciones.matriculaValida(matricula, ciud));
        System.out.print("Ingrese el nombre del propietario del vehiculo: ");
        prop = sc.nextLine();
        vehiculos.add(new Vehiculo(matricula,prop));
        JOptionPane.showMessageDialog(null,"Su vehiculo se ha registrado exitosamente");
    }

    public void cambiarPin() {
        System.out.println("Ingrese pin: ");
        boolean verificarPin= sc.hasNextInt();
        String pinNuevo= sc.nextLine(); 
        if(verificarPin==true&& pinNuevo.length()==4){
            this.pinAcceso = pinNuevo;
        }
        JOptionPane.showMessageDialog(null, "Su pin se ha cambiado con exito");
    }
    
    public void borrarVisitante(){
        System.out.print("Ingrese codigo de acceso: ");
        String idV; 
        idV = sc.nextLine();
        
        for(Persona vis : visitantes){
            String codigo_v= ((Visitante)vis).getCodigoAcceso().getCodigo();
            if(id.equals(codigo_v)){
                visitantes.remove(vis);
            }
        }
        JOptionPane.showMessageDialog(null,"Se ha borrado al visitante");
    }

    
    public void verListaDeVisitantes(){
        System.out.println("Los visitantes activos hasta el momento: ");
        for(Persona v: visitantes){
            if(!((Visitante)v).getCodigoAcceso().isUsed()){
            System.out.println((Visitante)v);
            }
        }
    }

    public Ciudadela getMineCiud(ArrayList<Ciudadela> ciuds){
        for(Ciudadela ciud : ciuds){
            if (ciud.getResidentes().contains(this)){
                return ciud;
            }
        }
        return null;
    }
}

