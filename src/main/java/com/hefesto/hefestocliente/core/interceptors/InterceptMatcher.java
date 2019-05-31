/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hefesto.hefestocliente.core.interceptors;

import com.google.inject.matcher.AbstractMatcher;
import com.hefesto.hefestocliente.core.annotations.Intercept;
import java.lang.reflect.Method;

/**
 *
 * @author flavio
 */
public class InterceptMatcher extends AbstractMatcher<Method>{

    @Override
    public boolean matches(Method t) {
        return t.isAnnotationPresent(Intercept.class);
    }
    
}
