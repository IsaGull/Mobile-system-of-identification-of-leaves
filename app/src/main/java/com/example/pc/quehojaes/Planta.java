package com.example.pc.quehojaes;

/**
 * Created by Pc on 01/04/2016.
 */
public class Planta {

    String nombreCientifico;
    String familia;
    String usos;
    String clima;
    String tipoNervadura;
    String forma;
    String borde;
    String descripcionGeneral;

    public Planta()
    {


    }

    public Planta(String nombreCientifico, String familia, String usos, String clima, String tipoNervadura, String forma, String borde, String descripcionGeneral) {
        this.nombreCientifico = nombreCientifico;
        this.familia = familia;
        this.usos = usos;
        this.clima = clima;
        this.tipoNervadura = tipoNervadura;
        this.forma = forma;
        this.borde = borde;
        this.descripcionGeneral = descripcionGeneral;
    }

    public String getNombreCientifico() {
        return nombreCientifico;
    }

    public void setNombreCientifico(String nombreCientifico) {
        this.nombreCientifico = nombreCientifico;
    }

    public String getFamilia() {
        return familia;
    }

    public void setFamilia(String familia) {
        this.familia = familia;
    }

    public String getUsos() {
        return usos;
    }

    public void setUsos(String usos) {
        this.usos = usos;
    }

    public String getClima() {
        return clima;
    }

    public void setClima(String clima) {
        this.clima = clima;
    }

    public String getTipoNervadura() {
        return tipoNervadura;
    }

    public void setTipoNervadura(String tipoNervadura) {
        this.tipoNervadura = tipoNervadura;
    }

    public String getForma() {
        return forma;
    }

    public void setForma(String forma) {
        this.forma = forma;
    }

    public String getBorde() {
        return borde;
    }

    public void setBorde(String borde) {
        this.borde = borde;
    }

    public String getDescripcionGeneral() {
        return descripcionGeneral;
    }

    public void setDescripcionGeneral(String descripcionGeneral) {
        this.descripcionGeneral = descripcionGeneral;
    }
}
