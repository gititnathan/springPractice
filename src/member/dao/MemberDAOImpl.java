package member.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import member.dto.MemberDBBean;


public class MemberDAOImpl implements MemberDAO{
	
private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	class MyRowMapper implements RowMapper<MemberDBBean>{
		@Override
		public MemberDBBean mapRow(ResultSet arg0, int arg1) throws SQLException {
			MemberDBBean dto = new MemberDBBean();
			dto.setNo(arg0.getInt("no"));
			dto.setName(arg0.getString("name"));
			dto.setId(arg0.getString("id"));
			dto.setPasswd(arg0.getString("passwd"));
			dto.setSsn1(arg0.getString("ssn1"));
			dto.setSsn2(arg0.getString("ssn2"));
			dto.setEmail(arg0.getString("email"));
			dto.setHp1(arg0.getString("hp1"));
			dto.setHp2(arg0.getString("hp2"));
			dto.setHp3(arg0.getString("hp3"));
			dto.setJoindate(arg0.getString("joindate"));
			return dto;
		}
	}
	MyRowMapper rowMapper = new MyRowMapper();
	
	@Override
	public List<MemberDBBean> listMember() {
		String sql = "select * from spring_member";
		List<MemberDBBean> memberList = jdbcTemplate.query(sql, rowMapper);
		return memberList;
	}

	@Override
	public boolean checkMember(String name, String ssn1, String ssn2) {
		String sql = "select * from spring_member where ssn1=? and ssn2=?";
		try {
		MemberDBBean checkMember = jdbcTemplate.queryForObject(sql, rowMapper, ssn1);
		if(checkMember==null) {
			return false;
		}else {
			return true;
		}
		}catch(Exception e) {
			return false;
		}
		
	}

	@Override
	public int insertMember(MemberDBBean dto) {
		String sql = "insert into spring_member values"
				+ "(member_seq.nextval, ?,?,?,?,?,?,?,?,?,sysdate)";
		Object[] values = new Object[] {dto.getName(), dto.getId(), dto.getPasswd(), 
				dto.getSsn1(), dto.getSsn2(), dto.getEmail(),
				dto.getHp1(), dto.getHp2(), dto.getHp3()};
		int res = jdbcTemplate.update(sql, values);
		return res;
	}

	@Override
	public int deleteMember(int no) {
		String sql = "delete from spring_member where no = ?";
		return jdbcTemplate.update(sql, no);
	}

	@Override
	public MemberDBBean getMember(int no) {
		// TODO Auto-generated method stub
		return null;
	}


}
