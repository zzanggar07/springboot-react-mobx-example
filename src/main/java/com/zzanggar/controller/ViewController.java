package com.zzanggar.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
@Api(value = "웹 페이지", description = "웹 페이지", basePath = "/")
public class ViewController {
    @GetMapping(value = {"", "/"})
    @ApiOperation(value = "메인 페이지", notes = "메인 페이지")
    public String home() {
        return getPage("/main");
    }

    @GetMapping(value = {"/docs", "/result", "/login"})
    @ApiOperation(value = "페이지 이동", notes = "페이지 이동")
    public String page(HttpServletRequest request) {
        return getPage(request.getRequestURI());
    }

    private String getPage(String page) {
        return String.format("page%s", page);
    }
}
