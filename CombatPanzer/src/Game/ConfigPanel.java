package Game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JPanel;

import MainApp.MainFrame;

public class ConfigPanel extends JPanel implements Observer{
	final int SIZE = 500;
	private MainFrame mainframe;
	private JButton returnbutton;
	
	public ConfigPanel(MainFrame frame){
		this.mainframe = frame;
		//ÉpÉlÉãÇÃê∂ê¨
		setPreferredSize(new Dimension(this.SIZE,this.SIZE));
		setBackground(Color.GREEN);
		this.returnbutton = new JButton("ñﬂÇÈ");
		returnbutton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event) {	
				mainframe.getContentPane().add(mainframe.start);
				mainframe.getContentPane().remove(mainframe.config);
				mainframe.config.setVisible(false);
				mainframe.start.setVisible(true);
				mainframe.pack();
			}
			});
		this.add(returnbutton);
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		repaint();
	}

}
