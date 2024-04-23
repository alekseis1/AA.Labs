import java.util.*;

public class Algorithms {

    public static Map<Integer, Integer> dijkstra(Map<Integer, Map<Integer, Integer>> graph, int start) {
        Map<Integer, Integer> distances = new HashMap<>();
        Set<Integer> visited = new HashSet<>();

        for (int node : graph.keySet()) {
            distances.put(node, Integer.MAX_VALUE);
        }
        distances.put(start, 0);

        while (visited.size() < graph.size()) {
            int current = getMinDistanceNode(distances, visited);
            visited.add(current);

            // Проверяем, что у текущего узла есть смежные узлы в графе
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

    private static int getMinDistanceNode(Map<Integer, Integer> distances, Set<Integer> visited) {
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
                    // Проверяем, что узел i имеет смежность с узлами k и j
                    if (dist.containsKey(i) && dist.get(i).containsKey(k) && dist.containsKey(k) && dist.get(k).containsKey(j)) {
                        int ikWeight = dist.get(i).get(k);
                        int kjWeight = dist.get(k).get(j);

                        // Проверяем, что значения не равны null и не равны Integer.MAX_VALUE
                        if (ikWeight != Integer.MAX_VALUE && kjWeight != Integer.MAX_VALUE) {
                            Integer currentWeight = dist.get(i).get(j);
                            if (currentWeight == null || currentWeight > ikWeight + kjWeight) {
                                dist.get(i).put(j, ikWeight + kjWeight);
                            }
                        }
                    }
                }
            }
        }

        return dist;
    }

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

    public static void main(String[] args) {
        // Пример использования
        int nodes = 5;
        int edges = 7;

        Map<Integer, Map<Integer, Integer>> sparseGraph = generateSparseGraph(nodes, edges);
        Map<Integer, Map<Integer, Integer>> denseGraph = generateDenseGraph(nodes);

        System.out.println("Sparse Graph:");
        System.out.println(sparseGraph);

        System.out.println("Dense Graph:");
        System.out.println(denseGraph);

        int startNode = 0;
        System.out.println("Dijkstra's shortest paths from node " + startNode + ":");
        Map<Integer, Integer> dijkstraDistances = dijkstra(sparseGraph, startNode);
        System.out.println(dijkstraDistances);

        System.out.println("Floyd-Warshall shortest paths:");
        Map<Integer, Map<Integer, Integer>> floydDistances = floydWarshall(denseGraph);
        System.out.println(floydDistances);
    }
}
