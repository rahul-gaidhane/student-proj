package in.example.student;

import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import in.example.course.Course;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Entity
@Table(name = Student.TABLE_NAME)
public class Student {
	
	public static final String TABLE_NAME = "student";
	
	public static final class COLUMNS {
		public static final String NAME = "name";
		public static final String GENDER = "gender";
	}
	
	@Id
	private UUID id;
	
	@Column(name = COLUMNS.NAME, nullable = false)
	private String name;
	
	@Column(name = COLUMNS.GENDER, nullable = false)
	private String gender;
	
	@ManyToMany
	@JoinTable(name = "student_courses")
	private List<Course> courses;
}
