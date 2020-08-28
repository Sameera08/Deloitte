package com.deloitte.org.service;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Optional;
import java.util.Random;

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
				urlKey = generateUniqueId(url);
				UrlEntity urlEntity = new UrlEntity();
				urlEntity.setUrl(url);
				Optional<UrlEntity> existingkey=urlRepo.findByUkey(urlKey);
				if(existingkey.isPresent() && existingkey.get().getUrl().equals(url)) {
					Random random = new Random();
					int randomNumber = random.nextInt(existingkey.get().getUid()) + 100;
					urlKey=urlKey+randomNumber;
				}
				urlEntity.setUkey(urlKey);
				if (null == urlRepo.findByUrl(url)) {
					urlRepo.saveAndFlush(urlEntity);
				} else {
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

	private int generateUniqueId(String urlKey) {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((urlKey == null) ? 0 : urlKey.hashCode());
		return result;
	}

}
