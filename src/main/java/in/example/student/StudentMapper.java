package in.example.student;

import org.mapstruct.Mapper;

@Mapper
public interface StudentMapper {
	
	public StudentInfo toStudentInfo(Student stu);
}
