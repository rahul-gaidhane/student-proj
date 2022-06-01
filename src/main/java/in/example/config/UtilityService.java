package in.example.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import in.example.book.Book;
import in.example.course.Course;
import in.example.course.CourseRepository;

@Service
public class UtilityService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UtilityService.class);
	
	@Autowired
	List<Course> courses;
	
	@Autowired
	private CourseRepository courseRepository;
	
	public List<Course> findCourses() {
		
		LOGGER.debug("Service to load all the courses...");
		
		loadCourses();
		
		return courses;
	}

	private void loadCourses() {
		
		LOGGER.debug("Service to load courses : {}", courses);
		
		if(CollectionUtils.isEmpty(courses)) {
			courses = courseRepository.findAll();
		}
	}

	public List<Book> findBooksByCourseId(UUID courseId) {
		
		LOGGER.debug("Service to find books by course id : {}", courseId);
		
		loadCourses();
		
		Optional<Course> course = courses.stream().filter(cur -> cur.getId().equals(courseId)).findFirst();
		
		List<Book> books = new ArrayList<>();
		
		if(course.isPresent()) {
			books = course.get().getBooks();
		}
		
		return books;
	}
}
