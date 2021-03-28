package com.cartisian.plane.controller;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cartisian.plane.service.CartisianPlaneService;

//@WebMvcTest(CartisianPlaneController.class)
@AutoConfigureMockMvc
@SpringBootTest
public class CartisianPlaneControllerTest {
	
	@Autowired
    private MockMvc mockMvc;
	
	/*
	 * Tests start for GET line equation using 2 points
	 */
	@Test
	public void getLineEquationNotPossibleCondition() throws Exception {
		assertEquals(mockMvc.perform(MockMvcRequestBuilders.get("/line-equation-by-two-points/2/4/2/4").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), CartisianPlaneService.LINE_NOT_POSSIBLE_BECAUSE_OF_SAME_POINTS);
		assertEquals(mockMvc.perform(MockMvcRequestBuilders.get("/line-equation-by-two-points/6/6/6/6").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), CartisianPlaneService.LINE_NOT_POSSIBLE_BECAUSE_OF_SAME_POINTS);
		assertEquals(mockMvc.perform(MockMvcRequestBuilders.get("/line-equation-by-two-points/-20/-20/-20/-20").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), CartisianPlaneService.LINE_NOT_POSSIBLE_BECAUSE_OF_SAME_POINTS);
	}
	
	@Test
	public void getLineEquationWhereParallelToY() throws Exception {
		assertEquals(mockMvc.perform(MockMvcRequestBuilders.get("/line-equation-by-two-points/3/4/3/5").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), "x=3.0");
		assertEquals(mockMvc.perform(MockMvcRequestBuilders.get("/line-equation-by-two-points/6/16/6/6").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), "x=6.0");
		assertEquals(mockMvc.perform(MockMvcRequestBuilders.get("/line-equation-by-two-points/-16/6/-16/-6").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), "x=-16.0");
	}
	
	
	@Test 
	public void getLineEquationWhereParallelToX() throws Exception {
		assertEquals(mockMvc.perform(MockMvcRequestBuilders.get("/line-equation-by-two-points/13/5/3/5").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), "y=5.0");
		assertEquals(mockMvc.perform(MockMvcRequestBuilders.get("/line-equation-by-two-points/6/16/-6/16").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), "y=16.0");
		assertEquals(mockMvc.perform(MockMvcRequestBuilders.get("/line-equation-by-two-points/-16/-26/1/-26").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), "y=-26.0");
		
	}
	
	@Test
	public void getLineEquationXandY() throws Exception {
		assertEquals(mockMvc.perform(MockMvcRequestBuilders.get("/line-equation-by-two-points/13/5/23/55").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), "-50.0x+10.0y+600.0=0");
		assertEquals(mockMvc.perform(MockMvcRequestBuilders.get("/line-equation-by-two-points/-60/16/-6/6").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), "10.0x+54.0y-264.0=0");
		assertEquals(mockMvc.perform(MockMvcRequestBuilders.get("/line-equation-by-two-points/-16/-6/-12/-26").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), "20.0x+4.0y+344.0=0");
	}
	
	
	
	/*
	 * Tests start for GET line equation using slope and interception
	 */
	@Test
	public void getLineEquationWithZeros() throws Exception {
		assertEquals(mockMvc.perform(MockMvcRequestBuilders.get("/line-equation-by-gradient-intercept/0/0").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), "y=0.0");
		assertEquals(mockMvc.perform(MockMvcRequestBuilders.get("/line-equation-by-gradient-intercept/20/0").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), "y=20.0x");
		assertEquals(mockMvc.perform(MockMvcRequestBuilders.get("/line-equation-by-gradient-intercept/-20/0").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), "y=-20.0x");
		assertEquals(mockMvc.perform(MockMvcRequestBuilders.get("/line-equation-by-gradient-intercept/0/10").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), "y=10.0");
		assertEquals(mockMvc.perform(MockMvcRequestBuilders.get("/line-equation-by-gradient-intercept/0/-10").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), "y=-10.0");
	}
	
	
	@Test
	public void getLineEquationWithOutZeros() throws Exception {
		//both positive
		assertEquals(mockMvc.perform(MockMvcRequestBuilders.get("/line-equation-by-gradient-intercept/10/6").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), "y=10.0x+6.0");
		//m positive and c negative
		assertEquals(mockMvc.perform(MockMvcRequestBuilders.get("/line-equation-by-gradient-intercept/20/-10").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), "y=20.0x-10.0");
		//m negative and c positive
		assertEquals(mockMvc.perform(MockMvcRequestBuilders.get("/line-equation-by-gradient-intercept/-20/10").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), "y=-20.0x+10.0");
		//both negative
		assertEquals(mockMvc.perform(MockMvcRequestBuilders.get("/line-equation-by-gradient-intercept/-5/-5").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), "y=-5.0x-5.0");
	
	}
	
	
	/*
	 * Tests start for parallel condition
	 */
	@Test
	public void isParallelForLinesParallelToY() throws Exception {
		//lines parallel to x(x=2 and x=3)
		assertTrue(Boolean.valueOf(mockMvc.perform(MockMvcRequestBuilders.get("/lines-are-parallel/2/4/2/6/3/7/3/8").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString()));
		assertTrue(Boolean.valueOf(mockMvc.perform(MockMvcRequestBuilders.get("/lines-are-parallel/-2/-4/-2/-6/-3/-7/-3/-8").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString()));
	}
	
	
	@Test
	public void isParallelForLinesParallelToX() throws Exception {
		//lines parallel to y (y=4 and y=7)
		assertTrue(Boolean.valueOf(mockMvc.perform(MockMvcRequestBuilders.get("/lines-are-parallel/2/4/12/4/13/7/3/7").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString()));
		assertTrue(Boolean.valueOf(mockMvc.perform(MockMvcRequestBuilders.get("/lines-are-parallel/-2/-4/-12/-4/-13/-7/-3/-7").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString()));
	}
	
	
	@Test
	public void isParallelForOtherLines() throws Exception {
		//lines parallel
		assertTrue(Boolean.valueOf(mockMvc.perform(MockMvcRequestBuilders.get("/lines-are-parallel/2/4/12/14/3/5/13/15").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString()));
		assertTrue(Boolean.valueOf(mockMvc.perform(MockMvcRequestBuilders.get("/lines-are-parallel/-2/-4/-12/-14/-3/-5/-13/-15").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString()));
		//lines not parallel
		assertFalse(Boolean.valueOf(mockMvc.perform(MockMvcRequestBuilders.get("/lines-are-parallel/2/4/112/14/3/5/13/15").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString()));
		assertFalse(Boolean.valueOf(mockMvc.perform(MockMvcRequestBuilders.get("/lines-are-parallel/-2/-4/-112/-14/-3/-5/-13/-15").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString()));
	}

	
	
	/*
	 * Tests start for perpendicular condition
	 */
	@Test
	public void isPerpendicularForLinesParallelToY() throws Exception {
		//lines parallel to x(x=2 and x=3)
		assertFalse(Boolean.valueOf(mockMvc.perform(MockMvcRequestBuilders.get("/lines-are-perpendicular/2/4/2/6/3/7/3/8").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString()));
		assertFalse(Boolean.valueOf(mockMvc.perform(MockMvcRequestBuilders.get("/lines-are-perpendicular/-2/-4/-2/-6/-3/-7/-3/-8").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString()));
	}
	
	
	@Test
	public void isPerpendicularForLinesParallelToX() throws Exception {
		//lines parallel to y (y=4 and y=7)
		assertFalse(Boolean.valueOf(mockMvc.perform(MockMvcRequestBuilders.get("/lines-are-perpendicular/2/4/12/4/13/7/3/7").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString()));
		assertFalse(Boolean.valueOf(mockMvc.perform(MockMvcRequestBuilders.get("/lines-are-perpendicular/-2/-4/-12/-4/-13/-7/-3/-7").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString()));
	}
	
	
	
	@Test
	public void isPerpendicularForLinesParallelToXandY() throws Exception {
		//one line parallel to x and one line parallel to y
		assertTrue(Boolean.valueOf(mockMvc.perform(MockMvcRequestBuilders.get("/lines-are-perpendicular/2/4/12/4/13/7/13/17").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString()));
		assertTrue(Boolean.valueOf(mockMvc.perform(MockMvcRequestBuilders.get("/lines-are-perpendicular/-2/-4/-12/-4/-13/-7/-13/-17").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString()));
	}
	
	
	@Test
	public void isPerpendicularForOtherLines() throws Exception {
		//lines perpendicular
		assertTrue(Boolean.valueOf(mockMvc.perform(MockMvcRequestBuilders.get("/lines-are-perpendicular/0/0/12/12/0/0/-12/12").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString()));
		assertTrue(Boolean.valueOf(mockMvc.perform(MockMvcRequestBuilders.get("/lines-are-perpendicular/0/0/-1/-1/0/0/1/-1").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString()));
		//lines not perpendicular
		assertFalse(Boolean.valueOf(mockMvc.perform(MockMvcRequestBuilders.get("/lines-are-perpendicular/2/4/112/14/3/5/13/15").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString()));
		assertFalse(Boolean.valueOf(mockMvc.perform(MockMvcRequestBuilders.get("/lines-are-perpendicular/-2/-4/-112/-14/-3/-5/-13/-15").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString()));
	}
	
	
	
	/*
	 * Tests start for line intersection point 
	 */
	@Test
	public void getIntersectionPointForParallelLines() throws Exception {
		//lines parallel to y
		assertEquals(mockMvc.perform(MockMvcRequestBuilders.get("/intersection-point-of-two-lines/2/4/2/6/3/7/3/8").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), CartisianPlaneService.LINES_WILL_NEVER_INTERSECT);
		assertEquals(mockMvc.perform(MockMvcRequestBuilders.get("/intersection-point-of-two-lines/-2/-4/-2/-6/-3/-7/-3/-8").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), CartisianPlaneService.LINES_WILL_NEVER_INTERSECT);
		//lines parallel to x
		
		assertEquals(mockMvc.perform(MockMvcRequestBuilders.get("/intersection-point-of-two-lines/2/4/12/4/13/7/3/7").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), CartisianPlaneService.LINES_WILL_NEVER_INTERSECT);
		assertEquals(mockMvc.perform(MockMvcRequestBuilders.get("/intersection-point-of-two-lines/-2/-4/-12/-4/-13/-7/-3/-7").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), CartisianPlaneService.LINES_WILL_NEVER_INTERSECT);
		//other parallel lines
		
		assertEquals(mockMvc.perform(MockMvcRequestBuilders.get("/intersection-point-of-two-lines/2/4/12/14/3/5/13/15").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), CartisianPlaneService.LINES_WILL_NEVER_INTERSECT);
		assertEquals(mockMvc.perform(MockMvcRequestBuilders.get("/intersection-point-of-two-lines/-2/-4/-12/-14/-3/-5/-13/-15").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), CartisianPlaneService.LINES_WILL_NEVER_INTERSECT);
	}
	
	
	
	@Test
	public void getIntersectionPointForNonParallelLines() throws Exception {
		
		assertEquals(mockMvc.perform(MockMvcRequestBuilders.get("/intersection-point-of-two-lines/2/4/12/6/3/7/3/8").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), "3.0,4.2");
		assertEquals(mockMvc.perform(MockMvcRequestBuilders.get("/intersection-point-of-two-lines/-2/-4/-12/-6/-3/-7/-3/-8").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), "-3.0,-4.2");
		
		assertEquals(mockMvc.perform(MockMvcRequestBuilders.get("/intersection-point-of-two-lines/2/4/12/15/13/17/3/7").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), "22.0,26.0");
		assertEquals(mockMvc.perform(MockMvcRequestBuilders.get("/intersection-point-of-two-lines/-2/-4/-12/-15/-13/-17/-3/-7").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), "-22.0,-26.0");
	}

}
