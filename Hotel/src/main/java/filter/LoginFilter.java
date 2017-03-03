package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {

        @Override
        public void destroy() {}

        @Override
        public void doFilter(ServletRequest requset,
                        ServletResponse response, FilterChain chain)
                        throws IOException, ServletException {
                HttpServletRequest req = (HttpServletRequest) requset;
                HttpSession session = req.getSession();
                if (session == null || session.getAttribute("id") == null) {
                        HttpServletResponse resp = (HttpServletResponse) response;
                        resp.sendRedirect("/Hotel/index.jsp");
                }
                chain.doFilter(requset, response);
        }

        @Override
        public void init(FilterConfig arg0) throws ServletException {}

}
