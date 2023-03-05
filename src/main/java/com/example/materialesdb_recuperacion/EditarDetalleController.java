package com.example.materialesdb_recuperacion;

import com.example.materialesdb_recuperacion.DAO.MaterialDAO;
import com.example.materialesdb_recuperacion.modelos.DatosTecnicos;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class EditarDetalleController {
    @javafx.fxml.FXML
    private GridPane gridPaneCuerpo;
    @javafx.fxml.FXML
    private Label botonVolverInicio;
    @javafx.fxml.FXML
    private TextField tfPesoNR;
    @javafx.fxml.FXML
    private TextField tfColorNR;
    @javafx.fxml.FXML
    private TextField tfHerramientaNR;
    @javafx.fxml.FXML
    private Button botonEditarRegistro;
    @javafx.fxml.FXML
    private TextField tfLugarNR;
    @javafx.fxml.FXML
    private TextField tfRecipienteNR;
    @javafx.fxml.FXML
    private TextField tfComposicionNR;

    private MaterialDAO materialDAO = new MaterialDAO();
    DatosTecnicos d = DatosTecnicos.getInstance();
    @FXML
    protected void initialize(){

        cargarDatos();

        cargarHandlers();
    }

    private void cargarDatos(){

        tfPesoNR.setText(String.valueOf(d.getPeso()));
        tfColorNR.setText(d.getColor());
        tfHerramientaNR.setText(d.getHerramientaUso());
        tfLugarNR.setText(d.getLugarUso());
        tfRecipienteNR.setText(d.getRecipiente());
        tfComposicionNR.setText(d.getComposicion());
    }

    private void cargarHandlers() {
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

        botonEditarRegistro.setOnMouseClicked((EventHandler) event -> {
            try {
                Alert alert;
                if(tfComposicionNR.getText().trim().equals("") || tfRecipienteNR.getText().trim().equals("") || tfLugarNR.getText().trim().equals("") || tfColorNR.getText().trim().equals("") || tfHerramientaNR.getText().trim().equals("") || tfPesoNR.getText().trim().equals("")){

                    alert = new Alert(Alert.AlertType.INFORMATION, "Comprueba que los campos se han introducido correctamente.");
                    alert.setHeaderText(null);
                    alert.showAndWait();

                }else {
                    DatosTecnicos dtAux = new DatosTecnicos();
                    dtAux.setIdMaterial(d.getIdMaterial());
                    dtAux.setColor(tfColorNR.getText());
                    dtAux.setPeso(Double.valueOf(tfPesoNR.getText()));
                    dtAux.setHerramientaUso(tfHerramientaNR.getText());
                    dtAux.setLugarUso(tfLugarNR.getText());
                    dtAux.setRecipiente(tfRecipienteNR.getText());
                    dtAux.setComposicion(tfComposicionNR.getText());

                    materialDAO.editarDatoTecnico(d, dtAux);

                    Stage stage = new Stage();
                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("maestroDetalleView.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), 640, 500);
                    stage.setTitle("Maestro Detalle");
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
