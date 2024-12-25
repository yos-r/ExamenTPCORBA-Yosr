package com.example.Contrat;




public abstract class PromotionPOA extends org.omg.PortableServer.Servant
 implements PromotionOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("ajouter_un_etudiant", new java.lang.Integer (0));
    _methods.put ("rechercher_un_etudiant", new java.lang.Integer (1));
    _methods.put ("calculer_moyenne_de_la_promotion", new java.lang.Integer (2));
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
       case 0:  // Contrat/Promotion/ajouter_un_etudiant
       {
         String nom = in.read_string ();
         String prenom = in.read_string ();
         int numero = in.read_long ();
         this.ajouter_un_etudiant (nom, prenom, numero);
         out = $rh.createReply();
         break;
       }

       case 1:  // Contrat/Promotion/rechercher_un_etudiant
       {
         int numero = in.read_long ();
         Etudiant $result = null;
         $result = this.rechercher_un_etudiant (numero);
         out = $rh.createReply();
         EtudiantHelper.write (out, $result);
         break;
       }

       case 2:  // Contrat/Promotion/calculer_moyenne_de_la_promotion
       {
         double $result = (double)0;
         $result = this.calculer_moyenne_de_la_promotion ();
         out = $rh.createReply();
         out.write_double ($result);
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:Contrat/Promotion:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public Promotion _this() 
  {
    return PromotionHelper.narrow(
    super._this_object());
  }

  public Promotion _this(org.omg.CORBA.ORB orb) 
  {
    return PromotionHelper.narrow(
    super._this_object(orb));
  }


} // class PromotionPOA
