package project.community.config;

import org.springframework.util.PatternMatchUtils;

import javax.servlet.*;
import javax.servlet.FilterConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginCheckFilter implements Filter {
    private static final String[] whitelist = {"/index", "/singIn", "/login", "/logout", "/js/*", "/css/*", "/img/*", "/boardpaging", "/posting"};
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    private boolean isLoginCheckPath(String requestURI) {
        return PatternMatchUtils.simpleMatch(whitelist, requestURI);
    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        System.out.println("HHHHHHHHHHHHHHHHHHHHHHHH");

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String requestURI = httpRequest.getRequestURI();

        if(!isLoginCheckPath(requestURI)){//true면 로그인 해야 사용 가능
            HttpSession session = httpRequest.getSession(false);

            if(session == null) {
                httpResponse.sendRedirect("/login");
                return;
            }
        }
        chain.doFilter(request, response);
    }
    @Override
    public void destroy() {}
}
