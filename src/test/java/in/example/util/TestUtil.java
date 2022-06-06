package in.example.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import in.example.student.StudentCreateRequest;

public class TestUtil {

	public static byte[] writeObjectAsByte(StudentCreateRequest request) throws JsonProcessingException {
		
		ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
     
        JavaTimeModule module = new JavaTimeModule();
        mapper.registerModule(module);
        return mapper.writeValueAsBytes(request);
	}
	
	
}
