
package org.AngelAlfaro.models;


public class Zonas {
    
    private int id_zona;
    private String zona;
    
    public Zonas(){
        
    }

    public Zonas(int id_zona, String zona) {
        this.id_zona = id_zona;
        this.zona = zona;
    }

    public int getId_zona() {
        return id_zona;
    }

    public void setId_zona(int id_zona) {
        this.id_zona = id_zona;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }
    
    
    
}
