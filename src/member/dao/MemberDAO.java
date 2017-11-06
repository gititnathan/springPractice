package member.dao;

import java.util.List;

import member.dto.MemberDBBean;

public interface MemberDAO {
	public List<MemberDBBean> listMember();
	public boolean checkMember(String name, String ssn1, String ssn2);
	public MemberDBBean getMember(int no);
	public int insertMember(MemberDBBean dto);
	public int deleteMember(int no);
//	public int updateMember(MemberDBBean dto);
}
