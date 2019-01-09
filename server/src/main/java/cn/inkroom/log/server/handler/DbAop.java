package cn.inkroom.log.server.handler;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 *
 * sql日志，用于打印sql
 *
 * @author 墨盒
 * @Date 19-1-9
 */

public class DbAop implements MethodInterceptor {

    private Logger logger = LoggerFactory.getLogger(getClass());


    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {

        Class<DbConnectFactory> factoryClass = DbConnectFactory.class;
       Field loggerField =  factoryClass.getField("logger");
       loggerField.setAccessible(true);

       Logger log = (Logger) loggerField.get(methodInvocation.getThis());

       //打印参数




        return methodInvocation.proceed();
    }
}
