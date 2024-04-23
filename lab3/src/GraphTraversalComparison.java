import java.util.*;

public class GraphTraversalComparison {

    public static void main(String[] args) {
        int[] values = {4, 8, 16, 32, 64, 128, 256, 512};

        for (int numNodes : values) {
            int[][] edges = generateRandomEdges(numNodes, 0.5);
            GraphDFS graphDFS = new GraphDFS(numNodes);
            GraphBFS graphBFS = new GraphBFS(numNodes);

            for (int[] edge : edges) {
                int u = edge[0];
                int v = edge[1];
                graphDFS.addEdge(u, v);
                graphBFS.addEdge(u, v);
            }

            long startTime = System.nanoTime();
            graphDFS.DFS(0);
            long endTime = System.nanoTime();
            double dfsTime = (endTime - startTime) / 1e9; // Convert to seconds

            startTime = System.nanoTime();
            graphBFS.BFS(0);
            endTime = System.nanoTime();
            double bfsTime = (endTime - startTime) / 1e9; // Convert to seconds

            System.out.printf("Input Size: %d\tDFS Time: %.6f\tBFS Time: %.6f%n", numNodes, dfsTime, bfsTime);
        }
    }

    // Generate random edges for a graph with numNodes nodes and edgeProbability probability
    private static int[][] generateRandomEdges(int numNodes, double edgeProbability) {
        List<int[]> edges = new ArrayList<>();
        Random random = new Random();

        for (int u = 0; u < numNodes; u++) {
            for (int v = u + 1; v < numNodes; v++) {
                if (random.nextDouble() < edgeProbability) {
                    edges.add(new int[]{u, v});
                }
            }
        }

        return edges.toArray(new int[0][]);
    }

    // Class representing GraphDFS for Depth-First Search
    private static class GraphDFS {
        private List<List<Integer>> adjacencyList;
        private int numNodes;

        public GraphDFS(int numNodes) {
            this.numNodes = numNodes;
            this.adjacencyList = new ArrayList<>(numNodes);
            for (int i = 0; i < numNodes; i++) {
                this.adjacencyList.add(new ArrayList<>());
            }
        }

        public void addEdge(int u, int v) {
            adjacencyList.get(u).add(v);
        }

        public void DFS(int startNode) {
            boolean[] visited = new boolean[numNodes];
            DFSUtil(startNode, visited);
        }

        private void DFSUtil(int v, boolean[] visited) {
            visited[v] = true;
            System.out.print(v + " ");

            for (int neighbour : adjacencyList.get(v)) {
                if (!visited[neighbour]) {
                    DFSUtil(neighbour, visited);
                }
            }
        }
    }

    // Class representing GraphBFS for Breadth-First Search
    private static class GraphBFS {
        private List<List<Integer>> adjacencyList;
        private int numNodes;

        public GraphBFS(int numNodes) {
            this.numNodes = numNodes;
            this.adjacencyList = new ArrayList<>(numNodes);
            for (int i = 0; i < numNodes; i++) {
                this.adjacencyList.add(new ArrayList<>());
            }
        }

        public void addEdge(int u, int v) {
            adjacencyList.get(u).add(v);
        }

        public void BFS(int startNode) {
            boolean[] visited = new boolean[numNodes];
            Queue<Integer> queue = new LinkedList<>();

            visited[startNode] = true;
            queue.offer(startNode);

            while (!queue.isEmpty()) {
                int currentNode = queue.poll();
                System.out.print(currentNode + " ");

                for (int neighbour : adjacencyList.get(currentNode)) {
                    if (!visited[neighbour]) {
                        visited[neighbour] = true;
                        queue.offer(neighbour);
                    }
                }
            }
        }
    }
}
