package com.company;

public class RedBlackTree {
    public static final boolean black = false;
    public static final boolean red = true;
    private RBTNode nil = new RBTNode(-1, null);
    private RBTNode root;
    private int numberOfElements = 0;
    int ageAverage= 0;

    public RedBlackTree() {
        nil.parent = nil;
        nil.left = nil;
        nil.right = nil;
        nil.setColor(black);
        root = nil;
    }

    public void insert(int k, Person person) {
        insert(new RBTNode(k, person));
    }

    private void insert(RBTNode z) {
        RBTNode y = nil;
        RBTNode x = root;
        while (x != nil) {
            y = x;
            if (z.key < x.key) {
                x = x.left;
            } else {
                x = x.right;
            }
        }
        z.parent = y;
        if (y == nil) {
            root = z;
        } else if (z.key < y.key) {
            y.left = z;
        } else {
            y.right = z;
        }
        z.left = nil;
        z.right = nil;
        z.color = red;
        insertFixup(z);
        numberOfElements++;

    }

    private void insertFixup(RBTNode node) {
        while (node.parent.color == red) {
            RBTNode uncle = nil;
            if (node.parent == node.parent.parent.left) {
                uncle = node.parent.parent.right;

                if (uncle != nil && uncle.color == red) {
                    node.parent.color = black;
                    uncle.color = black;
                    node.parent.parent.color = red;
                    node = node.parent.parent;
                    continue;
                }
                if (node == node.parent.right) {
                    //Double rotation needed
                    node = node.parent;
                    leftRotate(node);
                }
                node.parent.color = black;
                node.parent.parent.color = red;
                //if the "else if" code hasn't executed, this
                //is a case where we only need a single rotation
                rightRotate(node.parent.parent);
            } else {
                uncle = node.parent.parent.left;
                if (uncle != nil && uncle.color == red) {
                    node.parent.color = black;
                    uncle.color = black;
                    node.parent.parent.color = red;
                    node = node.parent.parent;
                    continue;
                }
                if (node == node.parent.left) {
                    //Double rotation needed
                    node = node.parent;
                    rightRotate(node);
                }
                node.parent.color = black;
                node.parent.parent.color = red;
                //if the "else if" code hasn't executed, this
                //is a case where we only need a single rotation
                leftRotate(node.parent.parent);
            }
        }
        root.color = black;
    }

    private void leftRotate(RBTNode x) {
        if (x.right == nil) {
            return;
        }
        RBTNode y = x.right;
        x.right = y.left;
        if (y.left != nil) {
            y.left.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == nil) {
            root = y;
        } else if (x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }
        y.left = x;
        x.parent = y;
    }

    private void rightRotate(RBTNode x) {
        if (x.left == nil) {
            return;
        }
        RBTNode y = x.left;
        x.left = y.right;
        if (y.right != nil) {
            y.right.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == nil) {
            root = y;
        } else if (x == x.parent.right) {
            x.parent.right = y;
        } else {
            x.parent.left = y;
        }
        y.right = x;
        x.parent = y;
    }

    public int height() {
        return height(root);
    }

    private int height(RBTNode node) {
        if (node == nil)
            return 0;
        else {
            int lHeight = height(node.left);
            int rHeight = height(node.right);

            if (lHeight > rHeight) {
                lHeight++;
                return lHeight;
            } else {
                rHeight++;
                return rHeight;
            }
        }
    }


    public RBTNode search(int k) {
        RBTNode v = root;
        while (v != nil && k != v.getKey()) {
            if (k < v.getKey()) {
                v = v.getLeft();
            } else {
                v = v.getRight();
            }
        }
        return v;
    }

    private boolean rule1(RBTNode node) {

        if (node.color == null) {
            return false;
        }
        if (node == nil) {
            return true;
        }

        return rule1(node.getLeft()) && rule1(node.getRight());
    }

    //No red node, has red child
    private boolean rule3(RBTNode node) {
        if (node == nil) {
            return true;
        }

        if (node.getColor()) {
            if (node.getLeft().getColor() || node.getRight().getColor()) {
                return false;
            }
        }

        return rule3(node.left) && rule3(node.right);
    }

    //If black node has leaf, leaf has to be red
    private boolean rule4(RBTNode node) {
        if (node == nil) {
            return true;
        }
        if (!node.getColor()) {
            if (node.getLeft() == nil && node.getRight() != nil) {
                if (!node.getRight().getColor()) {
                    return false;
                }
            }
            if (node.left != nil && node.right == nil) {
                if (!node.getLeft().getColor()) {
                    return false;
                }
            }
        }
        return rule4(node.left) && rule4(node.right);
    }

    //Every path has same amount of black nodes
    private int checkFive(RBTNode nodeL) {
        RBTNode nodeR = nodeL;
        int blackCounterL = 0;
        int blackCounterR = 0;
        while (nodeL != nil) {
            if (nodeL.color == RBTNode.black) {
                blackCounterL++;
            }
            nodeL = nodeL.left;
        }
        while (nodeR != nil) {
            if (nodeR.color == RBTNode.black) {
                blackCounterR++;
            }
            nodeR = nodeR.right;
        }
        return blackCounterR - blackCounterL;
    }

    private boolean rule5(RBTNode node) {
        if (node != nil) {
            return true;
        }
        int check = checkFive(node);

        return check == 0 && rule5(node.left) && rule5(node.right);
    }


    public boolean CheckRB() {

        if (!rule1(root)) {
            System.out.println("Violation of rule 1");
            return false;
        }
        if (root.getColor()) {
            System.out.println("Violation of rule 2");
            return false;
        }
        if (!rule3(root)) {
            System.out.println("Violation of rule 3");
            return false;
        }
        if (!rule4(root)) {
            System.out.println("Violation of rule 4");
            return false;
        }
        if (!rule5(root)) {
            System.out.println("Violation of rule 5");
            return false;
        }
        return true;
    }

    public Person get(Integer key) {
        if (key == null) throw new IllegalArgumentException("argument to get() is null");
        return get(root, key);
    }

    // value associated with the given key in subtree rooted at x; null if no such key
    private Person get(RBTNode x, Integer key) {
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else return x.person;
        }
        return null;
    }

    public void listing(RBTNode node) {
        if (node == nil) {
            return;
        }
        listing(node.left);
        System.out.print(((node.color==red)?"Color: Red ":"Color: Black ")+"Key: "+node.key+" Parent: "+node.parent.key+"\n");
        listing(node.right);
    }

    public int getAverageAge(RBTNode node) {
        if (node == nil) {
            return -1;
        }
        getAverageAge(node.left);
        ageAverage += node.person.getAge();
        getAverageAge(node.right);
        return ageAverage;
    }

    private RBTNode findNode(RBTNode nodeToFind, RBTNode node) {
        if (root == nil) {
            return null;
        }

        if (nodeToFind.key < node.key) {
            if (node.left != nil) {
                return findNode(nodeToFind, node.left);
            }
        } else if (nodeToFind.key > node.key) {
            if (node.right != nil) {
                return findNode(nodeToFind, node.right);
            }
        } else if (nodeToFind.key == node.key) {
            return node;
        }
        return null;
    }

    private void transplant(RBTNode target, RBTNode with){
        if(target.parent == nil){
            root = with;
        }else if(target == target.parent.left){
            target.parent.left = with;
        }else
            target.parent.right = with;
        with.parent = target.parent;
    }

    RBTNode treeMinimum(RBTNode subTreeRoot){
        while(subTreeRoot.left!=nil){
            subTreeRoot = subTreeRoot.left;
        }
        return subTreeRoot;
    }

    void rotateLeft(RBTNode node) {
        if (node.parent != nil) {
            if (node == node.parent.left) {
                node.parent.left = node.right;
            } else {
                node.parent.right = node.right;
            }
            node.right.parent = node.parent;
            node.parent = node.right;
            if (node.right.left != nil) {
                node.right.left.parent = node;
            }
            node.right = node.right.left;
            node.parent.left = node;
        } else {//Need to rotate root
            RBTNode right = root.right;
            root.right = right.left;
            right.left.parent = root;
            root.parent = right;
            right.left = root;
            right.parent = nil;
            root = right;
        }
    }

    void rotateRight(RBTNode node) {
        if (node.parent != nil) {
            if (node == node.parent.left) {
                node.parent.left = node.left;
            } else {
                node.parent.right = node.left;
            }

            node.left.parent = node.parent;
            node.parent = node.left;
            if (node.left.right != nil) {
                node.left.right.parent = node;
            }
            node.left = node.left.right;
            node.parent.right = node;
        } else {//Need to rotate root
            RBTNode left = root.left;
            root.left = root.left.right;
            left.right.parent = root;
            root.parent = left;
            left.right = root;
            left.parent = nil;
            root = left;
        }
    }

    boolean delete(RBTNode z){
        if((z = findNode(z, root))==null)
            return false;
        RBTNode x;
        RBTNode y = z; // temporary reference y
        boolean y_original_color = y.color;

        if(z.left == nil){
            x = z.right;
            transplant(z, z.right);
        }else if(z.right == nil){
            x = z.left;
            transplant(z, z.left);
        }else{
            y = treeMinimum(z.right);
            y_original_color = y.color;
            x = y.right;
            if(y.parent == z)
                x.parent = y;
            else{
                transplant(y, y.right);
                y.right = z.right;
                y.right.parent = y;
            }
            transplant(z, y);
            y.left = z.left;
            y.left.parent = y;
            y.color = z.color;
        }
        numberOfElements--;
        if(y_original_color==black)
            deleteFixup(x);
        return true;
    }

    void deleteFixup(RBTNode x){
        while(x!=root && x.color == black){
            if(x == x.parent.left){
                RBTNode w = x.parent.right;
                if(w.color == red){
                    w.color = black;
                    x.parent.color = red;
                    rotateLeft(x.parent);
                    w = x.parent.right;
                }
                if(w.left.color == black && w.right.color == black){
                    w.color = red;
                    x = x.parent;
                    continue;
                }
                else if(w.right.color == black){
                    w.left.color = black;
                    w.color = red;
                    rotateRight(w);
                    w = x.parent.right;
                }
                if(w.right.color == red){
                    w.color = x.parent.color;
                    x.parent.color = black;
                    w.right.color = black;
                    rotateLeft(x.parent);
                    x = root;
                }
            }else{
                RBTNode w = x.parent.left;
                if(w.color == red){
                    w.color = black;
                    x.parent.color = red;
                    rotateRight(x.parent);
                    w = x.parent.left;
                }
                if(w.right.color == black && w.left.color == black){
                    w.color = red;
                    x = x.parent;
                    continue;
                }
                else if(w.left.color == black){
                    w.right.color = black;
                    w.color = red;
                    rotateLeft(w);
                    w = x.parent.left;
                }
                if(w.left.color == red){
                    w.color = x.parent.color;
                    x.parent.color = black;
                    w.left.color = black;
                    rotateRight(x.parent);
                    x = root;
                }
            }
        }
        x.color = black;
    }


    public int getNumberOfElements() {
        return numberOfElements;
    }

    public RBTNode getRoot(){
        return root;
    }


}
