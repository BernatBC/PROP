package test.classes;

import classes.ConsultaAutors;
import classes.Frase;

import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.*;
import org.junit.jupiter.api.DisplayName;

import java.util.HashSet;
import java.util.Set;



public class ConsultaAutorsTest {
    
    @Before                                         
    public void setUp() {
        System.out.println("SetUp Completed!");
    }


    @Test
    @DisplayName("Test Autors")   
    public void COnsutaAutorsPerPrefix() {
        // Init
        ConsultaAutors autors = new ConsultaAutors();

        Frase q1 = new Frase("Oscar Ramos");
        Frase q2 = new Frase("Salvador Roura");
        Frase q3 = new Frase("Manel Macarra");
        Frase q4 = new Frase("Norman Norman");
        Frase q5 = new Frase("Pol Ramos");
        Frase q6 = new Frase("Manel Ramos");
        autors.addAutor(q1);
        autors.addAutor(q2);
        autors.addAutor(q3);
        autors.addAutor(q4);
        autors.addAutor(q5);
        autors.addAutor(q6);





        //ASSERT
        Set<Frase> result = new HashSet<>();
        result.add(q6); //Manel Ramos
        result.add(q3); //Manel Macarra
        assertEquals(result, autors.donaAutors("Man"));

        //veiem que els case sensitive, els noms propis i cognoms tenen sempre la primera lletra Majuscula
        assertEquals(null, autors.donaAutors("man"));


        //provem ara pel cognom Ramos, nomes agafant Ra
        result = new HashSet<>();
        result.add(q1); //Oscar Ramos
        result.add(q5); //Pol Ramos
        result.add(q6); //Manel Ramos
        assertEquals(result, autors.donaAutors("Ra"));

        //Cas nomes R, resultat igual que l'anterior cas però ara s'afegeix salvador Roura
        result.add(q2); //Salvador Roura
        assertEquals(result, autors.donaAutors("R"));

        //cas prefix null o cas prefix espai
        assertEquals(null, autors.donaAutors(""));
        assertEquals(null, autors.donaAutors(" "));
    }
}
