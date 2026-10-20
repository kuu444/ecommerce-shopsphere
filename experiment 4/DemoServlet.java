import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(
    urlPatterns = "/demo",
    initParams = {
        @WebInitParam(
            name = "greeting",
            value = "Welcome to Java Servlets!"
        )
    }
)
public class DemoServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        System.out.println("Servlet initialized");
    }

    @Override
    protected void service(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("Service method called");
        super.service(request, response);
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {

        String name = request.getParameter("username");

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<h2>GET Request Received</h2>");
        out.println("<p>Name: " + escapeHtml(name) + "</p>");
        out.println("<a href='index.html'>Go Back</a>");
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {

        String name = request.getParameter("username");

        if (name == null || name.trim().isEmpty()) {
            response.sendError(
                HttpServletResponse.SC_BAD_REQUEST,
                "Name is required"
            );
            return;
        }

        HttpSession session = request.getSession();
        session.setAttribute("username", name);

        Cookie cookie = new Cookie("student", "visited");
        cookie.setMaxAge(3600);
        cookie.setHttpOnly(true);
        cookie.setPath(request.getContextPath());
        response.addCookie(cookie);

        String greeting = getServletConfig()
                .getInitParameter("greeting");

        String appName = getServletContext()
                .getInitParameter("appName");

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<h1>" + escapeHtml(greeting) + "</h1>");
        out.println("<p>Hello, " + escapeHtml(name) + "!</p>");
        out.println("<p>Application: "
                + escapeHtml(appName) + "</p>");
        out.println("<p>Session username: "
                + escapeHtml((String) session.getAttribute("username"))
                + "</p>");
        out.println("<p>A cookie has been created.</p>");
        out.println("<a href='index.html'>Go Back</a>");
    }

    private String escapeHtml(String value) {
        if (value == null) {
            return "Not provided";
        }

        return value.replace("&", "&amp;")
                    .replace("<", "&lt;")
                    .replace(">", "&gt;")
                    .replace("\"", "&quot;")
                    .replace("'", "&#x27;");
    }

    @Override
    public void destroy() {
        System.out.println("Servlet destroyed");
    }
}
