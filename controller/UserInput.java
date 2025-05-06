package controller;

import java.util.Scanner;

public class UserInput {
    static String username, password;
    public static void getInput()
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your Username: ");
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
}
