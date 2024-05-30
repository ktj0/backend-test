package kr.co.polycube.backendtest;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.polycube.backendtest.dto.LottoRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.contains;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class LottoIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("lotto 번호 등록 통합 테스트")
    void testRegisterLottoNumbers() throws Exception {
        List<Integer> numbers = Arrays.asList(7, 28, 33, 2, 45, 19);
        LottoRequestDto requestDto = new LottoRequestDto();

        requestDto.setNumbers(numbers);

        mockMvc.perform(post("/lottos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.numbers").isArray())
                .andExpect(jsonPath("$.numbers", contains(7, 28, 33, 2, 45, 19)))
                .andDo(print());
    }
}
