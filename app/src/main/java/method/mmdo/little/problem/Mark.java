package method.mmdo.little.problem;

public class Mark {
    private boolean infinity;
    private int value;

    private Mark() {
        this.infinity = true;
        this.value = 0;
    }

    public static Mark get() {
        return new Mark();
    }

    public void setValue(int value) {
        if (!infinity) {
            throw new IllegalStateException("Value has been already initialized!");
        }

        infinity = false;
        this.value = value;
    }

    public int getValue() {
        if (infinity) {
            throw new IllegalStateException("Value hasn't been initialized yet!");
        }

        return value;
    }
}
