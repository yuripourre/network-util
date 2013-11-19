package br.com.etyllica.network.lps.network;

public class WifiNetwork{

	private String address;
	
	private int channel;
	
	private float frequency;
	
	private float quality;
		
	private String SSID;

	public WifiNetwork() {
		super();
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getChannel() {
		return channel;
	}

	public void setChannel(int channel) {
		this.channel = channel;
	}

	public float getFrequency() {
		return frequency;
	}

	public void setFrequency(float frequency) {
		this.frequency = frequency;
	}

	public float getQuality() {
		return quality;
	}

	public void setQuality(float quality) {
		this.quality = quality;
	}

	public String getSSID() {
		return SSID;
	}

	public void setSSID(String sSID) {
		SSID = sSID;
	}
	
	public String toString(){
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("SSID: ").append(SSID).append('\n');
		sb.append("Address: ").append(address).append('\n');
		sb.append("Channel: ").append(channel).append('\n');
		sb.append("Frequency: ").append(frequency).append('\n');
		sb.append("Quality: ").append(quality).append('\n');
		
		return sb.toString();
		
	}
}
