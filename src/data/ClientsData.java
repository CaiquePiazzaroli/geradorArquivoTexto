package data;

import enums.Gender;
import interfaces.PositionableLayoutObject;
import model.Client;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class ClientsData {

    public static Client client = new Client(
            "Roberto Carlos",
            LocalDate.of(1945, 6, 15),
            Gender.MALE,
            BigDecimal.valueOf(5000.00)
    );

    public static List<PositionableLayoutObject>  clients = Arrays.asList(
            new Client(
                    "Caique Mendes",
                    LocalDate.of(1996, 8, 3),
                    Gender.MALE,
                    BigDecimal.valueOf(11947.00)
            ),
            new Client(
                    "Mariana Souza",
                    LocalDate.of(1992, 5, 15),
                    Gender.FEMALE,
                    BigDecimal.valueOf(8500.50)
            ),
            new Client(
                    "Roberto Silva",
                    LocalDate.of(1985, 11, 22),
                    Gender.MALE,
                    BigDecimal.valueOf(15200.00)
            ),
            new Client(
                    "Ana Beatriz",
                    LocalDate.of(2001, 2, 10),
                    Gender.FEMALE,
                    BigDecimal.valueOf(4300.75)
            ),
            new Client(
                    "Lucas Oliveira",
                    LocalDate.of(1998, 7, 30),
                    Gender.MALE,
                    BigDecimal.valueOf(6750.25)
            )
    );


}
