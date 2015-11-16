package com.jefersonSpencer.trabalhoConclusao.core;

import static java.util.Arrays.asList;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import javax.swing.JDialog;

import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.TransportException;

import com.jefersonSpencer.trabalhoConclusao.UI.MainDialog;
import com.jefersonSpencer.trabalhoConclusao.wrappers.GitWrapper;
import com.jefersonSpencer.trabalhoConclusao.wrappers.VagrantWrapper;

import joptsimple.OptionException;
import joptsimple.OptionParser;
import joptsimple.OptionSet;

public class EngineCore {
	
	private PrintStream helpOutput;
	private PrintStream errorOutput;
	private VagrantWrapper vagrantWrapper;

	public EngineCore(PrintStream helpOutput, PrintStream errorOutput){
		this.helpOutput = helpOutput;
		this.errorOutput = errorOutput;
	}
	
	public void start(String[] commandLineArgs) throws IOException, InvalidRemoteException, TransportException, GitAPIException, InterruptedException{

		OptionParser parser = getCommandLineParser();
		
		OptionSet options = null;
		
		try{
			options = parser.parse(commandLineArgs);
		}catch(OptionException optEx){
			errorOutput.println(optEx.getMessage());
			parser.printHelpOn( helpOutput );
			return;
		}
		
		
		if(options.has("h")){
			printHelp(parser);
			return;
		}
		
		if(options.specs().isEmpty()){
			MainDialog mainDialog = new MainDialog(this);
			mainDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			mainDialog.setVisible(true);
			return;
		}
		
		if(options.has("c")){
			Path clonedRepoTargetDirectory = Files.createTempDirectory("TempSisApoioAvalTrabProg");
			cloneRepository("https://github.com/jefersonspencer/SAATP-ExemploJava.git", clonedRepoTargetDirectory);
		}
		if(options.has("up")){
			Path clonedRepoTargetDirectory = Files.createTempDirectory("TempSisApoioAvalTrabProg");
			cloneRepository((String)options.valueOf("up"), clonedRepoTargetDirectory);
			startExecutionExercise(clonedRepoTargetDirectory, new ArrayList<String>());
			System.out.println("Provisionado!");
			System.in.read();
			destroy();
			cleanFolder(clonedRepoTargetDirectory);
		}
		
	}

	public void cleanFolder(Path clonedRepoTargetDirectory) {
		clonedRepoTargetDirectory.toFile().listFiles(new FileFilter() {
		    @Override
		    public boolean accept(File pathname) {
		        if (pathname.isDirectory()) {
		            pathname.listFiles(this);
		            pathname.delete();
		        } else {
		            pathname.delete();
		        }
		        return false;
		    }
		});
		clonedRepoTargetDirectory.toFile().delete();
	}

	public void startExecutionExercise(Path executionPath, ArrayList<String> errorList) throws IOException, InterruptedException {
		vagrantWrapper = new VagrantWrapper(executionPath, errorList);
		vagrantWrapper.up();
	}
	
	public void destroy() throws IOException, InterruptedException{
		vagrantWrapper.destroy();
	}

	public void cloneRepository(String remoteRepoUrl, Path clonedRepoTargetDirectory) throws IOException, InvalidRemoteException, TransportException, GitAPIException {
	    GitWrapper gitWrapper = new GitWrapper();
	    gitWrapper.clone(remoteRepoUrl, clonedRepoTargetDirectory);
	}

	private void printHelp(OptionParser parser)
			throws IOException {
		parser.printHelpOn( helpOutput );
	}

	private OptionParser getCommandLineParser() {
		OptionParser parser = new OptionParser() {
			{
				acceptsAll( asList("c", "clone"), "clona o repositorio").withRequiredArg();
				acceptsAll( asList("up", "start"), "clona o reposiorio e inicia a execucao do exercicio").withRequiredArg();
				acceptsAll( asList( "h", "?" ), "exibe ajuda" ).forHelp();
			}
		};
		return parser;
	}
	
}
