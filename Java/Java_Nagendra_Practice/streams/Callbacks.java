package com.nagendra.practice.com.nagendra.practice;

import java.util.function.Consumer;

public class Callbacks
{
    static void  hello1(String firstName, String lastName, Consumer<String> callback)
    {
        System.out.println(firstName);
        if (lastName != null)
        {
            System.out.println(lastName);
        }
        else
        {
            callback.accept(firstName);
        }
    }
    static void  hello2(String firstName, String lastName, Runnable callback)
    {
        System.out.println(firstName);
        if (lastName != null)
        {
            System.out.println(lastName);
        }
        else
        {
            callback.run();
        }
    }


    public static void main(String[] args)
    {
        hello1("Mir", "Arham", value -> {
            System.out.println("No lastName provided for "+value);
        });
        hello2("Prakash",
                null,
                () -> System.out.println("No LastName Provided"));
    }

}