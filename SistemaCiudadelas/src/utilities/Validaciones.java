/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.util.ArrayList;
import Sesion.*;
import java.time.LocalDateTime;
import Sistema.*;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 * Clase que contiene herramientas utiles que serviran a lo largo del desarrollo del programa.
 * @author Alex Velez
 */
public class Validaciones {
    /**
     * Metodo que pregunta el id y valida que no se encuentre en un grupo determinado de un arreglo de personas 
     * Por ejemplo, si se llama ValidarId(personas, "CiudAdmin"), entonces validara el id que pregunte dentro de las personas
     * que son Administradores de ciudadelas, tipo podra tomar 3 valores: "CiudAdmin" o "Residente" o "Visitante".
     * @param personas ArrayList de Peronas.
     * @param tipo String del tipo de Persona que debe validar el id.
     * @return String con el id de la persona.
     */
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
                            JOptionPane.showMessageDialog(null,"Usted ha ingresado un id que ya esta registrado en el sistema, por favor ingrese otro id.");
                            idUnico =false;
                        }
                        break;
                    case "Residente":
                        if((persona instanceof Residente)&&id.equals(((Residente)persona).getId())){
                            JOptionPane.showMessageDialog(null,"Usted ha ingresado un id que ya esta registrado en el sistema, por favor ingrese otro id.");
                            idUnico =false;
                        }
                        break;
                    case "Visitante":
                        if((persona instanceof Visitante)&&id.equals(((Visitante)persona).getId())){
                            JOptionPane.showMessageDialog(null,"Usted ha ingresado un id que ya esta registrado en el sistema, por favor ingrese otro id.");
                            idUnico =false;
                        }
                        break;
                }
            }       
        }while(!idUnico);
        return id;
    }
    /**
     * Metodo que pregunta una fecha y hora, y valida que los valores ingresados sean correctos.
     * @return LocalDateTime de la fecha que pregunto.
     */
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
                JOptionPane.showMessageDialog(null,"Usted ha ingresado una fecha invalida, por favor ingrese una fecha valida.");
                time = null;
            }
        }while(time==null);
        return time;
    }
    /**
     * Metodo que valida si una cadena es numero o no.
     * @param cadena String a verificar si es numero o no.
     * @return Valor de verdad, true si la cadena si es cadena.
     */
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
    /**
     * Metodo que pregunta por pantalla una matricula, y verifica que esta sea valida, y ademas de que no este ingresada ya.
     * @param matricula String con la matricula del vehiculo.
     * @param ciud Ciudadela en la cual se va a verificar si esta o no registrada esa matricula.
     * @return valor de verdad. true si la matricula es valida y no esta registrada en la ciudadela que se le envio.
     */
    public static boolean matriculaValida(String matricula,Ciudadela ciud){
        boolean a,b;
        a = false;
        b = false;
        String [] matr = matricula.split("-");
        ArrayList<String> alfa = new ArrayList<>();
        for (char i=65;i<91;i++){
            alfa.add(String.valueOf(i));
        }
        if(matr.length==2){
            if(matr[0].length() == 3 && (matr[1].length()==4||matr[1].length()==3)){
                b = true;
                a=true;
                String[] cars = matr[0].split("");
                String[] digs = matr[1].split("");
                for(String c : cars){
                    a = a && alfa.contains(c);
                }
                for(String d : digs){
                    b = b && (isNumeric(d)||d.equals("0"));
                }
            }
        }
  
        if(a&&b){//Si la matricula es valida, verificamos que esta no este registrada en la ciudadela
            Vehiculo vehi = new Vehiculo(matricula,"Jose");//Se crea vehiculo con un nombre ficticio solo para comparar
            for(Residente r: ciud.getResidentes()){
                ArrayList<Vehiculo> vehiculos = r.getVehiculos();
                for(Vehiculo v: vehiculos){
                    if(vehi.equals(v))return false;
                }
            }
            return true;
        }
        return false;
        
    }
    
}
