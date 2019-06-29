/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.util.ArrayList;
import Sesion.*;
import Sistema.Visitante;
import java.time.LocalDateTime;

import java.util.Scanner;

/**
 *
 * @author CORE i7 ULTIMATE
 */
public class Validaciones {
    public static String ValidarId(ArrayList<Persona> personas,String tipo){
        Scanner sc = new Scanner(System.in);
        boolean idUnico;
        String id;
        do{
            idUnico = true;
            System.out.print("Ingrese el id: ");
            id = sc.nextLine();
            for (Object persona :personas){
                switch(tipo){
                    case "CiudAdmin":
                        if((persona instanceof AdministradorDeCiudadela)&&id.equals(((AdministradorDeCiudadela)persona).getId())){
                            System.out.println("Usted ha ingresado un id que ya esta registrado en el sistema, por favor ingrese otro id.");
                            idUnico =false;
                        }
                        break;
                    case "Residente":
                        if((persona instanceof Residente)&&id.equals(((Residente)persona).getId())){
                            System.out.println("Usted ha ingresado un id que ya esta registrado en el sistema, por favor ingrese otro id.");
                            idUnico =false;
                        }
                        break;
                    case "Visitante":
                        if((persona instanceof Visitante)&&id.equals(((Visitante)persona).getId())){
                            System.out.println("Usted ha ingresado un id que ya esta registrado en el sistema, por favor ingrese otro id.");
                            idUnico =false;
                        }
                        break;
                }
            }       
        }while(!idUnico);
        return id;
    }
    
    public static LocalDateTime consultarFecha(){
        Scanner sc = new Scanner(System.in);
        String year, month, day,hour;
        LocalDateTime time;
        do{
            System.out.print("Año: ");
            year = sc.nextLine();
            System.out.print("Mes: ");
            month = sc.nextLine();
            System.out.print("Dia: ");
            day= sc.nextLine();
            System.out.print("Hora: ");
            hour= sc.nextLine();
            try{
                time = LocalDateTime.of(Integer.parseInt(year),Integer.parseInt(month),Integer.parseInt(day),Integer.parseInt(hour),0);
            }catch(Exception e){
                System.out.println("Usted ha ingresado una fecha invalida, por favor ingrese una fecha valida.");
                time = null;
            }
        }while(time==null);
        return time;
    }
    
    public static boolean isNumeric(String cadena){
	boolean a;
        try {
		Integer.parseInt(cadena);
		a=true;
	} catch (NumberFormatException nfe){
		a = false;
	}
        return a && (Integer.parseInt(cadena)>0);
    }
}
