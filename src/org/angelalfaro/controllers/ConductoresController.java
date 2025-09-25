
package org.AngelAlfaro.controllers;

import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import org.AngelAlfaro.components.CardConductor;
import org.AngelAlfaro.conexion.Conexion;
import org.AngelAlfaro.system.Loader;

/**
 *
 * @author informatica
 */
public class ConductoresController implements Initializable {
    
    @FXML
    private Button btnAgregar, btnEditar, btnEliminar, btnActualizar, btnBuscar, btnLimpiar;
    
    @FXML
    private TextField  txtIdConductor, txtIdCiudadano, txtTipoSangre;
    
    @FXML
    private ScrollPane cardsContainer;
    
    private enum Mode{NULL,AGREGAR,EDITAR,ELIMINAR,BUSCAR};
    
    private Mode modeActual = Mode.NULL;
    
    private void setMode(Mode m){
        
        modeActual = m;
        
        txtIdConductor.setDisable(true);
        txtIdCiudadano.setDisable(true);
        txtTipoSangre.setDisable(true);
        
        btnAgregar.setDisable(false);
        btnEditar.setDisable(false);
        btnEliminar.setDisable(false);
        btnActualizar.setDisable(false);
        btnBuscar.setDisable(false);
        btnLimpiar.setDisable(false);
        
        switch(m){
            case NULL:
                txtIdConductor.setDisable(true);
                txtIdCiudadano.setDisable(true);
                txtTipoSangre.setDisable(true);
                
                btnAgregar.setDisable(false);
                btnEditar.setDisable(false);
                btnEliminar.setDisable(false);
                btnActualizar.setDisable(false);
                btnBuscar.setDisable(false);
                btnLimpiar.setDisable(false);
                
                break;
            case AGREGAR:
                txtIdCiudadano.setDisable(false);
                txtTipoSangre.setDisable(false);
                
                if(txtIdCiudadano.getText().isEmpty()){
                    txtIdCiudadano.requestFocus();
                } else {
                    txtTipoSangre.requestFocus();
                }
                
                btnEditar.setDisable(true);
                btnEliminar.setDisable(true);
                //btnActualizar.setDisable(true);
                btnBuscar.setDisable(true);
                //btnLimpiar.setDisable(true);
                
                break;
            case EDITAR:
                txtIdConductor.setDisable(false);
                txtTipoSangre.setDisable(false);
                
                if(txtIdConductor.getText().isEmpty()){
                    txtIdConductor.requestFocus();
                } else {
                    txtTipoSangre.requestFocus();
                }
                
                btnAgregar.setDisable(true);
                btnEliminar.setDisable(true);
                //btnActualizar.setDisable(true);
                btnBuscar.setDisable(true);
                //btnLimpiar.setDisable(true);
                
                break;
            case ELIMINAR:
                txtIdConductor.setDisable(false);
                
                txtIdConductor.requestFocus();
                
                btnAgregar.setDisable(true);
                btnEditar.setDisable(true);
                btnActualizar.setDisable(true);
                btnBuscar.setDisable(true);
                //btnLimpiar.setDisable(true);
                
                break;
            case BUSCAR:
                txtIdConductor.setDisable(false);
                
                txtIdConductor.requestFocus();
                
                btnAgregar.setDisable(true);
                btnEditar.setDisable(true);
                btnEliminar.setDisable(true);
                //btnActualizar.setDisable(true);
                //btnLimpiar.setDisable(true);
                
                break;
        }
        
    }
    
    private void add(){
        
        String idCiudadano = txtIdCiudadano.getText();
        String tipoSangre = txtTipoSangre.getText().trim();
        
        try{
            Connection cn = Conexion.getInstancia().getConnection();
            try (CallableStatement c = cn.prepareCall("{call sp_conductores_create(?,?)}")){
                
                c.setInt(1,Integer.parseInt(idCiudadano));
                c.setString(2,tipoSangre);
                
                try (ResultSet rs = c.executeQuery()){
                    
                    Loader.getSingleton().alerta(Alert.AlertType.CONFIRMATION,"Operacion Realizada","Se agrego el conductor correctamente");
                    
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
        
    }
    
    @FXML
    private void onAgregar(){
        
        if(modeActual != Mode.AGREGAR){
            setMode(modeActual.AGREGAR);
            return;
        } else {
            add();
            onLimpiar();
        }

    }
    
    private void update(){
        
        String id = txtIdConductor.getText();
        String tipoSangre = txtTipoSangre.getText();
        
        try{
            Connection cn = Conexion.getInstancia().getConnection();
            try (CallableStatement c = cn.prepareCall("{call sp_conductores_update(?,?)}")){
                
                c.setInt(1,Integer.parseInt(id));
                c.setString(2,tipoSangre);
                
                try (ResultSet rs = c.executeQuery()){
                    
                    Loader.getSingleton().alerta(Alert.AlertType.CONFIRMATION,"Operacion Realizada","Se actualizo el conductor correctamente");
                    
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
    
    private void delete(){
        
        String id = txtIdConductor.getText();
        //String zona = txtZona.getText();
        
        try{
            Connection cn = Conexion.getInstancia().getConnection();
            try (CallableStatement c = cn.prepareCall("{call sp_conductores_delete(?)}")){
                
                c.setInt(1,Integer.parseInt(id));
                //c.setString(2,zona);
                
                try (ResultSet rs = c.executeQuery()){
                    
                    Loader.getSingleton().alerta(Alert.AlertType.CONFIRMATION,"Operacion Realizada","Se elimino el conductor correctamente");
                    
                    while(rs.next()){
                        
                        
                        
                    }
                }
                
            } catch (Exception e){
                Loader.getSingleton().alerta(Alert.AlertType.ERROR,"Error","no se puede hacer la peticion");
            }
        } catch(Exception e){
            e.getStackTrace();
            Loader.getSingleton().alerta(Alert.AlertType.ERROR,"Error","No se puede conectar a la base de datos");
        }
        
    }
    
    @FXML
    private void onEliminar(){
        
        if(modeActual != Mode.ELIMINAR){
            setMode(modeActual.ELIMINAR);
            return;
        } else {
            delete();
            onLimpiar();
        }
        
    }
    
    private ArrayList<String[]> find(){
        
        ArrayList<String[]> result = new ArrayList<>();
        
        String id = txtIdConductor.getText();
        //String zona = txtZona.getText();
        
        try{
            Connection cn = Conexion.getInstancia().getConnection();
            try (CallableStatement c = cn.prepareCall("{call sp_conductores_read_by_id(?)}")){
                
                c.setInt(1,Integer.parseInt(id));
                //c.setString(2,zona);
                
                try (ResultSet rs = c.executeQuery()){
                    
                    //Loader.getSingleton().alerta(Alert.AlertType.CONFIRMATION,"Operacion Realizada","Se elimino la zona correctamente");
                    
                    while(rs.next()){
                        
                        int idConductor = rs.getInt("id_conductores");
                        int idCiudadano = rs.getInt("id_ciudadano");
                        String tipoSangre = rs.getString("tipoSangre");
                        
                        String[] array = {String.valueOf(idConductor), String.valueOf(idCiudadano), tipoSangre};
                        
                        result.add(array);
                        
                    }
                }
                
                onLimpiar();
                
            } catch (Exception e){
                Loader.getSingleton().alerta(Alert.AlertType.ERROR,"Error","no se puede hacer la peticion");
            }
        } catch(Exception e){
            e.getStackTrace();
            Loader.getSingleton().alerta(Alert.AlertType.ERROR,"Error","No se puede conectar a la base de datos");
        }
        
        return result;
        
    }
    
    @FXML
    private void onBuscar(){
        
        if(modeActual != Mode.BUSCAR){
            setMode(modeActual.BUSCAR);
            return;
        } else {
            
            reloadCards(find());
            
        }
        
    }
    
    @FXML
    private void onActualizar(){
        cardsContainer.setContent(new Pane());
        reloadCards(getZonas());
        setMode(modeActual.NULL);
    }
    
    @FXML
    private void onLimpiar(){
        cardsContainer.setContent(new Pane());
        txtIdConductor.clear();
        txtIdCiudadano.clear();
        txtTipoSangre.clear();
        setMode(modeActual.NULL);
    }
    
    @FXML
    private void home(){
        try {
            Loader.getSingleton().newScene("../views/HomeMuni.fxml","Home");
        } catch (Exception e){
            Loader.getSingleton().alerta(Alert.AlertType.ERROR,"Cargar Escena","Error al cargar la escena");
        }
    }
    
    private ArrayList<String[]> getZonas(){
        
        ArrayList<String[]> result = new ArrayList<>();
        
        try{
            Connection cn = Conexion.getInstancia().getConnection();
            try (CallableStatement c = cn.prepareCall("{call sp_conductores_read_all()}")){
                /*
                c.setString(1,usuario);
                c.setString(2,con);
                */
                
                try (ResultSet rs = c.executeQuery()){
                    while(rs.next()){
                        
                        int id = rs.getInt("id_conductores");
                        int idCiudadano = rs.getInt("id_ciudadano");
                        String tipoSangre = rs.getString("tipoSangre");
                        
                        String[] array = {String.valueOf(id), String.valueOf(idCiudadano), tipoSangre};
                        
                        result.add(array);
                    }
                }
                
            } catch (Exception e){
                //alerta(Alert.AlertType.ERROR,"Error","no se puede hacer la peticion");
            }
        } catch(Exception e){
            e.getStackTrace();
            Loader.getSingleton().alerta(Alert.AlertType.ERROR,"Error","No se puede conectar a la base de datos");
        }
        
        return result;
        
    }
    
    private void selectCard(String id,String idCiudadano, String tipoSangre){
         
         txtIdConductor.setText(id);
         txtIdCiudadano.setText(idCiudadano);
         txtTipoSangre.setText(tipoSangre);
         
     }
    
    private CardConductor createCardConductor(String id, String idCiudadano, String tipoSangre){
        
        CardConductor card = new CardConductor("No. " + id, "Id Ciudadano : " + idCiudadano, "Tipo de Sangre: " + tipoSangre);
        
        card.setOnMouseClicked(e -> {
            selectCard(id,idCiudadano,tipoSangre);
        });
        
        return card;
        
    }
    
    private void reloadCards( ArrayList<String[]> array ){
        FlowPane space = new FlowPane();
        space.setHgap(5);
        space.setVgap(15);
        
        for(String[] i: array){
            
            HBox contentBox = new HBox(5);
            //space.getChildren().add(contentBox);
            space.getChildren().add(createCardConductor(i[0],i[1],i[2]));
            
        }
        
        cardsContainer.setContent(space);
    }
    
    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
        FlowPane space = new FlowPane();
        space.setHgap(5);
        space.setVgap(15);
        
        for(String[] i: getZonas()){
            
            HBox contentBox = new HBox(5);
            //space.getChildren().add(contentBox);
            space.getChildren().add(createCardConductor(i[0],i[1],i[2]));
            
        }
        
        cardsContainer.setContent(space);
    }
    
}
