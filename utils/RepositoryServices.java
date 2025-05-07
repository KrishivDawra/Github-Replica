package utils;

import modal.User;

import java.io.File;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

public class RepositoryServices {
    public void editRepo(String folderName, HashMap<String, String> maps, Stack<String> commitStack) {
        Scanner sc = new Scanner(System.in);
//        System.out.println("Enter Repo Name: ");
//        String folderName = sc.nextLine();
        File folder = new File("Repositories/" + folderName); // Check for folder inside Repositories

        if (folder.exists()) {
            System.out.println("Folder exists: " + folder.getAbsolutePath());
            System.out.println("Enter Repo Name (e.g., 'repo1'): ");
            String repoName = sc.nextLine();
            File repoFolder = new File(folder, repoName); // Check for repo inside folderName

            if (repoFolder.exists()) {
                System.out.println("Repo found: " + repoFolder.getAbsolutePath());
                System.out.println("1. Want to create new file ? ");
                System.out.println("2. Edit existing file ?");
                int ch = sc.nextInt();
                sc.nextLine(); // To consume newline character

                if (ch == 1) {
                    // Create new file
                    System.out.println("Enter new file name:");
                    String fileName = sc.nextLine();

                    System.out.print("Enter your changes: ");
                    String changes = sc.nextLine();

                    System.out.print("Enter commit message: ");
                    String message = sc.nextLine();

                    maps.put(fileName, message);
                    commitStack.push("Commit: " + message + " on file: " + fileName);
                    FileService filecreation = new FileService();
                    filecreation.createFile(repoFolder, fileName, changes, message); // Pass repoFolder
                } else if (ch == 2) {
                    // Edit existing file
                    System.out.println("Enter file name you want to edit ? ");
                    String fileName = sc.nextLine();

                    System.out.print("Enter your changes: ");
                    String changes = sc.nextLine();

                    System.out.print("Enter commit message: ");
                    String message = sc.nextLine();

                    maps.put(fileName, message);
                    commitStack.push("Commit: " + message + " on file: " + fileName);
                    FileService edition = new FileService();
                    edition.editFile(repoFolder, fileName, changes, message); // Pass repoFolder
                }
            } else {
                System.out.println("Repo doesn't Exist inside the folder: " + folderName);
            }
        } else {
            System.out.println("Folder doesn't exist in Repositories!");
        }
    }



    public void newRepo(String folderName,HashMap<String, String> maps, Stack<String> commitStack)
    {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter repo name!");
        String repo = sc.nextLine();
        while (true) {
            System.out.print("Enter File Name: ");
            String fileName = sc.nextLine();
            System.out.print("Enter your changes: ");
            String commit = sc.nextLine();
            System.out.print("Enter your commit message: ");
            String commitHistory = sc.nextLine();

            FileService commit1 = new FileService();
            boolean commits = commit1.addFile(folderName, repo, fileName, commit, commitHistory);
            if (commits) {
                File file = new File(fileName);
                maps.put(fileName, commitHistory);
                System.out.println("Commit controller.data successfully saved to: " + file.getPath());
            } else {
                System.out.println("An error occurred while saving user controller.data.");
            }



            commitStack.push("Commit: " + commitHistory + " on file: " + fileName);
            //pushQueue.add("Commit: " + commitHistory + " on file: " + fileName);

            System.out.println("want to add more repo's ?(Yes/No)");
            String reply = sc.nextLine();
            if (reply.equals("No")) {
                break;
            }
        }
    }
}
