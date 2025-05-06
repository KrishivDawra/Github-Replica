package services;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
public class UserCommit {
    public void userCommit(HashMap <String, String> maps)
    {
        Scanner sc = new Scanner((System.in));
        for (Map.Entry<String, String> entry : maps.entrySet()) {
            System.out.println("------------------------------------------------------------");
            System.out.println("File Name: " + entry.getKey() + ", Commit message: " + entry.getValue());
        }
        System.out.println("------------------------------------------------------------");
    }
}
