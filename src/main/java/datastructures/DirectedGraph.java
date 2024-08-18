package src.main.java.datastructures;
import java.util.*;

public class DirectedGraph {
    private class Node {
        private String info;

        public Node(String info) {
            this.info = info;
        }

        public String toString() {
            return info;
        }
    }

    private Map<String, Node> nodes = new HashMap<>();
    // instance variables of graph: every graph has many nodes
    // we could make this a list, but it would take a while to only put unique
    // values in map
    // the label of each node in the graph is the key
    // we can use this to quickly lookup strings to see if we have nodes containing
    // them
    private Map<Node, List<Node>> adjacencyList = new HashMap<>();
    // The hash table is automatically implemented using a map for quick lookup
    // Stil

    public void addNode(String info) {
        var node = new Node(info);
        nodes.putIfAbsent(info, node);
        // we add a node to our graph if it is not in the graph
        adjacencyList.putIfAbsent(node, new ArrayList<>());
    }

    public void removeNode(String info) {
        Node node = nodes.get(info);
        if (node == null)
            return;
        // Don't yell at them with exception because if node was already deleted then
        // it's ok
        // we also have to remove all of the connections to this node
        for (Node each : adjacencyList.keySet())
            adjacencyList.get(each).remove(node);
        // for each node's list of connections, we are removing the node we are deleting
        // removes all of the edges that the other nodes have tothat node
        adjacencyList.remove(node);
        nodes.remove(node);
    }

    public void addEdge(String from, String to) {
        var fromNode = nodes.get(from);
        var toNode = nodes.get(to);

        if (fromNode == null || toNode == null)
            throw new IllegalArgumentException();

        adjacencyList.get(fromNode).add(toNode);
        // adjacencyList.get(toNode).add(fromNode); becomes undirected graph

        // if you add connection/edge from "john" to "josh" which are already in graph
        // Access list of nodes from AdjacencyList HashMap, add the node its connected
        // to

    }

    public void removeEdge(String from, String to) {
        var fromNode = nodes.get(from);
        var toNode = nodes.get(to);

        if (fromNode == null || toNode == null)
            return;

        adjacencyList.get(fromNode).remove(toNode);
    }

    public void depthFirstTraversal(String root) {
        if (nodes.get(root) == null)
            return;
        Node node = nodes.get(root);
        depthFirstTraversal(node);
    }

    private void depthFirstTraversal(Node root) {
        Stack<Node> stack = new Stack<>();
        Set<Node> visited = new HashSet<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node removed = stack.pop();

            if (visited.contains(removed))
                continue;

            visited.add(removed);
            System.out.println(removed);
            for (Node neighbor : adjacencyList.get(removed)) {
                if (!visited.contains(neighbor)) {
                    stack.push(neighbor);
                }
            }
        }
    }

    public void breadthFirstTraversal(String root) {
        if (nodes.get(root) == null)
            return;
        Node node = nodes.get(root);
        breadthFirstTraversal(node);
    }

    private void breadthFirstTraversal(Node root) {
        Queue<Node> queue = new ArrayDeque<>();
        Set<Node> visited = new HashSet<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node removed = queue.remove();

            if (visited.contains(removed))
                continue;

            visited.add(removed);
            System.out.println(removed);
            for (Node neighbor : adjacencyList.get(removed)) {
                if (!visited.contains(neighbor)) {
                    queue.remove(neighbor);
                }
            }
        }
    }

    public String toString() {
        StringBuilder result = new StringBuilder();

        for (Node source : adjacencyList.keySet()) {
            List<Node> targets = adjacencyList.get(source);
            if (!targets.isEmpty())
                result.append(source.toString()).append(" is connected to ").append(targets).append("\n");
            // appending \n adds new line

        }
        // for every node of graph, if it is connected to something
        // print out the list of nodes it's connected to

        return result.toString();
        // We have toString method for a node and graph objects
    }

}
