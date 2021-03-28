Run the project
Step 0: Get the project to local and get inside the folder
Step 1: Use 'gradle clean build' to run test cases and build the jar
Step 2: Run command 'java -jar build/libs/cartisian-plane-0.0.1-SNAPSHOT.jar' to run java application
Step 3: Open browser and open the swagger link 'http://localhost:8080/swagger-ui/'

We have below API's for all the scenarios given
1. Definition of a line by means of two points, where (x1,y1) and (x2,y2) are two points
API GET /line-equation-by-two-points/{x1}/{y1}/{x2}/{y2}


2. Definition of a line by means of gradient and y-intercept where m is gradient/slope and c is y-intercept
API GET /line-equation-by-gradient-intercept/{m}/{c}


3. Condition of parallelism of two lines, where line1 passes through (x1,y1)&(x2,y2) and line2 passes through (x3,y3)&(x4,y4)
​API GET /lines-are-parallel​/{x1}​/{y1}​/{x2}​/{y2}​/{x3}​/{y3}​/{x4}​/{y4}


4. Condition of perpendicularity of two lines, where line1 passes through (x1,y1)&(x2,y2) and line2 passes through (x3,y3)&(x4,y4)
/lines-are-perpendicular/{x1}/{y1}/{x2}/{y2}/{x3}/{y3}/{x4}/{y4}


5. Condition of incidence of two lines and definition of the incidence point, where line1 passes through (x1,y1)&(x2,y2) and line2 passes through (x3,y3)&(x4,y4)
/intersection-point-of-two-lines/{x1}/{y1}/{x2}/{y2}/{x3}/{y3}/{x4}/{y4}