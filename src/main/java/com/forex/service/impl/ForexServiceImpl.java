package com.forex.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.forex.entity.ForexEntity;
import com.forex.repository.ForexRepository;
import com.forex.service.ForexService;

@Service
public class ForexServiceImpl implements ForexService {
	/**
	 ** Jankasri Silalahi
	 **/
	@Autowired
	private ForexRepository repo;

	@Override
	public void create(ForexEntity entity) throws Exception {
		try {
			repo.save(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(ForexEntity entity) throws Exception {
		try {
			repo.saveAndFlush(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(ForexEntity entity) throws Exception {
		try {
			repo.delete(entity);
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
	public void deleteByFromAndTo(String from, String to) {
		try {
			ForexEntity entity = findByFromAndTo(from, to);

			repo.delete(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public ForexEntity findByPK(Long key) throws Exception {
		try {
			return repo.findById(key).get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ForexEntity findByFromAndTo(String from, String to) {
		try {
			return repo.findByFromAndTo(from, to);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<ForexEntity> findAll() throws Exception {
		try {
			return repo.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
