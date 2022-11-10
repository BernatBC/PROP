package classes;
//import classes.ExpressionTree;
//import classes.Node;
import java.util.*;

/** ExpressioBooleana
* @author Salvador Moya Bartolome (salvador.moya@estudiantat.upc.edu)
*/
class ExpressioBooleana{

    
    private String cos;
    private String nom;
    private Node root;
    

    public String getNom (){
        return nom;
    }

    public void setNom (String setnom){
        nom = setnom;
    }

    public String getCos (){
        return cos;
    }

    public void setCos (String setcos){
        cos = setcos;
        ExpressionTree eT = new ExpressionTree();
        root = eT.ConsultaExpressioBooleana(cos);
    }
    
    public Node getRoot (){
        return root;
    }
    
    public void setRoot (Node n){
        root = n;
    }
    
    public  ExpressioBooleana ExpressioBooleana (String nom, String cos){
    ExpressioBooleana exp = new ExpressioBooleana();
    this.setCos(cos);
    this.setNom(nom);
    ExpressionTree eT = new ExpressionTree();
        this.setRoot(eT.ConsultaExpressioBooleana(cos));
    return this;
    }
    
    public ExpressioBooleana ExpressioBooleana (){
    ExpressioBooleana exp = new ExpressioBooleana();
    this.cos = null;
    this.nom = null;
    this.setRoot(new Node());
    return this;
    }
    public ExpressioBooleana getExpressioBooleana(){
    
    return this;
    
    }
    
}


