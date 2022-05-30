package in.example;

import java.util.ArrayList;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import in.example.course.Course;
import in.example.course.CourseRepository;

@Component
public class DataLoader implements CommandLineRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(DataLoader.class);
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		LOGGER.debug("Service to load data...");
		
		Course course1 = new Course();
		course1.setId(UUID.randomUUID());
		course1.setName("Aero Enginer");
		course1.setStudents(new ArrayList<>());
		
		courseRepository.save(course1);
		
		Course course2 = new Course();
		course2.setId(UUID.randomUUID());
		course2.setName("Data Enginer");
		course2.setStudents(new ArrayList<>());
		
		courseRepository.save(course2);

	}

}
