package asw;


import java.text.ParseException;

import org.apache.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import asw.dbManagement.repository.InitParticipantRepository;
import asw.dbManagement.repository.ParticipantRepository;

@SpringBootApplication
@EnableScheduling
public class Application {
	public static final Logger logger = Logger.getLogger(Application.class);

    public static void main(String[] args) {
    	SpringApplication.run(Application.class, args);
    }
    
    @Bean
	public CommandLineRunner initDB(ParticipantRepository repository) throws ParseException {
    	return (args) -> { InitParticipantRepository.initDB(repository); };
    }
}