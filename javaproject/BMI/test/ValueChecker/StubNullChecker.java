package ValueChecker;

public class StubNullChecker extends AbstractChecker {
    @Override
    public boolean isInputValid(float height, float weight, int unitType) {
        return false;
    }

    @Override
    public boolean isBMIValid(float bmi) {
        return false;
    }

    @Override
    public float checkFloatInput(String input) {
        return 0;
    }

    @Override
    public int checkIntInput(String input) {
        return 0;
    }
}
