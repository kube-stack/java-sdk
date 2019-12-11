/*
 * Copyright (2019, ) Institute of Software, Chinese Academy of Sciences
 */
package com.uit.cloud.kubernetes;

import com.github.kubesys.kubernetes.ExtendedKubernetesClient;
import com.github.kubesys.kubernetes.api.model.virtualmachinedisksnapshot.Lifecycle.CreateDiskExternalSnapshot;

/**
 * @author wuheng@otcaix.iscas.ac.cn
 * @author wuyuewen@otcaix.iscas.ac.cn
 * @author liuhe@otcaix.iscas.ac.cn
 * 
 * @version 1.3.0
 * @since   2019/9/3
 *
 */
public class CreateDiskExternalSnapshotTest {
	
	
	public static void main(String[] args) throws Exception {

		ExtendedKubernetesClient client = AbstractTest.getClient();
		boolean successful = client.virtualMachineDiskSnapshots()
				.createDiskExternalSnapshot("disk33333.6", "vm.node22", get(), "abc");
		System.out.println(successful);
	}

	protected static CreateDiskExternalSnapshot get() {
		CreateDiskExternalSnapshot createDiskExternalSnapshot = new CreateDiskExternalSnapshot();
		createDiskExternalSnapshot.setPool("poolnbv");
		createDiskExternalSnapshot.setVol("disk33333");
		createDiskExternalSnapshot.setFormat("qcow2");
		createDiskExternalSnapshot.setType("localfs");
//		createDiskExternalSnapshot.setDomain("vm010");
		return createDiskExternalSnapshot;
	}
}
