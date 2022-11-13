package test.classes;

import classes.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.*;
import org.junit.jupiter.api.DisplayName;

/** Classe de proves d'una Llibreria.
 * @author Alexandre Ros i Roger (alexandre.ros.roger@estudiantat.upc.edu)
 */
public class LlibreriaTest {
    
    @Before                                         
    public void setUp() {
        System.out.println("SetUp Completed!");
    }


    @Test
    @DisplayName("Test Únic")   
    public void testLib() {
        // Init
        Paraula p1 = new Paraula("lleida");
        Paraula p2 = new Paraula("tarragona");
        Paraula p3 = new Paraula("barcelona");
        Paraula p4 = new Paraula("girona");
        Paraula p5 = new Paraula("blanes");
        Paraula p6 = new Paraula("puigcerdà");

        ArrayList<Paraula> a1 = new ArrayList<>();
        ArrayList<Paraula> a2 = new ArrayList<>();
        ArrayList<Paraula> a3 = new ArrayList<>();
        ArrayList<Paraula> a4 = new ArrayList<>();
        ArrayList<Paraula> a5 = new ArrayList<>();
        ArrayList<Paraula> a6 = new ArrayList<>();
        ArrayList<Paraula> a7 = new ArrayList<>();


        a1.add(p1); a1.add(p2); a1.add(p2);
        a2.add(p4);
        a3.add(p1); a3.add(p2); a3.add(p3); a3.add(p4); a3.add(p5); a3.add(p6); 
        a5.add(p4); a5.add(p5); a5.add(p3);
        a6.add(p3); a6.add(p3);
        a7.add(p5); a7.add(p5); a7.add(p5); a7.add(p5); a7.add(p5); a7.add(p5); a7.add(p5); a7.add(p5);

        Frase f1 = new Frase(a1, "lleida, tarragona, tarragona");
        Frase f2 = new Frase(a2, "+*+girona+*+");
        Frase f3 = new Frase(a3, "lleida tarragona barcelona girona blanes puigcerdà");
        Frase f4 = new Frase(a4, "");
        Frase f5 = new Frase(a5, "girona-blanes-barcelona");
        Frase f6 = new Frase(a6, "¿barcelona=barcelona?");
        Frase f7 = new Frase(a7, "** blanes blanes blanes blanes blanes blanes blanes blanes **");

        Frase[] phraseArray = {f1, f2, f3, f4};
        Frase[] phraseArray2 = {f2, f1, f5};
        Frase[] phraseArray3 = {f7, f6};

        Contingut c1 = new Contingut("lleida, tarragona, tarragona\n+*+girona+*+\nlleida tarragona barcelona girona blanes puigcerdà\n\n", phraseArray);
        Contingut c2 = new Contingut("+*+girona+*+\nlleida, tarragona, tarragona\ngirona-blanes-barcelona", phraseArray2);
        Contingut c3 = new Contingut("** blanes blanes blanes blanes blanes blanes blanes blanes **\n¿barcelona=barcelona?", phraseArray3); 

        Document d1 = new Document(new Frase("Jo Mateix"), new Frase("Poblacions #1"), false, null, LocalDate.of(2022, 11, 22), c1);
        Document d2 = new Document(new Frase("Jo Mateix"), new Frase("Poblacions #2"), false, null, LocalDate.of(2022, 10, 22), c2);
        Document d3 = new Document(new Frase("Ningú"), new Frase("Poblacions #3"), false, null, LocalDate.of(1990, 12, 31), c3);

        // Ja podem començar amb la llibreria

        Llibreria lib = new Llibreria();

        lib.addDocument(d1);

        // Comencem fent assert de coses bàsiques amb un sol document
        assertEquals(null, lib.getDocument("Ningú", "Poblacions #1").getL());
        assertEquals(false, lib.getDocument("Ningú", "Poblacions #1").getR());

        assertEquals(true, lib.getDocument("Jo Mateix", "Poblacions #1").getR());

        //System.out.println(lib);

        assertEquals(1, lib.getNdocs());

        // Now we add the second document
        lib.addDocument(d2);

        //System.out.println(lib);
        assertEquals(d1, lib.getIessim(0));
        assertEquals(d2, lib.getIessim(1));
        assertEquals(2, lib.getNdocs());
        //System.out.println(lib.getSetDocuments());

        // Computing the cosinus on documents 1 and 2
        // The cosinus 0.880172458846704 TF_IDF has been computed manually and verified that it is in fact correct.
        // So has the cosinus 0.9438798074485389 using ocurrences of words.
        assertEquals(0.880172458846704, lib.computeCosinus(d1, d2, 0));
        assertEquals(0.880172458846704, lib.computeCosinus(d2, d1, 0));
        assertEquals(0.9438798074485389, lib.computeCosinus(d1, d2,1));
        assertEquals(0.9438798074485389, lib.computeCosinus(d2, d1,1));

        // Finally, we are adding our third and last document
        lib.addDocument(d3);

        assertEquals(d3, lib.getIessim(2));
        // Both the author and title exist, but the document (author, title) doesnt
        assertEquals(false, lib.getDocument("Ningú", "Poblacions #2").getR());
        assertEquals(true, lib.getDocument("Ningú", "Poblacions #3").getR());
        assertEquals(d3, lib.getDocument("Ningú", "Poblacions #3").getL());

        d1.setFavourite(true);

        assertEquals(1, lib.getPreferits().getNdocs());

        // Just like before, the result has been computed beforehand by a human
        assertEquals(0.25708724836453034, lib.computeCosinus(d3, d2, 0));


        // Adding a document shouldn't change cosinus product with ocurrences
        assertEquals(0.9438798074485389, lib.computeCosinus(d2, d1, 1));
        assertEquals(0.36563621206356534, lib.computeCosinus(d2, d3, 1));

        lib.deleteDocument(d3);

        assertEquals(0.880172458846704, lib.computeCosinus(d2, d1, 0));
        assertEquals(0.9438798074485389, lib.computeCosinus(d2, d1,1));
        // After removing d3, the cosinus product of d1 and d2 stays like before.
        // All updates are done correctly.

    }
}
