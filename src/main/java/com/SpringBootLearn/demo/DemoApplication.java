package com.SpringBootLearn.demo;

import com.SpringBootLearn.demo.dao.StudentDAO;
import com.SpringBootLearn.demo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;

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
			createStudent(studentDAO);
		};
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
