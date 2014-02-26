package hu.sonrisa.spring.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestJSONController {

	@RequestMapping(value="/json/testArray", method=RequestMethod.GET)
	public @ResponseBody List<String> testFunction() {
		List<String> list = new ArrayList<String>();
		return list;
	}
}
