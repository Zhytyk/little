package method.mmdo.little.models;


public class Setting {
    private int dimension;

    private Setting(int dimension) {
        this.dimension = dimension;
    }

    public static Setting of(int dimension) {
        if (dimension <= 0) {
            throw new IllegalArgumentException("Invalid dimension");
        }

        return new Setting(dimension);
    }

    public int getDimension() {
        return dimension;
    }
}
