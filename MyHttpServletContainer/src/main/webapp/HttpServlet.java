package com.dist;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

public class HttpServlet implements Servlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("初始化。。。");
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        System.out.println("执行业务代码");
        PrintWriter printWriter= res.getWriter();
        printWriter.write("ok hello");
        printWriter.flush();
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {
        System.out.println("destroy");
    }
}
