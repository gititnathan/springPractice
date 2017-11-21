package test.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import test.dao.BoardDAO;
import test.dto.BoardDBBean;

@Controller
public class BoardController {
	@Autowired
	private BoardDAO boardDAO;

	@RequestMapping(value="/test_list.do")
	public ModelAndView listBoard(HttpServletRequest arg0, 
									HttpServletResponse arg1) throws Exception {
		List<BoardDBBean> list = boardDAO.listBoard();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("WEB-INF/test/list.jsp");
		mav.addObject("TestList", list);
		return mav;
	}

	@RequestMapping(value="/test_write.do", method=RequestMethod.GET)
	public String writeFormBoard(HttpServletRequest arg0, 
								HttpServletResponse arg1) throws Exception {
		return "WEB-INF/test/writeForm.jsp";
	}

	@RequestMapping(value="/test_writepro.do", method=RequestMethod.POST)
	public ModelAndView writeProBoard(HttpServletRequest arg0,
			@ModelAttribute BoardDBBean dto, BindingResult result) throws Exception {
		//���� �ޱ�
		MultipartHttpServletRequest mr = (MultipartHttpServletRequest)arg0; // req�� ���� �ָ� ����ȯ�ؼ� mr�� ����ش�.
		MultipartFile mf = mr.getFile("filename"); // mr���� filename�� ������ �ִ� �ָ� �����ͼ� mf�� ��´�.
		String filename = mf.getOriginalFilename(); // mf���� ������ ���������� �̸��� String�� filename�� ����ش�.
		System.out.println("filename = " + filename); 
		
		// ���� ���Ͽ� ���� Ȯ��.
		// ���� ��� �����ϱ�
		HttpSession session = arg0.getSession();
		String upPath = session.getServletContext().getRealPath("/files"); // ������ �������ִ� ��.
		
		// ������ ���� ����
		File file = new File(upPath, filename);
		
		if (result.hasErrors() || filename.trim().equals("") || filename == null ) {
			dto.setNum(0);
			dto.setRe_step(0);
			dto.setRe_level(0);
			dto.setFilename("");
			dto.setFilesize(0);
		} else {
			mf.transferTo(file);
		}

			dto.setIp(arg0.getRemoteAddr());
			dto.setFilename(filename);
		int res = boardDAO.insertBoard(dto);
		return new ModelAndView("redirect:test_list.do");
	}
	
	@RequestMapping(value="/test_content.do")
	public ModelAndView handleRequest(@RequestParam String num) throws Exception {
		int snum = Integer.parseInt(num);
		BoardDBBean dto = boardDAO.getBoard(snum, "content");
		ModelAndView mav = new ModelAndView();
		mav.addObject("getTest", dto);
		mav.setViewName("WEB-INF/test/content.jsp");
		return mav; 
	}
	
	@RequestMapping(value="/test_delete.do", method=RequestMethod.GET)
	public ModelAndView DeleteFormBoard(@RequestParam String num) throws Exception {
		int snum = Integer.parseInt(num);
		BoardDBBean dto = boardDAO.getBoard(snum, "delete");
		ModelAndView mav = new ModelAndView();
		mav.addObject("getTest", dto);
		mav.setViewName("WEB-INF/test/deleteForm.jsp");
		return mav; 
	}
	@RequestMapping(value="/test_delete.do", method=RequestMethod.POST)
	public ModelAndView DeleteProBoard(@RequestParam String num 
			, @RequestParam String passwd) throws Exception {
			int snum = Integer.parseInt(num);
			int res = boardDAO.deleteBoard(snum, passwd);
			/*if (res>0) {
			}else if (res<0) {
			}else {
			}*/
			return new ModelAndView("redirect:test_list.do");
	}
	
	@RequestMapping(value="/test_update.do", method=RequestMethod.GET)
	public ModelAndView UpdateFormBoard(@RequestParam String num) throws Exception {
		int snum = Integer.parseInt(num); // int�� �޾ƿ��� ���
		BoardDBBean dto = boardDAO.getBoard(snum, "update"); // update��� mode�� dto�� �ҷ��´�.
		return new ModelAndView("WEB-INF/test/updateForm.jsp", "getBoard", dto); // "getBoard"�� dto�� �Ѱ��ش�.
	}
	
	@RequestMapping(value="/test_update.do", method=RequestMethod.POST)
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
		return new ModelAndView("redirect:test_list.do");
	}
	/*
		�������� ���ڸ� ó���Ҷ�
		(@RequestParam Map<String, String> params)
		Set<Entry<String, String>> set = params.entrySet();
		for(Entry<String, String> entry : set){
			System.out.println(entry.getKey() + "=" + entry.getValue());	
		}
	 */
}
