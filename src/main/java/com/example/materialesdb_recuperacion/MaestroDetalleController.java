package com.example.materialesdb_recuperacion;

import com.example.materialesdb_recuperacion.DAO.MaterialDAO;
import com.example.materialesdb_recuperacion.modelos.DatosTecnicos;
import com.example.materialesdb_recuperacion.modelos.Material;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class MaestroDetalleController {
    @javafx.fxml.FXML
    private Label botonVolverInicio;
    @javafx.fxml.FXML
    private Button cabeceraNuevoDetalle;
    @javafx.fxml.FXML
    private Button botonEditarMaestro;
    @javafx.fxml.FXML
    private Label nombreMaterialMD;
    @javafx.fxml.FXML
    private Label FabricanteMD;
    @javafx.fxml.FXML
    private Label MaterialMD;
    @javafx.fxml.FXML
    private Label PrecioMD;
    @javafx.fxml.FXML
    private Label IndicadorPeligroMD;
    @javafx.fxml.FXML
    private Label FechaInicioVentaMD;
    @javafx.fxml.FXML
    private Label FechaFinVentaMD;
    @javafx.fxml.FXML
    private TableColumn tcPeso;
    @javafx.fxml.FXML
    private TableColumn tcColor;
    @javafx.fxml.FXML
    private TableColumn tcHerramienta;
    @javafx.fxml.FXML
    private TableColumn tcLugarUso;
    @javafx.fxml.FXML
    private TableColumn tcRecipiente;
    @javafx.fxml.FXML
    private TableColumn tcComposicion;

    private Material materialAux;
    private MaterialDAO materialDAO = new MaterialDAO();
    private ObservableList<DatosTecnicos> datos;
    Material m = Material.getInstance();
    DatosTecnicos d = DatosTecnicos.getInstance();
    @FXML
    private TableView tvDatosTecnicos;
    @FXML
    private TableColumn tcIdMaterialMD;
    @FXML
    private Button btnAtras;

    @FXML
    protected void initialize(){

        cargarDatosIniciales();

        cargarHandlers();
    }

    private void cargarDatosIniciales() {
        int id = m.getIdMaterial();
        materialAux = materialDAO.obtenerMaterial(id);

        nombreMaterialMD.setText(materialAux.getNombreMaterial());
        FabricanteMD.setText(materialAux.getFabricante());
        MaterialMD.setText(materialAux.getMaterial());
        PrecioMD.setText(String.valueOf(materialAux.getPrecio()));
        IndicadorPeligroMD.setText(materialAux.getIndicadorPeligro());
        FechaInicioVentaMD.setText(materialAux.getFechaInicioVenta());
        FechaFinVentaMD.setText(materialAux.getFechaFinVenta());

        datos = materialDAO.obtenerDatosTecnicosMD(id);
        tcIdMaterialMD.setCellValueFactory(new PropertyValueFactory<Material, Integer>("idMaterial"));
        tcPeso.setCellValueFactory(new PropertyValueFactory<Material, Double>("peso"));
        tcColor.setCellValueFactory(new PropertyValueFactory<Material, String>("color"));
        tcHerramienta.setCellValueFactory(new PropertyValueFactory<Material, String>("HerramientaUso"));
        tcLugarUso.setCellValueFactory(new PropertyValueFactory<Material, String>("LugarUso"));
        tcRecipiente.setCellValueFactory(new PropertyValueFactory<Material, String>("Recipiente"));
        tcComposicion.setCellValueFactory(new PropertyValueFactory<Material, String>("Composicion"));
        tvDatosTecnicos.setItems(datos);

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

        botonEditarMaestro.setOnMouseClicked(event->{
                try {
                    m.setIdMaterial(m.getIdMaterial());
                    m.setNombreMaterial(nombreMaterialMD.getText());
                    m.setFabricante(FabricanteMD.getText());
                    m.setMaterial(MaterialMD.getText());
                    m.setPrecio(Double.parseDouble(PrecioMD.getText()));
                    m.setIndicadorPeligro(IndicadorPeligroMD.getText());
                    m.setFechaInicioVenta(FechaInicioVentaMD.getText());
                    m.setFechaFinVenta(FechaFinVentaMD.getText());
                    Stage stage = new Stage();
                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("editarMaestroView.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), 640, 500);
                    stage.setTitle("Editar Maestro");
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
        });

        cabeceraNuevoDetalle.setOnMouseClicked((EventHandler) event -> {
            try {
                d.setIdMaterial(m.getIdMaterial());
                Stage stage = new Stage();
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("nuevoDetalleView.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 640, 500);
                stage.setTitle("Nuevo Detalle");
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

        btnAtras.setOnMouseClicked((EventHandler) event -> {
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

        cargarDobleClickDetalle();
    }

    private void cargarDobleClickDetalle(){
        tvDatosTecnicos.setRowFactory(tv->{
            TableRow<DatosTecnicos> row = new TableRow<>();
            row.setOnMouseClicked(event->{
                if(event.getClickCount()==2 && (!row.isEmpty())){
                    try {
                        d.setIdMaterial(row.getItem().getIdMaterial());
                        d.setPeso(row.getItem().getPeso());
                        d.setColor(row.getItem().getColor());
                        d.setHerramientaUso(row.getItem().getHerramientaUso());
                        d.setLugarUso(row.getItem().getLugarUso());
                        d.setRecipiente(row.getItem().getRecipiente());
                        d.setComposicion(row.getItem().getComposicion());
                        Stage stage = new Stage();
                        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("editarDetalleView.fxml"));
                        Scene scene = new Scene(fxmlLoader.load(), 640, 500);
                        stage.setTitle("Editar Detalle");
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
