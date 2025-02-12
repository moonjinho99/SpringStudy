package com.example.nloginTest.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


@RequiredArgsConstructor
@Slf4j
@Controller
public class LoginController {

    private final RestTemplate restTemplate;

    @GetMapping("/login/page")
    public String loginPage()
    {
        return "loginPage";
    }

    @GetMapping("/login/oauth2/code/naver")
    public void callback(@RequestParam("code") String code, @RequestParam("state") String state, HttpServletResponse response) {
        log.info("Callback received: code = {}, state = {}", code, state);

        // 다른 백엔드 API에 요청을 보내는 코드
        String backendUrl = "http://localhost:8787/oauth/naver";  // 다른 백엔드의 API URL
        //String backendUrl = "https://tutor-tutee.shop/oauth/naver";
        // 요청 본문 데이터 설정
        Map<String, String> requestData = new HashMap<>();
        requestData.put("code", code);
        requestData.put("state", state);

        // 백엔드로 POST 요청 보내기
        try {
            ResponseEntity<String> backendResponse = restTemplate.postForEntity(backendUrl, requestData, String.class);
            log.info("Response from backend: {}", backendResponse.getBody());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
