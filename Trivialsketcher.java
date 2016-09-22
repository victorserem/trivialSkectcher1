package trivialsketcher;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class Trivialsketcher  extends JFrame{
    private ArrayList<ArrayList<Point>> Strokes = new ArrayList<>();
    private ArrayList<Color> color = new ArrayList<Color>();
    private JButton button = new JButton("button");
    private Color k = (Color.WHITE);
    private JPanel p= new JPanel();
    public Trivialsketcher  () {
        
        p = new JPanel();
        p.setBackground(Color.WHITE);
        button= new JButton ("pick one color");
        button.addActionListener(
        new ActionListener(){          
            public void actionPerformed (ActionEvent event)
            {                
            
              k = (JColorChooser.showDialog(null, "choice color", k));
                
                Graphics g = getGraphics();
                if (k == null)
                    k=(Color.WHITE);
                
            }                    
        }
        );
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                  ArrayList<Point> lastPoint = new ArrayList();
                lastPoint.add(new Point(e.getX(), e.getY()));
                 Strokes.add(lastPoint);
                 color.add(k);
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                Graphics g = getGraphics();
                int last = Strokes.size();
                int lastp = Strokes.get(last-1).size();
                Strokes.get(last-1).add(new Point(e.getX(), e.getY()));
                 g.setColor(k);
               g.drawLine( Strokes.get(last-1).get(lastp - 1).x,  Strokes.get(last-1).get(lastp -1).y,e.getX(), e.getY());              
                g.dispose();
                
            }
        });
        add(p, BorderLayout.CENTER);
        add(button, BorderLayout.SOUTH);
        setSize(600, 600);
        setVisible(true);
    }
    @Override
    public void paint(Graphics g){
        super.paint(g);
       
         for (int y = 0; y < Strokes.size(); y++) {
        int i = 0;
        g.setColor(color.get(y));
        for (int j = 1; j < Strokes.get(y).size(); j++) {

                g.drawLine(Strokes.get(y).get(i).x, Strokes.get(y).get(i).y,
                        Strokes.get(y).get(j).x, Strokes.get(y).get(j).y);
                i++;
        }
    }
    }
    
    public static void main(String [] args)
    {
        Trivialsketcher  s = new Trivialsketcher ();
        s.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}