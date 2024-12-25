package com.example;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.*;
import org.omg.PortableServer.POA;
import com.example.Contrat.*;
// Serveur Promotion qui publie l'objet distant dans le service de nommage
public class PromotionServeur {
    public static void main(String[] args) {
        try {
            ORB orb = ORB.init(args, null); //init
            POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));//active poa
            rootpoa.the_POAManager().activate();
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService"); // référence de l'objet depuis le serveur de nommage
            NamingContextExt ncRef1 = NamingContextExtHelper.narrow(objRef);
            PromotionImpl promotionImpl = new PromotionImpl(ncRef1); //instance d'un objet promotion
            org.omg.CORBA.Object ref = rootpoa.servant_to_reference(promotionImpl); //enregistrement de l'objet promotion
            Promotion href = PromotionHelper.narrow(ref);

            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            NameComponent[] path = ncRef.to_name("Promotion");// publication de l'objet dans le servce de nommage
            ncRef.rebind(path, href);

            System.out.println("Serveur Promotion actif");

            orb.run(); // serveur en attente des requests
        } catch (Exception e) {
            // gesttion des exception
            System.err.println("Erreur serveur : " + e.getMessage());
            e.printStackTrace();
        }
    }
}
