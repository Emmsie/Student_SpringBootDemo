package be.brussel.model;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "student_sequence")
    @SequenceGenerator(name="student_sequence",
                        sequenceName = "student_sequence",
                        allocationSize = 1)
    private Long id;
    private String name;
    private String email;
    private LocalDate dayOfBirth;
    @Transient
    private Integer age;

    public Student(String name, String email, LocalDate dayOfBirth) {
        this.name = name;
        this.email = email;
        this.dayOfBirth = dayOfBirth;
    }

    public Integer getAge() {
        return Period.between(this.dayOfBirth, LocalDate.now()).getYears();
    }

}
