package ru.kogut.log;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import java.io.Serializable;

/**
 * @author S.Kogut on 03.02.2020
 */
public class Logger implements Serializable {

    @AroundInvoke
    public Object printLog(InvocationContext ctx) throws Exception{
        System.out.println("Вызван метод " + ctx.getMethod().getName());
        return ctx.proceed();
    }

}
