package org.mule.tools.anttasks;

import java.io.File;
import java.text.MessageFormat;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;

public class CloudHubDeploy extends Task {

	/** The application file to install */
	private File applicationFile;
	
	/** The username of your CloudHub account */
	private String username;
	
	/** The password of your CloudHub account */
	private String password;
	
	/** The domain name for the application */
	private String domain;

	private String cloudHubUrl = "https://cloudhub.io";
	
	private String domainCreationRequestTemplate = "{\"domain\" : \"[DOMAIN]\",\"workers\" : 1, \"muleVersion\":\"3.3\", \"properties\" : [{ \"your.system.property\", \"some.value\"}]}";

	/**
	 * install the application file
	 */
	@Override
	public void execute() throws BuildException {
		FileChecker checker = new FileChecker(getLocation());
		checker.checkFile(applicationFile, "application file", false, false);
		try {
			Client client = Client.create();
			// client basic auth demonstration
			client.addFilter(new HTTPBasicAuthFilter(username, password));
			WebResource webResource = client.resource(cloudHubUrl);
			String domainCreationPath = "/api/applications/" + domain;
			//String domainCreationRequest = domainCreationRequestTemplate.replace("[DOMAIN]", domain);
			//webResource.path(domainCreationPath).post(String.class, domainCreationRequest);
			String appDeploymentPath = domainCreationPath + "/deploy"; 
			String page = (String) webResource.path(appDeploymentPath).post(String.class, applicationFile);
			System.out.println(page);
		} catch (Exception exception) {
			throw new BuildException(MessageFormat.format("Problem deploying Mule application file {0} to {1}. Exception: {2}", applicationFile, cloudHubUrl, exception.getMessage()), exception, getLocation());
		}
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public void setCloudHubUrl(String cloudHubUrl) {
		this.cloudHubUrl = cloudHubUrl;
	}

	/**
	 * Specify the Mule application file to install
	 */
	public void setApplicationFile(File applicationFile) {
		this.applicationFile = applicationFile;
	}

}
