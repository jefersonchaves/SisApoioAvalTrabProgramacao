package com.jefersonSpencer.trabalhoConclusao.wrappers;

import java.nio.file.Path;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.TransportException;

public class GitWrapper {

	public GitWrapper() {
		
	}
	
	public void clone(String remoteUrl, Path clonedTargetFolder) throws InvalidRemoteException, TransportException, GitAPIException {
	    // then clone
	    //System.out.println("Cloning from " + remoteUrl + " to " + clonedTargetFolder);
	    Git result = Git.cloneRepository()
	            .setURI(remoteUrl)
	            .setDirectory(clonedTargetFolder.toFile())
	            .call();
	    
        // Note: the call() returns an opened repository already which needs to be closed to avoid file handle leaks!
        //System.out.println("Having repository: " + result.getRepository().getDirectory());

        // workaround for https://bugs.eclipse.org/bugs/show_bug.cgi?id=474093
        result.getRepository().close();
	}

}
