/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sistema;
import Sesion.Residente; 

/**
 *
 * @author Alex Velez
 */
public class Casa {
    private String manzana;
    private String villa;
    private Residente residente;
    
    

    public String getManzana() {
        return manzana;
    }

    public String getVilla() {
        return villa;
    }

    public Residente getResidente() {
        return residente;
    }
    
    public String toString(){
        return "Manzana: "+manzana+"Villa: "+villa+"Residente: "+residente; 
    }

    public Casa(String manzana, String villa, Residente residente) {
        this.manzana = manzana;
        this.villa = villa;
        this.residente = residente;
    }
    public Casa(String manzana,String villa){
        this(manzana,villa,null);
    }
    
    public Casa(){
    }
  
    public void setResidente(Residente r){
        residente= r;
    }
    



}
