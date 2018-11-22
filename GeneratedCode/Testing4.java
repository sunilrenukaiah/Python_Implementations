package eu.unicreditgroup.appsec.cast.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringEscapeUtils;
import org.owasp.esapi.ESAPI;
import org.owasp.esapi.errors.EncodingException;

@WebServlet(urlPatterns={"/public/xss"})
public class CrossSiteScripting_CAST extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet_KO_001_out_of_008(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String param = req.getParameter("param");
		
		PrintWriter writer = resp.getWriter();
		writer.write("<html><body>");
		
		writer.write(param); // issue
	}
	protected void doGet_OK_001(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String param = req.getParameter("param");
		
		PrintWriter writer = resp.getWriter();
		writer.write("<html><body>");
		
		writer.write(ESAPI.encoder().encodeForHTML(param)); // no issue
	}
	protected void doGet_OK_002(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String param = req.getParameter("param");
		
		PrintWriter writer = resp.getWriter();
		writer.write("<html><body>");
		writer.write(StringEscapeUtils.escapeHtml4(param)); // no issue
	}
	protected void doGet_KO_002_out_of_008(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String param = req.getParameter("param");
		
		PrintWriter writer = resp.getWriter();
		writer.write("<html><body>");
		writer.write(ESAPI.encoder().encodeForJavaScript(param)); // issue, wrong encoding
	}
	protected void doGet_KO_003_out_of_008(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String param = req.getParameter("param");
		
		PrintWriter writer = resp.getWriter();
		writer.write("<html><body>");
		try {
			writer.write(ESAPI.encoder().encodeForURL(param)); // issue, wrong encoding
		} catch(EncodingException exc) {
			log(exc.getMessage(), exc);
		}
	}
	protected void doGet_KO_004_out_of_008(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String param = req.getParameter("param");
		
		PrintWriter writer = resp.getWriter();
		writer.write("<html><body>");
		writer.write(StringEscapeUtils.escapeJson(param)); // issue, wrong encoding
	}
	protected void doGet_KO_005_out_of_008(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String param = req.getParameter("param");
		
		PrintWriter writer = resp.getWriter();
		writer.write("<html><body>");
		writer.write("<a href='/xss?param=" + param + "'>link</a>"); // issue
	}
	protected void doGet_OK_003(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String param = req.getParameter("param");
		
		PrintWriter writer = resp.getWriter();
		writer.write("<html><body>");
		writer.write("<a href='/xss?param=" + ESAPI.encoder().encodeForHTML(param) + "'>link</a>"); // no issue
	}
	protected void doGet_OK_004(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String param = req.getParameter("param");
		
		PrintWriter writer = resp.getWriter();
		writer.write("<html><body>");
		writer.write("<a href='/xss?param=" + StringEscapeUtils.escapeHtml4(param) + "'>link</a>"); // no issue
	}
	protected void doGet_KO_006_out_of_008(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String param = req.getParameter("param");
		PrintWriter writer = resp.getWriter();
		writer.write("<html><body>");
		writer.write("<a href='/xss?param=" + ESAPI.encoder().encodeForJavaScript(param) + "'>link</a>"); // issue, wrong encoding
	}
	protected void doGet_KO_007_out_of_008(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String param = req.getParameter("param");
		
		PrintWriter writer = resp.getWriter();
		writer.write("<html><body>");
		writer.write("<a href='/xss?param=" + StringEscapeUtils.escapeJson(param) + "'>link</a>"); // issue, wrong encoding
	}
	protected void doGet_KO_008_out_of_008(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String param = req.getParameter("param");
		
		PrintWriter writer = resp.getWriter();
		writer.write("<html><body>");
		try {
			writer.write("<a href='/xss?param=" + ESAPI.encoder().encodeForURL(param) + "'>link</a>"); // issue, wrong encoding
		} catch(EncodingException exc) {
			log(exc.getMessage(), exc);
		}

		writer.write("<html><body>");
		writer.write("</body></html>");
	}
}