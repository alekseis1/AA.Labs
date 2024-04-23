import java.util.*;

// Класс для глубокого поиска в графе
class GraphDFS {
    private Map<Integer, List<Integer>> graph;

    public GraphDFS() {
        this.graph = new HashMap<>();
    }

    public void addEdge(int u, int v) {
        if (!graph.containsKey(u)) {
            graph.put(u, new ArrayList<>());
        }
        graph.get(u).add(v);
    }

    private void DFSUtil(int v, Set<Integer> visited) {
        visited.add(v);

        if (graph.containsKey(v)) {
            for (int neighbour : graph.get(v)) {
                if (!visited.contains(neighbour)) {
                    DFSUtil(neighbour, visited);
                }
            }
        }
    }

    public void DFS(int v) {
        Set<Integer> visited = new HashSet<>();
        DFSUtil(v, visited);
    }
}

// Класс для поиска в ширину в графе
class GraphBFS {
    private Map<Integer, List<Integer>> adjList;

    public GraphBFS() {
        this.adjList = new HashMap<>();
    }

    public void addEdge(int u, int v) {
        adjList.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
        adjList.computeIfAbsent(v, k -> new ArrayList<>()).add(u);
    }

    public void bfs(int startNode) {
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();

        int maxNode = adjList.keySet().stream().max(Integer::compareTo).orElse(-1);

        visited.add(startNode);
        queue.add(startNode);

        while (!queue.isEmpty()) {
            int currentNode = queue.poll();
            if (adjList.containsKey(currentNode)) {
                for (int neighbor : adjList.get(currentNode)) {
                    if (!visited.contains(neighbor)) {
                        visited.add(neighbor);
                        queue.add(neighbor);
                    }
                }
            }
        }
    }
}
