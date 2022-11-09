package test.classes;

import classes.ConsultaData;
import classes.Document;
import classes.Frase;
import classes.Contingut;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

//import java.time.LocalDate;

import org.junit.*;
import org.junit.jupiter.api.DisplayName;

public class ConsultaDataTest {
    
    @Before                                         
    public void setUp() {
        System.out.println("SetUp Completed!");
    }




    @Test
    @DisplayName("Test Dates")   
    public void ConsultaDataProvaInserirDocument() {
        // Init
        ConsultaData consulta = new ConsultaData();
        /*voldrem comprovar que a mesura que anem inserint els documents es mantinguin ordenats. 
        * Per això després de fer cada inserció demanarem tots els documents per veure'n l'ordre.
        * Com nomes volem comporvar l'ordre dels documents segons la seva data, aquests seran gairebe trivials. */

        //creem un contingut base.
        Frase test[] = new Frase[1];
        test[0] = new Frase("text inutil");
        Contingut cont = new Contingut("text inutil", test);
        Document d1 = new Document(new Frase("prova"), new Frase("d1"), false, null, LocalDate.of(2018, 12, 23),cont);
        Document d2 = new Document(new Frase("prova"), new Frase("d2"), false, null, LocalDate.of(2022, 1, 6),cont);
        Document d3 = new Document(new Frase("prova"), new Frase("d3"), false, null, LocalDate.of(1840, 10, 11),cont);
        Document d4 = new Document(new Frase("prova"), new Frase("d4"), false, null, LocalDate.of(2020, 7, 17),cont);
        Document d5 = new Document(new Frase("prova"), new Frase("d5"), false, null, LocalDate.of(2018, 12, 23),cont);
        Document d6 = new Document(new Frase("prova"), new Frase("d6"), false, null, LocalDate.of(2018, 12, 23),cont);


        /*Com que en un ArrayList els elements s'insereixen al final, compararem el resultat de la funcio consulta.consulta() 
        * (que simplement mostra tots els elements que hi han inserits [si no es posa cap parametre])
        * amb l'array (on manualment s'hauran inserit els elements en ordre).*/
        ArrayList<Document> resultat = new ArrayList<>();
        resultat.add(d1);
        consulta.addDoc(d1);
        assertEquals(resultat, consulta.consulta());


    }


}
