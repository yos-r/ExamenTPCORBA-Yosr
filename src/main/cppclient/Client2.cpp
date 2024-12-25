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

        // Ajout d'emprunts de livres pour l'étudiant
        etudiant1->Emprunter_un_livre("Physique Quantique", "Feynman", "AB","1980");
        etudiant1->Emprunter_un_livre("Chimie", "Niels Bohr", "ABC","1905");


        // afficher livres emrptunes 
        std::cout << "Livres emprunt'es par etudiant 1 " << etudiant1->Liste_des_livres() << std::endl;

        
        // Destruction de l'ORB
        orb->destroy();
    } catch (const CORBA::Exception& ex) {
        // Gestion des exceptions CORBA
        std::cerr << "CORBA exception: " << ex._name() << std::endl;
    }
}
