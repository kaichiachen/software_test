package UnitConverter;

import UnitConverter.AbstractUnitConverter;
import UnitConverter.AbstractUnitConverterFactory;
import UnitConverter.ImperialUnitConverter;

public class StubImperialConverterFactory extends AbstractUnitConverterFactory {
    @Override
    public AbstractUnitConverter CreateUnitConverter(int converterType) {
        return new ImperialUnitConverter();
    }
}
