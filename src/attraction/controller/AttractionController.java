package attraction.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import attraction.dto.SidoDto;
import attraction.service.AttractionService;
import attraction.service.AttractionServiceImpl;

@WebServlet("/attraction")
public class AttractionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AttractionService attrService;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		attrService = AttractionServiceImpl.getInstance();
		
		// ServletContext 객체를 가져옵니다.
        ServletContext context = getServletContext();
        
        // 웹 세션을 얻어옵니다.
//        HttpSession session = context.getSession(true); // true를 사용하여 세션이 없을 경우 새로운 세션을 생성합니다.
        List<SidoDto> areaList = attrService.getAreaList();
        
        // 세션에 데이터를 저장합니다.
        context.setAttribute("areaList", areaList);
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		HttpSession session = req.getSession();
		
		
	}
}
