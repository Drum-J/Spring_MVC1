package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));


        response.getWriter().write("ok");
    }

    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam("username") String memberName,
            @RequestParam("age") int memberAge) {
        log.info("username ={}, age={}", memberName, memberAge);
        return "ok";

    }

    //@RequestParam에 괄호를 생략하려면 파라미터 네임이랑 변수 이름을 같이 해주면 된다.
    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            @RequestParam String username,
            @RequestParam int age) {
        log.info("username ={}, age={}", username, age);

        return "ok";

    }

    //@RequestParam 자체를 생략할 수도 있다, 마찬가지로 변수랑 파라미터 이름을 일치시켜야 한다.
    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age) {
        log.info("username ={}, age={}", username, age);

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            //true일 때, 꼭 들어와야하는 파라미터이다.
            @RequestParam(required = true) String username,
            @RequestParam(required = false) Integer age) {
        /* int age 가 아닌 Integer age 를 사용해야하는 이유는,
        int는 null이 될 수 없는 자바 기본형이고 Integer는 null이 가능한 객체형이기 때문이라고 한다.
        면접 질문에서도 공부했던 기본형은 null이 될 수 없다. 를 기억하자!!
        int age인 상태에서 username 만 넘기게 되면 500 에러가 뜬다.
        */
        log.info("username ={}, age={}", username, age);

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            //true일 때, 꼭 들어와야하는 파라미터이다. defaultValue가 있게되면 사실 required는 없어도 된다.
            @RequestParam(required = true, defaultValue = "guest") String username,
            @RequestParam(required = false, defaultValue = "-1") int age) {

        log.info("username ={}, age={}", username, age);

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap) {

        log.info("username ={}, age={}", paramMap.get("username"), paramMap.get("age"));
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData) {
        log.info("username ={}, age= {}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData helloData) {
        log.info("username ={}, age= {}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }
}
