package test.classes;

import classes.ConsultaData;
import classes.Document;
import classes.Frase;
import classes.Contingut;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;



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
        Document d5 = new Document(new Frase("prova"), new Frase("d5"), false, null, LocalDate.of(2018, 12, 22),cont);
        Document d6 = new Document(new Frase("prova"), new Frase("aquest primer"), false, null, LocalDate.of(2018, 12, 23),cont);
        Document d7 = new Document(new Frase("prova"), new Frase("el tercer dels tres"), false, null, LocalDate.of(2018, 12, 23),cont);


        /*Com que en un ArrayList els elements s'insereixen al final, compararem el resultat de la funcio consulta.consulta() 
        * (que simplement mostra tots els elements que hi han inserits [si no es posa cap parametre])
        * amb l'array (on manualment s'hauran inserit els elements en ordre).*/
        ArrayList<Document> resultat = new ArrayList<>();
        resultat.add(d1); // 23-12-2018
        consulta.addDoc(d1);
        assertEquals(resultat, consulta.consulta());

        //afegim un posterior
        resultat.add(d2); // 6-1-2022
        consulta.addDoc(d2);
        assertEquals(resultat, consulta.consulta());

        //un anerior en primera posicio
        resultat.add(0, d3);
        consulta.addDoc(d3);
        assertEquals(resultat, consulta.consulta());

        //els afegim de manera equivocada a resultat (d1, d2 i d3) i veiem que l'assert es un not equal (ja que d3 hauria d'estar el primer).
        resultat = new ArrayList<>();
        resultat.add(d1); // 23-12-2018 
        resultat.add(d2); // 6-1-2022 
        resultat.add(d3); // 11-10-1840
        assertNotEquals(resultat, consulta.consulta());
        //System.out.println(consulta.consulta()); //Veiem que consulta si dona d3, d1, d2.


        //afegim mes documents
        consulta.addDoc(d4);
        consulta.addDoc(d5);

        //expected
        resultat = new ArrayList<>();
        resultat.add(d3); // 11-10-1840
        resultat.add(d5); // 22-12-2018
        resultat.add(d1); // 23-12-2018
        resultat.add(d4); // 17-7-2020
        resultat.add(d2); // 6-1-2022
        assertEquals(resultat, consulta.consulta());

        //afegim 2 documents que comparteixen data amb un altre i veiem si s'han afegit correctament per ordre alfabetic.
        consulta.addDoc(d6);
        consulta.addDoc(d7);

        resultat.add(2, d6); //el primer dels 3 alfabeticament (de la data 23-12-2018).
        resultat.add(4, d7); //el tercer dels 3 alfabeticament (de la data 23-12-2018).
        assertEquals(resultat, consulta.consulta());
        //System.out.println(consulta.consulta());

    }


    @Test
    @DisplayName("Test Dates")   
    public void ConsultaDataProvaEsborrarDocument() {
        //Provarem ara d'esborrar documents i veure el set resultant (un altre cop també amb les dates min i max per tal d'obtenir tots els que queden inserits).
        //Es una funcio trivial i la seva correctesa es bassa en la correctesa del metode delete d'una ArrayList per tant no hauria d'haver cap problema
        //Tot i així procedim a fer un experiment trivial d'anar fent delete dels documents 1 per 1.


        ConsultaData consulta = new ConsultaData();
        //creem un contingut base.
        Frase test[] = new Frase[1];
        test[0] = new Frase("text inutil");
        Contingut cont = new Contingut("text inutil", test);
        Document d1 = new Document(new Frase("prova"), new Frase("d1"), false, null, LocalDate.of(2018, 12, 23),cont);
        Document d2 = new Document(new Frase("prova"), new Frase("d2"), false, null, LocalDate.of(2022, 1, 6),cont);
        Document d3 = new Document(new Frase("prova"), new Frase("d3"), false, null, LocalDate.of(1840, 10, 11),cont);
        Document d4 = new Document(new Frase("prova"), new Frase("d4"), false, null, LocalDate.of(2020, 7, 17),cont);
        Document d5 = new Document(new Frase("prova"), new Frase("d5"), false, null, LocalDate.of(2018, 12, 22),cont);
        Document d6 = new Document(new Frase("prova"), new Frase("aquest primer"), false, null, LocalDate.of(2018, 12, 23),cont);
        Document d7 = new Document(new Frase("prova"), new Frase("el tercer dels tres"), false, null, LocalDate.of(2018, 12, 23),cont);

        //Com que en el test anterior hem comprovat la funcio inserir la podem utilitzar sense problemes per aquest test
        consulta.addDoc(d1);
        consulta.addDoc(d2);
        consulta.addDoc(d3);
        consulta.addDoc(d4);
        consulta.addDoc(d5);
        consulta.addDoc(d6);
        consulta.addDoc(d7);

        ArrayList<Document> resultat = new ArrayList<>();
        //l'ordre que s'espera 
        resultat.add(d3); // 11-10-1840
        resultat.add(d5); // 22-12-2018
        resultat.add(d6); // 23-12-2018
        resultat.add(d1); // 23-12-2018
        resultat.add(d7); // 23-12-2018
        resultat.add(d4); // 17-7-2020
        resultat.add(d2); // 6-1-2022

        assertEquals(resultat, consulta.consulta());

        //ara anem esborrant 1 per 1 , de manera mes o nemnys random;

        //Doc 6 -> Restants: 1,2,3,4,5 i 7.
        resultat.remove(d6);
        consulta.deleteDoc(d6);
        assertEquals(resultat, consulta.consulta());

        //Doc 2 -> Restants: 1,3,4,5 i 7.
        resultat.remove(d2);
        consulta.deleteDoc(d2);
        assertEquals(resultat, consulta.consulta());

        //Doc 1 -> Restants: 3,4,5 i 7.
        resultat.remove(d1);
        consulta.deleteDoc(d1);
        assertEquals(resultat, consulta.consulta());

        //Doc 7 -> Restants: 3,4 i 5.
        resultat.remove(d7);
        consulta.deleteDoc(d7);
        assertEquals(resultat, consulta.consulta());

        //Doc 3 -> Restants: 4 i 5.
        resultat.remove(d3);
        consulta.deleteDoc(d3);
        assertEquals(resultat, consulta.consulta());

        //Doc 4 -> Restant: 5.
        resultat.remove(d4);
        consulta.deleteDoc(d4);
        assertEquals(resultat, consulta.consulta());
    }




    @Test
    @DisplayName("Test Dates")   
    public void ConsultaDataGettersSettersIConstructoraAmbParametres() {

        //provem ara les funcions trivials get i set dels atributs LocalDate anterior i LocalDate posterior.
        ConsultaData consulta = new ConsultaData();
        LocalDate primera = LocalDate.of(2000, 1, 1);
        LocalDate segona = LocalDate.of(2022, 11, 9);

        consulta.setAnterior(primera);
        consulta.setPosterior(segona);

        assertEquals(primera, consulta.getAnterior());
        assertEquals(segona, consulta.getPosterior());

        ConsultaData consulta2 = new ConsultaData(primera, segona);

        assertEquals(primera, consulta2.getAnterior());
        assertEquals(segona, consulta2.getPosterior());
    }


    @Test
    @DisplayName("Test Dates")
    public void ConsultaDataProvaConsultaAcotadaPerParametres() {
        /*Hem comprovat en l'anterior experiment que tant com la constructora per defecte com la que disposa de dos parametres funcionen correctament.
         * El test es basara en diversos intervals de cerca amb una llibreria constant (el fet de modificar la llibreria d'una prova a una altra no afecta
         * ja que hem comprovat que l'inserecio es fa correctament).
         */

         //creem un contingut base.
        Frase test[] = new Frase[1];
        test[0] = new Frase("text inutil");
        Contingut cont = new Contingut("text inutil", test);
        Document d1 = new Document(new Frase("prova"), new Frase("d1"), false, null, LocalDate.of(2018, 12, 23),cont);
        Document d2 = new Document(new Frase("prova"), new Frase("d2"), false, null, LocalDate.of(2022, 1, 6),cont);
        Document d3 = new Document(new Frase("prova"), new Frase("d3"), false, null, LocalDate.of(1840, 10, 11),cont);
        Document d4 = new Document(new Frase("prova"), new Frase("d4"), false, null, LocalDate.of(2020, 7, 17),cont);
        Document d5 = new Document(new Frase("prova"), new Frase("d5"), false, null, LocalDate.of(2018, 12, 22),cont);
        Document d6 = new Document(new Frase("prova"), new Frase("aquest primer"), false, null, LocalDate.of(2018, 12, 23),cont);
        Document d7 = new Document(new Frase("prova"), new Frase("el tercer dels tres"), false, null, LocalDate.of(2018, 12, 23),cont);
    
        //ordre esperat 
        ArrayList<Document> resultat = new ArrayList<>();
        resultat.add(d3); // 11-10-1840
        resultat.add(d5); // 22-12-2018
        resultat.add(d6); // 23-12-2018
        resultat.add(d1); // 23-12-2018
        resultat.add(d7); // 23-12-2018
        resultat.add(d4); // 17-7-2020
        resultat.add(d2); // 6-1-2022

        //Hem comprovat tant com les insercions com els Setters aixi que els utilitzem sense por pel test.
        ConsultaData consulta = new ConsultaData();
        LocalDate primera = LocalDate.of(1000, 1, 1);
        LocalDate segona = LocalDate.of(2022, 11, 9);

        consulta.addDoc(d1);
        consulta.addDoc(d2);
        consulta.addDoc(d3);
        consulta.addDoc(d4);
        consulta.addDoc(d5);
        consulta.addDoc(d6);
        consulta.addDoc(d7);

        consulta.setAnterior(primera);
        consulta.setPosterior(segona);

        //Fem un primer interval que agafi tots els documents.
        assertEquals(resultat, consulta.consulta());


        //actualitzem l'interval, ara agafara tots menys el 3
        primera = LocalDate.of(2000, 1, 1);
        segona = LocalDate.of(2022, 11, 9);
        consulta.setAnterior(primera);
        consulta.setPosterior(segona);

        resultat.remove(0); //esborrem el document de 1840.
        assertEquals(resultat, consulta.consulta());


        //actualitzem l'interval, ara agafara els 3 que comparteixen data (recordem ordenats per ordre alfabetic).
        primera = LocalDate.of(2018, 12, 23);
        segona = LocalDate.of(2018, 12, 23);
        consulta.setAnterior(primera);
        consulta.setPosterior(segona);

        resultat = new ArrayList<>();
        resultat.add(d6); // 23-12-2018
        resultat.add(d1); // 23-12-2018
        resultat.add(d7); // 23-12-2018
        assertEquals(resultat, consulta.consulta());

        //Afegim el del dia anterior i mirem que el retorni el primer, ja que es el mes antic.
        primera = LocalDate.of(2018, 12, 22);
        consulta.setAnterior(primera);

        resultat.add(0,d5); // 22-12-2018
        assertEquals(resultat, consulta.consulta());

        //Per ultim mirem un interval empty i un interval on la data inicial > final.
        resultat = new ArrayList<>();

        //dies posteriors i anteriors de documents existents
        primera = LocalDate.of(2018, 12, 24);
        segona = LocalDate.of(2020, 7, 16);
        consulta.setAnterior(primera);
        consulta.setPosterior(segona);
        //Resultat = res.
        assertEquals(resultat, consulta.consulta());

        //interval erroni
        primera = LocalDate.of(2020, 12, 24);
        segona = LocalDate.of(2020, 12, 20);
        consulta.setAnterior(primera);
        consulta.setPosterior(segona);
        //Resultat = res.
        assertEquals(resultat, consulta.consulta());
    
    
    
    
    }



}
