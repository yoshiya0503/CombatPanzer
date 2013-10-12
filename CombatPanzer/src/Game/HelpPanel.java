package Game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JPanel;

import MainApp.MainFrame;

public class HelpPanel extends JPanel{
	final int SIZE = 500;
	private MainFrame mainframe;
	private JButton returnbutton;
	
	public HelpPanel(MainFrame frame){
		this.mainframe = frame;
		//ÉpÉlÉãÇÃê∂ê¨
		setPreferredSize(new Dimension(this.SIZE,this.SIZE));
		setBackground(Color.WHITE);
		
		this.returnbutton = new JButton("ñﬂÇÈ");
		this.add(returnbutton);
		this.returnbutton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event) {
				mainframe.getContentPane().add(mainframe.start);
				mainframe.getContentPane().remove(mainframe.help);
				mainframe.help.setVisible(false);
				mainframe.start.setVisible(true);
				mainframe.pack();
			}
		});
	}	
}
