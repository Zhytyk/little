package method.mmdo.little.problem;

import java.util.ArrayList;
import java.util.List;

public class Little {
    private List<Node> nodes;

    private Little(Double[][] input) {
        this.nodes = new ArrayList<>();

        Cell[][] matrix = new Cell[input.length][input.length];
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input.length; j++) {
                matrix[i][j] = Cell.of(i, j, input[i][j]);
            }
        }

        nodes.add(Node.create(matrix));
    }

    public static Little get(Double[][] input) {
        return new Little(input);
    }

    public Result solve() {
        nodes.get(0).construct();
        return null;
    }
}
