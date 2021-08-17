[19317511.pdf](https://github.com/Ann133/travelling-salesman-problem/files/7003272/19317511.pdf)
# travelling-salesman-problem
A project completed in my second year of Computer Science &amp; Software Engineering to tackle the travelling salesman problem. 

This is a project created for the CS211 Algorithms & Data Structures 2 module. 

**The Task:** Using your knowledge of Java and data structures & algorithms, build an app that helps minimize the delay in delivering pizzas to customers. You can only import java.* libraries - everything must be in a single file called tsp.java, no extra image files allowed. For graphics use java.awt.* and javax.swing.*. The user pastes in data in the following format into a text field in your app, then they click a "compute" button:
(A list of up to 100 orders awaiting takeaway delivery)
 - Column 1: the order number
 - Column 2: the house address 
 - Column 3: minutes waiting so far
 - Column 4: GPS north
 - Column 5: GPS west
 e.g. 1, 38 Parsons Hall Maynooth, 41.537521, -6.6103

A graphical display of the solution can be shown in any way you want. Your app MUST produce output in this format:
 - List of order numbers, separated by commas, starting with first to be visited, visiting all orders.
 e.g. 3, 4, 1, 2

**Evaluation:** Customers are happy to wait 30 minutes for their pizza without getting angry - anything up to 30 minutes is regarded as no delay. Every minute above 30 minutes for any customer will be added together to get a "total delay time". The delivery driver can travel as the crow flies at 60 km/h. There is a time limit of 10 seconds. Make sure that your app pastes the solution into a text field, so that it can be copy and pasted.
