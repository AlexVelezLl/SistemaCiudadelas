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
import java.util.Scanner;
import java.time.LocalDateTime;
/**
 *
 * @author Alex Velez
 */
public class AdministradorDeSistema extends Usuario{
    String nombre;
    Scanner sc = new Scanner(System.in);
    public AdministradorDeSistema(){
        super("SuperAdmin","SuperAdmin");
        nombre = "Rocio Mera";
    }
    public String getNombre(){
        return nombre;
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
        //Obetniendo fecha de inicio de administracion
        System.out.println("Ingrese la fecha de inicio de la administracion del Admin: ");
        fInicioAdmin = consultarFecha();
        System.out.println("Ingrese la fecha de fin de la administracion del Admin: ");
        fFinAdmin = consultarFecha();
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
        ciudAdmin=new AdministradorDeCiudadela(nomAdmin, idAdmin, correoAdmin, fInicioAdmin,fFinAdmin);
        Usuario u = ciudAdmin;
        Ciudadela c = new Ciudadela(nombre, razonSocial, RUC, ubicacion,ciudAdmin,Integer.parseInt(numManzanas),Integer.parseInt(villasXManzana));
        ArrayList<Ciudadela> ciuds= sistema.getCiudadelas();
        sistema.agregarCiudadela(c);
        sistema.agregarUsuario(u);
        System.out.println("Se ha registrado la ciudadela "+nombre+", y las credenciales del administrador de la ciudadela son: ");
        System.out.println("Username: "+ciudAdmin.getUsername());
        System.out.println("Password: "+ciudAdmin.getPassword());
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
    private LocalDateTime consultarFecha(){
        String year, month, day;
        LocalDateTime time;
        do{
            System.out.println("Año: ");
            year = sc.nextLine();
            System.out.println("Mes: ");
            month = sc.nextLine();
            System.out.println("Dia: ");
            day= sc.nextLine();
            try{
                time = LocalDateTime.of(Integer.parseInt(year),Integer.parseInt(month),Integer.parseInt(day),0,0);
            }catch(Exception e){
                System.out.println("Usted ha ingresado una fecha invalida, por favor ingrese una fecha valida.");
                time = null;
            }
        }while(time==null);
        return time;
    }
}