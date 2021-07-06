package be.brussel.config;

import be.brussel.model.Student;
import be.brussel.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository){
        return args -> {
            Student Jip = new Student("Jip", "jip@jipmail.be", LocalDate.of(2018, Month.DECEMBER, 31));
            Student Dirk = new Student("Dirk", "dirk.hellothere@jipmail.be", LocalDate.of(2000, Month.AUGUST, 1));

            studentRepository.saveAll(List.of(Jip, Dirk));
        };
    }
}
