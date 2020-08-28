package com.deloitte.org.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.deloitte.org.entity.UrlEntity;
import com.deloitte.org.repo.URLRepo;
import com.deloitte.org.service.URLShortenerServiceImpl;

public class UrlShortenerTest {
	@Mock
	URLRepo urlRepo;
	@InjectMocks
	URLShortenerServiceImpl urlShortenerServiceImpl;

	@Test
	public void createUrlTest() {
		UrlEntity urlEntity = new UrlEntity();
		urlEntity.setUrl("https://www.google.co.in/webhp?source=search_app");
		urlEntity.setUkey(-110765290);
		when(urlRepo.save(any(UrlEntity.class))).thenReturn(urlEntity);
		assertEquals(-110765290, urlShortenerServiceImpl.createUrl("https://www.google.co.in/webhp?source=search_app"));
	}
	@Test
	public void getUrl() {
		Optional<UrlEntity> opurlEntity =Optional.ofNullable(new UrlEntity());
		opurlEntity.get().setUrl("https://www.google.co.in/webhp?source=search_app");
		opurlEntity.get().setUkey(-110765290);
		when( urlRepo.findByUkey(-110765290)).thenReturn(opurlEntity);
		assertEquals("https://www.google.co.in/webhp?source=search_app", urlShortenerServiceImpl.getUrl(-110765290));

	}

}
