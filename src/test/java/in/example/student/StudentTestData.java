package in.example.student;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

import com.github.javafaker.Faker;

import in.example.course.CourseTestData;

public class StudentTestData {

	public static StudentCreateRequest getStudentCreateRequest() {
		Faker faker = new Faker();
		StudentCreateRequest request = new StudentCreateRequest();
		request.setGender("male");
		request.setName(faker.name().fullName());
		
		List<StudentSelectedCourses> courses = CourseTestData.getCourses(1);
		request.setCourses(courses);
		
		return request;
	}

	public static StudentFilterRequest getStudentFilterRequest(String courseName) {
		
		StudentFilterRequest request = new StudentFilterRequest();
		
		request.setPage(0);
		request.setSize(10);
		request.setCourseName(courseName);
		
		return request;
	}

	public static List<Student> getStudents(int count) {
		Faker faker = new Faker();
		
		List<Student> students = new ArrayList<>();
		
		IntStream.range(0, count).boxed().forEach(i -> {
			Student stu = new Student();
			stu.setCreatedDate(LocalDateTime.now());
			stu.setGender("male");
			stu.setId(UUID.randomUUID());
			stu.setName(faker.name().fullName());
			stu.setVersion(0);
			
			students.add(stu);
		});
		
		return students;
	}

}
