package com.forex.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.forex.entity.ForexEntity;
import com.forex.entity.RateDailyEntity;
import com.forex.repository.RateDailyRepository;
import com.forex.service.RateDailyService;

@Service
public class RateDailyServiceImpl implements RateDailyService {
	/**
	 ** Jankasri Silalahi
	 **/

	@Autowired
	private RateDailyRepository repo;

	@Override
	public void create(RateDailyEntity entity) throws Exception {
		try {
			repo.save(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(RateDailyEntity entity) throws Exception {
		try {
			repo.saveAndFlush(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(RateDailyEntity entity) throws Exception {
		try {
			repo.delete(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteByRateList(List<RateDailyEntity> rateList) {
		try {
			repo.deleteAll(rateList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteByPK(Long id) throws Exception {
		try {
			repo.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public RateDailyEntity findByPK(Long key) throws Exception {
		try {
			return repo.findById(key).get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<RateDailyEntity> findByForex(ForexEntity forex) {
		try {
			return repo.findByForex(forex);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<RateDailyEntity> findByDateBetweenAndForex(Date startDate, Date endDate, ForexEntity forex) {
		try {
			return repo.findByDateBetweenAndForex(startDate, endDate, forex);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<RateDailyEntity> findAll() throws Exception {
		try {
			return repo.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
