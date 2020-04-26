package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PersonalDataManagement {

    public static void main(String[] args) {
        var rbt = new PersonalData();
        int counter = 0;
        String surname;
        String name;
        int age;
        String gender;
        var input = new Scanner(System.in);
        Scanner reader = null;
        String caseInput = "whatever";

        System.out.println("Personal Data Management System 1.0 \n Specify absolute path to the data file:");

        String path = input.nextLine();

        try {
            reader = new Scanner(new File(path)).useDelimiter("\\s*,\\s*");
        } catch (FileNotFoundException e) {
            System.out.println("Error: file not found");
            e.printStackTrace();
        }

        assert reader != null;

        while(reader.hasNext()){
            surname = reader.next();
            name= reader.next();
            age = reader.nextInt();
            reader.skip(", ");
            gender = reader.nextLine();
            rbt.insert(counter, new Person(surname, name, age, gender));
            counter++;
        }
        reader.close();

        System.out.println("Select operation: \n (d)elete, (i)nsert, (s)earch, (n)umber, (v)average, (l)isting, (e)nd");
        while(!caseInput.equals("e")){
            caseInput = input.next();
            switch (caseInput){
                case "d":
                    System.out.println("Enter key (int) of node to delete:");
                    rbt.delete(input.nextInt());
                    break;
                case "i":
                    System.out.println("Insertion of new person. \n Enter surname:");
                    surname = input.next();
                    System.out.println("Enter first name:");
                    name = input.next();
                    System.out.println("Enter age:");
                    age = input.nextInt();
                    System.out.println("Enter gender:");
                    gender = input.next();
                    rbt.insert(counter, new Person(surname, name, age, gender));
                    break;
                case "s":
                    System.out.println("Enter key (int) of node to search: ");
                    rbt.search(input.nextInt()).toString();
                    break;
                case "n":
                    System.out.println("Number of people: " + rbt.getNumberOfElements());
                    break;
                case "v":
                    int average = 0;
                    for(int i = 0; i <= rbt.getNumberOfElements(); i++){
                        System.out.println(rbt.get(i).getAge());
                        average += rbt.get(i).getAge();
                    }
                    System.out.println("Average age of people: " + average/rbt.getNumberOfElements());
                    break;
                case "l":
                    for(int i = 0; i <= rbt.getNumberOfElements(); i++){
                        System.out.println(rbt.get(i).toString());
                    }
            }
        }

    }


}