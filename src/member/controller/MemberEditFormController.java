package member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import member.dao.MemberDAO;
import member.dto.MemberDBBean;

public class MemberEditFormController implements Controller {
	
	private MemberDAO memberDAO;
	
	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		int no = ServletRequestUtils.getIntParameter(arg0, "no");
		MemberDBBean dto = memberDAO.getMember(no);		
		return new ModelAndView("WEB-INF/board/updateForm.jsp", "getBoard", dto);
	}
}
