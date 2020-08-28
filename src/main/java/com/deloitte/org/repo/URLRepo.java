package com.deloitte.org.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.org.entity.UrlEntity;
@Repository
public interface URLRepo extends JpaRepository<UrlEntity,Integer>{

	Optional<UrlEntity> findByUkey(Integer id);

	UrlEntity findByUrl(String url);

}
