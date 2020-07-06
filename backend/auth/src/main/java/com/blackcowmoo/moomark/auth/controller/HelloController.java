package com.blackcowmoo.moomark.auth.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

  @RequestMapping(value = "/")
  public String hello() {
    return "hello MooMarkAuthServer";
  }

  @RequestMapping(value = "anyone")
  public String anyone() {
    return "this page connect anyone";
  }

  @RequestMapping(value = "/api/v1/hello")
  public String hasRoleUserHello(HttpServletRequest request) {
    return request.getSession().getId();
  }
}
