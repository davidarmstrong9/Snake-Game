package project;

import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Snake {
	
	//TODO: Implement the game of snake
	public static void main(String[] args) {
		//Initializations
		boolean running = true;
		boolean eating = false;
		int direction = 2;
		List<body> list = new ArrayList<body> ();
		list.add (new body (.05, .45));
		list.add (new body (.075, .45));
		list.add (new body (.1, .45));
		list.add (new body (.125, .45));
		list.add (new body (.15, .45));
		list.add (new body (.175, .45));
		list.add (new body (.2, .45));
		apple food = new apple(.75,.45);
		int count = 0;
		StdDraw.setScale(-.02,1.015);
		List<Score> scoreList = new ArrayList<Score> ();


		// Run Game
		while (running) {
			StdDraw.clear (Color.lightGray);

			// Draw Snake and Apple
			for (body e : list) {
				e.draw ();
			}
			food.draw();

			// Eating
			if(Math.hypot (list.get((list.size ()-1)).getY()-food.getY(),list.get((list.size ()-1)).getX()-food.getX())<.06){
				food = new apple(Math.random (),Math.random());
				eating=true;
				count ++;
			}
			StdDraw.text(.15,.95,"Count: " + count);

			// Move Snake
			if (!eating) {
				list.remove (0);
			}
			eating=false;

			// Changing Directions
			if ((StdDraw.isKeyPressed (KeyEvent.VK_LEFT) || direction == 0) && direction!=2) {
				direction = 0;
				list.add (new body (list.get (list.size () - 1).getX () - .025, list.get (list.size () - 1).getY ()));
			}
			if ((StdDraw.isKeyPressed (KeyEvent.VK_UP) || direction == 1) && direction!=3) {
				list.add (new body (list.get (list.size () - 1).getX (), list.get (list.size () - 1).getY () + .025));
				direction = 1;
			}
			if ((StdDraw.isKeyPressed (KeyEvent.VK_RIGHT) || direction == 2) && direction!=0) {
				list.add (new body (list.get (list.size () - 1).getX () + .025, list.get (list.size () - 1).getY ()));
				direction = 2;
			}
			if ((StdDraw.isKeyPressed (KeyEvent.VK_DOWN) || direction == 3) && direction!=1) {
				list.add (new body (list.get (list.size () - 1).getX (), list.get (list.size () - 1).getY () - .025));
				direction = 3;
			}

			// Hitting Stuff
			for (body e : list) {
				if(e!=list.get(list.size()-1)) {
					if (e.getX () == list.get (list.size () - 1).getX () && e.getY () == list.get (list.size () - 1).getY ()) {
						running = false;
					}
				}
			}
			if(list.get (list.size ()-1).getX()<0 || list.get (list.size ()-1).getX()>1 || list.get (list.size ()-1).getY()<0 || list.get (list.size ()-1).getY()>1) {
				running = false;
			}


			StdDraw.show(50);
		}
	}
}
