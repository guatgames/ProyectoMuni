
package org.AngelAlfaro.controllers;

import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import org.AngelAlfaro.components.CardCiudadano;
import org.AngelAlfaro.conexion.Conexion;
import org.AngelAlfaro.system.Loader;

/**
 *
 * @author informatica
 */
public class CiudadanosController implements Initializable {
    
    @FXML
    private Button btnAgregar, btnEditar, btnEliminar, btnActualizar, btnBuscar, btnLimpiar;
    
    @FXML
    private TextField txtId, txtIdZona, txtNombre, txtApellido, txtDPI, txtTelefono, txtEmail, txtDireccion;
    @FXML
    private ScrollPane cardsContainer;
    
    private enum Mode{NULL,AGREGAR,EDITAR,ELIMINAR,BUSCAR};
    
    private Mode modeActual = Mode.NULL;
    
    private void setMode(Mode m){
        
        modeActual = m;
        
        txtId.setDisable(true);
        txtIdZona.setDisable(true);
        txtNombre.setDisable(true);
        txtApellido.setDisable(true);
        txtDPI.setDisable(true);
        txtTelefono.setDisable(true);
        txtEmail.setDisable(true);
        txtDireccion.setDisable(true);
        
        btnAgregar.setDisable(false);
        btnEditar.setDisable(false);
        btnEliminar.setDisable(false);
        btnActualizar.setDisable(false);
        btnBuscar.setDisable(false);
        btnLimpiar.setDisable(false);
        
        switch(m){
            case NULL:
                txtId.setDisable(true);
                txtIdZona.setDisable(true);
                txtNombre.setDisable(true);
                txtApellido.setDisable(true);
                txtDPI.setDisable(true);
                txtTelefono.setDisable(true);
                txtEmail.setDisable(true);
                txtDireccion.setDisable(true);
                
                btnAgregar.setDisable(false);
                btnEditar.setDisable(false);
                btnEliminar.setDisable(false);
                btnActualizar.setDisable(false);
                btnBuscar.setDisable(false);
                btnLimpiar.setDisable(false);
                
                break;
            case AGREGAR:
                txtIdZona.setDisable(false);
                txtNombre.setDisable(false);
                txtApellido.setDisable(false);
                txtDPI.setDisable(false);
                txtTelefono.setDisable(false);
                txtEmail.setDisable(false);
                txtDireccion.setDisable(false);
                
                txtIdZona.requestFocus();
                
                btnEditar.setDisable(true);
                btnEliminar.setDisable(true);
                //btnActualizar.setDisable(true);
                btnBuscar.setDisable(true);
                //btnLimpiar.setDisable(true);
                
                break;
            case EDITAR:
                txtId.setDisable(false);
                txtIdZona.setDisable(false);
                txtNombre.setDisable(false);
                txtApellido.setDisable(false);
                txtTelefono.setDisable(false);
                txtEmail.setDisable(false);
                txtDireccion.setDisable(false);
                
                txtId.requestFocus();
                
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
    
    private boolean isEmail(String email){
        
        String[] list = {"@gmail.com","@kinal.edu.g","@outlook.com","@yahoo.com","@hotmail.com",
                        "@mail.com","@aol.com"};
        
        boolean is = false;
        
        for(int i = 0;i < list.length; i++){

            if(email.contains(list[i])){
                
                is = true;
                break;
                
            } 

        }
        
        return is;
        
    }
    
    private void add(){
        
        //String id = txtId.getText().trim();
        String idZona = txtIdZona.getText().trim();
        String nom = txtNombre.getText().trim();    
        String ape = txtApellido.getText().trim();
        String dpi = txtDPI.getText().trim();
        String tel = txtTelefono.getText().trim();
        String email = txtEmail.getText().trim();
        String dir = txtDireccion.getText().trim();

        if (!idZona.isEmpty() && !nom.isEmpty() &&
                !ape.isEmpty() && !dpi.isEmpty() && !tel.isEmpty() && 
                !email.isEmpty() && !dir.isEmpty()){
            
                if (isEmail(email)){
                    
                    try{
                        Connection cn = Conexion.getInstancia().getConnection();
                        try (CallableStatement c = cn.prepareCall("{call sp_ciudadanos_create(?,?,?,?,?,?,?)}")){

                            try {

                                c.setInt(1,Integer.parseInt(idZona));
                                c.setString(2,nom);
                                c.setString(3,ape);
                                c.setString(4,dpi);
                                c.setString(5,tel);
                                c.setString(6,email);
                                c.setString(7,dir);
                                //c.setString(1,zona);
                                //c.setString(2,con);

                                try (ResultSet rs = c.executeQuery()){

                                    Loader.getSingleton().alerta(Alert.AlertType.CONFIRMATION,"Operacion Realizada","Se agrego el ciudadano correctamente");

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
                    
                    Loader.getSingleton().alerta(Alert.AlertType.WARNING, "Correo invalido", "El correo ingresado es invalido");
                    
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
        
        String id = txtId.getText().trim();
        String idZona = txtIdZona.getText().trim();
        String nom = txtNombre.getText().trim();    
        String ape = txtApellido.getText().trim();
        String dpi = txtDPI.getText().trim();
        String tel = txtTelefono.getText().trim();
        String email = txtEmail.getText().trim();
        String dir = txtDireccion.getText().trim();
        
        if (!id.isEmpty() && !idZona.isEmpty() && !nom.isEmpty() &&
                !ape.isEmpty() && !dpi.isEmpty() && !tel.isEmpty() && 
                !email.isEmpty() && !dir.isEmpty()){
            
                if (isEmail(email)){
                    
                    try{
                        Connection cn = Conexion.getInstancia().getConnection();
                        try (CallableStatement c = cn.prepareCall("{call sp_ciudadanos_update(?,?,?,?,?,?,?,?)}")){

                            try {

                                c.setInt(1, Integer.parseInt(id));
                                c.setInt(2, Integer.parseInt(idZona));
                                c.setString(3, nom);
                                c.setString(4, ape);
                                c.setString(5, dpi);
                                c.setString(6, tel);
                                c.setString(7, email);
                                c.setString(8, dir);

                                try (ResultSet rs = c.executeQuery()){

                                    Loader.getSingleton().alerta(Alert.AlertType.CONFIRMATION,"Operacion Realizada","Se actualizo el ciudadano correctamente");

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
                    
                    Loader.getSingleton().alerta(Alert.AlertType.WARNING, "Correo invalido", "El correo ingresado es invalido");
                    
                }
            
        } else {
            
            Loader.getSingleton().alerta(Alert.AlertType.WARNING, "Campo vacio", "Uno o mas de los campos estan vacios");
            
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
        
        String id = txtId.getText();
        //String zona = txtZona.getText();
        
        if (!id.isEmpty()){
            
                try{
                Connection cn = Conexion.getInstancia().getConnection();
                try (CallableStatement c = cn.prepareCall("{call sp_ciudadanos_delete(?)}")){

                    try {

                        c.setInt(1,Integer.parseInt(id));
                        //c.setString(2,zona);

                        try (ResultSet rs = c.executeQuery()){

                            Loader.getSingleton().alerta(Alert.AlertType.CONFIRMATION,"Operacion Realizada","Se elimino el ciudadano correctamente");

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
                try (CallableStatement c = cn.prepareCall("{call sp_ciudadanos_read_by_id(?)}")){

                   try {

                        c.setInt(1,Integer.parseInt(id));
                        //c.setString(2,zona);

                        try (ResultSet rs = c.executeQuery()){

                            //Loader.getSingleton().alerta(Alert.AlertType.CONFIRMATION,"Operacion Realizada","Se elimino la zona correctamente");

                            while(rs.next()){

                                int idCiudadano = rs.getInt("id_ciudadano");
                                int idZona = rs.getInt("id_zona");
                                String nom = rs.getString("nombre");
                                String ape = rs.getString("apellidos");
                                String dpi = rs.getString("dpi");
                                String telefono = rs.getString("telefono");
                                String email = rs.getString("email");
                                String dir = rs.getString("direccion");

                                String[] array = {String.valueOf(idCiudadano),String.valueOf(idZona),
                                                    nom, ape, dpi, telefono, email, dir};

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
        txtIdZona.clear();
        txtNombre.clear();
        txtApellido.clear();
        txtDPI.clear();
        txtTelefono.clear();
        txtEmail.clear();
        txtDireccion.clear();
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
            try (CallableStatement c = cn.prepareCall("{call sp_ciudadanos_read_all()}")){
                /*
                c.setString(1,usuario);
                c.setString(2,con);
                */
                
                try (ResultSet rs = c.executeQuery()){
                    while(rs.next()){
                        
                        int id = rs.getInt("id_ciudadano");
                        int idZona = rs.getInt("id_zona");
                        String nom = rs.getString("nombre");
                        String ape = rs.getString("apellidos");
                        String dpi = rs.getString("dpi");
                        String telefono = rs.getString("telefono");
                        String email = rs.getString("email");
                        String dir = rs.getString("direccion");
                        
                        String[] array = {String.valueOf(id),String.valueOf(idZona),
                                            nom, ape, dpi, telefono, email, dir};
                        
                        result.add(array);
                        
                    }
                    
                    //return result;
                    
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
    
    private void selectCard(String id,String idZona, String nom, String ape, String dpi, String tel, String email, String dir){
        
        txtId.setText(id);
        txtIdZona.setText(idZona);
        txtNombre.setText(nom);
        txtApellido.setText(ape);
        txtDPI.setText(dpi);
        txtTelefono.setText(tel);
        txtEmail.setText(email);
        txtDireccion.setText(dir);
        
    }
    
    private CardCiudadano createCardCiudadano(String id, String idZona, String nom, String ape, String dpi, String tel, String email, String dir){
        
        CardCiudadano card = new CardCiudadano("No. " + id,"Zona: " + idZona, "Nombre: " + nom,
                ape,"DPI: " + dpi,"Telefono: " + tel, "E-mail: " + email,"Direccion: " + dir);
        
        
        
        card.setOnMouseClicked(e -> {
            selectCard(id,idZona,nom,ape,dpi,tel,email,dir);
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
            space.getChildren().add(createCardCiudadano(i[0],i[1],i[2],i[3],i[4],i[5],i[6],i[7]));
            
        }
        
        cardsContainer.setContent(space);
    }
    
    
    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        
        getZonas();
        
        FlowPane space = new FlowPane();
        space.setHgap(5);
        space.setVgap(15);
        
        for(String[] i: getZonas()){
            
            HBox contentBox = new HBox(5);
            //space.getChildren().add(contentBox);
            space.getChildren().add(createCardCiudadano(i[0],i[1],i[2],i[3],i[4],i[5],i[6],i[7]));
            
        }
        
        cardsContainer.setContent(space);
    }
    
}
