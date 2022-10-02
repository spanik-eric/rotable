package at.rotable.terminplanungaufgabe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"at.rotable.terminplanungaufgabe.*"})
public class TerminplanungAufgabeApplication {

    public static void main(String[] args) {
        SpringApplication.run(TerminplanungAufgabeApplication.class, args);
    }

}
