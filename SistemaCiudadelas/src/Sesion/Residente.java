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
        System.out.println("Ingrese la fecha y hora que tiene planificada la visita: ");
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
            for (Persona a: visitantes){
                Visitante vis = (Visitante)a;
                String code = vis.getCodigoAcceso().getCodigo();
                
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
        do{
            System.out.print("Correo: ");
            correoV= sc.nextLine(); 
            if(!correoV.contains("@")){
                JOptionPane.showMessageDialog(null, "El correo que usted ingreso es invalido, por favor ingrese un correo valido");
            }
        }while(!correoV.contains("@"));
            
        c = generarCodigoAcceso();
        visitantes.add(new Visitante(nomV,idV,correoV,c,this));
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
           for (Persona a: visitantes){
                Visitante vis = (Visitante)a;
                String code = vis.getCodigoAcceso().getCodigo();
                
                if(cod.equals(code)){
                    codUnic = false;
                }
            }
        }while(!codUnic);
        
        CodigoAcceso codi= new CodigoAcceso(fIngreso,cod);
    
        visitantes.add(new Visitante(nombre,cedula,codi,this));
        String mensaje= "Saludos, estimado "+this.nombre+"\n El codigo que se ha generado para su visita es el siguiente: "+codi.getCodigo()+"\nAtentamente, \nEquipo Sistema Ciudadelas"; 
        mail.enviarCorreo(correo, mensaje); 
        JOptionPane.showMessageDialog(null,"Se ha registrado al visitante exitosamente, el codigo de acceso ha sido enviado a su correo"+codi.getCodigo());
        
    }
       /**
        * Registra un vehiculo por su placa y propietario 
        * @param ciuds ArrayList de todas las ciudadelas del sistema
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
        String pin;
        do{
            System.out.println("Ingrese pin: ");
            pin = sc.nextLine();
            if(!pin.equals("SALIR")&&(!Validaciones.isNumeric(pin)||pin.length()!=4)){
                JOptionPane.showMessageDialog(null, "Ha ingresado un pin incorrecto, por favor ingrese uno correcto, o escriba \"SALIR\" para salir ");
            }
        }while(!pin.equals("SALIR")&&(!Validaciones.isNumeric(pin)||pin.length()!=4));
        if(!pin.equals("SALIR")){
            JOptionPane.showMessageDialog(null,"Se ha cambiado el pin con exito");
        }
             
    }
    
    /**
     * borra un visitante de la lita de visitantes 
     */
    public boolean borrarVisitante(){
        System.out.print("Ingrese codigo de acceso: ");
        String idV; 
        idV = sc.nextLine();
        boolean borrado=false;
        
        for(Persona vis : visitantes){
            String codigo_v= ((Visitante)vis).getCodigoAcceso().getCodigo();
            boolean used = ((Visitante)vis).getCodigoAcceso().isUsed();
            if(idV.equals(codigo_v)&&!used){
                visitantes.remove(vis);
                JOptionPane.showMessageDialog(null,"Se ha borrado al visitante");
                return true;
            }
        }
        JOptionPane.showMessageDialog(null,"No hay ningun visitante con ese codigo de acceso");
        return false;
        
    }

    /**
     * Se muestra por pantalla los visitantes activos hasta el momento
     */
    public void verListaDeVisitantes(){
        if(visitantes.size()!=0){
            System.out.println("Saludos estimado, "+nombre+"\nLos visitantes activos hasta el momento: ");
            for(Persona v: visitantes){
                if(!((Visitante)v).getCodigoAcceso().isUsed()){
                System.out.println((Visitante)v);
                }
            }
        }else{
             JOptionPane.showMessageDialog(null,"No tiene registrados visitantes activos");       
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
    
    /**
     * Metodo que muestra la informacion del residente
     */
    @Override
    public void mostrarMiInformacion() {
        System.out.println("Información de Residente");
        System.out.println("Nombre: "+nombre);
        System.out.println("Id: "+id);
        System.out.println("Correo: "+correo);
        System.out.println("pinAcceso: "+pinAcceso);
        System.out.println("telefono: "+telefono);
        System.out.println("Casa: "+casa);
        System.out.println("Vehiculos: ");
        for (Vehiculo c:vehiculos){
            System.out.println(c);
        }
    }
}

