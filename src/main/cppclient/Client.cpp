#include <iostream>
#include "Contrat.hh"
#include <omniORB4/CORBA.h>
#include <omniORB4/Naming.hh>

int main(int argc, char* argv[]) {
    try {
        // Initialisation de l'ORB (Object Request Broker)
        CORBA::ORB_ptr orb = CORBA::ORB_init(argc, argv);

        // Résolution de la référence initiale du service de nommage
        CORBA::Object_var obj = orb->resolve_initial_references("NameService");
        CosNaming::NamingContext_var namingContext = CosNaming::NamingContext::_narrow(obj.in());

        // Résolution du nom "Promotion" dans le service de nommage
        CosNaming::Name name(1);
        name.length(1);
        name[0].id = CORBA::string_dup("Promotion");
        CORBA::Object_var obj_ref = namingContext->resolve(name);
        
        // Récupération de l'objet "Promotion" via un narrowing
        Contrat::Promotion_var promotion = Contrat::Promotion::_narrow(obj_ref.in());

        // Ajout d'étudiants à la promotion
        promotion->ajouter_un_etudiant("A", "B", 123);
        Contrat::Etudiant_var etudiant1 = promotion->rechercher_un_etudiant(123);

        // Ajout d'épreuves pour l'étudiant
        etudiant1->ajouter_une_epreuve("Physique", 15.0, 1.5);
        etudiant1->ajouter_une_epreuve("Ondes", 15.0, 1.5);

        // Calcul et affichage de la moyenne de l'étudiant
        std::cout << "Moyenne etudiant 1: " << etudiant1->calculer_la_moyenne() << std::endl;

        // Recherche et ajout d'épreuves pour un autre étudiant (identique à l'étudiant1)
        Contrat::Etudiant_var etudiant2 = promotion->rechercher_un_etudiant(123);
        etudiant2->ajouter_une_epreuve("Physique", 16.0, 1.5);
        etudiant2->ajouter_une_epreuve("Ondes", 14.0, 1.5);

        // Calcul et affichage de la moyenne de l'étudiant 2
        std::cout << "Moyenne etudiant 2: " << etudiant2->calculer_la_moyenne() << std::endl;

        // Calcul et affichage de la moyenne de la promotion
        std::cout << "Moyenne de la promotion : " << promotion->calculer_moyenne_de_la_promotion() << std::endl;

        // Destruction de l'ORB
        orb->destroy();
    } catch (const CORBA::Exception& ex) {
        // Gestion des exceptions CORBA
        std::cerr << "CORBA exception: " << ex._name() << std::endl;
    }
}
