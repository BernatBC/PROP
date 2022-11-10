package test.classes;

import classes.ConsultaRellevancia;

import static org.junit.jupiter.api.Assertions.assertEquals;


import classes.Document;
import classes.Llibreria;
import classes.Paraula;
import classes.Vocabulari;
import classes.Frase;
import classes.Contingut;
import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.*;
import org.junit.jupiter.api.DisplayName;

public class ConsultaRellevanciaTest {
    
    @Before                                         
    public void setUp() {
        System.out.println("SetUp Completed!");
    }

    @Test
    @DisplayName("Test Dates")   
    public void ConsultaRellevanciaProvaModePrimer() {

        //Cal comentar primer que el mode 2 es, en essencia, una cerca per semblança la qual agafa k=k i el document referencia s'aconsegueix
        //amb titol=autor=contingut = la nostra query. Per construccio el mode 2 funcionara, i quedara comprovat, al comprovar-se la cerca per Semblança (ja tested).

        //Init

        /* Com que la Classe ConsultaRellevancia nomes te un metode trivial (que retorna la llibreria que s'ha construit a la constructora)
         * mirarem de veure si aquesta llibreria dona els resultats esperats de la seguent manera:
         * Amb una Llibreria canviant i una query fixa (amb una k canviant).
         * Amb una Llibreria fixa i una query canviant (amb una k canviant).
         */
        //MODE 1 // MODE 1 // MODE 1 // MODE 1

        /* El que ara anem a fer es construir una serie de 6 documents (cadascun de dues frases per fer el test viable i no excesivament enorme).
         * Pere fer-ho n'hi han parametres trivials (titol, autor, favorit [no interve en la cerca], path [no interve en la cerca] i Data [no interve en la cerca])
         * i el contingut. Per crear aquest necessitem dues frases. Per crearles, però, no podem fer-ho directament mitjançant la constructora que utilitza un 
         * string ja totes les paraules tindran IDs diferents (fins i tot si son la mateixa) cosa que dificulta molt trobar el nombre d'aparicions d'una paraula
         * determinada en una frase; per això s'insereixen totes en un Vocabulari (on paraules iguals tenen IDs iguals) aixi com les de la query.
         */
        Vocabulari paraules = new Vocabulari();
        paraules.inserirObtenirParaula("primera");
        Frase test[] = new Frase[2];
        ArrayList<Paraula> words = new ArrayList<>();

        words.add(paraules.inserirObtenirParaula("Primera"));
        words.add(paraules.inserirObtenirParaula("frase"));
        words.add(paraules.inserirObtenirParaula("del"));
        words.add(paraules.inserirObtenirParaula("primer"));
        words.add(paraules.inserirObtenirParaula("document"));
        test[0] = new Frase(words,"Primera frase del primer document.");

        words = new ArrayList<>();
        words.add(paraules.inserirObtenirParaula("Hola"));
        words.add(paraules.inserirObtenirParaula("hola"));
        words.add(paraules.inserirObtenirParaula("hola"));
        test[1] = new Frase(words,"Hola hola hola.");
        Contingut cont1 = new Contingut("Primera frase del primer document. Hola hola hola.", test);
        Document d1 = new Document(new Frase("prova"), new Frase("d1"), false, null, LocalDate.now(),cont1);


        words = new ArrayList<>();
        words.add(paraules.inserirObtenirParaula("Segon"));
        words.add(paraules.inserirObtenirParaula("document"));
        words.add(paraules.inserirObtenirParaula("recordo"));
        words.add(paraules.inserirObtenirParaula("segon"));
        words.add(paraules.inserirObtenirParaula("document"));
        test[0] = new Frase(words,"Segon document, recordo, segon document.");

        words = new ArrayList<>();
        words.add(paraules.inserirObtenirParaula("Aquesta"));
        words.add(paraules.inserirObtenirParaula("nomes"));
        words.add(paraules.inserirObtenirParaula("te"));
        words.add(paraules.inserirObtenirParaula("un"));
        words.add(paraules.inserirObtenirParaula("hola"));
        test[1] = new Frase(words,"Aquesta nomes te un hola.");
        Contingut cont2 = new Contingut("Segon document, recordo, segon document. Aquesta nomes te un hola.", test);
        Document d2 = new Document(new Frase("prova"), new Frase("d2"), false, null, LocalDate.now(),cont2);


        words = new ArrayList<>();
        words.add(paraules.inserirObtenirParaula("EL"));
        words.add(paraules.inserirObtenirParaula("segon"));
        words.add(paraules.inserirObtenirParaula("document"));
        words.add(paraules.inserirObtenirParaula("es"));
        words.add(paraules.inserirObtenirParaula("l'anterior"));
        test[0] = new Frase(words,"El segon document es l'anterior.");

        words = new ArrayList<>();
        words.add(paraules.inserirObtenirParaula("A"));
        words.add(paraules.inserirObtenirParaula("mi"));
        words.add(paraules.inserirObtenirParaula("no"));
        words.add(paraules.inserirObtenirParaula("em"));
        words.add(paraules.inserirObtenirParaula("miris"));
        words.add(paraules.inserirObtenirParaula("no"));
        words.add(paraules.inserirObtenirParaula("tinc"));
        words.add(paraules.inserirObtenirParaula("la"));
        words.add(paraules.inserirObtenirParaula("paraula"));
        words.add(paraules.inserirObtenirParaula("per"));
        words.add(paraules.inserirObtenirParaula("saludar"));
        test[1] = new Frase(words,"A mi no em miris, no tinc la paraula per saludar.");
        Contingut cont3 = new Contingut("El segon document es l'anterior. A mi no em miris, no tinc la paraula per saludar.", test);
        Document d3 = new Document(new Frase("prova"), new Frase("d3"), false, null, LocalDate.now(),cont3);


        words = new ArrayList<>();
        words.add(paraules.inserirObtenirParaula("El"));
        words.add(paraules.inserirObtenirParaula("primer"));
        words.add(paraules.inserirObtenirParaula("document"));
        words.add(paraules.inserirObtenirParaula("m'agrada"));
        words.add(paraules.inserirObtenirParaula("mes"));
        test[0] = new Frase(words,"El primer document m'agrada mes.");

        words = new ArrayList<>();
        words.add(paraules.inserirObtenirParaula("La"));
        words.add(paraules.inserirObtenirParaula("primera"));
        words.add(paraules.inserirObtenirParaula("frase"));
        words.add(paraules.inserirObtenirParaula("diu"));
        words.add(paraules.inserirObtenirParaula("la"));
        words.add(paraules.inserirObtenirParaula("veritat"));
        test[1] = new Frase(words,"La primera frase diu la veritat.");
        Contingut cont4 = new Contingut("El primer document m'agrada mes. La primera frase diu la veritat.", test);
        Document d4 = new Document(new Frase("prova"), new Frase("d4"), false, null, LocalDate.now(),cont4);


        words = new ArrayList<>();
        words.add(paraules.inserirObtenirParaula("Cinc"));
        words.add(paraules.inserirObtenirParaula("documents"));
        words.add(paraules.inserirObtenirParaula("portem"));
        test[0] = new Frase(words,"Cinc documents portem.");

        words = new ArrayList<>();
        words.add(paraules.inserirObtenirParaula("Es"));
        words.add(paraules.inserirObtenirParaula("el"));
        words.add(paraules.inserirObtenirParaula("document"));
        words.add(paraules.inserirObtenirParaula("qui"));
        words.add(paraules.inserirObtenirParaula("escull"));
        words.add(paraules.inserirObtenirParaula("al"));
        words.add(paraules.inserirObtenirParaula("document"));
        words.add(paraules.inserirObtenirParaula("i"));
        words.add(paraules.inserirObtenirParaula("es"));
        words.add(paraules.inserirObtenirParaula("el"));
        words.add(paraules.inserirObtenirParaula("document"));
        words.add(paraules.inserirObtenirParaula("qui"));
        words.add(paraules.inserirObtenirParaula("vol"));
        words.add(paraules.inserirObtenirParaula("que"));
        words.add(paraules.inserirObtenirParaula("sigui"));
        words.add(paraules.inserirObtenirParaula("el"));
        words.add(paraules.inserirObtenirParaula("document"));
        words.add(paraules.inserirObtenirParaula("el"));
        words.add(paraules.inserirObtenirParaula("seu"));
        words.add(paraules.inserirObtenirParaula("propi"));
        words.add(paraules.inserirObtenirParaula("document"));
        test[1] = new Frase(words,"Es el document qui escull al document i es el document qui vol que sigui el document el seu propi document.");
        Contingut cont5 = new Contingut("Cinc documents portem. Es el document qui escull al document i esl el document qui vol que sigui el document el seu propi document.", test);
        Document d5 = new Document(new Frase("prova"), new Frase("d5"), false, null, LocalDate.now(),cont5);


        words = new ArrayList<>();
        words.add(paraules.inserirObtenirParaula("Final"));
        words.add(paraules.inserirObtenirParaula("del"));
        words.add(paraules.inserirObtenirParaula("test"));
        test[0] = new Frase(words,"Final del test.");

        words = new ArrayList<>();
        words.add(paraules.inserirObtenirParaula("Hola"));
        words.add(paraules.inserirObtenirParaula("hola"));
        test[1] = new Frase(words,"Hola hola.");
        Contingut cont6 = new Contingut("Final del test. Hola hola.", test);
        Document d6 = new Document(new Frase("prova"), new Frase("d6"), false, null, LocalDate.now(),cont6);


        Llibreria documents = new Llibreria();
        documents.addDocument(d1); 
        documents.addDocument(d2);
        documents.addDocument(d3);
        documents.addDocument(d4);
        documents.addDocument(d5);
        documents.addDocument(d6);

        //PRIMER PROVEM AMB UNA QUERY CONSTANT I UNA K VARIABLE

        //Construim la consulta per un primer test
        Integer k = 3;
        Paraula[] query = new Paraula[k];
        query[0] = paraules.inserirObtenirParaula("hola"); // Doc 1 = 2 cops, doc 2 = 1 cos, doc 6 = 1 cop
        query[1] = paraules.inserirObtenirParaula("que");  //Document 5 = 1 cop
        query[2] = paraules.inserirObtenirParaula("tal");  //Ningu
        //han de sortir el 1, despr
        //l'atribut frase nomes ens importa pel mode 2
        ConsultaRellevancia consulta = new ConsultaRellevancia(1,query, "", 1, documents);

        //amb la k = 1 nomes sortira Doc1 (l'unic amb valor 2)
        Llibreria resultat = new Llibreria();
        resultat.addDocument(d1);
        assertEquals(resultat.getSetDocuments(), consulta.getDocs().getSetDocuments());

        //amb la k = 3 sortiran l'1 i despres tenim un triple empat (resolt alfabeticament) -> Sortiran 2 i 5
        consulta = new ConsultaRellevancia(3,query, "", 1, documents);
        resultat = new Llibreria();
        resultat.addDocument(d1);
        resultat.addDocument(d2);
        resultat.addDocument(d5);
        assertEquals(resultat.getSetDocuments(), consulta.getDocs().getSetDocuments());

        //amb la k = 5 sortiran l'1 i despres tenim el triple empat i per ultim (tenim Doc 3 i Doc 4 amb count = 0) el 3 (per alfabetic).
        consulta = new ConsultaRellevancia(5,query, "", 1, documents);
        resultat.addDocument(d6);
        resultat.addDocument(d3);
        assertEquals(resultat.getSetDocuments(), consulta.getDocs().getSetDocuments());


        //provem a mostrar tots els documents
        consulta = new ConsultaRellevancia(6,query, "", 1, documents);
        resultat.addDocument(d4);
        assertEquals(resultat.getSetDocuments(), consulta.getDocs().getSetDocuments());


        //PASSEM A UNA QUERI VARIABLE I UNA K CONSTANT
        //agafem 3 per deixar una bona proporcio de documents fora.

        //provem amb una paraula
        query = new Paraula[1];
        query[0] = paraules.inserirObtenirParaula("document");
        consulta = new ConsultaRellevancia(3,query, "", 1, documents);
        resultat = new Llibreria();

        //Docs: d5-7, d2-2, d1-1, d3-1, d4-1, d6-0
        resultat.addDocument(d5);
        resultat.addDocument(d2);
        resultat.addDocument(d1);
        assertEquals(resultat.getSetDocuments(), consulta.getDocs().getSetDocuments());


        //diverses paraules
        query = new Paraula[8];
        query[0] = paraules.inserirObtenirParaula("document");
        //Docs: d5-7, d2-2, d1-1, d3-1, d4-1, d6-0
        query[1] = paraules.inserirObtenirParaula("hola");
        //Docs: d5-7, d1-3, d2-3, d3-1, d4-1, d6-1       (suma d'ocurrencies)
        query[2] = paraules.inserirObtenirParaula("que");
        //Docs: d5-8, d1-3, d2-3, d3-1, d4-1, d6-1       (suma d'ocurrencies)
        query[3] = paraules.inserirObtenirParaula("frase");
        //Docs: d5-7, d1-4, d2-3, d4-2, d3-1, d6-1       (suma d'ocurrencies)
        query[4] = paraules.inserirObtenirParaula("no");
        //Docs: d5-7, d1-3, d2-3, d3-3, d4-1, d6-1       (suma d'ocurrencies)
        query[5] = paraules.inserirObtenirParaula("arbre");
        //Docs: d5-7, d1-3, d2-3, d3-1, d4-1, d6-1       (suma d'ocurrencies)
        query[6] = paraules.inserirObtenirParaula("primer");
        //Docs: d5-7, d1-4, d2-3, d3-1, d4-2, d6-1       (suma d'ocurrencies)
        query[7] = paraules.inserirObtenirParaula("hola");
        //Docs: d5-7, d1-5, d2-4, d3-1, d4-1, d6-2       (suma d'ocurrencies)
        consulta = new ConsultaRellevancia(3,query, "", 1, documents);
        resultat = new Llibreria();

        //Els tres primers documents
        resultat.addDocument(d5);
        resultat.addDocument(d1);
        resultat.addDocument(d2);
        assertEquals(resultat.getSetDocuments(), consulta.getDocs().getSetDocuments());
        //provem fent k=4 per la mateixa query, per fer double check
        consulta = new ConsultaRellevancia(4,query, "", 1, documents);
        //El quart document 
        resultat.addDocument(d3);
        assertEquals(resultat.getSetDocuments(), consulta.getDocs().getSetDocuments());


    }

}
