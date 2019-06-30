/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sistema;

/**
 *
 * @author CORE i7 ULTIMATE
 */
public class Vehiculo {
    private final String  matricula, nomProp;
    public Vehiculo(String matricula, String nomProp){
        this.matricula = matricula;
        this.nomProp = nomProp;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getNomProp() {
        return nomProp;
    }
    @Override
    public boolean equals(Object obj){
        if(obj!=null && obj instanceof Vehiculo){
            return (((Vehiculo)obj).getMatricula().equals(matricula));
        }
        return false;
    }
}
