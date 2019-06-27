/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Simulacro;
import java.time.LocalTime;
import java.time.LocalDate;
/**
 *
 * @author Alex Velez
 */

import Sesion.Residente;
import java.util.Scanner;
public class PuntoAccesoResidentes extends PuntoAcceso{
    Scanner sc= new Scanner(System.in);

    

    
    @Override
    public RegistroIngreso comprobarAcceso() {
        String modoacceso;
        System.out.print("Que opcion desea hacer?");
        System.out.println("1)Entrar como Peaton");
        System.out.println("2)Entrar como vehiculo");
        System.out.println("Otro: Salir");
        modoacceso = sc.nextLine();
        if (modoacceso.equals("1")) {
            LocalTime t1= LocalTime.now(); //Instante en el que empieza a ingresar
            System.out.println("¡Bienvenido! Por favor ingrese su numero de cédula: ");
            String num_cedula = sc.nextLine();
            System.out.println("Ingrese su pin de acceso: ");
            String pin = sc.nextLine();
            while (pin.length()!=4 ) { //validamos que el pin sea solo de cuatro digitos
                System.out.println("Ingrese un pin valido (cuatro digitos)");
                pin = sc.nextLine();                
            }
            
            Residente residente = ObtenerResidente(num_cedula,pin);
            if (residente != null) {
                System.out.println("Acceso concedido");
                LocalTime t2= LocalTime.now(); //instante en el que termina de ingresar el peaton.
                //Si el ingreso es exitoso entonces se genera un RegistroIngreso
                LocalDate fingreso = LocalDate.now();
                double duracionIngreso = CalcularTiempo(t1,t2);
                RegistroIngreso registro= new RegistroIngreso(fingreso,duracionIngreso,residente);
                return registro;  
            }                         
        }else if (modoacceso.equals("2")) {
            LocalTime t1= LocalTime.now(); //Instante en el que empieza a ingresar el vehiculo
            System.out.println("¡Bienvenido! Por favor ingrese el numero de matricula del vehiculo: ");
            String matricula = sc.nextLine();
            Residente residente = ObtenerResidente(matricula);
            if (residente!= null){
                System.out.println("Acceso concedido");
                LocalTime t2= LocalTime.now(); //instante en el que termina de ingresar el vehiculo. 
                //Si el ingreso es exitoso entonces se genera un RegistroIngreso
                LocalDate fingreso = LocalDate.now();
                double duracionIngreso = CalcularTiempo(t1,t2);
                RegistroIngreso registro= new RegistroIngreso(fingreso,duracionIngreso,residente);
                return registro;
                
                
            }                                   
        }
        System.out.println("Acceso Denegado");
        return null;
    }
    
}
