import java.util.*;

public class Exercise {
    static class Tree {
        int data;
        Tree left;
        Tree right;
        Tree(int data) {this.data = data; this.left = null; this.right = null;}
        Tree(int data, Tree left, Tree right) {this.data = data; this.left = left; this.right = right;}
    }

    public void dfsPreorder(Tree root) {
        if (root == null) return;
        System.out.println(root.data);
        dfsPreorder(root.left);
        dfsPreorder(root.right);
    }

    public void dfsInorder(Tree root) {
        if (root == null) return;
        dfsPreorder(root.left);
        System.out.println(root.data);
        dfsPreorder(root.right);
    }

    public void dfsPostorder(Tree root) {
        if (root == null) return;
        dfsPreorder(root.left);
        dfsPreorder(root.right);
        System.out.println(root.data);
    }
}
