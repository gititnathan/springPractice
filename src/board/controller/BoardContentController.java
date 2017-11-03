package board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import board.dao.BoardDAO;
import board.dto.BoardDBBean;

public class BoardContentController implements Controller {

	private BoardDAO boardDAO;
	
	public void setBoardDAO(BoardDAO boardDAO) {
		this.boardDAO = boardDAO;
	}

	@Override 
	public ModelAndView handleRequest(HttpServletRequest arg0, 
									HttpServletResponse arg1) throws Exception {
		//String num = arg0.getParameter("num");
		int num = ServletRequestUtils.getIntParameter(arg0, "num");
		BoardDBBean dto = boardDAO.getBoard(num, "content");
		ModelAndView mav = new ModelAndView();
		mav.addObject("getBoard", dto);
		mav.setViewName("WEB-INF/board/content.jsp");
		return mav;  // 이렇게 주는 것과 ModelAndView 자체를 return해주는 두가지 방식이 존재한다.
	}
}
