package controller;

import java.util.Scanner;

public class RegisterInput {

    static String username, password,name, email;
    public static void getInput()
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your Name: ");
        name = sc.nextLine();
        System.out.print("Enter your Email: ");
        email = sc.nextLine();
        System.out.print("Enter your User Name: ");
        username = sc.nextLine();
        System.out.print("Enter your Password: ");
        password = sc.nextLine();
    }
    public static String getUsername()
    {
        return username;
    }
    public static String getPassword()
    {
        return password;
    }
    public static String getEmail()
    {
        return email;
    }
    public static String getName()
    {
        return name;
    }
}
