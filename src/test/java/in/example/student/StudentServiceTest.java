package in.example.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import in.example.course.Course;
import in.example.course.CourseRepository;

@ExtendWith(SpringExtension.class)
public class StudentServiceTest {
	
	private StudentService studentService;
	
	private CourseRepository courseRepository;
	
	private StudentRepository studentRepository;
	
	@BeforeEach
	public void setup() {
		
		courseRepository = mock(CourseRepository.class);
		
		studentRepository = mock(StudentRepository.class);
		
		studentService = new StudentServiceImpl(studentRepository, courseRepository);
	}
	
	@Test
	public void testCreateStudent() {
		
		StudentCreateRequest request = StudentTestData.getStudentCreateRequest();
		
		Course course = new Course();
		course.setId(request.getCourses().get(0).getId());
		course.setName(request.getCourses().get(0).getName());
		
		when(courseRepository.findById(Mockito.any())).thenReturn(Optional.of(course));
		
		StudentCreateResponse response = studentService.create(request);
		
		assertNotNull(response);
		assertNotNull(response.getId());
		assertNotNull(response.getMessage());
		assertEquals(StudentCreateResponse.MESSAGE, response.getMessage());
	}
	
	@Test
	public void testCreateStudentWhenExceptionIsThrown() {
		
		StudentCreateRequest request = StudentTestData.getStudentCreateRequest();
		
		when(courseRepository.findById(Mockito.any())).thenReturn(Optional.empty());
		
		assertThrows(EntityNotFoundException.class, () -> studentService.create(request));
	}
	
	@Test
	public void testFindStudentByCourseName() {
		
		StudentFilterRequest request = StudentTestData.getStudentFilterRequest("courseName");
		
		List<Student> students = StudentTestData.getStudents(2);
		
		Page<Student> responsePage = new PageImpl<>(students);
		
		when(studentRepository.findByCoursesName(Mockito.anyString(), Mockito.any())).thenReturn(responsePage);
		
		Page<StudentInfo> page = studentService.findByCourseName(request);
		
		assertNotNull(page);
		assertEquals(responsePage.getContent().size(), page.getContent().size());
	}
}
