package com.gagebu.dao;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.gagebu.vo.GageVO;

@Repository
public class GagebuDAO {

	@Inject
	private SqlSession sql;
	
	//1.가계부 내역 입력 기능
	public void insertSpending(GageVO vo) throws Exception {
		sql.insert("gagebu.insertSpending", vo);
	}

	// 2. 가계부 불러오기
	public List<GageVO> selectAllGagebu() throws Exception {
		return sql.selectList("gagebu.selectAllcommon");
	}
	
	//3. 가계부 내역 삭제
	public void deleteOne(int index) throws Exception {
		System.out.println("삭제 번호 : " + index);
		sql.delete("gagebu.deleteOne", index);
		System.out.println("삭제 완료");
	}

	// 4. 가계부 업데이트
	public GageVO selectOne(int index) throws Exception {
		System.out.println("업데이트할 대상 : " + index + "번");
		return sql.selectOne("gagebu.selectOne", index);
	}

	public void updateOne(Map<String, Object> map) throws Exception {
		
		String column = (String) map.get("column_name");
		System.out.println("업데이트 해당 DB칼럼명 : " + column );
		
		switch(column) {
		case "whenUsed": sql.update("gagebu.update_whenUsed", map);
						break;
		case "cate1": sql.update("gagebu.update_cate1", map);
						break;
		case "price": sql.update("gagebu.update_price", map);
						break;
		case "content": sql.update("gagebu.update_content", map);
						break;
		case "memo": sql.update("gagebu.update_memo", map);
						break;
		case "place": sql.update("gagebu.update_place", map);
						break;
		}
		
	}
	


}
