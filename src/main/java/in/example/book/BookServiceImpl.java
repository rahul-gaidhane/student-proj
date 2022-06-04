package in.example.book;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.example.author.AuthorInfo;
import in.example.config.UtilityService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BookServiceImpl implements BookService {

	private UtilityService  utilityService;
	
	@Autowired
	public BookServiceImpl(UtilityService utilityService) {
		this.utilityService = utilityService;
	}
	
	@Override
	public List<AuthorInfo> findAuthorsByBook(UUID bookId) {
		log.debug("Service to find authors by book id : {}", bookId);
		
		List<AuthorInfo> authorInfos = utilityService.findAuthorsByBook(bookId);
		
		return authorInfos;
	}

}
