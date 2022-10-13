/**
 * Copyright (2019, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubestack.client.api.models;

import io.github.kubestack.client.api.specs.VirtualMachineNetworkSpec;

/**
 * @author wuheng@iscas.ac.cn
 * 
 * @version 2.0.0
 * @since   2022.10.12
 * 
 **/
public class VirtualMachineNetwork extends KubeStackModel {

	protected VirtualMachineNetworkSpec spec;

	public VirtualMachineNetworkSpec getSpec() {
		return spec;
	}

	public void setSpec(VirtualMachineNetworkSpec spec) {
		this.spec = spec;
	}
	
}
