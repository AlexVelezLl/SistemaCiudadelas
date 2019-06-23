/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sistema;
import java.util.Scanner; 
import java.util.ArrayList;
import java.util.Random;
/**
 *
 * @author Alex Velez
 */
public class Usuario {
    protected String username; 
    protected String password;
    public Usuario(){}
    public Usuario(ArrayList<Usuario>users){
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
    
    public Usuario(String username, String password) {
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
    
    public boolean equals(Object obj){
        if(obj!=null && obj instanceof Usuario){
           Usuario u= (Usuario)obj; 
           if(username.equals(u.getUsername())){
               return true;
           }
        }
        return false;
    }

    @Override
    public String toString() {
        return "username: " + username + ", password: " + password;
    }
    
    
    
    public void cambiarCredenciales(ArrayList<Usuario> usuarios){
        Scanner sc = new Scanner(System.in);
        //Se preguntan el usuario antiguo
        System.out.println("Ingrese username a cambiar: ");
        String pastUsername= sc.nextLine();
        System.out.println("Ingrese password a cambiar: ");
        String pastPassword= sc.nextLine();
        Usuario pastUser= new Usuario(pastUsername,pastPassword);
        
        for(Usuario u: usuarios){
            if(u.equals(pastUser)){
                System.out.println("Ingrese nuevo username: ");
                String username= sc.nextLine(); 
                setUsername(username); 
                System.out.println("Ingrese nueva password: ");
                String password = sc.nextLine(); 
                setPassword(password);
            
            }
        }                     
    }
  
}
