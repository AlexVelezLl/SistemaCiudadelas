/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sistema;
import Sesion.Residente;
import java.util.ArrayList; 
import java.time.LocalDate;

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

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }
    
    public void agregarCiudadela(Ciudadela c){
        ciudadelas.add(c); 
    }
    
    public void agregarUsuario(Usuario u){
        usuarios.add(u); 
    }


    public void inicializarSistema(){
    
    LocalDate fInicio = LocalDate.of(2015, 12, 1);
    AdministradorCiudadela adm1 = new AdministradorCiudadela("Lupita Lopez","luloppez@gmail.com",fInicio,"0901921921","ciudAdmin1","jUQ!$190");
    usuarios.add(adm1); 
    Ciudadela c1 = new Ciudadela("Ciudad celeste","CORPACEL","0992426357001","SAMBORONDON , Via Samborondon",adm1);
    ciudadelas.add(c1);  
    
    
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 10; j++) {
                Casa c = new Casa(Integer.toString(i),Integer.toString(j));
                c1.getCasas().add(c);
                }     
            }
        
    Residente resd1,resd2;
    resd1= new Residente("Juan Perez","juanperez@gmail.com","0908204012","2586589",c1.getCasas().get(0),"resd1C1","jLP#@121");
    usuarios.add(resd1);
    c1.getCasas().get(0).setResidente(resd1);
  
    resd2= new Residente("Juana Crespo","juanacrespo@gmail.com","0906080011","2584586", c1.getCasas().get(1),"resd2C1","aQH%!087");
    usuarios.add(resd2);
    c1.getCasas().get(1).setResidente(resd2);

    }
    
    
    
    }
