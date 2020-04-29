/*
* Authors: Eric Walter, Giuseppe Buccellato
* Algorithms and Datastructures
* University of Applied Sciences Osnabrueck
* Computer Science and Media Applications (B.Sc.)
* Summer Semester 2020
* */
package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PersonalDataManagement {

    public static void main(String[] args) {
        var rbt = new RedBlackTree();
        var input = new Scanner(System.in);
        Scanner reader = null;
        int key = 0;
        int age;
        String surname;
        String name;
        String gender;
        String caseInput = "whatever";

        System.out.println("Personal Data Management System 1.0\nSpecify absolute path to the data file:");

        String path = input.nextLine();

        try {
            reader = new Scanner(new File(path)).useDelimiter("\\s*,\\s*");
        } catch (FileNotFoundException e) {
            System.out.println("Error: file not found");
            e.printStackTrace();
        }
        assert reader != null;
        while (reader.hasNext()) {
            surname = reader.next();
            name = reader.next();
            age = reader.nextInt();
            reader.skip(", ");
            gender = reader.nextLine();
            rbt.insert(key, new Person(surname, name, age, gender, key));
            key++;
        }
        reader.close();

        while (!caseInput.equals("e")) {
            System.out.println("Select operation: \n (d)elete, (i)nsert, (s)earch, (n)umber, (v)average, (l)isting, (e)nd");
            caseInput = input.next();
            switch (caseInput) {
                case "d":
                    System.out.println("Enter key (int) of node to delete:");
                    int tmp = input.nextInt();
                    if (tmp < 0 || tmp > rbt.getNumberOfElements()) {
                        System.out.println("Person with ID " + tmp + " does not exist");
                        break;
                    }
                    rbt.delete(rbt.search(tmp));
                    key = tmp;
                    break;
                case "i":
                    System.out.println("Insertion of new person. \n Enter last name:");
                    surname = input.next();
                    System.out.println("Enter first name:");
                    name = input.next();
                    System.out.println("Enter age:");
                    age = input.nextInt();
                    System.out.println("Enter gender:");
                    gender = input.next();
                    rbt.insert(key, new Person(surname, name, age, gender, key));
                    if (!(key < rbt.getNumberOfElements())) {
                        key++;
                    }
                    if (rbt.search(key) != null) {
                        key = rbt.getNumberOfElements();
                    }
                    System.out.println("Person inserted");
                    break;
                case "s":
                    System.out.println("Enter key (int) of node to search: ");
                    System.out.println(rbt.search(input.nextInt()).getPerson().toString());
                    break;
                case "n":
                    System.out.println("Number of people: " + rbt.getNumberOfElements());
                    break;
                case "v":
                    System.out.println("Average age of people: " + rbt.getAverageAge(rbt.getRoot()) / rbt.getNumberOfElements());
                    rbt.resetAgeAverage();
                    break;
                case "l":
                    rbt.listing(rbt.getRoot());
            }
        }

    }


}