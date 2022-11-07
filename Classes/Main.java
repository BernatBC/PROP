package Classes;


/** Main.
 * @author Bernat Borràs Civil (bernat.borras.civil@estudiantat.upc.edu)
 */
public class Main {

    public static void main(String[] args) {

        ConsultaData CD = new ConsultaData();
        ConsultaTitol CT = new ConsultaTitol();
        ConsultaPreferit CP = new ConsultaPreferit();

        Vocabulari v = new Vocabulari();
        Llibreria l = new Llibreria();
        DocumentCtrl DCtlr = new DocumentCtrl(v, l);

        DCtlr.crearDocument();
        /*
        Document doc1 = DCtlr.getDocument("John Doe", "Document Test").getR();

        Frase autor = new Frase("John Doe");
        
        Llibreria l1 = CD.getAnterior(LocalDate.now());
        Llibreria l2 = CP.getDocPreferit();
        Llibreria l3 = CT.getDocAutor(autor);*/

        while (true) ;
    }
}
