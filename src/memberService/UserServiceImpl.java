package memberService;

import memberDao.UserDao;
import memberDao.UserDaoImpl;
import memberDto.MemberDto;

public class UserServiceImpl implements UserService {
	private static UserService userService=new UserServiceImpl();
	private UserDao userDao;
	private UserServiceImpl() {
		userDao=UserDaoImpl.getInstance();
	}
	
	public static UserService getInstance() {
		return userService;
	}

	@Override
	public MemberDto getMemberByCredential(MemberDto member) throws Exception {
		return userDao.selectByIdAndPassword(member);
	}
	
	@Override
	public boolean createMember(MemberDto member) throws Exception{
		return userDao.insertMember(member);
	}
}
