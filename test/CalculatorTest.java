
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    Calculator cal ;

    @BeforeEach
    void setUp(){
        cal= new Calculator();
    }
    
    @org.junit.jupiter.api.Test
    void evalTest()  {
        //base test
        assertEquals( cal.Eval("1+4^2^3*2"),131073);
        assertEquals( cal.Eval("4^2^3*2+22"),131094);
        assertEquals(cal.Eval("1+2+2-2^2*3+7/2"),-3.5);
        assertEquals(cal.Eval("1+2+2/8-2^2*3+7/2"),-5.25);
        //test for  brackets
        assertEquals(cal.Eval("2^2^1*2*(2+1)+1"),25);
        assertEquals(cal.Eval("2^1-3*1-(2^3+8)"),-17);
        assertEquals(cal.Eval("-1*(2+1)-2-2^3"),-13.0);
        assertEquals(cal.Eval("(2+1)-1^2-2^3"),-6);
        //test for sqrt
        assertEquals(cal.Eval("√9"),3.0);
        assertEquals(cal.Eval("√9+3-6"),0);
        assertEquals(cal.Eval("-6+√9+3+6"),6);
        //test for trigonometry and log functions
        assertEquals(cal.Eval("√9+Cos60*Tan45"),3.5);
        assertEquals(cal.Eval("Sin60^2+Cos60^2"),1);
        // divide by  zero
        assertThrows(ArithmeticException.class,() -> cal.Eval("2/0"));
        //test for exception
        String str9 = "^2^1^1^1*2*2+1*1^2-2^3+8";//throw exception;
        assertThrows(IllegalArgumentException.class,() -> cal.Eval(str9));
        assertThrows(ArithmeticException.class,() ->cal.Eval("Tan90"));
    }
    @org.junit.jupiter.api.Test
    void factorialTest(){

    }
    @org.junit.jupiter.api.Test
    void binomTest(){

    }
}