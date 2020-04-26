package com.company;

public class Person {
    private String surname;
    private String name;
    private int age;
    private String gender;
    private int id;

    public Person(String surname, String name, int age, String gender) {
        this.surname = surname;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Person{" +
                "Surname='" + surname + '\'' +
                ",First name='" + name + '\'' +
                ", Age=" + age +
                ", Gender='" + gender + '\'' +
                ", Id=" + id +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }
}
