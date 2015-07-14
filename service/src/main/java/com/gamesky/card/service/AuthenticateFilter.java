package com.gamesky.card.service;

import com.gamesky.card.core.Constants;
import com.gamesky.card.core.ResultGenerator;
import com.gamesky.card.core.ReturnCode;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 6/11/15.
 *
 * @Author lianghongbin
 */
public class AuthenticateFilter extends OncePerRequestFilter {

    private static List<String> ignoreUrl = new ArrayList<>();

    public AuthenticateFilter() {
        ignoreUrl.add("/1_0/page/card");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if (request.getMethod().equalsIgnoreCase("post")) { //post的暂时不做校验
            filterChain.doFilter(request, response);
            return;
        }

        String url = request.getRequestURI();
        for (String ignore : ignoreUrl) {
            if (url.equalsIgnoreCase(ignore)) {
                filterChain.doFilter(request, response);
                return;
            }
        }

        String md5 = TokenGenerator.generate(request);
        if ("".equals(md5)) {
            filterChain.doFilter(request, response);
            return;
        }

        if (md5.equalsIgnoreCase(request.getParameter(Constants.TOKEN_KEY))) {
            filterChain.doFilter(request, response);
            return;
        }

        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(ResultGenerator.generateError(ReturnCode.TOKEN_CHECK_ERROR));
    }
}