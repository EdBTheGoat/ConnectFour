package connect_four_gui;

public class Show_CFOUR {

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(
				new Runnable() {
			
			@Override
			public void run() {
				C_FOUR yer = new C_FOUR();
			}
		});
	}

}
