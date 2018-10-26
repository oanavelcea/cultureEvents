package fr.dawan.cultureEvents.dao;

import java.util.List;

import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import fr.dawan.cultureEvents.beans.User;

public class UserDao {

	private HibernateTemplate hibernateTemplate;

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public List<User> findAll(){
		return (List<User>)hibernateTemplate.find("FROM User");
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public List<User> findAll(int start, int maxElts){
		return hibernateTemplate.getSessionFactory().getCurrentSession()
		.createQuery("FROM User")
		.setFirstResult(start)
		.setMaxResults(maxElts)
		.list();
	}
	
	
	@Transactional(readOnly=true)
	public User findById(long id) {
		return hibernateTemplate.get(User.class, id);
	}
	
	@Transactional
	public void save(User p) {
		hibernateTemplate.save(p);
	}
	
	@Transactional
	public void update(User p) {
		hibernateTemplate.saveOrUpdate(p);
	}
	
	@Transactional(readOnly=true)
	public long nbUsers() {
		return (Long)hibernateTemplate.find("SELECT COUNT(c.id) FROM User c").get(0);
	}
	
	@Transactional(readOnly=false)
	public void delete(long id) {
		hibernateTemplate.delete(findById(id));
	}
	
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public User findByEmail(String email){
		List<User> users = (List<User>) hibernateTemplate.find("FROM User u WHERE u.email= ?", email);
		if(users!=null && users.size()>0)
			return users.get(0);
		
		return null;
	}
	
	
	

	
	
}
