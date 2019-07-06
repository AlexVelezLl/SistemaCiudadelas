/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sistema;
import Sesion.*;
import Simulacro.*;
import java.util.ArrayList;


/**
 *Clase que se modela como una ciudadela
 * @author joangie
 */
public class Ciudadela {    
    private String nombre;
    private String RazonSocial;
    private String RUC;
    private String ubicacion;
    private AdministradorDeCiudadela adm;
    private ArrayList<Casa> casas;
    private ArrayList<Residente> residentes;
    private ArrayList<RegistroIngreso> ingresos;
    private PuntoAcceso[] puntosAcceso; 
    
    /**
     * Obtiene el nombre de la ciudadela
     * @return nombre String con el nombre de la ciudadela
     */
    public String getNombre() {
        return nombre;
    }
    /**
     * Obtiene los puntos de acceso de las ciudadelas
     * @return puntosAcceso Array de puntos de acceso 
     */
    public PuntoAcceso[] getPuntosAcceso(){
        return puntosAcceso;
    }
    /**
     * Obtiene la razon social
     * @return RazonSocial String con la razon social de la ciudadela
     */
    public String getRazonSocial() {
        return RazonSocial;
    }
    /**
     * Obtiene el ruc de la ciudadela
     * @return ruc String con el ruc de la ciudadela
     */
    public String getRUC() {
        return RUC;
    }
    /**
     * Metodo que obtiene la ubicacion de la ciudadela
     * @return ubicacion String con la ubicacion de la ciudadela
     */
    public String getUbicacion() {
        return ubicacion;
    }
    /**
     * Metodo que obtiene al administrador de ciudadela
     * @return adm AdministradorDeCiudadela que tendra toda la informacion de la ciudadela
     */

    public AdministradorDeCiudadela getAdm() {
        return adm;
    }
    /**
     * Metodo que obtiene las casas de la ciudadela
     * @return casas ArrayList de tipo Casas con la informacion de las casas de la ciudadela
     */
    public ArrayList <Casa> getCasas() {
        return casas;
    }
    /**
     * Metodo que obtiene los residentes
     * @return residentes ArrayList de Residentes que viven en la ciudadela
     */
    public ArrayList<Residente> getResidentes() {
        return residentes;
    }
    /**
     * Metodo para obtener los ingresos 
     * @return ingresos ArrayList del tipo RegistroIngreso que contara con toda la informacion de los ingresos
     */
    public ArrayList<RegistroIngreso> getIngresos() {
        return ingresos;
    }
    /**
     * Agregar ingreso
     * @param ingreso RegistroIngreso con toda la informacion sobre el ingreso
     */
    public void addIngreso(RegistroIngreso ingreso){
        ingresos.add(ingreso);
        
    }
    
    /**
     * Metodo que mostrara por pantalla la informacion de la ciudadela
     * @return nombre de ciudadela, razon social, ruc, ubicacion, administrador de ciudadela, casas, residentes,ingresos
     */
    public String toString() {
        return "Nombre de ciudadela: " + nombre + ", Razon Social: " + RazonSocial + ", RUC: " + RUC + ", ubicacion: " + ubicacion + ", Administrador de Ciudadela: " + adm + ", casas: " + casas + ", residentes: " + residentes + ", ingresos: " + ingresos;
    }
    /**
     * Constructor vacio de ciudadela
     */
    public Ciudadela(){
        
    }
    /**
     * Constructor de ciudadela 
     * @param nombre String con el nombre de ciuadela 
     * @param RazonSocial String con la razon social de la ciudadela
     * @param RUC String con el RUC de la ciudadela
     * @param ubicacion String con la ubicacion de la ciudadela
     * @param admin AdministradorDeCiudadela 
     * @param numManzanas  int con el numero de manzanas que habra en la ciudadela
     * @param villasXManzana int con el numero de villas que habra en la ciudadela
     */
    public Ciudadela(String nombre, String RazonSocial, String RUC, String ubicacion,AdministradorDeCiudadela admin,int numManzanas,int villasXManzana){
        this.nombre = nombre;
        this.RazonSocial = RazonSocial;
        this.RUC = RUC;
        this.ubicacion = ubicacion;
        residentes = new ArrayList(); 
        ingresos= new ArrayList();
        this.adm= admin; 
        casas = new ArrayList<>();
        for(int i=1;i<=numManzanas;i++){
            for(int j=1;j<=villasXManzana;j++){
                casas.add(new Casa(Integer.toString(i),Integer.toString(j),this));
            }
        }
        puntosAcceso = new PuntoAcceso[2];
        puntosAcceso[0]= new PuntoAccesoResidentes(this); 
        puntosAcceso[1]= new PuntoAccesoVisitantes(this);
        
    }
    /**
     * Añade un residente a la ciudadela
     * @param r Objeto de tipo Residente que sera agregado
     */
    public void addResidente(Residente r) {
        residentes.add(r); 
    }
    
    
}
