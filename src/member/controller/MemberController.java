package member.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import member.dao.MemberDAO;
import member.dto.MemberDBBean;

@Controller
public class MemberController {
	@Autowired
	private MemberDAO memberDAO;
	
	@RequestMapping(value="/member_main.do")
	public ModelAndView MainMember(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("WEB-INF/member/member_index.jsp");
		return mav;
	}
	@RequestMapping(value="/list_member.do")
	public ModelAndView ListMember(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		List<MemberDBBean> list = memberDAO.listMember();
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("WEB-INF/member/memberAll.jsp");
		mav.addObject("memberList", list);
		return mav;
	}
	@RequestMapping(value="/check_member.do")
	public ModelAndView CheckMember(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		return new ModelAndView("WEB-INF/member/memberSsn.jsp");
	}
	
	@RequestMapping(value="/checkOk_member.do")
	public ModelAndView CheckOkMember(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		// TODO Auto-generated method stub
		String name = ServletRequestUtils.getStringParameter(arg0, "name");
		String ssn1 = ServletRequestUtils.getStringParameter(arg0, "ssn1");
		String ssn2 = ServletRequestUtils.getStringParameter(arg0, "ssn2");
		boolean isMember = memberDAO.checkMember(name, ssn1, ssn2);
		return new ModelAndView("WEB-INF/member/checkMember.jsp", "isMember", isMember);
	}
	
	@RequestMapping(value="/join_member.do")
	public ModelAndView JoinFormMember(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		// TODO Auto-generated method stub
		String name = ServletRequestUtils.getStringParameter(arg0, "name");
		String ssn1 = ServletRequestUtils.getStringParameter(arg0, "ssn1");
		String ssn2 = ServletRequestUtils.getStringParameter(arg0, "ssn2");
		boolean isMember = memberDAO.checkMember(name, ssn1, ssn2);
		return new ModelAndView("WEB-INF/member/member.jsp", "isMember", isMember);
	}
	@RequestMapping(value="/joinOk_member.do")
	public ModelAndView JoinProMember(@ModelAttribute MemberDBBean dto, BindingResult result) throws Exception {
		// TODO Auto-generated method stub
		int res = memberDAO.insertMember(dto);
		return new ModelAndView("redirect:list_member.do");
	}
	@RequestMapping(value="/edit_member.do")
	public ModelAndView EditMember(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		int no = ServletRequestUtils.getIntParameter(arg0, "no");
		MemberDBBean dto = memberDAO.getMember(no);		
		return new ModelAndView("WEB-INF/board/updateForm.jsp", "getBoard", dto);
	}
	@RequestMapping(value="/delete_member.do")
	public ModelAndView DeleteMember(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		// TODO Auto-generated method stub
		int no = ServletRequestUtils.getIntParameter(arg0, "no");
		int res = memberDAO.deleteMember(no);
		return new ModelAndView("redirect:list_member.do");
	}
	
	
}
