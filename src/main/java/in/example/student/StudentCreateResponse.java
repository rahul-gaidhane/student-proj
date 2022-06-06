package in.example.student;

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
public class StudentCreateResponse {
	
	public static final String MESSAGE = "Student created successfully";
	
	private UUID id;
	
	private String message;
}
