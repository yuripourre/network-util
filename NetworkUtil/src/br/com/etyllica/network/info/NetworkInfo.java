package br.com.etyllica.network.info;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Enumeration;

import br.com.etyllica.debug.Logger;

public class NetworkInfo {

	public static void displayInformation() {
		Enumeration<NetworkInterface> nets;
		try {
			nets = NetworkInterface.getNetworkInterfaces();
			for (NetworkInterface netint : Collections.list(nets)) {
				displayInterfaceInformation(netint);
			}
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

	private static void displayInterfaceInformation(NetworkInterface netint) throws SocketException {
		System.out.printf("Interface: %s\n", netint.getDisplayName());
		//System.out.printf("Name: %s\n", netint.getName());
		Enumeration<InetAddress> inetAddresses = netint.getInetAddresses();
		for (InetAddress inetAddress : Collections.list(inetAddresses)) {
			Logger.log("InetAddress: "+inetAddress);
		}
		Logger.log("");
	}
	
}
