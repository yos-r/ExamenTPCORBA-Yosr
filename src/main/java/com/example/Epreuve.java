package com.example;

public class Epreuve {
    private String nom;
    private double note;
    private double coeff;
    public Epreuve(String nom, double note, double coeff) {
        this.nom = nom;
        this.note = note;
        this.coeff = coeff;
    }

    @Override
    public String toString() {
        return "Epreuve{" +
                "nom='" + nom + '\'' +
                ", note=" + note +
                ", coeff=" + coeff +
                '}';
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getNote() {
        return note;
    }

    public void setNote(double note) {
        this.note = note;
    }

    public double getCoeff() {
        return coeff;
    }

    public void setCoeff(double coeff) {
        this.coeff = coeff;
    }
}
