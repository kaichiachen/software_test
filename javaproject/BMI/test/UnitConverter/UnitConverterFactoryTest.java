package UnitConverter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnitConverterFactoryTest {
    @Test
    void createUnitConverter_CreateMetricUnitConverter() {
        AbstractUnitConverterFactory factory = new UnitConverterFactory();
        assertEquals(MetricUnitConverter.class, factory.CreateUnitConverter(0).getClass());
    }

    @Test
    void createUnitConverter_CreateImperialUnitConverter() {
        AbstractUnitConverterFactory factory = new UnitConverterFactory();
        assertEquals(ImperialUnitConverter.class, factory.CreateUnitConverter(1).getClass());
    }

    @Test
    void createUnitConverter_CreateNullUnitConverter() {
        AbstractUnitConverterFactory factory = new UnitConverterFactory();
        assertNull(factory.CreateUnitConverter(-1));
        assertNull(factory.CreateUnitConverter(2));
    }

}