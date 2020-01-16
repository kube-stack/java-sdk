/*
 * Copyright (2019, ) Institute of Software, Chinese Academy of Sciences
 */
package com.uit.cloud.kubernetes;

import com.github.kubesys.kubernetes.ExtendedKubernetesClient;
import com.github.kubesys.kubernetes.api.model.VirtualMachine;

import io.fabric8.kubernetes.client.Config;
import io.fabric8.kubernetes.client.ConfigBuilder;

/**
 * @author wuheng@otcaix.iscas.ac.cn
 * @since  2019/7/15
 *
 */
public class AbstractTest {

	public static Config config = new ConfigBuilder()
			.withApiVersion("v1")
			.withCaCertData("LS0tLS1CRUdJTiBDRVJUSUZJQ0FURS0tLS0tCk1JSUN5RENDQWJDZ0F3SUJBZ0lCQURBTkJna3Foa2lHOXcwQkFRc0ZBREFWTVJNd0VRWURWUVFERXdwcmRXSmwKY201bGRHVnpNQjRYRFRFNU1URXhPVEE0TVRVeE1Wb1hEVEk1TVRFeE5qQTRNVFV4TVZvd0ZURVRNQkVHQTFVRQpBeE1LYTNWaVpYSnVaWFJsY3pDQ0FTSXdEUVlKS29aSWh2Y05BUUVCQlFBRGdnRVBBRENDQVFvQ2dnRUJBS1paCktyK0tnSnc4M1gzbHRtd0VJYkloZ0podDdEZ2xLdExqT1VnWVZRVXZ2K0UrbUQ0ZlArV1BtcExCTzV5U1hkeEIKSjk4WXFaTzh0RlFDNVc2Q2ZCbE1TbnI5ZGJWNW9sNzBMRmdudXBkWi9lTGUxcFcvdEQ5MERvekxnTWtydG8rUwpoZHBpRGZ4SHVUOStLcWdyRXlmcXpML2UweGkzOUpxMTJkd0hHVGRVS3c3T2JoWndncXRZWEx6VDMxSllpUFFKCnMxYXZXbkV1RjF3V1RkQ0x1V0hzZlRiOThkSisrS3dBd3pCVUJiaVpETlZ4VEk5Ty9mMWtIYXdjVm1ybSt1VE4KYUxPTWpwb2RLSVpVM1QyTEozU1Z0eCtKeUZqZ25WMEZyemRqWnhTeVZ4ODVIeHpHczZmNWFoNVVDVExFaVVrMAp4RGJmQkQvUG9hQUNIMzVqallVQ0F3RUFBYU1qTUNFd0RnWURWUjBQQVFIL0JBUURBZ0trTUE4R0ExVWRFd0VCCi93UUZNQU1CQWY4d0RRWUpLb1pJaHZjTkFRRUxCUUFEZ2dFQkFBOXg4ZDA0TGNKaSt1RDhPVVJmREFhR20vWVcKcFBvWlRrOUpaUWxaZHZhQW1Gc2pRU3lydGlsaERPV0hvUVRNVEJzK2ovNG1ubWVEeU9IbEorclhJRDBKUUN2dApOWmFwbVpDUDl6NC9ueVI5eWRYcW1PRDJBWjdydVRQMlhLYkZDalZqZjFDQ2VQZmEvNDBDWFFyRHNpbkJ2eW5uCkpZM0RvVlN0NGN5WFVlL2FuSUg2bW05L3h1NDljYkF6eTMrck9lWU9CdjdrWkpJRVJ3NElBZTBjZHRubHNhZjYKOXBMaHJRN29Dc3VRSDRMemMwT1BLTzhpWHpzY0podGZvKzRjc2szb3lVakF1NDA2RWNjQXUxeFpVMkw0eHg1TQplRWp3eTUwR1NwcWpIVVRyUEFDbERTb0NGR3R5WWRuQmI1cmwyVXNnWnpiTGdjYUNTRE95cDF2N3BVUT0KLS0tLS1FTkQgQ0VSVElGSUNBVEUtLS0tLQo=")
			.withClientCertData("LS0tLS1CRUdJTiBDRVJUSUZJQ0FURS0tLS0tCk1JSUM4akNDQWRxZ0F3SUJBZ0lJTlFOa3RwSjVzSDB3RFFZSktvWklodmNOQVFFTEJRQXdGVEVUTUJFR0ExVUUKQXhNS2EzVmlaWEp1WlhSbGN6QWVGdzB4T1RFeE1Ua3dPREUxTVRGYUZ3MHlNREV4TVRnd09ERTFNVFphTURReApGekFWQmdOVkJBb1REbk41YzNSbGJUcHRZWE4wWlhKek1Sa3dGd1lEVlFRREV4QnJkV0psY201bGRHVnpMV0ZrCmJXbHVNSUlCSWpBTkJna3Foa2lHOXcwQkFRRUZBQU9DQVE4QU1JSUJDZ0tDQVFFQXEyaC82K0svUHhVdDNYaWUKclhBalBpYWgyUFNMbFREMnVFWUprV2JpRlZIdzNtdWxEamVvQkljNkVKQzNHb05IYlhoQmx4c3RkeS93ZHh4OApSUUp5YjI5cmtBY0pnNVM3OTBsYkpzVlZuNy9UdUMyNjdCY2ZtVTk5Y2dXaEFpMjR4OWdkcTFBTU1ZcGhpSHBuCkZ1UStFUHRwOTdyRDUzeGx6bngvUzFOd0h5VE81SE1aQ2Y0UkpVTXcyYkhPS3lBR21vNzFkekV5THgyYmtOaVgKV05od1hmejRCcWx3dHd0T0ZqQnVYVjVYTTI1RzIvS3JEME5IMmhRcDRmYVFPamlrRk5iT21vdERVSDR5WkZ2WApXQnNBa2Jhd0EvaFEzNVovRmgxSHBWQ0IrZlpLKzZaYmE5QlhXRTJySXdYSnBJQW9uM1lYTEtNNjg1d3pNYUsrClp1M3REUUlEQVFBQm95Y3dKVEFPQmdOVkhROEJBZjhFQkFNQ0JhQXdFd1lEVlIwbEJBd3dDZ1lJS3dZQkJRVUgKQXdJd0RRWUpLb1pJaHZjTkFRRUxCUUFEZ2dFQkFJVE9VaHprRFBDUnRFcHd4R04wckRBWEdNTWE3aEpJcWNBbQp5TnpRdE5wbDBJc1BjRjNycXdLbUc1VHVaY3M3UTF6N00zdjRLZ2VJUW9CRWdDa0poeEh6Q3AwVUViLzI1RjJwCmNuY2JwUGxVdUljeXpIWS9BQ1d0bklJQi81ZklpclhZbUl6MXZESmd2OWxXOWpvcTNwK1lNNnBmM2ZlRDNaRVQKRWxxNi9iNXpaTWhUa1RxR2gwdCsvWHp4NDRTUmtFTVpmekYrL1JVdUVUSWFCTHNtWStzWFFyR0dUS01rdWVMbgpzY3licHp5RnZJNjJNUHFRcnFqOUFkVmh4dGdHdkozemxuSEk5MXpzNVQ3OCtmQ0xnazFTdzJRSEwvUy9nOTVvCm12c003NzUzNmw4b2laVHMvSEJGS0Rhd1BscUJOMWh4cmhaZ3lWNEE2bE5VSCsvaHh5RT0KLS0tLS1FTkQgQ0VSVElGSUNBVEUtLS0tLQo=")
			.withClientKeyData("LS0tLS1CRUdJTiBSU0EgUFJJVkFURSBLRVktLS0tLQpNSUlFcEFJQkFBS0NBUUVBcTJoLzYrSy9QeFV0M1hpZXJYQWpQaWFoMlBTTGxURDJ1RVlKa1diaUZWSHczbXVsCkRqZW9CSWM2RUpDM0dvTkhiWGhCbHhzdGR5L3dkeHg4UlFKeWIyOXJrQWNKZzVTNzkwbGJKc1ZWbjcvVHVDMjYKN0JjZm1VOTljZ1doQWkyNHg5Z2RxMUFNTVlwaGlIcG5GdVErRVB0cDk3ckQ1M3hsem54L1MxTndIeVRPNUhNWgpDZjRSSlVNdzJiSE9LeUFHbW83MWR6RXlMeDJia05pWFdOaHdYZno0QnFsd3R3dE9GakJ1WFY1WE0yNUcyL0tyCkQwTkgyaFFwNGZhUU9qaWtGTmJPbW90RFVINHlaRnZYV0JzQWtiYXdBL2hRMzVaL0ZoMUhwVkNCK2ZaSys2WmIKYTlCWFdFMnJJd1hKcElBb24zWVhMS002ODV3ek1hSytadTN0RFFJREFRQUJBb0lCQUZQVWc5dWx4Y3JnWE5JWQpEK0xiR1NGb1czZDBBbUF3MWpsQlo2U0pJTUUwbGd6eHNJUEhqSkRLWWdkLy8wMGtMMmJHamEyTlNTc2xjd0dPCkdPalhUWWhhZ2N5cTVHaEo4YlA3V0cwWnpjcVhVc0NjL1BKQ1ROMzZGbXdSZkxGSUV6c0o0MWpKaVcwaE5KbysKNHZRN0pHL0J4ZzIyZGVGYzRaMmZIZEtGT0N6Q1VxWlpleWhxRU1wV3RKeUhZU1dqczFZR1pOVnVQYUlBSktybAo0bkN1OGhFQ2U0a2hWVWwySUtIYmt6b3hDMWNlQWJJWW5vRnFYS2M2MmpFdkpBazh3NFlSYU9GQmxRUXlTcXRzCldZc1NueENza1E4RktkQVVtMWIxR2FZZ1NnbVIvODZoeEFKQVN3a0xaaTJxUmhYOXpENWV1TklRMnVNQnQwQXIKZUc4SXFERUNnWUVBeUtaV28xREtjMVRaME9lY0N6NzlRZElkWlNRd3lqVTJvUVNvY0I4dGJOdFl2TmsvNVNtTgovZ1RuU3YxTXNzUFVOM043c2l1Y3M3Sk81TkxWNm53QUhHd1J5ekFQYXZiK24yTjVIeVdNS1ZFRTBRbnA5WHBRClFpRFYwaUFsbXk5Z2YxaHJ0ZEUrd0EyTk5KbVliUzNZTzI3MGhaaktWTDU3NlVYbTdmYVJVdWNDZ1lFQTJyRW4KdWJuTWdxZXVqUCtoallkdHZqaEhzRjZiSzQ5NEVNcUh4ckNKS0tKa3Z2ZGhraHlXODBwWDQ3aDNrNURwczBUYgpLQmVma01uQVZxRlg0RHlxT2lGdUYweGVkM2dEcUJ1Qmx2R3p1VXFCZk40VmNWMnU3OFpvWklldERvVVpCdlhOClpUSkJmS0w1Rk15VUZaR09vMEtoQXhVeEVYK1BjemJ6SEkwYk5lc0NnWUVBeFlDZDFTSElOVi8zQ3g5L3hMNGEKMGJnOXdQbXZvTXBpWEhwV1JnOGFTb1ZhUW9GaDJITmYxZ01EbkJmYWNEZzhZdHlrakV0TGNzSDMwRnNMdjBTUgpZMmlvMXlVV2VBWnB1WUJUaitvSW5mNm16QkNESDJObkxzQmRWL2pXcTNzbDlCT1g2aDd1TW1Ma2REc2dBNC9WCndTTEZROStTZ1hvQ1JDclBWYnFYYjA4Q2dZRUF4emx4L2lFRDdOYkREaEhtNDU3SFcrMjdhVEdXWEdXVHRJQTgKbHNYRi9hMks1K2xIQ3F0ZTNINFNlUkpZWktjMEx5c0FGNU1GdXdvQVhTc1llZC9xRjVEUzBRaXlXbFdMeHo1ZgpuTm5OcDNQM2FJQ011anpsRyt2OHQvQmR1QWVLV0N2ZWxGem5DWnNTSEYrT1crUEJOSHVEWU9LU1UrV0lFaWF4CjdJV2pqMGNDZ1lBYnYvVDFHajg1NkhqMnN0YTZsalBBZDJsT253OVlES3ZkSUxYR0dXVFFJWXViV3ZmVFF1eWQKYlNkN1VtTEh0Mnp0Wk4xQlhoQ2w3VCtuQkI3U1RiOUR5dUpoaW1tYUJBc3VmYzU1c1F1cWVNU3NCc1RKMk9SQwpKeWxwZ0ppMUtieWN2bU12aDNzdmVLUG1JNmFIZnQvNVduZGtlWHQ5V0lGR3B6N0d0N2ZrMlE9PQotLS0tLUVORCBSU0EgUFJJVkFURSBLRVktLS0tLQo=")
			.withMasterUrl("https://133.133.135.35:6443")
			.build();
	
	public static ExtendedKubernetesClient getClient() throws Exception {
		return new ExtendedKubernetesClient(config);
	}
	
	public static VirtualMachine getVMByName(String name) throws Exception {
		return getClient().virtualMachines().get(name);
	}
	
}
