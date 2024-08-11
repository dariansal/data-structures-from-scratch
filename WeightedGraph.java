import java.util.*;

// The WeightedGraph class represents a graph with nodes and weighted edges
public class WeightedGraph {
    
    // Inner class representing a node in the graph
    private class Node {
        private String info; // Information stored in the node
        private List<Edge> edges; // List of edges connected to this node

        // Constructor to initialize a node with information
        public Node(String info) {
            // Set node information and initialize the list of edges
            this.info = info;
            this.edges = new ArrayList<>();
        }

        // Override toString method to represent the node as a string
        public String toString() {
            return info;
        }

        // Method to add an edge from this node to another node with a given weight
        public void addEdge(Node to, int weight) {
            // Create a new edge and add it to the list
            edges.add(new Edge(this, to, weight));
        }

        // Method to get the list of edges connected to this node
        public List<Edge> getEdges() {
            return edges;
        }
    }

    // Inner class representing an edge in the graph
    private class Edge {
        private int weight; // Weight of the edge
        private Node from; // Node from which the edge originates
        private Node to; // Node to which the edge leads

        // Constructor to initialize an edge with from, to, and weight
        public Edge(Node from, Node to, int weight) {
            this.weight = weight;
            this.from = from;
            this.to = to;
        }

        // Override toString method to represent the edge as a string
        @Override
        public String toString() {
            return from + "->" + to;
        }
    }

    // Map to store nodes in the graph
    private Map<String, Node> nodes = new HashMap<>();

    // Method to add a node to the graph
    public void addNode(String info) {
        // Add a new node if it doesn't exist
        nodes.putIfAbsent(info, new Node(info));
    }

    // Method to add a weighted edge between two nodes in the graph
    public void addEdge(String from, String to, int weight) {
        // Get the source and target nodes
        var fromNode = nodes.get(from);
        var toNode = nodes.get(to);

        // Check if either of the nodes is not present in the graph
        if (fromNode == null || toNode == null)
            throw new IllegalArgumentException();

        // Add edges in both directions since it's an undirected graph
        fromNode.addEdge(toNode, weight);
        toNode.addEdge(fromNode, weight);
    }

    // Override toString method to represent the graph as a string
    public String toString() {
        // StringBuilder to store the string representation of the graph
        StringBuilder result = new StringBuilder();

        // Iterate through each node in the graph
        for (Node source : nodes.values()) {
            // Get the list of edges for the current node
            List<Edge> targets = source.getEdges();
            if (!targets.isEmpty())
                // Append the node and its connected edges to the result
                result.append(source.toString()).append(" is connected to ").append(targets).append("\n");
        }

        // Return the final string representation of the graph
        return result.toString();
    }

    // Inner class representing a node and its priority in Dijkstra's algorithm
    private class NodeEntry {
        private Node node;
        private int priority;

        // Constructor to initialize a NodeEntry with a node and its priority
        public NodeEntry(Node node, int priority) {
            this.node = node;
            this.priority = priority;
        }
    }

    // Method to get the shortest distance between two nodes using Dijkstra's algorithm
    public Path getShortestPath(String from, String to) {
        // Check if nodes exist in the graph
        if (nodes.get(from) == null || nodes.get(to) == null)
            throw new IllegalArgumentException();
        
        // Get the source and target nodes
        var fromNode = nodes.get(from);
        var toNode = nodes.get(to);

        // Map to store distances to nodes
        Map<Node, Integer> distances = new HashMap<>();

        // Initialize distances to all nodes with 'infinity' except for the source node
        for (var node : nodes.values()) {
            distances.put(node, Integer.MAX_VALUE);
        }

        // Map to store previous nodes in the shortest path
        Map<Node, Node> previousNodes = new HashMap<>();
        distances.replace(fromNode, 0); // Set the distance from the source node to itself as 0

        // Set to keep track of visited nodes
        Set<Node> visited = new HashSet<>();

        // Priority queue to store nodes and their priorities (shortest known distances)
        PriorityQueue<NodeEntry> pq = new PriorityQueue<>(
                Comparator.comparingInt(nodeEntry -> nodeEntry.priority));

        // Add the source node to the priority queue with a priority of 0
        pq.add(new NodeEntry(fromNode, 0));

        // Dijkstra's algorithm
        while (!pq.isEmpty()) {
            // Get the node with the smallest known distance
            Node current = pq.remove().node;
            
            // Mark the current node as visited
            visited.add(current);

            // Iterate through the edges of the current node
            for (Edge edge : current.getEdges()) {
                // Skip edges leading to already visited nodes
                if (visited.contains(edge.to))
                    continue;

                // Calculate the new distance from the source to the target node through the current edge
                int newDistance = distances.get(current) + edge.weight;

                // If the new distance is shorter than the known distance to the target node
                if (newDistance < distances.get(edge.to)) {
                    // Update the distance
                    distances.replace(edge.to, newDistance);
                    previousNodes.put(edge.to, current);
                    // Add the target node to the priority queue
                    pq.add(new NodeEntry(edge.to, newDistance));
                }
            }
        }

        // Return the shortest distance to the target node
        return buildPath(previousNodes, toNode);
    }

    // Helper method to build the shortest path from previous nodes
    private Path buildPath(Map<Node, Node> previousNodes, Node toNode) {
        // Stack to store nodes in reverse order
        Stack<Node> stack = new Stack<>();
        stack.push(toNode);
        
        // Traverse the previous nodes and push them onto the stack
        var previous = previousNodes.get(toNode);
        while (previous != null) {
            stack.push(previous);
            previous = previousNodes.get(previous);
        }

        // Create a Path object to store the path
        Path path = new Path();
        
        // Pop nodes from the stack to get the correct order
        while (!stack.isEmpty()) {
            path.add(stack.pop().info);
        }

        // Return the final path
        return path;
    }
}
