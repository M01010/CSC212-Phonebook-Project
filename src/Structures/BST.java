package Structures;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Predicate;

public class BST<T extends Comparable<T>> implements StructureOperations<T> {

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
        AtomicBoolean removed = new AtomicBoolean(false);
        BSTNode<T> p;
        p = remove_aux(tkey, root, removed);
        current = root = p;
        return removed.get();
    }

    private BSTNode<T> remove_aux(String key, BSTNode<T> p, AtomicBoolean flag) {
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
        flag.set(true);
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

    public boolean find(Relative rel) {
        switch (rel) {
            case Root:    // Easy case
                current = root;
                return true;
            case Parent:
                if (current == root) return false;
                current = findparent(current, root);
                return true;
            case LeftChild:
                if (current.left == null) return false;
                current = current.left;
                return true;
            case RightChild:
                if (current.right == null) return false;
                current = current.right;
                return true;
            default:
                return false;
        }
    }

    // Recursive version of findparent – preorder traversal used
    private BSTNode<T> findparent(BSTNode<T> p, BSTNode<T> t) {
        if (t == null) return null;    // empty tree

        if (t.right == null && t.left == null) return null; // can’t be the parent because it has no children
        else if (t.right == p || t.left == p) return t;    // parent is t
        else {
            BSTNode<T> q = findparent(p, t.left);
            if (q != null) // parent was found in previous call of findparent
                return q;
            else return findparent(p, t.right);
        }
    }

    public void display() {
        displayrec(root);
    }

    private void displayrec(BSTNode<T> n) {
        if (n == null) {
            return;
        }
        displayrec(n.left);
        System.out.println(n.data);
        displayrec(n.right);
    }

    public T search(Predicate<T> condition) {
        return searchrec(root, condition);
    }

    private T searchrec(BSTNode<T> n, Predicate<T> condition) {
        if (n == null) {
            return null;
        }
        T res = searchrec(n.left, condition);
        if (res != null) {
            return res;
        }
        if (condition.test(n.data)) {
            return n.data;
        }
        res = searchrec(n.right, condition);
        return res;
    }

    public LinkedList<T> filter(Predicate<T> condition) {
        LinkedList<T> l = new LinkedList<>();
        filterrec(root, l, condition);
        return l;
    }

    private void filterrec(BSTNode<T> n, LinkedList<T> l, Predicate<T> condition) {
        if (n == null) {
            return;
        }
        filterrec(n.left, l, condition);
        if (condition.test(n.data)) {
            l.insert(n.data);
        }
        filterrec(n.right, l, condition);
    }

    public T delete(Predicate<T> condition) {
        return deleterec(root, condition);
    }

    private T deleterec(BSTNode<T> n, Predicate<T> condition) {
        if (n == null) {
            return null;
        }
        T temp = deleterec(n.left, condition);
        if (condition.test(n.data)) {
            T data = n.data;
            remove_key(n.key);
            return data;
        }
        temp = deleterec(n.right, condition);
        return temp;
    }
}
