    
package org.AngelAlfaro.system;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
//import org.AngelAlfaro.singleton.Users;


public class Loader {
    
    private static Loader singleton = null;
    private Stage currentStage = null;
    
    public static  Loader getSingleton(){
        
        if(singleton == null){
            singleton = new Loader();
        }
        
        return singleton;
        
    }
    
    public void newScene(String path) throws Exception {
    
        FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
        
        Scene sc = new Scene(loader.load());
        
        Stage stage = getCurrentStage();
        stage.centerOnScreen();
        
        stage.setTitle("Escena");
        stage.setScene(sc);
        stage.show();
        
    }
    
    
    public void newScene(String path, String title) throws Exception {
    
        FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
        
        Scene sc = new Scene(loader.load());
        
        Stage stage = getCurrentStage();
        stage.setTitle(title);
        stage.setScene(sc);
        stage.show();
        
    }
    
    public void newScene(String path, String title, String img) throws Exception {
    
        FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
        
        Scene sc = new Scene(loader.load());
        
        Stage stage = getCurrentStage();
        stage.centerOnScreen();
        
        stage.setTitle(title);
        stage.getIcons().add(new Image(Loader.class.getResourceAsStream(img)));
        stage.setScene(sc);
        stage.show();
        
    }

    public Stage getCurrentStage() {
        return currentStage;
    }

    public void setCurrentStage(Stage currentStage) {
        this.currentStage = currentStage;
    }
    
    public void alerta(Alert.AlertType tipo, String titulo, String msg){
        Alert a = new Alert(tipo);
        a.setTitle(titulo);
        a.setHeaderText(null);
        a.setContentText(msg);
        a.show();
    }
    /*
    public static void cargador() throws IOException, ClassNotFoundException{
    
        FileInputStream entrada = null;
        FileOutputStream creador = null;
        
        try{
        
            entrada = new FileInputStream("datos.txt");
            ObjectInputStream tbEntrada = new ObjectInputStream(entrada);

            Users.setSingleton((Users) tbEntrada.readObject());
        
        } catch (FileNotFoundException ex){
        
            try {
                
                creador = new FileOutputStream("datos.txt");
                Users.getSingleton();
                
                        
            } catch (FileNotFoundException ex1) {
                
            }
        
        } catch (IOException ex){
        
        
        } 
    
    }
    
    public static void salvador(){
    
        FileOutputStream fichero = null;
        FileOutputStream creador = null;

        try {
            fichero = new FileOutputStream("datos.txt");
            ObjectOutputStream tuberia = new ObjectOutputStream(fichero);

            tuberia.writeObject(Users.getSingleton());


        } catch (FileNotFoundException ex){

            try {
                
                creador = new FileOutputStream("datos.txt");
                Users.getSingleton();
                
            } catch (FileNotFoundException e){
                
            }
       

        } catch (IOException ex){

        } finally {

            try {

                fichero.close();

            } catch (IOException ex) {

            }  
        }
    
    }
    */
}
