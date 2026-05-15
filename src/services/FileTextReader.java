package services;

import enums.Gender;
import model.Client;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that Read delimited and Positioned Files.
 *
 * @author caiquePiazzaroli
 * @version 1.0
 * */
public class FileTextReader {

    /**
     * Validate extension and existence of file
     *
     * @Param Path file: url that points to a file with .txt or .csv format
     * @return boolean that indicates if the files exists
     * */
    private static boolean validateFile(Path file) {
        String fileName = file.getFileName().toString();
        return (fileName.contains(".csv") || fileName.contains(".txt")) && Files.exists(file);
    }

    /**
     * Read all lines of a delimited file
     *
     * @Param Path file: url that points to a file with .txt or .csv format
     * @return List<String> containing all the lines of file
     * */
    public static List<String> readDelimited(Path file) {

        List<String> fileLines = new ArrayList<>();

        if (!validateFile(file)) {
            System.out.println("The file not exists or the extension is not valid! Provide a valid file!");
            return null;
        }

        try {
            return Files.readAllLines(file);
        } catch (IOException e) {
            System.out.println(e);
            return null;
        }
    }

    /**
     * Parse a Client class delimited file to a List<Client>
     *
     * @Param Path file: url that points to a file with .txt or .csv format
     * @return List<Client> that contains all clients found in file
     * */
    public static List<Client> fromDelimited(Path file) {

        List<Client> clients = new ArrayList<>();
        List<String> lines = readDelimited(file);

        if (lines == null) {
            System.out.println("File lines are null!");
            return null;
        }

        try {
            for (String line : lines) {
                String[] fields = line.split(";");
                clients.add(
                        new Client(
                                fields[0],
                                LocalDate.parse(fields[1]),
                                Gender.valueOf(fields[2]),
                                new BigDecimal(fields[3])
                        )
                );
            }
            return clients;
        } catch (Exception e) {
            System.out.println("File data is incorrect or invalid!");
            return null;
        }
    }
    // readPositioned(Path file) => List<String>
    // fromPositioned(Path file) => List<Client>
 }
