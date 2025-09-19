package com.SpringBootLearn.demo;

import com.SpringBootLearn.demo.dao.StudentDAO;
import com.SpringBootLearn.demo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@SpringBootApplication(
		scanBasePackages = {"com.SpringBootLearn.demo"}
)
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO){
		return  runner ->{
			//createStudent(studentDAO);
			//createMultipleStudent(studentDAO);
			//readStudent(studentDAO);
			//readAllStudent(studentDAO);
			readStudentByLastName(studentDAO);
		};
	}

	private void readStudentByLastName(StudentDAO studentDAO) {
		List<Student> theStudent = studentDAO.findByLastName("Dubey");
		for(Student tempStudent : theStudent){
			System.out.println(tempStudent);
		}
	}

	private void readAllStudent(StudentDAO studentDAO) {
		List<Student> theStudent = studentDAO.findAll();
		System.out.println(theStudent.get(0));
		for(Student tempStudent : theStudent){
			System.out.println(tempStudent);
		}
	}

	private void readStudent(StudentDAO studentDAO) {
		//create a student object
		Student tempStudent1 = new Student("Raghvendra","Dubey","raghavd.dubey22@gmail.com");
		studentDAO.save(tempStudent1);
		System.out.println("id: "+tempStudent1.getId());
		Student s1 = studentDAO.findById(tempStudent1.getId());
		System.out.println("Details of student having ID "+tempStudent1.getId()+" is "+s1.toString());
	}

	private void createMultipleStudent(StudentDAO studentDAO) {
		Student tempStudent1 = new Student("John","Doe","jd@gmail.com");
		Student tempStudent2 = new Student("Ab","Deviliers","abd@gmail.com");
		Student tempStudent3 = new Student("King","Kohli","king@gmail.com");
		studentDAO.save(tempStudent1);
		studentDAO.save(tempStudent2);
		studentDAO.save(tempStudent3);
	}

	private void createStudent(StudentDAO studentDAO){
		//create the student object
		Student tempStudent = new Student("Raghav","Dubey","rd@gmail.com");

		// save the student object
		studentDAO.save(tempStudent);

		// display id of created student
		System.out.println("Id of new student is "+tempStudent.getId());
	}
}
