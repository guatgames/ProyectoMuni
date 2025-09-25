
package org.AngelAlfaro.components;

import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


public class CardInfraccion extends Pane{
    
    private String id, codigo, desc, monto;

    public CardInfraccion(String id, String codigo, String desc, String monto) {
        this.id = id;
        this.codigo = codigo;
        this.desc = desc;
        this.monto = monto;
        
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
        
        Label descLabel = new Label(desc);
        descLabel.setWrapText(true); // Permite que el texto salte de línea
        descLabel.setPrefWidth(275);
        descLabel.setStyle(" -fx-font-size: 18px;");
        contentBox.getChildren().add(descLabel);
        
        Label montoLabel = new Label(monto);
        montoLabel.setWrapText(true); // Permite que el texto salte de línea
        //montoLabel.setPrefWidth(110);
        montoLabel.setStyle(" -fx-font-size: 18px;");
        contentBox.getChildren().add(montoLabel);

        this.setStyle("-fx-background-color: #e2fafb; -fx-border-color: lightgray;"
                + " -fx-border-width: 1; -fx-border-radius: 10; -fx-effect:"
                + " dropshadow(three-pass-box, rgba(0,0,0,0.3), 5, 0, 0, 0); -fx-margin: 20;"
                + "-fx-background-radius: 10");
        this.getChildren().add(contentBox);
        this.setPrefSize(300, 250);
        
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
