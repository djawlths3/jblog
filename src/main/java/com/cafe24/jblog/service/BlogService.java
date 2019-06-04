package com.cafe24.jblog.service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Reader;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cafe24.jblog.dao.BlogDao;
import com.cafe24.jblog.vo.BlogVo;
import com.cafe24.jblog.vo.CategoryVo;
import com.cafe24.jblog.vo.PostVo;
import com.cafe24.jblog.vo.UserVo;


@Service
public class BlogService {
	@Autowired
	BlogDao blogDao;
	private static final String SAVE_PATH = "/jblogImg";
	private static final String URL = "/images";
	public boolean blogInfoUpdate(BlogVo blogVo, MultipartFile multipartFile, UserVo authUser,  HttpServletRequest request) {
		System.out.println(request.getContextPath());
		String url = restore( multipartFile );
		blogVo.setLogo(url);
		blogVo.setBlogId(authUser.getId());
		return blogDao.updateBlogInfo(blogVo);
	}
	
	
	public BlogVo getBlogInfo(String id) {		
		return blogDao.getBlogInfo(id);
	}
		
	public Long categoryAdd(CategoryVo categoryVo) {
		return blogDao.categoryAdd(categoryVo);
	}
	
	public boolean categoryDelete(Long no) {
		return blogDao.categoryDelete(no);
	}

	public List getList(UserVo userVo) {
		return blogDao.getList(userVo);
	}
	
	public Long postAdd(PostVo postVo) {
		return blogDao.postAdd(postVo);
	}

	public PostVo postSelect(String id, Optional<Long> pathNo1, Optional<Long> pathNo2) {
		PostVo postVo = new PostVo();
		postVo.setId(id);
		if(pathNo1.isPresent()) {
			postVo.setCategoryNo(pathNo1.get());
		}else {
			return blogDao.getLatelyPost(postVo);
		}
		if(pathNo2.isPresent()) {
			postVo.setNo(pathNo2.get());
		} else {
			Long postNo = blogDao.getLatelyPostNo(postVo);
			postVo.setNo(postNo);
		}
		return blogDao.getPost(postVo);
	}

	public List getPostList(String id, Optional<Long> pathNo1) {
		PostVo postVo = new PostVo();
		postVo.setId(id);
		if(pathNo1.isPresent()) {
			postVo.setCategoryNo(pathNo1.get());
		} else {
			Long categoryNo = blogDao.getLatelycategoryNo(postVo);
			postVo.setCategoryNo(categoryNo);
		}
		
		return blogDao.getPostList(postVo);
	}
	
	
	
	//파일업로드
	public String restore(MultipartFile multipartFile) {
		String url = "";

		try {
		
			if(multipartFile.isEmpty()) {
				return url;
			}
			
			String originalFilename = 
					multipartFile.getOriginalFilename();
			String extName = originalFilename.substring(originalFilename.lastIndexOf('.')+1);
			String saveFileName = generateSaveFileName(extName);
			long fileSize = multipartFile.getSize();
			
			System.out.println("##########" + originalFilename);
			System.out.println("##########" + extName);
			System.out.println("##########" + saveFileName);
			System.out.println("##########" + fileSize);
			
			byte[] fileData = multipartFile.getBytes();
		
			OutputStream os = new FileOutputStream(SAVE_PATH + "/" + saveFileName);
			os.write(fileData);
			os.close();
			
			url = URL + "/" + saveFileName;			
			
		} catch (IOException e) {
			throw new RuntimeException("Fileupload error:" + e);
		}
		
		return url;
	}
	
	private String generateSaveFileName(String extName) {
		String filename = "";
		Calendar calendar = Calendar.getInstance();
		
		filename += calendar.get(Calendar.YEAR);
		filename += calendar.get(Calendar.MONTH);
		filename += calendar.get(Calendar.DATE);
		filename += calendar.get(Calendar.HOUR);
		filename += calendar.get(Calendar.MINUTE);
		filename += calendar.get(Calendar.SECOND);
		filename += calendar.get(Calendar.MILLISECOND);
		filename += "_"+new Random().nextInt(10000);
		filename += ("." + extName);
		
		return filename;
	}
}

