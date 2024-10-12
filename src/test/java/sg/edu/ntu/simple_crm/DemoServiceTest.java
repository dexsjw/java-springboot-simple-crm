package sg.edu.ntu.simple_crm;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DemoServiceTest {
    
    DemoService demoService;

    @BeforeEach
    public void init() {
        System.out.println("Before each test");
        demoService = new DemoService();
    }

    @Test
    public void testAdd() {
        // 1. SETUP
        // Create the instance of the class to be tested
        // DemoService demoService = new DemoService();
        // assertEquals(8, demoService.add(3,5), "3 + 5 should be 8, dumb dumb");

        // Define the expected result
        int expectedResult = 8;

        // 2. EXECUTE
        // Call the method to be tested
        int actualResult = demoService.add(3, 5);

        // 3. ASSERT
        // Compare that actual result = expected result
        assertEquals(expectedResult, actualResult, "3 + 5 should be 8.");
    }

    @Test
    public void testSubstract() {
        // 1.SETUP
        // Create the instance to be tested
        // DemoService demoService = new DemoService();

        // Define expected result
        int expectedResult = 2;

        // 2.EXECUTE
        // Call the method to be tested
        int actualResult = demoService.subtract(5, 3);

        // 3.ASSERT
        // Compare actual with expected
        assertEquals(expectedResult, actualResult, "5 - 3 should be 2.");
    }

    @Test
    public void testMultiply() {
        // 1.SETUP
        int expectedResult = 20;

        // 2.EXECUTE
        int actualResult = demoService.multiply(4, 5);

        // 3.ASSERT
        assertEquals(expectedResult, actualResult, "4 * 5 should be 20.");
    }

    @Test
    public void testDivide() {
        // 1.SETUP
        int expectedResult = 3;

        // 2.EXECUTE
        int actualResult = demoService.divide(27, 9);

        // 3.ASSERT
        assertEquals(expectedResult, actualResult);
        assertThrows(ArithmeticException.class, () -> demoService.divide(15, 0));
    }

    @Test
    public void testIsEven() {
        assertTrue(demoService.isEven(927592));
        assertFalse(demoService.isEven(7382843));
    }
}
