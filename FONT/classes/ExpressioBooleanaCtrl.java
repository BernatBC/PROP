package classes;
import java.util.*;
//import classes.ExpressioBooleana;


/** ExpressioBooleanaCtrl
* @author Salvador Moya Bartolome (salvador.moya@estudiantat.upc.edu)
*/

public class ExpressioBooleanaCtrl{

    private Map <String, ExpressioBooleana> SetDeExpressions = new HashMap<>();
    
    public ExpressioBooleanaCtrl(){}

    public Set<String> GetNomExpressions(){
        return SetDeExpressions.keySet();
    }

    public int getNEBS(){
        return SetDeExpressions.size();
    }

    public ArrayList<String> getAllEBS(){
        ArrayList<String> names = new ArrayList<>();

        for (String s : SetDeExpressions.keySet()) names.add(s);

        return names;
    }

    public boolean existsEB(String nom){
        return SetDeExpressions.containsKey(nom);
    }

    public void AddExpressioBooleana(String nom, String cos){
        ExpressioBooleana temp = new ExpressioBooleana(nom, cos);
        SetDeExpressions.put(nom, temp);
    }

    public ExpressioBooleana GetExpressioBooleana (String nom){
        return SetDeExpressions.get(nom);
    }
    
    public ExpressioBooleana ExpressioBooleanaTemporal (String cos){
        ExpressioBooleana temp = new ExpressioBooleana();
        temp.setCos(cos);
        return temp;
    }
    
    public void DeleteExpressioBooleana(String nom){
    
        SetDeExpressions.remove(nom);
    }

    public void SetExpressioBooleana(String nom, String cos){

        if (SetDeExpressions.containsKey(nom)){
            SetDeExpressions.remove(nom);
            SetDeExpressions.put(nom, new ExpressioBooleana(nom, cos));
        }
    }
}
