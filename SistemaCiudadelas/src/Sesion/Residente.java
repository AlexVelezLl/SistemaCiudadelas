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
 * Clase hija de Usuario que modela a un Residente.
 * @author joangie
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

    /**
     * Getter de nombre de residente
     * @return String con el nombre del residente
     */
    public String getNombre() {
        return nombre;
    }
    /**Getter de correo del residente
     * 
     * @return String con el correo del residente
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Getter de id de residente
     * @return String con el id de residente
     */
@Override
    public String getId() {
        return id;
    }
/**
 * Getter de PinAcceso 
 * @return String con el pin de acceso del residente
 */
    public String getPinAcceso() {
        return pinAcceso;
    }
/**
 * Getter de telefono de residente
 * @return String del telefono de residente
 */
    public String getTelefono() {
        return telefono;
    }
/**
 * Getter de casa residente 
 * @return Un objeto casa del residente donde estarian la manzana y villa
 */
    public Casa getCasa() {
        return casa;
    }
/**
 * getter de la lista Visitantes 
 * @return ArrayList de tipo persona de visitantes del residente
 */
    public ArrayList<Persona> getVisitantes() {
        return visitantes;
    }
/**
 * getter de vehiculos
 * @return ArrayList de tipo Vehiculo con los vehiculos del residente
 */
    public ArrayList<Vehiculo> getVehiculos() {
        return vehiculos;
    }
/**
 * Getter codigo de acceso
 * @return un arraylist del tipo codigo de acceso que tiene el residente
 */
    public ArrayList<CodigoAcceso> getCodigosAcceso() {
        return codigosAcceso;
    }
/**
 * Constructor vacio de residente
 */
    public Residente(){
    }
/**
 * Constructor de residente
 * @param nombre String con el nombre de residente
 * @param correo String con el correo de residente
 * @param ID String con la identificacion del residente
 * @param telefono String con el telefono de residente
 * @param casa Objeto tipo casa que contiene manzana y villa de la casa del residente
 * @param pinAcceso String con el pin de acceso del residente
 * @param vehiculo Objeto tipo vehiculo que tiene el residente
 */
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
    /**
     * Constructor de Residente
     * @param nombre String con el nombre de residente
     * @param correo String con el correo de residente
     * @param ID string con la identificacion del residente
     * @param telefono String con el telefono de residente
     * @param casa Objeto tipo casa del residente 
     * @param pinAcceso string con el pin de acceso del residente
     * @param vehiculo Vehiculo del residente
     * @param username String con el username del residente para iniciar sesion
     * @param password  String con password del residente
     */
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
    
    /**
     * Generar Codigo de Acceso
     * genera un codigo de acceso para una visita futura 
     * @return CodigoAcceso
     */
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
    
        
/**
 * Registra un visitante preguntando por teclado al residente y genera un codigo de acceso para la visita futura que es enviado por correo
 */
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
        String mensaje= "Saludos, estimado "+this.nombre+"\nEl codigo que se ha generado para su visita es el siguiente: "+c.getCodigo()+"\nAtentamente, \nEquipo Sistema Ciudadelas"; 
        mail.enviarCorreo(correo, mensaje);
        JOptionPane.showMessageDialog(null,"Se ha registrado al visitante exitosamente, el codigo de acceso ha sido enviado a su correo");
    }
    
    /**
     * Registrar Visitante con parametros, este es utilizado para cuando un visitante quiere ingresar por garita pero no cuenta con un codigo de acceso en ese momento
     * por lo que se genera un codigo de acceso en ese momento y este se lo envia por correo al residente 
     * @param nombre String con el nombre de visitante
     * @param cedula String con el nombre de cedula de visitante
     */
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
        String mensaje= "Saludos, estimado "+this.nombre+"\n El codigo que se ha generado para su visita es el siguiente: "+codi.getCodigo()+"\nAtentamente, \nEquipo Sistema Ciudadelas"; 
        mail.enviarCorreo(residenteV.getCorreo(), mensaje); 
        
        
    }
       /**
        * Registra un vehiculo por su placa y propietario 
        * @param ciuds 
        */
    
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

    /**
     * Cambia el pin de acceso que se le otorga al residente al registrarlo
     */
    public void cambiarPin() {
        System.out.println("Ingrese pin: ");
        boolean verificarPin= sc.hasNextInt();
        String pinNuevo= sc.nextLine(); 
        if(verificarPin==true&& pinNuevo.length()==4){
            this.pinAcceso = pinNuevo;
        }
        JOptionPane.showMessageDialog(null, "Su pin se ha cambiado con exito");
    }
    
    /**
     * borra un visitante de la lita de visitantes 
     */
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

    /**
     * Se muestra por pantalla los visitantes activos hasta el momento
     */
    public void verListaDeVisitantes(){
        System.out.println("Saludos estimado, "+nombre+"\nLos visitantes activos hasta el momento: ");
        for(Persona v: visitantes){
            if(!((Visitante)v).getCodigoAcceso().isUsed()){
            System.out.println((Visitante)v);
            }
        }
    }
    
    /**
     * Obtener mi ciudadela
     * Se obtiene la ciudadela en la que se encuentra registrado el residente
     * @param ciuds ArrayList de tipo ciudadela con las ciudadelas del sistema
     * @return retorna la ciudadela a la que pertenece el residente
     */

    public Ciudadela getMineCiud(ArrayList<Ciudadela> ciuds){
        for(Ciudadela ciud : ciuds){
            if (ciud.getResidentes().contains(this)){
                return ciud;
            }
        }
        return null;
    }
}

