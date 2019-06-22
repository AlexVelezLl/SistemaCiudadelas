/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sistema;
import java.time.LocalDateTime; 

/**
 *
 * @author joangie
 */
public class CodigoAcceso {
    private LocalDateTime fechaIngreso; 
    private boolean used; 
    private String codigo; 
    
    public CodigoAcceso(){
    
    }

    public CodigoAcceso(LocalDateTime fechaIngreso, String codigo){
        this.fechaIngreso = fechaIngreso;
        this.used = false;
        this.codigo = codigo;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public LocalDateTime getFechaIngreso() {
        return fechaIngreso;
    }

    public String getCodigo() {
        return codigo;
    }

    public boolean isUsed() {
        return used;
    }
    
    
    
    
}
