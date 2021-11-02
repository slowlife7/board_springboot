package simple.board.filter;

import org.springframework.util.PatternMatchUtils;
import simple.board.SessionConst;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginCheckFilter implements Filter {

    private static final String whiteList[]={"/", "/post","/post/list/*", "/member/signupForm", "/member/signup", "/member/loginForm", "/member/login", "/css/*", "/js/*"};

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();

        HttpServletResponse httpResponse = (HttpServletResponse) response;

        try {
            if(isLoginCheckPath(requestURI)) {
                HttpSession session = httpRequest.getSession(false);
                if(session == null || session.getAttribute(SessionConst.MY_SESSION_ID) == null) {
                    httpResponse.sendRedirect("/member/login?redirectURL="+requestURI);
                    return;
                }
            }
            chain.doFilter(request,response);
        } catch (Exception e){
            throw e;
        }

    }

    private boolean isLoginCheckPath(String requestURI) {
        return !PatternMatchUtils.simpleMatch(whiteList, requestURI);
    }
}
