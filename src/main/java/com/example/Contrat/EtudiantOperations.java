package com.example.Contrat;


/**
* Contrat/EtudiantOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Contrat.idl
* Wednesday, December 25, 2024 5:37:51 PM WAT
*/

public interface EtudiantOperations 
{
  void Ajouter_une_epreuve (String nom, double note, double coefficient);
  String[] Liste_des_epreuves ();
  double Calculer_la_moyenne ();
  void Emprunter_un_livre (String nom, String auteur, String collection, String datePublication);
  String[] Liste_des_livres ();

} // interface EtudiantOperations
