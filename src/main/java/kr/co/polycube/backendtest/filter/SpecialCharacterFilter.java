package kr.co.polycube.backendtest.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.regex.Pattern;

@Component
public class SpecialCharacterFilter implements Filter {
    private static final Pattern SPECIAL_CHAR_PATTERN = Pattern.compile("[^a-zA-Z0-9?&=:/]");

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String requestURI = httpRequest.getRequestURI();
        String queryString = httpRequest.getQueryString();

        // h2 콘솔에 접속하기 위함
        if (requestURI.startsWith("/h2-console")) {
            chain.doFilter(request, response);

            return;
        }

        if (containsSpecialCharacter(requestURI) || (queryString != null && containsSpecialCharacter(queryString))) {
            sendErrorResponse(httpResponse, "Invalid characters in URL");

            return;
        }

        chain.doFilter(request, response);
    }

    private boolean containsSpecialCharacter(String input) {
        return SPECIAL_CHAR_PATTERN.matcher(input).find();
    }

    // URI 에 특수문자 입력 시 예외 처리
    private void sendErrorResponse(HttpServletResponse response, String message) throws IOException {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{\"reason\": \"" + message + "\"}");
    }
}
