
package org.angelalfaro.controllers;

import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.AngelAlfaro.conexion.Conexion;
import org.AngelAlfaro.system.Loader;
import org.AngelAlfaro.system.Main;


public class UserViewController implements Initializable {
    
    @FXML
    private Label lblId;
    
    @FXML
    private TextField txtNom;
    
    @FXML
    private PasswordField psContrasena, psConfirmacion;
    
    @FXML
    private Button btnEliminar, btnEditar, btnLimpiar;
    
    private enum Mode{ELIMINAR,EDITAR,NULL};
    
    private Mode modeActual = Mode.NULL;
    
    private void setMode(Mode m){
        
        txtNom.setDisable(true);
        psContrasena.setDisable(true);
        psConfirmacion.setDisable(true);
        
        switch(m){
            case NULL:
                txtNom.setDisable(true);
                psContrasena.setDisable(true);
                psConfirmacion.setDisable(true);
                
                btnEliminar.setDisable(false);
                btnEditar.setDisable(false);
                btnLimpiar.setDisable(false);
                
                break;
            case ELIMINAR:
                txtNom.setDisable(false);
                txtNom.requestFocus();
                
                btnEditar.setDisable(true);
                
                break;
            case EDITAR:
                txtNom.setDisable(false);
                psContrasena.setDisable(false);
                psConfirmacion.setDisable(false);
                txtNom.requestFocus();
                
                btnEliminar.setDisable(true);
                
                break;
            
        }
        
    }
    
    private void delete(){
        
        String nom = txtNom.getText();
        //String zona = txtZona.getText();
        
        if (!nom.isEmpty() && nom.equals(Main.current_name)){
            
            try{
                Connection cn = Conexion.getInstancia().getConnection();
                try (CallableStatement c = cn.prepareCall("{call sp_login_delete(?)}")){

                    try {
                        
                        c.setInt(1,Main.curren_user);
                        //c.setString(2,zona);

                        try (ResultSet rs = c.executeQuery()){

                            Loader.getSingleton().alerta(Alert.AlertType.CONFIRMATION,"Operacion Realizada","Se elimino el usuario correctamente");
                            Loader.getSingleton().newScene("../views/LoginMuni.fxml", "Login");

                            while(rs.next()){



                            }
                        }
                        
                    } catch (NumberFormatException nfe){
                        
                        Loader.getSingleton().alerta(Alert.AlertType.WARNING, "Valor invalido", "El valor en el campo id no es numerico");
                        
                    }
                        

                } catch (Exception e){
                    Loader.getSingleton().alerta(Alert.AlertType.ERROR,"Error","no se puede hacer la peticion");

                }
            } catch(Exception e){
                e.getStackTrace();
                Loader.getSingleton().alerta(Alert.AlertType.ERROR,"Error","No se puede conectar a la base de datos");
            }
            
        } else {
            
            Loader.getSingleton().alerta(Alert.AlertType.WARNING, "Campo vacio", "El campo nombre esta vacio");
            
        }
        
    }
    
    @FXML
    private void onEliminar(){
        
        if(modeActual != Mode.ELIMINAR){
            setMode(modeActual.ELIMINAR);
            Loader.getSingleton().alerta(Alert.AlertType.INFORMATION, "ELIMINAR", "INGRESE SU NOMBRE PARA COMFIRMAR");
            return;
        } else {
            delete();
            onLimpiar();
        }
        
    }
    
    private void update(){
        
        String nom = txtNom.getText();
        String cont = psContrasena.getText();
        String conf = psConfirmacion.getText();
        
        if (!nom.isEmpty() && !cont.isEmpty() && !conf.isEmpty()){
            
            if (cont.equals(conf)){
                
                try{
                    Connection cn = Conexion.getInstancia().getConnection();
                    try (CallableStatement c = cn.prepareCall("{call sp_login_update(?,?)}")){

                        try {

                            c.setString(1,nom);
                            c.setString(2,cont);

                            try (ResultSet rs = c.executeQuery()){

                                Loader.getSingleton().alerta(Alert.AlertType.CONFIRMATION,"Operacion Realizada","Se actualizo el usuario correctamente");

                                while(rs.next()){



                                }
                            }

                        } catch (NumberFormatException nfe){

                            Loader.getSingleton().alerta(Alert.AlertType.WARNING, "Valor invalido", "El valor en el campo id no es numerico");

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
    private void onEditar(){
        
        if(modeActual != Mode.EDITAR){
            setMode(modeActual.EDITAR);
            return;
        } else {
            update();
            onLimpiar();
        }
        
    }
    
    @FXML
    private void onLimpiar(){
        txtNom.clear();
        psContrasena.clear();
        psConfirmacion.clear();
        setMode(modeActual.NULL);
    }
    
    @FXML
    private void home(){
        try{
            
            Loader.getSingleton().newScene("../views/HomeMuni.fxml","Home");
            
        } catch (Exception e){
            Loader.getSingleton().alerta(Alert.AlertType.ERROR, "Error","No se cargo la escena");
        }
    }
    
    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        
        lblId.setText("User: " + Main.current_name);
        
        setMode(modeActual);
       
    }
    
}
