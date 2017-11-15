package UnitConverter;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ImperialUnitConverterTest {

    float[][] TestHeightValues = {
            {-1.0f, -0.3048f},
            {0.0f, 0.0f},
            {1.0f, 0.3048f}
    };

    float[][] TestWeightValues = {
            {-1.0f, -0.4535924f},
            {0.0f, 0.0f},
            {1.0f,0.4535924f}
    };

    @Test
    void convertHeightToMetric() {
        ImperialUnitConverter converter= new ImperialUnitConverter();
        for (float[] item: TestHeightValues)  {
            float metricHeight= converter.convertHeightToMetric(item[0]);
            assertEquals(item[1], metricHeight, 1e-6);
        }
    }

    @Test
    void convertWeightToMetric() {
        ImperialUnitConverter converter= new ImperialUnitConverter();
        for (float[] item: TestWeightValues)  {
            float metricWeight= converter.convertWeightToMetric(item[0]);
            assertEquals(item[1], metricWeight, 1e-6);
        }
    }

}