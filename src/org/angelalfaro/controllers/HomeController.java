
package org.AngelAlfaro.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import org.AngelAlfaro.system.Loader;
import org.AngelAlfaro.system.Main;


public class HomeController implements Initializable {
    
    @FXML
    private Button btnZonas, btnCiudadanos, btnConductores, btnLicencias, btnVehiculos, btnAgentes;
    
    @FXML
    private Button btnInfracciones, btnMultas, btnPagos, btnUsuario;
    
    @FXML
    private void handleButtonAction(ActionEvent e){
        
        if(e.getSource() == btnZonas){
            
            try {
                Loader.getSingleton().newScene("../views/ZonasCRUD.fxml","Zonas");
            } catch (Exception ev){
                Loader.getSingleton().alerta(Alert.AlertType.ERROR,"Cargar Escena","Error al cargar la escena");
            }
            
        } else if(e.getSource() == btnCiudadanos){
            
            try {
                Loader.getSingleton().newScene("../views/CiudadanosCRUD.fxml","Ciudadanos");
            } catch (Exception ev){
                Loader.getSingleton().alerta(Alert.AlertType.ERROR,"Cargar Escena","Error al cargar la escena");
            }
            
        } else if(e.getSource() == btnConductores){
            
            try {
                Loader.getSingleton().newScene("../views/ConductoresCRUD.fxml","Conductores");
            } catch (Exception ev){
                Loader.getSingleton().alerta(Alert.AlertType.ERROR,"Cargar Escena","Error al cargar la escena");
            }
            
        } else if(e.getSource() == btnLicencias){
            
            try {
                Loader.getSingleton().newScene("../views/LicenciasCRUD.fxml","Licencias");
            } catch (Exception ev){
                Loader.getSingleton().alerta(Alert.AlertType.ERROR,"Cargar Escena","Error al cargar la escena");
            }
            
        } else if(e.getSource() == btnVehiculos){
            
            try {
                Loader.getSingleton().newScene("../views/VehiculosCRUD.fxml","Vehiculos");
            } catch (Exception ev){
                Loader.getSingleton().alerta(Alert.AlertType.ERROR,"Cargar Escena","Error al cargar la escena");
            }
            
        } else if(e.getSource() == btnAgentes){
            
            try {
                Loader.getSingleton().newScene("../views/AgentesCRUD.fxml","Agentes");
            } catch (Exception ev){
                Loader.getSingleton().alerta(Alert.AlertType.ERROR,"Cargar Escena","Error al cargar la escena");
            }
            
        } else if(e.getSource() == btnInfracciones){
            
            try {
                Loader.getSingleton().newScene("../views/InfraccionesCRUD.fxml","Infracciones");
            } catch (Exception ev){
                Loader.getSingleton().alerta(Alert.AlertType.ERROR,"Cargar Escena","Error al cargar la escena");
            }
            
        } else if(e.getSource() == btnMultas){
            
            try {
                Loader.getSingleton().newScene("../views/MultasCRUD.fxml","multas");
            } catch (Exception ev){
                Loader.getSingleton().alerta(Alert.AlertType.ERROR,"Cargar Escena","Error al cargar la escena");
            }
            
        } else if(e.getSource() == btnPagos){
            
            try {
                Loader.getSingleton().newScene("../views/PagosCRUD.fxml","Pagos");
            } catch (Exception ev){
                Loader.getSingleton().alerta(Alert.AlertType.ERROR,"Cargar Escena","Error al cargar la escena");
            }
            
        } else{
            
            
            
        }
        
    }
    
    @FXML
    private void logout(){
        try {
            Main.curren_user = 0;
            Main.current_name = "";
            Loader.getSingleton().newScene("../views/LoginMuni.fxml","Login");
        } catch (Exception e){
            Loader.getSingleton().alerta(Alert.AlertType.ERROR,"Cargar Escena","Error al cargar la escena");
        }
    }
    
    @FXML
    private void user(){
        
        try {
            Loader.getSingleton().newScene("../views/UserView.fxml","User");
        } catch (Exception ev){
            Loader.getSingleton().alerta(Alert.AlertType.ERROR,"Cargar Escena","Error al cargar la escena");
            System.out.println(ev);
        }
        
    }
    
    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
        /*
        FlowPane space = new FlowPane();
        space.setHgap(10);
        space.setVgap(15);
        
        for(int i = 0; i< 60; i++){
            space.getChildren().add(new Card("Nose","Sepa"));
            
        }
        
        cardsContainer.setContent(space);
       */
    }
    
}
