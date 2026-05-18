package services;

import interfaces.PositionableLayoutObject;
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

    /**
     * Save a positionable layout object in a positionable object
     * @param positionableObject -> Object that implements PositionedLayout
     * @param file  -> File Path that points to txt or csv file
     * @return String containing wrote objects
     * */
    public static String writePositioned(PositionableLayoutObject positionableObject, Path file) {

        String line = prepareString(positionableObject);

        if (!validateFile(file)) {
            System.out.println("The file not exists or the extension is not valid! Provide a valid file!");
            return null;
        }

        try {
            Files.writeString(file, line, StandardOpenOption.APPEND);
            return line;
        } catch (IOException e) {
            System.out.println("Error when trying to write file");
            return null;
        }

    }

    /**
     * Build a single positional String based on PositionedLayout Object
     * @param positionableObject -> Object that implements PositionedLayout
     * @return A String containing the object information in positional format
     * */
    private static String prepareString(PositionableLayoutObject positionableObject) {
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

        line.append("\n");

        return  line.toString();
   }


    /**
     * Write a collection List PositionedLayoutObject in positioned format
     *
     * @Param positionedObjectsList - The List<PositionedLayoutObject> collection that represents collection of objects that implements PositionedLayoutObject.
     * @param file - A Path object that points to a existent file in filesystem
     * @return A collection of clients wrote in the file: List<PositionedLayoutObject>
     * */
    public static List<PositionableLayoutObject> writeAllPositioned(List<PositionableLayoutObject> positionedObjectsList, Path file) {

        StringBuilder allLines = new StringBuilder();

        for(PositionableLayoutObject client : positionedObjectsList) {
            allLines.append(prepareString(client));
        }

        try {
            Files.writeString(file, allLines.toString(), StandardOpenOption.APPEND);
            return positionedObjectsList;
        } catch (IOException e) {
            System.out.println("Error when trying to write file");
            return null;
        }
    }

}
