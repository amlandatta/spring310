package com.vmware.training.spring310.studentservice;

import com.vmware.training.spring310.studentservice.client.CourseClient;
import com.vmware.training.spring310.studentservice.entity.Course;
import com.vmware.training.spring310.studentservice.entity.Student;
import com.vmware.training.spring310.studentservice.entity.StudentCourses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final Logger log = LoggerFactory.getLogger(StudentController.class);
    private Map<Integer, Student> studentRepository = new HashMap<>();
    private Map<Integer, StudentCourses> studentCourseRepository = new HashMap<>();

    private final CourseClient courseClient;

    @Value( "${course-service.baseuri}" )
    private String COURSE_BASE_URI;

    public StudentController(CourseClient courseClient) {
        super();
        this.courseClient = courseClient;
        initialize();
    }

    private void initialize() {
        Course course1 = Course.builder().id(1).build();
        Course course2 = Course.builder().id(2).build();
        Course course3 = Course.builder().id(3).build();

        Student student1 = Student.builder().studentId(1).name("David Joe")
                .email("davidjoe@test.com").build();
        Student student2 = Student.builder().studentId(2).name("Mohit Kumar")
                .email("mohitkumar@test.com").build();
        Student student3 = Student.builder().studentId(3).name("Mary Jane")
                .email("maryjane@test.com").build();
        studentRepository.put(1, student1);
        studentRepository.put(2, student2);
        studentRepository.put(3, student3);

        StudentCourses student1Courses = StudentCourses.builder().studentId(1).courses(
                Arrays.asList(course1)).build();
        StudentCourses student2Courses = StudentCourses.builder().studentId(1).build();
        StudentCourses student3Courses = StudentCourses.builder().studentId(3).courses(
                Arrays.asList(course2,course3)).build();
        studentCourseRepository.put(1, student1Courses);
        studentCourseRepository.put(2, student2Courses);
        studentCourseRepository.put(3, student3Courses);
    }

    @GetMapping
    public Collection<Student> getStudents(){
        return studentRepository.values();
    }

    @PostMapping
    public ResponseEntity<String> createStudent(@RequestBody Student student) {

        if(student != null && (student.getName() == null || student.getName().trim().length() == 0))
            return ResponseEntity.badRequest().body("Student name cannot be empty");

        student.setStudentId(studentRepository.size() + 1);
        studentRepository.put(student.getStudentId(), student);

        return ResponseEntity
                .ok(student)
                .status(201)
                .build();

    }

    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable("id") Integer id,
                                 @RequestBody Student updatedStudent) {

        Student student = studentRepository.get(id);

        student.setName(updatedStudent.getName());
        student.setEmail(updatedStudent.getEmail());

        studentRepository.put(student.getStudentId(), student);
        student = studentRepository.get(id);

        return student;

    }


    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable("id") Integer id) {
        Student student = studentRepository.get(id);

        if(student != null)
            return ResponseEntity.ok(student);
        else
            return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") Integer id) {

        if(studentRepository.containsKey(id)) {
            studentRepository.remove(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/courses")
    public ResponseEntity<StudentCourses> getStudentCourses(@PathVariable("id") Integer id) {
        StudentCourses studentCourses = studentCourseRepository.get(id);
        List<Course> courses = studentCourses.getCourses();

        if (null!=courses) {
            courses.forEach(c-> {
                Course course = this.courseClient.getCourse(c.getId());
                c.setDuration(course.getDuration());
                c.setName(course.getName());
            });
        }
        if(studentCourses != null)
            return ResponseEntity.ok(studentCourses);
        else
            return ResponseEntity.notFound().build();
    }


}
