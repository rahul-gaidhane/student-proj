package in.example.student;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;

import in.example.security.JwtUtil;
import in.example.security.MyUserDetailsService;
import in.example.util.TestUtil;


@WebMvcTest(controllers = StudentController.class)
@WithMockUser
public class StudentControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private StudentService studentService;
	
	@MockBean
	private MyUserDetailsService myUserDetailsService;
	
	@MockBean
	private JwtUtil jwtUtil;
	
	@Test
	public void testStudentCreation() throws JsonProcessingException, Exception {
		
		StudentCreateRequest request = StudentTestData.getStudentCreateRequest();
		
		when(studentService.create(Mockito.any())).thenReturn(new StudentCreateResponse(UUID.randomUUID(), StudentCreateResponse.MESSAGE));
		
		mockMvc.perform(post("/api/students")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(TestUtil.writeObjectAsByte(request)))
			.andDo(print())
			.andExpect(status().isCreated());
	}
	
	@Test
	public void testFindStudentByCourseName() throws Exception {
		
		StudentFilterRequest request = StudentTestData.getStudentFilterRequest("cour");
		
		Page<StudentInfo> students = StudentTestData.getStudentInfoPage(2);
		
		when(studentService.findByCourseName(Mockito.any())).thenReturn(students);
		
		mockMvc.perform(get("/api/students/find")
				.param("size", String.valueOf(request.getSize()))
				.param("page", String.valueOf(request.getPage()))
				.param("courseName", String.valueOf(request.getCourseName())))
			.andDo(print())
			.andExpect(status().isOk());
	}
}
