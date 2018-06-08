package ms2restspringdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Application {
	
	public static void main(String[] args) throws Exception {
		System.out.println("H2 Web Console im Browser mit \"http:/localhost:8080/console\" erreichbar.");
		System.out.println("JDBC-URL ist: jdbc:h2:mem:testdb");
		SpringApplication.run(Application.class, args);
	}
}
