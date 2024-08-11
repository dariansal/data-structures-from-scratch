## Purpose
This repository contains custom implementations of various data structures that I created in Fall 2023 while studying Data Structures and Algorithms. The goal of 
these implementations from scratch was to understand how these data structures work under the hood.

## Overview

Each data structure, implemented in Java, in this repository contains its standard operations and utility methods. 

## Project Structure
This project follows a simplified Java project structure:
- All source code is located in `src/main/java/com/datastructures/`
- Each Java file contains the corresponding data structure implementation

## Note: File Naming
To avoid naming conflicts with Java's standard library classes, the priority queue and stack impelementations needed a different class name. Thus, "Custom" was appended
to the class name of both of the corresponding classes.

## Implemented Data Structures

- `DirectedGraph.java`: Implements a directed graph using an adjacency list. Includes methods for adding nodes, edges, and traversing the graph.
  
- `Heap.java`: Implements a binary heap, supporting efficient insertion and extraction of the minimum (or maximum) element.
  
- `LinkedList.java`: Implements a singly linked list with basic operations such as insertion, deletion, and traversal.

- `PriorityQueueCustom.java`: Wraps the custom heap with priority-queue properties for better performance in element retrieval.
  
- `StackCustom.java`: Implements a stack data structure with push, pop, and peek operations.
  
- `Tree.java`: Implements a binary search tree with traversal methods (in-order, pre-order, post-order) and utility functions like insertion, deletion, and search.
  
- `WeightedGraph.java`: A weighted graph implementation with Dijkstra's algorithm.
  
- `Path.java`: Helper class for representing paths in the weighted graph.

## Future Plans

- Implement more advanced data structures (e.g., AVL Trees, Red-Black Trees).
- Implement additional algorithms for existing data structures.
- Write unit tests for each data structure using JUnit. Configure Maven to manage test dependencies and automate test execution.