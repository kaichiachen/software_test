package Main;

import java.security.InvalidParameterException;

import UnitConverter.StubImperialConverterFactory;
import UnitConverter.StubMetricConverterFactory;
import UnitConverter.StubNullConverterFactory;
import UnitConverter.UnitConverterFactory;
import ValueChecker.BMIChecker;
import ValueChecker.StubNullChecker;
import ValueChecker.StubPassChecker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BMICalculatorTest {

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
    float[][] ValidImperialProvider = {
            {2.0f,100.0f,122.0606842f},
            {6.0f,200.0f,27.1245995f},
            {1.0f,650.0f,3173.5778809f},
            {8.0f,720.0f,54.9273109f}};

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void result_NullBMIChecker() {
        BMICalculator calculator = new BMICalculator(null, new UnitConverterFactory());
        assertThrows(InvalidParameterException.class, ()->calculator.result(0,0,0),"The checker cannot be null.");
    }

    @Test
    void result_NullUnitConverterFactory() {
        BMICalculator calculator = new BMICalculator(new BMIChecker(new UnitConverterFactory()), null);
        assertThrows(InvalidParameterException.class, ()->calculator.result(0,0,0),"The unit converter factory cannot be null.");
    }

    @Test
    void result_InvalidMetricInput() {
        BMICalculator calculator = new BMICalculator(new StubNullChecker(), new StubMetricConverterFactory());
        for (float[] item: InvalidMetricProvider) {
            assertThrows(InvalidParameterException.class, ()->calculator.result(item[0],item[1],0),"The parameters are not invalid.");
        }
    }

    @Test
    void result_InvalidImperialInput() {
        BMICalculator calculator = new BMICalculator(new StubNullChecker(), new StubImperialConverterFactory());
        for (float[] item: InvalidImperialProvider) {
            assertThrows(InvalidParameterException.class, ()->calculator.result(item[0],item[1],0),"The parameters are not invalid.");
        }
    }

    @Test
    void result_UnitConverterFactoryCreateNullUnitConverter() {
        BMICalculator calculator = new BMICalculator(new StubPassChecker(), new StubNullConverterFactory());
        assertThrows(InvalidParameterException.class, ()->calculator.result(0,0,0),"The unit converter of this unit type does not exists.");
    }

    @Test
    void result_UnitConverterFactoryCreateMetricUnitConverter() {
        BMICalculator calculator = new BMICalculator(new StubPassChecker(), new StubMetricConverterFactory());
        float bmi = calculator.result(2.0f,100.0f,0);
        assertEquals(25.0f, bmi, 1e-6);
    }

    @Test
    void result_UnitConverterFactoryCreateImperialUnitConverter() {
        BMICalculator calculator = new BMICalculator(new StubPassChecker(), new StubImperialConverterFactory());
        for (float[] item: ValidImperialProvider) {
            float bmi = calculator.result(item[0], item[1],1);
            assertEquals(item[2], bmi, 1e-6);
        }
    }

    @Test
    void fitType_NullBMIChecker() {
        BMICalculator calculator = new BMICalculator(null, null);
        assertThrows(InvalidParameterException.class, ()->calculator.fitType(0.0f),"The checker cannot be null.");
    }

    @Test
    void fitType_InvalidInput() {
        BMICalculator calculator = new BMICalculator(new StubNullChecker(), null);
        assertThrows(InvalidParameterException.class, ()->calculator.fitType(-1.0f),"The bmi should be positive.");
    }

    @Test
    void fitType_ThinType() {
        BMICalculator calculator = new BMICalculator(new StubPassChecker(), null);
        assertEquals(BMIType.thin, calculator.fitType(5.0f));
    }

    @Test
    void fitType_NormalType() {
        BMICalculator calculator = new BMICalculator(new StubPassChecker(), null);
        assertEquals(BMIType.normal, calculator.fitType(20.0f));
    }

    @Test
    void fitType_FatType() {
        BMICalculator calculator = new BMICalculator(new StubPassChecker(), null);
        assertEquals(BMIType.fat, calculator.fitType(25.0f));
    }

    @Test
    void fitType_VeryFatType() {
        BMICalculator calculator = new BMICalculator(new StubPassChecker(), null);
        assertEquals(BMIType.veryfat, calculator.fitType(30.0f));
    }
}