package com.gagebu.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.gagebu.service.GagebuService;
import com.gagebu.vo.GageVO;
import com.gagebu.vo.UpdateVO;

@Controller
public class GagebuController {
	
	@Autowired
	GagebuService service;
	
	//가계부 입력페이지 로딩
	@GetMapping("/")
	public String goToMainHome() {
		return "index";
	}
	
	//1.가계부 내역 입력 기능
	@GetMapping("/spendThisMuch")
	public void enterYourSpending(GageVO vo) throws Exception {
		service.addSpending(vo);
	}
	
	//2. 가계부 불러오기
	@GetMapping("/howMuchSpent")
	public String readSpendingOfFamily(Model model) throws Exception {
		model.addAttribute("list", service.readGagebuAll());
		return "spendingList";
	}
	
	//3. 가계부 내역 삭제
	@GetMapping("/deleteOne")
	public void deleListItem(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("컨트롤러 시작");
		int index = Integer.parseInt(request.getParameter("idx"));
		service.getRidOf(index);
		System.out.println("컨트롤러 끝");
	}
	
	@GetMapping("/updateThis")
	public void updateListItem(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("항목 업데이트 컨트롤러 시작");
		
		int index = Integer.parseInt(request.getParameter("idx"));
		// input의 class, jsp에서 category로 받아온 값 (db의 칼럼명) => column_name
		String column_name = request.getParameter("category");
		// 변경된 내용 => current_value
		String current_value = request.getParameter("item");
		
		// index 값으로 db의 객체를 하나를 가져오고
		GageVO vo = service.getOneList(index);
		System.out.println("업데이트 대상 로딩 완료 --- ");
		
		// jsp에서 받아온 column_name을 기준으로 하여 해당 변수 값만 "get" 해서 prev_value에 저장한다.
		String prev_value = null;
		switch(column_name) {
		case "whenUsed": prev_value = vo.getWhenUsed();
							break;
		case "cate1": prev_value = vo.getCate1();
						break;
		case "price": prev_value = Integer.toString(vo.getPrice());
						break;
		case "content": prev_value = vo.getContent();
						break;
		case "memo": prev_value = vo.getMemo();
						break;
		case "place": prev_value = vo.getPlace();
						break;
		}
		
		// jsp에서 받아온 current_value 값과 prev_value 값을 비교해서 update 진행을 결정한다.
		if(!current_value.equals(prev_value)) {
			System.out.println("현재 값 : " + current_value + "와 DB기존 값 : " 
										+ prev_value + "는 일치하지 않습니다. 업데이트를 진행합니다." );
			
			Map<String, Object> map = new HashMap<String, Object>();

			// map에 값 집어넣기
			map.put("column_name", column_name);
			map.put("index_num", index);
			
			// price 값이 변경된 경우, String -> int로 변경해서 update query 실행 할 수 있도록 처리
			if(column_name == "price" ) {
				int current_value_price = Integer.parseInt(current_value);
				map.put("changed_value", current_value_price);				
			} else {				
				map.put("changed_value", current_value);
			}
			
			// 서비스 실행
			service.changeItem(map);
			System.out.println("업데이트를 종료합니다.");
			
		} else if(current_value.equals(prev_value)) {
			System.out.println("변경된 내용이 없습니다. 업데이트를 실행하지 않습니다.");
			
		} else {
			System.out.println("업데이트 중 오류가 발생하였습니다. 확인해주세요.");
		}
		
	}
}
