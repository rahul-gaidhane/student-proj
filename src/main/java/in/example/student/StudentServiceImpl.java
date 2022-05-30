package in.example.student;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.example.course.Course;
import in.example.course.CourseRepository;

@Service
public class StudentServiceImpl implements StudentService {

	private static final Logger LOGGER = LoggerFactory.getLogger(StudentServiceImpl.class);
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Override
	public StudentCreateResponse create(StudentCreateRequest request) {
		LOGGER.debug("Service to create a student : {}", request);
		
		Student stu = new Student();
		stu.setId(UUID.randomUUID());
		stu.setName(request.getName());
		stu.setGender(request.getGender());
		
		List<Course> courses = new ArrayList<>();
		
		request.getCourses().forEach(cur -> {
			Optional<Course> foundCourse = courseRepository.findById(cur.getId());
			
			if(foundCourse.isPresent()) {
				courses.add(foundCourse.get());
			}
		});
		
		stu.setCourses(courses);
		
		studentRepository.save(stu);
		
		return new StudentCreateResponse();
	}

}
