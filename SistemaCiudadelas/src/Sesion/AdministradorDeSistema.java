/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sesion;
import Sistema.Usuario;
import Sistema.*;
import java.util.ArrayList;
import Simulacro.RegistroIngreso;
import Sistema.SistemaCiudadelas;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.time.LocalDateTime;
import utilities.*;
/**
 *
 * @author Alex Velez
 */
public class AdministradorDeSistema extends Usuario{
    private final String nombre;
    private final String id;
    
    @Override
    public String getId(){
        return id;
    }
    Scanner sc = new Scanner(System.in);
    public AdministradorDeSistema(){
        super("SuperAdmin","SuperAdmin");
        nombre = "Rocio Mera";
        id = "0978654321";
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public void generarReporteUso(ArrayList<Ciudadela> ciudadelas){
        String nombreCiud,razonSocial;
        int numVis=0, numRes=0, tiempoVis=0,tiempoRes=0;
        float promRes, promVis; 
        String archivo= "ReporteDeUsos";
        LocalDateTime today= LocalDateTime.now(); 
        archivo= archivo+ today.getYear()+ today.getMonth()+ today.getDayOfMonth()+"-"+ today.getHour()+today.getMinute()+ today.getSecond()+".csv";
        
        try{
            File directorio=new File("Reportes"); 
            directorio.mkdir();
            FileWriter fw = new FileWriter("Reportes/"+archivo);
            fw.append("Nombre De Ciudadela,Razon Social,Numero Ingreso Visitantes,Tiempo Ingreso Promedios Visitantes,Numero Ingreso Residentes, Tiempo Ingreso Promedios Visitantes\n");
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
                if(numRes == 0){
                    promRes = 0;
                }else{
                    promRes = tiempoRes/numRes;
                }
                if(numVis == 0){
                    promVis = 0;
                }else{
                    promVis = tiempoVis/numVis;
                }
                
                       
                fw.append(nombreCiud+","+razonSocial+","+numVis+","+promVis+","+numRes+","+promRes+"\n");
                
            }
            fw.flush();
            fw.close();
            System.out.println("Se ha generado el reporte con exito! Su nombre es: \""+archivo+"\" y se encuentra en la carpeta reportes");
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void registrarCiudadela(SistemaCiudadelas sistema){
        String nombreC, razonSocial, RUC, ubicacion, nomAdmin, correoAdmin, idAdmin;
        String numManzanas, villasXManzana;
        LocalDateTime fInicioAdmin,fFinAdmin;
        AdministradorDeCiudadela ciudAdmin;
        System.out.print("Ingrese el nombre de la Ciudadela: ");
        nombreC = sc.nextLine();
        System.out.print("Ingrese la Razon Social: ");
        razonSocial = sc.nextLine();
        System.out.print("Ingrese el RUC: ");
        RUC = sc.nextLine();
        System.out.print("Ingrese la ubicacion de la ciudadela: ");
        ubicacion = sc.nextLine();
        idAdmin = Validaciones.ValidarId(sistema.getUsuarios(),"CiudAdmin");
        System.out.print("Ingrese el nombre del administrador: ");
        nomAdmin = sc.nextLine();
        System.out.print("Ingrese el correo del administrador: ");
        correoAdmin = sc.nextLine();
        //Obetniendo fecha de inicio de administracion
        do{
            System.out.println("Ingrese la fecha de inicio de la administracion del Admin: ");
            fInicioAdmin = Validaciones.consultarFecha();
            System.out.println("Ingrese la fecha de fin de la administracion del Admin: ");
            fFinAdmin = Validaciones.consultarFecha();
            if(fFinAdmin.isBefore(fInicioAdmin)){
                System.out.println("Su fecha de fin es menor a su fecha de inicio, por favor ingrese 2 fechas validas");
            }
        }while(fFinAdmin.isBefore(fInicioAdmin));
        System.out.print("Ingrese el numero de manzanas que tiene la ciudadela: ");
        numManzanas = sc.nextLine();
        while(!Validaciones.isNumeric(numManzanas)){
            System.out.print("Ingrese el numero de manzanas que tiene su ciudadela: ");
            numManzanas = sc.nextLine();
        }
        System.out.print("Ingrese el numero de villas que tiene cada manzana de la ciudadela: ");
        villasXManzana = sc.nextLine();
        while(!Validaciones.isNumeric(villasXManzana)){
            System.out.print("Ingrese el numero de manzanas que tiene su ciudadela: ");
            villasXManzana = sc.nextLine();
        }
        ciudAdmin=new AdministradorDeCiudadela(nomAdmin, idAdmin, correoAdmin, fInicioAdmin,fFinAdmin);
        Usuario u = ciudAdmin;
        Ciudadela c = new Ciudadela(nombreC, razonSocial, RUC, ubicacion,ciudAdmin,Integer.parseInt(numManzanas),Integer.parseInt(villasXManzana));
        sistema.agregarCiudadela(c);
        sistema.agregarUsuario(u);
        System.out.println("Se ha registrado la ciudadela "+nombreC+", y las credenciales del administrador de la ciudadela son: "+u);
    }
          
}