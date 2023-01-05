package shortest_path;

import lombok.Getter;
import lombok.Setter;

import java.util.*;
import java.util.stream.Stream;

/**
 * @author Polyakov Dmitry
 * @created 05.01.2023 11:53 PM
 */
@Getter
@Setter
public class Node implements Comparable<Node> {
    private int distance = Integer.MAX_VALUE;
    private final String name;

    private List<Node> shortestPath = new LinkedList<>();
    private final Map<Node, Integer> adjacentNodes = new HashMap<>();

    public Node(String name) {
        this.name = name;
    }

    public void addAdjacentNode(Node node, int weight) {
        adjacentNodes.put(node, weight);
    }

    @Override
    public int compareTo(Node node) {
        return Integer.compare(this.distance, node.getDistance());
    }


    public static void calculateShortestPath(Node source) {
        source.setDistance(0);
        Set<Node> settleNodes = new HashSet<>();
        Queue<Node> unsettledNodes = new PriorityQueue<>(Collections.singleton(source));
        while (!unsettledNodes.isEmpty()) {
            Node currentNode = unsettledNodes.poll();
            for (Map.Entry<Node, Integer> entry : currentNode.getAdjacentNodes().entrySet()) {
                if (!settleNodes.contains(entry.getKey())) {
                    evaluateDistanceAndPath(entry.getKey(), entry.getValue(), currentNode);
                    unsettledNodes.add(entry.getKey());
                }
            }
            settleNodes.add(currentNode);
        }
    }

    private static void evaluateDistanceAndPath(Node adjacentNode, int edgeWeight, Node sourceNode) {
        int newDistance = sourceNode.getDistance() + edgeWeight;
        if (newDistance < adjacentNode.getDistance()) {
            adjacentNode.setDistance(newDistance);
            adjacentNode.setShortestPath(Stream.concat(sourceNode.getShortestPath().stream()
                    , Stream.of(sourceNode)).toList());
        }
    }

    public static void printPaths(List<Node> nodes) {
        for (Node lastNode : nodes) {
            for (Node node : lastNode.getShortestPath()) {
                System.out.print(node.getName() + " -> ");
            }
            System.out.print(lastNode.getName() + " (distance: " + lastNode.getDistance() + ")");
        }
    }
}
