package com.web.book.service;

import java.util.List;

import com.web.book.model.AuthorVO;
import com.web.book.model.Criteria;

public interface AuthorService {

	/* 작가 등록 */
	public void authorEnroll(AuthorVO author) throws Exception;
	
	/* 작가 목록 */
	public List<AuthorVO> authorGetList(Criteria cri)throws Exception;;
	
	/* 작가 총 수 */
	public int authorGetTotal(Criteria cri)throws Exception;;
}