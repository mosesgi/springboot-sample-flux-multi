package com.moses.boot.sample.fluxStudy;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.moses.boot.sample.fluxStudy.util.StudyUtils.println;

/**
 * 异步+非阻塞 Servlet
 * 详见web module下同名类
 */
@WebServlet(name = "async-non-blocking", urlPatterns = "/async-non-blocking", asyncSupported = true)
public class AsyncNonBlockingServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AsyncContext asyncContext = req.startAsync();
        println("异步上下文执行开始");

        //非阻塞回调
        asyncContext.addListener(new AsyncListener() {
            @Override
            public void onComplete(AsyncEvent event) throws IOException {
                ServletResponse response = event.getSuppliedResponse();
                response.getOutputStream().println("Hello world 1");
                println("异步上下文执行完毕!");
            }

            @Override
            public void onTimeout(AsyncEvent asyncEvent) throws IOException {

            }

            @Override
            public void onError(AsyncEvent asyncEvent) throws IOException {

            }

            @Override
            public void onStartAsync(AsyncEvent asyncEvent) throws IOException {

            }
        });

        //非阻塞IO
        ServletOutputStream outputStream = resp.getOutputStream();
        outputStream.setWriteListener(new WriteListener() {
            @Override
            public void onWritePossible() throws IOException {
                outputStream.println("Hello world 2");
                println("异步+非阻塞上下文执行完毕!");
            }

            @Override
            public void onError(Throwable throwable) {

            }
        });

        println("异步上下文触发结束");
        //完成操作
        asyncContext.start(() -> {      //开启一个线程
            asyncContext.complete();
        });
    }
}
