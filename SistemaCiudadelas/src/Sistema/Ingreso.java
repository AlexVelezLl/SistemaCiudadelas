/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sistema;
import java.time.LocalDateTime;
public class Ingreso {
    LocalDateTime fIngreso;
    int TiempoIngreso;
    String tipoIngreso;
    PuntoAcceso puntoAcceso;
    
    public Ingreso(){
        
    }
    
    public String ObtenerIngreso(){
        return (puntoAcceso.gettipoIngreso());
    }
    
}
