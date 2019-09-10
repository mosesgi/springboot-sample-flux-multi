package com.moses.boot.sample.fluxStudy;

import java.util.Observable;

import static com.moses.boot.sample.fluxStudy.util.StudyUtils.println;

/**
 * 学习ReactiveX的例子, 观察者模型
 * http://reactivex.io/intro.html
 * 同步+非阻塞
 */
public class ObserverPatternDemo {
    public static void main(String[] args) {
        //同步非阻塞: 基本上采用Callback形式
        //当前实现: 同步+非阻塞
        //数据发布中心
        MyObservable observable = new MyObservable();

        println("Observable 添加观察者!");

        // 1 observable : N observer
        observable.addObserver((o, arg) -> {
            println("1 收到数据更新: " + arg);
        });
        observable.addObserver((o, arg) -> {
            println("2 收到数据更新: " + arg);
        });
        observable.addObserver((o, arg) -> {
            println("3 收到数据更新: " + arg);
        });

        println("Observable 通知观察者.");

        observable.setChanged();
        observable.notifyObservers("Hello, world"); //发布数据 - push data
    }


    public static class MyObservable extends Observable {
        public synchronized void setChanged(){
            super.setChanged();
        }
    }
}
