
package org.AngelAlfaro.components;

import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;


public class CardVehiculo extends Pane {
    
    private String id, placa, marca, modelo, anio, color, idCiudadano;

    public CardVehiculo(String id, String placa, String marca, String modelo, String anio, String color, String idCiudadano) {
        this.id = id;
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
        this.color = color;
        this.idCiudadano = idCiudadano;
        
        VBox contentBox = new VBox(10); // Espacio de 10px entre elementos
        contentBox.setPadding(new javafx.geometry.Insets(15)); // Relleno interno
        
        Label titleLabel = new Label(id);
        titleLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 24px; -fx-text-fill: #1c9ab0");
        contentBox.getChildren().add(titleLabel);

        Label placaLabel = new Label(placa);
        placaLabel.setWrapText(true); // Permite que el texto salte de línea
        //placaLabel.setPrefWidth(110);
        placaLabel.setStyle(" -fx-font-size: 18px;");
        contentBox.getChildren().add(placaLabel);
        
        Label marcaLabel = new Label(marca);
        marcaLabel.setWrapText(true); // Permite que el texto salte de línea
        //marcaLabel.setPrefWidth(110);
        marcaLabel.setStyle(" -fx-font-size: 18px;");
        contentBox.getChildren().add(marcaLabel);
        
        Label modeloLabel = new Label(modelo);
        modeloLabel.setWrapText(true); // Permite que el texto salte de línea
        //modeloLabel.setPrefWidth(110);
        modeloLabel.setStyle(" -fx-font-size: 18px;");
        contentBox.getChildren().add(modeloLabel);
        
        Label anioLabel = new Label(anio);
        anioLabel.setWrapText(true); // Permite que el texto salte de línea
        //anioLabel.setPrefWidth(110);
        anioLabel.setStyle(" -fx-font-size: 18px;");
        contentBox.getChildren().add(anioLabel);
        
        Pane colorContainer = new Pane();
        
        Label colorLabel = new Label("Color:         " + color.replace("0x", "#"));
        colorLabel.setWrapText(true); // Permite que el texto salte de línea
        //colorLabel.setPrefWidth(110);
        colorLabel.setStyle(" -fx-font-size: 18px;");
        colorContainer.getChildren().add(colorLabel);
        
        Pane colorPane = new Pane();
        colorPane.setPrefSize(25, 25);
        colorPane.setTranslateX(55);
       //colorPane.setTranslateY(5);
        colorPane.setStyle("-fx-background-color: " + color.replace("0x", "#") + "; -fx-border-radius: 5; -fx-background-radius: 5;"
                + "-fx-border-color: #262729");
        colorContainer.getChildren().add(colorPane);
        
        contentBox.getChildren().add(colorContainer);
        
        Label idCiudadanoLabel = new Label(idCiudadano);
        idCiudadanoLabel.setWrapText(true); // Permite que el texto salte de línea
        //idCiudadanoLabel.setPrefWidth(110);
        idCiudadanoLabel.setStyle(" -fx-font-size: 18px;");
        contentBox.getChildren().add(idCiudadanoLabel);

        this.setStyle("-fx-background-color: #e2fafb; -fx-border-color: lightgray;"
                + " -fx-border-width: 1; -fx-border-radius: 10; -fx-effect:"
                + " dropshadow(three-pass-box, rgba(0,0,0,0.3), 5, 0, 0, 0); -fx-margin: 20;"
                + "-fx-background-radius: 10");
        this.getChildren().add(contentBox);
        this.setPrefSize(300, 300);
        
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
