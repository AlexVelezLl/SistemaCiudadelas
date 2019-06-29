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
/**
 *
 * @author Alex Velez
 */
public class Residente extends Usuario{
    
private String nombre;
    private String correo;
    private String ID;
    private String pinAcceso;
    private String telefono;
    private Casa casa;
    private ArrayList<Visitante> visitantes;
    private ArrayList<String> vehiculos;
    private ArrayList<CodigoAcceso> codigosAcceso;

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public String getID() {
        return ID;
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

    public ArrayList<Visitante> getVisitantes() {
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
        this.ID = ID;
        this.telefono = telefono;
        this.casa = casa;
        visitantes = new ArrayList() ;
        vehiculos = new ArrayList();
        codigosAcceso = new ArrayList();
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
        this.ID = ID;
        this.telefono = telefono;
        this.casa = casa;
        visitantes = new ArrayList() ;
        vehiculos = new ArrayList();
        codigosAcceso = new ArrayList();
        this.pinAcceso = pinAcceso;
    }
    
    
    private static LocalDateTime consultarFecha(){
        Scanner sc = new Scanner(System.in);
        String year, month, day,hour;
        LocalDateTime time;
        do{
            System.out.print("Año: ");
            year = sc.nextLine();
            System.out.print("Mes: ");
            month = sc.nextLine();
            System.out.print("Dia: ");
            day= sc.nextLine();
            System.out.print("Hora: ");
            hour= sc.nextLine();
            try{
                time = LocalDateTime.of(Integer.parseInt(year),Integer.parseInt(month),Integer.parseInt(day),Integer.parseInt(hour),0);
            }catch(Exception e){
                System.out.println("Usted ha ingresado una fecha invalida, por favor ingrese una fecha valida.");
                time = null;
            }
            LocalDateTime today= LocalDateTime.now();
            LocalDateTime fIngreso=LocalDateTime.of(Integer.parseInt(year),Integer.parseInt(month),Integer.parseInt(day),Integer.parseInt(hour),0);
                if((fIngreso.isBefore(today))|| (fIngreso.equals(today) && fIngreso.getHour()<today.getHour())){
                    System.out.println("Ha ingresado una fecha pasada");
                    time = null;
            }
            
        }while(time==null);
        return time;
    }
    
    public CodigoAcceso generarCodigoAcceso(){
        LocalDateTime fIngreso= Residente.consultarFecha();
        String cod= ""; 
         Random rnd=new Random();
            for(int i=0;i<4;i++){
                cod +=(char)(rnd.nextInt(26)+65);
            }
            for(int i=0;i<4;i++){
                cod +=Integer.toString((int)(rnd.nextInt(9)));
            }
            for (CodigoAcceso a: this.getCodigosAcceso()){
                String code= a.getCodigo();
                if(cod.equals(code)==false){
                    CodigoAcceso c= new CodigoAcceso(fIngreso,cod);
                    return c; 
                }
                
            }
            
                return null; 
    }
    

    public void registrarVisitante(){
        Scanner sc = new Scanner(System.in); 
        System.out.print("Nombre: ");
        String nomV= sc.nextLine();
        System.out.print("ID: ");
        String idV= sc.nextLine();
        System.out.print("Correo: ");
        String correoV= sc.nextLine(); 
        Residente residenteV= this;
            for (Visitante visita : visitantes){
                String v_id= visita.getId();
                if(v_id.equals(idV)==false){
                    System.out.println("Registrando visitante... ");
                    CodigoAcceso cd= generarCodigoAcceso();
                    Visitante a= new Visitante(nomV,idV,correoV,cd,residenteV); 
                    visitantes.add(a);
                }
            
            }
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
        String id= sc.nextLine(); 
        
        for(Visitante vis : visitantes){
            String codigo_v= vis.getCodigoAcceso().getCodigo();
            if(id.equals(codigo_v)){
                visitantes.remove(vis);
            }
        }
    }

    
    public void verListaDeVisitantes(){
        for(Visitante v: visitantes){
            if(!v.getCodigoAcceso().isUsed()){
            System.out.println(v);
            }
    }
}
}
