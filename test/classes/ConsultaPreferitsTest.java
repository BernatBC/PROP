package test.classes;

import classes.ConsultaPreferit;
import classes.Frase;
import classes.Contingut;
import classes.Document;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.*;
import org.junit.jupiter.api.DisplayName;

/** Joc de proves per a la classe ConsultaPreferit.
 * @author Bernat Borràs Civil (bernat.borras.civil@estudiantat.upc.edu)
 */
public class ConsultaPreferitsTest {
    @Before                                         
    public void setUp() {
        System.out.println("SetUp Completed!");
    }

    @Test
    @DisplayName("Test Preferits")   
    public void ConsultaPreferits() {
        // Init
        ConsultaPreferit consulta = new ConsultaPreferit();
        /*voldrem comprovar que a mesura que anem inserint els documents es mantinguin ordenats. 
        * Per això després de fer cada inserció demanarem tots els documents per veure'n l'ordre.
        * Com nomes volem comporvar l'ordre dels documents segons la seva data, aquests seran gairebe trivials. */

        //creem un contingut base.
        Frase test[] = new Frase[1];
        test[0] = new Frase("text inutil");
        Contingut cont = new Contingut("text inutil", test);
        Document d1 = new Document(new Frase("autor"), new Frase("document1"), false, null, null, cont);
        Document d2 = new Document(new Frase("autor"), new Frase("document1"), false, null, null, cont);
        Document d3 = new Document(new Frase("autor"), new Frase("document1"), false, null, null, cont);
        Document d4 = new Document(new Frase("autor"), new Frase("document1"), false, null, null, cont);
        Document d5 = new Document(new Frase("autor"), new Frase("document1"), false, null, null, cont);

        Set<Document> esperat = new HashSet<>();

        assertEquals(esperat, consulta.getDocPreferit());

        consulta.afegirDocument(d1);
        esperat.add(d1);
        assertEquals(esperat, consulta.getDocPreferit());

        consulta.eliminarDocument(d1);
        esperat.remove(d1);
        assertEquals(esperat, consulta.getDocPreferit());

        consulta.eliminarDocument(d1);
        assertEquals(esperat, consulta.getDocPreferit());

        consulta.afegirDocument(d1);
        esperat.add(d1);
        assertEquals(esperat, consulta.getDocPreferit());

        consulta.afegirDocument(d2);
        esperat.add(d2);
        assertEquals(esperat, consulta.getDocPreferit());

        consulta.afegirDocument(d3);
        esperat.add(d3);
        assertEquals(esperat, consulta.getDocPreferit());

        consulta.afegirDocument(d5);
        esperat.add(d5);
        assertEquals(esperat, consulta.getDocPreferit());

        consulta.eliminarDocument(d3);
        esperat.remove(d3);
        assertEquals(esperat, consulta.getDocPreferit());

        consulta.eliminarDocument(d5);
        esperat.remove(d5);
        assertEquals(esperat, consulta.getDocPreferit());

        consulta.eliminarDocument(d2);
        esperat.remove(d2);
        assertEquals(esperat, consulta.getDocPreferit());

        consulta.afegirDocument(d4);
        esperat.add(d4);
        assertEquals(esperat, consulta.getDocPreferit());

        consulta.eliminarDocument(d1);
        esperat.remove(d1);
        assertEquals(esperat, consulta.getDocPreferit());

        consulta.eliminarDocument(d4);
        esperat.remove(d4);
        assertEquals(esperat, consulta.getDocPreferit());

    }
}
