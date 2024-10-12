package vbl.dao.impl;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import vbl.configs.JPAConfig;
import vbl.dao.IVideoDao;
import vbl.entity.Video;

public class VideoDao implements IVideoDao {
	@Override
	public void insert(Video Video) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.persist(Video);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}
	}
	
	@Override
	public void update(Video Video) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.merge(Video);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}
	}
	@Override
	public void delete(int cateid) throws Exception {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			Video Video = enma.find(Video.class, cateid);
			if(Video != null) {
				enma.remove(Video);
			} else {
				throw new Exception("Không tìm thấy");
			}
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}
	}
	
	@Override
	public Video findById(int cateid) {
	    EntityManager enma = JPAConfig.getEntityManager();
	    Video Video = enma.find(Video.class, cateid);
	    return Video;
	}

	
	@Override
	public List<Video> findAll(){
		EntityManager enma = JPAConfig.getEntityManager();
		TypedQuery<Video> query = enma.createNamedQuery("Video.findAll", Video.class);
		return query.getResultList();
	}
	
	@Override
	public List<Video> findByVideoname(String catname) {
		 EntityManager enma = JPAConfig.getEntityManager();
		 String jpql = "SELECT c FROM Video c WHERE c.catename like :catname";
		 TypedQuery<Video> query= enma.createQuery(jpql, Video.class);
		 query.setParameter("catename", "%" + catname + "%");
		 return query.getResultList();
	}
	
	@Override
	public List<Video> findAll(int page, int pagesize) {
		EntityManager enma = JPAConfig.getEntityManager();
		TypedQuery<Video> query= enma.createNamedQuery("Video.findAll", Video.class);
		query.setFirstResult(page*pagesize);
		query.setMaxResults(pagesize); 
		return query.getResultList(); 
	}
	
	@Override
	public int count() {
		EntityManager enma = JPAConfig.getEntityManager();
		String jpql = "SELECT count(c) FROM Video c";
		Query query = enma.createQuery(jpql);
		return ((Long)query.getSingleResult()).intValue();
	}
}
