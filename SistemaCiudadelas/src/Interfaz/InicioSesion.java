/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;
import java.util.ArrayList;
import java.util.Scanner;
import Sistema.Usuario;
import Sesion.*;
import Sistema.SistemaCiudadelas;
import utilities.Persona;

/**
 *
 * @author Alex Velez
 */
public class InicioSesion {
    Scanner sc = new Scanner(System.in);
    public Persona validarIngreso(String[] credenciales,ArrayList<Persona> users,String tipoInicio){
        switch(tipoInicio){
            case "SuperAdmin":
                for (Persona user:users){
                    if(credenciales[0].equals(((Usuario)user).getUsername())&&credenciales[1].equals(((Usuario)user).getPassword())&&(user instanceof AdministradorDeSistema)){//Verifica tambien si el usuario es del tiepo que el indico
                        return user;
                    }
               
                }
            case "CiudAdmin":
                for (Persona user:users){
                    if(credenciales[0].equals(((Usuario)user).getUsername())&&credenciales[1].equals(((Usuario)user).getPassword())&&(user instanceof AdministradorDeCiudadela)){
                        return user;
                    }
                }
            case "Residente":
                
                for (Persona user:users){
                    if(credenciales[0].equals(((Usuario)user).getUsername())&&credenciales[1].equals(((Usuario)user).getPassword())&&(user instanceof Residente)){
                        return user;
                    }
                }
            default:
                return null;
        }   
                    
    }
    public String[] preguntarCredenciales(){
        String [] credenciales=new String[2];
        //Preguntando por las credenciales del Usuario:
        System.out.print("Username: ");
        credenciales[0]=sc.nextLine();
        System.out.print("Password: ");
        credenciales[1] = sc.nextLine();
        
        return credenciales;
    }  
    
    public void mostrarOpciones(AdministradorDeSistema superAdmin, SistemaCiudadelas sist){
        String opcionHacer;
        System.out.println("Hola! "+superAdmin.getNombre()+" estas son las opciones que puedes realizar");
        do{
            System.out.println("Opciones: ");
            System.out.println("1)Registrar una nueva Ciudadela");
            System.out.println("2)Generar reporte de uso");
            System.out.println("3)Cambiar credenciales");
            System.out.println("4)Cerrar sesion");
            System.out.print("¿Que desea hacer?: ");
            opcionHacer = sc.nextLine();
            switch(opcionHacer){
                case "1":
                    superAdmin.registrarCiudadela(sist);
                    break;
                case "2":
                    superAdmin.generarReporteUso(sist.getCiudadelas());
                    break;
                case "3":
                    superAdmin.cambiarCredenciales(sist.getUsuarios());
                    break;
                case "4":
                    System.out.println("Sesion Cerrada");
                    break;
                default:
                    System.out.println("No ha ingresado una opcion valida, por favor Ingrese una opcion valida.");
            }
        }while(!opcionHacer.equals("4"));
    }
    
    public void mostrarOpciones(AdministradorDeCiudadela ciudAdmin, SistemaCiudadelas sist){
    String opcionHacer;
    System.out.println("Hola! "+ciudAdmin.getNombre()+" estas son las opciones que puedes realizar");
        do{
            System.out.println("Opciones: ");
            System.out.println("1)Registrar una nuevo Residente");
            System.out.println("2)Generar reporte de visitas");
            System.out.println("3)Cambiar credenciales");
            System.out.println("4)Cerrar sesion");
            System.out.print("¿Que desea hacer?: ");
            opcionHacer = sc.nextLine();
            switch(opcionHacer){
                case "1":
                    ciudAdmin.registrarResidente(sist);
                    break;
                case "2":
                    ciudAdmin.generarReporteVisitas(sist.getCiudadelas());
                    break;
                case "3":
                    ciudAdmin.cambiarCredenciales(sist.getUsuarios());
                    break;
                case "4":
                    System.out.println("Sesion Cerrada");
                    break;
                default:
                    System.out.println("No ha ingresado una opcion valida, por favor Ingrese una opcion valida.");
            }
        }while(!opcionHacer.equals("4")); 
    }
    public void mostrarOpciones(Residente residente,SistemaCiudadelas sist){
    String opcionHacer;
    System.out.println("Hola! "+residente.getNombre()+" estas son las opciones que puedes realizar:  ");
        do{
            System.out.println("Opciones: ");
            System.out.println("1)Registrar a un visitante");
            System.out.println("2)Registrar nuevo vehiculo");
            System.out.println("3)Cambiar pin");
            System.out.println("4)Borrar visitante");
            System.out.println("5)Ver listado de visitantes");
            System.out.println("6)Cambiar Credenciales");
            System.out.println("7)Cerrar sesion");
            System.out.print("¿Que desea hacer?: ");
            opcionHacer = sc.nextLine();
            switch(opcionHacer){
                case "1":
                    residente.registrarVisitante();
                    break;
                case "2":
                    residente.registrarVehiculo();
                    break;
                case "3":
                    residente.cambiarPin();
                    break;
                case "4":
                    residente.borrarVisitante();
                    break;
                case "5":
                    residente.verListaDeVisitantes();
                    break;
                case "6":
                    residente.cambiarCredenciales(sist.getUsuarios());
                    break;
                case "7":
                    System.out.println("Sesion cerrada");
                    break;
                default:
                    System.out.println("No ha ingresado una opcion valida, por favor Ingrese una opcion valida.");
            }
        }while(!opcionHacer.equals("7"));
        System.out.println("opcionHacer = " + opcionHacer);   
    }
}


