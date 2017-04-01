package com.xz.greendao_xz.greendao.service;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;

import java.util.List;


/**
 * @ClassName: BaseService
 * @Description: TODO
 * @author ghy
 * @date 2016-6-23 下午2:10:09
 * @param <T>
 *            实现类
 * @param <K>
 *            主键
 */
public class BaseService<T, K> {
	private AbstractDao<T, K> mDao;

	public BaseService(AbstractDao dao) {
		mDao = dao;
	}

	public void save(T item) {
		mDao.insert(item);
	}

	public void save(T... items) {
		mDao.insertInTx(items);
	}

	public void save(List<T> items) {
		mDao.insertInTx(items);
	}

	public void saveOrUpdate(T item) {
		mDao.insertOrReplace(item);
	}

	public void saveOrUpdate(T... items) {
		mDao.insertOrReplaceInTx(items);
	}

	public void saveOrUpdate(List<T> items) {
		mDao.insertOrReplaceInTx(items);
	}

	public void deleteByKey(K key) {
		mDao.deleteByKey(key);
	}

	public void delete(T item) {
		mDao.delete(item);
	}

	public void delete(T... items) {
		mDao.deleteInTx(items);
	}

	public void delete(List<T> items) {
		mDao.deleteInTx(items);
	}

	public void deleteAll() {
		mDao.deleteAll();
	}

	public void update(T item) {
		mDao.update(item);
	}

	public void update(T... items) {
		mDao.updateInTx(items);
	}

	public void update(List<T> items) {
		mDao.updateInTx(items);
	}

	public T query(K key) {
		return mDao.load(key);
	}

	public List<T> queryAll() {
		return mDao.loadAll();
	}

	public List<T> query(String where, String... params) {

		return mDao.queryRaw(where, params);
	}

	public List<T> queryMutlitAnd(WhereCondition arg0,
								  WhereCondition... conditions) {

		return mDao.queryBuilder().where(arg0, conditions).list();
	}

	public QueryBuilder<T> queryBuilder() {

		return mDao.queryBuilder();
	}

	public long count() {
		return mDao.count();
	}

	public void refresh(T item) {
		mDao.refresh(item);

	}

	public void detach(T item) {
		mDao.detach(item);
	}
}