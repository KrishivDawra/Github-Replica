package modal;

import controller.UserInput;
import utils.Authentication;
import utils.FileService;


public class User {
    private String storedUsername;
    private String storedPassword;

    // Method to get input and store it
    public void fetchUserData() {
        UserInput ui = new UserInput();
        ui.getInput();

        storedUsername = ui.getUsername();
        storedPassword = ui.getPassword();
    }

    public boolean authenticate() {
        Authentication loginChecker = new Authentication();
        return loginChecker.Login(storedUsername, storedPassword);
    }

    public String getStoredUsername() {
        return storedUsername;
    }

    public String getStoredPassword() {
        return storedPassword;
    }
    public String Project_initialize() {
        Project project = new Project();
        return project.folderName(storedUsername);
    }

}
