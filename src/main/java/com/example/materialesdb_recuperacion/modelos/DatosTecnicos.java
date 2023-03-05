package com.example.materialesdb_recuperacion.modelos;

public class DatosTecnicos {

    private static final DatosTecnicos instance = new DatosTecnicos();
    private Integer idMaterial;
    private Double peso;
    private String color;
    private String herramientaUso;
    private String lugarUso;
    private String recipiente;
    private String composicion;

    public DatosTecnicos(Integer idMaterial, Double peso, String color, String herramientaUso, String lugarUso, String recipiente, String composicion) {
        this.idMaterial = idMaterial;
        this.peso = peso;
        this.color = color;
        this.herramientaUso = herramientaUso;
        this.lugarUso = lugarUso;
        this.recipiente = recipiente;
        this.composicion = composicion;
    }
    public DatosTecnicos() {
        super();
    }

    public static DatosTecnicos getInstance() {
        return instance;
    }

    public Integer getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(Integer idMaterial) {
        this.idMaterial = idMaterial;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getHerramientaUso() {
        return herramientaUso;
    }

    public void setHerramientaUso(String herramientaUso) {
        this.herramientaUso = herramientaUso;
    }

    public String getLugarUso() {
        return lugarUso;
    }

    public void setLugarUso(String lugarUso) {
        this.lugarUso = lugarUso;
    }

    public String getRecipiente() {
        return recipiente;
    }

    public void setRecipiente(String recipiente) {
        this.recipiente = recipiente;
    }

    public String getComposicion() {
        return composicion;
    }

    public void setComposicion(String composicion) {
        this.composicion = composicion;
    }

}
