package in.example.config;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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
		
		if(CollectionUtils.isEmpty(courses)) {
			courses = courseRepository.findAll();
		}
		
		return courses;
	}
}
