package com.company;

public class RBTNode {
    public static final boolean black = false;
    public static final boolean red = true;
    public int key;
    public Boolean color;
    public RBTNode left, right, parent;
    public Person person;
    public int size;

    public RBTNode(int k, Person person) {
        setKey(k);
        setColor(red);
        setPerson(person);
        setLeft(null);
        setRight(null);
        setParent(null);
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Person getPerson() {
        return person;
    }

    public String getName() {
        return person.getName();
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public Boolean getColor() {
        return color;
    }

    public void setColor(Boolean color) {
        this.color = color;
    }

    public RBTNode getLeft() {
        return left;
    }

    public void setLeft(RBTNode left) {
        this.left = left;
    }

    public RBTNode getRight() {
        return right;
    }

    public void setRight(RBTNode right) {
        this.right = right;
    }

    public RBTNode getParent() {
        return parent;
    }

    public void setParent(RBTNode parent) {
        this.parent = parent;
    }

    public static boolean isBlack() {
        return black;
    }

    public static boolean isRed() {
        return red;
    }
}

