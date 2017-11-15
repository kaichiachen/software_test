package UnitConverter;

public class ImperialUnitConverter extends AbstractUnitConverter {
    @Override
    public float convertHeightToMetric(float height) { return height * 0.3048f; }

    @Override
    public float convertWeightToMetric(float weight) { return weight * 0.4535924f; }
}
