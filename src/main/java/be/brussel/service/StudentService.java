package be.brussel.service;

import be.brussel.model.Student;
import be.brussel.repository.StudentRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    //@Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {

        checkIfEmailAlreadyExistInDB(student.getEmail());
        studentRepository.save(student);
    }

    public void deleteStudentById(Long studentId){
        boolean studentExist = studentRepository.existsById(studentId);

        if(!studentExist){
            throw new IllegalStateException("student with id "+ studentId+" does not exist in db");
        } else{
            studentRepository.deleteById(studentId);
        }
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(()-> new IllegalStateException("student with id "+ studentId + "does not exist in db"));

        setStudentName(student, name);
        setStudentEmail(student, email);
    }

    private void setStudentName(Student student, String name){
        if(name != null && name.length()>0 && !Objects.equals(student.getName(), name)){
            student.setName(name);
        }
    }

    private void setStudentEmail(Student student, String email){
        if(email != null && email.length()!=0 && !Objects.equals(email, student.getEmail())){

            checkIfEmailAlreadyExistInDB(email);
            student.setEmail(email);
        }
    }

    private void checkIfEmailAlreadyExistInDB(String email){
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
        // todo create validator for email
        if (studentOptional.isPresent()){
            // todo create custom exception
            throw new IllegalStateException("email is taken");
        }
    }
}
