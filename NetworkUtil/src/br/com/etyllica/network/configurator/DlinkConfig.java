package br.com.etyllica.network.configurator;

import br.com.etyllica.network.configurator.dlink.DlinkConfiguratorDIR635;



public class DlinkConfig {

	public static void main(String[] args) {
		
		DlinkConfiguratorDIR635 dlink = new DlinkConfiguratorDIR635();
		
		dlink.setRouterIP("192.168.0.1");
		dlink.setCredentials("admin", "admin_password;)");
		
		dlink.authenticate();
		
		
	}

}
