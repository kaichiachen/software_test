package UnitConverter;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MetricUnitConverterTest {

    float[] values = { -1.0f, 0.0f, 1.0f };

    @Test
    void convertHeightToMetric() {
        MetricUnitConverter converter= new MetricUnitConverter();
        for (float value: values) {
            float metricHeight= converter.convertHeightToMetric(value);
            assertEquals(value, metricHeight,1e-6);
        }

    }

    @Test
    void convertWeightToMetric() {
        MetricUnitConverter converter= new MetricUnitConverter();
        for (float value: values) {
            float metricWeight= converter.convertWeightToMetric(value);
            assertEquals(value, metricWeight,1e-6);
        }
    }

}