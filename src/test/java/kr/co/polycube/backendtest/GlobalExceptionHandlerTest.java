package kr.co.polycube.backendtest;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.polycube.backendtest.dto.UserRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class GlobalExceptionHandlerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("잘못된 요청에 대한 예외 처리 통합 테스트")
    void testBadRequest() throws Exception {
        // 잘못된 요청을 보냅니다. (예: ID로 문자열을 사용)
        mockMvc.perform(get("/users/stringId")
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.reason").exists())
                .andDo(print());
    }

    @Test
    @DisplayName("존재하지 않는 API 호출에 대한 예외 처리 통합 테스트")
    void testNotFound() throws Exception {
        // 존재하지 않는 URL 로 요청을 보냅니다
        mockMvc.perform(get("/noneUri")
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.reason").value("Resource not found"))
                .andDo(print());
    }

    @Test
    @DisplayName("validation 예외 처리 통합 테스트")
    void testNotValid() throws  Exception {
        UserRequestDto requestDto = new UserRequestDto();
        // 빈 문자열을 보냅니다.
        requestDto.setName("");

        mockMvc.perform(post("/users")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.reason").exists())
                .andDo(print());
    }

    @Test
    @DisplayName("entity 가 존재하지 않을 때 예외 처리 통합 테스트")
    void testEntityNotFound() throws Exception {
        // 존재하지 않는 userId 로 요청을 보냅니다
        mockMvc.perform(get("/users/999")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.reason").value("해당 사용자가 존재하지 않습니다."))
                .andDo(print());
    }
}
