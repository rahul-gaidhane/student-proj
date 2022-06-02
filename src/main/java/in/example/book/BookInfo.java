package in.example.book;

import java.time.LocalDate;
import java.util.UUID;

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
public class BookInfo {
	
	private UUID id;
	
	private String name;
	
	private String publicationHouse;
	
	private LocalDate dateOfPublish;
	
	private UUID courseId;
}
