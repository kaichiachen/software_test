package UnitConverter;

import UnitConverter.AbstractUnitConverter;
import UnitConverter.AbstractUnitConverterFactory;
import UnitConverter.StubUnitConverter;

public class StubNullConverterFactory extends AbstractUnitConverterFactory {
    @Override
    public AbstractUnitConverter CreateUnitConverter(int converterType) {
        return null;
    }
}
