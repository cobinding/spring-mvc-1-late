package hello.springmvc.basic.request;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={}, age={}", username, age);
        response.getWriter().write("ok");
    }

    // 클래스 레벨에 @Controller가 있으면 뷰리졸버를 반환하기 때문에 ResponseBody를 통해 Json 담아주기
    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(@RequestParam("username") String userName,
        @RequestParam("age") int memberAge) {
            log.info("userName={}, memberAge={}", userName, memberAge);
            return "ok";
    }

    @RequestMapping("/request-param-v3")
    // 변수명과 파라미터명을 똑같이 하면 RequestParam("여기") 생략 가능
    public String requestParamV3(@RequestParam String username,
        @RequestParam int age) {
        log.info("userName={}, memberAge={}", username, age);
        return "ok";
    }

    @RequestMapping("/request-param-v4")
    // 객체 말고 String, int, Integer 이런 단순 타입은 완전 생략 가능!!
    public String requestParamV4(String username, int age) {
        log.info("userName={}, memberAge={}", username, age);
        return "ok";
    }

    @RequestMapping("/request-param-required")
    public String requestParamRequired(
        // required를 false로 하면 파라미터 생략가능. dafault는 true라서 반드시 들어오게끔 세팅
        @RequestParam(required = false) String username,
        @RequestParam int age) {
        // int형(primitive)을 false로 할 땐 Integer(객체)를 써서 null을 받을 수 있도록 설정해줘야함
        log.info("userName={}, memberAge={}", username, age);
        return "ok";
    }

    @RequestMapping("/request-param-default")
    // 빈문자 처리도 가능
    public String requestParamDefault(
        @RequestParam(defaultValue = "quest") String username,
        @RequestParam(required = false, defaultValue = "-1") int age) {

        log.info("userName={}, memberAge={}", username, age);
        return "ok";
    }

    // 파라미터 Map으로 조회
    @RequestMapping("/request-param-map")
    // 빈문자 처리도 가능
    public String requestParamMap(
       @RequestParam Map<String, Object> paramMap) {
        log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age"));
        return "ok";
    }
}
