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
/**
 *
 * @author Alex Velez
 */
public class Garita {
    Scanner sc;
    public Garita(){
        sc = new Scanner(System.in);
    }
    public void iniciarSimulacro(ArrayList <Ciudadela> ciudadelas){
        String nomCiud,hacer="";
        System.out.println("Escriba el nombre de la Ciudadela: ");
        nomCiud = sc.nextLine();
        Ciudadela c;
        RegistroIngreso r;
        do{
            c = buscarCiud(nomCiud,ciudadelas);
            if(c!=null){
                do{
                    System.out.println("Que desea hacer?");
                    System.out.println("1)Ingresar por el punto de acceso de Residente");
                    System.out.println("2)Ingresar por el punto de acceso de Visitante");
                    System.out.println("3)Salir");
                    hacer = sc.nextLine();
                    switch(hacer){
                        case "1":
                            PuntoAcceso d = c.getPuntosAcceso()[0];
                            r = d.comprobarAcceso();
                            if(r!=null){
                                c.setIngresos(c.getIngresos().add(r));
                            }else{
                                System.out.println("No se ha completado el ingreso con exito");
                            }
                            break;
                        case "2":
                            PuntoAcceso d = c.getPuntosAcceso()[1];
                            r = d.comprobarAcceso();
                            if(r!=null){
                                c.setIngresos(c.getIngresos().add(r));
                            }else{
                                System.out.println("No se ha completado el ingreso con exito");
                            }
                            break;
                        case "3":
                            System.out.println("Saliendo...");
                            break;
                        default:
                            System.out.println("No ha ingresado una opcion valida, por favor ingrese una opcion valida");
                    }
                }while(!hacer.equals("3"));
            }else{
                System.out.println("Ha ingresado una Ciudadela que no se encuentra en el sistema, por favor Ingrese una Ciudadela valida o escriba la palabra 'SALIR' para regresar");
            }
        }while(c==null&&!hacer.equals("Salir"));
        
    }
    private Ciudadela buscarCiud(String nombre, ArrayList <Ciudadela> ciudadelas){
        for(Ciudadela c: ciudadelas){
            if(c.getNombre().equals(nombre)){
                return c;
            }
        }
        return null;
    }
}
