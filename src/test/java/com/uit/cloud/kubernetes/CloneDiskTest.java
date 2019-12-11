/*
 * Copyright (2019, ) Institute of Software, Chinese Academy of Sciences
 */
package com.uit.cloud.kubernetes;

import com.github.kubesys.kubernetes.ExtendedKubernetesClient;
import com.github.kubesys.kubernetes.api.model.virtualmachinedisk.Lifecycle.CloneDisk;

/**
 * @author wuheng@otcaix.iscas.ac.cn
 * @author wuyuewen@otcaix.iscas.ac.cn
 * @author liuhe@otcaix.iscas.ac.cn
 * 
 * @version 1.3.0
 * @since   2019/9/3
 *
 */
public class CloneDiskTest {
	
	
	public static void main(String[] args) throws Exception {

		ExtendedKubernetesClient client = AbstractTest.getClient();
		boolean successful = client.virtualMachineDisks()
				.cloneDisk("disk44444", getCreateDisk());
		System.out.println(successful);
	}

	public static CloneDisk getCreateDisk() {
		CloneDisk createDisk = new CloneDisk();
		createDisk.setPool("123poolnfs");
		createDisk.setType("nfs");
		createDisk.setNewname("disk44444clone");
		createDisk.setFormat("qcow2");
		return createDisk;
	}
}
