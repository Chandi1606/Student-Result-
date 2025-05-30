
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mvc.Model;

@WebServlet("/GetResult")
public class GetResult extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String temp=request.getParameter("usn");
		Model m = new Model();
		m.setUsn(temp);
		m.getResult();
		
		HttpSession hs=request.getSession(true);
		hs.setAttribute("USN",m.getUsn());
		hs.setAttribute("NAME",m.getName());
		hs.setAttribute("MARKS1",m.getMarks1());
		hs.setAttribute("MARKS2",m.getMarks2());
		hs.setAttribute("MARKS3",m.getMarks3());
		hs.setAttribute("PERCENT",m.getPercentage());
		
		response.sendRedirect("/10-mvc/disp.jsp");
		m.closeConnection();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
