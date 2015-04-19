package com.cn.dao;

import java.util.List;

import javax.annotation.*;

import org.hibernate.*;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.cn.entity.User;

@Repository("userDao")
public class UserDaoImpl extends HibernateDaoSupport implements UserDao {
	
	@Resource
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		this.setSessionFactory(sessionFactory);
	}

	@Override
	public void addUser(User user) {
		this.getHibernateTemplate().save(user);
	}

	@Override
	public void deleteUser(int id) {
		User user=this.loadbyid(id);
		this.getHibernateTemplate().delete(user);
	}

	@Override
	public void updateUser(User user) {
		this.getHibernateTemplate().update(user);
	}

	@Override
	public User loadbyid(int id) {
		return this.getHibernateTemplate().load(User.class, id);
	}

	@Override
	public User loadbyemail(String email) {
		return (User)this.getSession().createQuery("from User where email=?")
				.setParameter(0, email).uniqueResult();
	}

	@Override
	public User loadbyusername(String username) {
		return (User)this.getSession().createQuery("from User where username=?")
				.setParameter(0, username).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> list() {
		return this.getSession().createQuery("from User").list();
	}

}