import java.util.stream.DoubleStream;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static java.lang.IO.print;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class Calculator {
    static double add(double ... operands){
        return DoubleStream.of(operands)
                .sum();
    }

    static double multiply(double ... operands){
        return DoubleStream.of(operands)
                .reduce(1,(a,b)->a*b);
    }
}

@Nested
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

void main() {
 Calculator calculator = new Calculator();
 print(calculator.add(2.0, 2.0));

}
