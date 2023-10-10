package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello from the main class");
        Scanner sc = new Scanner(System.in);

        Alien a1 = new Alien();
        System.out.println("Setting the details through lombok");
        System.out.println("Enter the age ");
        int age = sc.nextInt();
        a1.setAge(age);

        System.out.println("Enter the name ");
        String name = sc.next();
        a1.setName(name);

        System.out.println("Enter the Tech ");
        String tech = sc.next();
        a1.setTech(tech);

        System.out.println();
        System.out.println("Getting the details through lombok");
        System.out.println("The age which is entered is = "+a1.getAge());
        System.out.println("The name which is entered is = "+a1.getName());
        System.out.println("The tech which is entered is = "+a1.getTech());

    }
}