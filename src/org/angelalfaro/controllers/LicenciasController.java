
package org.angelalfaro.controllers;

import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import org.AngelAlfaro.conexion.Conexion;
import org.AngelAlfaro.system.Loader;
import org.AngelAlfaro.components.CardLicencia;


public class LicenciasController implements Initializable {
    
    @FXML
    private Button btnAgregar, btnEditar, btnEliminar, btnActualizar, btnBuscarLic, btnBuscarCondu, btnEstado, btnRenovar, btnLimpiar;
    
    @FXML
    private ComboBox<String> enumCategoria, enumEstado;
    
    @FXML
    private TextField txtId, txtIdConductor;
    
    @FXML
    private DatePicker fEmision, fVencimiento;

    private ObservableList<String> comboBoxData = FXCollections.observableArrayList();
    private ObservableList<String> comboBoxData2 = FXCollections.observableArrayList();
    
    @FXML
    private ScrollPane cardsContainer;
    
    private enum Mode{NULL,AGREGAR,EDITAR,ELIMINAR,BUSCARLIC,BUSCARCONDU,RENOVAR,ESTADO};
    
    private Mode modeActual = Mode.NULL;
    
    private void setMode(Mode m){
        
        modeActual = m;
 
        txtId.setDisable(true);
        txtIdConductor.setDisable(true);
        enumCategoria.setDisable(true);
        fEmision.setDisable(true);
        fVencimiento.setDisable(true);
        enumEstado.setDisable(true);
        
        btnAgregar.setDisable(false);
        btnEditar.setDisable(false);
        btnEliminar.setDisable(false);
        btnActualizar.setDisable(false);
        btnBuscarLic.setDisable(false);
        btnBuscarCondu.setDisable(false);
        btnEstado.setDisable(false);
        btnRenovar.setDisable(false);
        btnLimpiar.setDisable(false);
        
        switch(m){
            case NULL:
                txtId.setDisable(true);
                txtIdConductor.setDisable(true);
                enumCategoria.setDisable(true);
                fEmision.setDisable(true);
                fVencimiento.setDisable(true);
                enumEstado.setDisable(true);
               
                btnAgregar.setDisable(false);
                btnEditar.setDisable(false);
                btnEliminar.setDisable(false);
                btnActualizar.setDisable(false);
                btnBuscarLic.setDisable(false);
                btnBuscarCondu.setDisable(false);
                btnEstado.setDisable(false);
                btnRenovar.setDisable(false);
                btnLimpiar.setDisable(false);
                
                break;
            case AGREGAR:
                txtIdConductor.setDisable(false);
                enumCategoria.setDisable(false);
                fEmision.setDisable(false);
                fVencimiento.setDisable(false);
                enumEstado.setDisable(false);

                btnEditar.setDisable(true);
                btnEliminar.setDisable(true);
                //btnActualizar.setDisable(true);
                btnBuscarLic.setDisable(true);
                btnBuscarCondu.setDisable(true);
                btnEstado.setDisable(true);
                btnRenovar.setDisable(true);
                //btnLimpiar.setDisable(true);
                
                break;
            case EDITAR:
                txtId.setDisable(false);
                txtIdConductor.setDisable(false);
                enumCategoria.setDisable(false);
                fEmision.setDisable(false);
                fVencimiento.setDisable(false);
                enumEstado.setDisable(false);

                btnAgregar.setDisable(true);
                btnEliminar.setDisable(true);
                //btnActualizar.setDisable(true);
                btnBuscarLic.setDisable(true);
                btnBuscarCondu.setDisable(true);
                btnEstado.setDisable(true);
                btnRenovar.setDisable(true);
                //btnLimpiar.setDisable(true);
                
                break;
            case ELIMINAR:
                txtId.setDisable(false);

                btnAgregar.setDisable(true);
                btnEditar.setDisable(true);
                //btnActualizar.setDisable(true);
                btnBuscarLic.setDisable(true);
                btnBuscarCondu.setDisable(true);
                btnEstado.setDisable(true);
                btnRenovar.setDisable(true);
                //btnLimpiar.setDisable(true);
                
                break;
            case BUSCARLIC:
                txtId.setDisable(false);

                btnAgregar.setDisable(true);
                btnEditar.setDisable(true);
                btnEliminar.setDisable(true);
                //btnActualizar.setDisable(true);
                btnBuscarCondu.setDisable(true);
                btnEstado.setDisable(true);
                btnRenovar.setDisable(true);
                //btnLimpiar.setDisable(true);
                
                break;
            case BUSCARCONDU:
                txtIdConductor.setDisable(false);
                
                btnAgregar.setDisable(true);
                btnEditar.setDisable(true);
                btnEliminar.setDisable(true);
                btnBuscarLic.setDisable(true);
                btnEstado.setDisable(true);
                btnRenovar.setDisable(true);
                
                break;
            case ESTADO:
                txtId.setDisable(false);
                enumEstado.setDisable(false);
                
                btnAgregar.setDisable(true);
                btnEditar.setDisable(true);
                btnEliminar.setDisable(true);
                btnBuscarLic.setDisable(true);
                btnBuscarCondu.setDisable(true);
                //btnEstado.setDisable(true);
                btnRenovar.setDisable(true);
                break;
            case RENOVAR:
                txtId.setDisable(false);
                fEmision.setDisable(false);
                fVencimiento.setDisable(false);
                
                btnAgregar.setDisable(true);
                btnEditar.setDisable(true);
                btnEliminar.setDisable(true);
                btnBuscarLic.setDisable(true);
                btnBuscarCondu.setDisable(true);
                btnEstado.setDisable(true);
                //btnRenovar.setDisable(true);
        }
        
    }
    
    private void add(){
        
        //String id = txtId.getText();
        String idConductor = txtIdConductor.getText();  
        String categoria = enumCategoria.getValue();
        LocalDate fEmisionSt = fEmision.getValue();
        LocalDate fVencimientoSt = fVencimiento.getValue();
        String estado = enumEstado.getValue();
        
        if (!idConductor.isEmpty() && !categoria.isEmpty() && !estado.isEmpty() && 
            !fEmisionSt.toString().isEmpty() && !fVencimientoSt.toString().isEmpty() ){
        
            try{
                Connection cn = Conexion.getInstancia().getConnection();
                try (CallableStatement c = cn.prepareCall("{call sp_licencias_create(?,?,?,?,?)}")){

                    try {
                        
                        //c.setInt(1,Integer.parseInt(id));
                        c.setInt(1,Integer.parseInt(idConductor));
                        c.setString(2,categoria);
                        c.setDate(3, Date.valueOf(fEmisionSt));
                        c.setDate(4, Date.valueOf(fVencimientoSt));
                        c.setString(5, estado);

                        try (ResultSet rs = c.executeQuery()){

                            Loader.getSingleton().alerta(Alert.AlertType.CONFIRMATION,"Operacion Realizada","Se agrego la licencia correctamente");

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
            
            Loader.getSingleton().alerta(Alert.AlertType.WARNING, "Campo vacio", "Uno o mas campos estan vacio");
            
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
        String idConductor = txtIdConductor.getText();  
        String categoria = enumCategoria.getValue();
        LocalDate fEmisionSt = fEmision.getValue();
        LocalDate fVencimientoSt = fVencimiento.getValue();
        String estado = enumEstado.getValue();
        
        if (!id.isEmpty()  &&!idConductor.isEmpty() && !categoria.isEmpty() && !estado.isEmpty() && 
            !fEmisionSt.toString().isEmpty() && !fVencimientoSt.toString().isEmpty() ){
            
            try{
                Connection cn = Conexion.getInstancia().getConnection();
                try (CallableStatement c = cn.prepareCall("{call sp_licencias_update(?,?,?,?,?,?)}")){
                    
                    try {
                        
                        c.setInt(1,Integer.parseInt(id));
                        c.setInt(2,Integer.parseInt(idConductor));
                        c.setString(3,categoria);
                        c.setDate(4, Date.valueOf(fEmisionSt));
                        c.setDate(5, Date.valueOf(fVencimientoSt));
                        c.setString(6, estado);
                        
                        try (ResultSet rs = c.executeQuery()){

                            Loader.getSingleton().alerta(Alert.AlertType.CONFIRMATION,"Operacion Realizada","Se actualizo la licencia correctamente");

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
            
            Loader.getSingleton().alerta(Alert.AlertType.WARNING, "Campo vacio", "Uno o mas campos estan vacio");
            
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
        
        if (!id.isEmpty()){
            
            try{
                Connection cn = Conexion.getInstancia().getConnection();
                try (CallableStatement c = cn.prepareCall("{call sp_licencias_delete(?)}")){

                    try {
                        
                        c.setInt(1,Integer.parseInt(id));
                        //c.setString(2,zona);

                        try (ResultSet rs = c.executeQuery()){

                            Loader.getSingleton().alerta(Alert.AlertType.CONFIRMATION,"Operacion Realizada","Se elimino la licencia correctamente");

                            while(rs.next()){



                            };
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
    
    private ArrayList<String[]> findLic(){
        
        ArrayList<String[]> result = new ArrayList<>();
        
        String id = txtId.getText();
        //String zona = txtZona.getText();
        
        if (!id.isEmpty()){
            
            try{
                Connection cn = Conexion.getInstancia().getConnection();
                try (CallableStatement c = cn.prepareCall("{call sp_licencias_read_by_id(?)}")){
                    
                    try {
                        
                        c.setInt(1,Integer.parseInt(id));
                        //c.setString(2,zona);

                        try (ResultSet rs = c.executeQuery()){

                            //Loader.getSingleton().alerta(Alert.AlertType.CONFIRMATION,"Operacion Realizada","Se elimino la zona correctamente");

                            while(rs.next()){

                                int idLicencia = rs.getInt("id_licencia");
                                int idConductor = rs.getInt("id_conductores");
                                String categoria = rs.getString("categoria");
                                Date fEmision = rs.getDate("fechaEmision");
                                Date fVencimiento = rs.getDate("fechaVencimiento");
                                String estado = rs.getString("estado");
                        
                        String[] array = {String.valueOf(idLicencia),String.valueOf(idConductor),
                            categoria,fEmision.toString(),fVencimiento.toString(), estado};

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
    private void onBuscarLic(){
        
        if(modeActual != Mode.BUSCARLIC){
            setMode(modeActual.BUSCARLIC);
            return;
        } else {
            
            reloadCards(findLic());
            
        }
        
    }
    
    private ArrayList<String[]> findCondu(){
        
        ArrayList<String[]> result = new ArrayList<>();
        
        String id = txtIdConductor.getText();
        //String zona = txtZona.getText();
        
        if (!id.isEmpty()){
            
            try{
                Connection cn = Conexion.getInstancia().getConnection();
                try (CallableStatement c = cn.prepareCall("{call sp_licencias_read_by_conductor(?)}")){
                    
                    try {
                        
                        c.setInt(1,Integer.parseInt(id));
                        //c.setString(2,zona);

                        try (ResultSet rs = c.executeQuery()){

                            //Loader.getSingleton().alerta(Alert.AlertType.CONFIRMATION,"Operacion Realizada","Se elimino la zona correctamente");

                            while(rs.next()){

                                int idLicencia = rs.getInt("id_licencia");
                                int idConductor = rs.getInt("id_conductores");
                                String categoria = rs.getString("categoria");
                                Date fEmision = rs.getDate("fechaEmision");
                                Date fVencimiento = rs.getDate("fechaVencimiento");
                                String estado = rs.getString("estado");
                        
                        String[] array = {String.valueOf(idLicencia),String.valueOf(idConductor),
                            categoria,fEmision.toString(),fVencimiento.toString(), estado};

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
    private void onBuscarCondu(){
        
        if(modeActual != Mode.BUSCARCONDU){
            setMode(modeActual.BUSCARCONDU);
            return;
        } else {
            
            reloadCards(findCondu());
            
        }
        
    }
    
    @FXML
    private void onActualizar(){
        cardsContainer.setContent(new Pane());
        reloadCards(getZonas());
        setMode(modeActual.NULL);
    }
    
    private void estado(){
        
        String id = txtId.getText().trim();
        String estado = enumEstado.getValue();
        
        if (!id.isEmpty()){
            
            try{
                Connection cn = Conexion.getInstancia().getConnection();
                try (CallableStatement c = cn.prepareCall("{call sp_licencias_set_estado(?,?)}")){

                    try {
                        
                        c.setInt(1,Integer.parseInt(id));
                        c.setString(2, estado);
                        //c.setString(2,zona);

                        try (ResultSet rs = c.executeQuery()){

                            Loader.getSingleton().alerta(Alert.AlertType.CONFIRMATION,"Operacion Realizada","Se edito el estado correctamente");

                            while(rs.next()){



                            };
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
    private void onEstado(){
        
        if(modeActual != Mode.ESTADO){
            setMode(modeActual.ESTADO);
            return;
        } else {
            
            estado(); 
            
        }
        
    }
    
    private void renovar(){
        
        String id = txtId.getText().trim();
        LocalDate fEmisionSt = fEmision.getValue();
        LocalDate fVencimientoSt = fVencimiento.getValue();
        
        if (!id.isEmpty()){
            
            try{
                Connection cn = Conexion.getInstancia().getConnection();
                try (CallableStatement c = cn.prepareCall("{call sp_licencias_renovar(?,?,?)}")){

                    try {
                        
                        c.setInt(1,Integer.parseInt(id));
                        c.setDate(2, Date.valueOf(fEmisionSt));
                        c.setDate(3, Date.valueOf(fVencimientoSt));
                        
                        //c.setString(2,zona);

                        try (ResultSet rs = c.executeQuery()){

                            Loader.getSingleton().alerta(Alert.AlertType.CONFIRMATION,"Operacion Realizada","Se renovo la licencia correctamente");

                            while(rs.next()){



                            };
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
    private void onRenovar(){
        
        if(modeActual != Mode.RENOVAR){
            setMode(modeActual.RENOVAR);
            return;
        } else {
            
            renovar(); 
            
        }
        
    }
    
    @FXML
    private void onLimpiar(){
        cardsContainer.setContent(new Pane());
        txtId.clear();
        txtIdConductor.clear();
        enumCategoria.setValue("");
        fEmision.setValue(null);
        fVencimiento.setValue(null);
        enumEstado.setValue("");
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
            try (CallableStatement c = cn.prepareCall("{call sp_licencias_read_all()}")){
                /*
                c.setString(1,usuario);
                c.setString(2,con);
                */
                
                try (ResultSet rs = c.executeQuery()){
                    while(rs.next()){
                        
                        int id = rs.getInt("id_licencia");
                        int idConductor = rs.getInt("id_conductores");
                        String categoria = rs.getString("categoria");
                        Date fEmision = rs.getDate("fechaEmision");
                        Date fVencimiento = rs.getDate("fechaVencimiento");
                        String estado = rs.getString("estado");
                        
                        String[] array = {String.valueOf(id),String.valueOf(idConductor),
                            categoria,fEmision.toString(),fVencimiento.toString(), estado};
                        
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
    
     private void selectCard(String id, String idConductor, String categoria, String fEmisionSt, String fVencimientoSt, String estado){
         
         txtId.setText(id);
         txtIdConductor.setText(idConductor);
         enumCategoria.setValue(categoria);
         fEmision.setValue(LocalDate.parse(fEmisionSt));
         fVencimiento.setValue(LocalDate.parse(fVencimientoSt));
         enumEstado.setValue(estado);
         
     }
    
    private CardLicencia createCardLicencia(String id, String idConductor, String categoria, String fEmisionSt, String fVencimientoSt, String estado){
        
        CardLicencia card = new CardLicencia("No. " + id, "Id Conductor: " + idConductor, "Categoria: " + categoria, 
                                        "Fecha Emision: " + fEmisionSt, "Fecha Vencimiento: " + fVencimientoSt,
                                        "Estado: " + estado);
        
        card.setOnMouseClicked(e -> {
            selectCard(id,idConductor,categoria,fEmisionSt,fVencimientoSt,estado);
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
            space.getChildren().add(createCardLicencia(i[0],i[1],i[2],i[3],i[4],i[5]));
            
        }
        
        cardsContainer.setContent(space);
    }
    
    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        
        comboBoxData.addAll("A", "B", "C", "M", "T");
        enumCategoria.setItems(comboBoxData);
        
        comboBoxData2.addAll("vigente","vencida","suspendida");
        enumEstado.setItems(comboBoxData2);
        
        reloadCards(getZonas());
        
        setMode(modeActual);
       
    }
    
}
