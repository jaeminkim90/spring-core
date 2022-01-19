package hello.core.singletonTest;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class ConfigurationSingletonTest {

    @Test
    void configurationTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        // MemberRepository를 생성하는 두 Service 구현체를 꺼내온다.
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        // AppConfig에서 생성되는 MemberRepository 구현체도 꺼내온다.
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        // 각각의 구현체가 필드 변수에 가지고 있는 memberRepository 객체를 조회한다.
        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();

        // 세 가지 방식으로 생성된 MemberRepository 구현체의 참조값을 비교한다.
        System.out.println("memberServiceImpl -> memberRepository1 = " + memberRepository1);
        System.out.println("orderServiceImpl -> memberRepository2 = " + memberRepository2);
        System.out.println("memberRepository = " + memberRepository);

        // 세 객체의 참조값이 동일한지 검증한다.
        assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
        assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);
    }
}

