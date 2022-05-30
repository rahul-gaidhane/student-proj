package in.example.course;

public class CourseMapper {
	
	public static CourseInfo toCourseInfo(Course course) {
		CourseInfo courseInfo = new CourseInfo();
		
		courseInfo.setId(course.getId());
		courseInfo.setName(course.getName());
		
		return courseInfo;
	}
}
