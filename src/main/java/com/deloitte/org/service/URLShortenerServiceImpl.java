package com.deloitte.org.service;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Optional;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deloitte.org.entity.UrlEntity;
import com.deloitte.org.exception.AlreadyExistsException;
import com.deloitte.org.exception.InvalidUrlException;
import com.deloitte.org.repo.URLRepo;

@Service
public class URLShortenerServiceImpl implements URLShortenerService {
	@Autowired
	URLRepo urlRepo;

	@Override
	public Integer createUrl(String url) {
		int urlKey = 0;
		try {
			if (null != new URL(url).toURI()) {
				urlKey = url.hashCode();
				UrlEntity urlEntity = new UrlEntity();
				urlEntity.setUkey(urlKey);
				urlEntity.setUrl(url);
				try {
					urlRepo.saveAndFlush(urlEntity);
				}catch (Exception exception) {
					throw new AlreadyExistsException("Url already exists");
				}
			} else {
				throw new InvalidUrlException();
			}
		} catch (URISyntaxException exception) {
			throw new InvalidUrlException("Url not valid");
		} catch (MalformedURLException exception) {
			throw new InvalidUrlException("Url not valid");
		}
		
		return urlKey;
	}

	@Override
	public String getUrl(Integer id) {
		// TODO Auto-generated method stub
		Optional<UrlEntity> urlEntity = urlRepo.findByUkey(id);
		if (urlEntity.isPresent()) {
			return urlEntity.get().getUrl();
		} else
			return "No url found with the given id";
	}

}
