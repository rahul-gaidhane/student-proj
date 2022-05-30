package in.example.student;

import java.util.List;
import java.util.UUID;

import in.example.course.CourseInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StudentInfo {
	
	private String name;
	
	private String gender;
	
	private UUID id;
	
	private List<CourseInfo> courses;
}
