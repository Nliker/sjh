package memberDao;

import java.sql.SQLException;

import memberDto.MemberDto;

public interface UserDao {

	MemberDto selectByIdAndPassword(MemberDto member) throws SQLException;

}