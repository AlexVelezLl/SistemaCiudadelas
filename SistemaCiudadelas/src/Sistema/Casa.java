/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sistema;
import Sesion.Residente; 

/**
 * Clase que contiene la información de la casa de una ciudadela
 * @author joangie
 */
public class Casa {
    private String manzana;
    private String villa;
    private Residente residente;
    private Ciudadela ciudadela;
    
    /**
     * Obtener manzana de casa 
     * @return manzana String con la manzana de la casa
     */
    public String getManzana() {
        return manzana;
    }
    /**
     * Obtener villa de casa 
     * @return villa String con la villa de la casa
     */
    public String getVilla() {
        return villa;
    }
    /**
    * Obtener Residente de casa 
    * @return Residente Residente que se encontrara en la casa
    */

    public Residente getResidente() {
        return residente;
    }
    /**
     * toString de la casa que mostrara por pantalla manzana, villa y si existe un residente
     * @return String dque se mostrara por pantalla manzana, villa y si existe un residente
     */
    public String toString(){
        return "Manzana: "+manzana+"Villa: "+villa+"Residente: "+residente; 
    }
    /**
     * Constructor de casa 
     * @param manzana String con la manzana en la que se encontrara la casa
     * @param villa String con la villa en la que se encontrara la casa
     * @param ciudadela Objeto de tipo ciudadela en la que se encontrara la casa
     * @param residente Objeto de tipo Residente que sera el dueño de casa 
     */
    public Casa(String manzana, String villa, Ciudadela ciudadela, Residente residente) {
        this.manzana = manzana;
        this.villa = villa;
        this.residente = residente;
        this.ciudadela = ciudadela;
    }
    /**
     * Constructor de casa
     * 
     * @param manzana String con la manzana de la casa 
     * @param villa String con la villa de la casa
     * @param ciudadela Objeto del tipo Ciudadela que sera donde se encontrara la casa
     */
    public Casa(String manzana,String villa, Ciudadela ciudadela){
        this(manzana,villa,ciudadela,null);
    }
    
    /**
     * Constructor vacio de casa
     */
    public Casa(){
    }
  
    /**
     * Cambiara el residente que tiene una casa
     * @param r 
     */
    public void setResidente(Residente r){
        ciudadela.addResidente(r);
        residente= r;
    }
    

}
