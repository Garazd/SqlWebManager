package ua.com.juja.garazd.sqlwebmanager.integration;

import java.util.List;
import org.mockito.Mockito;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.core.Ordered;
import org.springframework.web.context.support.XmlWebApplicationContext;

public class SpyXmlWebApplicationContext extends XmlWebApplicationContext {

    private static List<String> spies;
    private static List<String> mocks;

    @Override
    protected DefaultListableBeanFactory createBeanFactory() {
        DefaultListableBeanFactory factory = super.createBeanFactory();
        factory.addBeanPostProcessor(new SpyPostProcessor());
        return factory;
    }

    public static void init(List<String> spies, List<String> mocks) {
        SpyXmlWebApplicationContext.spies = spies;
        SpyXmlWebApplicationContext.mocks = mocks;
    }

    class SpyPostProcessor implements BeanPostProcessor, Ordered {

        @Override
        public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
            return bean;
        }

        @Override
        public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
            if (spies.contains(beanName)) {
                return Mockito.spy(bean);
            } else if (mocks.contains(beanName)) {
                return Mockito.mock(bean.getClass());
            } else {
                return bean;
            }
        }

        @Override
        public int getOrder() {
            return Ordered.LOWEST_PRECEDENCE;
        }
    }
}