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
		//파일 받기
		MultipartHttpServletRequest mr = (MultipartHttpServletRequest)arg0; // req로 받은 애를 형변환해서 mr에 담아준다.
		MultipartFile mf = mr.getFile("filename"); // mr에서 filename을 가지고 있는 애를 가져와서 mf에 담는다.
		String filename = mf.getOriginalFilename(); // mf에서 가져온 원본파일의 이름을 String형 filename에 담아준다.
		System.out.println("filename = " + filename); 
		
		// 받은 파일에 대한 확인.
		// 저장 경로 설정하기
		HttpSession session = arg0.getSession();
		String upPath = session.getServletContext().getRealPath("/files"); // 절대경로 지정해주는 것.
		
		// 서버에 파일 쓰기
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
		int snum = Integer.parseInt(num); // int를 받아오는 방법
		BoardDBBean dto = boardDAO.getBoard(snum, "update"); // update라는 mode로 dto를 불러온다.
		return new ModelAndView("WEB-INF/test/updateForm.jsp", "getBoard", dto); // "getBoard"로 dto를 넘겨준다.
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
		여러개의 인자를 처리할때
		(@RequestParam Map<String, String> params)
		Set<Entry<String, String>> set = params.entrySet();
		for(Entry<String, String> entry : set){
			System.out.println(entry.getKey() + "=" + entry.getValue());	
		}
	 */
}
