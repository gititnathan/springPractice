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
		int num = ServletRequestUtils.getIntParameter(arg0, "num"); // int�� �޾ƿ��� ���
		BoardDBBean dto = boardDAO.getBoard(num, "update"); // update��� mode�� dto�� �ҷ��´�.
		return new ModelAndView("WEB-INF/board/updateForm.jsp", "getBoard", dto); // "getBoard"�� dto�� �Ѱ��ش�.
	}

}
