package Game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import MainApp.MainFrame;
public class StartPanel extends JPanel{
	final int SIZE = 600;
	private MainFrame mainframe;
	private JButton startbutton;
	private JButton helpbutton;
	private JButton optionbutton;
	private JButton endbutton;
	
	public StartPanel(MainFrame frame){
		this.mainframe = frame;
		//setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
		//�p�l���̐���
		setPreferredSize(new Dimension(this.SIZE,this.SIZE));
		setBackground(Color.WHITE);
		//�^�C�g���̐���
		//JLabel title = new JLabel("COMBAT PANZER!!");
		//add(title);
		
		//�{�^���̐���
		this.startbutton = new JButton("�Q�[���X�^�[�g");
		this.helpbutton = new JButton("�w���v");
		this.optionbutton = new JButton("�R���t�B�O");
		this.endbutton = new JButton("�����");
		
		//�p�l���Ƀ{�^����ǉ�
		this.add(this.startbutton);
		this.add(this.helpbutton);
		this.add(this.optionbutton);
		this.add(this.endbutton);
		
		//�摜�̐���
		//URL url = this.getClass().getClassLoader().getResource("ImageFile/OP2.png");
		//Image image = new ImageIcon(url).getImage();
		//ImageIcon image = new ImageIcon(url);
		//ImageIcon image = new ImageIcon("ImageFile/OP2.png");
		ClassLoader cl = this.getClass().getClassLoader();
		ImageIcon image = new ImageIcon(cl.getResource("OP2.png"));
		JLabel label = new JLabel(image);
		add(label);
		
		//�X�^�[�g�{�^���̃C�x���g
		this.startbutton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event) {	
				mainframe.getContentPane().add(mainframe.select);
				mainframe.getContentPane().remove(mainframe.start);
				mainframe.start.setVisible(false);
				mainframe.select.setVisible(true);
				mainframe.pack();
			}
		});
		
		//�w���v�{�^���̃C�x���g
		this.helpbutton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event) {
				mainframe.getContentPane().add(mainframe.help);
				mainframe.getContentPane().remove(mainframe.start);
				mainframe.start.setVisible(false);
				mainframe.help.setVisible(true);
				revalidate();
				mainframe.pack();
			}
		});
		
		//�I�v�V�����{�^���̃C�x���g
		this.optionbutton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event) {
				mainframe.getContentPane().add(mainframe.config);
				mainframe.getContentPane().remove(mainframe.start);
				mainframe.start.setVisible(false);
				mainframe.config.setVisible(true);
				mainframe.pack();
			}
		});
		
		//�I���{�^���̃C�x���g
		this.endbutton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event) {
				String select[] = {"�͂�","������"};
				JLabel msg = new JLabel("�I�����܂���?");
				int flag = JOptionPane.showOptionDialog(msg,null,"�I�����܂���?",JOptionPane.CLOSED_OPTION,JOptionPane.QUESTION_MESSAGE,null,select,select[1]);
				if(flag == JOptionPane.NO_OPTION)
					return;
				else if(flag == JOptionPane.YES_OPTION)
					System.exit(0);
			}});
	}
}
