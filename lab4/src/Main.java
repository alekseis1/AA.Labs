import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Main {

    public static Map<Integer, Map<Integer, Integer>> generateSparseGraph(int nodes, int edges) {
        Random random = new Random();
        Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();

        for (int i = 0; i < nodes; i++) {
            graph.put(i, new HashMap<>());
        }

        for (int e = 0; e < edges; e++) {
            int node1 = random.nextInt(nodes);
            int node2 = random.nextInt(nodes);
            int weight = random.nextInt(10) + 1;

            if (node1 != node2) {
                graph.get(node1).put(node2, weight);
                graph.get(node2).put(node1, weight);
            }
        }

        return graph;
    }

    public static Map<Integer, Map<Integer, Integer>> generateDenseGraph(int nodes) {
        Random random = new Random();
        Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();

        for (int i = 0; i < nodes; i++) {
            Map<Integer, Integer> edges = new HashMap<>();
            for (int j = 0; j < nodes; j++) {
                if (i != j) {
                    int weight = random.nextInt(10) + 1;
                    edges.put(j, weight);
                }
            }
            graph.put(i, edges);
        }

        return graph;
    }

    public static Map<Integer, Integer> dijkstra(Map<Integer, Map<Integer, Integer>> graph, int start) {
        Map<Integer, Integer> distances = new HashMap<>();
        List<Integer> visited = new ArrayList<>();

        for (int node : graph.keySet()) {
            distances.put(node, Integer.MAX_VALUE);
        }
        distances.put(start, 0);

        while (visited.size() < graph.size()) {
            int current = getMinDistanceNode(distances, visited);
            visited.add(current);

            if (graph.containsKey(current)) {
                for (int neighbor : graph.get(current).keySet()) {
                    int weight = graph.get(current).get(neighbor);
                    int newDistance = distances.get(current) + weight;

                    if (newDistance < distances.get(neighbor)) {
                        distances.put(neighbor, newDistance);
                    }
                }
            }
        }

        return distances;
    }

    private static int getMinDistanceNode(Map<Integer, Integer> distances, List<Integer> visited) {
        int minNode = -1;
        int minDistance = Integer.MAX_VALUE;

        for (int node : distances.keySet()) {
            if (!visited.contains(node) && distances.get(node) < minDistance) {
                minNode = node;
                minDistance = distances.get(node);
            }
        }

        return minNode;
    }

    public static Map<Integer, Map<Integer, Integer>> floydWarshall(Map<Integer, Map<Integer, Integer>> graph) {
        Map<Integer, Map<Integer, Integer>> dist = new HashMap<>(graph);

        for (int k : graph.keySet()) {
            for (int i : graph.keySet()) {
                for (int j : graph.keySet()) {
                    if (dist.containsKey(i) && dist.containsKey(k) && dist.containsKey(j)
                            && dist.get(i).containsKey(k) && dist.get(k).containsKey(j)) {
                        Integer ikWeight = dist.get(i).get(k);
                        Integer kjWeight = dist.get(k).get(j);

                        if (ikWeight != null && kjWeight != null) {
                            int currentWeight = dist.get(i).get(j) != null ? dist.get(i).get(j) : Integer.MAX_VALUE;
                            if (currentWeight > ikWeight + kjWeight) {
                                dist.get(i).put(j, ikWeight + kjWeight);
                            }
                        }
                    }
                }
            }
        }

        return dist;
    }


    public static void main(String[] args) {
        int[] values = {4, 8, 16, 32, 64, 128, 256, 512};

        System.out.println("IS\tDijSparse\tDijDense\tFWSparse\tFWDense");
        for (int i : values) {
            Map<Integer, Map<Integer, Integer>> graph = generateSparseGraph(i, i * 2);

            long startTime = System.nanoTime();
            dijkstra(graph, 0);
            long endTime = System.nanoTime();
            double timeDijkstraSparse = (endTime - startTime) / 1_000_000_000.0;

            graph = generateDenseGraph(i);

            startTime = System.nanoTime();
            dijkstra(graph, 0);
            endTime = System.nanoTime();
            double timeDijkstraDense = (endTime - startTime) / 1_000_000_000.0;

            graph = generateSparseGraph(i, i * 2);

            startTime = System.nanoTime();
            floydWarshall(graph);
            endTime = System.nanoTime();
            double timeFloydWarshallSparse = (endTime - startTime) / 1_000_000_000.0;

            graph = generateDenseGraph(i);

            startTime = System.nanoTime();
            floydWarshall(graph);
            endTime = System.nanoTime();
            double timeFloydWarshallDense = (endTime - startTime) / 1_000_000_000.0;

            System.out.printf("%d\t%.6f\t%.6f\t%.6f\t%.6f\n", i, timeDijkstraSparse, timeDijkstraDense, timeFloydWarshallSparse, timeFloydWarshallDense);
        }
    }
}
