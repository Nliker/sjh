package memberDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbUtil.DBUtil;
import memberDto.MemberDto;

public class UserDaoImpl implements UserDao{
	private static UserDao userDao=new UserDaoImpl();
	private DBUtil db;
	
	private UserDaoImpl() {
		db=DBUtil.getInstance();
	}
	public static UserDao getInstance() {
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
	
	
}
