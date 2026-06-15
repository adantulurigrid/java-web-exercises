package com.bobocode;

import com.bobocode.annotation.Trimmed;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class TrimmedAnnotationBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName)
            throws BeansException {

        boolean hasTrimmedMethod = false;

        for (Method method : bean.getClass().getDeclaredMethods()) {
            for (Parameter parameter : method.getParameters()) {
                if (parameter.isAnnotationPresent(Trimmed.class)) {
                    hasTrimmedMethod = true;
                    break;
                }
            }
        }

        if (!hasTrimmedMethod) {
            return bean;
        }

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(bean.getClass());

        enhancer.setCallback((MethodInterceptor) (obj, method, args, proxy) -> {

            Parameter[] parameters = method.getParameters();

            if (args != null) {
                for (int i = 0; i < args.length; i++) {
                    if (parameters[i].isAnnotationPresent(Trimmed.class)
                            && args[i] instanceof String str) {
                        args[i] = str.trim();
                    }
                }
            }

            return proxy.invoke(bean, args);
        });

        return enhancer.create();
    }
}