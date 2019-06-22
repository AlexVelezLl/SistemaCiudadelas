/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;
import java.util.Scanner;
import Sistema.SistemaCiudadelas;
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
                    inicio.validarIngreso(sistCiud.getUsuarios());
                    break;
                case "2"://Simulacro
                    gar.iniciarSimulacro();
                    break;
                case "3":
                    System.out.println("Saliendo... Tenga un buen dia.");
                    break;
                default:
                    System.out.println("No ha ingresado una opcion valida, por favor Ingrese una opcion valida.");
            }
        
        
        }while(!opcion.equals("3"));
    }
}
