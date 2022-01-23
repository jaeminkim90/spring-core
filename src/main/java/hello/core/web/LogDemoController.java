package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor // 자동으로 필드 변수 의존 관계를 주입하는 생성자를 만들어 준다.
public class LogDemoController {

    private final LogDemoService logDemoService;
    private final MyLogger myLogger;
    private static ApplicationContext ac;


    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request)  {
        String requestURL = request.getRequestURI().toString();

        // 프록시가 적용된 myLogger 클래스 정보 확인
        System.out.println("myLogger.getClass() = " + myLogger.getClass());
        ApplicationContext ac = new AnnotationConfigApplicationContext(MyLogger.class);

        // getBean()으로 프록시가 적용된 myLogger 클래스 정보 확인
        MyLogger myLogger = ac.getBean("myLogger", MyLogger.class);
        System.out.println("ac.getBean(myLogger) = " + myLogger.getClass());

        this.myLogger.setRequestURL(requestURL);
        this.myLogger.log("controller test");
        logDemoService.logic("testId");
        return  "OK";
    }
}
