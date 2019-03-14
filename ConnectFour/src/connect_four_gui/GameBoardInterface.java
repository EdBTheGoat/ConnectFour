package connect_four_gui;

public interface GameBoardInterface {
	public void displayBoard();
	public void clearBoard();

	public boolean isEmpty();
	public boolean isFull();
}