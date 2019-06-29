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
    import java.time.LocalDate;
/**
 *
 * @author Alex Velez
 */
public class PuntoAccesoVisitantes extends PuntoAcceso{
    Scanner sc = new Scanner(System.in);
    
    public PuntoAccesoVisitantes(Ciudadela ciudadela){
        super(ciudadela);
    }


  

    public boolean verificarHora(LocalDateTime time){
            LocalDateTime timeNow = LocalDateTime.now();
            timeNow=  LocalDateTime.of(timeNow.getYear(),timeNow.getMonth(),timeNow.getDayOfMonth(),timeNow.getHour(),0);
            time =  LocalDateTime.of(time.getYear(),time.getMonth(),time.getDayOfMonth(),time.getHour(),0);
            LocalDateTime timeD, timeA;
            for(int i=1;i<=12;i++){
                timeD = time.plusHours(i);
                timeA = time.minusHours(i);
                if(timeNow.equals(timeD)||timeNow.equals(timeA)){
                    return true;
                }
            }
            return false;
        }
    
    
        
    
    

    public boolean comprobarCodigo(String codigoAcceso){
        for (Residente r: ciudadela.getResidentes()){
            for (CodigoAcceso c: r.getCodigosAcceso()){
                if (c.getCodigo().equals(codigoAcceso)) {
                boolean valido = verificarHora(c.getFechaIngreso());
                if (valido== true) {
                    return true;                     
                }
                }
            }
        }
        return false;
    }
    
    
    @Override
    public RegistroIngreso comprobarAcceso() {
        System.out.print("¡Bienvenido! ¿Tiene codigo acceso?: ");
        String resp = sc.nextLine();
        LocalTime t1= LocalTime.now();
        while (resp.equals("no")){
            System.out.print("Ingrese su nombre: ");
            String nombre= sc.nextLine();
            System.out.print("Ingrese su identificacion: ");
            String id= sc.nextLine(); 
            System.out.println("Ingrese su correo: ");
            String correo=sc.nextLine();
            System.out.print("Ingrese el nombre del residente a visitar: ");
            String nomResidente = sc.nextLine();
            System.out.print("Ingrese la manzana del residente: ");
            String mz = sc.nextLine();
            System.out.print("Ingrese la villa del residente: ");
            String villa = sc.nextLine(); 
            Residente residente = ObtenerResidente(nomResidente,mz,villa);
            while(residente== null){
                System.out.println("El residente no existe, ingrese nuevamente los datos: ");
                System.out.print("Ingrese el nombre del residente a visitar: ");
                nomResidente = sc.nextLine();
                System.out.print("Ingrese la manzana del residente: ");
                mz = sc.nextLine();
                System.out.print("Ingrese la villa del residente: ");
                villa = sc.nextLine();  
            }                
            String email= residente.getCorreo();
            System.out.println("Se ha enviado un correo al Residente("+email+")");
            Visitante visitante= new Visitante(nombre,id,correo,residente);
            residente.registrarVisitante();
            System.out.println("Su codigo de acceso es: "+ visitante.getCodigoAcceso().getCodigo());
            resp="si";  
        }
        
            System.out.println("Ingrese su codigo de acceso: ");            
            String cod_acceso= sc.nextLine();
            while (cod_acceso.length()!= 4){
                System.out.println("Ingresar un codigo de acceso valido (8 num)");                
            }
            boolean validez = comprobarCodigo(cod_acceso);
            if (validez== true) {                
                System.out.println("Acceso concedido");
                LocalDate fingreso= LocalDate.now();
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

    
}
