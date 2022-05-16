package task2.model;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		System.out.println("Choose the type of Snake Game you want to play: 1(Classic) 2(PacSnake)");
		@SuppressWarnings("resource")
		Scanner scanner1 = new Scanner(System.in);
		int gameType = scanner1.nextInt();
		if (gameType == 1) {
			SnakeGame game1 = new SnakeGame();
			System.out.println(game1.getGameState());
			
			while ((game1.getGameState() != "You won!") && (game1.getGameState() != "You lost!"))  {
				
			    System.out.println("Your move? w(" + game1.getUpLabel() + ") " + "d(" + game1.getRightLabel() + ") " + "s(" + game1.getDownLabel() + ") " + "a(" + game1.getLeftLabel() + ")") ;
			
			    @SuppressWarnings("resource")
			    Scanner scanner = new Scanner(System.in);
			    String input = scanner.nextLine();
			
			    if (input.equals("w")) {
				    game1.pressUpButton();
				    System.out.println(game1.getGameState());
			    }
			    if (input.equals("d")) {
				    game1.pressRightButton();
				    System.out.println(game1.getGameState());
			    }
			    if (input.equals("s")) {
				    game1.pressDownButton();
				    System.out.println(game1.getGameState());
			    }
			    if (input.equals("a")) {
				    game1.pressLeftButton();
				    System.out.println(game1.getGameState());
			    }
			    if (input.equals("quit")) {
				    break;
			    }
		    }
		} else {
			SnakeGame2 game2 = new SnakeGame2();
			game2.storeFood();
			game2.storeObs();
			System.out.println(game2.getGameState());
			
			

			while ((game2.getGameState() != "You won!") && (game2.getGameState() != "You lost!"))  {
				System.out.println(game2.getPoint());
				System.out.println("Your move? w(" + game2.getUpLabel() + ") " + "d(" + game2.getRightLabel() + ") " + "s(" + game2.getDownLabel() + ") " + "a(" + game2.getLeftLabel() + ")" + " j(" + game2.getALabel() + ")") ;
				
				@SuppressWarnings("resource")
				Scanner scanner2 = new Scanner(System.in);
				String input = scanner2.nextLine();
				
				if (input.equals("w")) {
					game2.pressUpButton();
					System.out.println(game2.getGameState());
				}
				if (input.equals("d")) {
					game2.pressRightButton();
					System.out.println(game2.getGameState());
				}
				if (input.equals("s")) {
					game2.pressDownButton();
					System.out.println(game2.getGameState());
				}
				if (input.equals("a")) {
					game2.pressLeftButton();
					System.out.println(game2.getGameState());
				}
				if (input.equals("j")) {
					game2.pressAButton();
					System.out.println(game2.getGameState());
				}
				if (input.equals("quit")) {
					break;
				}
				if (input.equals("check")) {
					System.out.println(game2.getGameState());
				}
			}
		}
		
		
		
		
	}

}
