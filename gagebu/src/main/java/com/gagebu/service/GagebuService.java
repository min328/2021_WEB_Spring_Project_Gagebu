package com.gagebu.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gagebu.dao.GagebuDAO;
import com.gagebu.vo.GageVO;
import com.gagebu.vo.PeriodVO;

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

	// 달력형 가계부 기간정하기
	public void addPeriod(PeriodVO pvo) throws Exception {
		dao.insertPeriod(pvo);
	}
	
	// 달력형 가계부 기간 불러오기
	public List<PeriodVO> readPeriodAll() throws Exception {
		return dao.selectAllPeriod();
	}

	// 달력형 가계부 기간 삭제하기
	public void getRidOfPeriod(int index) throws Exception {
		dao.deletePeriod(index);
	}

}
