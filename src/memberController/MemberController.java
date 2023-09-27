package memberController;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import attraction.dto.AttractionDto;
import memberDto.MemberDto;
import memberService.MemberService;
import memberService.MemberServiceImpl;

@WebServlet("/member")
public class MemberController extends HttpServlet {
	private MemberService memberSerivce;
	
	@Override
	public void init() throws ServletException {
		memberSerivce=MemberServiceImpl.getInstance();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String order=req.getParameter("action");
		if("mvLogin".equals(order)){
			mvLogin(req,resp);
		}else if("login".equals(order)) {
			login(req,resp);
		}else if("mvJoin".equals(order)) {
			mvJoin(req,resp);
		}else if("join".equals(order)) {
			join(req,resp);
		}else if("logout".equals(order)) {
			logout(req,resp);
		}else if("mvMypage".equals(order)) {
			mvMypage(req,resp);
		}else if("modifyMember".equals(order)) {
			modifyMember(req,resp);
		}else if("deleteMember".equals(order)) {
			deleteMember(req,resp);
		}else {
			good();
		}
	}
	private void deleteMember(HttpServletRequest req, HttpServletResponse resp) {
		try {
			HttpSession hs=req.getSession();
			MemberDto member=(MemberDto) hs.getAttribute("memberInfo");
			memberSerivce.deleteMember(member.getId());
			hs.invalidate();
			StringBuilder json = new StringBuilder();
			resp.setContentType("application/json");
			resp.setCharacterEncoding("utf-8");
			resp.getWriter().write("{\"result\":\"true\"}");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	private void modifyMember(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		try {
			String name=req.getParameter("name");
			String email=req.getParameter("email");
			String strAge=req.getParameter("age");
			HttpSession hs=req.getSession();
			if("".equals(name) || "".equals(email) || "".equals(strAge)) {
				hs.setAttribute("loginFaildMsg", "모든 사항들을 채워주세요!");
				hs.setAttribute("nextUri",req.getContextPath()+"/member?action=mvMypage");
				resp.sendRedirect(req.getContextPath()+"/common/loginError.jsp");
				return;
			}
			int age=Integer.parseInt(strAge);
			MemberDto member=(MemberDto) hs.getAttribute("memberInfo");
			member.setEmail(email);
			member.setAge(age);
			member.setName(name);
			memberSerivce.modifyMember(member);
			hs.setAttribute("loginFaildMsg", "수정이 완료되었습니다!");
			hs.setAttribute("nextUri",req.getContextPath()+"/index.jsp");
			resp.sendRedirect(req.getContextPath()+"/common/loginError.jsp");
			return;
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void mvMypage(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		System.out.println("mvMypage");
		RequestDispatcher dp=req.getRequestDispatcher("user/mypage.jsp");
		dp.forward(req, resp);
	}
	private void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		HttpSession se=req.getSession();
		se.invalidate();
		resp.sendRedirect(req.getContextPath()+"/index.jsp");
	}

	private void join(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		try {
			System.out.println("join");
			String id=req.getParameter("id");
			String password=req.getParameter("password");
			String name=req.getParameter("name");
			String email=req.getParameter("email");
			String strAge=req.getParameter("age");
			HttpSession hs=req.getSession();
			if("".equals(id) || "".equals(password) || "".equals(name) || "".equals(email) || "".equals(strAge)) {
				hs.setAttribute("loginFaildMsg", "모든 사항들을 채워주세요!");
				hs.setAttribute("nextUri",req.getContextPath()+"/member?action=mvJoin");
				resp.sendRedirect(req.getContextPath()+"/common/loginError.jsp");
				return;
			}
			int age=Integer.parseInt(strAge);
			
			MemberDto member=new MemberDto();
			member.setId(id);
			member.setName(name);
			member.setPassword(password);
			member.setAge(age);
			member.setEmail(email);
			boolean result=memberSerivce.createMember(member);

			if(!result) {
				hs.setAttribute("loginFaildMsg", "유효하지 않은 아이디입니다!");
				hs.setAttribute("nextUri",req.getContextPath()+"/member?action=mvJoin");
				resp.sendRedirect(req.getContextPath()+"/common/loginError.jsp");
				return;
			}
			hs.setAttribute("loginFaildMsg", "정상적으로 회원가입 처리되었습니다!로그인을 진행해주세요.");
			hs.setAttribute("nextUri",req.getContextPath()+"/member?action=mvLogin");
			resp.sendRedirect(req.getContextPath()+"/common/loginError.jsp");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void mvJoin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("mvJoin");
		RequestDispatcher dp=req.getRequestDispatcher("user/register.jsp");
		dp.forward(req, resp);
	}

	private void login(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("login!!!");
		try {
			String id=req.getParameter("id");
			String password=req.getParameter("password");
			MemberDto member=new MemberDto();
			member.setId(id);
			member.setPassword(password);

			MemberDto memberInfo=memberSerivce.getMemberByCredential(member);
			HttpSession hs=req.getSession();
			System.out.println(memberInfo);
			if(memberInfo==null) {
				hs.setAttribute("loginFaildMsg", "아이디 또는 비밀번호 확인 후 다시 로그인하세요.");
				hs.setAttribute("nextUri",req.getContextPath()+"/member?action=mvLogin");
				resp.sendRedirect(req.getContextPath()+"/common/loginError.jsp");
				return;
			}

			hs.setAttribute("memberInfo",memberInfo);
			resp.sendRedirect(req.getContextPath()+"/index.jsp");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void mvLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("mvLogin");
		RequestDispatcher dp=req.getRequestDispatcher("user/login.jsp");
		dp.forward(req, resp);
	}

	private void good() {
		System.out.println("good~~~~~~~~~~~~~");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		doGet(req,resp);
	}
	
	
}
