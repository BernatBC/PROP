package test.classes;

import classes.ConsultaAvancada;
import classes.Frase;
import classes.Contingut;
import classes.Document;
import classes.Llibreria;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.*;
import org.junit.jupiter.api.DisplayName;

/** Joc de proves per a la classe ConsultaAvancadaTitol.
 * @author Bernat Borràs Civil (bernat.borras.civil@estudiantat.upc.edu)
 */
public class ConsultaAvancadaTest {
    @Before                                         
    public void setUp() {
        System.out.println("SetUp Completed!");
    }

    @Test
    @DisplayName("Seqüència al títol")   
    public void ConsultaAvancadaAlTitol() {
        // Init
        Frase test[] = new Frase[1];
        test[0] = new Frase("text_inutil");
        Frase autor = new Frase("autor1");
        Contingut cont = new Contingut("text_inutil", test);
        Document d1 = new Document(autor, new Frase("contéConsulta"), false, null, null, cont);
        Document d2 = new Document(autor, new Frase("Consulta"), false, null, null, cont);
        Document d3 = new Document(autor, new Frase("No ho conté"), false, null, null, cont);
        Document d4 = new Document(autor, new Frase("Classe Consulta Autor"), false, null, null, cont);
        Document d5 = new Document(autor, new Frase("Consultant"), false, null, null, cont);
        Document d6 = new Document(autor, new Frase("Cons no pertany"), false, null, null, cont);
        Document d7 = new Document(autor, new Frase("Consulat dels Estats Units"), false, null, null, cont);

        Llibreria l = new Llibreria();

        l.addDocument(d1);
        l.addDocument(d2);
        l.addDocument(d3);
        l.addDocument(d4);
        l.addDocument(d5);
        l.addDocument(d6);
        l.addDocument(d7);

        Set<Document> esperat = new HashSet<>();

        assertEquals(esperat, ConsultaAvancada.obtenirDocuments(l, "Cap Títol"));

        esperat.add(d1);
        esperat.add(d2);
        esperat.add(d4);
        esperat.add(d5);
        assertEquals(esperat, ConsultaAvancada.obtenirDocuments(l, "Consulta"));

        esperat.add(d6);
        esperat.add(d7);
        assertEquals(esperat, ConsultaAvancada.obtenirDocuments(l, "Cons"));

        esperat.remove(d1);
        esperat.remove(d2);
        esperat.remove(d5);
        esperat.add(d3);
        assertEquals(esperat, ConsultaAvancada.obtenirDocuments(l, " "));

        esperat.add(d1);
        esperat.add(d2);
        esperat.add(d5);
        assertEquals(esperat, ConsultaAvancada.obtenirDocuments(l, ""));
    }

    @Test
    @DisplayName("Seqüència al autor")   
    public void ConsultaAlAutor() {
        // Init
        Frase test[] = new Frase[1];
        test[0] = new Frase("text_inutil");
        Frase titol = new Frase("titol");
        Contingut cont = new Contingut("text_inutil", test);
        Document d1 = new Document(new Frase("contéConsulta"), titol, false, null, null, cont);
        Document d2 = new Document(new Frase("Consulta"), titol, false, null, null, cont);
        Document d3 = new Document(new Frase("No ho conté"), titol, false, null, null, cont);
        Document d4 = new Document(new Frase("Classe Consulta Autor"), titol, false, null, null, cont);
        Document d5 = new Document(new Frase("Consultant"), titol, false, null, null, cont);
        Document d6 = new Document(new Frase("Cons no pertany"), titol, false, null, null, cont);
        Document d7 = new Document(new Frase("Consulat dels Estats Units"), titol, false, null, null, cont);

        Llibreria l = new Llibreria();

        l.addDocument(d1);
        l.addDocument(d2);
        l.addDocument(d3);
        l.addDocument(d4);
        l.addDocument(d5);
        l.addDocument(d6);
        l.addDocument(d7);

        Set<Document> esperat = new HashSet<>();

        assertEquals(esperat, ConsultaAvancada.obtenirDocuments(l, "Cap Autor"));

        esperat.add(d1);
        esperat.add(d2);
        esperat.add(d4);
        esperat.add(d5);
        assertEquals(esperat, ConsultaAvancada.obtenirDocuments(l, "Consulta"));

        esperat.add(d6);
        esperat.add(d7);
        assertEquals(esperat, ConsultaAvancada.obtenirDocuments(l, "Cons"));

        esperat.remove(d1);
        esperat.remove(d2);
        esperat.remove(d5);
        esperat.add(d3);
        assertEquals(esperat, ConsultaAvancada.obtenirDocuments(l, " "));

        esperat.add(d1);
        esperat.add(d2);
        esperat.add(d5);
        assertEquals(esperat, ConsultaAvancada.obtenirDocuments(l, ""));
    }

    @Test
    @DisplayName("Seqüència al contingut")   
    public void ConsultaAlContingut() {
        // Init
        Frase test1[] = new Frase[0];
        Contingut cont1 = new Contingut("", test1);
        Frase test2[] = new Frase[1];
        test2[0] = new Frase("Accepta.");
        Contingut cont2 = new Contingut("Accepta.", test2);
        Frase test3[] = new Frase[2];
        test3[0] = new Frase("Accepta.");
        test3[1] = new Frase("No ho fa.");
        Contingut cont3 = new Contingut("Accepta. No ho fa.", test3);
        Frase test4[] = new Frase[2];
        test4[0] = new Frase("Accepta.");
        test4[1] = new Frase("Segona ocurrencia d'Accepta.");
        Contingut cont4 = new Contingut("Accepta. Segona ocurrencia d'Accepta.", test4);
        Frase test5[] = new Frase[2];
        test5[0] = new Frase("Ara no hi ha la primera.");
        test5[1] = new Frase("Segona ocurrencia d'Accepta.");
        Contingut cont5 = new Contingut("Ara no hi ha la primera. Segona ocurrencia d'Accepta.", test5);
        Frase test6[] = new Frase[3];
        test6[0] = new Frase("Ara no hi ha la primera.");
        test6[1] = new Frase("Ni a la segona frase.");
        test6[2] = new Frase("Ni tampoc a la tercera.");
        Contingut cont6 = new Contingut("Ara no hi ha la primera. Ni a la segona frase. Ni tampoc a la tercera.", test6);

        Document d1 = new Document(new Frase("autor1"), new Frase("titol1"), false, null, null, cont1);
        Document d2 = new Document(new Frase("autor2"), new Frase("titol2"), false, null, null, cont2);
        Document d3 = new Document(new Frase("autor3"), new Frase("titol3"), false, null, null, cont3);
        Document d4 = new Document(new Frase("autor4"), new Frase("titol4"), false, null, null, cont4);
        Document d5 = new Document(new Frase("autor5"), new Frase("titol5"), false, null, null, cont5);
        Document d6 = new Document(new Frase("autor6"), new Frase("titol6"), false, null, null, cont6);

        Llibreria l = new Llibreria();

        l.addDocument(d1);
        l.addDocument(d2);
        l.addDocument(d3);
        l.addDocument(d4);
        l.addDocument(d5);
        l.addDocument(d6);

        Set<Document> esperat = new HashSet<>();

        assertEquals(esperat, ConsultaAvancada.obtenirDocuments(l, "No apareix"));

        esperat.add(d2);
        esperat.add(d3);
        esperat.add(d4);
        esperat.add(d5);
        assertEquals(esperat, ConsultaAvancada.obtenirDocuments(l, "Accepta"));

        esperat.remove(d2);
        esperat.remove(d3);
        esperat.remove(d4);
        esperat.add(d6);
        assertEquals(esperat, ConsultaAvancada.obtenirDocuments(l, "no"));

        esperat.add(d3);
        esperat.add(d4);
        assertEquals(esperat, ConsultaAvancada.obtenirDocuments(l, " "));

        esperat.add(d1);
        esperat.add(d2);
        assertEquals(esperat, ConsultaAvancada.obtenirDocuments(l, ""));

        esperat.remove(d1);
        esperat.remove(d2);
        esperat.remove(d3);
        esperat.remove(d4);
        esperat.remove(d5);
        esperat.remove(d6);

        assertEquals(esperat, ConsultaAvancada.obtenirDocuments(l, "Aquí tampoc"));
    }
}
