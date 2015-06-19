package com.gamesky.card.service;

import com.gamesky.card.core.Constants;
import com.gamesky.card.core.ResultGenerator;
import com.gamesky.card.core.ReturnCode;
import com.gamesky.card.core.utils.MD5Utils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Created on 6/11/15.
 *
 * @Author lianghongbin
 */
public class AuthenticateFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        Enumeration enumeration = request.getAttributeNames();
        SortedSet<String> attributes = new TreeSet<String>();

        while (enumeration.hasMoreElements()) {
            String attribute = (String) enumeration.nextElement();
            if (attribute.equals(Constants.TOKEN_KEY)) {
                continue;
            }

            attributes.add(attribute);
        }

        int count = attributes.size();
        if (count == 0) {
            filterChain.doFilter(request, response);
            return;
        }

        StringBuilder builder = new StringBuilder();
        int index = 0;
        for (String name : attributes) {
            builder.append(name);
            builder.append(index++ * count);
            builder.append(request.getAttribute(name));
        }

        builder.append(count);
        String result = MD5Utils.toString(builder.toString());
        if (result.equalsIgnoreCase((String) request.getAttribute(Constants.TOKEN_KEY))) {
            filterChain.doFilter(request, response);
            return;
        }

        response.getWriter().print(ResultGenerator.generateError(ReturnCode.ILLEGAL_ARGUMENT));
    }
}