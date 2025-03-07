package Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class PathsFromSourceToTarget {
    static class Edge {
        int src;
        int dest;

        public Edge(int s, int d) {
            this.src = s;
            this.dest = d;
        }
    }

    public static void createGraph(ArrayList<Edge>[] graph) {
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        graph[0].add(new Edge(0, 1));
        graph[0].add(new Edge(0, 2));

        graph[1].add(new Edge(1, 0));
        graph[1].add(new Edge(1, 3));

        graph[2].add(new Edge(2, 0));
        graph[2].add(new Edge(2, 4));

        graph[3].add(new Edge(3, 1));
        graph[3].add(new Edge(3, 4));
        graph[3].add(new Edge(3, 5));

        graph[4].add(new Edge(4, 2));
        graph[4].add(new Edge(4, 3));
        graph[4].add(new Edge(4, 5));

        graph[5].add(new Edge(5, 3));
        graph[5].add(new Edge(5, 4));
        graph[5].add(new Edge(5, 6));

        graph[6].add(new Edge(6, 5));
    }

    public static void bfs(ArrayList<Edge>[] graph, int V, boolean[] vis, int start) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);

        while (!q.isEmpty()) {
            int curr = q.remove();
            if (!vis[curr]) {
                System.out.print(curr + " ");
                vis[curr] = true;

                for (Edge e : graph[curr]) {
                    if (!vis[e.dest]) {
                        q.add(e.dest);
                    }
                }
            }
        }
    }

    public static void dfs(ArrayList<Edge>[] graph, int curr, boolean[] vis) {
        System.out.print(curr + " ");
        vis[curr] = true;

        for (Edge e : graph[curr]) {
            if (!vis[e.dest]) {
                dfs(graph, e.dest, vis);
            }
        }
    }

    public static void printAllPaths(ArrayList<Edge>[] graph, boolean[] vis, int curr, String path, int tar) {
        if (curr == tar) {
            System.out.println(path);
            return;
        }

        vis[curr] = true;
        for (Edge e : graph[curr]) {
            if (!vis[e.dest]) {
                printAllPaths(graph, vis, e.dest, path + " -> " + e.dest, tar);
            }
        }
        vis[curr] = false;
    }

    public static void main(String[] args) {
        int V = 7;
        ArrayList<Edge>[] graph = new ArrayList[V];
        createGraph(graph);

        int src = 0, tar = 5;

        printAllPaths(graph, new boolean[V], src, "0", tar);
    }
}
