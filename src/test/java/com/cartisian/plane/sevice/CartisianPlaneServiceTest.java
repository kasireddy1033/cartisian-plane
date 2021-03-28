package com.cartisian.plane.sevice;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cartisian.plane.service.CartisianPlaneService;


@SpringBootTest()
public class CartisianPlaneServiceTest {
	
	@Autowired(required = true)
	CartisianPlaneService service;
	
	/*
	 * Tests start for GET line equation using 2 points
	 */
	@Test
	public void getLineEquationNotPossibleCondition() {
		assertEquals(service.getLineEquationByTwoPoints(2, 4, 2, 4), CartisianPlaneService.LINE_NOT_POSSIBLE_BECAUSE_OF_SAME_POINTS);
		assertEquals(service.getLineEquationByTwoPoints(6, 6, 6, 6), CartisianPlaneService.LINE_NOT_POSSIBLE_BECAUSE_OF_SAME_POINTS);
		assertEquals(service.getLineEquationByTwoPoints(-20, -20, -20, -20), CartisianPlaneService.LINE_NOT_POSSIBLE_BECAUSE_OF_SAME_POINTS);
	}
	
	@Test
	public void getLineEquationWhereParallelToY() {
		assertEquals(service.getLineEquationByTwoPoints(3, 4, 3, 5), "x=3.0");
		assertEquals(service.getLineEquationByTwoPoints(6, 16, 6, 6), "x=6.0");
		assertEquals(service.getLineEquationByTwoPoints(-16, 6, -16, -6), "x=-16.0");
	}
	
	
	@Test
	public void getLineEquationWhereParallelToX() {
		assertEquals(service.getLineEquationByTwoPoints(13, 5, 3, 5), "y=5.0");
		assertEquals(service.getLineEquationByTwoPoints(6, 16, -6, 16), "y=16.0");
		assertEquals(service.getLineEquationByTwoPoints(-16, -26, 1, -26), "y=-26.0");
	}
	
	@Test
	public void getLineEquationXandY() {
		assertEquals(service.getLineEquationByTwoPoints(13, 5, 23, 55), "-50.0x+10.0y+600.0=0");
		assertEquals(service.getLineEquationByTwoPoints(-60, 16, -6, 6), "10.0x+54.0y-264.0=0");
		assertEquals(service.getLineEquationByTwoPoints(-16, -6, -12, -26), "20.0x+4.0y+344.0=0");
	}
	
	
	
	/*
	 * Tests start for GET line equation using slope and interception
	 */
	@Test
	public void getLineEquationWithZeros() {
		assertEquals(service.getLineEquationBygradientAndIntercept(0, 0), "y=0.0");
		assertEquals(service.getLineEquationBygradientAndIntercept(20, 0), "y=20.0x");
		assertEquals(service.getLineEquationBygradientAndIntercept(-20, 0), "y=-20.0x");
		assertEquals(service.getLineEquationBygradientAndIntercept(0, 10), "y=10.0");
		assertEquals(service.getLineEquationBygradientAndIntercept(0, -10), "y=-10.0");
	}
	
	
	@Test
	public void getLineEquationWithOutZeros() {
		//both positive
		assertEquals(service.getLineEquationBygradientAndIntercept(10, 6), "y=10.0x+6.0");
		//m positive and c negative
		assertEquals(service.getLineEquationBygradientAndIntercept(20, -10), "y=20.0x-10.0");
		//m negative and c positive
		assertEquals(service.getLineEquationBygradientAndIntercept(-20, 10), "y=-20.0x+10.0");
		//both negative
		assertEquals(service.getLineEquationBygradientAndIntercept(-5, -5), "y=-5.0x-5.0");
	}
	
	
	/*
	 * Tests start for parallel condition
	 */
	@Test
	public void isParallelForLinesParallelToY() {
		//lines parallel to x(x=2 and x=3)
		assertTrue(service.isParallel(2, 4, 2, 6, 3, 7, 3, 8));
		assertTrue(service.isParallel(-2, -4, -2, -6, -3, -7, -3, -8));
	}
	
	
	@Test
	public void isParallelForLinesParallelToX() {
		//lines parallel to y (y=4 and y=7)
		assertTrue(service.isParallel(2, 4, 12, 4, 13, 7, 3, 7));
		assertTrue(service.isParallel(-2, -4, -12, -4, -13, -7, -3, -7));
	}
	
	
	@Test
	public void isParallelForOtherLines() {
		//lines parallel
		assertTrue(service.isParallel(2, 4, 12, 14, 3, 5, 13, 15));
		assertTrue(service.isParallel(-2, -4, -12, -14, -3, -5, -13, -15));
		//lines not parallel
		assertFalse(service.isParallel(2, 4, 112, 14, 3, 5, 13, 15));
		assertFalse(service.isParallel(-2, -4, -112, -14, -3, -5, -13, -15));
	}

	
	
	/*
	 * Tests start for perpendicular condition
	 */
	@Test
	public void isPerpendicularForLinesParallelToY() {
		//lines parallel to x(x=2 and x=3)
		assertFalse(service.isPerpendicular(2, 4, 2, 6, 3, 7, 3, 8));
		assertFalse(service.isPerpendicular(-2, -4, -2, -6, -3, -7, -3, -8));
	}
	
	
	@Test
	public void isPerpendicularForLinesParallelToX() {
		//lines parallel to y (y=4 and y=7)
		assertFalse(service.isPerpendicular(2, 4, 12, 4, 13, 7, 3, 7));
		assertFalse(service.isPerpendicular(-2, -4, -12, -4, -13, -7, -3, -7));
	}
	
	
	
	@Test
	public void isPerpendicularForLinesParallelToXandY() {
		//one line parallel to x and one line parallel to y
		assertTrue(service.isPerpendicular(2, 4, 12, 4, 13, 7, 13, 17));
		assertTrue(service.isPerpendicular(-2, -4, -12, -4, -13, -7, -13, -17));
	}
	
	
	@Test
	public void isPerpendicularForOtherLines() {
		//lines perpendicular
		assertTrue(service.isPerpendicular(0, 0, 12, 12, 0, 0, -12, 12));
		assertTrue(service.isPerpendicular(0, 0, -1, -1, 0, 0, 1, -1));
		//lines not perpendicular
		assertFalse(service.isParallel(2, 4, 112, 14, 3, 5, 13, 15));
		assertFalse(service.isParallel(-2, -4, -112, -14, -3, -5, -13, -15));
	}
	
	
	
	/*
	 * Tests start for line intersection point 
	 */
	@Test
	public void getIntersectionPointForParallelLines() {
		//lines parallel to y
		assertEquals(service.getIntersectionPoint(2, 4, 2, 6, 3, 7, 3, 8), CartisianPlaneService.LINES_WILL_NEVER_INTERSECT);
		assertEquals(service.getIntersectionPoint(-2, -4, -2, -6, -3, -7, -3, -8), CartisianPlaneService.LINES_WILL_NEVER_INTERSECT);
		//lines parallel to x
		assertEquals(service.getIntersectionPoint(2, 4, 12, 4, 13, 7, 3, 7), CartisianPlaneService.LINES_WILL_NEVER_INTERSECT);
		assertEquals(service.getIntersectionPoint(-2, -4, -12, -4, -13, -7, -3, -7), CartisianPlaneService.LINES_WILL_NEVER_INTERSECT);
		//other parallel lines
		assertEquals(service.getIntersectionPoint(2, 4, 12, 14, 3, 5, 13, 15), CartisianPlaneService.LINES_WILL_NEVER_INTERSECT);
		assertEquals(service.getIntersectionPoint(-2, -4, -12, -14, -3, -5, -13, -15), CartisianPlaneService.LINES_WILL_NEVER_INTERSECT);
	}
	
	
	
	@Test
	public void getIntersectionPointForNonParallelLines() {
		assertEquals(service.getIntersectionPoint(2, 4, 12, 6, 3, 7, 3, 8), "3.0,4.2");
		assertEquals(service.getIntersectionPoint(-2, -4, -12, -6, -3, -7, -3, -8), "-3.0,-4.2");
		assertEquals(service.getIntersectionPoint(2, 4, 12, 15, 13, 17, 3, 7), "22.0,26.0");
		assertEquals(service.getIntersectionPoint(-2, -4, -12, -15, -13, -17, -3, -7), "-22.0,-26.0");
	}


}
