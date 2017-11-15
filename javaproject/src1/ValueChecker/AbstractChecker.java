package ValueChecker;

import UnitConverter.AbstractUnitConverterFactory;

public abstract class AbstractChecker {

    public AbstractUnitConverterFactory UnitConverterFactory = null;

    public abstract boolean isInputValid(float height, float weight, int unitType);
    public abstract boolean isBMIValid(float bmi);
    public abstract float checkFloatInput(String input);
    public abstract int checkIntInput(String input);
}
