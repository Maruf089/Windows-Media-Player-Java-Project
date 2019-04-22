package Demo;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import java.awt.*;
import java.io.*;

import javax.imageio.*;
import javax.media.*;

public class MediaPlayer extends JFrame 
{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JMenu jMenu1;
	   JMenu jMenu2;
	    JMenuBar jMenuBar1;
	 JMenuItem jMenuItem1;
	 JFrame Mainframe;
	Player player;
    File file;
    /**
     * Creates new form NewJFrame
     * @return 
     */
    public MediaPlayer() {
        initComponents();
        setTitle( " Media Player Created by Maruf and Alamgir " ); 
        setSize(600,400);
        setLocation(500,100);
        
        
        
    }
                      
    @SuppressWarnings("deprecation")
	private void initComponents() {

        jMenuBar1 = new JMenuBar();
        jMenu1 = new JMenu();
        jMenuItem1 = new JMenuItem();
        jMenu2 = new JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jMenu1.setText("File");
        jMenuItem1.setText("OpenFile");
        try{
        Mainframe.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("C:/Users/MARUF HASAN.MARUF/Desktop/SampleJPGImage_100kbmb")))));
        }
        catch(Exception e)
        {
        	System.out.println("Can't Load Image");
        }
        
        
    
        jMenuItem1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);
        
    }                       

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {                                           
            try 
		 {
			openFile();
            createPlayer();
		 }
		 catch ( Exception e )
		 {
			 JOptionPane.showMessageDialog(null,"Invalid file or location", "Error loading file", JOptionPane.ERROR_MESSAGE );
		 }
    }

	

	 public void openFile()
	 {
		 JFileChooser fileChooser = new JFileChooser();
		 fileChooser.setFileSelectionMode( JFileChooser.FILES_ONLY );
		 int result = fileChooser.showOpenDialog( null );

	
		 if ( result == JFileChooser.CANCEL_OPTION )
			 file = null;
		 else
			 file = fileChooser.getSelectedFile();
	 }

	 
	 public void createPlayer()
	 {
		 if ( file == null )
			 return;

		 removePreviousPlayer();

		 try 
		 {
			 player = Manager.createPlayer( file.toURL() );  
	 
			 player.addControllerListener( new EventHandler() );
			 player.start();
		 }
		 catch ( Exception e )
		 {
			 JOptionPane.showMessageDialog(null,"Invalid file or location", "Error loading file", JOptionPane.ERROR_MESSAGE );
		 }
	 }

	 private void removePreviousPlayer()
	 {
		 if ( player == null )
			 return;

		 player.close();

		 Component visual = player.getVisualComponent();
		 Component control = player.getControlPanelComponent();

		 Container container = getContentPane();

		 if ( visual != null )
			 container.remove( visual );

	
		 if ( control != null )
			 container.remove( control );
	 }

	 public static void main(String args[])
	 {
		 MediaPlayer displayFrame = new MediaPlayer();
		 new MediaPlayer().setVisible(true);

	 }


	 private class EventHandler implements ControllerListener 
	 {
		 public void controllerUpdate( ControllerEvent e ) 
		 {
			 if ( e instanceof RealizeCompleteEvent ) 
			 {
				 Container container = getContentPane();
				 
				 
				 Component visualComponent = player.getVisualComponent();

				 if ( visualComponent != null )
					 getContentPane().add( visualComponent, BorderLayout.CENTER );

				 Component controlsComponent = player.getControlPanelComponent();

				 if ( controlsComponent != null )
					 getContentPane().add( controlsComponent, BorderLayout.SOUTH );
				 

				 getContentPane().doLayout();
				 

			 }
		 }
	 }
}