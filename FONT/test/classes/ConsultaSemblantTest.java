package test.classes;

import classes.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.*;
import org.junit.jupiter.api.DisplayName;

/** Classe de proves de la Consulta per Semblança.
 * @author Alexandre Ros i Roger (alexandre.ros.roger@estudiantat.upc.edu)
 */
public class ConsultaSemblantTest {
    
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

        ArrayList<Pair<Double, Document>> results, results2;

        results = ConsultaSemblant.executeQuery(lib, d3, 4, 0);
        results2 = ConsultaSemblant.executeQuery(lib, d3, 9, 1);

        // An error should appear telling us that d3 is not in lib.
        // Thus results should stay empty
        assertEquals("[]", results.toString());
        assertEquals("[]", results2.toString());

        results = ConsultaSemblant.executeQuery(lib, d1, 1, 0);
        results2 = ConsultaSemblant.executeQuery(lib, d1, 1,1);


        // Now, since there is only one document and the answer cannot contain the document
        // 'results' should stay empty.
        assertEquals("[]", results.toString());
        assertEquals("[]", results2.toString());

        lib.addDocument(d2); lib.addDocument(d3);

        results = ConsultaSemblant.executeQuery(lib, d1, 2, 0);
        results2 = ConsultaSemblant.executeQuery(lib, d1, 2, 1);

        // d1 és més semblant a d2 que a d3 amb els dos mètodes.
        assertEquals(d2, results.get(0).getR());
        assertEquals(d3, results.get(1).getR());
        assertEquals(d2, results2.get(0).getR());
        assertEquals(d3, results2.get(1).getR());

        results = ConsultaSemblant.executeQuery(lib, d2, 1, 0);
        results2 = ConsultaSemblant.executeQuery(lib, d2, 1, 1);

        // ara, com que d1 i d2 són més semblants, hauria de ser d1 el resultat i com que k = 1 només 1 document
        assertEquals(d1, results.get(0).getR());
        assertEquals(1, results.size());
        assertEquals(d1, results2.get(0).getR());
        assertEquals(1, results2.size());

        results = ConsultaSemblant.executeQuery(lib, d3, 10, 0);
        results2 = ConsultaSemblant.executeQuery(lib, d3, 10, 1);
        assertEquals(2, results.size());
        assertEquals(d2, results.get(0).getR());
        assertEquals(2, results2.size());
        assertEquals(d2, results2.get(0).getR());

        // We conclude ConsultaSemblant works as expected.
    }
}
