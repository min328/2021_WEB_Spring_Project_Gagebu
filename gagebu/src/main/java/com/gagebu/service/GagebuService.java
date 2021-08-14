package com.gagebu.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gagebu.dao.GagebuDAO;
import com.gagebu.vo.GageVO;

@Service
public class GagebuService {
	
	@Autowired
	GagebuDAO dao;

	// 1.가계부 내역 입력 기능
	public void addSpending(GageVO vo) throws Exception {
		dao.insertSpending(vo);
	}

	// 2. 가계부 불러오기
	public List<GageVO> readGagebuAll() throws Exception {
		return dao.selectAllGagebu();
	}
	
	// 3. 가계부 내역 삭제
	public void getRidOf(int index) throws Exception {
		dao.deleteOne(index);
	}

	// 4. 가계부 업데이트
	public GageVO getOneList(int index) throws Exception {
		return dao.selectOne(index);
	}
	
	public void changeItem(Map<String, Object> map) throws Exception {
		dao.updateOne(map);		
	}

}
