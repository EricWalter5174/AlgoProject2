/*
 * Authors: Eric Walter, Giuseppe Buccellato
 * Algorithms and Datastructures
 * University of Applied Sciences Osnabrueck
 * Computer Science and Media Applications (B.Sc.)
 * Summer Semester 2020
 * */
package com.company;

public class Person {
    private String surname;
    private String name;
    private int age;
    private String gender;
    private Integer id;

    public Person(String surname, String name, int age, String gender, Integer id) {
        this.surname = surname;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.id = id;
    }

    @Override
    public String toString() {
        return "ID: " + id +
                "\nLast name: '" + surname +
                "\nFirst name: '" + name + '\'' +
                "\nAge: " + age +
                "\nGender: '" + gender + '\'' + "\n";
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

}
