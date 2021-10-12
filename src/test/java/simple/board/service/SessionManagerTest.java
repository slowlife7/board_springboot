package simple.board.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import simple.board.model.User;

import static org.junit.jupiter.api.Assertions.*;

class SessionManagerTest {

    private SessionManager sessionManager = new SessionManager();

    @Test
    void sessionTest(){
        User user = new User();
        user.setName("billy");
        user.setId("rasgo");
        user.setPw("1234");

        //1.세션 생성
        MockHttpServletResponse response = new MockHttpServletResponse();
        sessionManager.createSession(user, response);

        //2.요청에 응답 쿠키 저장
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setCookies(response.getCookies());

        //3.세션 조회
        Object session = sessionManager.getSession(request);
        Assertions.assertThat(session).isEqualTo(user);

        //4.만료
        sessionManager.expire(request);
        Object expiredSession = sessionManager.getSession(request);
        Assertions.assertThat(expiredSession).isNull();
    }

}