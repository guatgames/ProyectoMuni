
package org.AngelAlfaro.components;

import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class CardAgente extends Pane {
    
    private String id, codigo, nom, ape, tel, idZona;

    public CardAgente(String id, String codigo, String nom, String ape, String tel, String idZona) {
        this.id = id;
        this.codigo = codigo;
        this.nom = nom;
        this.ape = ape;
        this.tel = tel;
        this.idZona = idZona;
        
        VBox contentBox = new VBox(10); // Espacio de 10px entre elementos
        contentBox.setPadding(new javafx.geometry.Insets(15)); // Relleno interno
        
        Label titleLabel = new Label(id);
        titleLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 24px; -fx-text-fill: #1c9ab0");
        contentBox.getChildren().add(titleLabel);

        Label codigoLabel = new Label(codigo);
        codigoLabel.setWrapText(true); // Permite que el texto salte de línea
        //codigoLabel.setPrefWidth(110);
        codigoLabel.setStyle(" -fx-font-size: 18px;");
        contentBox.getChildren().add(codigoLabel);
        
        Label nomLabel = new Label(nom + " " + ape);
        nomLabel.setWrapText(true); // Permite que el texto salte de línea
        //nomLabel.setPrefWidth(110);
        nomLabel.setStyle(" -fx-font-size: 18px;");
        contentBox.getChildren().add(nomLabel);
        
        Label telLabel = new Label(tel);
        telLabel.setWrapText(true); 
        telLabel.setStyle(" -fx-font-size: 18px;");
        contentBox.getChildren().add(telLabel);
        
        Label idZonaLabel = new Label(idZona);
        idZonaLabel.setWrapText(true); // Permite que el texto salte de línea
        idZonaLabel.setStyle(" -fx-font-size: 18px;");
        contentBox.getChildren().add(idZonaLabel);

        this.setStyle("-fx-background-color: #e2fafb; -fx-border-color: lightgray;"
                + " -fx-border-width: 1; -fx-border-radius: 10; -fx-effect:"
                + " dropshadow(three-pass-box, rgba(0,0,0,0.3), 5, 0, 0, 0); -fx-margin: 20;"
                + "-fx-background-radius: 10");
        this.getChildren().add(contentBox);
        this.setPrefSize(220, 210);
        
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
