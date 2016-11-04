package com.ptu.tinynews.util;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * Created by Administrator on 2016/10/14.
 */

public class RxBus
{
    private final Subject<Object, Object> bus;

    //PublishSubject只会把在订阅发生的事件点之后来自原始Observable的数据发送给观察者
    public RxBus()
    {
        bus = new SerializedSubject<>(PublishSubject.create());
    }

    private static class RxBusHolder
    {
        private static final RxBus sInstance = new RxBus();
    }

    public static RxBus getDefault()
    {
        return RxBusHolder.sInstance;
    }

    //提供一个新的事件
    public void post(Object o)
    {
        bus.onNext(o);
    }

    //根据传递的eventType类型返回特定类型(eventType)的被观察者
    public <T> Observable<T> toObservable(Class<T> eventType)
    {
        return bus.ofType(eventType);
    }
}
