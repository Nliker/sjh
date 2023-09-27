package memberService;

import memberDto.MemberDto;

public interface MemberService {

	MemberDto getMemberByCredential(MemberDto member) throws Exception;

	boolean createMember(MemberDto member) throws Exception;
	
	void modifyMember(MemberDto member) throws Exception;
	
	void deleteMember(String id) throws Exception;
}
