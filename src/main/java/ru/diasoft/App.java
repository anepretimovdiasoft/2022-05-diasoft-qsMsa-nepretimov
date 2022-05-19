package ru.diasoft;

import org.h2.tools.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class App {

    public static void main(String[] args) {

        SpringApplication.run(App.class, args);

        //Чтобы смотреть бд через DBeaver
        //Через браузер http://localhost:8080/h2-console
        try {
            Server server = Server.createTcpServer().start();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
