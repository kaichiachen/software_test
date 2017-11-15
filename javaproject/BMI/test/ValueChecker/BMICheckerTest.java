package ValueChecker;

import UnitConverter.StubImperialConverterFactory;
import UnitConverter.StubMetricConverterFactory;
import UnitConverter.StubNullConverterFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;

import static org.junit.jupiter.api.Assertions.*;

class BMICheckerTest {

    float[][] InvalidMetricProvider = {
            {-2.0f,-3.0f},
            {1.0f,-1.0f},
            {5.0f,-2.0f},
            {15.0f,-3.0f},
            {-5.0f,400.0f},
            {6.0f,200.0f},
            {20.0f,250.0f},
            {-10.0f,700.0f},
            {1.0f,650.0f},
            {8.0f,720.0f},
            {40.0f,530.0f},
            {-20.0f,1200.0f},
            {2.0f,1300.0f},
            {5.0f,1150.0f},
            {50.0f,1500.0f}};
    float[][] InvalidImperialProvider = {
            {-2.0f,-3.0f},
            {1.0f,-1.0f},
            {5.0f,-2.0f},
            {15.0f,-3.0f},
            {-5.0f,400.0f},
            {20.0f,250.0f},
            {-10.0f,700.0f},
            {40.0f,530.0f},
            {-20.0f,1200.0f},
            {2.0f,1300.0f},
            {5.0f,1150.0f},
            {50.0f,1500.0f}};

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void isInputValidTest_NullUnitConverterFactory() {
        BMIChecker checker = new BMIChecker(null);
        assertThrows(InvalidParameterException.class, ()->checker.isInputValid(2.0f,200.0f,0),"The unit converter factory cannot be null.");
    }

    @Test
    void isInputValidTest_UnitConverterFactoryCreateNullUnitConverter() {
        BMIChecker checker = new BMIChecker(new StubNullConverterFactory());
        assertThrows(InvalidParameterException.class, ()->checker.isInputValid(2.0f,200.0f,0),"The unit converter of this unit type does not exists.");
    }

    @Test
    void isInputValidTest_UnitConverterFactoryCreateMetricUnitConverter_Valid() {
        BMIChecker checker = new BMIChecker(new StubMetricConverterFactory());
        assertTrue(checker.isInputValid(2.0f, 200.0f, 0));
    }

    @Test
    void isInputValidTest_UnitConverterFactoryCreateMetricUnitConverter_Invalid() {
        BMIChecker checker = new BMIChecker(new StubMetricConverterFactory());
        for (float[] item: InvalidMetricProvider){
            assertFalse(checker.isInputValid(item[0], item[1], 0));
        }
    }

    @Test
    void isInputValidTest_UnitConverterFactoryCreateImperialUnitConverter_Valid() {
        BMIChecker checker = new BMIChecker(new StubImperialConverterFactory());
        assertTrue(checker.isInputValid(5.0f, 250.0f,1));
    }

    @Test
    void isInputValidTest_UnitConverterFactoryCreateImperialUnitConverter_Invalid() {
        BMIChecker checker = new BMIChecker(new StubImperialConverterFactory());
        for (float[] item: InvalidImperialProvider) {
            assertFalse(checker.isInputValid(item[0], item[1], 1));
        }
    }

    @Test
    void isBMIValid_Valid() {
        BMIChecker checker = new BMIChecker(null);
        assertTrue(checker.isBMIValid(0));
    }

    @Test
    void isBMIValid_Invalid() {
        BMIChecker checker = new BMIChecker(null);
        assertFalse(checker.isBMIValid(-1));
    }

    @Test
    void checkFloatInput_Valid() {
        BMIChecker checker = new BMIChecker(null);
        assertEquals(3.14f, checker.checkFloatInput("3.14"));
    }

    @Test
    void checkFloatInput_Invalid() {
        BMIChecker checker = new BMIChecker(null);
        assertThrows(NumberFormatException.class, ()->checker.checkFloatInput("3..14"));
    }

    @Test
    void checkIntInput_Valid() {
        BMIChecker checker = new BMIChecker(null);
        assertEquals(1, checker.checkIntInput("1"));
    }

    @Test
    void checkIntInput_Invalid() {
        BMIChecker checker = new BMIChecker(null);
        assertThrows(NumberFormatException.class, ()->checker.checkIntInput("$"));
    }
}