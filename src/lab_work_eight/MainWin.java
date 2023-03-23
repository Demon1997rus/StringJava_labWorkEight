package lab_work_eight;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.BoxLayout;
import javax.swing.border.BevelBorder;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.awt.event.ActionEvent;

public class MainWin {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWin window = new MainWin();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 855, 728);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));

		panel.setBackground(new Color(255, 255, 255));
		panel.setForeground(new Color(255, 255, 255));
		panel.setBounds(10, 11, 644, 678);
		frame.getContentPane().add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

		JTextArea textArea = new JTextArea();
		panel.add(textArea);

		JButton buttonEditedText = new JButton("Обработанный текст");
		buttonEditedText.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File file = new File("note.txt");
				textArea.setText(editedText(file)); 
			}
		});
		buttonEditedText.setBounds(664, 54, 153, 32);
		frame.getContentPane().add(buttonEditedText);	

		JButton buttonSourceText = new JButton("Исходный текст");
		buttonSourceText.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File file = new File("note.txt");
				textArea.setText(sourceText(file));
			}
		});
		buttonSourceText.setBounds(664, 11, 153, 32);
		frame.getContentPane().add(buttonSourceText);
		
		JButton buttonWriteEditedText = new JButton("Записать в файл");
		buttonWriteEditedText.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File file = new File("note.txt");
				writeText(file);
			}
		});
		buttonWriteEditedText.setBounds(664, 97, 153, 32);
		frame.getContentPane().add(buttonWriteEditedText);
	}

	private String sourceText(File file) {
		String text = "";
		try {
			Scanner sc = new Scanner(file);
			StringBuilder strBuilder = new StringBuilder();
			String str = "";
			while(sc.hasNextLine())	{
				str = sc.nextLine();
				strBuilder.append(str + "\n");
			}
			text = strBuilder.toString();
			sc.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		return text;
	}

	private String editedText(File file) {
		String text = "";
		try {
			Scanner sc = new Scanner(file);
			StringBuilder strBuilder = new StringBuilder();
			String str = "";
			boolean ok = true;
			while(sc.hasNextLine())
			{
				str = sc.nextLine();
				String[] strArr = str.split(" ");
				for (String st : strArr)
				{
					ok = true;
					for(char c : st.toCharArray())
					{
						if(c == 'A' || c == 'А')
						{
							strBuilder.append("<" + st + "> ");
							ok = false;
							break;
						}
					}
					if(ok)
						strBuilder.append(st + " ");
				}
				strBuilder.append("\n");
			}
			text = strBuilder.toString();
			sc.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		return text;
	}
	
	private void writeText(File file) {
		try(FileWriter writer = new FileWriter("noteEdited.txt",false)) {
			writer.write(editedText(file));
			writer.flush();
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
	}
}









