package in.example.student;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import in.example.course.Course;
import in.example.course.CourseRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class StudentServiceImpl implements StudentService {

	private StudentRepository studentRepository;
	
	private CourseRepository courseRepository;
	
	@Autowired
	public StudentServiceImpl(StudentRepository studentRepository, CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
		this.studentRepository = studentRepository;
	}
	
	@Override
	public StudentCreateResponse create(StudentCreateRequest request) {
		log.debug("Service to create a student : {}", request);
		
		Student stu = new Student();
		stu.setId(UUID.randomUUID());
		stu.setName(request.getName());
		stu.setGender(request.getGender());
		
		List<Course> courses = new ArrayList<>();
		
		request.getCourses().forEach(cur -> {
			Optional<Course> foundCourse = courseRepository.findById(cur.getId());
			
			if(foundCourse.isPresent()) {
				courses.add(foundCourse.get());
			} else {
				throw new EntityNotFoundException("Course not found for the given course id : " + cur.getId());
			}
		});
		
		stu.setCourses(courses);
		
		studentRepository.save(stu);
		
		return new StudentCreateResponse(stu.getId(), StudentCreateResponse.MESSAGE);
	}

	@Override
	public Page<StudentInfo> findByCourseName(StudentFilterRequest request) {
		log.debug("Service to find students by course name : {}", request);
		
		PageRequest pageable = PageRequest.of(request.getPage(), request.getSize());
		
		Page<Student> students = studentRepository.findByCoursesName(request.getCourseName(), pageable);
		
		StudentMapper stuMapper = Mappers.getMapper(StudentMapper.class);
		
		List<StudentInfo> studentsInfo = students.stream().map(stuMapper::toStudentInfo).collect(Collectors.toList());
		
		return new PageImpl<>(studentsInfo, students.getPageable(), students.getTotalElements());
	}

}
