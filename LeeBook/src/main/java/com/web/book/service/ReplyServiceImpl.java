package com.web.book.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.book.model.Criteria;
import com.web.book.model.PageDTO;
import com.web.book.model.ReplyDTO;
import com.web.book.model.ReplyPageDTO;
import com.web.book.model.UpdateReplyDTO;
import com.web.book.mybatis.ReplyMapper;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	private ReplyMapper replyMapper;

	/* 댓글등록 */
	@Override
	public int enrollReply(ReplyDTO dto) {

		int result = replyMapper.enrollReply(dto);

		setRating(dto.getBookId());

		return result;
	}

	/* 댓글 존재 체크 */
	@Override
	public String checkReply(ReplyDTO dto) {

		Integer result = replyMapper.checkReply(dto);

		if (result == null) {
			return "0";
		} else {
			return "1";
		}

	}

	@Override
	public ReplyPageDTO replyList(Criteria cri) {
		ReplyPageDTO dto = new ReplyPageDTO();

		dto.setList(replyMapper.getReplyList(cri));
		dto.setPageInfo(new PageDTO(cri, replyMapper.getReplyTotal(cri.getBookId())));

		return dto;
	}

	@Override
	public int updateReply(ReplyDTO dto) {

		int result = replyMapper.updateReply(dto);

		setRating(dto.getBookId());

		return result;
	}

	@Override
	public ReplyDTO getUpdateReply(int replyId) {

		return replyMapper.getUpdateReply(replyId);
	}

	@Override
	public int deleteReply(ReplyDTO dto) {

		int result = replyMapper.deleteReply(dto.getReplyId());

		setRating(dto.getBookId());

		return result;
	}

	public void setRating(int bookId) {

		Double ratingAvg = replyMapper.getRatingAverage(bookId);

		if (ratingAvg == null) {
			ratingAvg = 0.0;
		}

		ratingAvg = (double) (Math.round(ratingAvg * 10));
		ratingAvg = ratingAvg / 10;

		UpdateReplyDTO urd = new UpdateReplyDTO();
		urd.setBookId(bookId);
		urd.setRatingAvg(ratingAvg);

		replyMapper.updateRating(urd);

	}

}
