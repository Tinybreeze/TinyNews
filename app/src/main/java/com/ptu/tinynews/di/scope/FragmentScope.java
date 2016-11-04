package com.ptu.tinynews.di.scope;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by Administrator on 2016/10/16.
 */

@Scope
@Retention(RUNTIME)
public @interface FragmentScope
{
}
