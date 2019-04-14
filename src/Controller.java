import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Timer;

public class Controller implements ActionListener, KeyListener{
	private Model model;
	private View view;
	Action drawAction;
	final int drawDelay = 30;
	
	
	
	//Controller Constructor
	public Controller () {
		view = new View();
		model = new Model(view.getRedKnot(), view.getMapRN(), view.getItems());
		
		view.button_redknote.addActionListener(this);
		view.button_clapperrail.addActionListener(this);
		//view.button_redknote.addKeyListener(this);
	}
	
	//Uses ActionListener to run the start the program
	public void start() {
		drawAction = new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				model.updateLocation();
				view.update(model.getRedKnot(), model.getGamestatus());
			}
		};
		
		EventQueue.invokeLater(new Runnable(){
			public void run(){
				Timer t = new Timer(drawDelay, drawAction);
				t.start();
			}
		});
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if(keyCode == KeyEvent.VK_W) {
			model.getRedKnot().setyVel(-5);
		}else if(keyCode == KeyEvent.VK_A) {
			model.getRedKnot().setxVel(-5);
		}else if(keyCode == KeyEvent.VK_S) {
			model.getRedKnot().setyVel(5);
		}else if(keyCode == KeyEvent.VK_D) {
			model.getRedKnot().setxVel(5);
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if(keyCode == KeyEvent.VK_W) {
			model.getRedKnot().setyVel(0);
		}else if(keyCode == KeyEvent.VK_A) {
			model.getRedKnot().setxVel(0);
		}else if(keyCode == KeyEvent.VK_S) {
			model.getRedKnot().setyVel(0);
		}else if(keyCode == KeyEvent.VK_D) {
			model.getRedKnot().setxVel(0);
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if("redKnot".equals(e.getActionCommand())) {
			view.remove(view.button_redknote);
			view.remove(view.button_clapperrail);
			view.addKeyListener(this);
			model.setGamestatus(GameStatus.RN);
			
		}else if("clapperRail".equals(e.getActionCommand())) {
			model.setGamestatus(GameStatus.CR);
		}
		
	}

}
