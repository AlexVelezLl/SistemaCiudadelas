/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Simulacro;
    import java.util.Scanner;
    import java.time.LocalDateTime;
    import Sistema.Visitante;
    import Sesion.Residente;
    import Sistema.CodigoAcceso;
    import Sistema.Ciudadela;
    import java.time.LocalTime;
import java.util.ArrayList;

    import utilities.Persona;
/**
 * subClase que define el comportamiento del punto de acceso de un visitante.
 * @author Valeria Barzola
 */
public class PuntoAccesoVisitantes extends PuntoAcceso{
    Scanner sc = new Scanner(System.in);
    
    public PuntoAccesoVisitantes(Ciudadela ciudadela){
        super(ciudadela);
    }


    /**
     * Metodo que determina si un tiempo está entre cierto intervalo
     * @param time
     * @return valor de verdad 
     */
    public boolean verificarHora(LocalDateTime time){
            LocalDateTime timeNow = LocalDateTime.now();
            timeNow=  LocalDateTime.of(timeNow.getYear(),timeNow.getMonth(),timeNow.getDayOfMonth(),timeNow.getHour(),0);
            time =  LocalDateTime.of(time.getYear(),time.getMonth(),time.getDayOfMonth(),time.getHour(),0);
            LocalDateTime timeD, timeA;
            for(int i=0;i<=12;i++){
                timeD = time.plusHours(i);
                timeA = time.minusHours(i);
                if(timeNow.equals(timeD)||timeNow.equals(timeA)){
                    return true;
                }
            }
            return false;
        }
    
    /**
     * Metodo que comprueba la validez de un código
     * @param codigoAcceso String del codigo de acceso que se verificará
     * @return valor de verdad
     */
    public boolean comprobarCodigo(String codigoAcceso){
        CodigoAcceso c;
        ArrayList<Residente> residentes = ciudadela.getResidentes();
        for (Residente r: residentes){
            for (Persona vis: r.getVisitantes()){
                
                c=((Visitante)vis).getCodigoAcceso();
                if (codigoAcceso.equals(c.getCodigo())) {
                    boolean valido = verificarHora(c.getFechaIngreso());
                    if (valido) {
                        return true;                     
                    }
                }
            }
        }
        return false;
    }
    
    /**
     * Metodo que permite el ingreso a la Ciudadela y crea un registro de este ingreso
     * @return registroIngreso Objeto RegistroIngreso en caso de ingresar, con la informacion de ingreso.
     */
    
    @Override
    public RegistroIngreso comprobarAcceso() {
        LocalTime t1 = LocalTime.now();
        String resp;
        do{
            System.out.print("¡Bienvenido! ¿Tiene codigo acceso?(si/no/SALIR): ");
            resp = sc.nextLine();
            if(resp.equals("SALIR")) return null;
            if (!resp.equals("si")&&!resp.equals("no")){
                System.out.println("Ingrese una respuesta valida");
            }             
        }while(!resp.equals("si")&&!resp.equals("no"));
        if(resp.equals("no")){
            String nombre, id, nomResidente,mz,villa;
            Residente residente;
            System.out.print("Ingrese su nombre: ");
            nombre= sc.nextLine();
            System.out.print("Ingrese su identificacion: ");
            id= sc.nextLine();            
            do{
                System.out.print("Ingrese el nombre del residente a visitar: ");
                nomResidente = sc.nextLine();
                if(nomResidente.equals("SALIR"))return null;
                System.out.print("Ingrese la manzana del residente: ");
                mz = sc.nextLine();
                System.out.print("Ingrese la villa del residente: ");
                villa = sc.nextLine();
                residente = ObtenerResidente(nomResidente,mz,villa);
                if(residente==null){
                    System.out.println("Los datos ingresados no coinciden con ningun Residente, "
                            + "por favor ingrese un residente valido. Si desea salir escriba \"SALIR\".");
                }
            }while(residente==null);
            String email= residente.getCorreo();
         
       
            System.out.println("Se ha enviado un correo al Residente("+email+")");            
            residente.registrarVisitante(nombre,id);                       
        }
        
        System.out.print("Ingrese su codigo de acceso: ");            
        String cod_acceso= sc.nextLine();

        boolean validez = comprobarCodigo(cod_acceso);
        if (validez) {                
            System.out.println("Acceso concedido");
            LocalDateTime fingreso= LocalDateTime.now();
            LocalTime t2= LocalTime.now();
            double duracionIngreso = CalcularTiempo(t1,t2);
            Residente residente = ObtenerResidenteporCodigo(cod_acceso);
            Visitante visitante= ObtenerVisitante(cod_acceso);
            RegistroIngreso registro = new RegistroIngreso(visitante.getNombre(),fingreso,duracionIngreso,residente);
            return registro;
            
        }
            
        System.out.println("Ingreso Denegado");
        return null;
    }
    
    /**
     * Metodo para obtener el visitante por el codigo 
     * @param codigo String con el codigo por el cual se accederá al visitante
     * @return  visitante con el atributo especificado
     */
    public Visitante ObtenerVisitante(String codigo){
        for(Residente r: ciudadela.getResidentes()){
            for(Persona v: r.getVisitantes()){
                if (((Visitante)v).getCodigoAcceso().getCodigo().equals(codigo)) {
                    return ((Visitante)v);
                    
                }
            }
        }
        return null;
        
    }

    
}
