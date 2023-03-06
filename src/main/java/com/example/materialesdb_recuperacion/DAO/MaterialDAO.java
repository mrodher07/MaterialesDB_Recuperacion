package com.example.materialesdb_recuperacion.DAO;

import com.example.materialesdb_recuperacion.modelos.DatosTecnicos;
import com.example.materialesdb_recuperacion.modelos.Material;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.Date;

public class MaterialDAO {

    private final String servidor = "jdbc:mariadb://localhost:5555/noinch?useSSL=false";
    private final String usuario = "adminer";
    private final String passwd = "adminer";

    private Connection conexionBBDD;

    public ObservableList<Material> obtenerTodosMateriales() {

        ObservableList<Material> resultado = FXCollections.observableArrayList();

        try {
            conexionBBDD = DriverManager.getConnection(servidor, usuario, passwd);
            String SQL = "SELECT * " +
                    "FROM materiales " +
                    "ORDER By idMaterial";

            ResultSet consulta = conexionBBDD.createStatement().executeQuery(SQL);
            while (consulta.next()) {
                resultado.add(new Material(
                        consulta.getInt("idMaterial"),
                        consulta.getString("nombreMaterial"),
                        consulta.getString("fabricante"),
                        consulta.getString("material"),
                        consulta.getDouble("precio"),
                        consulta.getString("indicadorPeligro"),
                        consulta.getString("fechaInicioVenta"),
                        consulta.getString("fechaFinVenta")
                ));
                System.out.println("Row[1] added" + consulta);
            }

            conexionBBDD.close();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            return resultado;
        }
    }

    public boolean nuevoRegistroMaestro(String nombreMaterial, String fabricante, String material, Double precio, String indicadorPeligro, Date fechaInicio, Date fechaFin){
        int registrosAfectadosConsulta = 0;
        try {
            conexionBBDD = DriverManager.getConnection(servidor, usuario, passwd);
            String SQL = "INSERT INTO materiales ("+
                    "nombreMaterial, "+
                    "fabricante, "+
                    "material, "+
                    "precio, "+
                    "indicadorPeligro, "+
                    "fechaInicioVenta, "+
                    "fechaFinVenta)"+
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";

            java.sql.Date dateInicio = new java.sql.Date(fechaInicio.getTime());
            java.sql.Date dateFin = new java.sql.Date(fechaFin.getTime());

            System.out.println(dateInicio);

            PreparedStatement st = conexionBBDD.prepareStatement(SQL);
            st.setString(1, nombreMaterial);
            st.setString(2, fabricante);
            st.setString(3, material);
            st.setDouble(4, precio);
            st.setString(5, indicadorPeligro);
            st.setDate(6, dateInicio);
            st.setDate(7, dateFin);

            registrosAfectadosConsulta = st.executeUpdate();
            st.close();
            conexionBBDD.close();

            if (registrosAfectadosConsulta == 1){
                return true;
            }else {
                return false;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Material obtenerMaterial(Integer id) {

        Material resultado = new Material();

        try {
            conexionBBDD = DriverManager.getConnection(servidor, usuario, passwd);
            String SQL = "SELECT * " +
                    "FROM materiales " +
                    "WHERE idMaterial = "+id;

            ResultSet consulta = conexionBBDD.createStatement().executeQuery(SQL);

            while (consulta.next()) {
                resultado.setNombreMaterial(consulta.getString("nombreMaterial"));
                resultado.setFabricante(consulta.getString("fabricante"));
                resultado.setMaterial(consulta.getString("material"));
                resultado.setPrecio(consulta.getDouble("precio"));
                resultado.setIndicadorPeligro(consulta.getString("indicadorPeligro"));
                resultado.setFechaInicioVenta(consulta.getString("fechaInicioVenta"));
                resultado.setFechaInicioVenta(consulta.getString("fechaInicioVenta"));
                resultado.setFechaFinVenta(consulta.getString("fechaFinVenta"));

                System.out.println("Objeto Añadido");
            }
            conexionBBDD.close();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            return resultado;
        }
    }

    public ObservableList<DatosTecnicos> obtenerDatosTecnicosMD(Integer id) {

        ObservableList<DatosTecnicos> resultado = FXCollections.observableArrayList();

        try {
            conexionBBDD = DriverManager.getConnection(servidor, usuario, passwd);
            String SQL = "SELECT * " +
                    "FROM datosTecnicos " +
                    "WHERE idMaterial = "+id;

            ResultSet consulta = conexionBBDD.createStatement().executeQuery(SQL);
            while (consulta.next()) {
                resultado.add(new DatosTecnicos(
                        consulta.getInt("idMaterial"),
                        consulta.getDouble("peso"),
                        consulta.getString("color"),
                        consulta.getString("HerramientaUso"),
                        consulta.getString("LugarUso"),
                        consulta.getString("Recipiente"),
                        consulta.getString("Composicion")
                ));
                System.out.println("Row[1] added" + consulta);
            }

            conexionBBDD.close();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            return resultado;
        }
    }

    public Material obtenerDatoTecnico(Integer id) {

        Material resultado = new Material();

        try {
            conexionBBDD = DriverManager.getConnection(servidor, usuario, passwd);
            String SQL = "SELECT * " +
                    "FROM datostecnicos " +
                    "WHERE idMaterial = "+id;

            ResultSet consulta = conexionBBDD.createStatement().executeQuery(SQL);

            while (consulta.next()) {
                resultado.setNombreMaterial(consulta.getString("nombreMaterial"));
                resultado.setFabricante(consulta.getString("fabricante"));
                resultado.setMaterial(consulta.getString("material"));
                resultado.setPrecio(consulta.getDouble("precio"));
                resultado.setIndicadorPeligro(consulta.getString("indicadorPeligro"));
                resultado.setFechaInicioVenta(consulta.getString("fechaInicioVenta"));
                resultado.setFechaInicioVenta(consulta.getString("fechaInicioVenta"));
                resultado.setFechaFinVenta(consulta.getString("fechaFinVenta"));

                System.out.println("Objeto Añadido");
            }
            conexionBBDD.close();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            return resultado;
        }
    }

    public boolean editarMaterial(int id, String nombreMaterial, String fabricante, String material, Double precio, String indicadorPeligro, Date fechaInicio, Date fechaFin){
        int registrosAfectadosConsulta = 0;
        try {
            conexionBBDD = DriverManager.getConnection(servidor, usuario, passwd);
            java.sql.Date dateInicio = new java.sql.Date(fechaInicio.getTime());
            java.sql.Date dateFin = new java.sql.Date(fechaFin.getTime());
            String SQL = "UPDATE materiales "+
                    "SET nombreMaterial='"+nombreMaterial+
                    "',fabricante='"+fabricante+
                    "',material='"+material+
                    "',precio="+precio+
                    ",indicadorPeligro='"+indicadorPeligro+
                    "',fechaInicioVenta='"+dateInicio+
                    "',fechaFinVenta='"+dateFin+
                    "' WHERE idMaterial="+id+";";



            PreparedStatement st = conexionBBDD.prepareStatement(SQL);
            registrosAfectadosConsulta = st.executeUpdate();
            st.close();
            conexionBBDD.close();

            if (registrosAfectadosConsulta == 1){
                return true;
            }else {
                return false;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public boolean editarDatoTecnico(DatosTecnicos d, DatosTecnicos dtAux){
        int registrosAfectadosConsulta = 0;
        try {
            conexionBBDD = DriverManager.getConnection(servidor, usuario, passwd);
            String SQL = "UPDATE datostecnicos "+
                    "SET peso="+dtAux.getPeso()+
                    ", color='"+dtAux.getColor()+
                    "', HerramientaUso='"+dtAux.getHerramientaUso()+
                    "', LugarUso='"+dtAux.getLugarUso()+
                    "', Recipiente='"+dtAux.getRecipiente()+
                    "', Composicion='"+dtAux.getComposicion()+
                    "' WHERE idMaterial="+d.getIdMaterial()+
                    " AND peso =" +d.getPeso()+
                    " AND color ='"+d.getColor()+
                    "' AND HerramientaUso ='"+d.getHerramientaUso()+
                    "' AND LugarUso = '" + d.getLugarUso()+
                    "' AND Recipiente = '" + d.getRecipiente()+
                    "' AND Composicion = '" + d.getComposicion()+
                    "';";



            PreparedStatement st = conexionBBDD.prepareStatement(SQL);
            registrosAfectadosConsulta = st.executeUpdate();
            st.close();
            conexionBBDD.close();

            if (registrosAfectadosConsulta == 1){
                return true;
            }else {
                return false;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public boolean nuevoDatoTecnico(DatosTecnicos dtAux) {
        int registrosAfectadosConsulta = 0;
        try {
            conexionBBDD = DriverManager.getConnection(servidor, usuario, passwd);
            String SQL = "INSERT INTO datostecnicos ("+
                    "idMaterial, "+
                    "peso, "+
                    "color, "+
                    "HerramientaUso, "+
                    "LugarUso, "+
                    "Recipiente, "+
                    "Composicion)"+
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";


            PreparedStatement st = conexionBBDD.prepareStatement(SQL);
            st.setInt(1, dtAux.getIdMaterial());
            st.setDouble(2, dtAux.getPeso());
            st.setString(3, dtAux.getColor());
            st.setString(4, dtAux.getHerramientaUso());
            st.setString(5, dtAux.getLugarUso());
            st.setString(6, dtAux.getRecipiente());
            st.setString(7, dtAux.getComposicion());

            registrosAfectadosConsulta = st.executeUpdate();
            st.close();
            conexionBBDD.close();

            if (registrosAfectadosConsulta == 1){
                return true;
            }else {
                return false;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ObservableList<Material> buscarMateriales(String nombreMaterial, String fabricante, String material) {
        ObservableList<Material> datosResultadoConsulta = FXCollections.observableArrayList();

        try {
            conexionBBDD = DriverManager.getConnection(servidor, usuario, passwd);
            //java.sql.Date dateInicio = new java.sql.Date(fechaInicio.getTime());
            //java.sql.Date dateFin = new java.sql.Date(fechaFin.getTime());

            String SQL = "SELECT * "+
                    "FROM materiales "+
                    "WHERE nombreMaterial LIKE '%" +nombreMaterial+
                    "%' OR fabricante LIKE '%" +fabricante+
                    "%' OR material LIKE '%" +material+
                    //"%' OR precio >= " +precio+
                    "';";
            //' OR indicadorPeligro LIKE '%" +indicadorPeligro+ "%'
            ResultSet resultadoConsulta = conexionBBDD.createStatement().executeQuery(SQL);

            while(resultadoConsulta.next()){
                datosResultadoConsulta.add(new Material(
                        resultadoConsulta.getInt("idMaterial"),
                        resultadoConsulta.getString("nombreMaterial"),
                        resultadoConsulta.getString("fabricante"),
                        resultadoConsulta.getString("material"),
                        resultadoConsulta.getDouble("precio"),
                        resultadoConsulta.getString("indicadorPeligro"),
                        resultadoConsulta.getString("fechaInicioVenta"),
                        resultadoConsulta.getString("fechaFinVenta")
                ));
                System.out.println("Row[1] added"+resultadoConsulta.toString());
            }
            conexionBBDD.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            return datosResultadoConsulta;
        }

    }

    }
