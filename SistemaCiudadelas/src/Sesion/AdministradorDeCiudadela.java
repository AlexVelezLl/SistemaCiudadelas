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
import utilities.*;
/**
 * Clase hija de Usuario que modela a un administrador de ciudadela.
 * @author Alex Velez
 */ 
public class AdministradorDeCiudadela extends Usuario{
    private final String nombre, id, correo;
    private final LocalDateTime fInicio,fFin;
    private final Scanner sc;
    /**
     * Constructor de AdministradordeCiudadela cuyas credenciales se generaran automaticamente.
     * @param nombre String con el nombre del Administrador de la Ciudadela
     * @param identificacion String con el numero de cedula del Administrador de la Ciudadela
     * @param correo String con la direccion de correo electronico del Administrador de laCiudadela
     * @param fInicio LocalDateTime de la fecha de inicio de la administradocion del Administrador de laCiudadela
     * @param fFin LocalDateTime de la fecha de fin de la administradocion del Administrador de laCiudadela
     */
    public AdministradorDeCiudadela(String nombre, String identificacion, String correo, LocalDateTime fInicio,LocalDateTime fFin){
    super();
    this.nombre = nombre;
    this.id = identificacion;
    this.correo = correo;
    this.fInicio = fInicio;
    this.fFin = fFin;
    sc = new Scanner(System.in);
    }
    /**
     * Constructor de AdministradordeCiudadela cuyas credenciales las toma como parametros.
     * @param nombre String con el nombre del Administrador de la Ciudadela
     * @param identificacion String con el numero de cedula del Administrador de la Ciudadela
     * @param correo String con la direccion de correo electronico del Administrador de laCiudadela
     * @param fInicio LocalDateTime de la fecha de inicio de la administradocion del Administrador de laCiudadela
     * @param fFin LocalDateTime de la fecha de fin de la administradocion del Administrador de laCiudadela
     * @param username El nombre de usuario que tendra el Administrador de la Ciudadela en el sistema
     * @param password La contraseña que tendra el Administrador de la ciudadela en el sistema
     */
    public AdministradorDeCiudadela(String nombre, String identificacion, String correo, LocalDateTime fInicio,LocalDateTime fFin,String username, String password){
        super(username,password);
        this.nombre = nombre;
        this.id = identificacion;
        this.correo = correo;
        this.fInicio = fInicio;
        this.fFin = fFin;
        sc = new Scanner(System.in);
    }
    /**
     * Obtiene el nombre del Administrador de la Ciudadela
     * @return String con el nombre del Administrador de la Ciudadela
     */
    public String getNombre() {
        return nombre;
    }
    /**
     * Obtiene la Identificacion del Administrador de la Ciudadela
     * @return String con la Identificacion del administrador de la Ciudadela
     */
    @Override
    public String getId() {
        return id;
    }
    /**
     * Obtiene la direccion de correo electronico del Administrador de la Ciudadela
     * @return String con el correo del Administrador de la Ciudadela
     */
    public String getCorreo() {
        return correo;
    }
    /**
     * Obtiene la fecha de inicio de la administracion del Administrador de la Ciudadela
     * @return LocalDateTime con la fecha de inicio la administracion del Administrador de la Ciudadela
     */
    public LocalDateTime getfInicio() {
        return fInicio;
    }
    /**
     * Obtiene la fecha de fin de la administracion del Administrador de la Ciudadela
     * @return LocalDateTime con la fecha de fin la administracion del Administrador de la Ciudadela
     */
    public LocalDateTime getfFin() {
        return fFin;
    }
    /**
     * Metodo que genera un archivo .csv con el reporte de las visitas que hayan hecho Personas en un determinado periodo.
     * @param ciuds ArrayList con todas las ciudadelas que se encuentran registradas en el sistema
     */
    public void generarReporteVisitas(ArrayList<Ciudadela> ciuds){
        LocalDateTime f1,f2;
        String op;
        Ciudadela c = getMineCiud(ciuds);
        ArrayList<RegistroIngreso> registros,registrosFilt;
        registros = c.getIngresos();
        registrosFilt = new ArrayList<>();
        System.out.println("Ingrese la fecha de inicio del reporte: ");
        f1 = Validaciones.consultarFecha();
        System.out.println("Ingrese la fecha de fin del reporte:  ");
        f2 = Validaciones.consultarFecha();
        System.out.println("Tipos de ingreso: ");
            System.out.println("1)Residente");
            System.out.println("2)Visitante");
            System.out.println("3)Todos");
        do{
            System.out.print("Que tipo de ingreso desea revisar?");
            op = sc.nextLine();
            if(!op.equals("1")||!op.equals("2")||!op.equals("3")){
                JOptionPane.showMessageDialog(null,"No ha ingresado una opcion valida, por favor ingrese una valida");
            }
        }while(!op.equals("1")||!op.equals("2")||!op.equals("3"));
        
        for(RegistroIngreso reg : registros){//Filtrando la informacion
            if(reg.getFIngreso().isAfter(f1) && reg.getFIngreso().isBefore(f2)){
                switch(op){
                    case "1":
                        if(reg.getTipoIngreso().contains("Residente")){
                            registrosFilt.add(reg);
                        }
                        break;
                    case "2":
                        if(reg.getTipoIngreso().equals("Visitante")){
                            registrosFilt.add(reg);
                        }
                        break;
                    default:
                        registrosFilt.add(reg);
                        break;
                }
            }
        }
        String nomArchivo= "ReporteVisitas";
        LocalDateTime today= LocalDateTime.now(); 
        nomArchivo= nomArchivo+ today.getYear()+ today.getMonth()+ today.getDayOfMonth()+"-"+ today.getHour()+today.getMinute()+ today.getSecond()+".csv";
        try {
            File directorio=new File("Reportes"); 
            directorio.mkdir();//Hace una nueva carpeta si Reportes aun no se ha creado
            FileWriter fw = new FileWriter("Reportes/"+nomArchivo);
            fw.append("Fecha Ingreso,TipoDeIngreso,Nombre del residente,Manzana,Villa,Nombre del visitante, Matricula\n");
            for(RegistroIngreso r: registrosFilt){
                String lineaCSV ="";
                switch(r.getTipoIngreso()){
                    case "Residente.peaton":
                            lineaCSV = r.getFIngreso()+",Residente,"+r.getNomResidente()+","+r.getMz()+","+r.getVilla()+",-,-\n";
                        break;
                    case "Residente.vehiculo":
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
    /**
     * Metodo que pregunta todos los datos de un residente para registrarlo en el sistema
     * @param sist El sistema general de Sistema Ciudadelas
     */
    public void registrarResidente(SistemaCiudadelas sist){
        ArrayList<Ciudadela> ciudadelas = sist.getCiudadelas();
        String nombreR, correoR, idR, telefono,mz,villa,pinAcceso,matricula,prop,mensaje;
        Vehiculo v;
        Mailer mail = new Mailer();
        String [] pin;
        boolean pinValido;
        Ciudadela c = getMineCiud(ciudadelas);
        idR = Validaciones.ValidarId(sist.getUsuarios(),"Residente");
        System.out.print("Ingrese el nombre del residente: ");
        nombreR = sc.nextLine();
        do{
            System.out.print("Ingrese el correo del residente: ");
            correoR = sc.nextLine();
            if(!correoR.contains("@")){
                JOptionPane.showMessageDialog(null, "Usted ha ingresado un correo invalido, por favor ingrese un correo valido.");
            }
        }while(!correoR.contains("@"));
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
        mensaje = "Saludos! "+nombreR+".\nLe informamos que su registro a la ciudadela "+c.getNombre()+" se ha llevado con exito. "
                + "Sus credenciales para ingresar al sistema sistema son: "+resid+".\n\nAtentamente,\nEquipo de SistemaCiudadelas.";
        mail.enviarCorreo(correoR, mensaje);
        JOptionPane.showMessageDialog(null,"El residente se ha creado con exito, sus credenciales se le ha enviado a su correo");
    }
    /**
     * Metodo que verifica cual es la ciudadela del administrador de ciudadela que lo invoque
     * @param ciuds ArrayList con todas las ciudadelas que se encuentran registradas en el sistema
     * @return La Ciudadela que tenga como administrador de ciudadela al administrador de ciudadela que invoque al metodo
     */
    private Ciudadela getMineCiud(ArrayList<Ciudadela> ciuds){
        for(Ciudadela c : ciuds){
            if(c.getAdm().equals(this)){
                return c;
            }
        }
        return null;
    }
}
