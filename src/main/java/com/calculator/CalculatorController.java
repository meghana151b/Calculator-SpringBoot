package com.calculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class CalculatorController {
	
	@Autowired
	private CalculatorService calculatorService;
	
	//Serve the calculator page
	@RequestMapping("/")
	public String home() {
		return "home";
		
	}
	//Process the calculation and redirect to result page
	@PostMapping("/calculate")
	public String calculate(@RequestParam("num1") double n1,
			                @RequestParam("num2") double n2,
			                @RequestParam("operation") String operation,
			                Model model) {
		double result=0;
		switch(operation) {
		case "add":
			result = calculatorService.add(n1,n2);
			break;
		case "sub":
			result = calculatorService.subtract(n1,n2);
			break;
		case "mul":
			result = calculatorService.multiply(n1,n2);
			break;
		case "div":
			if(n2!=0) {
				result= calculatorService.divide(n1,n2);
			}else {
				model.addAttribute("error","Division by zero is not available");
				model.addAttribute("num1",n1);
				model.addAttribute("num2",n2);
				return "result";
			}
			break;
			default:
				model.addAttribute("error","Invalid Operation.");
				return "result";
		}
		model.addAttribute("num1",n1);
		model.addAttribute("num2",n2);
		model.addAttribute("operation",operation);
		model.addAttribute("result",result);
		return "result";
		
	}

}
