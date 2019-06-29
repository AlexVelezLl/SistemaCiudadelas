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
    private ArrayList<String> vehiculos;
    private ArrayList<CodigoAcceso> codigosAcceso;

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

    public ArrayList<String> getVehiculos() {
        return vehiculos;
    }

    public ArrayList<CodigoAcceso> getCodigosAcceso() {
        return codigosAcceso;
    }

    public Residente(){
    }

    public Residente(String nombre, String correo, String ID, String telefono, Casa casa) {
        this.nombre = nombre;
        this.correo = correo;
        this.id = ID;
        this.telefono = telefono;
        this.casa = casa;
        visitantes = new ArrayList<>() ;
        vehiculos = new ArrayList<>();
        codigosAcceso = new ArrayList<>();
        pinAcceso= "";
        Random rnd= new Random();
        for (int i = 0; i < 4; i++) {
            pinAcceso+=(int)(rnd.nextInt(9));
            
        }
    }
    public Residente(String nombre, String correo, String ID, String telefono, Casa casa,String pinAcceso,String username, String password) {
        super(username,password);
        this.nombre = nombre;
        this.correo = correo;
        this.id = ID;
        this.telefono = telefono;
        this.casa = casa;
        visitantes = new ArrayList<>() ;
        vehiculos = new ArrayList<>();
        codigosAcceso = new ArrayList<>();
        this.pinAcceso = pinAcceso;
    }
    
     
    public CodigoAcceso generarCodigoAcceso(){
        LocalDateTime fIngreso;
        do{
            fIngreso= Validaciones.consultarFecha();
            if(fIngreso.isBefore(LocalDateTime.now())){
                System.out.println("Usted ha ingresado una fecha pasada, por favor ingrese una fecha valida");
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
        Scanner sc = new Scanner(System.in);
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
        System.out.println("Se ha registrado al visitante exitosamente, el codigo de acceso ha sido enviado a su correo");
    }
        
       
    
    
    public void registrarVehiculo(){
        Scanner sc= new Scanner(System.in);
        System.out.print("Ingrese la matricula que desea agregar: ");
        String matricula= sc.nextLine();
        for (String v :vehiculos){
            boolean verificarV= v.equals(matricula);
            if (verificarV==false){
                vehiculos.add(matricula); 
            }
        }
        
    }

    public void cambiarPin() {
        Scanner sc= new Scanner(System.in);
        System.out.println("Ingrese pin: ");
        boolean verificarPin= sc.hasNextInt();
        String pinNuevo= sc.nextLine(); 
            if(verificarPin==true&& pinNuevo.length()==4){
                this.pinAcceso = pinNuevo;
        }
    }
    
    public void borrarVisitante(){
        Scanner sc= new Scanner(System.in);
        System.out.print("Ingrese codigo de acceso: ");
        String idV; 
        idV = sc.nextLine();
        
        for(Persona vis : visitantes){
            String codigo_v= ((Visitante)vis).getCodigoAcceso().getCodigo();
            if(id.equals(codigo_v)){
                visitantes.remove(vis);
            }
        }
    }

    
    public void verListaDeVisitantes(){
        for(Persona v: visitantes){
            if(!((Visitante)v).getCodigoAcceso().isUsed()){
            System.out.println(v);
            }
    }
}
}
