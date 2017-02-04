package edu.pse.beast.propertylist.View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;

import edu.pse.beast.datatypes.FailureExample;

public class ResultPresenterWindow extends JFrame {
	
	private JButton showResult;
	private JButton export;
	
	private JTextPane result;
	
	private FailureExample example;
	
	public ResultPresenterWindow() {
		this.setVisible(false);
		init();
	}
	
	public JButton getShowResult() {
		return showResult;
	}
	
	private void init() {
		this.setLayout(new BorderLayout());
		setBounds(0, 0, 300, 300);
		
		Dimension iconSize = new Dimension(80,40);
		
		showResult = new JButton();
		showResult.setPreferredSize(iconSize);
		showResult.setIcon(new ImageIcon(getClass().getResource("/images/other/eye.png")));
		showResult.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		getContentPane().add(showResult, BorderLayout.PAGE_START);
		
		result = new JTextPane();
		result.setEditable(false);
		result.setText("Not a result yet.");
		getContentPane().add(result, BorderLayout.CENTER);
		
		
		export = new JButton();
		export.setPreferredSize(iconSize);
		export.setText("Export");
		export.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		getContentPane().add(export, BorderLayout.PAGE_END);
		
		
	}
	
	private void appendPane(String text) {
		StyledDocument doc = result.getStyledDocument();
		try {
			doc.insertString(doc.getLength(), text, null);
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		result.setStyledDocument(doc);
	}
	
	private void appendLine(String text) {
		appendPane(text + "\n");
	}
	
	private void erasePane() {
		result.setText("");
	}

	public FailureExample getExample() {
		return example;
	}

	public void setExample(FailureExample example) {
		this.example = example;
	}
	
	public void present(String message) {
		
	}
	
	public void presentFailure(List<String> error) {
		erasePane();
		for (String line : error) appendLine(line);
	}

	public void presentFailureExample(FailureExample ex) {
		erasePane();
		appendLine("Type: " + ex.getTypeString() + "\n");
		for (int i = 0; i < ex.getNumOfElections(); i++) {
			appendLine("Election " + i);
			appendPane("Votes: ");
			if (ex.isChooseOneCandidate()) appendPane(Arrays.toString(ex.getVotes().get(i)));
			else {
				int[][] arr = ex.getVoteList().get(i);
				for (int j = 0; j < arr.length; j++) {
					appendPane(Arrays.toString(arr[j]));
					if (j < arr.length - 1) appendPane(", ");
				}
			}
			appendLine("");
			
			appendPane("Elected: ");
			if (ex.isOneSeatOnly()) appendPane(Integer.toString(ex.getElect().get(i)));
			else appendPane(Arrays.toString(ex.getSeats().get(i)));
			appendLine("\n");
		}
	}
	
	public void presentSuccess() {
		// TODO
	}
	public void presentTimeOut() {
		// TODO
	}
	
	
}