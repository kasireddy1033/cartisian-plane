package com.cartisian.plane.service;

import org.springframework.stereotype.Service;

@Service
public interface CartisianPlaneService {
	
	public static final String LINE_NOT_POSSIBLE_BECAUSE_OF_SAME_POINTS = "Not possible because both points are equal";
	public static final String LINES_WILL_NEVER_INTERSECT = "Lines Will Never Intersect";

	
	public String getLineEquationByTwoPoints(double x1, double y1, double x2, double y2);

	public String getLineEquationBygradientAndIntercept(double m, double c);

	public boolean isParallel(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4);

	public boolean isPerpendicular(double x1, double y1, double x2, double y2, double x3, double y3, double x4,
			double y4);

	public String getIntersectionPoint(double x1, double y1, double x2, double y2, double x3, double y3, double x4,
			double y4);
}
