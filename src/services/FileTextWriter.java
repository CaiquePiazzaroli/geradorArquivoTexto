package services;

import interfaces.PositionedLayout;
import model.Client;

import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.*;


/**
 * Class that write delimited or Positioned Files.
 *
 * @author caiquePiazzaroli
 * @version 1.0
 * */
public class FileTextWriter {

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
     * Write a unique client in delimited format
     *
     * @Param client - The Client object that represents a client.
     * @Param delimiter - A Character object that represent the delimiter pattern for write the file.
     * @param file - A Path object that points to a existent file in filesystem
     * @return String wrote in the file or null
     * */
    public static String writeDelimited(Client client, Character delimiter, Path file) {

        if (!validateFile(file)) {
            System.out.println("The file not exists or the extension is not valid! Provide a valid file!");
            return null;
        }

        String line = client.getName().concat(delimiter.toString())
                .concat(client.getDateBirth().toString()).concat(delimiter.toString())
                .concat(client.getGender().toString()).concat(delimiter.toString())
                .concat(client.getWage().toPlainString()).concat(delimiter.toString())
                .concat("\n");

        try {
            Files.writeString(file, line, StandardOpenOption.APPEND);
            return line;
        } catch (IOException e) {
            System.out.println("Error when trying to write file");
            return null;
        }
    }

    /**
     * Write a collection List Client in delimited format
     *
     * @Param clients - The List<Client> collection that represents collection of clients.
     * @Param delimiter - A Character object that represent the delimiter pattern for write the file.
     * @param file - A Path object that points to a existent file in filesystem
     * @return A collection of clients wrote in the file: List<Client>
     * */
    public static List<Client> writeAllDelimited(List<Client> clients, Character delimiter, Path file) {

        if (!validateFile(file)) {
            System.out.println("The file not exists or the extension is not valid! Provide a valid file!");
            return null;
        }

        StringBuilder allLines = new StringBuilder();

        for (Client client : clients) {
            allLines.append(client.getName().concat(delimiter.toString())
                    .concat(client.getDateBirth().toString()).concat(delimiter.toString())
                    .concat(client.getGender().toString()).concat(delimiter.toString())
                    .concat(client.getWage().toPlainString()).concat(delimiter.toString())
                    .concat("\n"));
        }

        try {
            Files.writeString(file, allLines.toString(), StandardOpenOption.APPEND);
            return clients;
        } catch (IOException e) {
            System.out.println("Error when trying to write file");
            return null;
        }
    }

    // writePositioned => String
    public static String writePositioned(PositionedLayout positionableObject, Path file) {

        Map<String, Integer> lastCharPositionFields = positionableObject.getCharacterNumberFields();
        StringBuilder line = new StringBuilder();

        for (String fieldName : lastCharPositionFields.keySet()) {
            try {
                Method fieldGetMethod = positionableObject.getClass().getMethod(fieldName);
                Integer fieldCharLastPosition = lastCharPositionFields.get(fieldName);

                line.append(fieldGetMethod.invoke(positionableObject).toString());

                while (line.length() < fieldCharLastPosition) {
                    line.append(" ");
                }

            } catch (Exception e) {
                System.out.println(e);
            }
        }

        return  line.toString();
    }

    // writeAllPositioned => List<String>


}
