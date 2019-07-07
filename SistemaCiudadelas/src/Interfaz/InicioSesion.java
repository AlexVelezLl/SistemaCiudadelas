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
import javax.swing.JOptionPane;
import utilities.Persona;

/**
 * Clase de interfaz de usario cuando el usuario haya decidido iniciar sesion
 * @author Alex Velez
 */
public class InicioSesion {
    Scanner sc = new Scanner(System.in);
    public Persona validarIngreso(String[] credenciales,ArrayList<Persona> users,String tipoInicio){//Validando que las credenciales ingresadas pertenezcan a quien se indico
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
    /**
     * Metodo que pregunta por pantalla las credenciales del usuario
     * @return Array de strings de 2 posiciones, en [0] usuario, y en [1] el password
     */
    public String[] preguntarCredenciales(){
        String [] credenciales=new String[2];
        //Preguntando por las credenciales del Usuario:
        System.out.print("Username: ");
        credenciales[0]=sc.nextLine();
        System.out.print("Password: ");
        credenciales[1] = sc.nextLine();
        
        return credenciales;
    }  
    /**
     * Muestra todas las opciones que puede hacer el Administrador del sistema, y ejectua la opcion que el usuario haya elegido hacer.
     * @param superAdmin Persona que administra el sistema
     * @param sist Sistema general de Sistema Ciudadelas
     */
    public void mostrarOpciones(AdministradorDeSistema superAdmin, SistemaCiudadelas sist){
        String opcionHacer;
        System.out.println("Hola! "+superAdmin.getNombre()+" estas son las opciones que puedes realizar");
        do{
            System.out.println("Opciones: ");
            System.out.println("1)Registrar una nueva Ciudadela");
            System.out.println("2)Generar reporte de uso");
            System.out.println("3)Cambiar credenciales");
            System.out.println("4)Mostrar informacion");
            System.out.println("5)Cerrar sesion");
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
                    superAdmin.mostrarMiInformacion();
                    break;
                case "5":
                    JOptionPane.showMessageDialog(null,"Sesion Cerrada");
                    break;
                default:
                    JOptionPane.showMessageDialog(null,"No ha ingresado una opcion valida, por favor Ingrese una opcion valida.");
            }
        }while(!opcionHacer.equals("5"));
    }
    /**
     * Muestra todas las opciones que puede hacer el Administrador de la ciudadela, y ejectua la opcion que el usuario haya elegido hacer.
     * @param ciudAdmin Persona que administra la ciudadela
     * @param sist Sistema general de Sistema Ciudadelas
     */
    public void mostrarOpciones(AdministradorDeCiudadela ciudAdmin, SistemaCiudadelas sist){
    String opcionHacer;
    System.out.println("Hola! "+ciudAdmin.getNombre()+" estas son las opciones que puedes realizar");
        do{
            System.out.println("Opciones: ");
            System.out.println("1)Registrar una nuevo Residente");
            System.out.println("2)Generar reporte de visitas");
            System.out.println("3)Cambiar credenciales");
            System.out.println("4)Mostrar mi informacion");
            System.out.println("5)Mostrar informacion de la ciudadela administrada");
            System.out.println("6)Cerrar sesion");
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
                    ciudAdmin.mostrarMiInformacion();
                    break;
                case "5": 
                    ciudAdmin.mostrarInfoCiudadela(sist.getCiudadelas());
                    break;
                case "6":
                    JOptionPane.showMessageDialog(null,"Sesion Cerrada");
                    break;
                default:
                    JOptionPane.showMessageDialog(null,"No ha ingresado una opcion valida, por favor Ingrese una opcion valida.");
            }
        }while(!opcionHacer.equals("6")); 
    }
    /**
     * Muestra todas las opciones que puede hacer el Residente, y ejectua la opcion que el usuario haya elegido hacer.
     * @param residente Persona que es residente de alguna ciuadela
     * @param sist Sistema general de Sistema Ciudadelas
     */
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
            System.out.println("7)Mostrar mi informacion");
            System.out.println("8)Cerrar sesion");
            System.out.print("¿Que desea hacer?: ");
            opcionHacer = sc.nextLine();
            switch(opcionHacer){
                case "1":
                    residente.registrarVisitante();
                    break;
                case "2":
                    residente.registrarVehiculo(sist.getCiudadelas());
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
                    residente.mostrarMiInformacion();
                case "8":
                    JOptionPane.showMessageDialog(null,"Sesion cerrada");
                    break;
                default:
                    JOptionPane.showMessageDialog(null,"No ha ingresado una opcion valida, por favor Ingrese una opcion valida.");
            }
        }while(!opcionHacer.equals("8"));
        System.out.println("opcionHacer = " + opcionHacer);   
    }
}


