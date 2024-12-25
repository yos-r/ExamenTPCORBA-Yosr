package com.example;
import com.example.*;
import com.example.Contrat.Etudiant;
import com.example.Contrat.EtudiantHelper;
import com.example.Contrat.Promotion;
import com.example.Contrat.PromotionHelper;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

import java.util.Arrays;

public class Client2 {
    public static void main(String[] args) {
        try {
            ORB orb = ORB.init(args, null); //init orb
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService"); // référence de l'objet depuis le serveur de nommage
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
            Promotion promotionRef = PromotionHelper.narrow(ncRef.resolve_str("Promotion")); // obtenir la reference de l'objet promotion
            // Déclaration de plusieurs étudiants
            promotionRef.ajouter_un_etudiant("A", "B", 12345);
            promotionRef.ajouter_un_etudiant("B", "A", 54321);
//            // Recherche des étudiants et ajout des emprunts de livres
            Etudiant etudiant1 = promotionRef.rechercher_un_etudiant(12345);
            Etudiant etudiant2 = promotionRef.rechercher_un_etudiant(54321);
//
            etudiant1.Emprunter_un_livre("Orientalism",  "Edward Said", "Penguin","1970");
            etudiant1.Emprunter_un_livre("Incerto",  "Nassim Nicholas Taleb", "Penguin","2003");

            etudiant1.Emprunter_un_livre("Profit Over People",  "Chomsky", "Penguin","2000");
            etudiant1.Emprunter_un_livre("Bed of Procrustes",  "Nassim Nicholas Taleb", "Penguin","2008");
//             Afficher les livres empruntes  pour chaque étudiant
            System.out.println(" livres empruntes par etudiant 1");
            System.out.println(Arrays.toString(etudiant1.Liste_des_livres()));
            System.out.println(" livres empruntes par etudiant 2");
            System.out.println(Arrays.toString(etudiant2.Liste_des_livres()));



        } catch (Exception e) {
            System.err.println("Erreur : " + e.getMessage());
            e.printStackTrace();
        }
    }
}
