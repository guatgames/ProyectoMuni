
package org.AngelAlfaro.components;

import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class CardPago extends Pane {
    
    private String id, idMulta, fecha, monto, metodo, referencia;

    public CardPago(String id, String idMulta, String fecha, String monto, String metodo, String referencia) {
        this.id = id;
        this.idMulta = idMulta;
        this.fecha = fecha;
        this.monto = monto;
        this.metodo = metodo;
        this.referencia = referencia;
        
        VBox contentBox = new VBox(10); // Espacio de 10px entre elementos
        contentBox.setPadding(new javafx.geometry.Insets(15)); // Relleno interno
        
        Label titleLabel = new Label(id);
        titleLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 24px; -fx-text-fill: #1c9ab0");
        contentBox.getChildren().add(titleLabel);

        Label idMultaLabel = new Label(idMulta);
        idMultaLabel.setWrapText(true); // Permite que el texto salte de línea
        //idMultaLabel.setPrefWidth(110);
        idMultaLabel.setStyle(" -fx-font-size: 18px;");
        contentBox.getChildren().add(idMultaLabel);
        
        Label fechaLabel = new Label(fecha);
        fechaLabel.setWrapText(true); // Permite que el texto salte de línea
        //fechaLabel.setPrefWidth(110);
        fechaLabel.setStyle(" -fx-font-size: 18px;");
        contentBox.getChildren().add(fechaLabel);
        
        Label montoLabel = new Label(monto);
        montoLabel.setWrapText(true); // Permite que el texto salte de línea
        //montoLabel.setPrefWidth(110);
        montoLabel.setStyle(" -fx-font-size: 18px;");
        contentBox.getChildren().add(montoLabel);
        
        Label metodoLabel = new Label(metodo);
        metodoLabel.setWrapText(true); // Permite que el texto salte de línea
        //metodLabel.setPrefWidth(110);
        metodoLabel.setStyle(" -fx-font-size: 18px;");
        contentBox.getChildren().add(metodoLabel);
        
        Label referenciaLabel = new Label(referencia);
        referenciaLabel.setWrapText(true); // Permite que el texto salte de línea
        //referenciaLabel.setPrefWidth(210);
        referenciaLabel.setStyle(" -fx-font-size: 18px;");
        contentBox.getChildren().add(referenciaLabel);

        this.setStyle("-fx-background-color: #e2fafb; -fx-border-color: lightgray;"
                + " -fx-border-width: 1; -fx-border-radius: 10; -fx-effect:"
                + " dropshadow(three-pass-box, rgba(0,0,0,0.3), 5, 0, 0, 0); -fx-margin: 20;"
                + "-fx-background-radius: 10");
        this.getChildren().add(contentBox);
        this.setPrefSize(230, 275);
        
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
