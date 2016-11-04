package com.ptu.tinynews.di.scope;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Qualifier;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by Administrator on 2016/10/16.
 */

@Qualifier
@Documented
@Retention(RUNTIME)
public @interface ContextLife
{
    String value() default "Application";
}
