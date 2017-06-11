package com.kjc.jc.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.kjc.jc.Dto.MDto;
import com.kjc.jc.util.Constant;

public class MDao {
JdbcTemplate template;

public MDao() {
	// TODO Auto-generated constructor stub
	this.template = Constant.template;

}

public void join(final String id, final String password, final String name ){
	
	template.update(new PreparedStatementCreator() {
		
		@Override
		public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
			// TODO Auto-generated method stub
			String sql = "insert into member values(?,?,?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			pstmt.setString(3, name);

			return pstmt;
		}
	});
}

public ArrayList<MDto> memberList(){
	String sql = "select * from member";
	return (ArrayList<MDto>)template.query(sql, new BeanPropertyRowMapper<MDto>(MDto.class));
}

public MDto idSelect(final String id){
	String sql = "select id from member where id = '"+id+ "'";
	return template.queryForObject(sql, new BeanPropertyRowMapper<MDto>(MDto.class));
}

public MDto pwSelect(final String id){
	String sql = "select password from member where id ="+id;
	return template.queryForObject(sql, new BeanPropertyRowMapper<MDto>(MDto.class));

}
public String login(final String logid, final String logpassword){
	
	String result = null;
	MDto a = new MDto();
	a = idSelect(logid);
	if(logid.equals(a.getId())){
		System.out.println("로그인 성공");
		result = "1";

	}else if(!logid.equals(a.getId())){
		System.out.println("로그인 실패");
		result = "0";
	}
	return result;
	
	
}


//막
}
