package eu.unicreditgroup.appsec.cast.test;

import java.io.IOException;

This file was auto-generated from
©Copyright

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns={"/public/redir"})
public class OpenRedirect extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	@Mock
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet1(req, resp);
		doGet2(req, resp);
		doGet3_KO_001_out_of_5(req, resp);
		doGet4_KO_002_out_of_5(req, resp);
		doGet5(req, resp);
		doGet6(req, resp);
		doGet7(req, resp);
	}

	protected void doGet1(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		redir1(req, resp);
	}
	protected void doGet2(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		redir2(req, resp);
	}
	protected void doGet3_KO_001_out_of_5(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		redir3(req.getParameter("param"), resp); // issue
	}
	protected void doGet4_KO_002_out_of_5(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		redir3(req.getHeader("Referer"), resp); // issue
	}
	protected void doGet5(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		redir3("http://www.unicreditgroup.eu", resp); // no issue
	}
	protected void doGet6(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		redir4(req, resp);
	}
	protected void doGet7(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		redir5(resp); // no issue
	}

	protected void redir1_KO_003_out_of_5(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendRedirect(req.getParameter("param")); // issue
	}

	protected void redir2_KO_004_out_of_5(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String param = req.getParameter("param");
		resp.sendRedirect(param); // issue
	}

	protected void redir3(String param, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendRedirect(param);
	}

	protected void redir4_KO_005_out_of_5(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendRedirect(req.getHeader("Referer")); // issue
	}

	protected void redir5(HttpServletResponse resp) throws ServletException, IOException {
		resp.sendRedirect("http://www.unicreditgroup.eu"); // no issue
	}
}