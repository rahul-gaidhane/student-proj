package in.example.course;

import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import in.example.student.Student;

@Entity
@Table(name = Course.TABLE_NAME)
public class Course {
	
	public static final String TABLE_NAME = "course";
	
	public static final class COLUMNS {
		public static final String NAME = "name";
	}
	
	@Id
	private UUID id;
	
	@Column(name = COLUMNS.NAME, nullable = false)
	private String name;
	
	@ManyToMany(mappedBy = "courses")
	private List<Student> students;
}
