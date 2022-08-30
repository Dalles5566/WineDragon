package com.dallas.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dallas.dao.WineDAO;
import com.dallas.vo.Entity.WineEntity;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
public class WineDAOImpl implements WineDAO  {

	//  It has been created through HibernateConfig
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<WineEntity> selectWineList() {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		//	Setup criteria means that, it's going to only look for WineEntity stuffs which from t_winedetail
		//																				@Table(name = "winedetail")
		CriteriaQuery<WineEntity> query = criteriaBuilder.createQuery(WineEntity.class);
		Root<WineEntity> root = query.from(WineEntity.class);
		return session.createQuery(query).getResultList();
	}

	//HQL
	@Override
	public WineEntity selectWineById(String id) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM WineEntity w WHERE w.id = '" + id + "'";
		Query query = session.createQuery(hql,WineEntity.class);
		//	Using query.uniqueResult() can prevent IndexOutOfBoundsException, that means if you do not an result from database
		WineEntity savedWineEntity = (WineEntity) query.uniqueResult();
		//It must be cleared, otherwise, it will cause a NonUniqueObjectException while running updateWine
		//A different object with the same identifier value was already associated with the session
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
