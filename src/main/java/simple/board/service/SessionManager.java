package simple.board.service;

import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.http.HttpRequest;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SessionManager {

    static final String MY_SESSION_ID = "mySessionId";

    private Map<String, Object> sessionStore = new ConcurrentHashMap<>();

    public void createSession(Object value, HttpServletResponse response) {
        String sessionId = UUID.randomUUID().toString();

        sessionStore.put(sessionId, value);

        Cookie sessionCookie = new Cookie(MY_SESSION_ID, sessionId);
        response.addCookie(sessionCookie);
    }

    public Object getSession(HttpServletRequest request) {

        Cookie sessionCookie = findSession(request, MY_SESSION_ID);
        if(sessionCookie == null){
            return null;
        }

        return sessionStore.get(sessionCookie.getValue());
    }

    public void expire(HttpServletRequest request) {
        Cookie findCookie = findSession(request, MY_SESSION_ID);
        if(findCookie != null) {
            sessionStore.remove(findCookie.getValue());
        }
    }

    private Cookie findSession(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();
        if(cookies == null){
            return null;
        }

        return Arrays.stream(cookies)
                .filter(c -> c.getName().equals(cookieName))
                .findAny().orElse(null);
    }
}
