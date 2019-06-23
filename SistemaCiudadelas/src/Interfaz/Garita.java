/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;
import java.util.Scanner; 
import java.util.ArrayList;
import Sistema.Usuario;
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
        String nomCiud,hacer;
        System.out.println("Escriba el nombre de la Ciudadela: ");
        nomCiud = sc.nextLine();
        Ciudadela c;
        do{
            c = buscarCiud(nomCiud,ciudadelas);
            if(c!=null){
                System.out.println("Que desea hacer?");
                System.out.println("1)Ingresar por el punto de acceso de Residente");
                System.out.println("2)Ingresar por el punto de acceso de Visitante");
                System.out.println("3)Salir");
                hacer = sc.nextLine();
                switch(hacer){
                    case "1":
                        c.getPuntosAcceso[0]
                }
            }
        }while(c!=null);
        
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
