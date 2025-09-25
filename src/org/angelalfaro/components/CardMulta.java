
package org.AngelAlfaro.components;

import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class CardMulta extends Pane {
    
    private String id, idI, idC, idV, idA, fecha, lugar, monto, estado,observacion;

    public CardMulta(String id, String idI, String idC, String idV, String idA,
            String fecha, String lugar, String monto, String estado, String observacion) {
        this.id = id;
        this.idI = idI;
        this.idC = idC;
        this.idV = idV;
        this.idA = idA;
        this.fecha = fecha;
        this.lugar = lugar;
        this.monto = monto;
        this.estado = estado;
        this.observacion = observacion;
        
        VBox contentBox = new VBox(10); // Espacio de 10px entre elementos
        contentBox.setPadding(new javafx.geometry.Insets(15)); // Relleno interno
        
        Label titleLabel = new Label(id);
        titleLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 24px; -fx-text-fill: #1c9ab0");
        contentBox.getChildren().add(titleLabel);

        Label idILabel = new Label(idI);
        idILabel.setWrapText(true); // Permite que el texto salte de línea
        //idILabel.setPrefWidth(110);
        idILabel.setStyle(" -fx-font-size: 18px;");
        contentBox.getChildren().add(idILabel);
        
        Label idCLabel = new Label(idC);
        idCLabel.setWrapText(true); // Permite que el texto salte de línea
        //idCLabel.setPrefWidth(110);
        idCLabel.setStyle(" -fx-font-size: 18px;");
        contentBox.getChildren().add(idCLabel);
        
        Label idVLabel = new Label(idV);
        idVLabel.setWrapText(true); // Permite que el texto salte de línea
        //idVLabel.setPrefWidth(110);
        idVLabel.setStyle(" -fx-font-size: 18px;");
        contentBox.getChildren().add(idVLabel);
        
        Label idALabel = new Label(idA);
        idALabel.setWrapText(true); // Permite que el texto salte de línea
        //idALabel.setPrefWidth(110);
        idALabel.setStyle(" -fx-font-size: 18px;");
        contentBox.getChildren().add(idALabel);
        
        Label fechaLabel = new Label(fecha);
        fechaLabel.setWrapText(true); // Permite que el texto salte de línea
        //fechaLabel.setPrefWidth(110);
        fechaLabel.setStyle(" -fx-font-size: 18px;");
        contentBox.getChildren().add(fechaLabel);
        
        Label lugarLabel = new Label(lugar);
        lugarLabel.setWrapText(true); // Permite que el texto salte de línea
        //lugarLabel.setPrefWidth(110);
        lugarLabel.setStyle(" -fx-font-size: 18px;");
        contentBox.getChildren().add(lugarLabel);
        
        Label montoLabel = new Label(monto);
        montoLabel.setWrapText(true); // Permite que el texto salte de línea
        //montoLabel.setPrefWidth(110);
        montoLabel.setStyle(" -fx-font-size: 18px;");
        contentBox.getChildren().add(montoLabel);
        
        Label estadoLabel = new Label(estado);
        estadoLabel.setWrapText(true); // Permite que el texto salte de línea
        //estadoLabel.setPrefWidth(110);
        estadoLabel.setStyle(" -fx-font-size: 18px;");
        contentBox.getChildren().add(estadoLabel);
        
        Label observacionLabel = new Label(observacion);
        observacionLabel.setWrapText(true); // Permite que el texto salte de línea
        //observacionLabel.setPrefWidth(110);
        observacionLabel.setStyle(" -fx-font-size: 18px;");
        contentBox.getChildren().add(observacionLabel);

        this.setStyle("-fx-background-color: #e2fafb; -fx-border-color: lightgray;"
                + " -fx-border-width: 1; -fx-border-radius: 10; -fx-effect:"
                + " dropshadow(three-pass-box, rgba(0,0,0,0.3), 5, 0, 0, 0); -fx-margin: 20;"
                + "-fx-background-radius: 10");
        this.getChildren().add(contentBox);
        this.setPrefSize(400, 400);
        
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
