package com.example.materialesdb_recuperacion;

import com.example.materialesdb_recuperacion.DAO.MaterialDAO;
import com.example.materialesdb_recuperacion.modelos.Material;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class NuevoRegistroMaestroController {
    private MaterialDAO materialDAO = new MaterialDAO();
    private Material materialAux;
    @javafx.fxml.FXML
    private GridPane gridPaneCuerpo;
    @javafx.fxml.FXML
    private Label botonVolverInicio;
    @javafx.fxml.FXML
    private TextField tfNombreNR;
    @javafx.fxml.FXML
    private TextField tfFabricanteNR;
    @javafx.fxml.FXML
    private TextField tfMaterialNR;
    @javafx.fxml.FXML
    private ComboBox cbIndicadorPeligroNR;
    @javafx.fxml.FXML
    private DatePicker tfFechaInicioNR;
    @javafx.fxml.FXML
    private DatePicker tfFechaFinNR;
    @javafx.fxml.FXML
    private Button botonNuevoRegistro;
    @javafx.fxml.FXML
    private TextField tfPrecioNR;

    @FXML
    protected void initialize(){

        ObservableList<String> olOptions = FXCollections.observableArrayList(
                "A",
                "B",
                "C",
                "D",
                "E"
        );

        cbIndicadorPeligroNR.setItems(olOptions);

        cargarHandlers();
    }

    private void cargarHandlers(){
        botonVolverInicio.setOnMouseClicked((EventHandler) event -> {
            try {
                Stage stage = new Stage();
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 640, 500);
                stage.setTitle("Inicio");
                stage.setMinWidth(640);
                stage.setMinHeight(500);
                stage.setScene(scene);
                stage.show();
                Node source = (Node) event.getSource();
                Stage stageActual = (Stage) source.getScene().getWindow();
                stageActual.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        botonNuevoRegistro.setOnMouseClicked((EventHandler) event -> {
            try {
                Alert alert;
                if(tfNombreNR.getText().trim().equals("") || tfFabricanteNR.getText().trim().equals("") || tfMaterialNR.getText().trim().equals("") || tfPrecioNR.getText().trim().equals("") || cbIndicadorPeligroNR.getValue().toString() == "" || tfFechaInicioNR.getValue().toString().trim().equals("") || tfFechaFinNR.getValue().toString().trim().equals("")){

                    alert = new Alert(Alert.AlertType.INFORMATION, "Comprueba que los campos se han introducido correctamente.");
                    alert.setHeaderText(null);
                    alert.showAndWait();

                }else {
                    var nombre = tfNombreNR.getText();
                    var fabricante = tfFabricanteNR.getText();
                    var material = tfMaterialNR.getText();
                    var precio = Double.parseDouble(tfPrecioNR.getText());
                    var indicadorPeligro = cbIndicadorPeligroNR.getValue().toString();
                    LocalDate fechaInicioValor = tfFechaInicioNR.getValue();
                    LocalDate fechaFinValor = tfFechaFinNR.getValue();

                    Date fechaInicio = Date.from(fechaInicioValor.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
                    Date fechaFin = Date.from(fechaFinValor.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());

                    System.out.println(nombre + " " + fabricante + " " + material + " " + precio + " " + indicadorPeligro + " " + fechaInicio + " " + fechaFin);

                    materialDAO.nuevoRegistroMaestro(nombre, fabricante, material, precio, indicadorPeligro, fechaInicio, fechaFin);

                    Stage stage = new Stage();
                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), 640, 500);
                    stage.setTitle("Inicio");
                    stage.setMinWidth(640);
                    stage.setMinHeight(500);
                    stage.setScene(scene);
                    stage.show();
                    Node source = (Node) event.getSource();
                    Stage stageActual = (Stage) source.getScene().getWindow();
                    stageActual.close();
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });

    }
}
