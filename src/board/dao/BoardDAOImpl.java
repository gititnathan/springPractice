package board.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import board.dto.BoardDBBean;

public class BoardDAOImpl implements BoardDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	class MyRowMapper implements RowMapper<BoardDBBean>{
		@Override
		public BoardDBBean mapRow(ResultSet arg0, int arg1) throws SQLException {
			BoardDBBean dto = new BoardDBBean();
			dto.setNum(arg0.getInt("num"));
			dto.setWriter(arg0.getString("writer"));
			dto.setEmail(arg0.getString("email"));
			dto.setSubject(arg0.getString("subject"));
			dto.setPasswd(arg0.getString("passwd"));
			dto.setReg_date(arg0.getString("reg_date"));
			dto.setReadcount(arg0.getInt("readcount"));
			dto.setContent(arg0.getString("content"));
			dto.setIp(arg0.getString("ip"));
			dto.setRe_group(arg0.getInt("re_group"));
			dto.setRe_step(arg0.getInt("re_step"));
			dto.setRe_level(arg0.getInt("re_level"));
			dto.setFilename(arg0.getString("filename"));
			dto.setFilesize(arg0.getInt("filesize"));
			return dto;
		}
	}
	MyRowMapper rowMapper = new MyRowMapper();

	@Override
	public List<BoardDBBean> listBoard() {
		String sql = "select * from spring_board order by re_group desc, re_step asc";
		List<BoardDBBean> boardList = jdbcTemplate.query(sql, rowMapper);
		return boardList;
	}

	@Override
	public BoardDBBean getBoard(int num, String mode) {
		if (mode.equals("content")) {
			String sql = "update spring_board set readcount=readcount+1 where num = ?";
			Object[] values = new Object[] {num};
			jdbcTemplate.update(sql, values);
		}
		String sql = "select * from spring_board where num = ?";
		BoardDBBean dto = jdbcTemplate.queryForObject(sql, rowMapper, num);
		return dto;
	}

	@Override
	public int insertBoard(BoardDBBean dto) {
		String sql = null;
		if(dto.getNum()==0) {
			sql = "select max(num) from spring_board";
			int num = jdbcTemplate.queryForInt(sql);
			System.out.println(dto.getNum() + "= dto.getNum()");
			dto.setRe_group(num+1);
			
		}else {
			sql = "update spring_board set re_step = re_step where re_group=? and re_step>?";
			Object[] values = new Object[] {dto.getRe_group(), dto.getRe_step()};
 				jdbcTemplate.update(sql, values);
		}
		try {
			sql = "insert into spring_board values(spring_board_seq.nextval, " 
					+"?,?,?,?,sysdate,0,?,?,?,?,?)";
			Object[] values = new Object[] {dto.getWriter(), dto.getEmail(), dto.getSubject(),
					dto.getPasswd(), dto.getContent(), dto.getIp(), dto.getRe_group(), dto.getRe_step(), dto.getRe_level()};
			int res = jdbcTemplate.update(sql, values);
			return res;
		}finally {
			
		}
	}

	@Override
	public int deleteBoard(int num, String passwd) {
		BoardDBBean dto = getBoard(num, "delete");
		if (dto.getPasswd().equals(passwd)) {
			String sql = "delete from spring_board where num = ?";
			return jdbcTemplate.update(sql, num);
		}
		return -1;
	}

	@Override
	public int updateBoard(BoardDBBean dto) {
		BoardDBBean dto2 = getBoard(dto.getNum(), "update");
		if (dto2.getPasswd().equals(dto.getPasswd())) {
			String sql = "update spring_board set writer=?, "
							+ "email=?, subject=?, content=? where num=?";
			Object[] values = new Object[] {dto.getWriter(), dto.getEmail(), 
									dto.getSubject(), dto.getContent(), dto.getNum()};
			return jdbcTemplate.update(sql, values);
		}
		return -1;
	}
	
	
}
















