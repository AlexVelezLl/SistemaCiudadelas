/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sistema;

/**
 *
 * @author PC
 */
public class PuntoAcceso {
    private BrazoRobotico brazoRobotico;
    private String tipoAcceso;
    private boolean Acceso;
    private String Datos;
    
    public PuntoAcceso(){
        
    }
    
    public Ingreso GenerarIngreso(){
        Acceso= true;
        Ingreso i1 = null;//Aqui se creará un objeto de clase ingreso más adelante
        return i1;
    }

    public String gettipoIngreso() {
        return tipoAcceso;
        
    }
}
