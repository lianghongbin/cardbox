package com.gamesky.card.service;

import com.gamesky.card.core.Constants;
import com.gamesky.card.core.utils.MD5Utils;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Created on 7/9/15.
 *
 * @Author lianghongbin
 */
public class TokenGenerator {

    public static String generate(HttpServletRequest request) {
        Enumeration enumeration = request.getParameterNames();
        SortedSet<String> parameters = new TreeSet<String>();

        while (enumeration.hasMoreElements()) {
            String parameter = (String) enumeration.nextElement();
            if (parameter.equals(Constants.TOKEN_KEY)) {
                continue;
            }

            parameters.add(parameter);
        }

        int count = parameters.size();
        if (count == 0) {
            return "";
        }

        StringBuilder builder = new StringBuilder();
        int index = 0;
        for (String name : parameters) {
            builder.append(name);
            builder.append(index++ * count);
            builder.append(request.getParameter(name));
        }

        builder.append(count);
        return MD5Utils.toString(builder.toString());
    }
}
