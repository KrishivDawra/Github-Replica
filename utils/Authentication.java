package utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Authentication {
    public boolean Login(String username, String password) {
        File file = new File("data/user.txt");
        if (!file.exists()) {
            return false;
        }

        try (Scanner fileReader = new Scanner(file)) {
            String existingUsername = null;

            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();

                if (line.startsWith("Username: ")) {
                    existingUsername = line.substring(10);
                }

                if (line.startsWith("Password: ") && existingUsername != null) {
                    String existingPassword = line.substring(10);

                    if (existingUsername.equals(username) && existingPassword.equals(password)) {
                        return true;
                    }

                    existingUsername = null;
                }
            }

        } catch (IOException e) {
            System.out.println("An error occurred while checking login.");
            e.printStackTrace();
        }

        return false;
    }


    public boolean checkUsernameExists(String username) {
        File file = new File("data/data.txt");
        try {
            // If the file does not exist, return false (no users to check)
            if (!file.exists()) {
                return false;
            }

            // Open the file for reading
            Scanner fileReader = new Scanner(file);
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                if (line.startsWith("Username: ")) {
                    String existingUsername = line.substring(10); // Extract username
                    if (existingUsername.equals(username)) {
                        fileReader.close();
                        return true; // Username found
                    }
                }
            }
            fileReader.close();
        } catch (IOException e) {
            System.out.println("An error occurred while checking username.");
            e.printStackTrace();
        }
        return false; // Username not found
    }

    public void register(String name ,String email, String username, String password){
        File file = new File("data/user.txt");
        try {
            // Create FileWriter in append mode to add new controller.data to the file without overwriting
            FileWriter fileWriter = new FileWriter(file, true);  // 'true' appends to the file

            // Write user controller.data to the file
            fileWriter.write("Name: " + name + "\n");
            fileWriter.write("Email: " + email + "\n");
            fileWriter.write("Username: " + username + "\n");
            fileWriter.write("Password: " + password + "\n");
            fileWriter.write("---------------------------\n");

            // Close the FileWriter
            fileWriter.close();

            System.out.println("User registered successfully!");
        } catch (IOException e) {
            System.out.println("An error occurred while registering user.");
            e.printStackTrace();
        }
    }
}
