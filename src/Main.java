import data.ClientsData;
import model.Client;
import services.FileGenerator;
import services.FileTextReader;
import services.FileTextWriter;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {

        Path file = Paths.get("c:", "geradorDeArquivos", "meuArquivoPosicional.txt");



        if(!Files.exists(file)) {
            FileGenerator fg = new FileGenerator();
            fg.createFile(file);
        }

        FileTextReader.readPositioned(file, Client.class);

    }
}
