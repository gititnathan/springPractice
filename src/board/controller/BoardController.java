package board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import board.dao.BoardDAO;
import board.dto.BoardDBBean;

@Controller
public class BoardController {
	@Autowired
	private BoardDAO boardDAO;

	@RequestMapping(value="/board_list.do")
	public ModelAndView listBoard(HttpServletRequest arg0, 
									HttpServletResponse arg1) throws Exception {
		List<BoardDBBean> list = boardDAO.listBoard();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("WEB-INF/board/list.jsp");
		mav.addObject("boardList", list);
		return mav;
	}

	@RequestMapping(value="/board_write.do", method=RequestMethod.GET)
	public String writeFormBoard(HttpServletRequest arg0, 
								HttpServletResponse arg1) throws Exception {
		return "WEB-INF/board/writeForm.jsp";
	}

	@RequestMapping(value="/board_write.do", method=RequestMethod.POST)
	public ModelAndView writeProBoard(HttpServletRequest arg0,
			@ModelAttribute BoardDBBean dto, BindingResult result) throws Exception {
		if (result.hasErrors()) {
			dto.setNum(0);
			dto.setRe_step(0);
			dto.setRe_level(0);
			dto.setFilename("");
		}
		dto.setIp(arg0.getRemoteAddr());
		System.out.println(dto.getWriter()); //dto에 안나온다?
		int res = boardDAO.insertBoard(dto);
		return new ModelAndView("redirect:board_list.do");
	}
	
	@RequestMapping(value="/board_content.do")
	public ModelAndView handleRequest(@RequestParam String num) throws Exception {
		int snum = Integer.parseInt(num);
		BoardDBBean dto = boardDAO.getBoard(snum, "content");
		ModelAndView mav = new ModelAndView();
		mav.addObject("getBoard", dto);
		mav.setViewName("WEB-INF/board/content.jsp");
		return mav; 
	}
	
	@RequestMapping(value="/board_delete.do", method=RequestMethod.GET)
	public ModelAndView DeleteFormBoard(@RequestParam String num) throws Exception {
		int snum = Integer.parseInt(num);
		BoardDBBean dto = boardDAO.getBoard(snum, "delete");
		ModelAndView mav = new ModelAndView();
		mav.addObject("getBoard", dto);
		mav.setViewName("WEB-INF/board/deleteForm.jsp");
		return mav; 
	}
	@RequestMapping(value="/board_delete.do", method=RequestMethod.POST)
	public ModelAndView DeleteProBoard(@RequestParam String num 
			, @RequestParam String passwd) throws Exception {
			int snum = Integer.parseInt(num);
			int res = boardDAO.deleteBoard(snum, passwd);
			/*if (res>0) {
			}else if (res<0) {
			}else {
			}*/
			return new ModelAndView("redirect:board_list.do");
	}
	
	@RequestMapping(value="/board_update.do", method=RequestMethod.GET)
	public ModelAndView UpdateFormBoard(@RequestParam String num) throws Exception {
		int snum = Integer.parseInt(num); // int를 받아오는 방법
		BoardDBBean dto = boardDAO.getBoard(snum, "update"); // update라는 mode로 dto를 불러온다.
		return new ModelAndView("WEB-INF/board/updateForm.jsp", "getBoard", dto); // "getBoard"로 dto를 넘겨준다.
	}
	
	@RequestMapping(value="/board_update.do", method=RequestMethod.POST)
	protected ModelAndView UpdateProBoard(@ModelAttribute BoardDBBean dto, BindingResult result)
		throws Exception {
		if (result.hasErrors()) {
			dto.setNum(0);
			dto.setRe_step(0);
			dto.setRe_level(0);
		}
		int res = boardDAO.updateBoard(dto);
		/*if (res>0) {
		}else if (res<0) {
		}else {
		}*/
		return new ModelAndView("redirect:board_list.do");
	}
	/*
		여러개의 인자를 처리할때
		(@RequestParam Map<String, String> params)
		Set<Entry<String, String>> set = params.entrySet();
		for(Entry<String, String> entry : set){
			System.out.println(entry.getKey() + "=" + entry.getValue());	
		}
	 */
}
