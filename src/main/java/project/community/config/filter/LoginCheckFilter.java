//package project.community.config;
//
//import org.apache.catalina.session.StandardSessionFacade;
//import org.springframework.util.PatternMatchUtils;
//
//import javax.servlet.*;
//import javax.servlet.FilterConfig;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//
//public class LoginCheckFilter implements Filter {
//    private static final String[] blacklist = {"/write", "/update", "/delete", "/member"};
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//    }
//
//    private boolean isLoginCheckPath(String requestURI){
//        return PatternMatchUtils.simpleMatch(blacklist, requestURI);
//    }
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//            throws IOException, ServletException {
//        System.out.println("체인 시작");
//
//        HttpServletRequest httpRequest = (HttpServletRequest) request;
//        HttpServletResponse httpResponse = (HttpServletResponse) response;
//        String requestURI = httpRequest.getRequestURI();
//
//        System.out.println(requestURI);
//        System.out.println(isLoginCheckPath(requestURI) + "true면 인증이 필요한 url");
//        if(isLoginCheckPath(requestURI)){//true면 로그인 해야 사용 가능
//            System.out.println("if문 시작");
//
//            HttpSession session = httpRequest.getSession(false);
//            System.out.println("session에 유저 정보가 있는지 확인: " + session.getAttribute("user"));
//            System.out.println(session);
//
//            if(session.getAttribute("user") == null || session == null) {
//                System.out.println("세션이 null이면 로그인페이지로 + 필터걸린 uri 기억");
//                session.setAttribute("previousUrl", requestURI);
//                System.out.println(session.getAttribute("previousUrl"));
//                httpResponse.sendRedirect("/login");
//                return;
//            }
//        }
//        System.out.println("체인 끝");
//        chain.doFilter(request, response);
//    }
//    @Override
//    public void destroy() {}
//}
