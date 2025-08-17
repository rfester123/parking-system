package com.tete.parking;

public class PlateBST {
    private static class Node {
        String plate, location;
        Node left, right;
        Node(String p, String loc) { plate = p; location = loc; }
    }
    private Node root;

    public void insert(String plate, String location) {
        root = insertRec(root, plate, location);
    }
    private Node insertRec(Node n, String p, String loc) {
        if (n == null) return new Node(p, loc);
        int cmp = p.compareTo(n.plate);
        if (cmp < 0) n.left = insertRec(n.left, p, loc);
        else if (cmp > 0) n.right = insertRec(n.right, p, loc);
        else n.location = loc; // atualiza se já existir
        return n;
    }

    public String find(String plate) {
        Node n = root;
        while (n != null) {
            int cmp = plate.compareTo(n.plate);
            if (cmp == 0) return n.location;
            n = (cmp < 0) ? n.left : n.right;
        }
        return null;
    }
}
