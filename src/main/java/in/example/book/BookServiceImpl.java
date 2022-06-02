package in.example.book;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.example.author.AuthorInfo;
import in.example.config.UtilityService;

@Service
public class BookServiceImpl implements BookService {

	private static final Logger LOGGER = LoggerFactory.getLogger(BookServiceImpl.class);
	
	private UtilityService  utilityService;
	
	@Autowired
	public BookServiceImpl(UtilityService utilityService) {
		this.utilityService = utilityService;
	}
	
	@Override
	public List<AuthorInfo> findAuthorsByBook(UUID bookId) {
		LOGGER.debug("Service to find authors by book id : {}", bookId);
		
		List<AuthorInfo> authorInfos = utilityService.findAuthorsByBook(bookId);
		
		return authorInfos;
	}

}
