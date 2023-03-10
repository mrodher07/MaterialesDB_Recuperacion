package com.example.materialesdb_recuperacion;

import com.example.materialesdb_recuperacion.DAO.MaterialDAO;
import com.example.materialesdb_recuperacion.modelos.Material;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class HelloController {
    @FXML
    private Label botonVolverInicio;
    @FXML
    private Button cabeceraNuevoRegistro;
    @FXML
    private TextField inputNombre;
    @FXML
    private TextField inputFabricante;
    @FXML
    private TextField inputMaterial;
    @FXML
    private ComboBox cbPeligro;
    @FXML
    private DatePicker fechaInicioVenta;
    @FXML
    private DatePicker fechaFinVenta;
    @FXML
    private Button botonBuscar;
    @FXML
    private TextField inputPrecio;
    @FXML
    private TableView tvMateriales;
    @FXML
    private TableColumn tcIDMaterial;
    @FXML
    private TableColumn tcNombreMaterial;
    @FXML
    private TableColumn tcFabricante;
    @FXML
    private TableColumn tcMaterial;
    @FXML
    private TableColumn tcPrecio;
    @FXML
    private TableColumn tcIndicadorPeligro;
    @FXML
    private TableColumn tcFechaInicioVenta;
    @FXML
    private TableColumn tcFechaFinVenta;

    private Material materialAux;
    private MaterialDAO materialDAO = new MaterialDAO();
    private ObservableList<Material> datos;

    Material m = Material.getInstance();

    @FXML
    protected void initialize(){
        cargarDatosIniciales();
        ObservableList<String> olOptions = FXCollections.observableArrayList(
                "A",
                "B",
                "C",
                "D",
                "E"
        );
        cbPeligro.setItems(olOptions);
        cargarDobleClickMD();
        cargarHandlers();
    }

    private void cargarDatosIniciales() {
        Platform.runLater(()->{
            datos = materialDAO.obtenerTodosMateriales();
            tcIDMaterial.setCellValueFactory(new PropertyValueFactory<Material, Integer>("idMaterial"));
            tcNombreMaterial.setCellValueFactory(new PropertyValueFactory<Material, String>("nombreMaterial"));
            tcFabricante.setCellValueFactory(new PropertyValueFactory<Material, String>("fabricante"));
            tcMaterial.setCellValueFactory(new PropertyValueFactory<Material, String>("material"));
            tcPrecio.setCellValueFactory(new PropertyValueFactory<Material, Double>("precio"));
            tcIndicadorPeligro.setCellValueFactory(new PropertyValueFactory<Material, String>("indicadorPeligro"));
            tcFechaInicioVenta.setCellValueFactory(new PropertyValueFactory<Material, String>("fechaInicioVenta"));
            tcFechaFinVenta.setCellValueFactory(new PropertyValueFactory<Material, String>("fechaFinVenta"));
            tvMateriales.setItems(datos);
        });
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

        cabeceraNuevoRegistro.setOnMouseClicked((EventHandler) event -> {
            try {
                Stage stage = new Stage();
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("nuevoRegistroMaestroView.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 640, 500);
                stage.setTitle("Nuevo Registro");
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

        botonBuscar.setOnMouseClicked((EventHandler) event -> {

            var nombreMaterial = inputNombre.getText().trim();
            var fabricante = inputFabricante.getText().trim();
            var material = inputMaterial.getText().trim();
            double precio;
            if(inputPrecio.getText().isEmpty()){
                precio = 0.00;
            }else{
                precio = Double.parseDouble(inputPrecio.getText());
            }
            String indicadorPeligro;
            if(cbPeligro.getValue() == null){
                indicadorPeligro = "";
            }else{
                indicadorPeligro = cbPeligro.getValue().toString();
            }

            /*LocalDate fechaInicioValor = fechaInicioVenta.getValue();
            LocalDate fechaFinValor = fechaFinVenta.getValue();
            Date fechaInicio = Date.from(fechaInicioValor.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
            Date fechaFin = Date.from(fechaFinValor.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());*/

            Platform.runLater(()->{
                datos = materialDAO.buscarMateriales(nombreMaterial, fabricante, material, precio, indicadorPeligro);
                tcIDMaterial.setCellValueFactory(new PropertyValueFactory<Material, Integer>("idMaterial"));
                tcNombreMaterial.setCellValueFactory(new PropertyValueFactory<Material, String>("nombreMaterial"));
                tcFabricante.setCellValueFactory(new PropertyValueFactory<Material, String>("fabricante"));
                tcMaterial.setCellValueFactory(new PropertyValueFactory<Material, String>("material"));
                tcPrecio.setCellValueFactory(new PropertyValueFactory<Material, Double>("precio"));
                tcIndicadorPeligro.setCellValueFactory(new PropertyValueFactory<Material, String>("indicadorPeligro"));
                tcFechaInicioVenta.setCellValueFactory(new PropertyValueFactory<Material, String>("fechaInicioVenta"));
                tcFechaFinVenta.setCellValueFactory(new PropertyValueFactory<Material, String>("fechaFinVenta"));
                tvMateriales.setItems(datos);
            });
        });
    }

    private void cargarDobleClickMD(){
        tvMateriales.setRowFactory(tv->{
            TableRow<Material> row = new TableRow<>();
            row.setOnMouseClicked(event->{
                if(event.getClickCount()==2 && (!row.isEmpty())){
                    try {
                        m.setIdMaterial(row.getItem().getIdMaterial());
                        Stage stage = new Stage();
                        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("maestroDetalleView.fxml"));
                        Scene scene = new Scene(fxmlLoader.load(), 640, 500);
                        stage.setTitle("Maestro Detalle");
                        stage.setMinWidth(640);
                        stage.setMinHeight(500);
                        stage.setScene(scene);
                        stage.show();
                        Node node = (Node) event.getSource();
                        Stage stageActual = (Stage) node.getScene().getWindow();
                        stageActual.close();

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                }
            });
            return row;
        });
    }

}