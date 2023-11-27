package com.mycompany.a3;


import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.util.UITimer;

public class Game extends Form implements Runnable {
	
	private GameWorld gw;
	private MapView mv;
	private ScoreView sv;
	private int tickRate = 20;
	private UITimer timer;
	
	private Button accelerateButton = new Button();
	private Button brakeButton = new Button();
	private Button rightButton = new Button();
	private Button leftButton = new Button();
	private CheckBox soundCheckBox = new CheckBox();
	private Button positionButton = new Button();
	private Button pauseButton = new Button();
	
	private Toolbar toolBar = new Toolbar();
	
	private Accelerate accelerate;
	private Brake brake;
	private About about;
	private Quit quit;
	private Left left;
	private Right right;
	private Help help;
	private SoundCommand sound;
	private Consume consume;
	private Position position;
	private Pause pause;
	
	
	
	
	public Game() {

		// Creating a border layout for the GUI
		this.setLayout(new BorderLayout());

		gw = new GameWorld();
		mv = new MapView(gw);
		sv = new ScoreView(gw);

		gw.addObserver(mv);
		gw.addObserver(sv);

		// Generate a new tool bar and assign the current layout to it
		//Toolbar toolBar = new Toolbar();
		this.setToolbar(toolBar);

		// Set the title 
		toolBar.setTitle("The Journey Game");

		// Creating the south, east, and west containers
		Container west = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		west.getAllStyles().setBorder(Border.createLineBorder(1, ColorUtil.BLACK));
		
		Container south = new Container(new FlowLayout(Component.CENTER));
		south.getAllStyles().setBorder(Border.createLineBorder(1, ColorUtil.BLACK));
		
		Container east = new Container((new BoxLayout(BoxLayout.Y_AXIS)));
		east.getAllStyles().setBorder(Border.createLineBorder(1, ColorUtil.BLACK));

		
		// Adding the containers to the various region of the GUI
		this.add(BorderLayout.NORTH, sv);
		this.add(BorderLayout.EAST, east);
		this.add(BorderLayout.SOUTH, south);
		this.add(BorderLayout.WEST, west);
		this.add(BorderLayout.CENTER, mv);
		
	
		// Initializing required commands
		accelerate = new Accelerate(gw);
		brake = new Brake(gw);
		left = new Left(gw);
		right = new Right(gw);
		consume = new Consume(gw);
		//FlagCollide flagCollide = new FlagCollide(gw);
		//FoodCollide foodCollide = new FoodCollide(gw);
		//SpiderCollide spiderCollide = new SpiderCollide(gw);
		//ClockTick tick = new ClockTick(gw);
		sound = new SoundCommand(gw, toolBar);
		about = new About();
		help = new Help();
		quit = new Quit(gw);
		position = new Position(gw);
		pause = new Pause(this);
		

		// Modify the tool bar by adding several command objects to it
		toolBar.getAllStyles().setBgTransparency(255);
		toolBar.addCommandToRightBar(help);
		toolBar.addCommandToSideMenu(accelerate);
		toolBar.addCommandToSideMenu(about);
		toolBar.addCommandToSideMenu(quit);
				
		// Creating instance of Sound object and a new check box
		soundCheckBox.setCommand(sound);
		toolBar.addComponentToSideMenu(soundCheckBox);
		soundCheckBox.getAllStyles().setBgTransparency(255);
		soundCheckBox.getAllStyles().setPadding(TOP, 3);
		soundCheckBox.getAllStyles().setPadding(BOTTOM, 3);
		soundCheckBox.getAllStyles().setBgColor(ColorUtil.rgb(137, 155, 178));
		soundCheckBox.getAllStyles().setFgColor(ColorUtil.WHITE);
		//soundCheckBox.setFocusable(false);	
		
		// Creating side container buttons with styling
		// Create a brake command button 
		//Button brakeButton = new Button();
		east.add(brakeButton);
		brakeButton.getAllStyles().setMarginTop(100);
		brakeButton.setCommand(brake);
		brakeButton.getAllStyles().setPadding(TOP, 3);
		brakeButton.getAllStyles().setPadding(BOTTOM, 3);
		brakeButton.getAllStyles().setBgTransparency(255);
		brakeButton.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		brakeButton.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
		brakeButton.getUnselectedStyle().setBorder(Border.createLineBorder(3,ColorUtil.BLACK));
		
		// Creating a right command button 
		//Button rightButton = new Button();
		east.add(rightButton);
		rightButton.setCommand(right);
		rightButton.getAllStyles().setPadding(TOP, 3);
		rightButton.getAllStyles().setPadding(BOTTOM, 3);
		rightButton.getAllStyles().setBgTransparency(255);
		rightButton.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		rightButton.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
		rightButton.getUnselectedStyle().setBorder(Border.createLineBorder(3,ColorUtil.BLACK));
		
		// Create an accelerate command button 
		//Button accelerateButton = new Button();
		west.add(accelerateButton);
		accelerateButton.getAllStyles().setMarginTop(100);
		accelerateButton.setCommand(accelerate);
		accelerateButton.getAllStyles().setPadding(TOP, 3);
		accelerateButton.getAllStyles().setPadding(BOTTOM, 3);
		accelerateButton.getAllStyles().setBgTransparency(255);
		accelerateButton.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		accelerateButton.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
		accelerateButton.getUnselectedStyle().setBorder(Border.createLineBorder(3,ColorUtil.BLACK));
		
		// Creating a left command button 
		//Button leftButton = new Button();
		west.add(leftButton);
		leftButton.setCommand(left);
		leftButton.getAllStyles().setPadding(TOP, 3);
		leftButton.getAllStyles().setPadding(BOTTOM, 3);
		leftButton.getAllStyles().setBgTransparency(255);
		leftButton.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		leftButton.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
		leftButton.getUnselectedStyle().setBorder(Border.createLineBorder(3,ColorUtil.BLACK));
		
		// Creating the south container buttons with styling
		//Creating a position button
		south.add(positionButton);
		positionButton.setCommand(position);
		positionButton.getAllStyles().setPadding(TOP, 3);
		positionButton.getAllStyles().setPadding(BOTTOM, 3);
		positionButton.getAllStyles().setBgTransparency(255);
		positionButton.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		positionButton.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
		positionButton.getUnselectedStyle().setBorder(Border.createLineBorder(3,ColorUtil.BLACK));
		
		//Creating a pause button
		south.add(pauseButton);
		pauseButton.setCommand(pause);
		pauseButton.getAllStyles().setPadding(TOP, 3);
		pauseButton.getAllStyles().setPadding(BOTTOM, 3);
		pauseButton.getAllStyles().setBgTransparency(255);
		pauseButton.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		pauseButton.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
		pauseButton.getUnselectedStyle().setBorder(Border.createLineBorder(3,ColorUtil.BLACK));

		//Adding keyListeners to the specific commands below
		this.addKeyListener('b', brake);
		this.addKeyListener('r', right);
		this.addKeyListener('a', accelerate);
		this.addKeyListener('l', left);
		this.addKeyListener('c', consume);
		
		this.show();
		gw.init();
		gw.createSounds();
		
		
		// Setting width and height of the GameWorld
		gw.setWidth(mv.getWidth());
		gw.setHeight(mv.getHeight());
		
		this.revalidate();
		
		timer = new UITimer(this);
		timer.schedule(tickRate, true, this);
		gw.setTickRate(tickRate);

	}
	
	@Override
	public void run() {
		gw.tick();
		
	}

	public void pause() {
		if (gw.getPaused() == false) {					
			pauseButton.setText("Play");					
			gw.pauseSound();								
			gw.setPaused(true);					
			timer.cancel();
				
			positionButton.setEnabled(true);
			accelerateButton.setEnabled(false);	
			brakeButton.setEnabled(false);			
			rightButton.setEnabled(false);			
			leftButton.setEnabled(false);			
			soundCheckBox.setEnabled(false);
								
			toolBar.removeCommand(about);			
			toolBar.removeCommand(quit);			
			toolBar.removeCommand(help);			
			toolBar.removeCommand(accelerate);			
								
			this.removeKeyListener('r', right);			
			this.removeKeyListener('l', left);			
			this.removeKeyListener('a', accelerate);			
			this.removeKeyListener('b', brake);								
		}						
		else {							
			pauseButton.setText("Pause");								
			gw.setPosition(false);								
			gw.playSound();								
			gw.setPaused(false);					
			
			timer.schedule(20, true, this);			
			
			positionButton.setEnabled(false);		
			accelerateButton.setEnabled(true);			
			brakeButton.setEnabled(true);			
			rightButton.setEnabled(true);			
			leftButton.setEnabled(true);					
			soundCheckBox.setEnabled(true);
								
			
			toolBar.addCommandToRightBar(help);			
			toolBar.addCommandToSideMenu(accelerate);			
			toolBar.addCommandToSideMenu(about);			
			toolBar.addCommandToSideMenu(quit);
								
			this.addKeyListener('b', brake);			
			this.addKeyListener('r', right);			
			this.addKeyListener('a', accelerate);			
			this.addKeyListener('l', left);			
			this.addKeyListener('c', consume);
					
			
		}
		
	}

	
}
