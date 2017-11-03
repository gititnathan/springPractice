package board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractCommandController;

import board.dao.BoardDAO;
import board.dto.BoardDBBean;

public class BoardUpdateProCommandController extends AbstractCommandController {

	private BoardDAO boardDAO;
	
	public void setBoardDAO(BoardDAO boardDAO) {
		this.boardDAO = boardDAO;
	}
	
	@Override
	protected ModelAndView handle(HttpServletRequest arg0, 
					HttpServletResponse arg1, Object arg2, BindException arg3)
			throws Exception {
		BoardDBBean dto = (BoardDBBean)arg2;
		int res = boardDAO.updateBoard(dto);
		/*if (res>0) {
		}else if (res<0) {
		}else {
		}*/
		
		return new ModelAndView("redirect:board_list.do");
	}

}












