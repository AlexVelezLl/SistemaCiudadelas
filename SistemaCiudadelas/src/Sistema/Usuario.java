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
 * @author Alex Velez
 */
public abstract class Usuario implements Persona{
    protected String username; 
    protected String password;
    
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
    
    public Usuario(String username, String password) { //usado no mas para el superadmin
        this.username = username;
        this.password = password;
    }
    
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    @Override
    public String toString() {
        return "username: " + username + ", password: " + password;
    }
    
    
    
    public void cambiarCredenciales(ArrayList<Persona> usuarios){
        Scanner sc = new Scanner(System.in);
        String usernameN;
        boolean used;
        
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
        }while(used);
        username = usernameN;
        System.out.print("Ingrese nueva password: ");
        String passwordN = sc.nextLine(); 
        password = passwordN;
        JOptionPane.showMessageDialog(null,"Sus credenciales se han cambiado exitosamente!");
    }
  
}
