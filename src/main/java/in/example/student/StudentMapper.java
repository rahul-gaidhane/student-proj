package in.example.student;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.util.CollectionUtils;

import in.example.course.CourseInfo;
import in.example.course.CourseMapper;

public class StudentMapper {
	
	public static StudentInfo toStudentInfo(Student stu) {
		StudentInfo studentInfo = new StudentInfo();

		studentInfo.setGender(stu.getGender());
		studentInfo.setId(stu.getId());
		studentInfo.setName(stu.getName());
		
		if(CollectionUtils.isEmpty(stu.getCourses())) {
			studentInfo.setCourses(new ArrayList<>());
		} else {
			List<CourseInfo> courseInfos = stu.getCourses().stream().map(CourseMapper::toCourseInfo).collect(Collectors.toList());
			
			studentInfo.setCourses(courseInfos);
		}
		
		return studentInfo;
	}
}
