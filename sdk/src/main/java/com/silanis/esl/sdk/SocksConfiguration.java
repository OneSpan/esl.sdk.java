package com.silanis.esl.sdk;

/**
 * Created by rochbu on 05/12/14.
 */
public class SocksConfiguration {

	private String socksHost;
	private int socksPort;
    private String userName;
    private String password;
    private boolean credentials;

    public SocksConfiguration(){
    	socksHost = null;
    	socksPort = 0;
        userName = null;
        password = null;
        credentials = false;
    }

    public String getSocksHost() {
    	return socksHost;
    }
    
    public void setSocksHost(String socksHost) {
    	this.socksHost = socksHost;
    }
    
    public int getSocksPort() {
    	return socksPort;
    }
    
    public void setSocksPort(int socksPort) {
    	this.socksPort = socksPort;
    }

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public boolean hasCredentials() {
        return credentials;
    }
    public void setCredentials(boolean credentials) {
        this.credentials = credentials;
    }
}
