package com.example;

import org.omg.CORBA.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import com.example.Contrat.*;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import java.util.Enumeration;

// Classe PromotionImpl qui permet de gérer et publier des étudiants dans le service de nommage
class PromotionImpl extends PromotionPOA {
    private NamingContextExt namingContext;

    public PromotionImpl(NamingContextExt namingContext) {
        this.namingContext = namingContext;
    }

    // Ajouter un étudiant et le publier dans le Naming Service
    public void ajouter_un_etudiant(String nom, String prenom, int numero) {
        try {
            ORB orb = ORB.init((String[]) null, null);
            // Résolution de la référence à la RootPOA
            POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootpoa.the_POAManager().activate();

            // Créer l'objet EtudiantImpl
            EtudiantImpl etudiantImpl = new EtudiantImpl(nom, prenom, numero);

            org.omg.CORBA.Object ref = rootpoa.servant_to_reference(etudiantImpl);
            Etudiant href = EtudiantHelper.narrow(ref); // Référence CORBA de l'objet Etudiant

            // Résolution du service de nommage
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            // Publier l'objet Etudiant dans le service de nommage
            NameComponent[] path = ncRef.to_name(String.valueOf(numero));
            ncRef.rebind(path, href);
            System.out.println("Étudiant avec nom " + nom + " " + numero + " est enregistré dans le Naming Service.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Rechercher un étudiant par son numéro (via le Naming Service)
    public Etudiant rechercher_un_etudiant(int numero) {
        try {
            ORB orb = ORB.init((String[]) null, null); // Init ORB
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService"); // Référence de l'objet depuis le serveur de nommage
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
            Etudiant student = EtudiantHelper.narrow(ncRef.resolve_str(String.valueOf(numero)));

            return student;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    // Calculer la moyenne de la promotion
    public double calculer_moyenne_de_la_promotion() {
        double sommeMoyennes = 0;
        int nombreEtudiants = 0;

        try {
            // parcourir le Naming Service
            BindingListHolder bindingListHolder = new BindingListHolder();
            BindingIteratorHolder bindingIteratorHolder = new BindingIteratorHolder();
            namingContext.list(1000, bindingListHolder, bindingIteratorHolder);

            for (Binding binding : bindingListHolder.value) {
                try {
                    // Résolution de chaque objet dans le Naming Service
                    org.omg.CORBA.Object obj = namingContext.resolve(binding.binding_name);

                    // Vérification si l'objet est de type Etudiant
                    Etudiant etudiant = EtudiantHelper.narrow(obj);
                    if (etudiant != null) {
                        // Calculer la moyenne de chaque étudiant via Calculer_la_moyenne()
                        sommeMoyennes += etudiant.Calculer_la_moyenne();
                        nombreEtudiants++;
                    }
                } catch (Exception e) {
                    System.err.println("Erreur lors de la récupération d'un étudiant : " + e.getMessage());
                }
            }

            if (nombreEtudiants > 0) {
                return sommeMoyennes / nombreEtudiants;
            } else {
                System.out.println("Aucun étudiant trouvé dans le Naming Service.");
                return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

}


