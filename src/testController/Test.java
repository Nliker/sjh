package testController;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/test")
public class Test extends HttpServlet{

	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("test init!");
		super.init(config);
		ServletContext application = getServletContext();
		application.setAttribute("good2","im good2");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("test");
//		System.out.println(getServletContext().getAttribute("good2"));
	}
	

}
