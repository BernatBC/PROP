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
        //Inicialitzem la classe consultaPreferit
        ConsultaPreferit consulta = new ConsultaPreferit();

        Frase test[] = new Frase[1];
        test[0] = new Frase("text inutil");
        Contingut cont = new Contingut("text inutil", test);
        Document d1 = new Document(new Frase("autor1"), new Frase("document1"), false, null, null, cont);
        Document d2 = new Document(new Frase("autor2"), new Frase("document2"), false, null, null, cont);
        Document d3 = new Document(new Frase("autor3"), new Frase("document3"), false, null, null, cont);
        Document d4 = new Document(new Frase("autor4"), new Frase("document2"), false, null, null, cont);
        Document d5 = new Document(new Frase("autor2"), new Frase("document5"), false, null, null, cont);

        Set<Document> esperat = new HashSet<>();

        //Ens retorna un conjunt buit de documents.
        assertEquals(esperat, consulta.getDocPreferit());

        //Marquem un document com a preferit i veiem que al fer la consulta ens el retorna.
        consulta.afegirDocument(d1);
        esperat.add(d1);
        assertEquals(esperat, consulta.getDocPreferit());

        //Quan el tornem a afegir, la consulta es comporta de la mateixa manera.
        consulta.afegirDocument(d1);
        assertEquals(esperat, consulta.getDocPreferit());
        
        //Eliminem el document, i ja no apareix a la consulta.
        consulta.eliminarDocument(d1);
        esperat.remove(d1);
        assertEquals(esperat, consulta.getDocPreferit());

        //Quan el tornem a eliminar, segueix comportant-se com esperàvem.
        consulta.eliminarDocument(d1);
        assertEquals(esperat, consulta.getDocPreferit());

        //El tornem a afegir, i comprovem que s'afageixi correctament.
        consulta.afegirDocument(d1);
        esperat.add(d1);
        assertEquals(esperat, consulta.getDocPreferit());

        //Afegim més documents.
        consulta.afegirDocument(d2);
        esperat.add(d2);
        assertEquals(esperat, consulta.getDocPreferit());

        consulta.afegirDocument(d3);
        esperat.add(d3);
        assertEquals(esperat, consulta.getDocPreferit());

        consulta.afegirDocument(d5);
        esperat.add(d5);
        assertEquals(esperat, consulta.getDocPreferit());

        //Eliminem aguns documents i seguim comprovant que el resultat sigui correcte.
        consulta.eliminarDocument(d3);
        esperat.remove(d3);
        assertEquals(esperat, consulta.getDocPreferit());

        consulta.eliminarDocument(d5);
        esperat.remove(d5);
        assertEquals(esperat, consulta.getDocPreferit());

        consulta.eliminarDocument(d2);
        esperat.remove(d2);
        assertEquals(esperat, consulta.getDocPreferit());

        //Eliminem i tornem a afegir algun document més.
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
