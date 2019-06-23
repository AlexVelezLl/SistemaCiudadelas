/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sesion;
import Sistema.*;
import java.util.ArrayList;
import Simulacro.RegistroIngreso;
import Sistema.SistemaCiudadelas;
import Java.util.Scanner;
import java.time.LocalDateTime;
import java.time.LocalTime;
/**
 *
 * @author Alex Velez
 */
public class AdministradorDeSistema extends Usuario{
    String nombre;
    Scanner sc = new Scanner();
    public AdministradorDeSistema(){
        super("SuperAdmin","SuperAdmin");
        nombre = "Rocio Mera";
    }
    public void generarReporteUso(ArrayList<Ciudadela> ciudadelas){
        String nombreCiud,razonSocial;
        int numVis=0, numRes=0, tiempoVis=0,tiempoRes=0;
        float promRes, promVis;
        System.out.println("**************************************");
        System.out.println("*           REPORTE DE USO           *");
        System.out.println("**************************************");
        for(Ciudadela c:ciudadelas){
            nombreCiud = c.getNombre();
            razonSocial = c.getRazonSocial();
            for(RegistroIngreso ingreso: c.getIngresos()){//Obteniendo datos de registros
                if(ingreso.getTipoIngreso().contains("Residente")){
                    numRes++;
                    tiempoRes += ingreso.getTiempoIngreso();
                }else{
                    numVis++;
                    tiempoVis += ingreso.getTiempoIngreso();
                }  
            }
            promRes = tiempoRes/numRes;
            promVis = tiempoVis/numVis;
            System.out.println("Nombre de la Ciudadela: "+nombre+", Razon Social: "+razonSocial);
            System.out.println("Numero ingreso visitantes: "+numVis+", Tiempo ingreso promedio visitantes: "+promVis);
            System.out.println("Numero ingreso residentes: "+numVis+", Tiempo ingreso promedios residentes: "+promRes+"\n");
        }  
    }
    public void registrarCiudadela(SistemaCiudadelas sistema){
        String nombreC, razonSocial, RUC, ubicacion, nomAdmin, correoAdmin, idAdmin,yeari,monthi,dayi,yearf,monthf,dayf;
        String numManzanas, villasXManzana;
        LocalDateTime fInicioAdmin,fFinAdmin;
        String[] credenciales;
        AdministradorDeCiudadela ciudAdmin;
        ArrayList<Usuario> users;
        System.out.println("Ingrese el nombre de la Ciudadela: ");
        nombreC = sc.nextLine();
        System.out.println("Ingrese la Razon Social: ");
        razonSocial = sc.nextLine();
        System.out.println("Ingrese el RUC: ");
        RUC = sc.nextLine();
        System.out.println("Ingrese la ubicacion de la ciudadela: ");
        ubicacion = sc.nextLine();
        System.out.println("Ingrese el nombre del administrador: ");
        nomAdmin = sc.nextLine();
        System.out.println("Ingrese el correo del administrador: ");
        correoAdmin = sc.nextLine();
        System.out.println("Ingrese el id del admin: ");
        idAdmin = sc.nextLine();
        System.out.println("Ingrese la fecha de inicio de la administracion del Admin: ");
        System.out.println("Año: ");
        yeari = sc.nextLine();
        while(!validar(yeari)){
            System.out.println("Ingrese un Año valido: ");
            yeari = sc.nextLine();
        }
        System.out.println("Mes: ");
        monthi = sc.nextLine();
        while(!validar(monthi)&&Integer.parseInt(monthi)<=12){
            System.out.println("Ingrese un Año valido: ");
            monthi = sc.nextLine();
        }
        System.out.println("Dia: ");
        dayi = sc.nextLine();
        while(!validar(dayi)&&Integer.parseInt(dayi)<=31){
            System.out.println("Ingrese un mes valido: ");
            dayi = sc.nextLine();
        }
        fInicioAdmin = LocalDateTime.of(Integer.parseInt(yeari),Integer.parseInt(monthi),Integer.parseInt(monthi),0,0);
        System.out.println("Ingrese la fecha del fin de la administracion del Admin: ");
        System.out.println("Año: ");
        yearf = sc.nextLine();
        while(!validar(yearf)){
            System.out.println("Ingrese un Año valido: ");
            yearf = sc.nextLine();
        }
        System.out.println("Mes: ");
        monthf = sc.nextLine();
        while(!validar(monthf)&&Integer.parseInt(monthf)<=12){
            System.out.println("Ingrese un Año valido: ");
            monthf = sc.nextLine();
        }
        System.out.println("Dia: ");
        dayf = sc.nextLine();
        while(!validar(dayf)&&Integer.parseInt(dayf)<=31){
            System.out.println("Ingrese un mes valido: ");
            dayf = sc.nextLine();
        }
        fFinAdmin = LocalDateTime.of(Integer.parseInt(yearf),Integer.parseInt(monthf),Integer.parseInt(monthf),0,0);
        credenciales = generarCredenciales();
        ciudAdmin=new AdministradorDeCiudadela(nomAdmin, idAdmin, correoAdmin, fInicioAdmin,fFinAdmin,credenciales);
        users = sistema.getUsuarios();
        Usuario u = ciudAdmin;
        users.add(u);
        sistema.setUsuarios(users);
        System.out.println("Ingrese el numero de manzanas que tiene la ciudadela: ");
        numManzanas = sc.nextLine();
        while(!validar(numManzanas)){
            System.out.println("Ingrese el numero de manzanas que tiene su ciudadela: ");
            numManzanas = sc.nextLine();
        }
        System.out.println("Ingrese el numero de villas que tiene cada manzana de la ciudadela: ");
        villasXManzana = sc.nextLine();
        while(!validar(villasXManzana)){
            System.out.println("Ingrese el numero de manzanas que tiene su ciudadela: ");
            villasXManzana = sc.nextLine();
        }
        Ciudadela c = new Ciudadela(nombre, razonSocial, RUC, ubicacion,ciudAdmin,Integer.parseInt(numManzanas),Integer.parseInt(villasXManzana));
        ArrayList<Ciudadela> ciuds= sistema.getCiudadelas();
        sistema.setCiudadelas(ciuds.add(c));
    }
    private boolean validar(String cadena){
	boolean a;
        try {
		Integer.parseInt(cadena);
		a=true;
	} catch (NumberFormatException nfe){
		a = false;
	}
        return a && (Integer.parseInt(cadena)>0);
    }
    private String[] generarCredenciales(){
        String[] credenciales = new String[2];
        return credenciales;
    }
}
