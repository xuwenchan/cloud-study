package com.userModule.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;

@RestController
@RequestMapping("redis")
public class RedisTestController {

	 	@Autowired
	    private RedisTemplate  redisTemplate;
	 
	    @RequestMapping("/set")
	    public String setUserName(String name) {
	        redisTemplate.opsForValue().set("1", name);
	        return "succees";
	    }
	 
	    @RequestMapping("/get")
	    public String getUserName(String key) {
	        return  redisTemplate.opsForValue().get(key).toString();
	    }
	    
	    @RequestMapping("putsession")
	    public void sessionTest(HttpServletRequest request) {
	    	HttpSession session=request.getSession();
	    	session.setAttribute("name", "xuwenchan");
	    	session.setAttribute("age", "18");
	    	session.setAttribute("address", "henan");
	    	redisTemplate.opsForValue().set("session", JSON.toJSONString(session));
	    	
	    }
	    @RequestMapping("getsession")
	    public void getsession() {
	    	String sessionStr=(String) redisTemplate.opsForValue().get("session");
	    	HttpSession session=JSON.parseObject(sessionStr, HttpSession.class);
	    	//HttpSession session=JSON.parseObject(redisTemplate.opsForValue().get("session"),HttpSession.class);
	    	//HttpSession session=(HttpSession)JSON.parse((String) redisTemplate.opsForValue().get("session"));
	    	//HttpSession session=(HttpSession) redisTemplate.opsForValue().get("session");
	    	System.out.println(session.getAttribute("name"));
	    	System.out.println(session.getAttribute("age"));
	    	System.out.println(session.getAttribute("address"));
	    }
	    	
	    
	    
}
