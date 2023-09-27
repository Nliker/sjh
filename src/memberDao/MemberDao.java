package memberDao;

import java.sql.SQLException;

import memberDto.MemberDto;

public interface MemberDao {

	MemberDto selectByIdAndPassword(MemberDto member) throws SQLException;

	boolean insertMember(MemberDto member) throws SQLException;
	
	void updateMember(MemberDto member) throws SQLException;
	
	void deleteMember(String id) throws SQLException;
}
