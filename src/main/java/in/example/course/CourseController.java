package in.example.course;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.example.book.BookInfo;

@RestController
@RequestMapping("/api/courses")
public class CourseController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CourseController.class);
	
	@Autowired
	private CourseService courseService;
	
	@GetMapping
	public ResponseEntity<List<CourseInfo>> findAll() {
		LOGGER.debug("Request to find all courses...");
		
		List<CourseInfo> courses = courseService.findAll();
		
		return new ResponseEntity<List<CourseInfo>>(courses, HttpStatus.OK);
	}
	
	@GetMapping("/{courseId}/books")
	public ResponseEntity<List<BookInfo>> findBooksByCourse(@PathVariable("courseId") UUID courseId) {
		LOGGER.debug("Request to find all the books by course id : {}", courseId);
		
		List<BookInfo> books = courseService.findBooksByCourse(courseId);
		
		return new ResponseEntity<List<BookInfo>>(books, HttpStatus.OK);
	}
}
