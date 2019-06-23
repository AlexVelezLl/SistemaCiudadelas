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
 *
 * @author joangie
 */
public class Ciudadela {    
    private String nombre;
    private String RazonSocial;
    private String RUC;
    private String ubicacion;
    private AdministradorDeCiudadela adm;
    private Casa[] casas;
    private ArrayList<Residente> residentes;
    private ArrayList<RegistroIngreso> ingresos;
    private PuntoAcceso[] puntosAcceso; 
    
    
    public String getNombre() {
        return nombre;
    }
    public PuntoAcceso[] getPuntosAcceso(){
        return puntosAcceso;
    }
    public String getRazonSocial() {
        return RazonSocial;
    }

    public String getRUC() {
        return RUC;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public AdministradorDeCiudadela getAdm() {
        return adm;
    }

    public Casa[] getCasas() {
        return casas;
    }

    public ArrayList<Residente> getResidentes() {
        return residentes;
    }

    public ArrayList<RegistroIngreso> getIngresos() {
        return ingresos;
    }
    
    public void setIngresos(ArrayList<RegistroIngreso> ingresos){
        this.ingresos = ingresos;
        
    }
    public String toString() {
        return "Nombre de ciudadela: " + nombre + ", Razon Social: " + RazonSocial + ", RUC: " + RUC + ", ubicacion: " + ubicacion + ", Administrador de Ciudadela: " + adm + ", casas: " + casas + ", residentes: " + residentes + ", ingresos: " + ingresos;
    }

    public Ciudadela(){
        
    }

    public Ciudadela(String nombre, String RazonSocial, String RUC, String ubicacion,AdministradorDeCiudadela admin,int numManzanas,int villasXManzana){
        this.nombre = nombre;
        this.RazonSocial = RazonSocial;
        this.RUC = RUC;
        this.ubicacion = ubicacion;
        residentes = new ArrayList(); 
        ingresos= new ArrayList();
        puntosAcceso = new PuntoAcceso[2];
        puntosAcceso[0]= new PuntoAccesoResidentes(); 
        puntosAcceso[1]= new PuntoAccesoVisitantes();
        this.adm= admin; 
        casas = new Casa[numManzanas*villasXManzana];
        int num = 0;
        for(int i=0;i<numManzanas;i++){
            for(int j=0;j<villasXManzana;i++){
                casas[num] =new Casa(Integer.toString(i),Integer.toString(j));
                num++;
            }
        }
        
    }
    
    
}
