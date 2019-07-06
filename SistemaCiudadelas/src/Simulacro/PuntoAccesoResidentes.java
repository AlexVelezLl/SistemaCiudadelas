/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Simulacro;
import java.time.LocalTime;
import java.time.LocalDateTime;
/**
 * 
 * 
 *
 */

import Sesion.Residente;
import Sistema.Ciudadela;
import java.util.Scanner;
import javax.swing.JOptionPane;
/**
 * subClase que define el comportamiento del punto de acceso de un residente.
 * @author Valeria Barzola
 */
public class PuntoAccesoResidentes extends PuntoAcceso{
    Scanner sc= new Scanner(System.in);

    public PuntoAccesoResidentes(Ciudadela ciudadela) {
        super(ciudadela);
    }

    

    /**
     * Metodo que permite el ingreso a la Ciudadela y crea un registro de este ingreso.
     * @return El registro de Ingreso generado con la infromacion del ingreso.
     */
    @Override
    public RegistroIngreso comprobarAcceso() {
        String modoacceso;
        do{
            System.out.println("Opciones");
            System.out.println("1)Entrar como Peaton");
            System.out.println("2)Entrar como vehiculo");
            System.out.println("3)Salir");
            System.out.print("¿Que desea hacer?: ");
            modoacceso = sc.nextLine();
            if (modoacceso.equals("1")) {
                System.out.println(ciudadela.getResidentes());
                LocalTime t1= LocalTime.now(); //Instante en el que empieza a ingresar
                System.out.print("¡Bienvenido! Por favor ingrese su numero de cédula: ");
                String num_cedula = sc.nextLine();
                System.out.print("Ingrese su pin de acceso: ");
                String pin = sc.nextLine();
                while (pin.length()!=4 ) { //validamos que el pin sea solo de cuatro digitos
                    System.out.print("Ingrese un pin valido (cuatro digitos)");
                    pin = sc.nextLine();                
                }

                Residente residente = ObtenerResidente(num_cedula,pin);
                if (residente != null) {
                    JOptionPane.showMessageDialog(null,"Acceso concedido");
                    LocalTime t2= LocalTime.now(); //instante en el que termina de ingresar el peaton.
                    //Si el ingreso es exitoso entonces se genera un RegistroIngreso
                    LocalDateTime fingreso = LocalDateTime.now();
                    double duracionIngreso = CalcularTiempo(t1,t2);
                    RegistroIngreso registro= new RegistroIngreso(fingreso,duracionIngreso,residente);
                    return registro;  
                } 
                JOptionPane.showMessageDialog(null,"Acceso denegado");
            }else if (modoacceso.equals("2")) {
                LocalTime t1= LocalTime.now(); //Instante en el que empieza a ingresar el vehiculo
                System.out.print("¡Bienvenido! Por favor ingrese el numero de matricula del vehiculo: ");
                String matricula = sc.nextLine();
                Residente residente = ObtenerResidente(matricula);
                if (residente!= null){
                    JOptionPane.showMessageDialog(null,"Acceso concedido");
                    LocalTime t2= LocalTime.now(); //instante en el que termina de ingresar el vehiculo. 
                    //Si el ingreso es exitoso entonces se genera un RegistroIngreso
                    LocalDateTime fingreso = LocalDateTime.now();
                    double duracionIngreso = CalcularTiempo(t1,t2);
                    RegistroIngreso registro= new RegistroIngreso(fingreso,duracionIngreso,residente);
                    return registro;
                }
                JOptionPane.showMessageDialog(null,"Acceso denegado");
            }else if(!modoacceso.equals("3")){
                JOptionPane.showMessageDialog(null,"Por favor ingrese una opcion valida");
            }
        }while(!modoacceso.equals("3"));
        JOptionPane.showMessageDialog(null,"Acceso Denegado");
        return null;
    }
    
}
