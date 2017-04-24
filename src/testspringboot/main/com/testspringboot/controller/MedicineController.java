package com.testspringboot.controller;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.testspringboot.api.MedicineManager;
import com.testspringboot.domain.Medicine;

@RestController
@RequestMapping("/medicines")
public class MedicineController extends BaseController{
	
	@Autowired
	private MedicineManager medicineManager;
	
	@RequestMapping(method = RequestMethod.POST)
	public Medicine addMedicine(@RequestBody Medicine medicine){
		return this.medicineManager.addNew(medicine);
	}
	
	@RequestMapping(value = "/{id}",method = RequestMethod.GET)
	public Medicine getMedicine(@PathVariable("id") String id){
		return this.medicineManager.find(id);
	}
	
	@RequestMapping(value = "/salesforce",method = RequestMethod.GET)
	public String getCode(@RequestParam("code") String code) throws ClientProtocolException, IOException{
		System.out.println("code provided is ::::::::"+code);
		
		
		String url = "https://login.salesforce.com/services/oauth2/token?format=json";

		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders header = new HttpHeaders();
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", "3MVG9ZL0ppGP5UrD6fVPrguxGpk8rDdwJoyOBetc0ytKKZX_r8aOKlv.SfVDj1YZ8Eb.mfZZUtWmddJQEOTxt");
		params.add("redirect_uri", "http://localhost:9090/medicines/salesforce");
		params.add("code", code);
		params.add("client_secret", "8055122779223746116");
		HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(
				params, header);

		ResponseEntity<String> result = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

		System.out.println(result.getBody());
		String responseString = result.getBody();
		JSONObject json = new JSONObject(responseString);
		
		url = json.getString("instance_url")+ "/services/data/v36.0/query/?q=select+id,name+from+organization";	
		System.out.println("url is "+url);
//		header = new HttpHeaders();
//		header.add("Authorization", "Bearer "+json.getString("access_token"));
//		requestEntity = new HttpEntity<MultiValueMap<String, String>>(
//				new LinkedMultiValueMap<String, String>(), header);
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		headers.add("Authorization", "Bearer " + json.getString("access_token"));
		headers.add("Content-Type", "application/json");
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

		requestEntity = new HttpEntity<MultiValueMap<String, String>>(new LinkedMultiValueMap<String, String>(), headers);
		
		result = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
		System.out.println(result.getBody());
		responseString = result.getBody();
		
//		//using code given from salesforce to generate token
//		DefaultHttpClient httpclient = new DefaultHttpClient();
//	    HttpPost httpPost = new HttpPost("https://login.salesforce.com/services/oauth2/token?format=json");
//	 
//	    List<NameValuePair> params = new ArrayList<NameValuePair>();
//	    params.add(new BasicNameValuePair("grant_type", "authorization_code"));
//	    params.add(new BasicNameValuePair("client_id", "3MVG9ZL0ppGP5UrD6fVPrguxGpk8rDdwJoyOBetc0ytKKZX_r8aOKlv.SfVDj1YZ8Eb.mfZZUtWmddJQEOTxt"));
//	    params.add(new BasicNameValuePair("client_secret", "8055122779223746116"));
//	    params.add(new BasicNameValuePair("redirect_uri", "http://localhost:9090/medicines/salesforce"));
//	    params.add(new BasicNameValuePair("code", code));
//	    httpPost.setEntity(new UrlEncodedFormEntity(params));
//	 
//	    HttpResponse response = httpclient.execute(httpPost);
//	    HttpEntity entity = response.getEntity();
//	    String responseOfLogin = EntityUtils.toString(entity);
//	    System.out.println("response is "+responseOfLogin);
//	    
//	    
//	    //using refresh token to generate access token sales force
//	    JSONObject json = new JSONObject(responseOfLogin);
//	    String refreshToken = json.getString("refresh_token");
//	    System.out.println("refreshToken is ::::::::::::"+refreshToken);
//	    httpPost = new HttpPost("https://login.salesforce.com/services/oauth2/token?format=json");
//	 
//	    params = new ArrayList<NameValuePair>();
//	    params.add(new BasicNameValuePair("grant_type", "refresh_token"));
//	    params.add(new BasicNameValuePair("client_id", "3MVG9ZL0ppGP5UrD6fVPrguxGpk8rDdwJoyOBetc0ytKKZX_r8aOKlv.SfVDj1YZ8Eb.mfZZUtWmddJQEOTxt"));
//	    params.add(new BasicNameValuePair("client_secret", "8055122779223746116"));
//	    params.add(new BasicNameValuePair("refresh_token", refreshToken));
//	    httpPost.setEntity(new UrlEncodedFormEntity(params));
//	 
//	    response = httpclient.execute(httpPost);
//	    entity = response.getEntity();
//	    String responseOfRefreshToken = EntityUtils.toString(entity);
//	    System.out.println("responseOfRefreshToken is "+responseOfRefreshToken);
//	    httpclient.getConnectionManager().shutdown(); 
		
		return responseString;
		
	}
}
