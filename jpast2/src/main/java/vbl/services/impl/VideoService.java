package vbl.services.impl;

import java.util.List;

import vbl.dao.IVideoDao;
import vbl.dao.impl.VideoDao;
import vbl.entity.Video;
import vbl.services.IVideoService;

public class VideoService implements IVideoService {
	IVideoDao cateDao = new VideoDao();
	@Override
	public int count() {
		return cateDao.count();
	}

	@Override
	public List<Video> findAll(int page, int pagesize) {
		return cateDao.findAll(page, pagesize);
	}

	@Override
	public List<Video> findByVideoname(String catname) {
		return cateDao.findByVideoname(catname);
	}

	@Override
	public List<Video> findAll() {
		return cateDao.findAll();
	}

	@Override
	public Video findById(int cateid) {
		return cateDao.findById(cateid);
	}

	@Override
	public void delete(int cateid) throws Exception {
		cateDao.delete(cateid);
	}

	@Override
	public void update(Video Video) {
		cateDao.update(Video);
	}

	@Override
	public void insert(Video Video) {
		cateDao.insert(Video);
	}

}
