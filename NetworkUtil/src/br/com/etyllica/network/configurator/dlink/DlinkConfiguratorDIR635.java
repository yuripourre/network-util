package br.com.etyllica.network.configurator.dlink;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import br.com.etyllica.util.checksum.Checksum;
import br.com.etyllica.util.checksum.ChecksumAlgorithm;

public class DlinkConfiguratorDIR635 extends RouterConfigurator{

	public DlinkConfiguratorDIR635(){
		super();
	}
	
	public void setCredentials(String username, String password){
		this.adminUser = username;
		this.adminPassword = password;
	}
	
	public void setRouterIP(String routerIP){
		this.routerIP = routerIP;
	}

	public void authenticate(){
		
		InputStream stream = null;
		//Autenticate
		try {
			
			stream = connectGet(retriveUrlLogin());
			//stream = connectGet("http://192.168.0.1/Status/Device_Info.shtml");

			String s = null;

			BufferedReader stdInput = new BufferedReader(new InputStreamReader(stream));

			while ((s = stdInput.readLine()) != null) {
				System.out.println(s);
			}
			
			stream.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private InputStream connectGet(String url) throws IOException{

		URL urls = new URL(url);

		HttpURLConnection connection = (HttpURLConnection)urls.openConnection();
		connection.setRequestMethod("GET");
		connection.connect();

		return connection.getInputStream();

	}	

	private String getSalt(){

		String salt = "";

		String s = null;

		InputStream stream = null;

		try {
			stream = connectGet("http://"+routerIP);

			//Read Stream
			BufferedReader stdInput = new BufferedReader(new 
					InputStreamReader(stream));

			boolean found = false;
			while ((s = stdInput.readLine()) != null) {

				String str = s.trim(); 

				if(str.startsWith("function send_login()")){
					found = true;
				}
				if(found){
					if(str.startsWith("var salt = ")){
						salt = str.substring(12, 20);
						break;
					}				
				}
			}
			
			stdInput.close();
			stream.close();
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return salt;
	}

	public String retriveUrlLogin(){
		String username = adminUser;
		String password = adminPassword;

		String salt = getSalt();
		

		final char DELIMITER = '\u0001';

		for (int i = password.length(); i < 16; i++) {
			password += DELIMITER;
		}

		String input = salt + password;

		for (int i = input.length(); i < 63; i++) {
			input += DELIMITER;
		}

		if(username=="user"){
			input += 'U';
		}else{
			input += DELIMITER;
		}

		Checksum c = new Checksum();

		String hash = c.getCheckSum(input, ChecksumAlgorithm.MD5);

		String loginHash = salt+hash;

		return "http://"+routerIP+"/post_login.xml?hash="+loginHash;
	}


}