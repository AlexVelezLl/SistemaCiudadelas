/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;
import java.util.Scanner;
import Sistema.SistemaCiudadelas;
import Sesion.*;
/**
 *
 * @author Alex Velez
 */
public class Index {
    static SistemaCiudadelas sistCiud = new SistemaCiudadelas();
    static InicioSesion inicio = new InicioSesion();
    static Garita gar = new Garita();
    public static void main(String[] args){
        //Implementacion de la Interfaz Principal
        Scanner sc = new Scanner(System.in);
        String opcion;
        System.out.println("***********************************************");
        System.out.println("*            SISTEMA DE CIUDADELAS            *");
        System.out.println("***********************************************");
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
                                superAdmin = inicio.validarIngreso(credenciales,sistCiud.getUsuarios(),"SuperAdmin");
                                if(superAdmin!= null){
                                    inicio.mostrarOpciones(superAdmin, sistCiud);
                                }else{
                                    System.out.println("Usuario o contraseña incorrecta");
                                }
                                break;
                            case "2":
                                AdministradorDeCiudadela ciudAdmin;
                                credenciales = inicio.preguntarCredenciales();
                                ciudAdmin = inicio.validarIngreso(credenciales,sistCiud.getUsuarios(),"CiudAdmin");
                                if(ciudAdmin!= null){
                                    inicio.mostrarOpciones(ciudAdmin, sistCiud);
                                }else{
                                    System.out.println("Usuario o contraseña incorrecta");
                                }
                                break;
                            case "3":
                                Residente residente;
                                credenciales = inicio.preguntarCredenciales();
                                residente = inicio.validarIngreso(credenciales,sistCiud.getUsuarios(),"Residente");
                                if(residente!= null){
                                    inicio.mostrarOpciones(residente, sistCiud);
                                }else{
                                    System.out.println("Usuario o contraseña incorrecta");
                                }
                                break;
                            case "4":
                                break;
                            default:
                                System.out.println("No ha ingresado una opcion valida, por favor Ingrese una opcion valida.");
                        }
                    }while(!opcionInicio.equals("4"));
                    
                    break;
                case "2"://Simulacro
                    gar.iniciarSimulacro();
                    break;
                case "3"://Saliendo
                    System.out.println("Saliendo... Tenga un buen dia.");
                    break;
                default:
                    System.out.println("No ha ingresado una opcion valida, por favor Ingrese una opcion valida.");
            }
        
        
        }while(!opcion.equals("3"));
    }
}