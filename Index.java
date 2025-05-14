import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import controller.RegisterInput;
import modal.User;
import services.Merge;
import utils.Authentication;
import services.Commit;
import services.UndoCommit;
import services.UserCommit;

public class Index {
    public static void main(String[] args) {
        HashMap<String,String> maps = new HashMap<>();

        //Stack<String> commitStack = loadCommitStack();
        Stack<String> commitStack = new Stack<>();
        //Queue<String> pushQueue = loadPushQueue();
        Authentication loginChecker = new Authentication();


        System.out.println("Welcome to GitTrack...");
        System.out.println("Enter your choice!");
        System.out.println("1. Sign Up");
        System.out.println("2. Login");

        Scanner sc = new Scanner(System.in);
        int first_choice = sc.nextInt();
        sc.nextLine();

        if (first_choice == 1) {
            RegisterInput register = new RegisterInput();
            register.getInput();
            Authentication registerUser = new Authentication();
            String name = register.getName();
            String email = register.getEmail();
            String username = register.getUsername();
            String password = register.getPassword();
            registerUser.register(name , email, username, password);
            first_choice = 2;
//            if(isRegistered)
//            {
//                System.out.println("Registered Succesfully!");
//            }
//            else {
//                System.out.println("Registration failed or username already exists.");
//            }
        }
        if (first_choice == 2) {
            System.out.println("Enter login details!");
            User user = new User();
            user.fetchUserData();
            String folderName = "";
            boolean islogin = false;
            if (user.authenticate()) {
                folderName = user.Project_initialize();
                System.out.println("Login Successful! Welcome, " + user.getStoredUsername());
                islogin = true;
            } else {
                System.out.println("Login Failed! Invalid username or password.");
            }

            if (islogin) {
                System.out.println("Login successful! Welcome, " + user.getStoredUsername() + "!");
                int k = 0;
                while(true)
                {
                    System.out.println("1. Commit");
                    System.out.println("2. Merge Repo's");
                    System.out.println("3. Check commit history");
                    System.out.println("4. Undo commit");
                    System.out.println("5. Exit!");
                    int choice = sc.nextInt();

                    if(choice == 1)
                    {
                        Commit commit1 = new Commit();
                        commit1.commit(folderName, maps, commitStack);
                    }
                    else if(choice == 2)
                    {
                        System.out.println("Enter first folder to services.merge: ");
                        sc.nextLine();
                        String folderA = sc.nextLine();
                        System.out.println("Enter second folder to services.merge: ");
                        String folderB = sc.nextLine();
                        System.out.println("enter name of new folder:");
                        String mergename = sc.nextLine();
                        System.out.println("Enter repo name: ");
                        String repo = sc.nextLine();
                        Merge desti = new Merge(user.getStoredUsername(), repo, folderA, folderB, mergename);

                    }
                    else if(choice == 3)
                    {
                        UserCommit hashmap = new UserCommit();
                        hashmap.userCommit(maps);

                        System.out.println("Do you want to continue ?(Y/N)");
                        char c = sc.next().charAt(0);
                        if(c == 'N')
                        {
                            break;
                        }
                    }
                    else if(choice == 4)
                    {
                        UndoCommit prop = new UndoCommit();
                        prop.undoCommit(commitStack);

                        System.out.println("Do you want to continue ?(Y/N)");
                        char c = sc.next().charAt(0);
                        if(c == 'N' || c == 'n')
                        {
                            break;
                        }
                    }

                    else if(choice  == 5)
                    {
                        break;
                    }
                }
            } else {
                System.out.println("Invalid Username or Password. Please try again.");
            }
        }
        else {
            System.out.println("Invalid choice. Please restart the program.");
        }

        sc.close();
    }



    public static void saveCommitStack(Stack<String> stack) {
        try {
            FileWriter fw = new FileWriter("stack.txt");
            for (String commit : stack) {
                fw.write(commit + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Error saving commit stack.");
            e.printStackTrace();
        }
    }

    public static void savePushQueue(Queue<String> pushQueue) throws IOException {
        FileWriter fw = new FileWriter("queue.txt");
        BufferedWriter bw = new BufferedWriter(fw);
        for (String commit : pushQueue) {
            bw.write(commit);
            bw.newLine();
        }
        bw.close();
        fw.close();
    }


//    public static Stack<String> loadCommitStack() {
//        Stack<String> stack = new Stack<>();
//        try {
//            File file = new File("stack.txt");
//            if (file.exists()) {
//                Scanner sc = new Scanner(file);
//                while (sc.hasNextLine()) {
//                    stack.push(sc.nextLine());
//                }
//                sc.close();
//            }
//        } catch (IOException e) {
//            System.out.println("Error loading commit stack.");
//            e.printStackTrace();
//        }
//        return stack;
//    }

//    public static Queue<String> loadPushQueue() {
//        Queue<String> queue = new LinkedList<>();
//        try {
//            File file = new File("queue.txt");
//            if (file.exists()) {
//                Scanner sc = new Scanner(file);
//                while (sc.hasNextLine()) {
//                    queue.add(sc.nextLine());
//                }
//                sc.close();
//            }
//        } catch (IOException e) {
//            System.out.println("Error loading push queue.");
//            e.printStackTrace();
//        }
//        return queue;
//    }




}
