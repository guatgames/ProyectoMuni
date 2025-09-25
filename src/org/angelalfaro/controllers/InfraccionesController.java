
package org.angelalfaro.controllers;

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
import org.AngelAlfaro.conexion.Conexion;
import org.AngelAlfaro.system.Loader;
import org.AngelAlfaro.components.CardInfraccion;


public class InfraccionesController implements Initializable {
    
    @FXML
    private TextField txtId, txtCodigo, txtMonto, txtDescripcion;
    
    @FXML
    private Button btnAgregar, btnEditar, btnEliminar, btnActualizar, btnBuscar, btnLimpiar;
    
    @FXML
    private ScrollPane cardsContainer;
    
    private enum Mode{NULL,AGREGAR,EDITAR,ELIMINAR,BUSCAR};
    
    private Mode modeActual = Mode.NULL;
    
    private void setMode(Mode m){
        
        modeActual = m;
        
        txtId.setDisable(true);
        txtCodigo.setDisable(true);
        txtDescripcion.setDisable(true);
        txtMonto.setDisable(true);
        
        btnAgregar.setDisable(false);
        btnEditar.setDisable(false);
        btnEliminar.setDisable(false);
        btnActualizar.setDisable(false);
        btnBuscar.setDisable(false);
        btnLimpiar.setDisable(false);
        
        switch(m){
            case NULL:
                txtId.setDisable(true);
                txtCodigo.setDisable(true);
                txtDescripcion.setDisable(true);
                txtMonto.setDisable(true);

                btnAgregar.setDisable(false);
                btnEditar.setDisable(false);
                btnEliminar.setDisable(false);
                btnActualizar.setDisable(false);
                btnBuscar.setDisable(false);
                btnLimpiar.setDisable(false);
                
                break;
            case AGREGAR:
                txtCodigo.setDisable(false);
                txtDescripcion.setDisable(false);
                txtMonto.setDisable(false);

                btnEditar.setDisable(true);
                btnEliminar.setDisable(true);
                //btnActualizar.setDisable(true);
                btnBuscar.setDisable(true);
                //btnLimpiar.setDisable(true);
                
                break;
            case EDITAR:
                txtId.setDisable(false);
                txtCodigo.setDisable(false);
                txtDescripcion.setDisable(false);
                txtMonto.setDisable(false);

                btnAgregar.setDisable(true);
                btnEliminar.setDisable(true);
                //btnActualizar.setDisable(true);
                btnBuscar.setDisable(true);
                //btnLimpiar.setDisable(true);
                
                break;
            case ELIMINAR:
                txtId.setDisable(false);
                txtId.requestFocus();

                btnAgregar.setDisable(true);
                btnEditar.setDisable(true);
                btnActualizar.setDisable(true);
                btnBuscar.setDisable(true);
                //btnLimpiar.setDisable(true);
                
                break;
            case BUSCAR:
                txtId.setDisable(false);
                txtId.requestFocus();

                btnAgregar.setDisable(true);
                btnEditar.setDisable(true);
                btnEliminar.setDisable(true);
                //btnActualizar.setDisable(true);
                //btnLimpiar.setDisable(true);
                
                break;
        }
        
    }
    
    private void add(){
        
        //String id = txtId.getText().trim();
        String codigo = txtCodigo.getText();
        String desc = txtDescripcion.getText();
        String monto = txtMonto.getText();
        
        if (!codigo.isEmpty() && !desc.isEmpty() && !monto.isEmpty()){
        
            try{
                Connection cn = Conexion.getInstancia().getConnection();
                try (CallableStatement c = cn.prepareCall("{call sp_infracciones_create(?,?,?)}")){

                    try {
                        
                        c.setString(1,codigo);
                        c.setString(2,desc);
                        c.setDouble(3, Double.parseDouble(monto));

                        try (ResultSet rs = c.executeQuery()){

                            Loader.getSingleton().alerta(Alert.AlertType.CONFIRMATION,"Operacion Realizada","Se agrego la infraccion correctamente");

                            while(rs.next()){



                            }
                        }
                        
                    } catch (NumberFormatException e){
                        
                        Loader.getSingleton().alerta(Alert.AlertType.WARNING, "Valor invalido", "Uno o mas campos no son numerico");
                        
                    }

                } catch (Exception e){
                    //Loader.getSingleton().alerta(Alert.AlertType.ERROR,"Error","no se puede hacer la peticion");
                }
            } catch(Exception e){
                e.getStackTrace();
                Loader.getSingleton().alerta(Alert.AlertType.ERROR,"Error","No se puede conectar a la base de datos");
            }
            
        } else {
            
            Loader.getSingleton().alerta(Alert.AlertType.WARNING, "Campo vacio", "El campo zona esta vacio");
            
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
        
        String id = txtId.getText();
        String codigo = txtCodigo.getText();
        String desc = txtDescripcion.getText();
        String monto = txtMonto.getText();
        
        if (!id.isEmpty() && !codigo.isEmpty() && !desc.isEmpty() && !monto.isEmpty()){
            
            try{
                Connection cn = Conexion.getInstancia().getConnection();
                try (CallableStatement c = cn.prepareCall("{call sp_infracciones_update(?,?,?,?)}")){
                    
                    try {
                        
                        c.setInt(1,Integer.parseInt(id));
                        c.setString(2,codigo);
                        c.setString(3,desc);
                        c.setDouble(4, Double.parseDouble(monto));
                        
                        try (ResultSet rs = c.executeQuery()){

                            Loader.getSingleton().alerta(Alert.AlertType.CONFIRMATION,"Operacion Realizada","Se actualizo la infraccion correctamente");

                            while(rs.next()){



                            }
                        }
                        
                    } catch (NumberFormatException nfe){
                        
                        Loader.getSingleton().alerta(Alert.AlertType.WARNING, "Valor invalido", "Uno o mas valores no son numericos");
                        
                    }

                } catch (Exception e){
                    //Loader.getSingleton().alerta(Alert.AlertType.ERROR,"Error","no se puede hacer la peticion");
                }
            } catch(Exception e){
                e.getStackTrace();
                Loader.getSingleton().alerta(Alert.AlertType.ERROR,"Error","No se puede conectar a la base de datos");
            }
            
        } else {
            
            Loader.getSingleton().alerta(Alert.AlertType.WARNING, "Campo vacio", "El campo id o el campo zona esta vacio");
            
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
        
        String id = txtId.getText().trim();
        //String zona = txtZona.getText();
        
        if (!id.isEmpty()){
            
            try{
                Connection cn = Conexion.getInstancia().getConnection();
                try (CallableStatement c = cn.prepareCall("{call sp_infracciones_delete(?)}")){

                    try {
                        
                        c.setInt(1,Integer.parseInt(id));
                        //c.setString(2,zona);

                        try (ResultSet rs = c.executeQuery()){

                            Loader.getSingleton().alerta(Alert.AlertType.CONFIRMATION,"Operacion Realizada","Se elimino la infraccion correctamente");

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
            
            Loader.getSingleton().alerta(Alert.AlertType.WARNING, "Campo vacio", "El campo id esta vacio");
            
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
        
        String id = txtId.getText();
        //String zona = txtZona.getText();
        
        if (!id.isEmpty()){
            
            try{
                Connection cn = Conexion.getInstancia().getConnection();
                try (CallableStatement c = cn.prepareCall("{call sp_infracciones_read_by_id(?)}")){
                    
                    try {
                        
                        c.setInt(1,Integer.parseInt(id));
                        //c.setString(2,zona);

                        try (ResultSet rs = c.executeQuery()){

                            //Loader.getSingleton().alerta(Alert.AlertType.CONFIRMATION,"Operacion Realizada","Se elimino la zona correctamente");

                            while(rs.next()){

                                int idInfraccion = rs.getInt("id_infracciones");
                                String codigo = rs.getString("codigo");
                                String desc = rs.getString("descripcion");
                                double monto = rs.getDouble("montoBase");

                                String[] array = {String.valueOf(idInfraccion),codigo, desc, Double.toString(monto)};

                                result.add(array);

                            }
                        }

                        onLimpiar();
                        
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

            return result;
            
        } else {
            
            Loader.getSingleton().alerta(Alert.AlertType.WARNING, "Campo vacio", "El campo id esta vacio");
            
            return null;
            
        }
        
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
        txtId.clear();
        txtCodigo.clear();
        txtDescripcion.clear();
        txtMonto.clear();
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
            try (CallableStatement c = cn.prepareCall("{call sp_infracciones_read_all()}")){
                /*
                c.setString(1,usuario);
                c.setString(2,con);
                */
                
                try (ResultSet rs = c.executeQuery()){
                    while(rs.next()){
                        
                        int id = rs.getInt("id_infracciones");
                        String codigo = rs.getString("codigo");
                        String desc = rs.getString("descripcion");
                        double monto = rs.getDouble("montoBase");
                        
                        String[] array = {String.valueOf(id),codigo, desc, Double.toString(monto)};
                        
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
    
     private void selectCard(String id, String codigo, String desc, String monto){
         
        txtId.setText(id);
        txtCodigo.setText(codigo);
        txtDescripcion.setText(desc);
        txtMonto.setText(monto);
        
     }
    
    private CardInfraccion createCardInfraccion(String id, String codigo, String desc, String monto){
        
        CardInfraccion card = new CardInfraccion("No. " + id, "Codigo: " + codigo, "Descripcion: " + desc,
                "Monto: " +  monto);
        
        card.setOnMouseClicked(e -> {
            selectCard(id,codigo,desc, monto);
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
            space.getChildren().add(createCardInfraccion(i[0],i[1],i[2],i[3]));
            
        }
        
        cardsContainer.setContent(space);
    }
    
    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        
        reloadCards(getZonas());
        
        setMode(modeActual);
       
    }
    
}
