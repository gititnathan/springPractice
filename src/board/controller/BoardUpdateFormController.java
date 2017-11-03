package board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import board.dao.BoardDAO;
import board.dto.BoardDBBean;

public class BoardUpdateFormController implements Controller {

	private BoardDAO boardDAO;
	
	public void setBoardDAO(BoardDAO boardDAO) {
		this.boardDAO = boardDAO;
	}
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0, 
										HttpServletResponse arg1) throws Exception {
		int num = ServletRequestUtils.getIntParameter(arg0, "num"); // int를 받아오는 방법
		BoardDBBean dto = boardDAO.getBoard(num, "update"); // update라는 mode로 dto를 불러온다.
		return new ModelAndView("WEB-INF/board/updateForm.jsp", "getBoard", dto); // "getBoard"로 dto를 넘겨준다.
	}

}
