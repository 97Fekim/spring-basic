package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingletonTest {
    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer(){
        AppConfig appConfig = new AppConfig();
        // 1. 조회 : 호출할 때 마다 객체를 생성
        MemberService memberService1 = appConfig.MemberService();
        MemberService memberService2 = appConfig.MemberService();

        // 참조값이 다른 것을 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService1 = " + memberService2);

        // memberService1 != memberService2
        Assertions.assertThat(memberService1).isNotSameAs(memberService2);
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest(){
        SingletonService instance1 = SingletonService.getInstance();
        SingletonService instance2 = SingletonService.getInstance();
        Assertions.assertThat(instance1).isSameAs(instance2);
    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainerSingletonTest(){
        ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(AppConfig.class);

        MemberRepository memberRepository1 =
                applicationContext.getBean("memberRepository", MemberRepository.class);
        MemberRepository memberRepository2 =
                applicationContext.getBean("memberRepository", MemberRepository.class);

        Assertions.assertThat(memberRepository1).isSameAs(memberRepository2);

    }

    @Test
    @DisplayName("스프링이 제공하는 싱글톤 컨테이너")
    void springSingletonContainer(){
        ApplicationContext annotationConfigApplicationContext
                = new AnnotationConfigApplicationContext(AppConfig.class);

        OrderService os = annotationConfigApplicationContext.getBean("OrderService", OrderService.class);
        MemberService ms = annotationConfigApplicationContext.getBean("MemberService", MemberService.class);

        // test

    }

    @Test
    void configurationDeep(){
        ApplicationContext ac
                = new AnnotationConfigApplicationContext(AppConfig.class);

        AppConfig bean = ac.getBean(AppConfig.class);

        System.out.println("bean = " + bean.getClass());
    }
}
