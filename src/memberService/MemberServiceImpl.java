package memberService;

import memberDao.UserDao;
import memberDao.UserDaoImpl;
import memberDto.MemberDto;

public class MemberServiceImpl implements MemberService {
	private static MemberService userService=new MemberServiceImpl();
	private UserDao userDao;
	private MemberServiceImpl() {
		userDao=UserDaoImpl.getInstance();
	}
	
	public static MemberService getInstance() {
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
