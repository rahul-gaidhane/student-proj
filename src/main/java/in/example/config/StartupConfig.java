package in.example.config;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import in.example.course.Course;
import in.example.course.CourseRepository;

@Configuration
public class StartupConfig {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(StartupConfig.class);
	
	@Autowired
	private CourseRepository courseRepository;

	@Bean
	public List<Course> courses() {
		
		LOGGER.debug("Service to create all courses bean...");
		
		List<Course> courses = courseRepository.findAll();
		
		LOGGER.debug("Number of courses : {}", courses.size());
		
		return courses;
	}
}
