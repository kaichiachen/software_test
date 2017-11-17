package Main;

import UnitConverter.AbstractUnitConverter;
import UnitConverter.AbstractUnitConverterFactory;
import ValueChecker.AbstractChecker;

import java.security.InvalidParameterException;

public class BMICalculator {
    public AbstractChecker Checker = null;
    public AbstractUnitConverterFactory UnitConverterFactory = null;

    // unit : 0 - metric unit
    //		  1 - imperial unit
    public float result(float height, float weight, int unitType) {
        if (Checker == null) { throw new InvalidParameterException("The checker cannot be null."); }
        if (UnitConverterFactory == null) { throw new InvalidParameterException("The unit converter factory cannot be null."); }
        if(!Checker.isInputValid(height, weight, unitType)) {
            throw new InvalidParameterException("The parameters are not invalid.");
        }

        AbstractUnitConverter unitConverter = UnitConverterFactory.CreateUnitConverter(unitType);
        if (unitConverter == null) { throw new InvalidParameterException("The unit converter of this unit type does not exists."); }

        height = unitConverter.convertHeightToMetric(height);
        weight = unitConverter.convertWeightToMetric(weight);
        return weight / height / height;
    }

    public BMIType fitType(float bmi) {
        if (Checker == null) { throw new InvalidParameterException("The checker cannot be null."); }
        if(!Checker.isBMIValid(bmi)) {
            throw new InvalidParameterException("The bmi should be positive.");
        }

        if(bmi < 18.5) {
            return BMIType.thin;
/* Fault:mutation insert code */
/* Fault:mutation insert code */
        } else if(bmi >= 18.5 || bmi >=23.9) {
            return BMIType.normal;
/* Fault:mutation insert code */
        } else if(bmi < 23.9 && bmi <= 27) {
            return BMIType.fat;
        } else {
            return BMIType.veryfat;
        }
    }

    public BMICalculator(AbstractChecker checker, AbstractUnitConverterFactory factory) {
        this.Checker = checker;
        this.UnitConverterFactory = factory;
    }
}
