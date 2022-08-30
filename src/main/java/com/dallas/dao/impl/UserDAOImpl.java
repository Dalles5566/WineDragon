package com.dallas.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dallas.dao.UserDAO;
import com.dallas.vo.User;


@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	SessionFactory sessionFactory;

	//HQL Query
	@Override
	@Transactional
	public String getPasswordByUserName(String username) {
		Session session = sessionFactory.getCurrentSession();
		
		String hql = "FROM User u WHERE u.username = '" + username + "'";

		//	Using query.uniqueResult() can prevent IndexOutOfBoundsException, that means if you do not an result from database
		User savedUser = (User)session.createQuery(hql,User.class).uniqueResult();
		return savedUser.getPassword();
	}

}
