/*
 * Copyright (2019, ) Institute of Software, Chinese Academy of Sciences
 */
package com.uit.cloud.kubernetes;

import com.github.kubesys.kubernetes.ExtendedKubernetesClient;
import com.github.kubesys.kubernetes.api.model.virtualmachinepool.Lifecycle;

/**
 * @author wuheng@otcaix.iscas.ac.cn
 * @author wuyuewen@otcaix.iscas.ac.cn
 * @author liuhe@otcaix.iscas.ac.cn
 * 
 * @version 1.3.0
 * @since   2019/9/3
 *
 */
public class CreateVMDirPoolTest {
	
	
	public static void main(String[] args) throws Exception {

		ExtendedKubernetesClient client = AbstractTest.getClient();
		boolean successful = client.virtualMachinePools()
				.createPool("pooluittest1", "vm.node22", getPool(), "123");
		System.out.println(successful);
	}

	protected static Lifecycle.CreatePool getPool() {
		Lifecycle.CreatePool createPool = new Lifecycle.CreatePool();
		
		//dir
		createPool.setType("dir");
		createPool.setContent("vmdi");
		createPool.setAutostart(true);
        createPool.setUrl("localfs:///dev/sdb1:/mnt/uit");

		// uus
//		createPool.setType("uus");
//		createPool.setUrl("uus-iscsi-independent://admin:admin@192.168.3.10:7000/p1/4/2/0/32/0/3");
		
		// nfs
//		createPool.setType("nfs");
//		createPool.setTarget("poolnfs");
//		createPool.setUrl("nfs://192.168.3.99:/nfs/nfs");
//		createPool.setOpt("nolock");
		
		// glusterfs
//		createPool.setType("glusterfs");
//		createPool.setTarget("poolglusterfs");
//		createPool.setUrl("glusterfs://192.168.3.93:nfsvol");
		
		return createPool;
	}
	
}
