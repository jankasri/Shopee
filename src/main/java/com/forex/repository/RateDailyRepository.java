package com.forex.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.forex.entity.ForexEntity;
import com.forex.entity.RateDailyEntity;

@Repository
public interface RateDailyRepository extends JpaRepository<RateDailyEntity, Long> {
	/**
	 ** Jankasri Silalahi
	 **/

	List<RateDailyEntity> findByForex(ForexEntity forex);

	List<RateDailyEntity> findByDateBetweenAndForex(Date startDate, Date endDate, ForexEntity forex);
}
