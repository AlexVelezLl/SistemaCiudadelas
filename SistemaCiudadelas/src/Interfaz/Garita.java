/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;
import java.util.Scanner; 
import java.util.ArrayList;
import Simulacro.*;
import Sistema.Ciudadela;
import javax.swing.JOptionPane;
/**
 * Clase de interfaz de usuario cuando el usuario haya decidido hacer el simulacro de ingreso
 * @author Alex Velez
 */
public class Garita {
    Scanner sc;
    public Garita(){
        sc = new Scanner(System.in);
    }
    public void iniciarSimulacro(ArrayList <Ciudadela> ciudadelas){
        String nomCiud,hacer, seguir;
        Ciudadela c;
        RegistroIngreso r;
        PuntoAcceso d;
        
        do{
            System.out.print("Escriba el nombre de la Ciudadela: ");
            nomCiud = sc.nextLine();
            c = buscarCiud(nomCiud,ciudadelas);
            if(c!=null){
                do{
                    System.out.println("Opciones: ");
                    System.out.println("1)Ingresar por el punto de acceso de Residente");
                    System.out.println("2)Ingresar por el punto de acceso de Visitante");
                    System.out.println("3)Regresar");
                    System.out.print("¿Que desea hacer?: ");
                    hacer = sc.nextLine();
                    switch(hacer){
                        case "1":
                            d = c.getPuntosAcceso()[0];
                            r = d.comprobarAcceso();
                            if(r!=null){
                                c.addIngreso(r);
                            }else{
                                JOptionPane.showMessageDialog(null,"No se ha completado el ingreso con exito");
                            }
                            do{
                                System.out.println("Opciones:");
                                System.out.println("1)Simular otro dato");
                                System.out.println("2)Regresar al menu principal");
                                System.out.print("¿Que desea hacer?");
                                seguir = sc.nextLine();
                                if(seguir.equals("2")){
                                    hacer="3";
                                }else if(!seguir.equals("1")&&!seguir.equals("2")){
                                    JOptionPane.showMessageDialog(null,"Por favor ingrese una opcion que este en el menu");
                                }
                            }while(!seguir.equals("1")&&!seguir.equals("2"));
                            break;
                        case "2":
                            d = c.getPuntosAcceso()[1];
                            r = d.comprobarAcceso();
                            if(r!=null){
                                c.addIngreso(r);
                            }else{
                                JOptionPane.showMessageDialog(null,"No se ha completado el ingreso con exito");
                            }
                            do{
                                System.out.println("Opciones: ");
                                System.out.println("1)Simular otro dato");
                                System.out.println("2)Regresar al menu principal");
                                System.out.print("¿Que desea hacer?");
                                seguir = sc.nextLine();
                                if(seguir.equals("1")){
                                    hacer="3";
                                }else if(!seguir.equals("1")&&!seguir.equals("2")){
                                    JOptionPane.showMessageDialog(null,"Por favor ingrese una opcion que este en el menu");
                                }
                            }while(!seguir.equals("1")&&!seguir.equals("2"));
                            break;
                        case "3":
                            System.out.println("Saliendo...");
                            break;
                        default:
                            JOptionPane.showMessageDialog(null,"No ha ingresado una opcion valida, por favor ingrese una opcion valida");
                    }
                }while(!hacer.equals("3"));
            }else if(!nomCiud.equals("SALIR")){
                JOptionPane.showMessageDialog(null,"Ha ingresado una Ciudadela que no se encuentra en el sistema, por favor Ingrese una Ciudadela valida o escriba la palabra 'SALIR' para regresar");
            }
        }while(c==null&&!nomCiud.equals("SALIR"));
        
    }
    /**
     * Metodo para buscar la ciudadela por su nombre
     * @param nombre String del nombre de la ciudadela
     * @param ciudadelas ArrayList de todas las ciudadelas del sistema
     * @return Ciudadela que tenga el mismo nombre del parametro
     */
    private Ciudadela buscarCiud(String nombre, ArrayList <Ciudadela> ciudadelas){
        for(Ciudadela c: ciudadelas){
            if(c.getNombre().equals(nombre)){
                return c;
            }
        }
        return null;
    }
}
