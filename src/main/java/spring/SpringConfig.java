package spring;

import driver.CustomFieldDecorator;
import driver.SingletonDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.SimpleThreadScope;
import org.springframework.stereotype.Component;
import spring.annotations.Block;
import spring.annotations.PageObject;

@Configuration
@ComponentScan(basePackages = {"pages", "blocks", "api", "service"})
public class SpringConfig{
    @Bean
    public static BeanFactoryPostProcessor beanFactoryPostProcessor(){
        return new CustomScopeRegisteringBeanFactoryPostProcessor();
    }

    public static class CustomScopeRegisteringBeanFactoryPostProcessor implements BeanFactoryPostProcessor{
        @Override
        public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory){
            configurableListableBeanFactory.registerScope("thread", new SimpleThreadScope());
        }
    }

    @Component
    public class CustomBeanPostProcessor implements BeanPostProcessor{
        @Override
        public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException{
            if(bean.getClass().isAnnotationPresent(PageObject.class) || bean.getClass().isAnnotationPresent(Block.class))
                PageFactory.initElements(new CustomFieldDecorator(new DefaultElementLocatorFactory(SingletonDriver.getDriver())), bean);
            return bean;
        }
    }
}
