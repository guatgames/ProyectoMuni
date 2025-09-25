
package org.AngelAlfaro.controllers;

import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import org.AngelAlfaro.conexion.Conexion;

import org.AngelAlfaro.system.Loader;
import org.AngelAlfaro.system.Main;

/**
 *
 * @author Usuario
 */
public class SignInController implements Initializable {
    
    @FXML
    private TextField  txtNom;
    
    @FXML
    private PasswordField psContrasena, psConfirmacion;
    
    @FXML
    private void login(){
        try {
            Loader.getSingleton().newScene("../views/LoginMuni.fxml","Login");
        } catch (Exception e){
            Loader.getSingleton().alerta(Alert.AlertType.ERROR,"Cargar Escena","Error al cargar la escena");
        }
    }
    
    @FXML 
    private void signIn(){
        
        String nom = txtNom.getText();
        String cont = psContrasena.getText();
        String conf = psConfirmacion.getText();
        
        if (!nom.isEmpty() && !cont.isEmpty() && !conf.isEmpty()){

            if (cont.equals(conf)){

                try{
                    Connection cn = Conexion.getInstancia().getConnection();
                    try (CallableStatement c = cn.prepareCall("{call sp_login_create(?,?)}")){

                        c.setString(1,nom);
                        c.setString(2,cont);

                        try (ResultSet rs = c.executeQuery()){
                            
                            while (rs.next()){
                                
                                Main.curren_user = rs.getInt("id");
                                Main.current_name = nom;
                            
                                //System.out.println(Main.curren_user);
                                
                            }

                            Loader.getSingleton().alerta(Alert.AlertType.CONFIRMATION,"Operacion Realizada","Se agrego el usuario correctamente");

                            Loader.getSingleton().newScene("../views/HomeMuni.fxml", "Home");
                            
                            while(rs.next()){



                            }
                        }

                    } catch (Exception e){
                        //Loader.getSingleton().alerta(Alert.AlertType.ERROR,"Error","no se puede hacer la peticion");
                    }
                } catch(Exception e){
                    e.getStackTrace();
                    Loader.getSingleton().alerta(Alert.AlertType.ERROR,"Error","No se puede conectar a la base de datos");
                }

            } else {

                Loader.getSingleton().alerta(Alert.AlertType.WARNING, "Confirmacion erronea", "Las contraseñas no concuerdan");

            }
            
        } else {
            
            Loader.getSingleton().alerta(Alert.AlertType.WARNING, "Campo vacio", "uno de los campos esta vacio");
            
        }
        
    }
    
    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        //alerta(Alert.AlertType.ERROR,"Error","");
        //System.out.println("Hola");
    }
    
}
