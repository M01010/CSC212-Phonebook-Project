package Structures;


public class BST<T> {
    static class BSTNode<T> {
        private String key;
        private T data;
        private BSTNode<T> left, right;

        public BSTNode(String key, T data) {
            this.key = key;
            this.data = data;
            left = right = null;
        }
    }

    BSTNode<T> root, current;

    public BST() {
        root = current = null;
    }

    public boolean empty() {
        return root == null;
    }

    public T retrieve() {
        return current.data;
    }

    public boolean findkey(String tkey) {
        BSTNode<T> p = root, q = root;

        if (empty()) return false;

        while (p != null) {
            q = p;
            if (p.key.equals(tkey)) {
                current = p;
                return true;
            }
            if (tkey.compareTo(p.key) < 0) {
                p = p.left;
            } else {
                p = p.right;
            }
        }

        current = q;
        return false;
    }

    public boolean insert(String k, T val) {
        BSTNode<T> p, q = current;

        if (findkey(k)) {
            current = q;  // findkey() modified current
            return false; // key already in the BST
        }

        p = new BSTNode<>(k, val);
        if (empty()) {
            root = current = p;
            return true;
        }
        // current is pointing to parent of the new key
        if (k.compareTo(current.key) < 0) {
            current.left = p;
        } else {
            current.right = p;
        }
        current = p;
        return true;
    }

    public boolean remove_key(String tkey) {
        Boolean removed = false;
        BSTNode<T> p;
        p = remove_aux(tkey, root, removed);
        current = root = p;
        return removed;
    }

    private BSTNode<T> remove_aux(String key, BSTNode<T> p, Boolean flag) {
        BSTNode<T> q, child = null;
        if (p == null) return null;
        if (key.compareTo(p.key) < 0) {
            p.left = remove_aux(key, p.left, flag); //go left
            return p;
        }
        if (key.compareTo(p.key) > 0) {
            p.right = remove_aux(key, p.right, flag); //go right
            return p;
        }
        // key is found
        flag = true;
        if (p.left != null && p.right != null) { //two children
            q = find_min(p.right);
            p.key = q.key;
            p.data = q.data;
            p.right = remove_aux(q.key, p.right, flag);
        } else {
            if (p.right == null) //one child
            {
                child = p.left;
            } else {
                child = p.right;
            }
            return child;
        }
        return p;
    }

    private BSTNode<T> find_min(BSTNode<T> p) {
        if (p == null) return null;

        while (p.left != null) {
            p = p.left;
        }

        return p;
    }


}
