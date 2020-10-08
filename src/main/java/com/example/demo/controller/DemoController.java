package com.example.demo.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.util.DateUtils;

@RestController
public class DemoController {

	DateUtils dateUtis;
	private SayService sayService;

	DemoController(DateUtils dateUtis, SayService sayService) {
		this.dateUtis = dateUtis;
		this.sayService = sayService;
	}

	@GetMapping("/")
	String getToday() {
		return this.dateUtis.todayString();
	}

	@GetMapping("/say")
	String say() {
		return sayService.say();
	}

}

interface SayService {
	String say();
}

@Component
class Cat implements SayService {
	@Override
	public String say() {
		return "meow";
	}
}
