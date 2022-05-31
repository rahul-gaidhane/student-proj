package in.example.author;

import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import in.example.book.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = Author.TABLE_NAME)
public class Author {
	public static final String TABLE_NAME = "author";
	
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
	
	@ManyToMany(mappedBy = "authors")
	private List<Book> books;
}
