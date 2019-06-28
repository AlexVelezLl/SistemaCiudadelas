/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sistema;
import Sesion.AdministradorDeCiudadela;
import Sesion.Residente;
import java.util.ArrayList; 
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author Alex Velez
 */
public class SistemaCiudadelas {
    private ArrayList<Ciudadela> ciudadelas; 
    private ArrayList<Usuario> usuarios;
    
public SistemaCiudadelas(){
    inicializarSistema(); 
    ciudadelas= new ArrayList();
    usuarios= new ArrayList();
}

    public ArrayList<Ciudadela> getCiudadelas() {
        return ciudadelas;
    }

    public void setCiudadelas(ArrayList<Ciudadela> c){
        ciudadelas = c;
    }
    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }
    
    public void setUsuarios(ArrayList<Usuario> users){
        usuarios = users;
    }
    public void agregarCiudadela(Ciudadela c){
        ciudadelas.add(c); 
    }
    
    public void agregarUsuario(Usuario u){
        usuarios.add(u); 
    }


    public void inicializarSistema(){
    
    LocalDateTime fInicio = LocalDateTime.of(2015, 12, 1,0,0);
    LocalDateTime fFin = LocalDateTime.of(2020, 12, 1,0,0);
    
    //public AdministradorDeCiudadela(String nombre, String identificacion, String correo, LocalDateTime fInicio,LocalDateTime fFin){
    AdministradorDeCiudadela adm1 = new AdministradorDeCiudadela("Lupita Lopez","0901921921","luloppez@gmail.com",fInicio,fFin);
    usuarios.add(adm1); 
    //public Ciudadela(String nombre, String RazonSocial, String RUC, String ubicacion,AdministradorDeCiudadela admin,int numManzanas,int villasXManzana){

    Ciudadela c1 = new Ciudadela("Ciudad celeste","CORPACEL","0992426357001","SAMBORONDON , Via Samborondon",adm1,8,10);
    ciudadelas.add(c1);  
   
    Residente resd1,resd2;
    resd1= new Residente("Juan Perez","juanperez@gmail.com","0908204012","2586589",c1.getCasas().get(0));
    usuarios.add(resd1);
    c1.getCasas().get(0).setResidente(resd1);
  
    resd2= new Residente("Juana Crespo","juanacrespo@gmail.com","0906080011","2584586", c1.getCasas().get(1));
    usuarios.add(resd2);
    c1.getCasas().get(1).setResidente(resd2);

    }
  
    }

