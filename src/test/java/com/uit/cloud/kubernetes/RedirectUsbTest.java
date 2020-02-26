/*
 * Copyright (2019, ) Institute of Software, Chinese Academy of Sciences
 */
package com.uit.cloud.kubernetes;

import com.github.kubesys.kubernetes.ExtendedKubernetesClient;
import com.github.kubesys.kubernetes.api.model.virtualmachine.Lifecycle;

/**
 * @author wuheng@otcaix.iscas.ac.cn
 * @author wuyuewen@otcaix.iscas.ac.cn
 * @author liuhe@otcaix.iscas.ac.cn
 * 
 * @version 1.3.0
 * @since   2019/9/3
 *
 */
public class RedirectUsbTest {
	
	
	public static void main(String[] args) throws Exception {

		ExtendedKubernetesClient client = AbstractTest.getClient();
		boolean successful = client.virtualMachines()
				.redirectUsb("wywwin10", get());
		System.out.println(successful);
	}

	public static Lifecycle.RedirectUsb get() throws Exception {
		Lifecycle.RedirectUsb redirectUsb = new Lifecycle.RedirectUsb();
		redirectUsb.setAction("off");
		redirectUsb.setNumber("8");
		return redirectUsb;
	}
}
