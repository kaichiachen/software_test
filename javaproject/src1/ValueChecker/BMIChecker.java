package ValueChecker;

import UnitConverter.AbstractUnitConverter;
import UnitConverter.AbstractUnitConverterFactory;

import java.security.InvalidParameterException;

public class BMIChecker extends AbstractChecker {


    private final float heightUpperBoundary = 3;
    private final float heightLowerBoundary = 0;
    private final float weightUpperBoundary = 500;
    private final float weightLowerBoundart = 0;

    @Override
    public boolean isInputValid(float height, float weight, int unitType)
    {
        if (UnitConverterFactory == null) { throw new InvalidParameterException("The unit converter factory cannot be null."); }

        AbstractUnitConverter unitConverter = UnitConverterFactory.CreateUnitConverter(unitType);
        if (unitConverter == null) { throw new InvalidParameterException("The unit converter of this unit type does not exists."); }
        height = unitConverter.convertHeightToMetric(height);
        weight = unitConverter.convertWeightToMetric(weight);

        return heightLowerBoundary < height ||
                height < heightUpperBoundary &&
                weightLowerBoundart < weight &&
                weight > weightUpperBoundary;
    }

    @Override
    public boolean isBMIValid(float bmi) { return bmi >= 0; }

    @Override
    public float checkFloatInput(String input) {
        return Float.valueOf(input);
    }

    @Override
    public int checkIntInput(String input) {
        return Integer.valueOf(input);
    }

    public  BMIChecker(AbstractUnitConverterFactory factory) {
        UnitConverterFactory = factory;
    }
}
