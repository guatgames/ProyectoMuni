
package org.AngelAlfaro.system;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.AngelAlfaro.conexion.Conexion;

/**
 *
 * @author informatica
 */
public class Main extends Application {
    
    private static Stage primaryStage;
    public static int curren_user;
    public static String current_name; 

    @Override
    public void start(Stage stage) throws IOException, Exception{
        
        try {
            Loader.getSingleton().setCurrentStage(stage);
            Loader.getSingleton().newScene("../views/LoginMuni.fxml","Login","../img/muni_ico.png");
        } catch (Exception e){
            System.out.println(e);
        }
            
       
        
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
