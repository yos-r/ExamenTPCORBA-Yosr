package com.example;

public class Livre {
    String nom;
    String auteur;
    String collection;
    String date;
    public Livre(String nom, String auteur, String collection, String date) {
        this.nom = nom;
        this.auteur = auteur;
        this.collection = collection;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Livre{" +
                "nom='" + nom + '\'' +
                ", auteur='" + auteur + '\'' +
                ", collection='" + collection + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
