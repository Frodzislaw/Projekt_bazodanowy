package com.example.projekt;

import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;

public class produkt {

    private Integer id_produktu;
    private String nazwa;
    private Double cena;
    private String kategoria;
    private Integer ilosc_na_stanie;
    private String producent;
    private ComboBox ilosc_produktow;

    public produkt(int id_produktu, String nazwa, double cena, String kategoria, Integer ilosc_na_stanie, String producent) {
        this.id_produktu = id_produktu;
        this.nazwa = nazwa;
        this.cena = cena;
        this.kategoria = kategoria;
        this.ilosc_na_stanie = ilosc_na_stanie;
        this.producent = producent;
        this.ilosc_produktow = new ComboBox<>(FXCollections.observableArrayList(0,1,2, 3, 6 ,22));
        ilosc_produktow.setValue(0);
    }

    public int getId_produktu() {
        return id_produktu;
    }

    public void setId_produktu(int id_produktu) {
        this.id_produktu = id_produktu;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public String getKategoria() {
        return kategoria;
    }

    public void setKategoria(String kategoria) {
        this.kategoria = kategoria;
    }

    public Integer getIlosc_na_stanie() {
        return ilosc_na_stanie;
    }

    public void setIlosc_na_stanie(Integer ilosc_na_stanie) {
        this.ilosc_na_stanie = ilosc_na_stanie;
    }

    public String getProducent() {
        return producent;
    }

    public void setProducent(String producent) {
        this.producent = producent;
    }

    public ComboBox getIlosc_produktow() {
        return ilosc_produktow;
    }

    public void setIlosc_produktow(ComboBox ilosc_produktow) {
        this.ilosc_produktow = ilosc_produktow;
    }

    @Override
    public String toString() {
        return "id_produktu=" + id_produktu +
                ", nazwa='" + nazwa + '\'' +
                ", cena=" + cena +
                ", kategoria='" + kategoria + '\'' +
                ", ilosc_na_stanie=" + ilosc_na_stanie +
                ", producent='" + producent + '\''+"Wybrana liosc: " + ilosc_produktow.getValue();

    }
}
