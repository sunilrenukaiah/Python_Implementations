package eu.unicreditgroup.appsec.cast.test;

Copyright

import java.io.File;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.owasp.esapi.ESAPI;
import org.owasp.esapi.codecs.WindowsCodec;

public class CommandInjection_CAST_TC_by_method extends HttpServlet {
	private static final String COMMAND = "calc.exe";
	private static final String[] COMMAND_A = { "calc.exe" };
	private static final File DIRECTORY = new File("c:/temp");
	private static final String ENV_KEY = "key";
	private static final String ENV_VALUE = "value";
	private static final File FILE = new File(DIRECTORY, "file");
	private static final String[] ENV = { "key=value" };

	//process1.
	public static void main_process1a_KO_001(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process1a(req.getParameter("command")); // issue
	}
	public static void main_process1a_OK_001(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process1a(ESAPI.encoder().encodeForOS(new WindowsCodec(), req.getParameter("command"))); // no issue
	}
	public static void main_process1b_OK_002(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process1b(); // no issue
	}
	//process2.
	public void main_process2a_KO_002(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process2a(req.getParameter("command")); // issue
	}
	public static void main_process2a_OK_003(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process2a(ESAPI.encoder().encodeForOS(new WindowsCodec(), req.getParameter("command"))); // no issue
	}
	public static void main_process2b_OK_004(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process2b(); // no issue
	}
	//process3.
	protected void main_process3a_KO_003(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process3a(req.getParameter("command"), req.getParameter("command2")); // issue
	}
	public static void main_process3a_OK_005(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process3a(ESAPI.encoder().encodeForOS(new WindowsCodec(), req.getParameter("command")), 
				  ESAPI.encoder().encodeForOS(new WindowsCodec(), req.getParameter("command2"))); // no issue
	}
	public static void main_process3b_OK_006(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process3b(); // no issue
	}
	//process4.
	public static void main_process4a_KO_004(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process4a(req.getParameter("command")); // issue
	}
	public static void main_process4a_OK_007(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process4a(ESAPI.encoder().encodeForOS(new WindowsCodec(), req.getParameter("command"))); // no issue
	}
	public static void main_process4b_KO_005(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process4b(req.getParameter("command")); // issue -> CAST 09/10 : do not agree, should be //no issue , because pb.command and pb.redirectError are called with static string, not with tainted input
	}	
	//process5.	
	public static void main_process5a_KO_006(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process5a(req.getParameter("command")); // issue
	}
	public static void main_process5a_OK_008(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process5a(ESAPI.encoder().encodeForOS(new WindowsCodec(), req.getParameter("command"))); // no issue
	}
	public static void main_process5b_OK_009(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process5b(req.getParameter("command")); // no issue
	}
	//process6.	
	public static void main_process6a_KO_007(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process6a(req.getParameter("command")); // issue
	}
	public static void main_process6a_OK_010(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process6a(ESAPI.encoder().encodeForOS(new WindowsCodec(), req.getParameter("command"))); // no issue
	}
	public static void main_process6b_OK_011(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process6b(req.getParameter("command")); // no issue
	}
	public static void main_process6c_KO_008(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process6c(req.getParameter("command")); // issue
	}
	public static void main_process6c_OK_012(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process6c(ESAPI.encoder().encodeForOS(new WindowsCodec(), req.getParameter("command"))); // no issue
	}
	public static void main_process6d_OK_013(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process6d(req.getParameter("command")); // no issue
	}
	//process7.	
	public static void main_process7a_KO_009(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process7a(req.getParameter("command")); // issue
	}
	public static void main_process7a_OK_014(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process7a(ESAPI.encoder().encodeForOS(new WindowsCodec(), req.getParameter("command"))); // no issue
	}
	public static void main_process7b_OK_015(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process7b(req.getParameter("command")); // no issue
	}
	public static void main_process7c_KO_010(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process7c(req.getParameter("command")); // issue
	}
	public static void main_process7c_OK_016(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process7c(ESAPI.encoder().encodeForOS(new WindowsCodec(), req.getParameter("command"))); // no issue
	}
	public static void main_process7d_OK_017(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process7d(req.getParameter("command")); // no issue
	}
	//exec1.
	public static void main_exec1a_KO_011(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		exec1a(req.getParameter("command")); // issue
	}
	public static void main_exec1a_OK_018(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		exec1a(ESAPI.encoder().encodeForOS(new WindowsCodec(), req.getParameter("command"))); // no issue
	}

	public static void main_exec1b_OK_019(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		exec1b(); // no issue
	}
	//exec2.
	public static void main_exec2a_KO_012(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		exec2a(req.getParameter("command")); // issue
	}
	public static void main_exec2a_OK_020(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		exec2a(ESAPI.encoder().encodeForOS(new WindowsCodec(), req.getParameter("command"))); // no issue
	}
	public static void main_exec2b_OK_021(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		exec2b(); // no issue
	}
	public static void main_exec3a_KO_013(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		exec3a(req.getParameter("command"), new String[] { req.getParameter("command2")}); // issue
	}
	//exec3.
	public static void main_exec3a_OK_022(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		exec3a(ESAPI.encoder().encodeForOS(new WindowsCodec(), req.getParameter("command")), new String[] { ESAPI.encoder().encodeForOS(new WindowsCodec(), req.getParameter("command2"))}); // no issue
	}
	public static void main_exec3b_KO_014(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		exec3b(req.getParameter("command"), new String[] { req.getParameter("command2")}); // issue
	}
	public static void main_exec3b_OK_023(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		exec3b(req.getParameter("command"), new String[] { ESAPI.encoder().encodeForOS(new WindowsCodec(), req.getParameter("command2"))}); // no issue
	}	
	public static void main_exec3c_KO_015(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		exec3c(req.getParameter("command"), new String[] { req.getParameter("command2")}); // issue
	}	
	public static void main_exec3c_OK_024(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		exec3c(ESAPI.encoder().encodeForOS(new WindowsCodec(), req.getParameter("command")), new String[] { req.getParameter("command2")}); // no issue
	}	
	public static void main_exec3d_OK_025(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		exec3d(req.getParameter("command"), new String[] { req.getParameter("command")}); // no issue
	}		
	//exec4.
	public static void main_exec4a_KO_016(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		exec4a(req.getParameter("command"), new String[] { req.getParameter("command2")}); // issue
	}
	public static void main_exec3a_OK_026(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		exec4a(ESAPI.encoder().encodeForOS(new WindowsCodec(), req.getParameter("command")), new String[] { ESAPI.encoder().encodeForOS(new WindowsCodec(), req.getParameter("command2"))}); // no issue
	}
	public static void main_exec4b_KO_017(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		exec4b(req.getParameter("command"), new String[] { req.getParameter("command2")}); // issue
	}
	public static void main_exec4b_OK_027(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		exec4b(req.getParameter("command"), new String[] { ESAPI.encoder().encodeForOS(new WindowsCodec(), req.getParameter("command2"))}); // no issue
	}	
	public static void main_exec4c_KO_018(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		exec4c(req.getParameter("command"), new String[] { req.getParameter("command2")}); // issue
	}	
	public static void main_exec4c_OK_028(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		exec4c(req.getParameter("command"), new String[] { ESAPI.encoder().encodeForOS(new WindowsCodec(), req.getParameter("command2"))}); // no issue
	}	
	public static void main_exec4d_KO_019(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		exec4d(req.getParameter("command"), new String[] { req.getParameter("command2")}); // issue
	}
	public static void main_exec4d_OK_029(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		exec4d(ESAPI.encoder().encodeForOS(new WindowsCodec(), req.getParameter("command")), new String[] { req.getParameter("command2")}); // no issue
	}	
	public static void main_exec4e_OK_030(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		exec4e(req.getParameter("command"), new String[] { req.getParameter("command2")}); // no issue
	}	
	public static void main_exec4f_OK_031(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		exec4f(req.getParameter("command"), new String[] { req.getParameter("command2")}); // no issue
	}	
	//exec5.
	public static void main_exec5a_KO_020(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		exec5a(req.getParameter("command"), new String[] { req.getParameter("command2")}, req.getParameter("command3")); // issue
	}
	public static void main_exec5a_OK_032(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		exec5a(ESAPI.encoder().encodeForOS(new WindowsCodec(), req.getParameter("command")), new String[] { ESAPI.encoder().encodeForOS(new WindowsCodec(), req.getParameter("command2"))}, ESAPI.encoder().encodeForOS(new WindowsCodec(), req.getParameter("command3"))); // no issue
	}
	public static void main_exec5b_KO_021(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		exec5b(req.getParameter("command"), new String[] { req.getParameter("command2")}, req.getParameter("command3")); // issue
	}
	public static void main_exec5b_OK_033(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		exec5b(req.getParameter("command"), new String[] { ESAPI.encoder().encodeForOS(new WindowsCodec(), req.getParameter("command2"))}, ESAPI.encoder().encodeForOS(new WindowsCodec(), req.getParameter("command3"))); // no issue
	}	
	public static void main_exec5c_KO_022(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		exec5c(req.getParameter("command"), new String[] { req.getParameter("command2")}, req.getParameter("command3")); // issue
	}	
	public static void main_exec5c_OK_034(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		exec5c(ESAPI.encoder().encodeForOS(new WindowsCodec(), req.getParameter("command")), new String[] { req.getParameter("command2")}, ESAPI.encoder().encodeForOS(new WindowsCodec(), req.getParameter("command3"))); // no issue
	}	
	public static void main_exec5d_KO_023(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		exec5d(req.getParameter("command"), new String[] { req.getParameter("command2")}, req.getParameter("command3")); // issue
	}
	public static void main_exec5d_OK_035(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		exec5d(req.getParameter("command"), new String[] { req.getParameter("command2")}, ESAPI.encoder().encodeForOS(new WindowsCodec(), req.getParameter("command3"))); // no issue
	}	
	public static void main_exec5e_KO_024(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		exec5e(req.getParameter("command"), new String[] { req.getParameter("command2")}, req.getParameter("command3")); // issue
	}	
	public static void main_exec5e_OK_036(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		exec5e(ESAPI.encoder().encodeForOS(new WindowsCodec(),req.getParameter("command")), new String[] { ESAPI.encoder().encodeForOS(new WindowsCodec(),req.getParameter("command2"))}, req.getParameter("command3")); // no issue
	}
	public static void main_exec5f_KO_025(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		exec5f(req.getParameter("command"), new String[] { req.getParameter("command2")}, req.getParameter("command3")); // issue
	}
	public static void main_exec5f_OK_037(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		exec5f(req.getParameter("command"), new String[] { ESAPI.encoder().encodeForOS(new WindowsCodec(), req.getParameter("command2"))}, req.getParameter("command3")); // no issue
	}	
	public static void main_exec5g_KO_026(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		exec5g(req.getParameter("command"), new String[] { req.getParameter("command2")}, req.getParameter("command3")); // issue
	}	
	public static void main_exec5g_OK_038(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		exec5g(ESAPI.encoder().encodeForOS(new WindowsCodec(), req.getParameter("command")), new String[] { req.getParameter("command2")}, req.getParameter("command3")); // no issue
	}
	public static void main_exec5h_OK_039(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		exec5h(req.getParameter("command"), new String[] { req.getParameter("command2")}, req.getParameter("command3")); // no issue
	}
	//exec6.
	public static void main_exec6a_KO_027(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		exec6a(req.getParameter("command"), new String[] { req.getParameter("command2")}, req.getParameter("command3")); // issue
	}
	public static void main_exec6a_OK_040(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		exec6a(ESAPI.encoder().encodeForOS(new WindowsCodec(), req.getParameter("command")), new String[] { ESAPI.encoder().encodeForOS(new WindowsCodec(), req.getParameter("command2"))}, ESAPI.encoder().encodeForOS(new WindowsCodec(), req.getParameter("command3"))); // no issue
	}
	public static void main_exec6b_KO_028(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		exec6b(req.getParameter("command"), new String[] { req.getParameter("command2")}, req.getParameter("command3")); // issue
	}
	public static void main_exec6b_OK_041(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		exec6b(req.getParameter("command"), new String[] { ESAPI.encoder().encodeForOS(new WindowsCodec(), req.getParameter("command2"))}, ESAPI.encoder().encodeForOS(new WindowsCodec(), req.getParameter("command3"))); // no issue
	}	
	public static void main_exec6c_KO_029(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		exec6c(req.getParameter("command"), new String[] { req.getParameter("command2")}, req.getParameter("command3")); // issue
	}	
	public static void main_exec6c_OK_042(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		exec6c(req.getParameter("command"), new String[] { ESAPI.encoder().encodeForOS(new WindowsCodec(), req.getParameter("command2"))}, ESAPI.encoder().encodeForOS(new WindowsCodec(), req.getParameter("command3"))); // no issue
	}	
	public static void main_exec6d_KO_030(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		exec6d(req.getParameter("command"), new String[] { req.getParameter("command2")}, req.getParameter("command3")); // issue
	}
	public static void main_exec6d_OK_043(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		exec6d(ESAPI.encoder().encodeForOS(new WindowsCodec(), req.getParameter("command")), new String[] { req.getParameter("command2")}, ESAPI.encoder().encodeForOS(new WindowsCodec(), req.getParameter("command3"))); // no issue
	}	
	public static void main_exec6e_KO_031(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		exec6e(req.getParameter("command"), new String[] { req.getParameter("command2")}, req.getParameter("command3")); // issue
	}	
	public static void main_exec6e_OK_044(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		exec6e(req.getParameter("command"), new String[] { req.getParameter("command2")}, ESAPI.encoder().encodeForOS(new WindowsCodec(), req.getParameter("command3"))); // no issue
	}
	public static void main_exec6f_KO_032(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		exec6f(req.getParameter("command"), new String[] { req.getParameter("command2")}, req.getParameter("command3")); // issue
	}
	public static void main_exec6f_OK_045(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		exec6f(req.getParameter("command"), new String[] { req.getParameter("command2")}, ESAPI.encoder().encodeForOS(new WindowsCodec(), req.getParameter("command3"))); // no issue
	}	
	public static void main_exec6g_KO_033(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		exec6g(req.getParameter("command"), new String[] { req.getParameter("command2")}, req.getParameter("command3")); // issue
	}	
	public static void main_exec6g_OK_046(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		exec6g(ESAPI.encoder().encodeForOS(new WindowsCodec(),req.getParameter("command")), new String[] { ESAPI.encoder().encodeForOS(new WindowsCodec(),req.getParameter("command2"))}, req.getParameter("command3")); // no issue
	}
	public static void main_exec6g_KO_034(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		exec6h(req.getParameter("command"), new String[] { req.getParameter("command2")}, req.getParameter("command3")); // issue
	}	
	public static void main_exec6h_OK_047(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		exec6h(req.getParameter("command"), new String[] { ESAPI.encoder().encodeForOS(new WindowsCodec(), req.getParameter("command2"))}, req.getParameter("command3")); // no issue
	}
	public static void main_exec6i_KO_035(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		exec6i(req.getParameter("command"), new String[] { req.getParameter("command2")}, req.getParameter("command3")); // issue
	}	
	public static void main_exec6i_OK_048(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		exec6i(req.getParameter("command"), new String[] { ESAPI.encoder().encodeForOS(new WindowsCodec(), req.getParameter("command2"))}, req.getParameter("command3")); // no issue
	}
	public static void main_exec6j_KO_036(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		exec6j(req.getParameter("command"), new String[] { req.getParameter("command2")}, req.getParameter("command3")); // issue
	}
	public static void main_exec6j_OK_049(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		exec6j(ESAPI.encoder().encodeForOS(new WindowsCodec(), req.getParameter("command")), new String[] { req.getParameter("command2")}, req.getParameter("command3")); // no issue
	}	
	public static void main_exec6k_KO_037(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		exec6k(req.getParameter("command"), new String[] { req.getParameter("command2")}, req.getParameter("command3")); // issue
	}	
	public static void main_exec6k_OK_050(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		exec6k(ESAPI.encoder().encodeForOS(new WindowsCodec(), req.getParameter("command")), new String[] { req.getParameter("command2")}, req.getParameter("command3")); // no issue
	}
	public static void main_exec6l_OK_051(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		exec6l(req.getParameter("command"), new String[] { req.getParameter("command2")}, req.getParameter("command3")); // no issue
	}	
	
	
	private static void process1a(String command) throws IOException {
		ProcessBuilder pb = new ProcessBuilder();
		pb.command(command); // issue
		pb.start();
	}

	private static void process1b() throws IOException {
		ProcessBuilder pb = new ProcessBuilder();
		pb.command(COMMAND);
		pb.start();
	}

	private static void process2a(String dir) throws IOException {
		ProcessBuilder pb = new ProcessBuilder();
		pb.command(COMMAND);
		pb.directory(new File(dir)); // issue
		pb.start();
	}

	private static void process2b() throws IOException {
		ProcessBuilder pb = new ProcessBuilder();
		pb.command(COMMAND);
		pb.directory(DIRECTORY);
		pb.start();
	}

	private static void process3a(String envName, String envValue) throws IOException {
		ProcessBuilder pb = new ProcessBuilder();
		pb.command(COMMAND);
		pb.environment().put(envName, envValue); // issue
		pb.start();
	}

	private static void process3b() throws IOException {
		ProcessBuilder pb = new ProcessBuilder();
		pb.command(COMMAND);
		pb.environment().put(ENV_KEY, ENV_VALUE);
		pb.start();
	}

	private static void process4a(String errorFile) throws IOException {
		ProcessBuilder pb = new ProcessBuilder();
		pb.command(COMMAND);
		pb.redirectError(new File(errorFile)); // issue
		pb.start();
	}

	private static void process4b(String errorFile) throws IOException {
		ProcessBuilder pb = new ProcessBuilder();
		pb.command(COMMAND);
		pb.redirectError(FILE);
		pb.start();
	}

	private static void process5a(String errorFile) throws IOException {
		ProcessBuilder pb = new ProcessBuilder();
		pb.command(COMMAND);
		pb.redirectError(Redirect.to(new File(errorFile))); // issue
		pb.start();
	}

	private static void process5b(String errorFile) throws IOException {
		ProcessBuilder pb = new ProcessBuilder();
		pb.command(COMMAND);
		pb.redirectError(Redirect.to(FILE));
		pb.start();
	}

	private static void process6a(String inputFile) throws IOException {
		ProcessBuilder pb = new ProcessBuilder();
		pb.command(COMMAND);
		pb.redirectInput(new File(inputFile)); // issue
		pb.start();
	}

	private static void process6b(String inputFile) throws IOException {
		ProcessBuilder pb = new ProcessBuilder();
		pb.command(COMMAND);
		pb.redirectInput(FILE);
		pb.start();
	}

	private static void process6c(String inputFile) throws IOException {
		ProcessBuilder pb = new ProcessBuilder();
		pb.command(COMMAND);
		pb.redirectInput(Redirect.from(new File(inputFile))); // issue
		pb.start();
	}

	private static void process6d(String inputFile) throws IOException {
		ProcessBuilder pb = new ProcessBuilder();
		pb.command(COMMAND);
		pb.redirectInput(Redirect.from(FILE));
		pb.start();
	}

	private static void process7a(String outputFile) throws IOException {
		ProcessBuilder pb = new ProcessBuilder();
		pb.command(COMMAND);
		pb.redirectOutput(new File(outputFile)); // issue
		pb.start();
	}

	private static void process7b(String outputFile) throws IOException {
		ProcessBuilder pb = new ProcessBuilder();
		pb.command(COMMAND);
		pb.redirectOutput(FILE);
		pb.start();
	}

	private static void process7c(String outputFile) throws IOException {
		ProcessBuilder pb = new ProcessBuilder();
		pb.command(COMMAND);
		pb.redirectOutput(Redirect.to(new File(outputFile))); // issue
		pb.start();
	}

	private static void process7d(String outputFile) throws IOException {
		ProcessBuilder pb = new ProcessBuilder();
		pb.command(COMMAND);
		pb.redirectOutput(Redirect.to(FILE));
		pb.start();
	}

	private static void exec1a(String command) throws IOException {
		Runtime.getRuntime().exec(command); // issue
	}

	private static void exec1b() throws IOException {
		Runtime.getRuntime().exec(COMMAND);
	}

	private static void exec2a(String command) throws IOException {
		Runtime.getRuntime().exec(new String[] { command }); // issue
	}

	private static void exec2b() throws IOException {
		Runtime.getRuntime().exec(new String[] { COMMAND });
	}

	private static void exec3a(String command, String[] env) throws IOException {
		Runtime.getRuntime().exec(command, env); // issue
	}

	private static void exec3b(String command, String[] env) throws IOException {
		Runtime.getRuntime().exec(COMMAND, env); // issue
	}

	private static void exec3c(String command, String[] env) throws IOException {
		Runtime.getRuntime().exec(command, ENV); // issue
	}

	private static void exec3d(String command, String[] env) throws IOException {
		Runtime.getRuntime().exec(COMMAND, ENV);
	}

	private static void exec4a(String command, String[] env) throws IOException {
		Runtime.getRuntime().exec(new String[] { command }, env); // issue
	}

	private static void exec4b(String command, String[] env) throws IOException {
		Runtime.getRuntime().exec(new String[] { COMMAND }, env); // issue
	}

	private static void exec4c(String command, String[] env) throws IOException {
		Runtime.getRuntime().exec(COMMAND_A, env); // issue
	}

	private static void exec4d(String command, String[] env) throws IOException {
		Runtime.getRuntime().exec(new String[] { command }, ENV); // issue
	}

	private static void exec4e(String command, String[] env) throws IOException {
		Runtime.getRuntime().exec(new String[] { COMMAND }, ENV);
	}

	private static void exec4f(String command, String[] env) throws IOException {
		Runtime.getRuntime().exec(COMMAND_A, ENV);
	}

	private static void exec5a(String command, String[] env, String dir) throws IOException {
		Runtime.getRuntime().exec(command, env, new File(dir)); // issue
	}

	private static void exec5b(String command, String[] env, String dir) throws IOException {
		Runtime.getRuntime().exec(COMMAND, env, new File(dir)); // issue
	}

	private static void exec5c(String command, String[] env, String dir) throws IOException {
		Runtime.getRuntime().exec(command, ENV, new File(dir)); // issue
	}

	private static void exec5d(String command, String[] env, String dir) throws IOException {
		Runtime.getRuntime().exec(COMMAND, ENV, new File(dir)); // issue
	}

	private static void exec5e(String command, String[] env, String dir) throws IOException {
		Runtime.getRuntime().exec(command, env, DIRECTORY); // issue
	}

	private static void exec5f(String command, String[] env, String dir) throws IOException {
		Runtime.getRuntime().exec(COMMAND, env, DIRECTORY); // issue
	}

	private static void exec5g(String command, String[] env, String dir) throws IOException {
		Runtime.getRuntime().exec(command, ENV, DIRECTORY); // issue
	}

	private static void exec5h(String command, String[] env, String dir) throws IOException {
		Runtime.getRuntime().exec(COMMAND, ENV, DIRECTORY);
	}

	private static void exec6a(String command, String[] env, String dir) throws IOException {
		Runtime.getRuntime().exec(new String[] { command }, env, new File(dir)); // issue
	}

	private static void exec6b(String command, String[] env, String dir) throws IOException {
		Runtime.getRuntime().exec(new String[] { COMMAND }, env, new File(dir)); // issue
	}

	private static void exec6c(String command, String[] env, String dir) throws IOException {
		Runtime.getRuntime().exec(COMMAND_A, env, new File(dir)); // issue
	}

	private static void exec6d(String command, String[] env, String dir) throws IOException {
		Runtime.getRuntime().exec(new String[] { command }, ENV, new File(dir)); // issue
	}

	private static void exec6e(String command, String[] env, String dir) throws IOException {
		Runtime.getRuntime().exec(new String[] { COMMAND }, ENV, new File(dir)); // issue
	}

	private static void exec6f(String command, String[] env, String dir) throws IOException {
		Runtime.getRuntime().exec(COMMAND_A, ENV, new File(dir)); // issue
	}

	private static void exec6g(String command, String[] env, String dir) throws IOException {
		Runtime.getRuntime().exec(new String[] { command }, env, DIRECTORY); // issue
	}

	private static void exec6h(String command, String[] env, String dir) throws IOException {
		Runtime.getRuntime().exec(new String[] { COMMAND }, env, DIRECTORY); // issue
	}

	private static void exec6i(String command, String[] env, String dir) throws IOException {
		Runtime.getRuntime().exec(COMMAND_A, env, DIRECTORY); // issue
	}

	private static void exec6j(String command, String[] env, String dir) throws IOException {
		Runtime.getRuntime().exec(new String[] { command }, ENV, DIRECTORY); // issue
	}

	private static void exec6k(String command, String[] env, String dir) throws IOException {
		Runtime.getRuntime().exec(new String[] { COMMAND }, ENV, DIRECTORY);
	}

	private static void exec6l(String command, String[] env, String dir) throws IOException {
		Runtime.getRuntime().exec(COMMAND_A, ENV, DIRECTORY);
	}
}