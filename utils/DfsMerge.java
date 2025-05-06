package utils;

import java.io.File;
import java.io.IOException;

public class DfsMerge {
    public void dfsMerge(File currentFolder, File destinationFolder) {
        File[] files = currentFolder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    File destFile = new File(destinationFolder, file.getName());
                    int copyCount = 1;
                    while (destFile.exists()) {
                        String newName = "copy" + (copyCount > 1 ? copyCount : "") + "_" + file.getName();
                        destFile = new File(destinationFolder, newName);
                        copyCount++;
                    }

                    try {
                        FileService fir = new FileService();
                        fir.copyFile(file, destFile);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (file.isDirectory()) {
                    File newDestFolder = new File(destinationFolder, file.getName());
                    if (!newDestFolder.exists()) {
                        newDestFolder.mkdirs();
                    }


                    dfsMerge(file, newDestFolder);
                }
            }
        }
    }
}
