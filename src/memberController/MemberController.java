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

import memberDto.MemberDto;
import memberService.UserService;
import memberService.UserServiceImpl;

@WebServlet("/member")
public class MemberController extends HttpServlet {
	private UserService userService;
	
	@Override
	public void init() throws ServletException {
		userService=UserServiceImpl.getInstance();
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
		}
		else {
			good();
		}
	}

	private void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		HttpSession se=req.getSession();
		se.invalidate();
		resp.sendRedirect(req.getContextPath()+"/index.jsp");
	}

	private void join(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		System.out.println("join!!!");
		
		resp.sendRedirect(req.getContextPath()+"/index.jsp");
	}

	private void mvJoin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("mvJoin");
		RequestDispatcher dp=req.getRequestDispatcher("/register.jsp");
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

			MemberDto memberInfo=userService.getMemberByCredential(member);
			HttpSession hs=req.getSession();
			System.out.println(memberInfo);
			if(memberInfo==null) {
				hs.setAttribute("loginFaildMsg", "아이디 또는 비밀번호 확인 후 다시 로그인하세요.");
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
		RequestDispatcher dp=req.getRequestDispatcher("/login.jsp");
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
