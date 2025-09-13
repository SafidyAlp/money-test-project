package fr.emse.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MoneyBagTest {
    
    private Money f12CHF;
    private Money f14CHF;
    private Money f7USD;
    private Money f21USD;
    private MoneyBag fMB1;
    private MoneyBag fMB2;

    @BeforeEach
    public void setUp() {
        f12CHF = new Money(12, "CHF");
        f14CHF = new Money(14, "CHF");
        f7USD = new Money(7, "USD");
        f21USD = new Money(21, "USD");
        fMB1 = new MoneyBag(f12CHF, f7USD);
        fMB2 = new MoneyBag(f14CHF, f21USD);
    }

    @Test
    public void testBagEquals() {
        assertTrue(!fMB1.equals(null));
        assertEquals(fMB1, fMB1);
        assertTrue(!fMB1.equals(f12CHF));
        assertTrue(!f12CHF.equals(fMB1));
        assertTrue(!fMB1.equals(fMB2));
    }

    @Test
    public void testBagSimpleAdd() {
        IMoney result = fMB1.add(f12CHF);
        Money[] expectedBag = { new Money(24, "CHF"), f7USD };
        MoneyBag expected = new MoneyBag(expectedBag);
        assertEquals(expected, result);
    }

    @Test
    public void testSimpleBagAdd() {
        IMoney result = f12CHF.add(fMB1);
        Money[] expectedBag = { new Money(24, "CHF"), f7USD };
        MoneyBag expected = new MoneyBag(expectedBag);
        assertEquals(expected, result);
    }

    @Test
    public void testBagBagAdd() {
        IMoney result = fMB1.add(fMB2);
        Money[] expectedBag = { 
            new Money(26, "CHF"), 
            new Money(28, "USD") 
        };
        MoneyBag expected = new MoneyBag(expectedBag);
        assertEquals(expected, result);
    }
}