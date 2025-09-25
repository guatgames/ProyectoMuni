
package org.AngelAlfaro.conexion;

import java.sql.Connection;
import java.sql.DriverManager;       
 
public class Conexion {
    private static Conexion instancia;
    private Connection connection;
    
    public Conexion(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/DB_Municipalidad_JavaFX";
            String user = "root";
            String password = "admin";
            connection =  DriverManager.getConnection(url,user,password); 
            System.out.println("Conectado");    
        } catch(Exception e){
            System.out.println(e); 
        }    
      }
    public static Conexion getInstancia(){
      if(instancia == null){
          instancia = new Conexion();
      }       
      return instancia;
    }
 
    public Connection getConnection() {
        return connection;
    }
 
    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}