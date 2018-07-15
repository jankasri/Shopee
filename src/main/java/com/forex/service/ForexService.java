package com.forex.service;

import com.forex.entity.ForexEntity;

public interface ForexService extends GenericService<ForexEntity, Long> {
	/**
	 ** Jankasri Silalahi
	 **/

	ForexEntity findByFromAndTo(String from, String to);

	void deleteByFromAndTo(String from, String to);
}
