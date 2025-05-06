package services;
import java.util.*;

import utils.RepositoryServices;

public class Commit {
    public void commit(String folderName ,HashMap<String, String> maps, Stack<String> commitStack)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your choice:");
        System.out.println("1. Create new repo!");
        System.out.println("2. Edit existing repo!");
        int select = sc.nextInt();
        if(select == 1) {
            RepositoryServices repo = new RepositoryServices();
            repo.newRepo(folderName, maps, commitStack);
        }
        else if(select == 2)
        {
            RepositoryServices repo = new RepositoryServices();
            repo.editRepo(maps, commitStack);
        }
    }
}
