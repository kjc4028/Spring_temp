package com.kjc.jc.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.kjc.jc.Dto.BDto;
import com.kjc.jc.util.Constant;

public class BDao {
	DataSource dataSource;
	
	JdbcTemplate template;
	
	public BDao() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/mysql2");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		this.template = Constant.template;
	}


	public ArrayList<BDto> list(){

		String sql = "select bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent from mvcboard order by bGroup desc, bStep asc";
		return (ArrayList<BDto>)template.query(sql, new BeanPropertyRowMapper<BDto>(BDto.class));

		//		ArrayList<BDto> dtos = new ArrayList<BDto>();
		//		Connection conn = null;
		//		PreparedStatement pstmt = null;
		//		ResultSet rs = null;
		//		try {
		//			conn = dataSource.getConnection();
		//			String sql = "select bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent from mvcboard order by bGroup desc, bStep asc";
		//			pstmt = conn.prepareStatement(sql);
		//			rs = pstmt.executeQuery();
		//
		//			while(rs.next()){
		//				int bId = rs.getInt("bId");
		//				String bName = rs.getString("bName");
		//				String bTitle = rs.getString("bTitle");
		//				String bContent = rs.getString("bContent");
		//				Timestamp bDate = rs.getTimestamp("bDate");
		//				int bHit = rs.getInt("bHit");
		//				int bGroup = rs.getInt("bGroup");
		//				int bStep = rs.getInt("bStep");
		//				int bIndent = rs.getInt("bIndent");
		//
		//				BDto dto = new BDto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent);
		//				dtos.add(dto);
		//			}
		//		} catch (Exception e) {
		//			e.printStackTrace();
		//		}finally {
		//
		//			try {
		//				if(rs != null)rs.close();
		//				if(pstmt != null)pstmt.close();
		//				if(conn != null)conn.close();
		//
		//			} catch (SQLException e) {
		//				e.printStackTrace();
		//			}
		//		}
		//		return dtos;

	}

	public void write(final String bName, final String bTitle, final String bContent) {



		template.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				// TODO Auto-generated method stub
				String sql = "insert into mvcboard ( bId,bName, bTitle, bContent, bHit, bGroup, bStep, bIndent) values ( ?, ?, ?, ?, 0, ?, 0, 0 )";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, gNext());
				pstmt.setString(2, bName);
				pstmt.setString(3, bTitle);
				pstmt.setString(4, bContent);
				pstmt.setInt(5,gNext());

				return pstmt;
			}
		});
	}

	public int gNext() throws SQLException{
		Connection conn = null; 
		PreparedStatement pstmt = null;
		ResultSet rs = null ;
		int aa = 1;

		try {
			String sql = "select bId from mvcboard order by bId desc";
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()){
				aa = rs.getInt(1)+1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs!=null)rs.close();
			if(pstmt!=null)pstmt.close();
			if(conn!=null)conn.close();

		}
		return aa;
		
		
	}

	public BDto contentView(String strID){
		
		upHit(strID);
		String query = "select * from mvcboard where bId = " + strID;
		return template.queryForObject(query, new BeanPropertyRowMapper<BDto>(BDto.class));
		
	}
	
	private void upHit(final String bId) {
		// TODO Auto-generated method stub
		
		String query = "update mvcboard set bHit = bHit + 1 where bId = ?";
		this.template.update(query, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				// TODO Auto-generated method stub
				ps.setInt(1, Integer.parseInt(bId));
			}
		});
		
	}
	
	public void delete(final String bId){
		
		String sql = "delete from mvcboard where bId = ? ";
		this.template.update(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				// TODO Auto-generated method stub
				ps.setInt(1, Integer.parseInt(bId));
				
			}
		});
	}
	
	public void modify(final String bId, final String bName,final String bTitle, final String bContent){
		String sql = "update mvcboard set bName=?, bTitle = ?, bContent = ? where bId = ? ";
		this.template.update(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				// TODO Auto-generated method stub
				ps.setString(1, bName );
				ps.setString(2, bTitle );
				ps.setString(3, bContent );
				ps.setInt(4, Integer.parseInt(bId));
				

			}
		});
	}

	public void reply(final String bName, final String bTitle, final String bContent,final String bGroup, final String bStep, final String bIndent){
		
		replyShape(bGroup, bStep);
		String sql = "insert into mvcboard (bId,bName, bTitle, bContent, bGroup, bStep, bIndent) values ( ?,?, ?, ?, ?, ?, ? )";
		this.template.update(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				// TODO Auto-generated method stub
				ps.setInt(1, gNext());
				ps.setString(2, bName);
				ps.setString(3, bTitle);
				ps.setString(4, bContent);
				ps.setInt(5, Integer.parseInt(bGroup));
				ps.setInt(6, Integer.parseInt(bStep) + 1);
				ps.setInt(7, Integer.parseInt(bIndent) + 1);

				
			}
		});
		
	}
	
	private void replyShape( final String bGroup, final String bStep) {
		// TODO Auto-generated method stub
		
		String query = "update mvcboard set bStep = bStep + 1 where bGroup = ? and bStep > ?";
		
		this.template.update(query, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				// TODO Auto-generated method stub
				ps.setString(1, bGroup);
				ps.setString(2, bStep);
			}
		});
	}

	public BDto reply_view(String bId) {
		// TODO Auto-generated method stub
		String query = "select * from mvcboard where bId = " + bId;
//		return template.queryForObject(query, ParameterizedBeanPropertyRowMapper.newInstance(BDto.class));
		return template.queryForObject(query, new BeanPropertyRowMapper<BDto>(BDto.class));
		
	}
	
	
	
}







