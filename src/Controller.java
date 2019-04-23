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
	final int frameWidth;
	final int frameHeight;
	
	
	//Controller Constructor
	public Controller () {
		view = new View();
		model = new Model(view.getFrameWidth(), view.getFrameHeight(), view.getRedKnot(), view.getClapperRail(), view.getMapRN(), view.getItems(), view.getCRitems(), view.getScoreBoard(), view.getStatusBar());
		
		view.button_redknote.addActionListener(this);
		view.button_clapperrail.addActionListener(this);
		view.button_menu.addActionListener(this);
		view.addKeyListener(this);
		this.frameWidth = view.getFrameWidth();
		this.frameHeight = view.getFrameHeight();
//		view.button_redknote.addKeyListener(this);
//		view.button_clapperrail.addKeyListener(this);
	}
	
	//Uses ActionListener to run the start the program
	public void start() {
		drawAction = new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				model.updateLocation();
				view.update(model.getRedKnot(), model.getClapperrail(), model.getMapRN(), model.getGamestatus(), model.getScoreBoard(), model.getItems(), model.getCRitems());
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
		if(model.getGamestatus() == GameStatus.RN) {
			
			if(keyCode == KeyEvent.VK_UP) {
				model.getRedKnot().setyVel(-10);
			}else if(keyCode == KeyEvent.VK_LEFT) {
				model.getRedKnot().setxVel(-10);
			}else if(keyCode == KeyEvent.VK_DOWN) {
				model.getRedKnot().setyVel(10);
			}else if(keyCode == KeyEvent.VK_RIGHT) {
				model.getRedKnot().setxVel(10);
			}
		}else if(model.getGamestatus() == GameStatus.CR) {
			
			if(keyCode == KeyEvent.VK_UP) {
				model.getClapperrail().setY(frameHeight/2-32-100);
			}else if(keyCode == KeyEvent.VK_LEFT) {
				model.getClapperrail().setX(frameWidth/2-32-100);
			}else if(keyCode == KeyEvent.VK_DOWN) {
				model.getClapperrail().setY(frameHeight/2-32+100);
			}else if(keyCode == KeyEvent.VK_RIGHT) {
				model.getClapperrail().setX(frameWidth/2-32+100);
			}
		}
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if(model.getGamestatus() == GameStatus.RN) {
			if(keyCode == KeyEvent.VK_UP) {
				model.getRedKnot().setyVel(0);
			}else if(keyCode == KeyEvent.VK_LEFT) {
				model.getRedKnot().setxVel(0);
			}else if(keyCode == KeyEvent.VK_DOWN) {
				model.getRedKnot().setyVel(0);
			}else if(keyCode == KeyEvent.VK_RIGHT) {
				model.getRedKnot().setxVel(0);
			}
		}else if(model.getGamestatus() == GameStatus.CR) {

			if(keyCode == KeyEvent.VK_UP) {
				
				model.getClapperrail().setY(frameHeight/2-32);
			}else if(keyCode == KeyEvent.VK_LEFT) {
				model.getClapperrail().setX(frameWidth/2-32);
			}else if(keyCode == KeyEvent.VK_DOWN) {
				model.getClapperrail().setY(frameHeight/2-32);
			}else if(keyCode == KeyEvent.VK_RIGHT) {
				model.getClapperrail().setX(frameWidth/2-32);
			}
		}
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
		case "redKnot":
			model.setGamestatus(GameStatus.RN);
			break;
			
		case "clapperRail":
			model.setGamestatus(GameStatus.CR);
			break;
			
		case "menu":
			model.setGamestatus(GameStatus.Menu);
			break;
		}
		
		view.requestFocusInWindow();
		
	}

}
