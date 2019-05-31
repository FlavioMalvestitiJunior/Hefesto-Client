/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hefesto.hefestocliente.core.interceptors;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;

/**
 *
 * @author flavio
 */
public class InterceptorModule extends AbstractModule {

    @Override
    protected void configure() {
        bindInterceptor(Matchers.any(), new InterceptMatcher(), new InvokerInterceptor());
    }
    
}
