/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.AngelAlfaro.components;

import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 *
 * @author informatica
 */
public class CardCiudadano extends Pane {
    
    private String id, idZona, nom, ape, dpi, tel, email, dir;
    
    public CardCiudadano(String id, String zona, String nom, String ape, String dpi, String tel, String email, String dir ) {
        
        this.id = id;
        this.idZona = zona;
        this.nom = nom;
        this.ape = ape;
        this.dpi = dpi;
        this.tel = tel;
        this.email = email;
        this.dir = dir;
        
        VBox contentBox = new VBox(10); // Espacio de 10px entre elementos
        contentBox.setPadding(new javafx.geometry.Insets(15)); // Relleno interno
        
        Label titleLabel = new Label(id);
        titleLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 24px; -fx-text-fill: #1c9ab0");
        contentBox.getChildren().add(titleLabel);

        Label idZonaLabel = new Label(zona);
        idZonaLabel.setWrapText(true); // Permite que el texto salte de línea
        idZonaLabel.setStyle(" -fx-font-size: 18px;");
        contentBox.getChildren().add(idZonaLabel);
        
        Label nomLabel = new Label(nom +" " + ape);
        nomLabel.setWrapText(true); 
        nomLabel.setStyle(" -fx-font-size: 18px;");
        contentBox.getChildren().add(nomLabel);
        
        Label dpiLabel = new Label(dpi);
        dpiLabel.setWrapText(true); 
        dpiLabel.setStyle(" -fx-font-size: 18px;");
        contentBox.getChildren().add(dpiLabel);
        
        Label telLabel = new Label(tel);
        telLabel.setWrapText(true); 
        telLabel.setStyle(" -fx-font-size: 18px;");
        contentBox.getChildren().add(telLabel);
        
        Label emailLabel = new Label(email);
        emailLabel.setWrapText(true); 
        emailLabel.setStyle(" -fx-font-size: 18px;");
        contentBox.getChildren().add(emailLabel);
        
        Label dirLabel = new Label(dir);
        dirLabel.setWrapText(true); 
        dirLabel.setStyle(" -fx-font-size: 18px;");
        contentBox.getChildren().add(dirLabel);

        this.setStyle("-fx-background-color: #e2fafb; -fx-border-color: lightgray;"
                + " -fx-border-width: 1; -fx-border-radius: 10; -fx-effect:"
                + " dropshadow(three-pass-box, rgba(0,0,0,0.3), 5, 0, 0, 0); -fx-margin: 20;"
                + "-fx-background-radius: 10");
        this.getChildren().add(contentBox);
        this.setPrefSize(275, 325);
        
        this.setOnMouseEntered(e -> {
        
            this.setStyle("-fx-background-color: #9fe5d7; -fx-border-color: lightgray;"
                + " -fx-border-width: 1; -fx-border-radius: 10; -fx-effect:"
                + " dropshadow(three-pass-box, rgba(0,0,0,0.3), 5, 0, 0, 0); -fx-margin: 20;"
                + "-fx-background-radius: 10");
            
            this.setCursor(Cursor.HAND);
            
        });
        
        this.setOnMouseExited(e -> {
        
            this.setStyle("-fx-background-color: #e2fafb; -fx-border-color: lightgray;"
                + " -fx-border-width: 1; -fx-border-radius: 10; -fx-effect:"
                + " dropshadow(three-pass-box, rgba(0,0,0,0.3), 5, 0, 0, 0); -fx-margin: 20;"
                + "-fx-background-radius: 10");
            
        });
        
    }  
    
}
