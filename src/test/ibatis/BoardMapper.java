package test.ibatis;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import test.dto.BoardDBBean;

import com.ibatis.common.resources.Resources;

import java.io.Reader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.sql.SQLException;

/**
 * This is not a best practices class.  It's just an example
 * to give you an idea of how iBATIS works.  For a more complete
 * example, see JPetStore 5.0 at http://www.ibatis.com.
 */
public class BoardMapper {

  /**
   * SqlMapClient instances are thread safe, so you only need one.
   * In this case, we'll use a static singleton.  So sue me.  ;-)
   */
	private static SqlSessionFactory sqlMapper;

  /**
   * It's not a good idea to put code that can fail in a class initializer,
   * but for sake of argument, here's how you configure an SQL Map.
   */
	static {
		try {
			String resource = "test/ibatis/SqlMapConfig.xml";
			Reader reader = Resources.getResourceAsReader(resource);
			sqlMapper = new SqlSessionFactoryBuilder().build(reader);
			reader.close();
		}catch (IOException e) {
      // Fail fast.
			throw new RuntimeException("Something bad happened while building the SqlMapClient instance." + e, e);
		}
	}

	public static List listBoard(){
		SqlSession session = sqlMapper.openSession();
		try {
			List list = session.selectList("listBoard");
			return list;
		}finally {
			session.close();
		}
	}
	
	public static void readcount(int num) {
		SqlSession session = sqlMapper.openSession();
		try {
			int res = session.update("readcount", num);
			session.commit();
		}finally {
			session.close();
		}
	}
	
	public static BoardDBBean getBoard(int num) {
		SqlSession session = sqlMapper.openSession();
		try {
			BoardDBBean dto = session.selectOne("getBoard", num);
			return dto;
		}finally {
			session.close();
		}
	}
	
	public static void restepCount(String sql) {
		SqlSession session = sqlMapper.openSession();
		HashMap map = new HashMap();
		map.put("sql", sql);
		try {
			session.update("restepCount", map);
		}finally {
			session.close();
		}
	}
	
	public static int insertBoard(BoardDBBean dto) {
		SqlSession session = sqlMapper.openSession(); 
		try {
			 int res = session.insert("insertBoard", dto);
			 session.commit();
			 return 1;
		  }finally {
			  session.close();
		  }
	  }
	
	  public static int deleteBoard(int num) {
	  SqlSession session = sqlMapper.openSession(); 
		  try {
		int res = session.delete("deleteBoard", num);
		session.commit();
		return res;
		  }finally {
			  session.close();
		  }
	  }
	  
	  public static int updateBoard(BoardDBBean dto) {
		  SqlSession session = sqlMapper.openSession();
		  try {
			  int res = session.update("updateBoard", dto);
			  session.commit();
			  return res;
		  }finally {
			  session.close();
		  }
	  }
	  
}















