package com.example;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.*;
import org.omg.PortableServer.POA;
import com.example.Contrat.*;  // import des classes générées par idlj

// serveur etudiant qui publie l'objet distant dans le service de nommage
public class EtudiantServeur {
    public static void main(String[] args) {
        try {
            // initialisation de l'orb
            ORB orb = ORB.init(args, null);
            // résolution de la référence à la rootpoa
            POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootpoa.the_POAManager().activate();

            // création d'une instance de l'objet etudiant
            EtudiantImpl etudiantImpl = new EtudiantImpl("John", "Doe", 12345);

            // conversion de l'objet servant en référence corba
            org.omg.CORBA.Object ref = rootpoa.servant_to_reference(etudiantImpl);
            Etudiant href = EtudiantHelper.narrow(ref); // référence corba de l'objet etudiant

            // résolution du service de nommage
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            // publier l'objet etudiant dans le service de nommage
            NameComponent[] path = ncRef.to_name("Etudiant");
            ncRef.rebind(path, href);

            System.out.println("serveur etudiant actif");

            // lancer l'orb pour écouter les requêtes
            orb.run();
        } catch (Exception e) {
            System.err.println("erreur serveur : " + e.getMessage());
            e.printStackTrace();
        }
    }
}

/*
description:
ce programme est un serveur qui publie un objet `etudiant` dans un service de nommage corba.
il initialise l'orb, crée une instance de l'objet `etudiant`, convertit cette instance en référence corba,
et l'enregistre sous un chemin ("etudiant") dans le service de nommage. le serveur reste ensuite actif pour
répondre aux requêtes des clients via l'orb. conçu pour les applications distribuées basées sur corba.
*/
