package utils;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.*;

public class FileService {
    public boolean addFile(String folderName, String repo, String fileName, String commit, String commitHistory) {
        // Create folder with the user's folderName
        File folder = new File("Repositories/" + folderName);

        // If the folder doesn't exist, create it
        if (!folder.exists()) {
            if (folder.mkdirs()) {
                System.out.println("Folder created at: " + folder.getPath());
            } else {
                System.out.println("Failed to create folder.");
                return false;  // Return false if the folder can't be created
            }
        } else {
            System.out.println("Folder already exists at: " + folder.getPath());
        }

        // Create the repository folder inside the user's folder
        File repoFolder = new File(folder, repo);

        // If the repo folder doesn't exist, create it
        if (!repoFolder.exists()) {
            if (repoFolder.mkdirs()) {
                System.out.println("Repository folder created at: " + repoFolder.getPath());
            } else {
                System.out.println("Failed to create repository folder.");
                return false;  // Return false if the repo folder can't be created
            }
        } else {
            System.out.println("Repository folder already exists at: " + repoFolder.getPath());
        }

        // Now, create the file inside the repo folder
        File file = new File(repoFolder, fileName);

        // Create or open the data file to append commit details
        File data = new File("data/data.txt");
        try {
            // Write commit history to the data file
            FileWriter fw = new FileWriter(data, true); // true for appending
            fw.write("File Name: " + fileName + "\n");
            fw.write("Changes: " + commit + "\n");
            fw.write("Message: " + commitHistory + "\n");
            fw.write("---------------------------------------" + "\n");
            fw.close();

            // Write the commit details into the file inside the repository folder
            FileWriter fs = new FileWriter(file, true);
            fs.write(commit + "\n");
            fs.close();

            return true;  // Return true when everything is done successfully

        } catch (IOException e) {
            e.printStackTrace();
            return false;  // Return false if there is an exception
        }

    }


    public void copyFile(File source, File dest) throws IOException {
        try (InputStream in = new FileInputStream(source);
             OutputStream out = new FileOutputStream(dest)) {

            byte[] buffer = new byte[1024];
            int length;
            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }
        }
    }



    public void createFile(File repoFolder, String fileName, String changes, String message) {
        File file = new File(repoFolder, fileName); // Create the file inside the repo folder
        File data = new File("data/data.txt");
        try {
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getAbsolutePath());
            } else {
                System.out.println("File already exists.");
            }

            // Write changes to the file
            FileWriter writer = new FileWriter(file);
            writer.write(changes);
            writer.close();

            // Log commit message in data.txt
            FileWriter fs = new FileWriter(data, true);
            fs.write("File Name: " + fileName + "\n");
            fs.write("Message: " + message + "\n");
            fs.write("---------------------------------\n");
            fs.close();

            System.out.println("Data written to file successfully!");

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


    public void editFile(File repoFolder, String fileName, String changes, String message) {

        File file = new File(repoFolder, fileName); // Look for the file inside the given repo folder
        File data = new File("data/data.txt");

        if (file.exists() && file.isFile()) {
            try {
                // Append changes to the file
                FileWriter fw = new FileWriter(file, true); // append mode
                fw.write(changes + "\n");
                fw.close();

                // Log commit message in data.txt
                FileWriter fs = new FileWriter(data, true);
                fs.write("File Name: " + fileName + "\n");
                fs.write("Message: " + message + "\n");
                fs.write("---------------------------------\n");
                fs.close();

                System.out.println("Changes appended to " + file.getPath());
            } catch (IOException e) {
                System.out.println("Error writing to the file.");
                e.printStackTrace();
            }
        } else {
            System.out.println("File doesn't Exist!");
        }
    }



}
