import ui.HomeFrame;

/**
 * The entry point for the N3N3 Ta7weesh application.
 * <p>
 * This class launches the HomeFrame GUI using the Swing event dispatch thread.
 * </p>
 */
public class Main {
     /**
     * The main method that starts the application.
     *
     * @param args the command-line arguments (not used)
     */
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            new HomeFrame().setVisible(true);
        });
    }
}