package in.example.course;

import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import in.example.book.Book;
import in.example.student.Student;
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
	
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "courseId")
	private List<Book> books;
}
