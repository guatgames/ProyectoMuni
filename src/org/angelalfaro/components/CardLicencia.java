
package org.AngelAlfaro.components;

import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 *
 * @author informatica
 */
public class CardLicencia extends Pane {
    
    private String id, idConductor, categoria, fEmision, fVencimiento, estado;

    public CardLicencia(String id, String idConductor, String categoria, String fEmision, String fVencimiento, String estado) {
        this.id = id;
        this.idConductor = idConductor;
        this.categoria = categoria;
        this.fEmision = fEmision;
        this.fVencimiento = fVencimiento;
        this.estado = estado;
        
        VBox contentBox = new VBox(10); // Espacio de 10px entre elementos
        contentBox.setPadding(new javafx.geometry.Insets(15)); // Relleno interno
        
        Label titleLabel = new Label(id);
        titleLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 24px; -fx-text-fill: #1c9ab0");
        contentBox.getChildren().add(titleLabel);

        Label idConductorLabel = new Label(idConductor);
        idConductorLabel.setWrapText(true); // Permite que el texto salte de línea
        //idConductorLabel.setPrefWidth(110);
        idConductorLabel.setStyle(" -fx-font-size: 18px;");
        contentBox.getChildren().add(idConductorLabel);
        
        Label categoriaLabel = new Label(categoria);
        categoriaLabel.setWrapText(true); // Permite que el texto salte de línea
        //categoriaLabel.setPrefWidth(110);
        categoriaLabel.setStyle(" -fx-font-size: 18px;");
        contentBox.getChildren().add(categoriaLabel);
        
        Label fEmisionLabel = new Label(fEmision);
        fEmisionLabel.setWrapText(true); // Permite que el texto salte de línea
        //categoriaLabel.setPrefWidth(110);
        fEmisionLabel.setStyle(" -fx-font-size: 18px;");
        contentBox.getChildren().add(fEmisionLabel);
        
        Label fVencimientoLabel = new Label(fVencimiento);
        fVencimientoLabel.setWrapText(true); // Permite que el texto salte de línea
        //categoriaLabel.setPrefWidth(110);
        fVencimientoLabel.setStyle(" -fx-font-size: 18px;");
        contentBox.getChildren().add(fVencimientoLabel);
        
        Label estadoLabel = new Label(estado);
        estadoLabel.setWrapText(true); // Permite que el texto salte de línea
        //estadoLabel.setPrefWidth(110);
        estadoLabel.setStyle(" -fx-font-size: 18px;");
        contentBox.getChildren().add(estadoLabel);

        this.setStyle("-fx-background-color: #e2fafb; -fx-border-color: lightgray;"
                + " -fx-border-width: 1; -fx-border-radius: 10; -fx-effect:"
                + " dropshadow(three-pass-box, rgba(0,0,0,0.3), 5, 0, 0, 0); -fx-margin: 20;"
                + "-fx-background-radius: 10");
        this.getChildren().add(contentBox);
        this.setPrefSize(300,275);
        
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
