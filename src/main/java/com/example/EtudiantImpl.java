package com.example;
import java.util.ArrayList;
import com.example.Contrat.*;
import org.omg.CORBA.*;
import org.omg.CORBA.Object;

public class EtudiantImpl extends EtudiantPOA implements Etudiant {
    private String nom;
    private String prenom;
    private int numero;
    private ArrayList<Epreuve> epreuves = new ArrayList<>();
    private ArrayList<Livre> livres = new ArrayList<>();

    public EtudiantImpl(String nom, String prenom, int numero) {
        this.nom = nom;
        this.prenom = prenom;
        this.numero = numero;
    }

    // ajouter une épreuve avec son nom, sa note et son coefficient
    public void Ajouter_une_epreuve(String nom, double note, double coefficient) {
        System.out.println("LOL");
        Epreuve epreuve = new Epreuve(nom, note, coefficient);
        epreuves.add(epreuve);
    }

    // obtenir la liste des épreuves sous forme de tableau de chaînes
    public String[] Liste_des_epreuves() {
        return epreuves.stream()
                .map(Epreuve::toString)  // convertir chaque épreuve en chaîne
                .toArray(String[]::new);  // collecter les résultats dans un tableau
    }

    // calculer la moyenne des notes en fonction des coefficients
    public double Calculer_la_moyenne() {
        double total = 0, sommeCoefficients = 0;
        for (Epreuve epreuve : epreuves) {
            total += epreuve.getNote() * epreuve.getCoeff();
            sommeCoefficients += epreuve.getCoeff();
        }
        return sommeCoefficients > 0 ? total / sommeCoefficients : 0;
    }

    // emprunter un livre avec son nom, auteur, collection et date de publication
    public void Emprunter_un_livre(String nom, String auteur, String collection, String datePublication) {
        Livre livre = new Livre(nom, auteur, collection, datePublication);
        livres.add(livre);
    }

    // obtenir la liste des livres empruntés sous forme de tableau de chaînes
    public String[] Liste_des_livres() {
        return livres.stream()
                .map(Livre::toString)  // convertir chaque livre en chaîne
                .toArray(String[]::new);  // collecter les résultats dans un tableau
    }

    @Override
    public boolean _is_equivalent(Object other) {
        return false;
    }

    @Override
    public int _hash(int maximum) {
        return 0;
    }

    @Override
    public Object _duplicate() {
        return null;
    }

    @Override
    public void _release() {

    }

    @Override
    public Request _request(String operation) {
        return null;
    }

    @Override
    public Request _create_request(Context ctx, String operation, NVList arg_list, NamedValue result) {
        return null;
    }

    @Override
    public Request _create_request(Context ctx, String operation, NVList arg_list, NamedValue result, ExceptionList exclist, ContextList ctxlist) {
        return null;
    }

    @Override
    public Policy _get_policy(int policy_type) {
        return null;
    }

    @Override
    public DomainManager[] _get_domain_managers() {
        return new DomainManager[0];
    }

    @Override
    public Object _set_policy_override(Policy[] policies, SetOverrideType set_add) {
        return null;
    }
}

/*
description:
cette classe implémente les fonctionnalités d'un étudiant en utilisant les méthodes définies dans l'interface `EtudiantPOA`.
elle permet d'ajouter des épreuves avec leurs notes et coefficients, de calculer une moyenne pondérée,
et de gérer l'emprunt de livres. la liste des épreuves et des livres est consultable sous forme de tableaux de chaînes.
ce code est conçu pour être utilisé dans un système distribué basé sur corba.
*/
