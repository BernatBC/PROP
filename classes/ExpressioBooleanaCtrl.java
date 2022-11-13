package classes;
import java.util.*;
//import classes.ExpressioBooleana;


/** ExpressioBooleanaCtrl
* @author Salvador Moya Bartolome (salvador.moya@estudiantat.upc.edu)
*/

public class ExpressioBooleanaCtrl{

    private Map <String, ExpressioBooleana> SetDeExpressions;

    public void CreaExpressioBooleana(String nom, String cos){

        SetDeExpressions.put(nom, new ExpressioBooleana(nom, cos));
    }

    public ExpressioBooleana GetExpressioBooleana (String nom){
        
        return SetDeExpressions.get(nom);
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
