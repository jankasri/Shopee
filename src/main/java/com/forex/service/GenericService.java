package com.forex.service;

import java.util.List;

public interface GenericService<E, K> {
	/**
	 ** Jankasri Silalahi
	 **/

	public void create(E entity) throws Exception;

	public void update(E entity) throws Exception;

	public void delete(E entity) throws Exception;

	public void deleteByPK(K id) throws Exception;

	public E findByPK(K key) throws Exception;

	public List<E> findAll() throws Exception;

}
