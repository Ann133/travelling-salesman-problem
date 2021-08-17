import java.awt.*; // AWT = Abstract Window Toolkit
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class tsp
{
    private static final int width = 850;
    private static final int height = 540;
    
    public static void main(String[] args)
    {
        new Window(width, height);
    }
}

// Create a simple GUI window 
class Window
{
    // ArrayLists for house details
    static ArrayList<String> order = new ArrayList<>();
    static ArrayList<String> address = new ArrayList<>();
    static ArrayList<String> minutes = new ArrayList<>();
    static ArrayList<String> north = new ArrayList<>();
    static ArrayList<String> west = new ArrayList<>();
    static ArrayList<String> route = new ArrayList<>(); // An ArrayList of the final route

    // Main components of the window
    JFrame frame;
    Panel rightPanel, bottomPanel;

    // Inner components that are used in the window
    Font font;
    JLabel info, input;
    Button btnSubmit, btnReset;
    static JTextArea tfInput, tfResult;
    JScrollPane scrollPane;
    static int count = 0;

    // Default constructor
    Window() {}

    // Create and display a window
    Window(int width, int height)
    {
        // Create and set up the window
        frame = new JFrame("Maynooth's Apache routey");
        frame.setBounds(0, 0, width, height);
        frame.setCursor(new Cursor(Cursor.HAND_CURSOR));
        frame.setResizable(false);
        frame.setLayout(null);
        // When the window is closed, the application also stops
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

        // Display the window in the center of the screen
        frame.setLocationRelativeTo(null);

        createRightPanel();
        createBottomPanel();
        
        frame.add(rightPanel);
        frame.add(bottomPanel);
        
        // Set the window's size automatically by looking at what the JFrame contains 
        // frame.pack();
        frame.setVisible(true);
    }

    private void createRightPanel()
    {
        rightPanel = new Panel();
        rightPanel.setBounds(650, 0, 200, 600);
        rightPanel.setBackground(new Color(114, 211, 237));
        rightPanel.setLayout(null);

        final String message = "<html> Order numbers in order of routey: </html>";

        info = new JLabel(message);
        info.setBounds(10, 0, 175, 50);
        info.setFont(new Font("SansSerif", Font.BOLD, 14));
        info.setFocusable(false);

        tfResult = new JTextArea();
        tfResult.setBounds(10, 45, 165, 265);
        tfResult.setFont(new Font("SansSerif", Font.BOLD, 15));
        tfResult.setEditable(false);
        tfResult.setLineWrap(true);
        tfResult.setWrapStyleWord(true);

        btnReset = new Button("Reset");
        btnReset.setBounds(10, 320, 165, 80);
        btnReset.setBackground(Color.LIGHT_GRAY);
        btnReset.setFocusable(true);
        btnReset.setFont(new Font("SansSerif", Font.BOLD, 24));

        btnReset.addActionListener(new ActionListener() {
            // When the "submit" button is clicked, this method is run
            @Override
            public void actionPerformed(ActionEvent e)
            {
                tfInput.setText("");
                tfResult.setText("");
                System.out.println("Text field reset");
                order.removeAll(order);
                address.removeAll(address);
                minutes.removeAll(minutes);
                north.removeAll(north);
                west.removeAll(west);
            }
        });

        btnSubmit = new Button("Submit");
        btnSubmit.setBounds(10, 410, 165, 80);
        btnSubmit.setBackground(Color.LIGHT_GRAY);
        btnSubmit.setFont(new Font("SansSerif", Font.BOLD, 24));

        btnSubmit.addActionListener(new ActionListener() {
            // This method gets called when the "submit" button is clicked
            @Override
            public void actionPerformed(ActionEvent e)
            {
                // Include Apache's co-ordinates
                order.add("0");
                address.add("Maynooth Apache Pizza");
                minutes.add("0");
                north.add("53.38197");
                west.add("-6.59274");

                System.out.println("Input submitted");
                String text = tfInput.getText();
                split(text);
                System.out.println(" ");

                sort(north, west);
            }
        });


        // Creates the right panel and adds all the above inner components
        rightPanel.add(info);
        rightPanel.add(btnSubmit);
        rightPanel.add(btnReset);
        rightPanel.add(tfResult);

        info.setVisible(true);
        tfResult.setVisible(true);
        btnReset.setVisible(true);
        btnSubmit.setVisible(true);
        rightPanel.setVisible(true);
    }

    // Create panel to display and take in user input
    private void createBottomPanel()
    {
        bottomPanel = new Panel();
        bottomPanel.setBounds(0, 0, 850, 540);
        bottomPanel.setBackground(new Color(114, 211, 237));
        bottomPanel.setLayout(null);

        final String message = "<html> Input the order number, house address, minutes waiting so far & the co-ordinates: </html>";

        input = new JLabel(message);
        input.setBounds(10, -45, 600, 120);
        input.setFont(new Font("SansSerif", Font.BOLD, 14));
        input.setFocusable(false);

        tfInput = new JTextArea(20, 20);
        tfInput.setBounds(10, 45, 630, 445);
        tfInput.setLineWrap(true);
        tfInput.setWrapStyleWord(true);
        tfInput.setFont(new Font("SansSerif", Font.BOLD, 15));
        scrollPane = new JScrollPane(tfInput);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setSize(650, 450);
        scrollPane.setBounds(10, 45, 640, 445);

        // Creating the bottom panel and adding components to it
        bottomPanel.add(input);
        bottomPanel.add(scrollPane);

        input.setVisible(true);
        tfInput.setVisible(true);
        bottomPanel.setVisible(true);
    }

    // Method to split input String into separate ArrayLists
    public void split(String input)
    {
        String text = input.replaceAll("\n", ",");
        int n = text.indexOf(",");
        String addition = text.substring(0, n);
        String leftover = text.substring(n+1);

        // If count is 0, add substring until comma to ArrayList 'order'
        if (count == 0)
        {
            order.add(addition);
            count++;
        }
        
        // If count is 1, add substring until comma to ArrayList 'address'
        else if (count == 1)
        {
            address.add(addition);
            count++;
        }

        // If count is 2, add substring until comma to ArrayList 'minutes'
        else if (count == 2)
        {
            minutes.add(addition);
            count++;
        }

        // If count is 3, add substring until comma to ArrayList 'north'
        else if (count == 3)
        {
            north.add(addition);
            count++;
        }

        // If count is 4, add substring until comma to ArrayList 'west'
        else if (count == 4)
        {
            west.add(addition);
            count = 0;
        }
                
        try { split(leftover); } catch (StringIndexOutOfBoundsException e) { west.add(leftover); return; }
    }

    // Getting the distance between two co-ordinates in km
    public double getDistance (String latitude1, String longitude1, String latitude2, String longitude2)
    {
        /* Point A */
        double lat1 = Double.parseDouble(latitude1); // Latitude of location 1
        double long1 = Double.parseDouble(longitude1); // Longitude of location 1

        // Convert the values of latitude & longitude from degrees to radians
        lat1 = Math.toRadians(lat1);
        long1 = Math.toRadians(long1);

        /* Point B */
        double lat2 = Double.parseDouble(latitude2); // Latitude of location 2
        double long2 = Double.parseDouble(longitude2); // Longitude of location 2

        // Convert the values of latitude & longitude to radians
        lat2 = Math.toRadians(lat2);
        long2 = Math.toRadians(long2);

        /* Haversine Formula */
        double dlong = long2 - long1;
        double dlat = lat2 - lat1;

        double distance = Math.pow(Math.sin(dlat / 2), 2) 
                        + Math.cos(lat1) * Math.cos(lat2) 
                        * Math.pow(Math.sin(dlong / 2), 2);

        double result = 2 * Math.asin(Math.sqrt(distance));

        double radius = 6371; // Radius of Earth in kilometers

        // Distance in kilometers from location 1 to location 2 rounded to 2 decimal points
        double output = Math.round((result * radius) * 100.0) / 100.0;
        return output;
    }

    // Method to sort the order numbers using 'Nearest Neighbour' algorithm
    public void sort (ArrayList<String> north, ArrayList<String> west)
    {
        double[] start = {53.38197, -6.59274};
        int size = north.size(); // +1 because of order 0
        boolean northOrWest = true; 
        
        // Combining north and west co-ordinates into one array
        double[][] points = new double[size][2];
        points[0][0] = 53.38197;
        points[0][1] = -6.59274;

        // Filling up 'points' array
        for (int i = 1; i < points.length; i++) {
            for (int j = 0; j < points[i].length; j++) {
                if (northOrWest) {
                    points[i][j] = Double.parseDouble(north.get(i));
                    northOrWest = false;
                }
                else if (!northOrWest) {
                    points[i][j] = Double.parseDouble(west.get(i));
                    northOrWest = true;
                }
            }
        }

        // Getting the first delivery point
        nearestPoint(start, points, order);
    }

    public void nearestPoint (double[] start, double[][] points, ArrayList<String> order)
    {
        final int x = 0;
        final int y = 1;

        int pos = 0;
        
        double[] closestPoint = points[1];

        double closestDist = getDistance(String.valueOf(start[x]), String.valueOf(start[y]), String.valueOf(closestPoint[x]), String.valueOf(closestPoint[y]));

        /* Traverse the array */
        for (int i = 0; i < points.length; i++)
        {
            
            double dist = getDistance(String.valueOf(start[x]), String.valueOf(start[y]), String.valueOf(points[i][x]), String.valueOf(points[i][y]));

            if (dist < closestDist && dist != 0.0) {
                closestDist = dist;
                closestPoint = points[i];
                pos = i;
            } 
        }
        route.add(order.get(pos));

        otherPoints();

        System.out.println("Final Route: " + Arrays.deepToString(route.toArray()));
    }

    // Getting the route for the rest of the deliveries
    public void otherPoints () 
    {
        /* Getting the most recently visited house */
        String recent = route.get(route.size()-1);
        int index = order.indexOf(recent);

        /* Getting the starting co-ordinates */
        String latitude1 = north.get(index);
        String longitude1 = west.get(index);

        double closestDist = 9999.0; 

        for (int i = 0; i < order.size(); i++)
        {
            // If the route already contains that order number, skip it (it has been visited)
            if (route.contains(order.get(i)))
            {
                
            } else {
                /* Getting the value and index of next house to visit */
                String next = order.get(i);
                int nextInd = order.indexOf(next);

                String latitude2 = north.get(nextInd);
                String longitude2 = west.get(nextInd);

                double dist = getDistance(latitude1, longitude1, latitude2, longitude2);

                if (dist < closestDist)
                {
                    closestDist = dist;
                    route.add(order.get(i));
                }
            }
        }

        if (route.size() == order.size())
        {
            String result1 = Arrays.deepToString(route.toArray()).replaceAll("\\s","");
            String result2 = result1.replaceFirst("0,","");
            tfResult.setText(result2.substring(1,result2.length()-1));
            return;
        } else {
            otherPoints();
        }
    }
}