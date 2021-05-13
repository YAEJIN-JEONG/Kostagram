package model;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;


public class MemberDAO {

	private static MemberDAO dao=new MemberDAO();
	private DataSource dataSource;
	
	private MemberDAO(){
		dataSource=DataSourceManager.getInstance().getDataSource();
	}
	
	public static MemberDAO getInstance(){		
		return dao;
	}
	
	public void closeAll(PreparedStatement pstmt,
			Connection con) throws SQLException{
		closeAll(null,pstmt,con);
	}
	
	public void closeAll(ResultSet rs,PreparedStatement pstmt,
			Connection con) throws SQLException{
		if(rs!=null)
			rs.close();
		if(pstmt!=null)
			pstmt.close();
		if(con!=null)
			con.close();
	}
	
	public MemberVO login(String userEmail,String userPassword) throws SQLException{
		MemberVO vo=null;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			con=dataSource.getConnection();
			String sql="select user_name, profile_image, user_email_checked from k_member where user_Email=? and user_Password=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, userEmail);
			pstmt.setString(2, userPassword);
			rs=pstmt.executeQuery();
			if(rs.next()){
				
				vo=new MemberVO(rs.getString(1), userPassword, userEmail, rs.getInt(3));
				vo.setProfileImage(rs.getString(2));
				
//				vo=new MemberVO(rs.getString(1),userPassword,userEmail,rs.getInt(2));
				
			}
		}finally{
			closeAll(rs, pstmt,con);
		}
		return vo;
	}
	public void register(String email, String password, String name) throws SQLException {
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			con=dataSource.getConnection();
			StringBuilder sql=new StringBuilder();		
			sql.append("INSERT INTO k_member(user_email,user_password,user_name) ");
			sql.append("values(?,?,?) ");
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			pstmt.setString(3, name);
			pstmt.executeUpdate();
		}finally{
			closeAll(pstmt,con);
		}
	}
	public MemberVO getProfileTotalList(String user_email) throws SQLException {
		MemberVO mvo=null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con=dataSource.getConnection();
			String sql="select user_name, profile_image, user_email, profile_content from k_member where user_email=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_email);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				mvo=new MemberVO();
				mvo.setUserName(rs.getString(1));
				mvo.setProfileImage(rs.getString(2));
				mvo.setUserEmail(rs.getString(3));
				mvo.setProfileContent(rs.getString(4));
			}
		} finally {
			closeAll(rs, pstmt, con);
		}
		return mvo;
	}
	public void updateProfile(MemberVO mvo) throws SQLException {
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			con=dataSource.getConnection();
			pstmt=con.prepareStatement("update k_member set profile_image=?,profile_content=? where user_email=?");
			pstmt.setString(1, mvo.getProfileImage());
			pstmt.setString(2, mvo.getProfileContent());
			pstmt.setString(3, mvo.getUserEmail());	
			pstmt.executeUpdate();			
		}finally{
			closeAll(pstmt,con);
		}
	}
	
	

	// getProfileByEmail 구현해서 update profilejsp 구현하기.
	   public MemberVO getProfileByEmail(String userEmail) throws SQLException {
	      MemberVO vo = null;
	      Connection con = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      try {
	         con = dataSource.getConnection();
	         StringBuilder sql = new StringBuilder();
	         sql.append("SELECT profile_image,profile_content ");
	         sql.append("FROM k_member ");
	         sql.append("WHERE user_email=? ");
	         pstmt = con.prepareStatement(sql.toString());
	         pstmt.setString(1, userEmail);
	         rs = pstmt.executeQuery();
	         if (rs.next()) {
	            vo = new MemberVO();
	            vo.setUserEmail("userEmail");
	            vo.setProfileImage(rs.getString("profile_image"));
	            vo.setProfileContent(rs.getString("profile_content"));
	         }
	      } finally {
	         closeAll(rs, pstmt, con);
	      }
	      return vo;
	   }
	   
	   public boolean isExist(String userEmail) throws SQLException {
		      Connection con = null;
		      PreparedStatement pstmt = null;
		      ResultSet rs = null;
		      int num = 0;
		      try {
		         con = dataSource.getConnection();
		         StringBuilder sql = new StringBuilder();
		         sql.append("SELECT COUNT(*) ");
		         sql.append("FROM k_member ");
		         sql.append("WHERE user_email = ? ");
		         pstmt = con.prepareStatement(sql.toString());
		         pstmt.setString(1, userEmail);
		         rs = pstmt.executeQuery();
		         if (rs.next()) {
		            num = rs.getInt(1);
		         }
		      } finally {
		         closeAll(rs, pstmt, con);
		      }
		      if (num>0) {
		    	  return true;
		      }else {
		    	  return false;		    	  
		      }
		   }
	   
	   public ArrayList<String> getAllMember() throws SQLException {
		      Connection con = null;
		      PreparedStatement pstmt = null;
		      ResultSet rs = null;
		      ArrayList<String> list = new ArrayList<String>();
		      try {
		         con = dataSource.getConnection();
		         StringBuilder sql = new StringBuilder();
		         sql.append("SELECT user_email from k_member ");
		         pstmt = con.prepareStatement(sql.toString());
		         rs = pstmt.executeQuery();
		         while(rs.next()) {
		            list.add(rs.getString(1));
		         }
		      } finally {
		         closeAll(rs, pstmt, con);
		      }
		      return list;
		   }
	   
		public void emailChecked(String email) throws SQLException {
			Connection con=null;
			PreparedStatement pstmt=null;
			try{
				con=dataSource.getConnection();
				pstmt=con.prepareStatement("update k_member set user_email_checked = 1 where user_email=?");
				pstmt.setString(1, email);
				pstmt.executeUpdate();			
			}finally{
				closeAll(pstmt,con);
			}
		}
		
		   public boolean isChecked(String userEmail) throws SQLException {
			      Connection con = null;
			      PreparedStatement pstmt = null;
			      ResultSet rs = null;
			      int num = 0;
			      try {
			         con = dataSource.getConnection();
			         StringBuilder sql = new StringBuilder();
			         sql.append("SELECT user_email_checked ");
			         sql.append("FROM k_member ");
			         sql.append("WHERE user_email = ? ");
			         pstmt = con.prepareStatement(sql.toString());
			         pstmt.setString(1, userEmail);
			         rs = pstmt.executeQuery();
			         if (rs.next()) {
			            num = rs.getInt(1);
			         }
			      } finally {
			         closeAll(rs, pstmt, con);
			      }
			      if (num == 1) {
			    	  return true;
			      }else {
			    	  return false;		    	  
			      }
			   }
		   
			public void deleteMemberByEmail(String email) throws SQLException {
				Connection con = null;
				PreparedStatement pstmt = null;
				try {
					con = dataSource.getConnection();
					pstmt = con.prepareStatement("DELETE FROM k_member WHERE user_email=?");
					pstmt.setString(1, email);
					pstmt.executeUpdate();
				} finally {
					closeAll(pstmt, con);
				}
			}
	
			public int countPostsByEmail(String userEmail) throws SQLException {
				Connection con = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				int posts = 0;
				try {
					con = dataSource.getConnection();
					pstmt = con.prepareStatement("SELECT COUNT(*) FROM k_board WHERE user_email = ?");
					pstmt.setString(1, userEmail);
					rs = pstmt.executeQuery();
					if (rs.next()) {
						posts = rs.getInt(1);
					}
				} finally {
					closeAll(rs, pstmt, con);
				}
				return posts;
			}
}