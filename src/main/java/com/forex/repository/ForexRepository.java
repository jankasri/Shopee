package com.forex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.forex.entity.ForexEntity;

@Repository
public interface ForexRepository extends JpaRepository<ForexEntity, Long> {
	/**
	 ** Jankasri Silalahi
	 **/

	ForexEntity findByFromAndTo(String from, String to);
}
