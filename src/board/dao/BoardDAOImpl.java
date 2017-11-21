package board.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import board.dto.BoardDBBean;
import board.ibatis.BoardMapper;

public class BoardDAOImpl implements BoardDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
//	class MyRowMapper implements RowMapper<BoardDBBean>{
//		@Override
//		public BoardDBBean mapRow(ResultSet arg0, int arg1) throws SQLException {
//			BoardDBBean dto = new BoardDBBean();
//			dto.setNum(arg0.getInt("num"));
//			dto.setWriter(arg0.getString("writer"));
//			dto.setEmail(arg0.getString("email"));
//			dto.setSubject(arg0.getString("subject"));
//			dto.setPasswd(arg0.getString("passwd"));
//			dto.setReg_date(arg0.getString("reg_date"));
//			dto.setReadcount(arg0.getInt("readcount"));
//			dto.setContent(arg0.getString("content"));
//			dto.setIp(arg0.getString("ip"));
//			dto.setRe_step(arg0.getInt("re_step"));
//			dto.setRe_level(arg0.getInt("re_level"));
//			return dto;
//		}
//	}
//	MyRowMapper rowMapper = new MyRowMapper();
//	
//	private class MyResultSetExtractor implements ResultSetExtractor<BoardDBBean>{
//		@Override
//		public BoardDBBean extractData(ResultSet arg0) throws SQLException, DataAccessException {
//			if (arg0.next()) {
//				BoardDBBean dto = new BoardDBBean();
//				dto.setNum(arg0.getInt("num"));
//				dto.setWriter(arg0.getString("writer"));
//				dto.setEmail(arg0.getString("email"));
//				dto.setSubject(arg0.getString("subject"));
//				dto.setPasswd(arg0.getString("passwd"));
//				dto.setReg_date(arg0.getString("reg_date"));
//				dto.setReadcount(arg0.getInt("readcount"));
//				dto.setContent(arg0.getString("content"));
//				dto.setIp(arg0.getString("ip"));
//				dto.setRe_step(arg0.getInt("re_step"));
//				dto.setRe_level(arg0.getInt("re_level"));
//				return dto;
//			}
//			throw new DataRetrievalFailureException("해당 객체를 찾을 수 없습니다.");
//		}
//	}
//	private MyResultSetExtractor extractor = new MyResultSetExtractor();
	
/*	private class MyPreparedStatementSetterForPrimaryKey implements PreparedStatementSetter{
		private Integer num;
		public MyPreparedStatementSetterForPrimaryKey(Integer num) {
			this.num = num;
		}
		@Override
		public void setValues(PreparedStatement arg0) throws SQLException {
			arg0.setInt(1, num);
		}
	} //setValues로 preparedStatement에 int값을 담아야 getBoard의 Query 실행할때 new로 선언해서 해당 클래스가 가지고 있는 값을 넘겨줘야 함.

*/

	@Override
	public BoardDBBean getBoard(int num, String mode) {
		if(mode.equals("content")) {
			BoardMapper.readcount(num);
		}
		return BoardMapper.getBoard(num);
	}

	
	@Override
	public List<BoardDBBean> listBoard() {
		return BoardMapper.listBoard();
	}

	
	@Override
	public int insertBoard(BoardDBBean dto) {
		if (dto.getNum()==0) {
	String sql = "update spring_board set re_step = re_step + 1";
	BoardMapper.restepCount(sql);
		}else {
			String sql = "update spring_board set re_step = re_step + 1 where re_step >"+ dto.getNum();
			BoardMapper.restepCount(sql);
			dto.setRe_step(dto.getRe_step()+1);
			dto.setRe_level(dto.getRe_level()+1);
		}		
		
		return BoardMapper.insertBoard(dto);
	}

	@Override
	public int deleteBoard(int num, String passwd) {
		BoardDBBean dto = BoardMapper.getBoard(num);
		if (dto.getPasswd().equals(passwd)) {
			return BoardMapper.deleteBoard(num);
		}
		return -1;
	}

	@Override
	public int updateBoard(BoardDBBean dto) {
		BoardDBBean dto2 = getBoard(dto.getNum(), "update");
		if (dto2.getPasswd().equals(dto.getPasswd())) {
			return BoardMapper.updateBoard(dto);
		}
		return -1;
	}
	
	
}
















