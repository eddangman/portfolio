package com.web.book.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.book.model.BookVO;
import com.web.book.model.CateVO;
import com.web.book.mybatis.AdminMapper;

@Service
public class AdminServiceImpl implements AdminService{
	
	private static final Logger log = LoggerFactory.getLogger(AuthorServiceImpl.class);
	
	@Autowired
	private AdminMapper adminMapper;	
	
	/* 상품 등록 */
	@Override
	public void bookEnroll(BookVO book) {
		
		log.info("(srevice)bookEnroll........");
		adminMapper.bookEnroll(book);
		
	}

	@Override
	public List<CateVO> cateList() {
		
		log.info("(service)cateList........");		
		return adminMapper.cateList();
	}
	
	

	
}
