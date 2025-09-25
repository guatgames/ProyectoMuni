
package org.AngelAlfaro.components;

import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.control.Button;

public class Card extends Pane {
    public Card(String title, String description) {
        VBox contentBox = new VBox(10); // Espacio de 10px entre elementos
        contentBox.setPadding(new javafx.geometry.Insets(15)); // Relleno interno
        
        Label titleLabel = new Label(title);
        titleLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 18px;");
        contentBox.getChildren().add(titleLabel);

        Label descriptionLabel = new Label(description);
        descriptionLabel.setWrapText(true); // Permite que el texto salte de línea
        contentBox.getChildren().add(descriptionLabel);

        this.setStyle("-fx-background-color: #e2fafb; -fx-border-color: lightgray;"
                + " -fx-border-width: 1; -fx-border-radius: 10; -fx-effect:"
                + " dropshadow(three-pass-box, rgba(0,0,0,0.3), 5, 0, 0, 0); -fx-margin: 20;"
                + "-fx-background-radius: 10");
        this.getChildren().add(contentBox);
        this.setPrefSize(100, 50);
        
        this.setOnMouseEntered(ev -> {
        
                
            
        });
        
    }
    
    
}