/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hefesto.hefestocliente.core.interceptors;

import com.hefesto.hefestocliente.core.Icone;
import com.hefesto.hefestocliente.core.Messages;
import com.hefesto.hefestocliente.core.StatisticLogger;
import java.awt.Cursor;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.perf4j.StopWatch;
import org.perf4j.javalog.JavaLogStopWatch;

/**
 *
 * @author flavio
 */
public class InvokerInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invoker) throws Throwable {
        Icone icone = Icone.getInstance();
        icone.mudaCursor(Cursor.WAIT_CURSOR);
        StopWatch stop = null;
        Long t1 = System.currentTimeMillis();
        invoker.getMethod().setAccessible(true);
        Object retorno = null;
        if (Messages.getMessage("performance").equalsIgnoreCase("F")) {
            synchronized (this) {
                stop = new JavaLogStopWatch(invoker.getMethod().getDeclaringClass().getSimpleName() + "." + invoker.getMethod().getName(),
                        StatisticLogger.getLogger());
                retorno = invoker.proceed();
                stop.stop();
            }
        } else {
            retorno = invoker.proceed();
            Long t2 = System.currentTimeMillis();
            String metodoLog = "Metodo: " + invoker.getMethod().getDeclaringClass().getSimpleName() + "." + invoker.getMethod().getName()
                    + " ------------> Chamado em " + (t2 - t1) + "ms";
            t1 = null;
            t2 = null;
            System.out.println(metodoLog);
        }
        icone.mudaCursor(Cursor.DEFAULT_CURSOR);
        icone = null;
        return retorno;
    }

}
