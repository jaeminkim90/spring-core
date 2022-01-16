package hello.core.beanfind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class ApplicationContextInfoTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = ac.getBean(beanDefinitionName);// object가 꺼내짐으로, getBean을 이용하여 Bean을 꺼낸다
            System.out.println("name = " + beanDefinitionName + " object: " + bean);
        }
    }

    @Test
    @DisplayName("애플리케이션 빈 출력하기")
    void findApplicationBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName); // Bean 메타데이터

            // ROLE.APPLICATION: 개발자가 애플리케이션 개발을 위해 등록한 bean 또는 외부 라이브러리
            // ROLE.INFRASTRUCTURE: 스프링 내부에서 사용하는 bean
            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                Object bean = ac.getBean(beanDefinitionName);// object가 꺼내짐으로, getBean을 이용하여 Bean을 꺼낸다
                System.out.println("name = " + beanDefinitionName + " object: " + bean);
            }
        }
    }
}
