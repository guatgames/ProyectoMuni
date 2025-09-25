
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
import org.AngelAlfaro.components.CardAgente;
import org.AngelAlfaro.controllers.ZonasController;

public class AgentesController implements Initializable{
    
    @FXML
    private TextField txtId, txtCodigo, txtNombre, txtApellido, txtTelefono, txtIdZona;
    
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
        txtNombre.setDisable(true);
        txtApellido.setDisable(true);
        txtTelefono.setDisable(true);
        txtIdZona.setDisable(true);
        
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
                txtNombre.setDisable(true);
                txtApellido.setDisable(true);
                txtTelefono.setDisable(true);
                txtIdZona.setDisable(true);

                btnAgregar.setDisable(false);
                btnEditar.setDisable(false);
                btnEliminar.setDisable(false);
                btnActualizar.setDisable(false);
                btnBuscar.setDisable(false);
                btnLimpiar.setDisable(false);
                
                break;
            case AGREGAR:
                txtCodigo.setDisable(false);
                txtNombre.setDisable(false);
                txtApellido.setDisable(false);
                txtTelefono.setDisable(false);
                txtIdZona.setDisable(false);
                
                btnEditar.setDisable(true);
                btnEliminar.setDisable(true);
                //btnActualizar.setDisable(true);
                btnBuscar.setDisable(true);
                //btnLimpiar.setDisable(true);
                
                break;
            case EDITAR:
                txtId.setDisable(false);
                txtCodigo.setDisable(false);
                txtNombre.setDisable(false);
                txtApellido.setDisable(false);
                txtTelefono.setDisable(false);
                txtIdZona.setDisable(false);

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
        
        String codigo = txtCodigo.getText();
        String nom = txtNombre.getText();
        String ape = txtApellido.getText();
        String tel = txtTelefono.getText();
        String idZona = txtIdZona.getText();
        
        if (!codigo.isEmpty() && !nom.isEmpty() && !ape.isEmpty() && !tel.isEmpty()
                && !idZona.isEmpty()){
        
            try{
                Connection cn = Conexion.getInstancia().getConnection();
                try (CallableStatement c = cn.prepareCall("{call sp_agentes_create(?,?,?,?,?)}")){

                    try {
                        
                        c.setString(1,codigo);
                        c.setString(2,nom);
                        c.setString(3,ape);
                        c.setString(4,tel);
                        c.setInt(5,Integer.parseInt(idZona));
                        
                        try (ResultSet rs = c.executeQuery()){

                            Loader.getSingleton().alerta(Alert.AlertType.CONFIRMATION,"Operacion Realizada","Se agrego el agente correctamente");

                            while(rs.next()){



                            }
                        }
                        
                    } catch (NumberFormatException nfe){
                        
                        Loader.getSingleton().alerta(Alert.AlertType.WARNING, "Valor invalido", "Uno o mas valores no son numericos numerico");
                        
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
        String nom = txtNombre.getText();
        String ape = txtApellido.getText();
        String tel = txtTelefono.getText();
        String idZona = txtIdZona.getText();
        
        if (!id.isEmpty() && !codigo.isEmpty() && !nom.isEmpty() && !ape.isEmpty() && !tel.isEmpty()
                && !idZona.isEmpty()){
            
            try{
                Connection cn = Conexion.getInstancia().getConnection();
                try (CallableStatement c = cn.prepareCall("{call sp_agentes_update(?,?,?,?,?,?)}")){
                    
                    try {
                        
                        c.setInt(1,Integer.parseInt(id));
                        c.setString(2,codigo);
                        c.setString(3,nom);
                        c.setString(4,ape);
                        c.setString(5,tel);
                        c.setInt(6,Integer.parseInt(idZona));
                        
                        try (ResultSet rs = c.executeQuery()){

                            Loader.getSingleton().alerta(Alert.AlertType.CONFIRMATION,"Operacion Realizada","Se actualizo el agente correctamente");

                            while(rs.next()){



                            }
                        }
                        
                    } catch (NumberFormatException nfe){
                        
                        Loader.getSingleton().alerta(Alert.AlertType.WARNING, "Valor invalido", "Uno o mas campos no son numericos numerico");
                        
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
                try (CallableStatement c = cn.prepareCall("{call sp_agentes_delete(?)}")){

                    try {
                        
                        c.setInt(1,Integer.parseInt(id));
                        //c.setString(2,zona);

                        try (ResultSet rs = c.executeQuery()){

                            Loader.getSingleton().alerta(Alert.AlertType.CONFIRMATION,"Operacion Realizada","Se elimino el agente correctamente");

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
                try (CallableStatement c = cn.prepareCall("{call sp_agentes_read_by_id(?)}")){
                    
                    try {
                        
                        c.setInt(1,Integer.parseInt(id));
                        //c.setString(2,zona);

                        try (ResultSet rs = c.executeQuery()){

                            //Loader.getSingleton().alerta(Alert.AlertType.CONFIRMATION,"Operacion Realizada","Se elimino la zona correctamente");

                            while(rs.next()){

                                int idAgente = rs.getInt("id_agente");
                                String codigo = rs.getString("codigo");
                                String nom = rs.getString("nombre");
                                String ape = rs.getString("apellido");
                                String tel = rs.getString("telefono");
                                int idZona = rs.getInt("id_zona");

                                String[] array = {String.valueOf(idAgente),codigo, nom, ape, tel,String.valueOf(idZona)};

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
        txtNombre.clear();
        txtApellido.clear();
        txtTelefono.clear();
        txtIdZona.clear();
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
            try (CallableStatement c = cn.prepareCall("{call sp_agentes_read_all()}")){
                /*
                c.setString(1,usuario);
                c.setString(2,con);
                */
                
                try (ResultSet rs = c.executeQuery()){
                    while(rs.next()){
                        
                        int id = rs.getInt("id_agente");
                        String codigo = rs.getString("codigo");
                        String nom = rs.getString("nombre");
                        String ape = rs.getString("apellido");
                        String tel = rs.getString("telefono");
                        int idZona = rs.getInt("id_zona");
                        
                        String[] array = {String.valueOf(id),codigo, nom, ape, tel,String.valueOf(idZona)};
                        
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
    
     private void selectCard(String id, String codigo, String nom, String ape, String tel, String idZona){
         
        txtId.setText(id);
        txtCodigo.setText(codigo);
        txtNombre.setText(nom);
        txtApellido.setText(ape);
        txtTelefono.setText(tel);
        txtIdZona.setText(idZona);
         
     }
    
    private CardAgente createCardAgente(String id, String codigo, String nom, String ape, String tel, String idZona){
        
        CardAgente card = new CardAgente("No. " + id, "Codigo: " + codigo, "Nombre: " + nom,
                ape, "Telefono: " + tel, "Id Zona: " + idZona);
        
        card.setOnMouseClicked(e -> {
            selectCard(id,codigo,nom,ape,tel,idZona);
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
            space.getChildren().add(createCardAgente(i[0],i[1],i[2],i[3],i[4],i[5]));
            
        }
        
        cardsContainer.setContent(space);
    }
    
    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        
        reloadCards(getZonas());
        
        setMode(modeActual);
       
    }
    
}
