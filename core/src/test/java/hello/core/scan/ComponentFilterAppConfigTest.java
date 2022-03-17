package hello.core.scan;

import hello.core.AutoAppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import java.lang.annotation.Target;

public class ComponentFilterAppConfigTest {
    @Test
    void filterScan(){
        ApplicationContext ac =
                new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);

        BeanA beanA = ac.getBean("beanA", BeanA.class);
        Assertions.assertThat(beanA).isNotNull();

        //ac.getBean("beanB", BeanB.class);
    }

    @Configuration
    @ComponentScan(
            includeFilters = @ComponentScan.Filter(
                    type = FilterType.ANNOTATION, classes = MyIncludeComponent.class
            ),
            excludeFilters = @ComponentScan.Filter(
                    type = FilterType.ANNOTATION, classes = MyExcludeComponent.class
            )
    )
    static class ComponentFilterAppConfig {

    }
}
