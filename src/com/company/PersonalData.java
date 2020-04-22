package com.company;

public class PersonalData {
    public static final boolean black=false;
    public static final boolean red=true;
    private RBTNode nil = new RBTNode(-1, null);
    private RBTNode root;
    public PersonalData() {
        nil.parent = nil;
        nil.left = nil;
        nil.right = nil;
        nil.setColor(black);
        root = nil;
    } // Constructor
    public void insert(int k, Person person){
        insert(new RBTNode( k, person));
    }
    private void insert(RBTNode z) {
        RBTNode y = nil;
        RBTNode x = root;
        while(x!= nil) {
            y = x;
            if(z.key < x.key) {
                x = x.left;
            }else {
                x = x.right;
            }
        }
        z.parent = y;
        if(y == nil) {
            root = z;
        }else if(z.key < y.key) {
            y.left = z;
        } else {
            y.right = z;
        }
        z.left = nil;
        z.right = nil;
        z.color = red;
        insertFixup(z);

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
        // Line 44-87: http://www.codebytes.in/2014/10/red-black-tree-java-implementation.html 14.04.2020 - 10:21
    }
    private void  leftRotate(RBTNode x) {
        if(x.right == nil) {
            return;
        }
        RBTNode y = x.right;
        x.right = y.left;
        if(y.left != nil) {
            y.left.parent = x;
        }
        y.parent = x.parent;
        if(x.parent == nil) {
            root = y;
        } else if(x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }
        y.left = x;
        x.parent = y;
    }
    private void  rightRotate(RBTNode x) {
        if(x.left == nil) {
            return;
        }
        RBTNode y = x.left;
        x.left = y.right;
        if(y.right != nil) {
            y.right.parent = x;
        }
        y.parent = x.parent;
        if(x.parent == nil) {
            root = y;
        } else if(x == x.parent.right) {
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
        else
        {
            int lHeight= height(node.left);
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
    public Person search(int k) {
        RBTNode v = root;
        while (v != nil && k != v.getKey()) {
            if(k < v.getKey()) {
                v = v.getLeft();
            }else {
                v = v.getRight();
            }
        }
        return v.getPerson();
    }
    private RBTNode search_E(int k) {
        RBTNode v = root;
        while (v != nil && k != v.getKey()) {
            if(k < v.getKey()) {
                v = v.getLeft();
            }else {
                v = v.getRight();
            }
        }
        return v;
    }

    private boolean rule1(RBTNode node) {

        if(node.color ==null) {
            return false;
        }
        if(node == nil ) {
            return true;
        }

        return rule1(node.getLeft()) && rule1(node.getRight());
    }

    //No red node, has red child
    private boolean rule3(RBTNode node) {
        if(node == nil) {
            return true;
        }

        if(node.getColor()) {
            if(node.getLeft().getColor() || node.getRight().getColor()) {
                return false;
            }
        }

        return rule3(node.left) && rule3(node.right);
    }
    //If black node has leaf, leaf has to be red
    private boolean rule4(RBTNode node) {
        if(node == nil) {
            return true;
        }
        if(!node.getColor()) {
            if(node.getLeft() == nil && node.getRight() != nil) {
                if(!node.getRight().getColor()) {
                    return false;
                }
            }
            if(node.left != nil && node.right == nil) {
                if(!node.getLeft().getColor()){
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
        while(nodeL != nil) {
            if(nodeL.color == RBTNode.black) {
                blackCounterL++;
            }
            nodeL = nodeL.left;
        }
        while(nodeR != nil) {
            if(nodeR.color == RBTNode.black) {
                blackCounterR++;
            }
            nodeR = nodeR.right;
        }
        return blackCounterR - blackCounterL;
    }

    private boolean rule5(RBTNode node) {
        if(node != nil) {
            return true;
        }
        int check = checkFive(node);

        return check == 0 && rule5(node.left) && rule5(node.right);
    }


    public boolean CheckRB() {

        if(!rule1(root)) {
            System.out.println("Violation of rule 1");
            return false;
        }
        if(root.getColor()) {
            System.out.println("Violation of rule 2");
            return false;
        }
        if(!rule3(root)){
            System.out.println("Violation of rule 3");
            return false;
        }
        if(!rule4(root)){
            System.out.println("Violation of rule 4");
            return false;
        }
        if(!rule5(root)){
            System.out.println("Violation of rule 5");
            return false;
        }
        return true;
    }
    public void manipulation1() {
        root.color = null;
    }
    public void manipulation2() {
        root.color = red;
    }
    public void manipulation3() {
        int i = 0;
        RBTNode temp = nil;
        while(!(temp.color)) {
            temp = search_E(i);
            i++;
        }
        if(temp.left != nil || temp.right != nil) {
            if(temp.left != nil) {
                temp.left.color = red;
            } else {
                temp.right.color = red;
            }
        }
        if(temp.parent != nil) {
            temp.parent.color = red;
        }
    }
    public void manipulation4() {
        int i = 0;
        RBTNode temp = nil;
        while(true) {
            temp = search_E(i);
            i++;
            if(!(temp.color)) {
                if(temp.getLeft() == nil && temp.getRight() != nil && temp.right.color) {
                    temp.right.color = black;
                    break;
                }
                if(temp.getLeft() != nil && temp.getRight() == nil && temp.left.color) {
                    temp.left.color = black;
                    break;
                }
            }
        }
    }
}
