package kr.co.polycube.backendtest.aop;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
@RequiredArgsConstructor
@Slf4j(topic = "Client Agent inquiry")
public class LoggingAspect {
    private final HttpServletRequest request;

    @Before("execution(* kr.co.polycube.backendtest.controller.UserController.createUser(..)) || " +
            "execution(* kr.co.polycube.backendtest.controller.UserController.getUser(..)) || " +
            "execution(* kr.co.polycube.backendtest.controller.UserController.updateUser(..))")
    public void logClientAgent() {
        String clientAgent = request.getHeader("User-Agent");

        log.info("Client Agent: " + clientAgent);
    }
}
