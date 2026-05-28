import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    void add() {
        assertEquals(4.0, Calculator.add(2.0, 2.0));
    }

    @Test
    void multiply() {
        assertEquals(2.0, Calculator.multiply(2.0, 1.0));
    }
}