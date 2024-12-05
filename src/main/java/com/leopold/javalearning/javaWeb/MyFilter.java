package com.leopold.javalearning.javaWeb;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter("/*")
public class MyFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("对request进行过滤");
        // 下面这行代码就是放行
        chain.doFilter(request, response);
        System.out.println("对response进行过滤");
    }
}
