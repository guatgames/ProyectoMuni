
package org.angelalfaro.controllers;

import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import org.AngelAlfaro.components.CardPago;
import org.AngelAlfaro.conexion.Conexion;
import org.AngelAlfaro.controllers.ZonasController;
import org.AngelAlfaro.system.Loader;

public class PagosController implements Initializable{
    
    @FXML
    private TextField txtId,txtIdMulta, txtMonto, txtMetodo,txtReferencia;
    
    @FXML
    private DatePicker datePickerF, r1, r2;
    
    @FXML
    private Button btnAgregar, btnEditar, btnEliminar, btnActualizar, btnBuscar, btnLimpiar;
    
    @FXML
    private ScrollPane cardsContainer;
    
    private enum Mode{NULL,AGREGAR,BUSCARM,BUSCARR,BUSCARP};
    
    private Mode modeActual = Mode.NULL;
    
    private void setMode(Mode m){
        
        modeActual = m;
        
        txtId.setDisable(true);
        txtIdMulta.setDisable(true);
        txtMonto.setDisable(true);
        txtMetodo.setDisable(true);
        txtReferencia.setDisable(true);
        datePickerF.setDisable(true);
        r1.setDisable(true);
        r2.setDisable(true);

        btnAgregar.setDisable(false);
        btnEditar.setDisable(false);
        btnEliminar.setDisable(false);
        btnActualizar.setDisable(false);
        btnBuscar.setDisable(false);
        btnLimpiar.setDisable(false);
        
        switch(m){
            case NULL:
                txtId.setDisable(true);
                txtIdMulta.setDisable(true);
                txtMonto.setDisable(true);
                txtMetodo.setDisable(true);
                txtReferencia.setDisable(true);
                datePickerF.setDisable(true);
                r1.setDisable(true);
                r2.setDisable(true);

                btnAgregar.setDisable(false);
                btnEditar.setDisable(false);
                btnEliminar.setDisable(false);
                btnActualizar.setDisable(false);
                btnBuscar.setDisable(false);
                btnLimpiar.setDisable(false);
                
                break;
            case AGREGAR:
                txtIdMulta.setDisable(false);
                txtMonto.setDisable(false);
                txtMetodo.setDisable(false);
                txtReferencia.setDisable(false);
                datePickerF.setDisable(false);
                
                btnEditar.setDisable(true);
                btnEliminar.setDisable(true);
                //btnActualizar.setDisable(true);
                btnBuscar.setDisable(true);
                //btnLimpiar.setDisable(true);
                
                break;
            case BUSCARM:
                txtIdMulta.setDisable(false);
                txtIdMulta.requestFocus();

                btnAgregar.setDisable(true);
                btnEliminar.setDisable(true);
                //btnActualizar.setDisable(true);
                btnBuscar.setDisable(true);
                //btnLimpiar.setDisable(true);
                
                break;
            case BUSCARR:
                r1.setDisable(false);
                r2.setDisable(false);

                btnAgregar.setDisable(true);
                btnEditar.setDisable(true);
                btnActualizar.setDisable(true);
                btnBuscar.setDisable(true);
                //btnLimpiar.setDisable(true);
                
                break;
            case BUSCARP:
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
        
        String idMulta = txtIdMulta.getText().trim();
        String monto = txtMonto.getText();
        String fecha = String.valueOf(datePickerF.getValue());
        String metodo = txtMetodo.getText();
        String referencia = txtReferencia.getText();
        
        if (!idMulta.isEmpty() && !monto.isEmpty() && !fecha.isEmpty() &&
                !metodo.isEmpty() && !referencia.isEmpty()){
        
            try{
                Connection cn = Conexion.getInstancia().getConnection();
                try (CallableStatement c = cn.prepareCall("{call sp_pagos_create(?,?,?,?,?)}")){

                    c.setInt(1,Integer.parseInt(idMulta));
                    c.setString(2,monto);
                    c.setDate(3,Date.valueOf(fecha));
                    c.setString(4,metodo);
                    c.setString(5,referencia);

                    try (ResultSet rs = c.executeQuery()){

                        Loader.getSingleton().alerta(Alert.AlertType.CONFIRMATION,"Operacion Realizada","Se agrego el pago correctamente");

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
    
    
    
    private ArrayList<String[]> findP(){
        
        ArrayList<String[]> result = new ArrayList<>();
        
        String id = txtId.getText();
        //String zona = txtZona.getText();
        
        if (!id.isEmpty()){
            
            try{
                Connection cn = Conexion.getInstancia().getConnection();
                try (CallableStatement c = cn.prepareCall("{call sp_pagos_read_by_id(?)}")){
                    
                    try {
                        
                        c.setInt(1,Integer.parseInt(id));
                        //c.setString(2,zona);

                        try (ResultSet rs = c.executeQuery()){

                            //Loader.getSingleton().alerta(Alert.AlertType.CONFIRMATION,"Operacion Realizada","Se elimino la zona correctamente");

                            while(rs.next()){

                                int idPago = rs.getInt("id_pago");
                                int idMulta = rs.getInt("id_multa");
                                Date fecha = rs.getDate("fechaPago");
                                double monto = rs.getDouble("montoPagado");
                                double metodo = rs.getDouble("metodoPago");
                                String referencia = rs.getString("referencia");

                                String[] array = {String.valueOf(idPago),String.valueOf(idMulta),String.valueOf(fecha),Double.toString(monto),
                                                    Double.toString(metodo),referencia};

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
    private void onBuscarP(){
        
        if(modeActual != Mode.BUSCARP){
            setMode(modeActual.BUSCARP);
            return;
        } else {
            
            reloadCards(findP());
            
        }
        
    }
    
    private ArrayList<String[]> findM(){
        
        ArrayList<String[]> result = new ArrayList<>();
        
        String idMulta = txtIdMulta.getText();
        //String zona = txtZona.getText();
        
        if (!idMulta.isEmpty()){
            
            try{
                Connection cn = Conexion.getInstancia().getConnection();
                try (CallableStatement c = cn.prepareCall("{call sp_pagos_read_by_multa(?)}")){
                    
                    try {
                        
                        c.setInt(1,Integer.parseInt(idMulta));
                        //c.setString(2,zona);

                        try (ResultSet rs = c.executeQuery()){

                            //Loader.getSingleton().alerta(Alert.AlertType.CONFIRMATION,"Operacion Realizada","Se elimino la zona correctamente");

                            while(rs.next()){

                                int idPago = rs.getInt("id_pago");
                                int p_idMulta = rs.getInt("id_multa");
                                Date fecha = rs.getDate("fechaPago");
                                double monto = rs.getDouble("montoPagado");
                                double metodo = rs.getDouble("metodoPago");
                                String referencia = rs.getString("referencia");

                                String[] array = {String.valueOf(idPago),String.valueOf(p_idMulta),String.valueOf(fecha),Double.toString(monto),
                                                    Double.toString(metodo),referencia};

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
    private void onBuscarM(){
        
        if(modeActual != Mode.BUSCARM){
            setMode(modeActual.BUSCARM);
            return;
        } else {
            
            reloadCards(findM());
            
        }
        
    }
    
    private ArrayList<String[]> findR(){
        
        ArrayList<String[]> result = new ArrayList<>();
        
        LocalDate rango1 = r1.getValue();
        LocalDate rango2 = r2.getValue();
        
        if (!rango1.toString().isEmpty() && !rango2.toString().isEmpty()){
            
            try{
                Connection cn = Conexion.getInstancia().getConnection();
                try (CallableStatement c = cn.prepareCall("{call sp_pagos_read_by_rango(?,?)}")){
                    
                    try {
                        
                        c.setDate(1,Date.valueOf(rango1));
                        c.setDate(2,Date.valueOf(rango2));
                        //c.setString(2,zona);

                        try (ResultSet rs = c.executeQuery()){

                            //Loader.getSingleton().alerta(Alert.AlertType.CONFIRMATION,"Operacion Realizada","Se elimino la zona correctamente");

                            while(rs.next()){

                                int idPago = rs.getInt("id_pago");
                                int p_idMulta = rs.getInt("id_multa");
                                Date fecha = rs.getDate("fechaPago");
                                double monto = rs.getDouble("montoPagado");
                                double metodo = rs.getDouble("metodoPago");
                                String referencia = rs.getString("referencia");

                                String[] array = {String.valueOf(idPago),String.valueOf(p_idMulta),String.valueOf(fecha),Double.toString(monto),
                                                    Double.toString(metodo),referencia};

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
            
            Loader.getSingleton().alerta(Alert.AlertType.WARNING, "Campo vacio", "Uno de los campos esta vacio");
            
            return null;
            
        }
        
    }
    
    @FXML
    private void onBuscarR(){
        
        if(modeActual != Mode.BUSCARR){
            setMode(modeActual.BUSCARR);
            return;
        } else {
            
            reloadCards(findR());
            
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
        txtIdMulta.clear();
        txtMonto.clear();
        txtMetodo.clear();
        txtReferencia.clear();
        datePickerF.setValue(null);
        r1.setValue(null);
        r2.setValue(null);
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
            try (CallableStatement c = cn.prepareCall("{call sp_pagos_read_all()}")){
                /*
                c.setString(1,usuario);
                c.setString(2,con);
                */
                
                try (ResultSet rs = c.executeQuery()){
                    while(rs.next()){
                        
                        int id = rs.getInt("id_pago");
                        int idMulta = rs.getInt("id_multa");
                        Date fecha = rs.getDate("fechaPago");
                        double monto = rs.getDouble("montoPagado");
                        double metodo = rs.getDouble("metodoPago");
                        String referencia = rs.getString("referencia");
                        
                        String[] array = {String.valueOf(id),String.valueOf(idMulta),String.valueOf(fecha),Double.toString(monto),
                                            Double.toString(metodo),referencia};
                        
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
    
     private void selectCard(String id, String idMulta, String fecha, String monto, String metodo, String referencia){
         
        txtId.setText(id);
        txtIdMulta.setText(idMulta);
        txtMonto.setText(monto);
        txtMetodo.setText(metodo);
        txtReferencia.setText(referencia);
        datePickerF.setValue(LocalDate.parse(fecha));
         
     }
    
    private CardPago createCardZona(String id, String idMulta, String fecha, String monto, String metodo, String referencia){
        
        CardPago card = new CardPago("No. " + id, "Id Multa: " + idMulta, "Fecha: " + fecha, "Monto: " + monto,
                                    "Metodo de Pago: " + metodo, "Referencia: " + referencia);
        
        card.setOnMouseClicked(e -> {
            selectCard(id,idMulta,fecha,monto,metodo,referencia);
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
            space.getChildren().add(createCardZona(i[0],i[1],i[2],i[3],i[4],i[5]));
            
        }
        
        cardsContainer.setContent(space);
    }
    
    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        
        reloadCards(getZonas());
        
        setMode(modeActual);
       
    }
    
}
