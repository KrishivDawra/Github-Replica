package modal;
import java.io.File;
public class Project {
    public static String folderName(String username)
    {
        String parentDir = "repositories";

        // Create repositories folder if it doesn't exist
        File repoFolder = new File(parentDir);
        if (!repoFolder.exists()) {
            repoFolder.mkdir();
        }

        File userFolder = new File(parentDir + "/" + username);
        if (!userFolder.exists()) {
            if (userFolder.mkdir()) {
                System.out.println("Folder created: " + userFolder.getPath());
            } else {
                System.out.println("Failed to create folder!");
            }
        } else {
            System.out.println("⚠️ Folder already exists for user: " + username);
        }
        return username;
    }
}
