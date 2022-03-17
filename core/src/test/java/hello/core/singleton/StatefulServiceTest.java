package hello.core.singleton;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton(){
        AnnotationConfigApplicationContext ac
                = new AnnotationConfigApplicationContext(TestConfig.class);

        // clientA
        StatefulService s1 = ac.getBean("statefulService", StatefulService.class);
        // clientB
        StatefulService s2 = ac.getBean("statefulService", StatefulService.class);
        org.assertj.core.api.Assertions.assertThat(s1.order("a",1)).isNotEqualTo(s2.order("b",2));

    }

    static class TestConfig{
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}