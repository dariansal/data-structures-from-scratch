import java.lang.Math;
import java.util.*;

public class Tree {
    private class Node {
        private int data;
        private Node leftChild;
        private Node rightChild;

        private Node(int data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "Node: " + data;
        }
    }

    private Node root; // every tree has a root

    public void insert(int value) {
        var current = root; // current starts at top
        Node add = new Node(value);

        if (root == null) {
            root = add; // add node and have root refrence if tree is empty
            return;
        }

        while (current != null) {
            if (value < current.data) { // should go to left subtree
                if (current.leftChild == null) { // if theres nothing there
                    current.leftChild = add; // connect leftChild node
                    break; // while loop would execute forever without this
                }
                current = current.leftChild; // go one row down and execute while loop again
            } else if (value > current.data) {
                if (current.rightChild == null) {
                    current.rightChild = add;
                    break;
                }
                current = current.rightChild;
            } else if (value == current.data)
                break;
        }
    }

    public boolean find(int value) {
        var current = root;

        while (current != null) { // doesn't execute if tree is empty
            if (value < current.data) // left subtree
                current = current.leftChild;
            else if (value > current.data)
                current = current.rightChild;
            else // value = current.data you found the value
                return true;
        }
        return false; // you get to end of tree (current = null because leftChild and rightChild are
                      // null) and don't find value
    }

    public void traversePreOrder() { // we call this method from outside class; overloading
        traversePreOrder(root);
    }

    private void traversePreOrder(Node root) { // implemenation detail
        if (root == null) {
            return;
        }

        System.out.println(root.data);
        traversePreOrder(root.leftChild);
        traversePreOrder(root.rightChild);
    }

    public void traverseInOrder() {
        traverseInOrder(root);
    }

    private void traverseInOrder(Node root) {
        if (root == null) {
            return;
        }

        traverseInOrder(root.leftChild);
        System.out.println(root.data);
        traverseInOrder(root.rightChild);

    }

    public void traversePostOrder() {
        traversePostOrder(root);
    }

    private void traversePostOrder(Node root) {
        if (root == null) {
            return;
        }

        traversePostOrder(root.leftChild);
        traversePostOrder(root.rightChild);
        System.out.println(root.data);
    }

    public void traverseLevelOrder() {
        traverseLevelOrder(root);
    }

    private void traverseLevelOrder(Node root) {
        for (int i = 0; i <= height(root); i++) {
            for (int node : nodesAtKDistance(i)) {
                System.out.println(node);
            }
        }
    }

    private boolean isLeaf(Node root) {
        return root.leftChild == null && root.rightChild == null;
    }

    public int height() {
        return height(root);
    }

    private int height(Node root) {
        if (root == null)
            throw new IllegalStateException();

        if (isLeaf(root))
            return 0;

        int leftHeight = height(root.leftChild);
        int rightHeight = height(root.rightChild);

        return 1 + Math.max(leftHeight, rightHeight);
    }

    public int minValue() {
        return minValue(root);
    }

    private int minValue(Node root) {
        if (isLeaf(root)) // only works when there are fully filled binary trees
            return root.data;

        int leftMin = minValue(root.leftChild);
        int rightMin = minValue(root.rightChild);

        return Math.min(Math.min(leftMin, rightMin), root.data);
    }

    // tree.equals(tree2)
    @Override // Overriding method from the object class
    public boolean equals(Object obj) {
        Tree other = (Tree) obj; // casting Object object to a Tree object
        if (other == null) {
            return false;
        }
        return equals(this.root, other.root);
        // calls equals method with arguments being root of each tree object

    }

    private boolean equals(Node root1, Node root2) {
        if (root1 == null && root2 == null) { // both trees are empty
            return true;
        }

        if (root1 != null && root2 != null) {
            return root1.data == root2.data && equals(root1.leftChild, root2.leftChild)
                    && equals(root1.rightChild, root2.rightChild);
            // Preorder, look at root first because you want to
            // compare the treesgoing from top to bottom, then check left subtree, then
            // check right subtree; once you get to the leafnodes, the children are null so
            // the equals function will return true, so compare data of leafnodes
        }
        return false;
    }

    public boolean isBinarySearchTree() {
        return isBinarySearchTree(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean isBinarySearchTree(Node root, int min, int max) {
        if (root == null)
            return true;

        if (root.data < min || root.data > max) // Preorder, check the root first
            return false;

        return isBinarySearchTree(root.leftChild, min, root.data - 1) &&
                isBinarySearchTree(root.rightChild, root.data + 1, max);
    }

    public ArrayList<Integer> nodesAtKDistance(int distance) {
        if (distance > height(root))
            throw new IllegalArgumentException();
        ArrayList<Integer> list = new ArrayList<>();
        nodesAtKDistance(root, distance, list);
        return list;
    }

    private void nodesAtKDistance(Node root, int distance, ArrayList<Integer> list) {
        if (root == null)
            return;

        if (distance == 0) {
            list.add(root.data);
        }

        if (root.rightChild != null && root.leftChild != null) {
            nodesAtKDistance(root.leftChild, distance - 1, list);
            nodesAtKDistance(root.rightChild, distance - 1, list);
        }
    }

}