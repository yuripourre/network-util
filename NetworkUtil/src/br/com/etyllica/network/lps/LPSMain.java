package br.com.etyllica.network.lps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import br.com.etyllica.Etyllica;
import br.com.etyllica.network.lps.network.IwlistParser;
import br.com.etyllica.network.lps.network.WifiNetwork;

public class LPSMain extends Etyllica{

	private final String ROOT_PASSWORD = "*****";
	
	public LPSMain() {
		super(640, 480);
	}

	@Override
	public void startGame() {

		displayInformation();
		
		//ifconfig to get InterfaceName
		
		
		
		List<WifiNetwork> networks = displayWifi(ROOT_PASSWORD, "wlan0");
				
		setMainApplication(new LPSApplication(networks));

	}
	
	private List<WifiNetwork> displayWifi(String rootPassword, String interfaceName){
		
		List<WifiNetwork> list = null;
		
		String s = null;

		try {

			String[] commands = {
					"/bin/sh",
					"-c",
					//TODO Try iw instead iwlist
					//iw wlan3 w0 survey dump
					"echo "+rootPassword+" | sudo -S iwlist "+interfaceName+" scan",
					//"ls /etc | grep release"
					};
			
			Process p = Runtime.getRuntime().exec(commands);
			p.waitFor();

			BufferedReader stdInput = new BufferedReader(new 
					InputStreamReader(p.getInputStream()));

			BufferedReader stdError = new BufferedReader(new 
					InputStreamReader(p.getErrorStream()));

			// read the output from the command
			System.out.println("Here is the standard output of the command:\n");
			
			list = new IwlistParser().parse(stdInput);
			
			for(WifiNetwork network: list){
				System.out.println(network);
			}
			
			// read any errors from the attempted command
			System.out.println("Here is the standard error of the command (if any):\n");
			while ((s = stdError.readLine()) != null) {
				System.out.println(s);
			}


		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	private void displayInformation(){
		Enumeration<NetworkInterface> nets;
		try {
			nets = NetworkInterface.getNetworkInterfaces();
			for (NetworkInterface netint : Collections.list(nets)){
				displayInterfaceInformation(netint);
			}
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

	private void displayInterfaceInformation(NetworkInterface netint) throws SocketException {
		System.out.printf("Interface: %s\n", netint.getDisplayName());
		//System.out.printf("Name: %s\n", netint.getName());
		Enumeration<InetAddress> inetAddresses = netint.getInetAddresses();
		for (InetAddress inetAddress : Collections.list(inetAddresses)) {
			System.out.println("InetAddress: "+inetAddress);
		}
		System.out.println("");
	}


}
