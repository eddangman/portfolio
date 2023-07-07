package com.web.book.mybatis;

import java.util.List;

import com.web.book.model.AttachImageVO;

public interface AttachMapper {

	List<AttachImageVO> getAttachList(int bookId);

}
