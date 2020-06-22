package com.blackcowmoo.moomark.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

  @RequestMapping(value = "hello")
  public String hello() {
    return "hello";
  }

  @RequestMapping(value = "/api/v1/hello")
  public String hasRoleUserHello() {
    return "hello";
  }
}
