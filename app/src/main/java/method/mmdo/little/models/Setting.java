package method.mmdo.little.models;

import java.io.Serializable;

public class Setting implements Serializable {
    public static final String SETTING = "setting";
    private static Setting instance;
    private int dimension;

    private Setting(int dimension) {
        this.dimension = dimension;
    }

    public static Setting of(int dimension) {
        instance = new Setting(dimension);
        return instance;
    }

    public int getDimension() {
        return dimension;
    }
}
