package spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class Spring implements ApplicationContextAware {

    private static ApplicationContext appContext;
    public static <T> T get (Class<T> beanClass) {
        return appContext.getBean(beanClass);
    }

    public static <T> T get (String beanName) {
        return (T) appContext.getBean(beanName);
    }
    @Override
    public void setApplicationContext (ApplicationContext applicationContext) throws BeansException {
        appContext = applicationContext;
    }
}
