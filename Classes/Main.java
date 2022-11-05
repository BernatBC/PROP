import Classes.Llibreria.java;
import Classes.Vocabulari.java;
import Classes.DocumentCtrl.java;

/** Main.
 * @author Bernat Borràs Civil (bernat.borras.civil@estudiantat.upc.edu)
 */
public class Main {

    public static void main(String[] args) {

        Vocabulari v = new Vocabulari();
        Llibreria l = new Llibreria();
        DocumentCtrl DCtlr = new DocumentCtrl(v, l);

        while (true) ;
    }
}
