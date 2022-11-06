package Classes;
import Classes.Llibreria;
import Classes.Vocabulari;
import Classes.DocumentCtrl;

/** Main.
 * @author Bernat Borràs Civil (bernat.borras.civil@estudiantat.upc.edu)
 */
public class Main {

    public static void main(String[] args) {

        Vocabulari v = new Vocabulari();
        Llibreria l = new Llibreria();
        DocumentCtrl DCtlr = new DocumentCtrl(v, l);

        DCtlr.crearDocument();

        while (true) ;
    }
}
