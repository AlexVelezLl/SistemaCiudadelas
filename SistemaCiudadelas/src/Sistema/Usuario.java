/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sistema;
import java.util.Scanner; 
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;
import utilities.Persona;
/**
 *
 * @author joangie
 */
public abstract class Usuario implements Persona{
    protected String username; 
    protected String password;
    
    /**
     * Constructor vacio de usuario que será el que generará un username único  
     */
    public Usuario(){
    String credencial="";
    Random rnd=new Random();
            for(int i=0;i<4;i++){
                credencial +=(char)(rnd.nextInt(26)+65);
            }
            for(int i=0;i<4;i++){
                credencial +=(int)(rnd.nextInt(9));
            }
            username = credencial;
            password = credencial;
    }
   
    /**
     * Constructor de usuario que recibe como parametro el arrayList de users para verificar que si el que se genera ya existe 
     * se crean las credenciales para usuarios del sistema
     * @param users 
     */
    public Usuario(ArrayList<Usuario>users){ //para cuando se registran nuevos tipos
        ArrayList<String> usernames= new ArrayList<>();
        for(Usuario user:users){//Obteniendo todos los usernames para comprobar si el que se genero ya existe
            usernames.add(user.getUsername());
        }
        String credencial="";
        do{
            Random rnd=new Random();
            for(int i=0;i<4;i++){
                credencial +=(char)(rnd.nextInt(26)+65);
            }
            for(int i=0;i<4;i++){
                credencial +=(int)(rnd.nextInt(9));
            }
            username = credencial;
            password = credencial;
            
        }while(usernames.contains(credencial));
    }
    
    /**
     * Constructor de usuario que recibe como parametro una password y username 
     * este constructor solo es usado para crear al superadmin
     * @param username String con el nombre de usuario (superadmin) 
     * @param password String con la contraseña del usuario (superadmin)
     */
    public Usuario(String username, String password) { //usado no mas para el superadmin
        this.username = username;
        this.password = password;
    }
    /**
     * Getter de username 
     * se obtiene el username del usuario
     * @return username String con el nombre de usuario del residente
     */
    public String getUsername() {
        return username;
    }
    /**
     * Getter de password 
     * se obtiene el password del usuario
     * @return password String con la contraseña del usuario del residente 
     */
    public String getPassword() {
        return password;
    }
  
    /**
     * toString de Usuario que muestra por pantalla username y password de usuario
     * @return String que muestra username y password 
     */
    @Override
    public String toString() {
        return "username: " + username + ", password: " + password;
    }
    /**
     * Metodo abstracto. Su implementacion mostrará la informacion dependiendo del usuario.
     */
    public abstract void mostrarMiInformacion();    
    /**
     * Cambiar credenciales de usuario, su username y password dependiendo de la opcion que escoja el usuario
     * @param usuarios ArrayList con todos los usuarios del sistema
     */
    
    public void cambiarCredenciales(ArrayList<Persona> usuarios){
        Scanner sc = new Scanner(System.in);
        String usernameN;
        String opcion; 
        boolean used;
        boolean vieja;
        do{
            System.out.println("Opciones: ");
            System.out.println("1. Cambiar username y password");
            System.out.println("2.Cambiar username");
            System.out.println("3.Cambiar password");
            System.out.println("4.Salir");
            System.out.print("¿Que desea hacer?: ");
            opcion = sc.nextLine();
            switch(opcion){
                
               
                case "1":
                    do{
                        used = false;
                        System.out.print("Ingrese nuevo username: ");
                        usernameN= sc.nextLine();
                        for(Persona u:usuarios){
                            if(usernameN.equals(((Usuario)u).getUsername())){
                                System.out.println("El username que usted ingreso ya se encuentra registrado, por favor ingrese uno nuevo");
                                used = true;
                            }
                        }
                        username = usernameN;

                    }while(used);
                     do{
                        vieja= false; 
                        String viejaPassword= password;
                        System.out.print("Ingrese nueva password: ");
                        String passwordN = sc.nextLine(); 
                        if(viejaPassword.equals(passwordN)){
                                JOptionPane.showMessageDialog(null,"La password ingresada ha sido utilizada antes");
                                vieja= true; 
                        }
                        password= passwordN; 
                    }while(vieja); 
                     
                     System.out.println("Sus credenciales se han cambiado exitosamente!");
                 break; 

                 
                case "2":
                    do{
                        used = false;
                        System.out.print("Ingrese nuevo username: ");
                        usernameN= sc.nextLine();
                        for(Persona u:usuarios){
                            if(usernameN.equals(((Usuario)u).getUsername())){
                                JOptionPane.showMessageDialog(null,"El username que usted ingreso ya se encuentra registrado, por favor ingrese uno nuevo");
                                used = true;
                            }
                        }
                    username = usernameN;
                    }while(used);
                    
                    System.out.println("Su username se ha cambiado exitosamente!");
                  break; 

                  
                case "3":
                    do{
                        vieja= false; 
                        String viejaPassword= password;
                        System.out.print("Ingrese nueva password: ");
                        String passwordN = sc.nextLine(); 
                        if(viejaPassword.equals(passwordN)){
                                JOptionPane.showMessageDialog(null,"La password ingresada ha sido utilizada antes");
                                vieja= true; 
                        }
                    password= passwordN; 
                    }while(vieja); 
                        System.out.println("Su clave se ha cambiado exitosamente!");

                case "4":
                    
                    System.out.println("Regresando...");
                    
                    
                    break;
                    
                    
                default:
                    
                    
                    JOptionPane.showMessageDialog(null,"No ha ingresado una opcion valida, por favor Ingrese una opcion valida."); 
            } 
            }while(!opcion.equals("4"));

    }
}


