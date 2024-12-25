package com.example;
import com.example.*;
import com.example.Contrat.Etudiant;
import com.example.Contrat.EtudiantHelper;
import com.example.Contrat.Promotion;
import com.example.Contrat.PromotionHelper;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

public class Client {
    public static void main(String[] args) {
        try {
            ORB orb = ORB.init(args, null); //init orb
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService"); // référence de l'objet depuis le serveur de nommage
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
            Promotion promotionRef = PromotionHelper.narrow(ncRef.resolve_str("Promotion")); // obtenir la reference de l'objet promotion
            // Déclaration de plusieurs étudiants
            promotionRef.ajouter_un_etudiant("A", "B", 12345);
            promotionRef.ajouter_un_etudiant("B", "A", 54321);
//            // Recherche des étudiants et ajout des épreuves
            Etudiant etudiant1 = promotionRef.rechercher_un_etudiant(12345);
            Etudiant etudiant2 = promotionRef.rechercher_un_etudiant(54321);
//
            etudiant1.Ajouter_une_epreuve("Ondes", 16, 1);
            etudiant1.Ajouter_une_epreuve("Telecom", 18, 2);

            etudiant2.Ajouter_une_epreuve("Ondes", 12, 1);
            etudiant2.Ajouter_une_epreuve("Telecom", 12, 2);
//             Afficher les résultats pour chaque étudiant

            System.out.println("\nÉpreuves de  Etudiant 1:");
            for (String epreuve : etudiant1.Liste_des_epreuves()) {
                System.out.println("- " + epreuve);
            }
            System.out.println("Moyenne de Etudiant 1 : " + etudiant1.Calculer_la_moyenne());

            System.out.println("\nÉpreuves de  Etudiant 2:");
            for (String epreuve : etudiant2.Liste_des_epreuves()) {
                System.out.println("- " + epreuve);
            }
            System.out.println("Moyenne de Etudiant 2 : " + etudiant2.Calculer_la_moyenne());

            System.out.println("Moyenne de la promotion : " + promotionRef.calculer_moyenne_de_la_promotion());


        } catch (Exception e) {
            System.err.println("Erreur : " + e.getMessage());
            e.printStackTrace();
        }
    }
}
