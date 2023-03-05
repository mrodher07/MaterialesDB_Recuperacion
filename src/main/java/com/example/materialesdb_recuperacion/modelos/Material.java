package com.example.materialesdb_recuperacion.modelos;

public class Material {
    private static final Material instance = new Material();
    private Integer idMaterial;
    private String nombreMaterial;
    private String fabricante;
    private String material;
    private Double precio;
    private String indicadorPeligro;
    private String fechaInicioVenta;
    private String fechaFinVenta;

    public Material(Integer idMaterial, String nombreMaterial, String fabricante, String material, Double precio, String indicadorPeligro, String fechaInicioVenta, String fechaFinVenta) {
        this.idMaterial = idMaterial;
        this.nombreMaterial = nombreMaterial;
        this.fabricante = fabricante;
        this.material = material;
        this.precio = precio;
        this.indicadorPeligro = indicadorPeligro;
        this.fechaInicioVenta = fechaInicioVenta;
        this.fechaFinVenta = fechaFinVenta;
    }

    public Material() {
        super();
    }

    public static Material getInstance() {
        return instance;
    }

    public void setIdMaterial(Integer idMaterial) {
        this.idMaterial = idMaterial;
    }
    public Integer getIdMaterial() {
        return idMaterial;
    }
    public void setNombreMaterial(String nombreMaterial) {
        this.nombreMaterial = nombreMaterial;
    }
    public String getNombreMaterial() {
        return nombreMaterial;
    }
    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }
    public String getFabricante() {
        return fabricante;
    }
    public void setMaterial(String material) {
        this.material = material;
    }
    public String getMaterial() {
        return material;
    }
    public void setPrecio(Double precio) {
        this.precio = precio;
    }
    public Double getPrecio() {
        return precio;
    }
    public void setIndicadorPeligro(String indicadorPeligro) {
        this.indicadorPeligro = indicadorPeligro;
    }
    public String getIndicadorPeligro() {
        return indicadorPeligro;
    }
    public void setFechaInicioVenta(String FechaInicioVenta) {
        this.fechaInicioVenta = FechaInicioVenta;
    }
    public String getFechaInicioVenta() {
        return fechaInicioVenta;
    }
    public void setFechaFinVenta(String FechaFinVenta) {
        this.fechaFinVenta = FechaFinVenta;
    }
    public String getFechaFinVenta() {
        return fechaFinVenta;
    }

    @Override
    public String toString() {
        return "Material{" +
                "idMaterial=" + idMaterial +
                ", nombreMaterial='" + nombreMaterial + '\'' +
                ", fabricante='" + fabricante + '\'' +
                ", material='" + material + '\'' +
                ", precio=" + precio +
                ", indicadorPeligro='" + indicadorPeligro + '\'' +
                ", fechaInicioVenta='" + fechaInicioVenta + '\'' +
                ", fechaFinVenta='" + fechaFinVenta + '\'' +
                '}';
    }
}
