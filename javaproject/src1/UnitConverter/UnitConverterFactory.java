package UnitConverter;

public class UnitConverterFactory extends AbstractUnitConverterFactory {
    @Override
    public AbstractUnitConverter CreateUnitConverter(int converterType) {
        switch (converterType) {
            case 0 : return new MetricUnitConverter();
            case 1 : return new ImperialUnitConverter();
            default: return null;
        }
    }
}
