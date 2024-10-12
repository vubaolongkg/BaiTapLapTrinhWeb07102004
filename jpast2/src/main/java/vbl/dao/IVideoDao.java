package vbl.dao;

import java.util.List;

import vbl.entity.Video;

public interface IVideoDao {

	int count();

	List<Video> findAll(int page, int pagesize);

	List<Video> findByVideoname(String catname);

	List<Video> findAll();

	Video findById(int cateid);

	void delete(int cateid) throws Exception;

	void update(Video Video);

	void insert(Video Video);

}
