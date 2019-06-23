/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Simulacro;

/**
 *
 * @author Alex Velez
 */

import Sesion.Residente;
import java.util.Scanner;
public class PuntoAccesoResidentes extends PuntoAcceso{
    Scanner sc= new Scanner(System.in);

    

    
    @Override
    public boolean comprobarAcceso() {
        String modoacceso;
        System.out.print("El residente a entrar ¿es un peaton o un vehiculo?: ");
        modoacceso = sc.nextLine();
        if (modoacceso.equals("peaton")) {
            System.out.println("¡Bienvenido! Por favor ingrese su numero de cédula: ");
            String num_cedula = sc.nextLine();
            System.out.println("Ingrese su pin de acceso: ");
            String pin = sc.nextLine();
            while (pin.length()!=4 ) { //validamos que el pin sea solo de cuatro digitos
                System.out.println("Ingrese un pin valido (cuatro digitos)");
                pin = sc.nextLine();                
            }
            
            for (Residente r: ciudadela.residentes){
                if ((num_cedula).equals(r.id)&& pin.equals(r.pinAcceso)){
                    System.out.println("Acceso concedido");
                    return true;                   
                }               
            }  
        }else if (modoacceso.equals("vehiculo")) {
            System.out.println("Ingrese el numero de matricula del vehiculo: ");
            String matricula = sc.nextLine();
            for (Residente r : ciudadela.residentes){              
                if (r.matriculasVehiculos.contains(matricula)){
                    System.out.println("Acceso concedido");
                    return true;
                }
            }
        }
        System.out.println("Acceso Denegado");
        return false;
    }
    
    public static void main (String[] args){
        PuntoAccesoResidentes h = new PuntoAccesoResidentes();
        h.comprobarAcceso();
    }
    
}
