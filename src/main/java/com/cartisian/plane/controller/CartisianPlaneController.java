package com.cartisian.plane.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cartisian.plane.service.CartisianPlaneService;

@RestController
public class CartisianPlaneController {
	
	@Autowired
	CartisianPlaneService service;
	
	@GetMapping("/line-equation-by-two-points/{x1}/{y1}/{x2}/{y2}")
	public String getLineEquationByTwoPoints(@PathVariable double x1, @PathVariable double y1, @PathVariable double x2, @PathVariable double y2) {
		return service.getLineEquationByTwoPoints(x1, y1, x2, y2);
	}
	
	@GetMapping("/line-equation-by-gradient-intercept/{m}/{c}")
	public String getLineEquationBygradientAndIntercept(@PathVariable double m, @PathVariable double c) {
		return service.getLineEquationBygradientAndIntercept(m, c);
	}
	
	
	@GetMapping("/lines-are-parallel/{x1}/{y1}/{x2}/{y2}/{x3}/{y3}/{x4}/{y4}")
	public boolean isParallel(@PathVariable double x1, @PathVariable double y1, @PathVariable double x2, @PathVariable double y2, @PathVariable double x3, @PathVariable double y3, @PathVariable double x4, @PathVariable double y4) {
		return service.isParallel(x1, y1, x2, y2, x3, y3, x4, y4);
	}
	
	
	@GetMapping("/lines-are-perpendicular/{x1}/{y1}/{x2}/{y2}/{x3}/{y3}/{x4}/{y4}")
	public boolean isPerpendicular(@PathVariable double x1, @PathVariable double y1, @PathVariable double x2, @PathVariable double y2, @PathVariable double x3, @PathVariable double y3, @PathVariable double x4, @PathVariable double y4) {
		return service.isPerpendicular(x1, y1, x2, y2, x3, y3, x4, y4);
	}
	
	@GetMapping("/intersection-point-of-two-lines/{x1}/{y1}/{x2}/{y2}/{x3}/{y3}/{x4}/{y4}")
	public String getIntersectionPoint(@PathVariable double x1, @PathVariable double y1, @PathVariable double x2, @PathVariable double y2, @PathVariable double x3, @PathVariable double y3, @PathVariable double x4, @PathVariable double y4) {
		return service.getIntersectionPoint(x1, y1, x2, y2, x3, y3, x4, y4);
	}
	
	

}
