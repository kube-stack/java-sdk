/*
 * Copyright (2019, ) Institute of Software, Chinese Academy of Sciences
 */
package com.uit.cloud.kubernetes;

import java.util.Map;

import com.github.kubesys.kubernetes.ExtendedKubernetesClient;
import com.github.kubesys.kubernetes.api.model.VirtualMachine;

import io.fabric8.kubernetes.client.Config;
import io.fabric8.kubernetes.client.ConfigBuilder;

/**
 * @author wuheng@otcaix.iscas.ac.cn
 * @since  2019/5/1
 *
 * This code is used to manage CustomResource's lifecycle,
 * such as VirtualMachine
 */
public class UpdateVirtualMachineWithNameTest {
	
	public static Config config = new ConfigBuilder()
			.withApiVersion("v1")
			.withCaCertData("LS0tLS1CRUdJTiBDRVJUSUZJQ0FURS0tLS0tCk1JSUN5RENDQWJDZ0F3SUJBZ0lCQURBTkJna3Foa2lHOXcwQkFRc0ZBREFWTVJNd0VRWURWUVFERXdwcmRXSmwKY201bGRHVnpNQjRYRFRFNU1EWXlNekF5TVRNMU4xb1hEVEk1TURZeU1EQXlNVE0xTjFvd0ZURVRNQkVHQTFVRQpBeE1LYTNWaVpYSnVaWFJsY3pDQ0FTSXdEUVlKS29aSWh2Y05BUUVCQlFBRGdnRVBBRENDQVFvQ2dnRUJBTjBiCkhzMi9VS0hpY0FZMHhWMFN3RUhPMWdZRUhwc0JvN3F2RE53NHFKNjZ2TEI4SE5SUkVYZ3lTcnNLcEFXN1JqZnoKT1ArVXVZUDNyeGZBMVQ0UjAxcURad2lBb0M1WHJlRFR4K252TEdFdjhCZ0tNTFYvY1BRMzJuSm5EM1RHVTF4ZQp5ck1FU0lUZzN5Mk1yakRsRWhnaGtTaHM0RGRXYkJxNmxBeHVwOXV3WG14dEUzYVVtR0t1WUpudzkwTFFkUWFyCmlIb3hhSGdONUJKdmtRcmhuZ24zSDdjblA5YlpUdmpKeFlqZDB3K3FNd0IxVXBtS3BvQ2NhY0dQamRNWkN2YTMKUFc2RFNqK0Fab3Y0b3FzcUFGTEVOalBEUFBPQ3loVHE2TEI4Tkdwdys0M3A1bUJURTFJdzYwWGlUY3ZYenZYdQpaQml5Mm5oK1YvVUlGZFZVUjhrQ0F3RUFBYU1qTUNFd0RnWURWUjBQQVFIL0JBUURBZ0trTUE4R0ExVWRFd0VCCi93UUZNQU1CQWY4d0RRWUpLb1pJaHZjTkFRRUxCUUFEZ2dFQkFEQy8yV0tsUUlwNmFYRHhlbGdvMmxDTG5GZ3EKUngxKzVXT3FQbzJpL2dUWDl1dzNtYTZzWWdFbHdXeG1wa25wNkN3dnJob1MzV2pXVG1IMXhYc0FDZ3pqc3lIZQpmOEZzM0lSZzZBaEZvUHpzcGNJMWFUaS9iZTNNSi9CYy80QThNZGVTS0lXOTdnVmFvSENTYlVMNUNRVnJUZS9PCk9IWStTR0FRUzhxZEllaTQrUS9MZ21CWHVtcmJ6WjQwVEFGbndrR3NVRVF5bDVqN3laNjd4cVVvS1RMUDZSaDIKakZzQmZOR0ZNNjRtQmQrWk5RNlc1U3Q2SS9UL3AvcTJ4QVVuVERJOGpXM0NpMVovTjhVdThHMFlRM3BEQzc4MAp6VTdZWktGR2RjVVlLNUNOdndUZzgwZHFNcXVzZCtOcXJpMDl6WFFXdTQ3UWVrQ05BUVRFK1FlNnRLUT0KLS0tLS1FTkQgQ0VSVElGSUNBVEUtLS0tLQo=")
			.withClientCertData("LS0tLS1CRUdJTiBDRVJUSUZJQ0FURS0tLS0tCk1JSUM4akNDQWRxZ0F3SUJBZ0lJTFhDdCs2UzhZSVV3RFFZSktvWklodmNOQVFFTEJRQXdGVEVUTUJFR0ExVUUKQXhNS2EzVmlaWEp1WlhSbGN6QWVGdzB4T1RBMk1qTXdNakV6TlRkYUZ3MHlNREEyTWpJd01qRXpOVGxhTURReApGekFWQmdOVkJBb1REbk41YzNSbGJUcHRZWE4wWlhKek1Sa3dGd1lEVlFRREV4QnJkV0psY201bGRHVnpMV0ZrCmJXbHVNSUlCSWpBTkJna3Foa2lHOXcwQkFRRUZBQU9DQVE4QU1JSUJDZ0tDQVFFQXhCMXZpV0dtdUVMclpqeUcKSlFDY0gwdjVSbzNhS3UyS3N6Y3VWNXFsL29HSDhLRmVWdlFTd2xWQUp3cTUyM0xVM0h5TWlCanVIZHhhc2RaRwo2eHQ0cGE3YVpqd21NSE5Kc2NMaVFWVGc1bmgxQjJCWkRhbW51Sis4TTdQM3cxZ0xOQ3ZZcms1djJwUjFpRWI5CjkyL0RBUVc1QzR1TnNWZUlJdTU1Zlg4WGY5akdEYWNWdTV1Sm5jTkxmSURpcXQxT3JUZUgvdk50aTh5OW9hVkQKb3BDMVF0ZSs4MjBUbGRlZWVJSVd2ZEEvOTF6YmF5azAyRUJ1MUZnejJFMmswM2Ewb1J3YTZKMFVxU041dVdqSwpQL091cFdCQmNEV2gwQUYvVW1KNzFETi9IVzRvQ2drM3N5bGdyWlliaDFIMlhQTnlFb2lVeFJRejFhd0Y2cDRKCmxTTHpUUUlEQVFBQm95Y3dKVEFPQmdOVkhROEJBZjhFQkFNQ0JhQXdFd1lEVlIwbEJBd3dDZ1lJS3dZQkJRVUgKQXdJd0RRWUpLb1pJaHZjTkFRRUxCUUFEZ2dFQkFMSEc0d0Z6OFl0RVpuaEJNTkllODFUeGFqVWxTUm1teTRsVwp3MzVoSlR0Z0gxVHAwMEg0c2hBTlhXMkYweGl0OW83SFc3TVBML3RUL0ZMZERqcmE4N0xvbE96Uy8xdUVhS2ovCkJnSS92MFBoMVBpdElPZHRML0dxbWhLYzY5aXduRkIrMVc3aDk0eWlKOXIwYUxuYlNYaG9xVGFyeHI4QzBNYUgKOE9QeTE5a00zcXdvVGVIWTAxbkFNQk5JSFFwelNjclkxTyt2VksyWmhlM3pHQzluaDUydkhGaUpyOXhqNHUwdgpKQWxuWVdlWHlMaS9rVW4wZFd1NTI0TkZSLzl6YjR3MUUwSEI2dHBPNlNhWldqbDV4NG51dDBVeFhEb05uRVljCklMdFkvL2hLN0VwV01jamNXZXd2ZWI0R2VKYWV5ei91b3c4VHhOR01Zd0FmOGpxb0h3dz0KLS0tLS1FTkQgQ0VSVElGSUNBVEUtLS0tLQo=")
			.withClientKeyData("LS0tLS1CRUdJTiBSU0EgUFJJVkFURSBLRVktLS0tLQpNSUlFb2dJQkFBS0NBUUVBeEIxdmlXR211RUxyWmp5R0pRQ2NIMHY1Um8zYUt1MktzemN1VjVxbC9vR0g4S0ZlClZ2UVN3bFZBSndxNTIzTFUzSHlNaUJqdUhkeGFzZFpHNnh0NHBhN2FaandtTUhOSnNjTGlRVlRnNW5oMUIyQloKRGFtbnVKKzhNN1AzdzFnTE5DdllyazV2MnBSMWlFYjk5Mi9EQVFXNUM0dU5zVmVJSXU1NWZYOFhmOWpHRGFjVgp1NXVKbmNOTGZJRGlxdDFPclRlSC92TnRpOHk5b2FWRG9wQzFRdGUrODIwVGxkZWVlSUlXdmRBLzkxemJheWswCjJFQnUxRmd6MkUyazAzYTBvUndhNkowVXFTTjV1V2pLUC9PdXBXQkJjRFdoMEFGL1VtSjcxRE4vSFc0b0NnazMKc3lsZ3JaWWJoMUgyWFBOeUVvaVV4UlF6MWF3RjZwNEpsU0x6VFFJREFRQUJBb0lCQUJXcVN0QWFnWmlKakFmWApWOXB1bnRDMlMrdUhjRHFkMDF6SkU4Mkp3QnpGb29icXpNaytYWFFxaHRQSlU0N3l1S0ZEQVNObjNhWmUyWVliCjdlbTdOekRXaURvVEd3VHZONHp6d3BzRkRYTm9pMFV0VjZreXZOeWdpT0lOVjZtMDZFZnpNQlRSaFhuWVBNTzUKVkFVcHk1a3BVSy85MHNGc3NoVnAwdklTUUQ1WE9ucUdNS040aU03aFFjeGNLbzQ3M0ZqZ0JibHlWb1hXS0NyUwpORGhpdmpxNG15bld5YWtlMy9MQ0xtVnFnU2R5SXNvNGFUOUwwcUhHaytnMzNTTzdjWWdOckNKditpMzA1akRmClNBaFlYZE5UcGhLRisxTkdpdG1Xc2pqVHEvVzYrN242SXYvSVV0Q0pBWmxZTWNOSmFSY1ZrYW1jVkt3WloxMU0KQ1RPSFJnRUNnWUVBK2tzajQ4NDZSRlFyM1NLOXBuOWJvei8yajhWME5DNmwwQ3I5dDNEWkRneUw5MU1kOXdVSQp6bSs4ZDJ3aTE3azU3UTNMWllEZUpWZzhNbTB5K05URGNLR2pIK3U2T1hUMSs5SHFpVExQKzhlbW1WaXIwd0NFCndISGVBWnZsbmVOLytCdmtlRXhjL0cvTU9hM0VHcW5OQ1BkYVhHdlFuTUY2cDl5SERnRnlFRDBDZ1lFQXlKWVQKNmdUUkl4U2ZDMjNyS0pFZEtKSndRa2tOZ3A4dHlZRUpkSi9LcDVXcVZZcTkzOEJQdzlBZlFKSWorMFNHdE1tWAo5WW9mTFpDM1lueUNTL2xPR0FkcjBmZW1LSUJObDdsNWFjeFk4WWlIUVhsRTNPamhPMVlqVVRoZzhpWXFxNmMzCnpwdmNiR29RaG1ZVUhPdWN4NUY0QmdhOGNBYjNRWWZqUlNPb0VGRUNnWUJCY3I2S3hoVmtZcUlwcS9qdGZhWUgKdFNVVENTM09xMWYrYkE5MkZWTWNvbGxETGYvSzVRMGRkZ2hSTGZ5YWJqdmdhZGdDemNUSS9SNllqUWRrRzdhVApVQ0RvcXVwQ05meFFnZDZUUzY4cEJsYmhDRmU5MEFmbWpwL2Z5ZFVzQ1I0Z1VhTFhkaGhtaGhGZ0Q3a1V3OHZ5CmlVT2VvUzZtbnMwazhieDdCQlBBZlFLQmdISGpGQ2Q0MksyTVZja05mY2tvcTErUDFZRXRMU2lPN0ZaT2wybksKR0N3U2hBWFJvb0RhRmUvZHlRREdlUHQvS3VTVzJWcW1NNUdSYXkzVjk3emZMZ0hpcE1zd1N2MFA2LzVMWnE0MQpRZ0xSTzhwbTZqeHJWUVoxbjlQTHFPK1QwQUpxRFB1WDd3ejNJNjdMWW53UklwRUUvUnFlMUhyZTJhQXhZVTZ1Ck1nd2hBb0dBVGFYZ21HSjhEbzhrbTJGUFg0d0phWTdoajFVRnFTRVJXTWdMZmdZenJLNjVLWTJ2OGQ2bkk5SXkKZUN4cldTdm9odXNqbWRPemQyZllWOGc0Z2dhK0hHOVNBRWx4NFlldVJYQzFIc0lnUjdVMHYxYjQ3UXArM1A2WApBOWdVNHlUUlpnOTZxSUF6Q0hDTnpBV25NcTV6RUx1Qzc0ZVVlTzJBMUVPank1dDZuOWc9Ci0tLS0tRU5EIFJTQSBQUklWQVRFIEtFWS0tLS0tCg=")
			.withMasterUrl("https://10.25.0.137:6443")
			.build();
	
	public static void main(String[] args) throws Exception {

		ExtendedKubernetesClient client = new ExtendedKubernetesClient(config);
		VirtualMachine vm = client.virtualMachines().get("vm1");
		if (vm != null) {
			Map<String, String> labels = vm.getMetadata().getLabels();
			labels.put("henry", "henry");
			vm.getSpec().getLifecycle().getCreateAndStartVM().setMemory("2048");
		}
		boolean successful = client.virtualMachines().update(vm);
		System.out.println(successful);
	}
	
}
