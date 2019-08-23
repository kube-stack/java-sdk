/*
 * Copyright (2019, ) Institute of Software, Chinese Academy of Sciences
 */
package com.uit.cloud.kubernetes;

import com.github.kubesys.kubernetes.ExtendedKubernetesClient;
import com.github.kubesys.kubernetes.api.model.virtualmachine.Lifecycle.PlugNIC;

/**
 * @author wuheng@otcaix.iscas.ac.cn
 * @since  2019/7/15
 *
 * This code is used to manage CustomResource's lifecycle,
 * such as VirtualMachine
 */
public class PlugNICTest {
	
	
	public static void main(String[] args) throws Exception {

		ExtendedKubernetesClient client = AbstractTest.getClient();
		boolean successful = client.virtualMachines()
				.plugNIC("950646e8c17a49d0b83c1c797811e042", get());
		System.out.println(successful);
	}
	
	public static PlugNIC get() {
		PlugNIC plugNIC = new PlugNIC();
		
		/*
		 * libvirt bridge network
		 * Parameters:
		 * 	bridge
		 * 		libvirt bridge name, default is "virbr0"
		 */
//		plugNIC.setType("bridge");
//		plugNIC.setSource("name=virbr0");
		
		/*
		 * l2 network example
		 * Parameters:
		 * 	name
		 * 		ovs bridge name
		 * 	virtual_type (optional)
		 * 		default is "openvswitch"
		 */
//		plugNIC.setType("l2bridge"); 
//		plugNIC.setSource("name=br-native");
		
		
		/*
		 * l3 network example
		 * Parameters:
		 * 	name
		 * 		ovs bridge name
		 * 	virtualport_type (optional)
		 * 		default is "openvswitch"
		 * 	ip (optional)
		 * 		ip address for l3 network, default is "dynamic" from DHCP
		 * 	switch
		 * 		switch name
		 */
		plugNIC.setType("l3bridge"); 
		plugNIC.setSource("name=br-int,ip=192.168.4.6,switch=ls1");
		
		/*
		 * 	mac address
		 * 		if no mac, create a random mac
		 * 		Note! Mac address is unique and does not support a value that start with "fe:" (e.g. fe:54:00:05:37:b3)
		 */
		plugNIC.setMac("52:54:00:20:d0:81");
		// inbound bandwidth limitation in KB, default is no limitation
		plugNIC.setInbound("102400");
		// outbound bandwidth limitation in KB, default is no limitation
		plugNIC.setOutbound("102400");
		plugNIC.setLive(true);
		plugNIC.setConfig(true);
		return plugNIC;
	}
}
