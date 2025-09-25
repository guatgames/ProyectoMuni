
package org.AngelAlfaro.components;

import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


public class CardConductor extends Pane {
    
    private String id, idCiudadano, tipoSangre;
    
    public CardConductor(String id, String idCiudadano, String tipoSangre) {
        
        this.id = id;
        this.idCiudadano = idCiudadano;
        this.tipoSangre = tipoSangre;
        
        VBox contentBox = new VBox(10); // Espacio de 10px entre elementos
        contentBox.setPadding(new javafx.geometry.Insets(15)); // Relleno interno
        
        Label titleLabel = new Label(id);
        titleLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 24px; -fx-text-fill: #1c9ab0");
        contentBox.getChildren().add(titleLabel);

        Label idCiudadanoLabel = new Label(idCiudadano);
        idCiudadanoLabel.setWrapText(true); // Permite que el texto salte de línea
        //zonaLabel.setPrefWidth(110);
        idCiudadanoLabel.setStyle(" -fx-font-size: 18px;");
        contentBox.getChildren().add(idCiudadanoLabel);
        
        Label tipoSangreLabel = new Label(tipoSangre);
        tipoSangreLabel.setWrapText(true); // Permite que el texto salte de línea
        //zonaLabel.setPrefWidth(110);
        tipoSangreLabel.setStyle(" -fx-font-size: 18px;");
        contentBox.getChildren().add(tipoSangreLabel);

        this.setStyle("-fx-background-color: #e2fafb; -fx-border-color: lightgray;"
                + " -fx-border-width: 1; -fx-border-radius: 10; -fx-effect:"
                + " dropshadow(three-pass-box, rgba(0,0,0,0.3), 5, 0, 0, 0); -fx-margin: 20;"
                + "-fx-background-radius: 10");
        this.getChildren().add(contentBox);
        this.setPrefSize(250, 150);
        
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
