package com.jefersonSpencer.trabalhoConclusao.wrappers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.ArrayList;

public class VagrantWrapper {
	
	private Path workingFolder;
	private ArrayList<String> errorList;
	
	public VagrantWrapper(Path workingFolder, ArrayList<String> errorList){
		this.workingFolder = workingFolder;
		this.errorList = errorList;
	}
	
	public Boolean up() throws IOException, InterruptedException{
		ProcessBuilder builder = new ProcessBuilder("vagrant", "up");
        builder.directory(workingFolder.toFile());
        builder.environment().put("PATH", System.getenv("PATH"));
        final Process process = builder.start();
        
        InputStream errorStream = process.getErrorStream();
        InputStreamReader isrError = new InputStreamReader(errorStream);
        BufferedReader brError = new BufferedReader(isrError);
        String lineError;
        while((lineError = brError.readLine()) != null){
        	errorList.add(lineError);
        }
        /*
        InputStream is = process.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String line;
        while ((line = br.readLine()) != null) {
          System.out.println(line);
        }*/
        
        process.waitFor();
        
        return process.exitValue() == 0;
	}
	
	public Boolean halt() throws IOException, InterruptedException{
		ProcessBuilder builder = new ProcessBuilder("vagrant", "halt");
        builder.directory(workingFolder.toFile());
        builder.environment().put("PATH", System.getenv("PATH"));
        final Process process = builder.start();
        
        /*InputStream errorStream = process.getErrorStream();
        InputStreamReader isrError = new InputStreamReader(errorStream);
        BufferedReader brError = new BufferedReader(isrError);
        String lineError;
        while((lineError = brError.readLine()) != null){
        	System.out.println(lineError);
        }
        
        InputStream is = process.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String line;
        while ((line = br.readLine()) != null) {
          System.out.println(line);
        }*/
        
        process.waitFor();
        
        return process.exitValue() == 0;
	}
	
	public Boolean destroy() throws IOException, InterruptedException{
		ProcessBuilder builder = new ProcessBuilder("vagrant", "destroy", "-f");
        builder.directory(workingFolder.toFile());
        builder.environment().put("PATH", System.getenv("PATH"));
        final Process process = builder.start();
        
        /*InputStream errorStream = process.getErrorStream();
        InputStreamReader isrError = new InputStreamReader(errorStream);
        BufferedReader brError = new BufferedReader(isrError);
        String lineError;
        while((lineError = brError.readLine()) != null){
        	System.out.println(lineError);
        }
        
        InputStream is = process.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String line;
        while ((line = br.readLine()) != null) {
          System.out.println(line);
        }*/
        
        process.waitFor();
        
        return process.exitValue() == 0;
	}

}
