/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sesion;
import Sistema.*;
import java.util.ArrayList;
/**
 *
 * @author Alex Velez
 */
public class Residente extends Usuario{
    
private String nombre;
    private String correo;
    private String ID;
    private String pinAcceso;
    private String telefono;
    private Casa casa;
    private ArrayList<Visitante> visitantes;
    private ArrayList<String> vehiculos;
    private ArrayList<String> codigosAcceso;

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public String getID() {
        return ID;
    }

    public String getPinAcceso() {
        return pinAcceso;
    }

    public String getTelefono() {
        return telefono;
    }

    public Casa getCasa() {
        return casa;
    }

    public ArrayList<Visitante> getVisitantes() {
        return visitantes;
    }

    public ArrayList<String> getVehiculos() {
        return vehiculos;
    }

    public ArrayList<String> getCodigosAcceso() {
        return codigosAcceso;
    }

    public Residente() {
    }

    public Residente(String nombre, String correo, String ID, String pinAcceso, String telefono, Casa casa, String username, String password) {
        super(username,password);
        this.nombre = nombre;
        this.correo = correo;
        this.ID = ID;
        this.pinAcceso = pinAcceso;
        this.telefono = telefono;
        this.casa = casa;
        visitantes = new ArrayList() ;
        vehiculos = new ArrayList();
        codigosAcceso = new ArrayList();
    }
    

    public void registrarVisitante(Visitante visitante){
        visitantes.add(visitante);
    }
    
    public void registrarVehiculo(String matricula){
        vehiculos.add(matricula);
    }

    public void cambiarPin(String pinAcceso) {
        this.pinAcceso = pinAcceso;
    }
    
    public void borrarVisitante(Visitante visitante){
    
    }
    
    public void verListaDeVisitantes(){
        for(Visitante visitante: visitantes){
            System.out.println(visitante);
        }
    }
}
