package in.example.student;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/students")
public class StudentController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(StudentController.class);
	
	@Autowired
	private StudentService studentService;
	
	@PostMapping
	public ResponseEntity<StudentCreateResponse> create(@RequestBody StudentCreateRequest 
			request) {
		LOGGER.debug("request to create student : {}", request);
		
		StudentCreateResponse response = studentService.create(request);
		
		return new ResponseEntity<StudentCreateResponse>(response, HttpStatus.CREATED);
	}
	

}
