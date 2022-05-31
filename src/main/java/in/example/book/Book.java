package in.example.book;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import in.example.author.Author;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = Book.TABLE_NAME)
public class Book {

	public static final String TABLE_NAME = "book";
	
	public static final class COLUMNS {
		public static final String NAME = "name";
		public static final String DATE_OF_PUBLISH = "date_of_publish";
		public static final String PUBLICATION_HOUSE = "publication_house";
		public static final String COURSE_ID = "course_id";
	}
	
	@Id
	private UUID id;
	
	@Column(name = COLUMNS.NAME, nullable = false)
	private String name;
	
	@Column(name = COLUMNS.DATE_OF_PUBLISH, nullable = false)
	private LocalDate dateOfPublish;
	
	@Column(name = COLUMNS.PUBLICATION_HOUSE, nullable = false)
	private String publicationHouse;
	
	private UUID courseId;
	
	@ManyToMany
	@JoinTable(name = "book_author")
	private List<Author> authors;
}
