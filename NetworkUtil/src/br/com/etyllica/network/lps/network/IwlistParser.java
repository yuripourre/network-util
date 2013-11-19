package br.com.etyllica.network.lps.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class IwlistParser implements WifiParser{

	@Override
	public List<WifiNetwork> parse(BufferedReader output) {
	
		List<WifiNetwork> networks = new ArrayList<WifiNetwork>();
		
		
		WifiNetwork cell = null;
		
		String str = "";
		
		try {
			while ((str = output.readLine()) != null) {
				
				String s = str.trim();
				
				if(s.startsWith("Cell")){
					
					if(cell!=null){
						networks.add(cell);
					}
										
					cell = new WifiNetwork();
					
					String address = s.split(" - Address: ")[1]; 
					cell.setAddress(address);
				}
								
				if(s.startsWith("Channel:")){
					cell.setChannel(Integer.parseInt(s.substring("Channel:".length())));
				}
				
				if(s.startsWith("Frequency:")){
					
					float frequency = Float.parseFloat(s.substring("Frequency:".length(),"Frequency:".length()+5));
					cell.setFrequency(frequency);					
				}
				
				if(s.startsWith("Quality=")){
					
					String quality = s.substring("Quality=".length(),"Quality=".length()+5);
					String[] qualityFraction = quality.split("/");
					float qualityFactor = Float.parseFloat(qualityFraction[0])/Float.parseFloat(qualityFraction[1]);
									
					cell.setQuality(qualityFactor);
				}
				
				if(s.startsWith("ESSID:")){
					
					String ssid = s.substring("ESSID:\"".length());
					
					ssid = ssid.substring(0, ssid.length()-1);
					
					cell.setSSID(ssid);
				}
						
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return networks;
	}


}
