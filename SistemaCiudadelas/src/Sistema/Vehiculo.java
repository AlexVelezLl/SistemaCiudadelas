/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sistema;

/**
 *Clase que se modela como vehiculo
 * @author CORE i7 ULTIMATE
 */
public class Vehiculo {
    private final String  matricula, nomProp;
    public Vehiculo(String matricula, String nomProp){
        this.matricula = matricula;
        this.nomProp = nomProp;
    }
/**
 * Obtener matricula
 * @return matricula String con la matricula del vehiculo
 */
    public String getMatricula() {
        return matricula;
    }
/**
 * Obtener nombre de propietario
 * @return nomProp String con el nombre del propietario del vehiculo
 */
    public String getNomProp() {
        return nomProp;
    }
    /**
     * Metodo equals que comprobara que es el mismo vehiculo si la matricula es igual
     * @param obj Objeto con el que se comparara
     * @return boolean true si son el mismo y false si no son el mismo vehiculo
     */
    @Override
    public boolean equals(Object obj){
        if(obj!=null && obj instanceof Vehiculo){
            return (((Vehiculo)obj).getMatricula().equals(matricula));
        }
        return false;
    }
}
