
package org.AngelAlfaro.controllers;

import java.sql.ResultSet;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.AngelAlfaro.conexion.Conexion;
import org.AngelAlfaro.system.Loader;


public class LoginController implements Initializable {
    
    @FXML
    private TextField txtUsuario;
    
    @FXML
    private PasswordField psContrasena;
    
    @FXML
    private void login(){
        //System.out.println("Hola");
        String usuario = txtUsuario.getText().trim();
        String con = psContrasena.getText().trim();
        
        if(usuario.isEmpty() || con.isEmpty()){
            Loader.getSingleton().alerta(Alert.AlertType.WARNING,"Alerta","Uno de los campos o ambos estan vacios");
            return;
        }
        
        try{
            Connection cn = Conexion.getInstancia().getConnection();
            try (CallableStatement c = cn.prepareCall("{call sp_ValidarLogin(?,?)}")){
                
                c.setString(1,usuario);
                c.setString(2,con);
                
                try (ResultSet rs = c.executeQuery()){
                    if(rs.next()){
                        
                        Loader.getSingleton().alerta(Alert.AlertType.CONFIRMATION,"Ingreso","Ingreso Exitoso");
                        cargarMenuPrincipal();
                        
                    } else{
                        Loader.getSingleton().alerta(Alert.AlertType.ERROR,"Error","Usuario o Contrana incorrecta");
                    }
                }
                
            } catch (Exception e){
                //alerta(Alert.AlertType.ERROR,"Error","no se puede hacer la peticion");
            }
        } catch(Exception e){
            e.getStackTrace();
            Loader.getSingleton().alerta(Alert.AlertType.ERROR,"Error","No se puede conectar a la base de datos");
        }
    }
    
    @FXML
    private void signIn(){
        try {
            Loader.getSingleton().newScene("../views/SignInMuni.fxml","SignIn");
        } catch (Exception e){
            Loader.getSingleton().alerta(Alert.AlertType.ERROR,"Cargar Escena","Error al cargar la escena");
        }
    }
    
    private void cargarMenuPrincipal(){
        try{
            
            Loader.getSingleton().newScene("../views/HomeMuni.fxml","Home");
            
        } catch (Exception e){
            Loader.getSingleton().alerta(Alert.AlertType.ERROR, "Error","No se cargo el Menu");
            System.out.println(e.toString());
        }
    }
    
    

    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        //alerta(Alert.AlertType.ERROR,"Error","");
        //System.out.println("Hola");
    }
    
}
