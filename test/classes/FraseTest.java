package test.classes;

import classes.Frase;
import classes.Paraula;
import classes.Vocabulari;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


import java.util.ArrayList;

import org.junit.*;
import org.junit.jupiter.api.DisplayName;

public class FraseTest {
    
    @Before                                         
    public void setUp() {
        System.out.println("SetUp Completed!");
    }

    @Test
    @DisplayName("Test frase conte paraula")   
    public void fraseConteParaula() {
        // Init
        Frase fraseP2 = new Frase("No vull fer testos a prop.");

        // Assert
        assertEquals(true, fraseP2.conteParaula("prop"));
    }


    @Test
    @DisplayName("Test frase donawords")   
    public void fraseDonaWordsRetornaHashMapDeIdparaulaINumeroAparicions() {
        //Guardem les paraules per tal de mantenir un mateix index per una mateixa paraula, encara que aquesta es torni a crear posteriorment
        Vocabulari words = new Vocabulari();
        // Init
        ArrayList<Paraula> q = new ArrayList<>();
        q.add(words.inserirObtenirParaula("hello"));
        q.add(words.inserirObtenirParaula("world"));
        q.add(words.inserirObtenirParaula("hello"));
        q.add(words.inserirObtenirParaula("hello"));
        Frase frase2 = new Frase(q, "hello world hello");
        System.out.print(frase2.donaWords());
       
        //ASSERTS

        //mirem el numero de aparicions de la paraula hello i a mes comprovem que te Id = 0.
        assertEquals(3, frase2.donaWords().get(words.inserirObtenirParaula("hello").getId()));

        //mirem el numero de aparicions de la paraula world i a mes comprovem que te Id = 1.
        assertEquals(1,frase2.donaWords().get(words.inserirObtenirParaula("world").getId()));

        //comprovem que el HashMap nomes te 2 elements i, per etant, un hipotetic element 3 (amb Id = 2) apareix "null" cops.
        assertEquals(null,frase2.donaWords().get(words.inserirObtenirParaula("no").getId()));

    }


    @Test                                               
    @DisplayName("Test frase conte sequencia")   
    public void fraseConteSequencia() {
        // Init
        Frase fraseC = new Frase("No vull fer testos a prop.");
        String[] paraules = {"No", "vull", "fer"};
        
        
        // Asserts

        //Hem de tenir en compte que el constructor de Frase NO guarda les paraules en minuscula (podent crear dues paraules per la mateixa [Hola i hola]).
        //Les tres primeres paraules.
        assertEquals(true, fraseC.conteSequencia(paraules));

        //Tota la frase.
        String[] paraules2 = {"No", "vull", "fer", "testos", "a", "prop"};
        assertEquals(true, fraseC.conteSequencia(paraules2));

        //Repetim una paraula.
        String[] paraules3 = {"No", "no", "fer"};
        assertEquals(false, fraseC.conteSequencia(paraules3));

        //Tres paraules empty.
        String[] paraules4 = {"", "", ""};
        assertEquals(false, fraseC.conteSequencia(paraules4));

        //Una paraula empty.
        String[] paraules5 = {""};
        assertEquals(false, fraseC.conteSequencia(paraules5));

        //Una paraula amb majuscula, quan no deberia.
        String[] paraules6 = {"Fer"};
        assertEquals(false, fraseC.conteSequencia(paraules6));

    }


    @Test                                               
    @DisplayName("Test frase conte caracters")   
    public void fraseConteCaractersEnOrdre() {
        // Init
        Frase fraseC = new Frase("Vull buscar-ne lletres aqui.");
        String lletres = "-ne lletres";
        // Assert
        assertTrue(fraseC.conteCaracters(lletres));
        //ultima lletra.
        assertTrue(fraseC.conteCaracters("i"));
        //Mirem que la funcio conteCaracters diferencia correctament les majuscules i les minuscules.
        assertFalse(fraseC.conteCaracters("vull"));
        assertTrue(fraseC.conteCaracters("Vull"));
        //canviem nomes una lletra
        assertFalse(fraseC.conteCaracters("Vull buscar-ne lletra"));
        //Si passem un string sense caràcters sempre retornarà true ja que una subword de qualsevol paraula pot ser "".
        assertTrue(fraseC.conteCaracters(""));
        //Provem de buscar un espai en blanc.
        assertTrue(fraseC.conteCaracters(" "));


        
    }



    


    
}
