package board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import board.dao.BoardDAO;

public class BoardDeleteProController implements Controller {

	private BoardDAO boardDAO;
	
	public void setBoardDAO(BoardDAO boardDAO) {
		this.boardDAO = boardDAO;
	}
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0, 
									HttpServletResponse arg1) throws Exception {
		int num = ServletRequestUtils.getIntParameter(arg0, "num");
		String passwd = arg0.getParameter("passwd");
		int res = boardDAO.deleteBoard(num, passwd);
		
		/*if (res>0) {
		}else if (res<0) {
		}else {
		}*/
		
		return new ModelAndView("redirect:board_list.do");
	}

}





