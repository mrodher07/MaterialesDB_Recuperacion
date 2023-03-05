module com.example.materialesdb_recuperacion {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.mariadb.jdbc;


    opens com.example.materialesdb_recuperacion to javafx.fxml;
    exports com.example.materialesdb_recuperacion;
    exports com.example.materialesdb_recuperacion.DAO;
    exports com.example.materialesdb_recuperacion.modelos;
}