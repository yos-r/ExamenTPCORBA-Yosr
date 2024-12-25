package com.example.Contrat;


public abstract class EtudiantPOA extends org.omg.PortableServer.Servant
 implements EtudiantOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("Ajouter_une_epreuve", new java.lang.Integer (0));
    _methods.put ("Liste_des_epreuves", new java.lang.Integer (1));
    _methods.put ("Calculer_la_moyenne", new java.lang.Integer (2));
    _methods.put ("Emprunter_un_livre", new java.lang.Integer (3));
    _methods.put ("Liste_des_livres", new java.lang.Integer (4));

  }

  public org.omg.CORBA.portable.OutputStream _invoke (String $method,
                                org.omg.CORBA.portable.InputStream in,
                                org.omg.CORBA.portable.ResponseHandler $rh)
  {
    org.omg.CORBA.portable.OutputStream out = null;
    java.lang.Integer __method = (java.lang.Integer)_methods.get ($method);
    if (__method == null)
      throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);

    switch (__method.intValue ())
    {
       case 0:  // Contract/Etudiant/Ajouter_une_epreuve
       {
         String nom = in.read_string ();
         double note = in.read_double ();
         double coefficient = in.read_double ();
         this.Ajouter_une_epreuve (nom, note, coefficient);
         out = $rh.createReply();
         break;
       }

       case 1:  // Contrat/Etudiant/Liste_des_epreuves
       {
         String $result[] = null;
         $result = this.Liste_des_epreuves ();
         out = $rh.createReply();
         StringSequenceHelper.write (out, $result);
         break;
       }

       case 2:  // Contrat/Etudiant/Calculer_la_moyenne
       {
         double $result = (double)0;
         $result = this.Calculer_la_moyenne ();
         out = $rh.createReply();
         out.write_double ($result);
         break;
       }

       case 3:  // Contrat/Etudiant/Emprunter_un_livre
       {
         String nom = in.read_string ();
         String auteur = in.read_string ();
         String collection = in.read_string ();
         String datePublication = in.read_string ();
         this.Emprunter_un_livre (nom, auteur, collection, datePublication);
         out = $rh.createReply();
         break;
       }
      case 4:  // Contrat/Etudiant/Liste_des_livres
      {
        String $result[] = null;
        $result = this.Liste_des_livres ();
        out = $rh.createReply();
        StringSequenceHelper.write (out, $result);
        break;
      }


      default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:Contrat/Etudiant:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public Etudiant _this() 
  {
    return EtudiantHelper.narrow(
    super._this_object());
  }

  public Etudiant _this(org.omg.CORBA.ORB orb) 
  {
    return EtudiantHelper.narrow(
    super._this_object(orb));
  }


} // class EtudiantPOA
