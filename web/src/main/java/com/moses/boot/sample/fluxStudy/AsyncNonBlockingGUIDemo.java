package com.moses.boot.sample.fluxStudy;


import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static com.moses.boot.sample.fluxStudy.util.StudyUtils.println;

/**
 * 异步非阻塞 + GUI示例
 */
public class AsyncNonBlockingGUIDemo {

    public static void main(String[] args) {
        //Swing
        //创建一个窗口
        JFrame jFrame = new JFrame();

        jFrame.setTitle("简单GUI程序");
        jFrame.setBounds(300, 300, 400, 300);

        //非阻塞.
        //异步. 在关闭窗口时线程被切换 main -> AWT-EventQueue-*
        //Reactive
        jFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e){
                println("销毁当前窗口!");
                jFrame.dispose();   //销毁当前窗口
            }

            @Override
            public void windowClosed(WindowEvent e) {
                println("窗口被关闭,退出程序!");
                System.exit(0);
            }
        });

        //增加鼠标事件监听
        jFrame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                println("当前的鼠标位置, x: " + e.getX() + ", y: " + e.getY());
            }
        });

        println("启动一个JFrame窗口");
        jFrame.setVisible(true);


    }
}
