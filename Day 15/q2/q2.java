import java.util.*;

public class Exercise {
    static class Tree {
        int data;
        Tree left;
        Tree right;
        Tree(int data) {this.data = data; this.left = null; this.right = null;}
        Tree(int data, Tree left, Tree right) {this.data = data; this.left = left; this.right = right;}
    }

    public void reverseTree(Tree root) {
        if (root == null) return;
        Tree temp = root.right;
        root.right = root.left;
        root.left = temp;
        reverseTree(root.left);
        reverseTree(root.right);
    }
}
