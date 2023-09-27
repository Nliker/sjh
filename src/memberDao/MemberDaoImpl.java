package memberDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbUtil.DBUtil;
import memberDto.MemberDto;

public class MemberDaoImpl implements MemberDao{
	private static MemberDao userDao=new MemberDaoImpl();
	private DBUtil db;
	
	private MemberDaoImpl() {
		db=DBUtil.getInstance();
	}
	public static MemberDao getInstance() {
		return userDao;
	}
	@Override
	public MemberDto selectByIdAndPassword(MemberDto member) throws SQLException {
		try(
				Connection conn=db.getConnection();
				PreparedStatement stmt=conn.prepareStatement(
						"select * from members where user_id=? and user_pass=?;"
						);
				){
			int index=1;
			stmt.setString(index++,member.getId());
			stmt.setString(index++, member.getPassword());
			ResultSet rs=stmt.executeQuery();
			if(rs.next()) {
				MemberDto memberInfo=new MemberDto();
				memberInfo.setId(rs.getString("user_id"));
				memberInfo.setName(rs.getString("user_name"));
				memberInfo.setEmail(rs.getString("email"));
				memberInfo.setAge(rs.getInt("age"));
				memberInfo.setJoinDate(rs.getString("joinDate"));
				memberInfo.setPassword(rs.getString("user_pass"));
				System.out.println(memberInfo);
				return memberInfo;
			}
		}
		return null;
	}
	@Override
	public boolean insertMember(MemberDto member) throws SQLException {
		try(
				Connection conn=db.getConnection();
				PreparedStatement stmt=conn.prepareStatement(
						"insert into members(user_id,user_name,user_pass,joinDate,age,email) values(?,?,?,?,?,?);"
						);
				){
			int index=1;
			stmt.setString(index++,member.getId());
			stmt.setString(index++,member.getName());
			stmt.setString(index++,member.getPassword());
			stmt.setString(index++,"20231111");
			stmt.setInt(index++,member.getAge());
			stmt.setString(index++,member.getEmail());
			stmt.executeUpdate();
			
			return true;
		}catch(Exception e) {
			return false;
		}
	}


	@Override
	public void updateMember(MemberDto member) throws SQLException {
		try(
				Connection conn=db.getConnection();
				PreparedStatement stmt=conn.prepareStatement(
						"update members set user_name=?,email=?,age=? where user_id=?;"
						);
				){
			int index=1;
			stmt.setString(index++,member.getName());
			stmt.setString(index++,member.getEmail());
			stmt.setInt(index++,member.getAge());
			stmt.setString(index++,member.getId());
			stmt.executeUpdate();
		}
	}
	@Override
	public void deleteMember(String id) throws SQLException {
		try(
				Connection conn=db.getConnection();
				PreparedStatement stmt=conn.prepareStatement(
						"delete from members where user_id=?;"
						);
				){
			stmt.setString(1,id);
			stmt.executeUpdate();
		}
	}
	
	
}
