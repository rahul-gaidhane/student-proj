package in.example.course;

import org.mapstruct.Mapper;

@Mapper
public interface CourseMapper {
	
	public CourseInfo toCourseInfo(Course course);
}
