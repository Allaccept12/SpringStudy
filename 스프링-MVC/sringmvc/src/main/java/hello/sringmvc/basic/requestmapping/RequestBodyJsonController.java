package hello.sringmvc.basic.requestmapping;


import com.fasterxml.jackson.databind.ObjectMapper;
import hello.sringmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyJsonController {

    private ObjectMapper objectMapper = new ObjectMapper();


    @PostMapping("/request-body-json-v1")
    public void requestBodyJsonV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String body = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("body = {}",body);
        HelloData helloData = objectMapper.readValue(body, HelloData.class);
        log.info("username = {} , age = {}", helloData.getUsername(),helloData.getAge());

        response.getWriter().write("ok");
    }

    @ResponseBody
    @PostMapping("/request-body-json-v2")
    public String requestBodyJsonV2(@RequestBody String body) throws IOException {

        log.info("body = {}",body);
        HelloData helloData = objectMapper.readValue(body, HelloData.class);
        log.info("username = {} , age = {}", helloData.getUsername(),helloData.getAge());
        return "ok";
    }

    @ResponseBody
    @PostMapping("/request-body-json-v3")
    public String requestBodyJsonV3(@RequestBody HelloData helloDatas)  {
        log.info("username = {} , age = {}", helloDatas.getUsername(),helloDatas.getAge());
        return "ok";
    }

    @ResponseBody
    @PostMapping("/request-body-json-v4")
    public String requestBodyJsonV4(@RequestBody HttpEntity<HelloData> data)  {
        HelloData helloData = data.getBody();
        log.info("username = {} , age = {}", helloData.getUsername(),helloData.getAge());
        return "ok";
    }
    @ResponseBody
    @PostMapping("/request-body-json-v5")
    public HelloData requestBodyJsonV5(@RequestBody HelloData data)  {
        log.info("username = {} , age = {}", data.getUsername(),data.getAge());
        return data;
    }

}

















