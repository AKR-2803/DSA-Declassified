class AVL {
    public class Node {
        private int value;
        private int height;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }

        public int getValue(Node node) {
            return value;
        }
    }

    private Node root;

    public AVL() {
    }

    public int height() {
        return height(root);
    }

    private int height(Node node) {
        if (node == null) {
            return -1;
        }
        return node.height;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void display() {
        display(this.root, "Root Node: ");
    }

    private void display(Node node, String details) {
        if (node == null) {
            return;
        }
        System.out.println(details + node.value);

        display(node.left, "Left Child of " + node.value + ": ");
        display(node.right, "Right Child of " + node.value + ": ");
    }

    public void insert(int value) {
        root = insert(value, root);
    }

    private Node insert(int newValue, Node node) {
        if (node == null) {
            node = new Node(newValue);
            return node;
        }

        if (newValue < node.value) {
            node.left = insert(newValue, node.left);
        } else {
            node.right = insert(newValue, node.right);
        }

        node.height = Math.max(height(node.left), height(node.right)) + 1;

        // check for violation of balanced tree, everytime you insert a node
        return rotate(node);
    }

    private Node rotate(Node node) {
        // left heavy tree
        if (height(node.right) - height(node.left) < -1) {
            // left-left case
            if (height(node.left.right) - height(node.left.left) < 0) {
                return rightRotate(node);
            }

            // left-right case
            if (height(node.left.right) - height(node.left.left) > 0) {
                // left rotate(c), then it becomes left-left case, fo right rotate(p)
                node.left = leftRotate(node.left);
                return rightRotate(node);
            }
        }

        // right heavy tree
        if (height(node.right) - height(node.left) > 1) {
            // right-left case
            if (height(node.right.right) - height(node.right.left) < 0) {
                node.right = rightRotate(node.right);
                return leftRotate(node);
            }

            // right-right case
            if (height(node.right.right) - height(node.right.left) > 0) {
                // left rotate(p)
                return leftRotate(node);
            }
        }

        // if none of the above conditions occur, that means it is a balanced tree even
        // after the insertion, hence simply return the node
        return node;
    }

    private Node leftRotate(Node node) {
        Node p = node;
        Node c = p.right;

        Node temp = c.left;

        c.left = p;
        p.right = temp;

        p.height = Math.max(height(p.left), height(p.right)) + 1;
        c.height = Math.max(height(c.left), height(c.right)) + 1;

        // new/updated root of the subtree/tree after rotation
        return c;
    }

    private Node rightRotate(Node node) {
        Node p = node;
        Node c = p.left;

        Node temp = c.right;

        c.right = p;
        p.left = temp;

        p.height = Math.max(height(p.left), height(p.right)) + 1;
        c.height = Math.max(height(c.left), height(c.right)) + 1;

        // new/updated root of the subtree/tree after rotation
        return c;
    }

    public void populate(int[] nums) {
        for (int num : nums) {
            this.insert(num);
        }
    }

    public boolean balanced() {
        return balanced(root);
    }

    private boolean balanced(Node node) {
        if (node == null) {
            return true;
        }
        return Math.abs(height(node.left) - height(node.right)) <= 1 && balanced(node.left) && balanced(node.right);
    }

    public void preorder() {
        preorder(root);
    }

    private void preorder(Node node) {
        if (node == null) {
            return;
        }
        System.out.print(node.value + " - ");
        preorder(node.left);
        preorder(node.right);
    }

    public void postorder() {
        postorder(root);
    }

    private void postorder(Node node) {
        if (node == null) {
            return;
        }
        postorder(node.left);
        postorder(node.right);
        System.out.print(node.value + " - ");
    }

    public void inorder() {
        inorder(root);
    }

    private void inorder(Node node) {
        if (node == null) {
            return;
        }
        inorder(node.left);
        System.out.print(node.value + " - ");
        inorder(node.right);
    }
}