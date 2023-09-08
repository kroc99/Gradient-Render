import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Random;



public class Project1_Codebase {
    //references to some variables we may want to access in a global context
    static int WIDTH = 500; //width of the image
    static int HEIGHT = 500; //height of the image
    static BufferedImage Display; //the image we are displaying
    static JFrame window; //the frame containing our window


    public static void main(String[] args) {
        //run the GUI on the special event dispatch thread (EDT)
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //Create the window and set options
                //The window
                window = new JFrame("RandomWalker");
                window.setPreferredSize(new Dimension(WIDTH + 100, HEIGHT + 50));
                window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                window.setVisible(true);
                window.pack();


                //Display panel/image
                JPanel DisplayPanel = new JPanel();
                Display = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
                DisplayPanel.add(new JLabel(new ImageIcon(Display)));
                window.add(DisplayPanel, BorderLayout.CENTER);

                //Config panel
                JPanel Configuration = new JPanel();
                Configuration.setBackground(new Color(230, 230, 230));
                Configuration.setPreferredSize(new Dimension(100, 500));
                Configuration.setLayout(new FlowLayout());

                //Step count input
                JLabel StepCountLabel = new JLabel("Step Count:");
                Configuration.add(StepCountLabel);

                JTextField StepCount = new JTextField("500");
                StepCount.setPreferredSize(new Dimension(100, 25));
                Configuration.add(StepCount);

                //Walker type input
                JLabel WalkerType = new JLabel("Walker Type:");
                Configuration.add(WalkerType);

                ButtonGroup WalkerTypes = new ButtonGroup();//group of buttons
                JRadioButton Standard = new JRadioButton("Standard");//creates a radio button. in a ButtonGroup, only one can be selected at a time
                Standard.setActionCommand("standard");//can be grabbed to see which button is active
                Standard.setSelected(true);//set this one as selected by default
                JRadioButton Picky = new JRadioButton("Picky");
                Picky.setActionCommand("picky");
                WalkerTypes.add(Standard); //add to panel
                WalkerTypes.add(Picky);
                Configuration.add(Standard); //set as part of group
                Configuration.add(Picky);

                //Walker type input
                JLabel Geometry = new JLabel("World Geometry:");
                Configuration.add(Geometry);
                ButtonGroup Geometries = new ButtonGroup();
                JRadioButton Bounded = new JRadioButton("Bounded");
                Bounded.setActionCommand("bounded");
                Bounded.setSelected(true);
                JRadioButton Toroidal = new JRadioButton("Toroidal");
                Toroidal.setActionCommand("toroidal");
                Geometries.add(Bounded);
                Geometries.add(Toroidal);
                Configuration.add(Bounded);
                Configuration.add(Toroidal);

                //Path Rendering input
                JLabel Render = new JLabel("Path Render:");
                Configuration.add(Render);
                ButtonGroup Renderers = new ButtonGroup();
                JRadioButton Black = new JRadioButton("Black");
                Black.setActionCommand("black");

                Black.setSelected(true);
                JRadioButton Gradient = new JRadioButton("Gradient");
                Gradient.setActionCommand("gradient");

                JLabel c1 = new JLabel("Gradient Start:");
                JTextField color1 = new JTextField("0000FF");
                JLabel c2 = new JLabel("Gradient End:");
                JTextField color2 = new JTextField("FFAA00");
                Renderers.add(Black);
                Renderers.add(Gradient);

                Configuration.add(Black);
                Configuration.add(Gradient);
                Configuration.add(c1);
                Configuration.add(color1);
                Configuration.add(c2);
                Configuration.add(color2);

                //Run Button
                JButton Run = new JButton("Walk");
                Run.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int count = Integer.parseInt(StepCount.getText()); //get from a TextField, and read as an int
                        String walk_type = WalkerTypes.getSelection().getActionCommand();//gets the action command of which radio button is selected
                        String geom_type = Geometries.getSelection().getActionCommand();
                        String render_type = Renderers.getSelection().getActionCommand();
                        int grad_start = (int) Long.parseLong(color1.getText(), 16);//Hex string to int
                        int grad_end = (int) Long.parseLong(color2.getText(), 16);//Hex string to int

                        //===Walk, Update Display, repaint===
                        //1. Generate a Buffered image using the data from above (return one from a method)?
                        //2. UpdateDisplay([[insert the image you made]]);
                        // Create an instance of the selected walker type
                        Walker walker = null;
                        if (walk_type.equals("standard")) {
                            walker = new StandardWalker(WIDTH / 2, HEIGHT / 2); // Start the walker in the center
                        } else if (walk_type.equals("picky")) {
                            walker = new PickyWalker();
                        }


                        // Create an instance of the selected world geometry type
                        WorldGeometry worldGeometry = null;
                        if (geom_type.equals("bounded")) {
                            worldGeometry = new BoundedWorld();
                        } else if (geom_type.equals("toroidal")) {
                            worldGeometry = new ToroidalWorld();
                        }

                        // Create an instance of the selected path renderer type
                        PathRenderer pathRenderer = null;
                        if (render_type.equals("black")) {
                            pathRenderer = new BlackPathRenderer();
                        } else if (render_type.equals("gradient")) {
                            pathRenderer = new GradientPathRenderer(grad_start, grad_end);
                        }

                        // Simulate the walker's path
                        BufferedImage simulationResult = simulateWalkerPath(walker, worldGeometry, pathRenderer, count);

                        // Update the display with the simulation result
                        UpdateDisplay(simulationResult);
                        window.repaint();
                    }
                });
                Configuration.add(Run);
                window.add(Configuration, BorderLayout.EAST);

            }

        });



    }




static BufferedImage simulateWalkerPath(Walker walker, WorldGeometry worldGeometry, PathRenderer pathRenderer, int count) {
        // Create a BufferedImage to store the simulation result
        BufferedImage resultImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);

        // Create a Graphics2D object to draw on the resultImage
        Graphics2D g2d = resultImage.createGraphics();

        // Initialize walker position
        int x = walker.getX();
        int y = walker.getY();

        // Simulate walker's path
        for (int i = 0; i < count; i++) {
        // Update walker position based on its type
        walker.step();

        // Handle world geometry (bounded or toroidal)
        if (worldGeometry instanceof BoundedWorld) {
        // Handle bounded world logic here
        // Check if the walker is out of bounds and adjust position if needed
        } else if (worldGeometry instanceof ToroidalWorld) {
        // Handle toroidal world logic here
        // Implement logic to wrap the walker around the world's edges if needed
        }

        // Render the walker's path using the selected path renderer
        pathRenderer.renderPath(resultImage, x, y, Color.BLACK);

        // Update the walker's current position
        x = walker.getX();
        y = walker.getY();
        }

        // Dispose of the Graphics2D object
        g2d.dispose();

        return resultImage;
        }
    //A method to update the display image to match one generated by you
    static void UpdateDisplay(BufferedImage img){
        //Below 4 lines draws the input image on the display image
        Graphics2D g = (Graphics2D) Display.getGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0,0,WIDTH,HEIGHT);
        g.drawImage(img,0,0,null);

        //forces the window to redraw its components (i.e., the image)
        window.repaint();
    }

}
































