package snakepackage;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.Event.*;
import enums.GridSize;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * @author jd-
 *
 */
public class SnakeApp {

    private static SnakeApp app;
    public static final int MAX_THREADS = 8;
    Snake[] snakes = new Snake[MAX_THREADS];
    private static final Cell[] spawn = {
        new Cell(1, (GridSize.GRID_HEIGHT / 2) / 2),
        new Cell(GridSize.GRID_WIDTH - 2,
        3 * (GridSize.GRID_HEIGHT / 2) / 2),
        new Cell(3 * (GridSize.GRID_WIDTH / 2) / 2, 1),
        new Cell((GridSize.GRID_WIDTH / 2) / 2, GridSize.GRID_HEIGHT - 2),
        new Cell(1, 3 * (GridSize.GRID_HEIGHT / 2) / 2),
        new Cell(GridSize.GRID_WIDTH - 2, (GridSize.GRID_HEIGHT / 2) / 2),
        new Cell((GridSize.GRID_WIDTH / 2) / 2, 1),
        new Cell(3 * (GridSize.GRID_WIDTH / 2) / 2,
        GridSize.GRID_HEIGHT - 2)};
    private JFrame frame;
    private JButton Play;
    private JButton Pause;
    private JButton Finish;
    private static Board board;
    int nr_selected = 0;
    Thread[] thread = new Thread[MAX_THREADS];
    static boolean play,play2 = true;
    int primermuerto=0;

    public SnakeApp() {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        frame = new JFrame("The Snake Race");
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // frame.setSize(618, 640);
        frame.setSize(GridSize.GRID_WIDTH * GridSize.WIDTH_BOX + 17,
                GridSize.GRID_HEIGHT * GridSize.HEIGH_BOX + 40);
        frame.setLocation(dimension.width / 2 - frame.getWidth() / 2,
                dimension.height / 2 - frame.getHeight() / 2);
        board = new Board();
        
        
        frame.add(board,BorderLayout.CENTER);
        Play =new JButton("Play");
        Pause =new JButton("Pause");
        Finish =new JButton("Reanudar");
        JPanel actionsBPabel=new JPanel();
        actionsBPabel.setLayout(new FlowLayout());
        actionsBPabel.add(Play);
        actionsBPabel.add(Pause);
        actionsBPabel.add(Finish);
        frame.add(actionsBPabel,BorderLayout.SOUTH);

    }

    public static void main(String[] args) {
        app = new SnakeApp();
        app.prepareAcciones();
        app.init();
        
    }

    private void init() {
        
        
        
        for (int i = 0; i != MAX_THREADS; i++) {
            
            snakes[i] = new Snake(i + 1, spawn[i], i + 1);
            snakes[i].addObserver(board);
            thread[i] = new Thread(snakes[i]);
            //thread[i].start();
        }

        frame.setVisible(true);

            
        while (true) {
			int x = 0;
			for (int i = 0; i != MAX_THREADS; i++) {
				if (snakes[i].isSnakeEnd() == true) {
					if (primermuerto == 0) {

						primermuerto = snakes[i].getIdt();
					}
					x++;
				}
			}
			if (x == MAX_THREADS) {
				break;
			}
        }


        System.out.println("Thread (snake) status:");
        for (int i = 0; i != MAX_THREADS; i++) {
            System.out.println("["+i+"] :"+thread[i].getState());
        }
        

    }
    public void prepareAcciones() {
    	
    	Play.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				play();
				
			}
    	});
    	Pause.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				try {
					pause();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
    	});
    	Finish.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				reanudar();
				
			}
    	});
    }

    protected void reanudar() {
    	for (int i = 0; i != MAX_THREADS; i++) {
    		snakes[i].setPause(false);
            snakes[i].setRenau();
         
        }
	}

	public  void pause() throws InterruptedException {
		
            for (int i = 0; i != MAX_THREADS; i++) {
                snakes[i].setPause(true);
                System.out.println(thread[i].getState());
            }
            JOptionPane.showMessageDialog(null, "PAUSE"+ "\n\nLa serpiente mas larga es: "+mejor()[1]+" de tañano: "+ mejor()[0]+"\nLa primera en morir fue: "+primermuerto);
           
        }

	private int[] mejor() {
		int[] a = new int[2];
		a[0] = 0;
		for (int i = 0; i != MAX_THREADS; i++) {
			if (a[0] < snakes[i].getBody().size()) {
				a[0] = snakes[i].getBody().size();
				a[1]= snakes[i].getIdt();
			}

		}
		return a;
	}

	public void play() {
		if(play2) {
		for (int i = 0; i != MAX_THREADS; i++) {
			thread[i].start();
		}
		play2=false;
		}
	}

	public static SnakeApp getApp() {
        return app;
    }

}
