package com.dallas.WineDragonSpringBoot.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dallas.WineDragonSpringBoot.dao.WineDAO;
import com.dallas.WineDragonSpringBoot.vo.Entity.WineEntity;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
public class WineDAOImpl implements WineDAO  {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<WineEntity> selectWineList() {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<WineEntity> query = criteriaBuilder.createQuery(WineEntity.class);
		Root<WineEntity> root = query.from(WineEntity.class);
		return session.createQuery(query).getResultList();
	}

	@Override
	public WineEntity selectWineById(String id) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM WineEntity w WHERE w.id = '" + id + "'";
		Query query = session.createQuery(hql,WineEntity.class);
		WineEntity savedWineEntity = (WineEntity) query.uniqueResult();
		session.clear();
		return savedWineEntity;
	}

	@Override
	public void addWine(WineEntity wine) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(wine);
	}

	@Override
	public void updateWine(WineEntity wine) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(wine);
	}

	@Override
	public void removeWineById(String id) {
		Session session = sessionFactory.getCurrentSession();
		WineEntity saveWine = session.byId(WineEntity.class).load(id);
		if (saveWine != null){
			session.delete(saveWine);
		}
	}

}
