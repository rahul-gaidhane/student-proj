package in.example.student;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Version;

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
		public static final String CREATED_DATE = "created_date";
	}
	
	@Id
	private UUID id;
	
	@Column(name = COLUMNS.NAME, nullable = false)
	private String name;
	
	@Column(name = COLUMNS.GENDER, nullable = false)
	private String gender;
	
	@Column(name = COLUMNS.CREATED_DATE, nullable = false)
	private LocalDateTime createdDate;
	
	@ManyToMany
	@JoinTable(name = "student_course")
	private List<Course> courses;
	
	@Version
	private Integer version;
	
	@PrePersist
	@PreUpdate
	public void updateCreatedDate() {
		this.createdDate = LocalDateTime.now();
	}
}
