package member.ibatis;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;
import com.ibatis.sqlmap.client.SqlMapException;

import member.dto.MemberDBBean;

import com.ibatis.common.resources.Resources;

import java.io.Reader;
import java.io.IOException;
import java.util.List;
import java.sql.SQLException;

/**
 * This is not a best practices class.  It's just an example
 * to give you an idea of how iBATIS works.  For a more complete
 * example, see JPetStore 5.0 at http://www.ibatis.com.
 */
public class MemberMapper {

  /**
   * SqlMapClient instances are thread safe, so you only need one.
   * In this case, we'll use a static singleton.  So sue me.  ;-)
   */
  private static SqlMapClient sqlMapper;

  /**
   * It's not a good idea to put code that can fail in a class initializer,
   * but for sake of argument, here's how you configure an SQL Map.
   */
  static {
    try {
      Reader reader = Resources.getResourceAsReader("member/ibatis/SqlMapConfig.xml");
      sqlMapper = SqlMapClientBuilder.buildSqlMapClient(reader);
      reader.close(); 
    } catch (IOException e) {
      // Fail fast.
      throw new RuntimeException("Something bad happened while building the SqlMapClient instance." + e, e);
    }
  }

  public static List listMember (){
	  List list = null;
	  try {
		  list = sqlMapper.queryForList("listMember");
	  }catch(SQLException e) {
		  System.err.println("listMember() 실행중 애러 발생");
		  e.printStackTrace();
	  }
	  return list;
  }
  
  public static MemberDBBean getMember(int no) {
	  MemberDBBean dto = null;
	  try {
		  dto =(MemberDBBean) sqlMapper.queryForObject("getMember",no);
	  }catch(SQLException e){
		  e.printStackTrace();
	  }
	  return dto;
  }
  public static int insertMember(MemberDBBean dto) {
	  try {
		  sqlMapper.insert("insertBoard", dto);
		  return 1;
	  }catch(SQLException e) {
		  e.printStackTrace();
		  return -1;
	  }	
  }
  public static int deleteMember(int no) {
	  try {
		  sqlMapper.delete("deleteMember", no);
	  return 1;
	  }catch(SQLException e) {
		  e.printStackTrace();
	  return -1;
	  }
  }
  public static int updateMember(MemberDBBean dto) {
	  try {
		  sqlMapper.update("updateMember", dto);
		  return 1;
	  }catch(SQLException e){
		  e.printStackTrace();
		  return -1;
	  }
  }
  

  

}
