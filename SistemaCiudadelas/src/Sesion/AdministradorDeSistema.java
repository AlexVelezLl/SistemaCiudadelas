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
import javax.swing.JOptionPane;
import utilities.*;
/**
 * Clase hija de Usuario que modela a un administrador de ciudadela.
 * @author Alex Velez
 */
public class AdministradorDeSistema extends Usuario{
    private final String nombre;
    private final String id;
    Scanner sc;
    
    /**
     * Constructor de la clase Administrador de sistema. Sus datos van a permanecer siempre constantes
     */
    public AdministradorDeSistema(){
        super("SuperAdmin","SuperAdmin");
        nombre = "Rocio Mera";
        id = "0978654321";
        sc = new Scanner(System.in);
    }
    /**
     * Metodo que obtiene la identificacion del administrador del sistema
     * @return String con el id del administrador
     */
    @Override
    public String getId(){
        return id;
    }
    
    /**
     * Metodo que obtiene el nombre del administrador del sistema
     * @return String con el nombre del administrador del sistema
     */
    public String getNombre(){
        return nombre;
    }
    /**
     * Metodo que genera un archivo .csv con la informacion del tiempo de demora del acceso de residente y visitantes.
     * @param ciudadelas ArrayList de todas las ciudadelas del sistema
     */
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
            JOptionPane.showMessageDialog(null,"Se ha generado el reporte con exito! Su nombre es: \""+archivo+"\" y se encuentra en la carpeta reportes");
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    /**
     * Metodo en el que se pregunta por pantalla la informacion necesaria para registrar a una ciudadela.
     * @param sistema Objeto que contiene al sistema general de sistema ciudadelas
     */
    public void registrarCiudadela(SistemaCiudadelas sistema){
        String nombreC, razonSocial, RUC, ubicacion, nomAdmin, correoAdmin, idAdmin;
        String numManzanas, villasXManzana,mensaje;
        Mailer mail = new Mailer();
        LocalDateTime fInicioAdmin,fFinAdmin;
        AdministradorDeCiudadela ciudAdmin;
        do{
            System.out.print("Ingrese el nombre de la Ciudadela: ");
            nombreC = sc.nextLine();
            if(verifiNomCiud(nombreC,sistema.getCiudadelas())){
                JOptionPane.showMessageDialog(null,"El nombre de la ciudadela que usted ingreso ya se encuentra registrado en el sistema, por favor ingrese otro nombre");
            }
        }while(verifiNomCiud(nombreC,sistema.getCiudadelas()));
        System.out.print("Ingrese la Razon Social: ");
        razonSocial = sc.nextLine();
        System.out.print("Ingrese el RUC: ");
        RUC = sc.nextLine();
        System.out.print("Ingrese la ubicacion de la ciudadela: ");
        ubicacion = sc.nextLine();
        idAdmin = Validaciones.ValidarId(sistema.getUsuarios(),"CiudAdmin");
        System.out.print("Ingrese el nombre del administrador: ");
        nomAdmin = sc.nextLine();
        do{
            System.out.print("Ingrese el correo del administrador: ");
            correoAdmin = sc.nextLine();
            if(!correoAdmin.contains("@")){
                JOptionPane.showMessageDialog(null, "El correo que usted ingreso es invalido, por favor ingrese un correo valido");
            }
        }while(!correoAdmin.contains("@"));
        //Obetniendo fecha de inicio de administracion
        do{
            System.out.println("Ingrese la fecha de inicio de la administracion del Admin: ");
            fInicioAdmin = Validaciones.consultarFecha();
            System.out.println("Ingrese la fecha de fin de la administracion del Admin: ");
            fFinAdmin = Validaciones.consultarFecha();
            if(fFinAdmin.isBefore(fInicioAdmin)){
                JOptionPane.showMessageDialog(null,"Su fecha de fin es menor a su fecha de inicio, por favor ingrese 2 fechas validas");
            }
        }while(fFinAdmin.isBefore(fInicioAdmin));
        do{
            System.out.print("Ingrese el numero de manzanas que tiene la ciudadela: ");
            numManzanas = sc.nextLine();
            if(!Validaciones.isNumeric(numManzanas)){
                JOptionPane.showMessageDialog(null,"Por favor ingrese un numero valido");
            }
        }while(!Validaciones.isNumeric(numManzanas));
        do{       
            System.out.print("Ingrese el numero de villas que tiene cada manzana de la ciudadela: ");
            villasXManzana = sc.nextLine();
            if(!Validaciones.isNumeric(villasXManzana)){
                JOptionPane.showMessageDialog(null,"Por favor ingrese un numero valido");
            }
        }while(!Validaciones.isNumeric(villasXManzana));
        ciudAdmin=new AdministradorDeCiudadela(nomAdmin, idAdmin, correoAdmin, fInicioAdmin,fFinAdmin);
        Ciudadela c = new Ciudadela(nombreC, razonSocial, RUC, ubicacion,ciudAdmin,Integer.parseInt(numManzanas),Integer.parseInt(villasXManzana));
        sistema.agregarCiudadela(c);
        sistema.agregarUsuario(ciudAdmin);
        mensaje = "Saludos! Estimado"+nomAdmin+".\nSe le informa que la cuidadela"+nombreC+" se ha registrado exitosamente en el sistema de SistemaCiudadelas. "
                + "Las credenciales que se le ha asignado son: "+ciudAdmin+".\n\nAtentamente,\nEquipo de SistemaCiudadelas";
        mail.enviarCorreo(correoAdmin, mensaje);
        JOptionPane.showMessageDialog(null,"Se ha registrado la ciudadela "+nombreC+", y las credenciales del administrador de la ciudadela se las ha enviado a su correo.");
    }
    /**
     * Metodo que verifica si el nombre de la ciuadela ya esta en uso por alguna otra ciudadela que este en el sistema.
     * @param nom String del nombre de la ciudadela
     * @param ciudadelas ArrayList de todas las ciudadelas del sistema
     * @return Valor de verdad de la condicion, devuelve true si el nombre ya esta en uso
     */
    private boolean verifiNomCiud(String nom,ArrayList<Ciudadela>ciudadelas){
        for(Ciudadela c: ciudadelas){
            if(nom.equals(c.getNombre())) return true;
        }
        return false;
    }
    
    @Override
    /**
     * Metodo que muestra por pantalla toda la informacion del administrador del sistema
     */
    public void mostrarMiInformacion() {  
        System.out.println("\nInformacion del administrador de Sistema: ");
        System.out.printf("%s: %-10s\n","Nombre",getNombre());
        System.out.printf("%-6s: %-10s\n\n","Id",getId()); 
    }

}