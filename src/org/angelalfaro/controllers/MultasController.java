
package org.angelalfaro.controllers;

import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import org.AngelAlfaro.components.CardMulta;
import org.AngelAlfaro.conexion.Conexion;
import org.AngelAlfaro.controllers.ZonasController;
import org.AngelAlfaro.system.Loader;


public class MultasController implements Initializable {
    
    @FXML
    private TextField txtId, txtIdInfraccion, txtIdConductor, txtIdVehiculo, txtIdAgente, txtLugar, txtMonto, txtObservacion;
    
    @FXML
    private DatePicker datePickerF;
    
    @FXML
    private ComboBox<String> enumEstado;
    
    private ObservableList<String> comboBoxData = FXCollections.observableArrayList();
    
    @FXML
    private Button btnAgregar, btnEditar, btnEliminar, btnActualizar, btnBuscar, btnLimpiar;
    
    @FXML
    private ScrollPane cardsContainer;
    
    private enum Mode{NULL,AGREGAR,EDITAR,ELIMINAR,BUSCAR};
    
    private Mode modeActual = Mode.NULL;
    
    private void setMode(Mode m){
        
        modeActual = m;
        
        txtId.setDisable(true);
        txtIdInfraccion.setDisable(true);
        txtIdConductor.setDisable(true);
        txtIdVehiculo.setDisable(true);
        txtIdAgente.setDisable(true);
        datePickerF.setDisable(true);
        txtLugar.setDisable(true);
        txtMonto.setDisable(true);
        enumEstado.setDisable(true);
        txtObservacion.setDisable(true);
        
        btnAgregar.setDisable(false);
        btnEditar.setDisable(false);
        btnEliminar.setDisable(false);
        btnActualizar.setDisable(false);
        btnBuscar.setDisable(false);
        btnLimpiar.setDisable(false);
        
        switch(m){
            case NULL:
                txtId.setDisable(true);
                txtIdInfraccion.setDisable(true);
                txtIdConductor.setDisable(true);
                txtIdVehiculo.setDisable(true);
                txtIdAgente.setDisable(true);
                datePickerF.setDisable(true);
                txtLugar.setDisable(true);
                txtMonto.setDisable(true);
                enumEstado.setDisable(true);
                txtObservacion.setDisable(true);

                btnAgregar.setDisable(false);
                btnEditar.setDisable(false);
                btnEliminar.setDisable(false);
                btnActualizar.setDisable(false);
                btnBuscar.setDisable(false);
                btnLimpiar.setDisable(false);
                
                break;
            case AGREGAR:
                txtIdInfraccion.setDisable(false);
                txtIdConductor.setDisable(false);
                txtIdVehiculo.setDisable(false);
                txtIdAgente.setDisable(false);
                datePickerF.setDisable(false);
                txtLugar.setDisable(false);
                txtMonto.setDisable(false);
                enumEstado.setDisable(false);
                txtObservacion.setDisable(false);

                btnEditar.setDisable(true);
                btnEliminar.setDisable(true);
                //btnActualizar.setDisable(true);
                btnBuscar.setDisable(true);
                //btnLimpiar.setDisable(true);
                
                break;
            case EDITAR:
                txtId.setDisable(false);
                datePickerF.setDisable(false);
                txtLugar.setDisable(false);
                txtMonto.setDisable(false);
                enumEstado.setDisable(false);
                txtObservacion.setDisable(false);

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
        
        String idI = txtIdInfraccion.getText();
        String idC = txtIdConductor.getText();
        String idV = txtIdVehiculo.getText();
        String idA = txtIdAgente.getText();
        LocalDate fecha = datePickerF.getValue();
        String lugar = txtLugar.getText();
        String monto = txtMonto.getText();
        String estado = enumEstado.getValue();
        String observacion = txtObservacion.getText();
        
        if (!idI.isEmpty() && !idC.isEmpty() && !idV.isEmpty() && !idA.isEmpty() && !fecha.toString().isEmpty() &&
                !lugar.isEmpty() && !monto.isEmpty() && !estado.isEmpty() && !observacion.isEmpty()){
        
            try{
                Connection cn = Conexion.getInstancia().getConnection();
                try (CallableStatement c = cn.prepareCall("{call sp_multas_create(?,?,?,?,?,?,?,?,?)}")){

                    try {
                        
                        c.setInt(1,Integer.parseInt(idI));
                        c.setInt(2,Integer.parseInt(idC));
                        c.setInt(3,Integer.parseInt(idV));
                        c.setInt(4,Integer.parseInt(idA));
                        c.setDate(5,Date.valueOf(fecha));
                        c.setString(6,lugar);
                        c.setString(7,monto);
                        c.setString(8,estado);
                        c.setString(9,observacion);

                        try (ResultSet rs = c.executeQuery()){

                            Loader.getSingleton().alerta(Alert.AlertType.CONFIRMATION,"Operacion Realizada","Se agrego la multa correctamente");

                            while(rs.next()){



                            }
                        }
                        
                    } catch (NumberFormatException e){
                        
                        Loader.getSingleton().alerta(Alert.AlertType.WARNING, "Valor invalido", "Uno o mas campos no son numericos");
                        
                    }

                } catch (Exception e){
                   Loader.getSingleton().alerta(Alert.AlertType.ERROR,"Error","no se puede hacer la peticion");
                }
            } catch(Exception e){
                e.getStackTrace();
                Loader.getSingleton().alerta(Alert.AlertType.ERROR,"Error","No se puede conectar a la base de datos");
            }
            
        } else {
            
            Loader.getSingleton().alerta(Alert.AlertType.WARNING, "Campo vacio", "Uno o mas campos estan vacios");
            
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
        LocalDate fecha = datePickerF.getValue();
        String lugar = txtLugar.getText();
        String monto = txtMonto.getText();
        String estado = enumEstado.getValue();
        String observacion = txtObservacion.getText();
        
        if (!id.isEmpty() && !fecha.toString().isEmpty() && !lugar.isEmpty() && !monto.isEmpty()
                && !estado.isEmpty() && !observacion.isEmpty()){
            
            try{
                Connection cn = Conexion.getInstancia().getConnection();
                try (CallableStatement c = cn.prepareCall("{call sp_multas_update(?,?,?,?,?,?)}")){
                    
                    try {
                        
                        c.setInt(1,Integer.parseInt(id));
                        c.setDate(2,Date.valueOf(fecha));
                        c.setString(3,lugar);
                        c.setString(4,monto);
                        c.setString(5,estado);
                        c.setString(6,observacion);
                        
                        try (ResultSet rs = c.executeQuery()){

                            Loader.getSingleton().alerta(Alert.AlertType.CONFIRMATION,"Operacion Realizada","Se actualizo la multa correctamente");

                            while(rs.next()){



                            }
                        }
                        
                    } catch (NumberFormatException nfe){
                        
                        Loader.getSingleton().alerta(Alert.AlertType.WARNING, "Valor invalido", "Uno o mas campos no son numericos");
                        
                    }

                } catch (Exception e){
                    //Loader.getSingleton().alerta(Alert.AlertType.ERROR,"Error","no se puede hacer la peticion");
                }
            } catch(Exception e){
                e.getStackTrace();
                Loader.getSingleton().alerta(Alert.AlertType.ERROR,"Error","No se puede conectar a la base de datos");
            }
            
        } else {
            
            Loader.getSingleton().alerta(Alert.AlertType.WARNING, "Campo vacio", "Uno o mas campos estan vacios");
            
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
                try (CallableStatement c = cn.prepareCall("{call sp_multas_delete(?)}")){

                    try {
                        
                        c.setInt(1,Integer.parseInt(id));
                        //c.setString(2,zona);

                        try (ResultSet rs = c.executeQuery()){

                            Loader.getSingleton().alerta(Alert.AlertType.CONFIRMATION,"Operacion Realizada","Se elimino la multa correctamente");

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
                try (CallableStatement c = cn.prepareCall("{call sp_multas_read_by_id(?)}")){
                    
                    try {
                        
                        c.setInt(1,Integer.parseInt(id));
                        //c.setString(2,zona);

                        try (ResultSet rs = c.executeQuery()){

                            //Loader.getSingleton().alerta(Alert.AlertType.CONFIRMATION,"Operacion Realizada","Se elimino la zona correctamente");

                            while(rs.next()){

                                int idMulta = rs.getInt("id_multa");
                                int idI = rs.getInt("id_infracciones");
                                int idC = rs.getInt("id_conductores");
                                int idV = rs.getInt("id_vehiculo");
                                int idA = rs.getInt("id_agente");
                                Date fecha = rs.getDate("fecha");
                                String lugar = rs.getString("lugar");
                                double monto = rs.getDouble("monto");
                                String estado = rs.getString("estado");
                                String observaciones = rs.getString("observaciones");

                                String[] array = {String.valueOf(idMulta),String.valueOf(idI),String.valueOf(idC),String.valueOf(idV),
                                                    String.valueOf(idA),String.valueOf(fecha),lugar,Double.toString(monto),
                                                    estado,observaciones};

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
        txtIdInfraccion.clear();
        txtIdConductor.clear();
        txtIdVehiculo.clear();
        txtIdAgente.clear();
        datePickerF.setValue(null);
        txtLugar.clear();
        txtMonto.clear();
        enumEstado.setValue("");
        txtObservacion.clear();
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
            try (CallableStatement c = cn.prepareCall("{call sp_multas_read_all()}")){
                /*
                c.setString(1,usuario);
                c.setString(2,con);
                */
                
                try (ResultSet rs = c.executeQuery()){
                    while(rs.next()){
                        
                        int id = rs.getInt("id_multa");
                        int idI = rs.getInt("id_infracciones");
                        int idC = rs.getInt("id_conductores");
                        int idV = rs.getInt("id_vehiculo");
                        int idA = rs.getInt("id_agente");
                        Date fecha = rs.getDate("fecha");
                        String lugar = rs.getString("lugar");
                        double monto = rs.getDouble("monto");
                        String estado = rs.getString("estado");
                        String observaciones = rs.getString("observaciones");
                        
                        String[] array = {String.valueOf(id),String.valueOf(idI),String.valueOf(idC),String.valueOf(idV),
                                            String.valueOf(idA),String.valueOf(fecha),lugar,Double.toString(monto),
                                            estado,observaciones};
                        
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
    
     private void selectCard(String id, String idI, String idC, String idV, String idA,
            String fecha, String lugar, String monto, String estado, String observacion){
         
         txtId.setText(id);
         txtIdInfraccion.setText(idI);
         txtIdConductor.setText(idC);
         txtIdVehiculo.setText(idV);
         txtIdAgente.setText(idA);
         datePickerF.setValue(LocalDate.parse(fecha));
         txtLugar.setText(lugar);
         txtMonto.setText(monto);
         enumEstado.setValue(estado);
         txtObservacion.setText(observacion);
         
     }
    
    private CardMulta createCardMulta(String id, String idI, String idC, String idV, String idA,
            String fecha, String lugar, String monto, String estado, String observacion){
        
        CardMulta card = new CardMulta("No. " + id, "Id Infraccion: " + idI, "Id Conductor: " + idC,
                                    "Id Vehiculo: " + idV, "Id Agente: " + idA, "Fecha: " + fecha,
                                    "Lugar: " + lugar, "Monto: " + monto, "Estado: " + estado,
                                    "Observaciones: " + observacion);
        
        card.setOnMouseClicked(e -> {
            selectCard(id,idI,idC,idV,idA,fecha,lugar,monto,estado,observacion);
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
            space.getChildren().add(createCardMulta(i[0],i[1],i[2],i[3],i[4],i[5],i[6],i[7],i[8],i[9]));
            
        }
        
        cardsContainer.setContent(space);
    }
    
    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        
        comboBoxData.addAll("pendiente", "pagado", "anulado");
        enumEstado.setItems(comboBoxData);
        
        reloadCards(getZonas());
        
        setMode(modeActual);
       
    }
    
}
