package com.jefersonSpencer.trabalhoConclusao.UI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import com.jefersonSpencer.trabalhoConclusao.core.EngineCore;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainDialog extends JDialog {


	/**
	 * 
	 */
	private static final long serialVersionUID = 4353309804393617152L;

	/**
	 * Create the dialog.
	 * @param engineCore 
	 */
	public MainDialog(EngineCore engineCore) {
		setBounds(100, 100, 450, 61);
		getContentPane().setLayout(new BorderLayout());
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.NORTH);
			{
				JButton btnOpenStudentDialog = new JButton("Aluno");
				btnOpenStudentDialog.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						StudentDialog studentDialog = new StudentDialog();
						studentDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						studentDialog.setVisible(true);
					}
				});
				buttonPane.add(btnOpenStudentDialog);
			}
			{
				JButton btnOpenProfessorDialog = new JButton("Professor");
				btnOpenProfessorDialog.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ProfessorDialog professorDialog = new ProfessorDialog(engineCore);
						professorDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						professorDialog.setVisible(true);
					}
				});
				buttonPane.add(btnOpenProfessorDialog);
			}
		}
	}

}
