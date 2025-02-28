package Graph;

import java.util.ArrayList;

public class ArticulatonPoint {
    static class Edge {
        int src;
        int dest;

        public Edge(int s, int d) {
            this.src = s;
            this.dest = d;
        }
    }

    public static void createGraph(ArrayList<Edge> graph[]) {
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<Edge>();
        }
        graph[0].add(new Edge(0, 1));
        graph[0].add(new Edge(0, 2));
        graph[0].add(new Edge(0, 3));

        graph[1].add(new Edge(1, 0));
        graph[1].add(new Edge(1, 2));

        graph[2].add(new Edge(2, 0));
        graph[2].add(new Edge(2, 1));

        graph[3].add(new Edge(3, 0));
        graph[3].add(new Edge(3, 4));

        graph[4].add(new Edge(4, 3));
    }

    public static void dfs(ArrayList<Edge> graph[], int curr, boolean vis[], int dt[], int low[], int time, int parent,
            boolean isAP[]) {
        vis[curr] = true;
        dt[curr] = low[curr] = ++time;
        int children = 0;

        for (int i = 0; i < graph[curr].size(); i++) {
            Edge e = graph[curr].get(i);
            int neighbor = e.dest;

            if (neighbor == parent) {
                continue; // Skip the edge back to parent
            }

            if (!vis[neighbor]) {
                dfs(graph, neighbor, vis, dt, low, time, curr, isAP);
                low[curr] = Math.min(low[curr], low[neighbor]);

                // Check articulation point conditions
                if (parent != -1 && dt[curr] <= low[neighbor]) {
                    isAP[curr] = true;
                }
                children++;
            } else {
                low[curr] = Math.min(low[curr], dt[neighbor]);
            }
        }

        // If curr is the root and has more than one child, it's an articulation point
        if (parent == -1 && children > 1) {
            isAP[curr] = true;
        }
    }

    public static void getAP(ArrayList<Edge> graph[], int V) {
        int dt[] = new int[V]; // Discovery times of visited vertices
        int low[] = new int[V]; // Earliest visited vertex reachable
        int time = 0; // Initialize time
        boolean vis[] = new boolean[V]; // Visited array
        boolean isAP[] = new boolean[V]; // To store articulation points

        // Apply DFS for all unvisited vertices
        for (int i = 0; i < V; i++) {
            if (!vis[i]) {
                dfs(graph, i, vis, dt, low, time, -1, isAP);
            }
        }

        // Print articulation points
        System.out.println("Articulation Points:");
        for (int i = 0; i < V; i++) {
            if (isAP[i]) {
                System.out.println(i);
            }
        }
    }

    public static void main(String[] args) {
        int V = 5;
        ArrayList<Edge> graph[] = new ArrayList[V];
        createGraph(graph);
        getAP(graph, V);
    }
}
