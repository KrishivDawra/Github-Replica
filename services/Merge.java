package services;

import java.io.*;
import utils.DfsMerge;

public class Merge {
    public Merge(String username, String repo, String FolderA, String FolderB, String mergename) {
        // Your folders
        File folderA = new File(FolderA);
        File folderB = new File(FolderB);

        File mergedFolder = new File("Repositories/"+ username + "/" + repo + "/" + mergename); // Destination

        if (!mergedFolder.exists()) {
            mergedFolder.mkdirs();
        }

        // Use DFS to merge Folder A
        DfsMerge fir = new DfsMerge();
        fir.dfsMerge(folderA, mergedFolder);

        // Use DFS to merge Folder B
        DfsMerge sec = new DfsMerge();
        sec.dfsMerge(folderB, mergedFolder);

        System.out.println("Folders merged successfully with Graph (DFS) traversal!");
    }
}
