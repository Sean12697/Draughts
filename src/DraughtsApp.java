import java.util.Scanner;

public class DraughtsApp
{

	public static Scanner in = new Scanner(System.in);

	public static void main (String [] args)
	{
		DraughtsBoard GameBoard = new DraughtsBoard();

		final int BLANK = 0;
		final int BLACK = 1;
		final int WHITE = 2;
		final int BLACKKING = 3;
		final int WHITEKING = 4;

		int X, Y;
		int newX, newY;
		newX = 10;
		newY = 10;
		int Piece = 0;
		int playersPiece = 0;
		int enemyPiece = 0;
		X=0;
		Y=0;
		int moves = 0;

		while (X != -1)
		{		
			System.out.println();
			GameBoard.draw();
			System.out.println("\nEnter -1 to quit");

			if (moves % 2 == 0) {
				System.out.print("It is Whites move!\n");
				playersPiece = 2;
				enemyPiece = 1;
			} else {
				System.out.print("It is Blacks move!\n");
				playersPiece = 1;
				enemyPiece = 2;
			}

			System.out.print("Enter X coordinate: ");
			X = in.nextInt();
			if (X != -1)
			{
				System.out.print("Enter Y coordinate: ");
				Y = in.nextInt();

				Piece = GameBoard.get_square(X-1, Y-1);
				System.out.print("\nSquare at "+X+","+Y+" is ");
				switch (Piece)
				{
				case BLANK:
					System.out.println("blank");
					break;

				case BLACK:
					System.out.println("a black piece");
					break;

				case WHITE:
					System.out.println("a white piece");
					break;

				case BLACKKING:
					System.out.println("a black king piece");
					break;

				case WHITEKING:
					System.out.println("a white king piece");
					break;
				}

					if (Piece == playersPiece || Piece == playersPiece + 2) { //Tests if user has selected one of their pieces
						if (hasValidMove()) { //has a valid move
						newX = 0;
						newY = 0;

						while (!(newX > 0 && newX < 9)) { //On Board
							System.out.print("Enter a new X coordinate: ");
							newX = in.nextInt();
						}
						while (!(newY > 0 && newY < 9)) { //On Board
							System.out.print("Enter a new Y coordinate: ");
							newY = in.nextInt();
						}

						//Rules
						//Single pieces must move towards the opponent's side of the board.
						if ((Piece == BLACK && newY - Y == -1) || (Piece == WHITE && newY - Y == 1) || Piece > 2) {
							//All moves are made on the diagonals.
							if (Math.abs(newX - X) == 1) {
								System.out.println(newX + ", " + newY + ": Valid");
								//Sets (negate one to transform position to actual index)
								GameBoard.set_square(X - 1, Y - 1, BLANK);
								GameBoard.set_square(newX - 1, newY - 1, Piece);
								moves++;
								//Invalid
							} else { System.out.println(newX + ", " + newY + ": Invalid (Must move diagonally)"); }
							//Jumping over player
						} else if ((Piece == BLACK && newY - Y == -2) || (Piece == WHITE && newY - Y == 2) || (Piece > 2 && Math.abs(newY - Y) == 2)) {
							if (Math.abs(newX - X) == 2) {
								//Gets difference then adds onto original, to gain the inbertween
								int eX = ((X - newX) / 2) + newX;
								int eY = ((Y - newY) / 2) + newY;
								int getSquare = GameBoard.get_square(eX - 1, eY - 1);
								//There is an enemy piece between (negate one to transform position to actual index)
								if (getSquare == enemyPiece || getSquare == enemyPiece + 2) {
									GameBoard.set_square(X - 1, Y - 1, BLANK);
									GameBoard.set_square(eX - 1, eY - 1, BLANK); //Take piece
									GameBoard.set_square(newX - 1, newY - 1, Piece);
									System.out.println("\nEnemy piece taken!");
									moves++;
								} else { System.out.println(newX + ", " + newY + ": Invalid (There must be an enemy to jump over)"); }
							} else { System.out.println(newX + ", " + newY + ": Invalid (Must jump over)"); }
						} else { System.out.println(newX + ", " + newY + ": Invalid (Must move towards enemy)"); }
					} else { System.out.println(newX + ", " + newY + ": Cannot move"); }
				} else { System.out.println(newX + ", " + newY + ": Is not one of your pieces."); }
			}
		}
	}
	
	public static boolean hasValidMove() {
		return true;
	}
}