package com.gamesky.card.inner.controller;

import com.gamesky.card.core.Constants;
import com.gamesky.card.core.model.Admin;
import com.gamesky.card.service.AdminService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created on 6/11/15.
 *
 * @Author lianghongbin
 */
public class AuthenticateFilter extends OncePerRequestFilter {

    private List<String> ignoreUrl;
    private List<String> resources;
    @Autowired
    private AdminService adminService;

    public List<String> getIgnoreUrl() {
        return ignoreUrl;
    }

    public void setIgnoreUrl(List<String> ignoreUrl) {
        this.ignoreUrl = ignoreUrl;
    }

    public List<String> getResources() {
        return resources;
    }

    public void setResources(List<String> resources) {
        this.resources = resources;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String url = request.getRequestURI();
        for (String ignore : ignoreUrl) {
            if (url.equalsIgnoreCase(ignore)) {
                filterChain.doFilter(request, response);
                return;
            }
        }

        for (String resource : resources) {
            if (url.startsWith(resource)) {
                filterChain.doFilter(request, response);
                return;
            }
        }

        HttpSession session = request.getSession();
        String phone = (String) session.getAttribute(Constants.INNER_LOGIN_KEY);
        if (StringUtils.isNotBlank(phone)) {
            Admin admin = adminService.findByPhone(phone);
            if (admin == null || admin.getLocked()) {
                response.sendRedirect("/login");
                return;
            }

            if (url.startsWith("/admin/")) {
                if (!admin.getTop()) {
                    response.sendRedirect("/login");
                    return;
                }
            }

            filterChain.doFilter(request, response);
            return;
        }

        response.sendRedirect("/login");
    }
}