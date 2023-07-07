package com.web.book.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.book.model.AttachImageVO;
import com.web.book.mybatis.AttachMapper;

@Service
public class AttachServiceImpl implements AttachService {

	private static final Logger log = LoggerFactory.getLogger(AuthorServiceImpl.class);
	
	@Autowired
	AttachMapper attachMapper;
	
	/* 이미지 데이터 반환 */
	@Override
	public List<AttachImageVO> getAttachList(int bookId) {
		log.info("getAttachList.........");		
		return attachMapper.getAttachList(bookId);
	}

	
}
