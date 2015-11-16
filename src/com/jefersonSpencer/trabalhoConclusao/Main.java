package com.jefersonSpencer.trabalhoConclusao;

import java.io.IOException;

import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.TransportException;

import com.jefersonSpencer.trabalhoConclusao.core.EngineCore;

public class Main {

	public static void main(String[] args) throws IOException, InterruptedException, InvalidRemoteException, TransportException, GitAPIException {
		/*OptionParser parser = new OptionParser() {
			{
				accepts("x", "xxxxx");
				acceptsAll( asList( "h", "?" ), "show help" ).forHelp();
			}
		};
		
		OptionSet options = parser.parse(args);
		if(options.has("h") || options.specs().isEmpty()){
				parser.printHelpOn( System.out );
		}*/
		
	    EngineCore engineCore = new EngineCore(System.out, System.err);
		engineCore.start(args);
        
        //System.out.print("Done");

	}

}
