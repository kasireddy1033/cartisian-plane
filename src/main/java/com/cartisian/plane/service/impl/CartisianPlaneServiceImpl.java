package com.cartisian.plane.service.impl;

import org.springframework.stereotype.Service;

import com.cartisian.plane.service.CartisianPlaneService;

@Service
public class CartisianPlaneServiceImpl implements CartisianPlaneService {
	

	@Override
	public String getLineEquationByTwoPoints(double x1, double y1, double x2, double y2) {
		if(x1==x2 && y1==y2) {
			return LINE_NOT_POSSIBLE_BECAUSE_OF_SAME_POINTS;
		}
		if(x1==x2) {
			return "x=" + x1;
		} else if (y1==y2) {
			return "y=" + y1;
		} else {
			double a = y1-y2;
			double b = x2-x1;
			double c = x1*y2-x2*y1;
			String result="";
			if(a!=0) {
				result += a + "x";
			}
			if(b!=0) {
				if(result.length()>0) {
					if(b>0)
						result+="+";
				}
				result += b + "y" ;
			}
			if(c!=0) {
				if(result.length()>0) {
					if(c>0)
						result+="+";
				}
				result += c + "=0";
			} else {
				result += "=0";
			}
			return  result; 
		}
	}
	
	@Override
	public String getLineEquationBygradientAndIntercept(double m, double c) {
		if(c>0)
			return "y=" + (m!=0?m+"x+":"") + c;
		else
			return "y=" + (m!=0?m+"x":"") + (c==0 && m!=0?"":c);
	}

	@Override
	public boolean isParallel(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4) {
		if(x1==x2 && x3==x4 || y1==y2 && y3==y4) {
			return true;
		} else {
			if(x2-x1!=0 && x4-x3!=0) {
				double m1=(y2-y1)/(x2-x1);
				double m2= (y4-y3)/(x4-x3);
				if(m1==m2) {
					return true;
				}
			}
			return false;			
		}
	}

	@Override
	public boolean isPerpendicular(double x1, double y1, double x2, double y2, double x3, double y3, double x4,
			double y4) {
		if (x1==x2 && x3==x4)
            return false;
		else if(x1==x2) {
			if(y3==y4)
				return true;
			else
				return false;
		} else if(x3==x4) {
			if(y1==y2)
				return true;
			else
				return false;
		} else {
			double m1 = (y2-y1)/(x2-x1);
			double m2 = (y4-y3)/(x4-x3);
			if (m1 * m2 == -1)
                return true;
            else
                return false;
		}
	}

	@Override
	public String getIntersectionPoint(double x1, double y1, double x2, double y2, double x3, double y3, double x4,
			double y4) {
		//ax+by=c
		double a1 = y2-y1;
		double b1 = x1-x2;
		double c1 = a1*x1+b1*y1;
		
		double a2 = y4-y3;
		double b2 = x3-x4;
		double c2 = a2*x3+b2*y3;
		double determinant = a1*b2 - a2*b1;
		if(determinant==0) {
			return LINES_WILL_NEVER_INTERSECT;
		} else {
			return ((b2*c1 - b1*c2)/determinant) + "," + ((a1*c2 - a2*c1)/determinant);
		}
	}

}
