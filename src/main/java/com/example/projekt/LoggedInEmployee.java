package com.example.projekt;

public class LoggedInEmployee {

    private final Integer id_pracownika;
    private final String imie;
    private final String nazwisko;
    private final Integer id_konta;
    private final String rola;

    public LoggedInEmployee(Integer id_pracownika, String imie, String nazwisko, Integer id_konta, String rola) {
        this.id_pracownika = id_pracownika;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.id_konta = id_konta;
        this.rola = rola;
    }

    public Integer getId_pracownika() {
        return id_pracownika;
    }

    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public Integer getId_konta() {
        return id_konta;
    }

    public String getRola() {
        return rola;
    }
}
