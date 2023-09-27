package memberService;

import memberDao.MemberDao;
import memberDao.MemberDaoImpl;
import memberDto.MemberDto;

public class MemberServiceImpl implements MemberService {
	private static MemberService userService=new MemberServiceImpl();
	private MemberDao memberDao;
	private MemberServiceImpl() {
		memberDao=MemberDaoImpl.getInstance();
	}
	
	public static MemberService getInstance() {
		return userService;
	}

	@Override
	public MemberDto getMemberByCredential(MemberDto member) throws Exception {
		return memberDao.selectByIdAndPassword(member);
	}
	
	@Override
	public boolean createMember(MemberDto member) throws Exception{
		return memberDao.insertMember(member);
	}

	@Override
	public void modifyMember(MemberDto member) throws Exception {	
		memberDao.updateMember(member);
	}

	@Override
	public void deleteMember(String id) throws Exception {
		memberDao.deleteMember(id);
	}
}
