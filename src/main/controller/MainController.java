package main.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import attraction.dto.SidoDto;
import attraction.service.AttractionService;
import attraction.service.AttractionServiceImpl;

@WebServlet("/main")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AttractionService attrService;

	public void init(ServletConfig config) throws ServletException {
		System.out.println("hi");
		super.init(config);
		attrService = AttractionServiceImpl.getInstance();
		
        ServletContext context = getServletContext();
        
        List<SidoDto> areaList = attrService.getAreaList();
        
        context.setAttribute("areaList", areaList);
        System.out.println(context.getAttribute("areaList"));
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {		
		req.getRequestDispatcher("/main/main.jsp").forward(req, resp);
	}
}
