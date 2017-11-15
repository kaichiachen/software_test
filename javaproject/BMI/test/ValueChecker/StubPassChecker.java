package ValueChecker;

public class StubPassChecker extends AbstractChecker {
    @Override
    public boolean isInputValid(float height, float weight, int unitType) {
        return true;
    }

    @Override
    public boolean isBMIValid(float bmi) {
        return true;
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
