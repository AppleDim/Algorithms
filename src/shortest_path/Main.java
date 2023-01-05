package shortest_path;

import java.util.List;

/**
 * @author Polyakov Dmitry
 * @created 05.01.2023 11:52 PM
 */

public class Main {
    public static void main(String[] args) {
        Node nodeS = new Node("S");
        Node nodeT = new Node("T");
        Node nodeX1 = new Node("X1");
        Node nodeX2 = new Node("X2");
        Node nodeX3 = new Node("X3");
        Node nodeX4 = new Node("X4");
        Node nodeX5 = new Node("X5");

        nodeS.addAdjacentNode(nodeT, 7);
        nodeS.addAdjacentNode(nodeX1, 2);
        nodeS.addAdjacentNode(nodeX3, 3);
        nodeX1.addAdjacentNode(nodeX2, 3);
        nodeX1.addAdjacentNode(nodeX5, 1);
        nodeX2.addAdjacentNode(nodeT, 2);
        nodeX2.addAdjacentNode(nodeX4, 2);
        nodeX3.addAdjacentNode(nodeT, 5);
        nodeX3.addAdjacentNode(nodeX4, 4);
        nodeX4.addAdjacentNode(nodeT, 2);
        nodeX5.addAdjacentNode(nodeX3, 3);
        nodeX5.addAdjacentNode(nodeX2, 1);

        Node.calculateShortestPath(nodeS);
        Node.printPaths(List.of(nodeT));
    }
}
