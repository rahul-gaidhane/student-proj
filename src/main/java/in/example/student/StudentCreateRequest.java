package in.example.student;

import java.util.List;

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
public class StudentCreateRequest {
	
	private String name;
	
	private String gender;
	
	private List<StudentSelectedCourses> courses;
}
