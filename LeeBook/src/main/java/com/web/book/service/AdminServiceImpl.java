package com.web.book.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.book.model.AttachImageVO;
import com.web.book.model.BookVO;
import com.web.book.model.CateVO;
import com.web.book.model.Criteria;
import com.web.book.mybatis.AdminMapper;

@Service
public class AdminServiceImpl implements AdminService{
	
	private static final Logger log = LoggerFactory.getLogger(AuthorServiceImpl.class);
	
	@Autowired
	private AdminMapper adminMapper;	
	
	/* 상품 등록 */
	@Transactional
	@Override
	public void bookEnroll(BookVO book) {
		
		log.info("(srevice)bookEnroll........");
		adminMapper.bookEnroll(book);
		
		if (book.getImageList() == null || book.getImageList().size() <= 0) {
			return;
		}

		book.getImageList().forEach(attach -> {			
			attach.setBookId(book.getBookId());
			adminMapper.imageEnroll(attach);

		});
		
	}

	/* 카테고리 리스트 */
	@Override
	public List<CateVO> cateList() {
		
		log.info("(service)cateList........");		
		return adminMapper.cateList();
	}
	
	/* 상품 리스트 */
	@Override
	public List<BookVO> goodsGetList(Criteria cri) {
		
		log.info("goodsGetTotalList()..........");
		
		return adminMapper.goodsGetList(cri);
	}

	/* 상품 총 갯수 */
	public int goodsGetTotal(Criteria cri) {
		
		log.info("goodsGetTotal().........");
		
		return adminMapper.goodsGetTotal(cri);
	}

	/* 상품 조회 페이지 */
	@Override
	public BookVO goodsGetDetail(int bookId) {
		
		log.info("(service)bookGetDetail......." + bookId);		
		return adminMapper.goodsGetDetail(bookId);
	}

	/* 상품 정보 수정 */
	@Transactional
	@Override
	public int goodsModify(BookVO vo) {

		int result = adminMapper.goodsModify(vo);

//		if (result == 1 && vo.getImageList() != null && vo.getImageList().size() > 0) {
//
//			adminMapper.deleteImageAll(vo.getBookId());
//
//			vo.getImageList().forEach(attach -> {
//
//				attach.setBookId(vo.getBookId());
//				adminMapper.imageEnroll(attach);
//
//			});
//
//		}

		return result;
	}

	/* 지정 상품 이미지 정보 얻기 */
	@Override
	public List<AttachImageVO> getAttachInfo(int bookId) {
		log.info("getAttachInfo........");
		
		return adminMapper.getAttachInfo(bookId);
	}

	/* 상품 정보 삭제 */
	@Override
	@Transactional
	public int goodsDelete(int bookId) {
		log.info("goodsDelete..........");

		adminMapper.deleteImageAll(bookId);

		return adminMapper.goodsDelete(bookId);
	}
	
	

	
}
