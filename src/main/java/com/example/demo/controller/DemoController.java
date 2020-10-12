package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.util.DateUtils;

//@RestController
public class DemoController {

	DateUtils dateUtis;
	private SayService sayService;

	DemoController(DateUtils dateUtis, @Qualifier("cat_rock") SayService sayService) {
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

@Component("cat_rock")
class Cat implements SayService {
	@Override
	public String say() {
		return "meow";
	}
}

@Component("dog_rock")
class Dog implements SayService {
	@Override
	public String say() {
		return "boooww";
	}
}
