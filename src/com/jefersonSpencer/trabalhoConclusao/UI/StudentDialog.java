package com.jefersonSpencer.trabalhoConclusao.UI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StudentDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7897750838778200515L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtRemoteRepository;
	private JTextField txtLocalRepositorio;
	/**
	 * Create the dialog.
	 */
	public StudentDialog() {
		setModalityType(JDialog.ModalityType.DOCUMENT_MODAL);
		setBounds(100, 100, 450, 158);
		getContentPane().setLayout(new BorderLayout());
		FlowLayout fl_contentPanel = new FlowLayout();
		fl_contentPanel.setAlignment(FlowLayout.RIGHT);
		contentPanel.setLayout(fl_contentPanel);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JLabel lblRepositrio = new JLabel("Repositório");
			contentPanel.add(lblRepositrio);
		}
		{
			txtRemoteRepository = new JTextField();
			contentPanel.add(txtRemoteRepository);
			txtRemoteRepository.setColumns(28);
		}
		{
			JLabel lblNewLabel = new JLabel("Diretorio");
			contentPanel.add(lblNewLabel);
		}
		{
			txtLocalRepositorio = new JTextField();
			txtLocalRepositorio.setColumns(20);
			contentPanel.add(txtLocalRepositorio);
		}
		{
			JButton btnChooseFolder = new JButton("...");
			btnChooseFolder.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					JFileChooser fileChooser = new JFileChooser();
					fileChooser.setFileSelectionMode( JFileChooser.DIRECTORIES_ONLY);
					if(fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION)
					  txtLocalRepositorio.setText(fileChooser.getSelectedFile().getPath());
				}
			});
			contentPanel.add(btnChooseFolder);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
