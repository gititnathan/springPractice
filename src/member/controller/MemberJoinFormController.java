package member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import member.dao.MemberDAO;

public class MemberJoinFormController implements Controller {
	private MemberDAO memberDAO;
	
	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		// TODO Auto-generated method stub
		String name = ServletRequestUtils.getStringParameter(arg0, "name");
		String ssn1 = ServletRequestUtils.getStringParameter(arg0, "ssn1");
		String ssn2 = ServletRequestUtils.getStringParameter(arg0, "ssn2");
		boolean isMember = memberDAO.checkMember(name, ssn1, ssn2);
		return new ModelAndView("WEB-INF/member/member.jsp", "isMember", isMember);
	}

}
