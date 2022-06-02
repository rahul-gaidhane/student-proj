package in.example.course;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.example.book.BookInfo;
import in.example.config.UtilityService;

@Service
public class CourseServiceImpl implements CourseService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CourseServiceImpl.class);
	
	private UtilityService utilityService;
	
	@Autowired
	public CourseServiceImpl(UtilityService utilityService) {
		this.utilityService = utilityService;
	}

	@Override
	public List<CourseInfo> findAll() {
		LOGGER.debug("Service to find all courses...");
		
		List<CourseInfo> courses = utilityService.findCourses();
		
		return courses;
	}

	@Override
	public List<BookInfo> findBooksByCourse(UUID courseId) {
		LOGGER.debug("Service to find books by course id : {}", courseId);
		
		List<BookInfo> books = utilityService.findBooksByCourseId(courseId);
		
		return books;
	}
}
