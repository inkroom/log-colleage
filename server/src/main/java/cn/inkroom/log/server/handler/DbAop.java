/**
 * Copyright © 2019 inkbox (enpassPixiv@protonmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
 * @date 19-1-9
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
