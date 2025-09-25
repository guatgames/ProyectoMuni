
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
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import org.AngelAlfaro.conexion.Conexion;
import org.AngelAlfaro.system.Loader;
import org.AngelAlfaro.components.CardVehiculo;
import org.AngelAlfaro.controllers.ZonasController;

public class VehiculosController implements Initializable {
    
    @FXML
    private Button btnAgregar, btnEditar, btnEliminar, btnActualizar, btnBuscar, btnLimpiar;
   
    @FXML
    private TextField txtId, txtPlaca, txtMarca, txtModelo, txtAnio, txtIdCiudadano;
    
    @FXML
    private ColorPicker colorPicker;
    
    @FXML
    private ScrollPane cardsContainer;
    
    private enum Mode{NULL,AGREGAR,EDITAR,ELIMINAR,BUSCAR};
    
    private Mode modeActual = Mode.NULL;
    
    private void setMode(Mode m){
        
        modeActual = m;
        
        txtId.setDisable(true);
        txtPlaca.setDisable(true);
        txtMarca.setDisable(true);
        txtModelo.setDisable(true);
        txtAnio.setDisable(true);
        txtIdCiudadano.setDisable(true);
        colorPicker.setDisable(true);
        
        btnAgregar.setDisable(false);
        btnEditar.setDisable(false);
        btnEliminar.setDisable(false);
        btnActualizar.setDisable(false);
        btnBuscar.setDisable(false);
        btnLimpiar.setDisable(false);
        
        switch(m){
            case NULL:
                txtId.setDisable(true);
                txtPlaca.setDisable(true);
                txtMarca.setDisable(true);
                txtModelo.setDisable(true);
                txtAnio.setDisable(true);
                txtIdCiudadano.setDisable(true);
                colorPicker.setDisable(true);
                
                btnAgregar.setDisable(false);
                btnEditar.setDisable(false);
                btnEliminar.setDisable(false);
                btnActualizar.setDisable(false);
                btnBuscar.setDisable(false);
                btnLimpiar.setDisable(false);
                
                break;
            case AGREGAR:
                txtPlaca.setDisable(false);
                txtMarca.setDisable(false);
                txtModelo.setDisable(false);
                txtAnio.setDisable(false);
                txtIdCiudadano.setDisable(false);
                colorPicker.setDisable(false);

                btnEditar.setDisable(true);
                btnEliminar.setDisable(true);
                //btnActualizar.setDisable(true);
                btnBuscar.setDisable(true);
                //btnLimpiar.setDisable(true);
                
                break;
            case EDITAR:
                txtId.setDisable(false);
                colorPicker.setDisable(false);
                txtIdCiudadano.setDisable(false);

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
        
        //txtId.setDisable(true);
        String placa = txtPlaca.getText();
        String marca = txtMarca.getText();
        String modelo = txtModelo.getText();
        String anio = txtAnio.getText();
        String idCiudadano = txtIdCiudadano.getText();
        String color = colorPicker.getValue().toString(); 
        
        if (!placa.isEmpty() && !marca.isEmpty() && !modelo.isEmpty() && !anio.isEmpty()
                && !idCiudadano.isEmpty() && !color.isEmpty()){
        
            try{
                Connection cn = Conexion.getInstancia().getConnection();
                try (CallableStatement c = cn.prepareCall("{call sp_vehiculos_create(?,?,?,?,?,?)}")){

                    try {
                        
                        c.setString(1, placa);
                        c.setString(2, marca);
                        c.setString(3, modelo);
                        c.setInt(4, Integer.parseInt(anio));
                        c.setString(5, color.replace("0x", "#"));
                        c.setInt(6, Integer.parseInt(idCiudadano));

                        try (ResultSet rs = c.executeQuery()){

                            Loader.getSingleton().alerta(Alert.AlertType.CONFIRMATION,"Operacion Realizada","Se agrego un vehiculo correctamente");

                            while(rs.next()){



                            }
                        }
                        
                    } catch (NumberFormatException nfe){
                        
                        Loader.getSingleton().alerta(Alert.AlertType.WARNING, "Valor invalido", "Uno o mas campos no son numericos");
                        
                    }

                } catch (Exception e){
                    Loader.getSingleton().alerta(Alert.AlertType.ERROR,"Error","no se puede hacer la peticion");
                    System.out.println(e);
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
        
        String id =txtId.getText();
        //String placa = txtPlaca.getText();
        //String marca = txtMarca.getText();
        //String modelo = txtModelo.getText();
        //String anio = txtAnio.getText();
        String idCiudadano = txtIdCiudadano.getText();
        String color = colorPicker.getValue().toString(); 
        
        if ( !id.isEmpty() && !idCiudadano.isEmpty() && !color.isEmpty()){
            
            try{
                Connection cn = Conexion.getInstancia().getConnection();
                try (CallableStatement c = cn.prepareCall("{call sp_vehiculos_update(?,?,?)}")){
                    
                    try {
                        
                        c.setString(1, id);
                        c.setString(2, color.replace("0x", "#"));
                        c.setInt(3, Integer.parseInt(idCiudadano));
                        
                        try (ResultSet rs = c.executeQuery()){

                            Loader.getSingleton().alerta(Alert.AlertType.CONFIRMATION,"Operacion Realizada","Se actualizo el vehiculo correctamente");

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
                try (CallableStatement c = cn.prepareCall("{call sp_vehiculos_delete(?)}")){

                    try {
                        
                        c.setInt(1,Integer.parseInt(id));
                        //c.setString(2,zona);

                        try (ResultSet rs = c.executeQuery()){

                            Loader.getSingleton().alerta(Alert.AlertType.CONFIRMATION,"Operacion Realizada","Se elimino la zona correctamente");

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
                try (CallableStatement c = cn.prepareCall("{call sp_vehiculos_read_by_id(?)}")){
                    
                    try {
                        
                        c.setInt(1,Integer.parseInt(id));
                        //c.setString(2,zona);

                        try (ResultSet rs = c.executeQuery()){

                            //Loader.getSingleton().alerta(Alert.AlertType.CONFIRMATION,"Operacion Realizada","Se elimino la zona correctamente");

                            while(rs.next()){

                                int idVehiculo = rs.getInt("id_vehiculo");
                                String placa = rs.getString("placa");
                                String marca = rs.getString("marca");
                                String modelo = rs.getString("modelo");
                                int anio = rs.getInt("anio");
                                String color = rs.getString("color");
                                int idCiudadano = rs.getInt("id_ciudadano");


                                String[] array = {String.valueOf(id),placa,marca,modelo,String.valueOf(anio),
                                                color,String.valueOf(idCiudadano)};

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
        txtPlaca.clear();
        txtMarca.clear();
        txtModelo.clear();
        txtAnio.clear();
        txtIdCiudadano.clear();
        colorPicker.setValue(Color.WHITE);
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
            try (CallableStatement c = cn.prepareCall("{call sp_vehiculos_read_all()}")){
                /*
                c.setString(1,usuario);
                c.setString(2,con);
                */
                
                try (ResultSet rs = c.executeQuery()){
                    while(rs.next()){
                        
                        int id = rs.getInt("id_vehiculo");
                        String placa = rs.getString("placa");
                        String marca = rs.getString("marca");
                        String modelo = rs.getString("modelo");
                        int anio = rs.getInt("anio");
                        String color = rs.getString("color");
                        int idCiudadano = rs.getInt("id_ciudadano");
                        
                        
                        String[] array = {String.valueOf(id),placa,marca,modelo,String.valueOf(anio),
                                        color,String.valueOf(idCiudadano)};
                        
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
    
     private void selectCard(String id, String placa, String marca, String modelo, String anio, String color, String idCiudadano){
         
        txtId.setText(id);
        txtPlaca.setText(placa);
        txtMarca.setText(marca);
        txtModelo.setText(modelo);
        txtAnio.setText(anio);
        txtIdCiudadano.setText(idCiudadano);
        colorPicker.setValue(Color.valueOf(color));
         
         
     }
    
    private CardVehiculo createCardVehiculo(String id, String placa, String marca, String modelo, String anio, String color, String idCiudadano){
        
        CardVehiculo card = new CardVehiculo("No. " + id, "Placa: " + placa, "Marca: " + marca,
                                    "Modelo: " + modelo, "Año: " + anio, color, "Id Ciudadano: " + idCiudadano);
        
        card.setOnMouseClicked(e -> {
            selectCard(id,placa,marca,modelo,anio,color,idCiudadano);
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
            space.getChildren().add(createCardVehiculo(i[0],i[1],i[2],i[3],i[4],i[5],i[6]));
            
        }
        
        cardsContainer.setContent(space);
    }
    
    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        
            reloadCards(getZonas());
        
        setMode(modeActual);
       
    }
    
}
