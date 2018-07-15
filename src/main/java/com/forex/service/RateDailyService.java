package com.forex.service;

import java.util.Date;
import java.util.List;

import com.forex.entity.ForexEntity;
import com.forex.entity.RateDailyEntity;

public interface RateDailyService extends GenericService<RateDailyEntity, Long> {
	/**
	 ** Jankasri Silalahi
	 **/

	List<RateDailyEntity> findByForex(ForexEntity forex);

	List<RateDailyEntity> findByDateBetweenAndForex(Date startDate, Date endDate, ForexEntity forex);

	void deleteByRateList(List<RateDailyEntity> rateList);
}
