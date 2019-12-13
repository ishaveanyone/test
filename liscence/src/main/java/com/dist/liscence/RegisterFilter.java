package com.dist.liscence;


import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.annotation.Resource;
import javax.naming.Name;
import javax.servlet.*;
import javax.servlet.FilterConfig;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Date;

public class RegisterFilter implements Filter {
    private static Logger logger = LoggerFactory.getLogger(RegisterFilter.class);

    private Date liscenceDate;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(filterConfig.getServletContext());
        liscenceDate = (Date) context.getBean("liscenceDate");
        System.out.println(liscenceDate);
    }
    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        Date now = Calendar.getInstance().getTime();
        now.setDate(19);
        if (liscenceDate == null) {
            logger.error("证书未加载");
            servletResponse.getOutputStream().print("liscence not load");
            writeToResponse((HttpServletResponse)servletResponse,
                    "未加载");
        }
        else if (DateUtil.compare(now, liscenceDate) == 1) {
            logger.warn("证书已过期");
            writeToResponse((HttpServletResponse)servletResponse,
                    "证书已过期");
        }else{
            System.out.println("通过");
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }

    @Override
    public void destroy() {

    }


    /**
     * 向HttpServletResponse中写入返回数据
     * @param response
     * @param o      返回数据
     */
    private void writeToResponse(HttpServletResponse response, Object o){
        OutputStream os = null;
        response.setContentType("application/json;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        try {
            os = response.getOutputStream();
            String str = JSONObject.toJSONString(o);
            os.write(str.getBytes("utf-8"));
            os.flush();
        } catch (IOException e) {
            logger.error("ValidateFilter.writeToResponse error",e);
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    logger.error("ValidateFilter.writeToResponse close OutputStream error",e);
                }
            }
        }
    }

}
