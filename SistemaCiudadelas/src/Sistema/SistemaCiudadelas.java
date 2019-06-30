/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sistema;
import Sesion.*;
import java.util.ArrayList; 
import java.time.LocalDate;
import java.time.LocalDateTime;
import utilities.Persona;

/**
 *
 * @author Alex Velez
 */
public class SistemaCiudadelas {
    private ArrayList<Ciudadela> ciudadelas; 
    private ArrayList<Persona> usuarios;
    
public SistemaCiudadelas(){
    ciudadelas= new ArrayList();
    usuarios= new ArrayList();
    inicializarSistema(); 
}

    public ArrayList<Ciudadela> getCiudadelas() {
        return ciudadelas;
    }

    public void setCiudadelas(ArrayList<Ciudadela> c){
        ciudadelas = c;
    }
    public ArrayList<Persona> getUsuarios() {
        return usuarios;
    }
    
    public void agregarCiudadela(Ciudadela c){
        ciudadelas.add(c); 
    }
    
    public void agregarUsuario(Usuario u){
        usuarios.add(u); 
    }


    public void inicializarSistema(){
        AdministradorDeSistema superAdmin = new AdministradorDeSistema();
        Residente resd1,resd2;
        Vehiculo vehi1, vehi2;
        usuarios.add(superAdmin);
        LocalDateTime fInicio = LocalDateTime.of(2015, 12, 1,0,0);
        LocalDateTime fFin = LocalDateTime.of(2020, 12, 1,0,0);

        AdministradorDeCiudadela adm1 = new AdministradorDeCiudadela("Lupita Lopez","0901921921","luloppez@gmail.com",fInicio,fFin,"CiudAdmin","CiudAdmin");
        usuarios.add(adm1); 

        Ciudadela c1 = new Ciudadela("Ciudad celeste","CORPACEL","0992426357001","SAMBORONDON , Via Samborondon",adm1,2,4);
        ciudadelas.add(c1);  
        
        vehi1 = new Vehiculo("GTB-8303","Juan Perez");
        resd1= new Residente("Juan Perez","juanperez@gmail.com","0908204012","2586589",c1.getCasas().get(0),"0959",vehi1,"Residente1","Residente1");
        usuarios.add(resd1);
        c1.getCasas().get(0).setResidente(resd1);
        
        vehi2 = new Vehiculo("GSP-3404","Juana Crespo");
        resd2= new Residente("Juana Crespo","juanacrespo@gmail.com","0906080011","2584586", c1.getCasas().get(1),"4231",vehi2,"Residente2","Residente2");
        usuarios.add(resd2);
        c1.getCasas().get(1).setResidente(resd2);

    }
  
    }

