/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;
import java.util.Scanner;
import Sistema.SistemaCiudadelas;
import Sesion.*;
import javax.swing.JOptionPane;
import utilities.Persona;
/**
 *
 * @author Alex Velez
 */
public class Index {
    public static void main(String[] args){
        SistemaCiudadelas sistCiud = new SistemaCiudadelas();
        InicioSesion inicio = new InicioSesion();
        Garita gar = new Garita();
        //Implementacion de la Interfaz Principal
        Scanner sc = new Scanner(System.in);
        String opcion;
        System.out.println("********************************************");
        System.out.println("*         SISTEMA DE CIUDADELAS            *");
        System.out.println("********************************************");
        System.out.println("*      \\_/       .:'    .:'    .:'         *");
        System.out.println("*    -=(_)=-  /\\||   /\\||   /\\||           *");
        System.out.println("*      / \\   //\\\\|  //\\\\|  //\\\\|           *");
        System.out.println("*           //  \\\\ //  \\\\ //  \\\\           *");
        System.out.println("*          //    \\^/    \\^/    \\\\          *");
        System.out.println("*          |[]  []|[]  []|[]  []|          *");
        System.out.println("*         &|  ||  %  ||  |  ||  |%         *");
        System.out.println("*      &%&--==--&%-==--%&\"\"\"\"\"%&%\"\"\"\"      *");
        System.out.println("********************************************");
        do{
            System.out.println("Opciones: ");
            System.out.println("1.Iniciar Sesion");
            System.out.println("2.Simular Ingreso a ciudadela");
            System.out.println("3.Salir");
            System.out.print("¿Que desea hacer?: ");
            opcion = sc.nextLine();
            switch(opcion){
                case "1"://Iniciar Sesion
                    String opcionInicio;
                    String[] credenciales;
                    do{
                        System.out.println("Opciones: ");
                        System.out.println("1)Iniciar sesion como administrador del sistema");
                        System.out.println("2)Iniciar sesion como administrador de ciudadela");
                        System.out.println("3)Iniciar sesion como Residente");
                        System.out.println("4)Regresar");
                        System.out.print("¿Que desea hacer?: ");
                        opcionInicio = sc.nextLine();
                        switch(opcionInicio){
                            case "1":
                                AdministradorDeSistema superAdmin;
                                credenciales = inicio.preguntarCredenciales();
                                Persona superAdminP = inicio.validarIngreso(credenciales,sistCiud.getUsuarios(),"SuperAdmin");
                                superAdmin =  (AdministradorDeSistema) superAdminP;
                                if(superAdmin!= null){
                                    inicio.mostrarOpciones(superAdmin, sistCiud);
                                }else{
                                    JOptionPane.showMessageDialog(null,"Usuario o contraseña incorrectos");
                                }
                                break;
                            case "2":
                                AdministradorDeCiudadela ciudAdmin;
                                Persona ciudAdminP;
                                credenciales = inicio.preguntarCredenciales();
                                ciudAdminP = inicio.validarIngreso(credenciales,sistCiud.getUsuarios(),"CiudAdmin");
                                ciudAdmin = (AdministradorDeCiudadela)ciudAdminP;
                                if(ciudAdmin!= null){
                                    inicio.mostrarOpciones(ciudAdmin, sistCiud);
                                }else{
                                    JOptionPane.showMessageDialog(null,"Usuario o contraseña incorrectos");
                                }
                                break;
                            case "3":
                                Residente residente;
                                Persona residenteP;
                                credenciales = inicio.preguntarCredenciales();
                                residenteP = inicio.validarIngreso(credenciales,sistCiud.getUsuarios(),"Residente");
                                residente = (Residente)residenteP;
                                if(residente!= null){
                                    inicio.mostrarOpciones(residente, sistCiud);
                                }else{
                                    JOptionPane.showMessageDialog(null,"Usuario o contraseña incorrectos");
                                }
                                break;
                            case "4":
                                System.out.println("Regresando...");
                                break;
                            default:
                                JOptionPane.showMessageDialog(null,"No ha ingresado una opcion valida, por favor Ingrese una opcion valida.");
                        }
                    }while(!opcionInicio.equals("4"));
                    
                    break;
                case "2"://Simulacro
                    gar.iniciarSimulacro(sistCiud.getCiudadelas());
                    break;
                case "3"://Saliendo
                    System.out.println("Saliendo... Tenga un buen dia.");
                    break;
                default:
                    JOptionPane.showMessageDialog(null,"No ha ingresado una opcion valida, por favor Ingrese una opcion valida.");
            }
        
        
        }while(!opcion.equals("3"));
    }
}