package member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.sun.xml.internal.ws.util.StringUtils;

import member.dao.MemberDAO;

public class MemberDeleteProController implements Controller {
	private MemberDAO memberDAO;
	
	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		// TODO Auto-generated method stub
		int no = ServletRequestUtils.getIntParameter(arg0, "no");
		int res = memberDAO.deleteMember(no);
		return new ModelAndView("redirect:list_member.do");
	}

}
