package services;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Represents a File Generator class that creates a file csv or txt.
 * @author caiquePiazzaroli
 * @version 1.0
 * */
public class FileGenerator {

    /**
     * Creates a file from a URL in the filesystem
     * @Param Path file: url that points to a file with .txt or .csv format
     * */
    public void createFile(Path file) {

        if(!validateFileExtension(file)) {
            System.out.println("Invalid file: ".concat(file.toString()));
            System.out.println("Make sure to pass a .csv or .txt file Path");
        }

        if(!validateDirectoryExistence(file)) {
            createParentDirectory(file);
        }

        try {
            Files.createFile(file);
        } catch (FileAlreadyExistsException e) {
            System.out.println("The FILE already exist in this path!");
        } catch (IOException e) {
            System.out.println("The FILE could not be created!");
        }
    }

    /**
     * Validate directory existence of a URL file.
     * @Param Path file: url that points to a file with .txt or .csv format
     * @return boolean that indicates if directory exists or not.
     * */
    private boolean validateDirectoryExistence(Path directory) {

        String directoryName = directory.getFileName().toString();

        if(directoryName.endsWith(".txt") || directoryName.endsWith(".csv")) {
            directory = directory.getParent();
        }

        return Files.exists(directory);
    }


    /**
     * Create a parent directory based on the URL Path file
     * @Param Path file: url that points to a file with .txt or .csv format
     * @return no return (void)
     * */
    private void createParentDirectory(Path file) {
        try {
            Path parentDirectory = file.getParent();
            Files.createDirectories(parentDirectory);
        } catch (IOException e) {
            System.err.println("The DIRECTORY could not be created!");
        }
    }

    /**
     * Validate if Path file provided is compatible with .csv or .txt extension
     * @Param Path file: url that points to a file with .txt or .csv format
     * @return boolean that indicates if the file is valid or not.
     * */
    private boolean validateFileExtension(Path file) {
        String fileName = file.getFileName().toString();
        return fileName.endsWith(".csv") || fileName.endsWith(".txt");
    }
}
