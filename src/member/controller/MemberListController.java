package member.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import member.dao.MemberDAO;
import member.dto.MemberDBBean;

public class MemberListController implements Controller {
	
	private MemberDAO memberDAO;
	
	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		List<MemberDBBean> list = memberDAO.listMember();
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("WEB-INF/member/memberAll.jsp");
		mav.addObject("memberList", list);
		return mav;
	}

}
