package test.classes;
import classes.Paraula;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.*;
import org.junit.jupiter.api.DisplayName;

public class ParaulaTest {
    
    @Before                                         
    public void setUp() {
        System.out.println("SetUp Completed!");
    }
    
    @Test
    @DisplayName("Test 1 paraula")   
    public void UnaParaulaTest() {
        
        Paraula p1 = new Paraula("test");
        assertEquals("test", p1.getParaula());
        assertEquals(1, p1.getOcurrencia());
        assertEquals(0, p1.getId());

        p1.incrementarOcurrencia();
        assertEquals("test", p1.getParaula());
        assertEquals(2, p1.getOcurrencia());
        assertEquals(0, p1.getId());

        p1.decrementarOcurrencia();
        assertEquals("test", p1.getParaula());
        assertEquals(1, p1.getOcurrencia());
        assertEquals(0, p1.getId());

        p1.decrementarOcurrencia();
        assertEquals("test", p1.getParaula());
        assertEquals(0, p1.getOcurrencia());
        assertEquals(0, p1.getId());

        p1.decrementarOcurrencia();
        assertEquals("test", p1.getParaula());
        assertEquals(0, p1.getOcurrencia());
        assertEquals(0, p1.getId());

    }

    @Test
    @DisplayName("Test varies paraula")   
    public void VariesParaulaTest() {
        
        Paraula p1 = new Paraula("test");
        assertEquals("test", p1.getParaula());
        assertEquals(1, p1.getOcurrencia());
        assertEquals(0, p1.getId());

        Paraula p2 = new Paraula("projecte");
        assertEquals("test", p1.getParaula());
        assertEquals(1, p1.getOcurrencia());
        assertEquals(0, p1.getId());
        assertEquals("projecte", p2.getParaula());
        assertEquals(1, p2.getOcurrencia());
        assertEquals(1, p2.getId());

        p2.incrementarOcurrencia();
        assertEquals("test", p1.getParaula());
        assertEquals(1, p1.getOcurrencia());
        assertEquals(0, p1.getId());
        assertEquals("projecte", p2.getParaula());
        assertEquals(2, p2.getOcurrencia());
        assertEquals(1, p2.getId());

        p2.decrementarOcurrencia();
        assertEquals("test", p1.getParaula());
        assertEquals(1, p1.getOcurrencia());
        assertEquals(0, p1.getId());
        assertEquals("projecte", p2.getParaula());
        assertEquals(1, p2.getOcurrencia());
        assertEquals(1, p2.getId());

    }
}