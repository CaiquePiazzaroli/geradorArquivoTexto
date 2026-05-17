import data.ClientsData;
import services.FileGenerator;
import services.FileTextWriter;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {

        Path file = Paths.get("c:", "geradorDeArquivos", "meuArquivoDelimitado.txt");

        if(!Files.exists(file)) {
            FileGenerator fg = new FileGenerator();
            fg.createFile(file);
        }

        //System.out.println(FileTextWriter.writeDelimited(ClientsData.client, ';', file));

//        List<Client> clients = FileTextReader.fromDelimited(file);
//        if (clients != null) {
//            for (Client client : clients) {
//                System.out.println(client.getName());
//            }
//        }

        System.out.println(FileTextWriter.writePositioned(ClientsData.client, file));
    }
}
