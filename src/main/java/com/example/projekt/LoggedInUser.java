package com.example.projekt;
public class LoggedInUser {
    private final int id;
    private final String nazwa_firmy;
    private final String adres;
    private final String nip;

    public LoggedInUser(int id, String nazwa_firmy, String adres, String nip) {
        this.id = id;
        this.nazwa_firmy = nazwa_firmy;
        this.adres = adres;
        this.nip = nip;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return nazwa_firmy;
    }

    public String getRole() {
        return adres;
    }
    public String getnip() {
        return nip;
    }
}

