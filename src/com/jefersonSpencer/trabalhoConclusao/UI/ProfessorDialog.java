package com.jefersonSpencer.trabalhoConclusao.UI;

import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import com.jefersonSpencer.trabalhoConclusao.core.EngineCore;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.awt.event.ActionEvent;
import javax.swing.DefaultListModel;
import javax.swing.JProgressBar;

public class ProfessorDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -88128242804271864L;
	private ArrayList<String> errorList = new ArrayList<String>();
	Path clonedRepoTargetDirectory;
	/**
	 * Create the dialog.
	 */
	public ProfessorDialog(EngineCore engineCore) {
		setModalityType(JDialog.ModalityType.DOCUMENT_MODAL);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JList<String> listProjects = new JList<String>();
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		listModel.addElement("https://github.com/jefersonspencer/SAATP-ExemploJava.git");
		listModel.addElement("https://github.com/jefersonspencer/SAATP-ExemploJava.git");
		listProjects.setModel(listModel);
		listProjects.setSelectedIndex(0);
		listProjects.setBounds(6, 29, 400, 153);
		getContentPane().add(listProjects);
		
		JLabel lblProjetosNoGithub = new JLabel("Projetos no GitHub");
		lblProjetosNoGithub.setBounds(6, 7, 129, 16);
		getContentPane().add(lblProjetosNoGithub);
		
		JButton btnExecute = new JButton("Executar");
		JButton btnStop = new JButton("Parar");
		JButton btnViewErrors = new JButton("Ver Erros");
		JProgressBar progressBarTasks = new JProgressBar();
		
		btnExecute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(listProjects.isSelectionEmpty())
					return;
				
				btnExecute.setEnabled(false);
				progressBarTasks.setEnabled(true);
				
				SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
				    protected Void doInBackground() throws Exception {
				    	clonedRepoTargetDirectory = Files.createTempDirectory("TempSisApoioAvalTrabProg");
						engineCore.cloneRepository(listProjects.getSelectedValue(), clonedRepoTargetDirectory);
						engineCore.startExecutionExercise(clonedRepoTargetDirectory, errorList);
						return null;
				    }

				    protected void done() {
				        try {
				            get();
				            btnStop.setEnabled(true);
							btnViewErrors.setEnabled(true);
				        } catch (ExecutionException e) {
				        	btnExecute.setEnabled(true);
				            e.getCause().printStackTrace();
				            //String msg = String.format("Unexpected problem: %s", 
				            //               e.getCause().toString());
				        } catch (InterruptedException e) {
				        	btnExecute.setEnabled(true);
				            // Process e here
				        } finally{
				        	progressBarTasks.setEnabled(false);
				        }
				    }
				};
				worker.execute();
			}
		});
		btnExecute.setBounds(6, 226, 117, 29);
		getContentPane().add(btnExecute);
		
		btnStop.setEnabled(false);
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(listProjects.isSelectionEmpty())
					return;
				
				btnStop.setEnabled(false);
				btnViewErrors.setEnabled(false);
				progressBarTasks.setEnabled(true);
				
				SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
				    protected Void doInBackground() throws Exception {
				    	engineCore.destroy();
						engineCore.cleanFolder(clonedRepoTargetDirectory);
						return null;
				    }

				    protected void done() {
				        try {
				            get();
				            btnExecute.setEnabled(true);
				            int nextSelectedIndex = 
				            		listProjects.getSelectedIndex() < (listProjects.getModel().getSize() - 1) ? 
				            		listProjects.getSelectedIndex() + 1 : 
				            	    listProjects.getSelectedIndex();
				            listProjects.setSelectedIndex(nextSelectedIndex);
				        } catch (ExecutionException e) {
				        	btnStop.setEnabled(true);
							btnViewErrors.setEnabled(true);
				            e.getCause().printStackTrace();
				            //String msg = String.format("Unexpected problem: %s", 
				            //               e.getCause().toString());
				        } catch (InterruptedException e) {
				        	btnStop.setEnabled(true);
							btnViewErrors.setEnabled(true);
				            // Process e here
				        } finally{
				        	progressBarTasks.setEnabled(false);
				        }
				    }
				};
				worker.execute();
			}
		});
		btnStop.setBounds(133, 226, 117, 29);
		getContentPane().add(btnStop);
		
		btnViewErrors.setEnabled(false);
		btnViewErrors.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ErrorDisplayDialog errorDisplayDialog = new ErrorDisplayDialog(errorList);
				errorDisplayDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				errorDisplayDialog.setVisible(true);
			}
		});
		btnViewErrors.setBounds(262, 226, 117, 29);
		getContentPane().add(btnViewErrors);
		
		progressBarTasks.setEnabled(false);
		progressBarTasks.setIndeterminate(true);
		progressBarTasks.setBounds(6, 194, 438, 20);
		getContentPane().add(progressBarTasks);
		
		JButton btnAddProject = new JButton("+");
		btnAddProject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String projectName = JOptionPane.showInputDialog("URL do projeto");
				if(projectName != null)
					listModel.addElement(projectName);
			}
		});
		btnAddProject.setBounds(411, 52, 33, 29);
		getContentPane().add(btnAddProject);
		
		JButton btnRemoveProject = new JButton("-");
		btnRemoveProject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(listProjects.isSelectionEmpty())
					return;
				
				listModel.remove(listProjects.getSelectedIndex());
			}
		});
		btnRemoveProject.setBounds(411, 99, 33, 29);
		getContentPane().add(btnRemoveProject);

	}
}
