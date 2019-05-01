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
		model = new Model(view.getFrameWidth(), view.getFrameHeight(), view.getRedKnot(), view.getClapperRail(), view.getMapRN(), 
				view.getItems(), view.getCRitems(), view.getScoreBoard(), view.getStatusBar(), view.getQuiz_RN(), view.getQuiz_CR(),
				view.isAnswerRightFlag(), view.isAnswerWrongFlag());
		
		view.button_redknote.addActionListener(this);
		view.button_clapperrail.addActionListener(this);
		view.button_menu.addActionListener(this);
		view.button_submit.addActionListener(this);
		
		view.button_A.addActionListener(this);
		view.button_B.addActionListener(this);
		view.button_C.addActionListener(this);
		view.button_D.addActionListener(this);
		
		//view.button_submit;
		view.addKeyListener(this);
		this.frameWidth = view.getFrameWidth();
		this.frameHeight = view.getFrameHeight();

	}
	
	//Uses ActionListener to run the start the program
	public void start() {
		drawAction = new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				model.updateLocation();
				view.update(model.getRedKnot(), model.getClapperrail(), model.getMapRN(),
						model.getGamestatus(), model.getScoreBoard(), model.getItems(), 
						model.getCRitems(), model.getQuiz_RN(), model.getQuiz_CR(),
						model.isAnswerRightFlag(), model.isAnswerWrongFlag());
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
				model.getClapperrail().setY(frameHeight/2-200-100);
			}else if(keyCode == KeyEvent.VK_LEFT) {
				model.getClapperrail().setX(frameWidth/2-200-100);
			}else if(keyCode == KeyEvent.VK_DOWN) {
				model.getClapperrail().setY(frameHeight/2-50+100);
			}else if(keyCode == KeyEvent.VK_RIGHT) {
				model.getClapperrail().setX(frameWidth/2-50+100);
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
				model.getClapperrail().setY(frameHeight/2-100);
			}else if(keyCode == KeyEvent.VK_LEFT) {
				model.getClapperrail().setX(frameWidth/2-100);
			}else if(keyCode == KeyEvent.VK_DOWN) {
				model.getClapperrail().setY(frameHeight/2-100);
			}else if(keyCode == KeyEvent.VK_RIGHT) {
				model.getClapperrail().setX(frameWidth/2-100);
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
		case "submit":
			if(model.getGamestatus().equals(GameStatus.RNQUIZ)) {
				if(model.getQuiz_RN().getSelected().equals(model.getQuiz_RN().getQuestions().get(model.getQuiz_RN().getQuestionIndex()).correctanswer)) {
					model.setAnswerRightFlag(true);
					model.setAnswerWrongFlag(false);
				}else {
					model.setAnswerRightFlag(false);
					model.setAnswerWrongFlag(true);
				}
			}else if (model.getGamestatus().equals(GameStatus.CRQUIZ)){
				
			}
			break;
		case "A":
			view.button_submit.setEnabled(true);
			if(model.getGamestatus().equals(GameStatus.RNQUIZ)) {
				model.getQuiz_RN().setSelected("A");
			}else if (model.getGamestatus().equals(GameStatus.CRQUIZ)){
				model.getQuiz_CR().setSelected("A");
			}
			
			break;
		case "B":
			view.button_submit.setEnabled(true);
			if(model.getGamestatus().equals(GameStatus.RNQUIZ)) {
				model.getQuiz_RN().setSelected("B");
			}else if (model.getGamestatus().equals(GameStatus.CRQUIZ)){
				model.getQuiz_CR().setSelected("B");
			}
			break;
		case "C":
			view.button_submit.setEnabled(true);
			if(model.getGamestatus().equals(GameStatus.RNQUIZ)) {
				model.getQuiz_RN().setSelected("C");
			}else if (model.getGamestatus().equals(GameStatus.CRQUIZ)){
				model.getQuiz_CR().setSelected("C");
			}
			break;
		case "D":
			view.button_submit.setEnabled(true);
			if(model.getGamestatus().equals(GameStatus.RNQUIZ)) {
				model.getQuiz_RN().setSelected("D");
			}else if (model.getGamestatus().equals(GameStatus.CRQUIZ)){
				model.getQuiz_CR().setSelected("D");
			};
			break;
		}
		
		view.requestFocusInWindow();
		
	}

}
