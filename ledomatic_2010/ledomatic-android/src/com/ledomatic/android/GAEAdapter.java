package com.ledomatic.android;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import android.util.Log;

public class GAEAdapter
{
	public class PostException extends Exception
	{

		public PostException(String string)
		{
			super(string);
		}

	}

	public enum Channel
	{
		RGB,
		OUT
	}

	private static GAEAdapter instance = null;

//	Map<String, Boolean> devices = new HashMap<String, Boolean>();

	private GAEAdapter()
	{
//		devices.put("blue LED", true);
//		devices.put("red LED", false);
//		devices.put("green LED", true);
	}

	public static GAEAdapter getInstance()
	{
		if (null == instance)
			instance = new GAEAdapter();

		return instance;
	}
	
	

	public List<String> getDevices()
	{

		List<String> results = new ArrayList<String>();
		
		String result = getRequest("listdevices");
		if(null != result){
			String[] split = result.split("=");
			if(null != split && split.length > 1)
			{
				String[] split2 = split[1].split(",");
				results.addAll(Arrays.asList(split2));
			}
		}
		

		return results;
	}



	public boolean getToogleState(String device)
	{

		
		String result = getRequest(device, Channel.OUT, "13" );
		if(result.equals("result=On"))
			return true;

		return false;
	}

	public void setRGBValue(String device, String color)
	{
		postRequest(device, Channel.RGB, "0" , "color=" + color);
	}

	public void setToggleState(String device, boolean state)
	{
		postRequest(device, Channel.OUT, "13" , "state=" +( state ? "On" : "Off") );
	}

	
	private String getRequest(String device, Channel channel, String pin)
	{
		String content = "";
		HttpClient httpclient = new DefaultHttpClient();
		HttpGet httpget = new HttpGet("http://led-o-matic.appspot.com/rr/" + device + "/" + channel.name() + "/" + pin );
		
		
		HttpResponse response;
		try
		{
			response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();
			int statusCode = response.getStatusLine().getStatusCode();
			if(statusCode != 200) throw new PostException("status: " + statusCode);
			InputStream stream = entity.getContent();
			content = readInputStream(stream);

		} catch (ClientProtocolException e)
		{
			// Handle not connecting to client
			Log.d("ledomatic", e.toString());
		} catch (IOException e)
		{
			// couldn't connect to host
			Log.d("ledomatic", e.toString());
		} catch (PostException e)
		{
			Log.d("ledomatic", e.toString());
		}
		return content;
		
	}
	
	private String getRequest(String command)
	{
		String content = "";
		HttpClient httpclient = new DefaultHttpClient();
		HttpGet httpget = new HttpGet("http://led-o-matic.appspot.com/rr/?command=" + command );
		
		
		HttpResponse response;
		try
		{
			response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();
			int statusCode = response.getStatusLine().getStatusCode();
			if(statusCode != 200) throw new PostException("status: " + statusCode);
			InputStream stream = entity.getContent();
			content = readInputStream(stream);

		} catch (ClientProtocolException e)
		{
			// Handle not connecting to client
			Log.d("ledomatic", e.toString());
		} catch (IOException e)
		{
			// couldn't connect to host
			Log.d("ledomatic", e.toString());
		} catch (PostException e)
		{
			Log.d("ledomatic", e.toString());
		}
		return content;
	}
	
	private String postRequest(String device, Channel channel, String pin, String parameter )
	{
		String content = "";
		HttpClient httpclient = new DefaultHttpClient();
		String url = "http://led-o-matic.appspot.com/rr/" + device + "/" + channel.name() + "/" + pin;
		
		Log.d("ledomatic", "post-url: " + url);
		
		HttpPost httpost = new HttpPost( url);
		
		try
		{
			httpost.setEntity( new StringEntity(parameter,  "8859-1" ));
		} catch (UnsupportedEncodingException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		HttpResponse response;
		try
		{
			response = httpclient.execute(httpost);
			HttpEntity entity = response.getEntity();
			int statusCode = response.getStatusLine().getStatusCode();
			if(statusCode != 200) throw new PostException("status: " + statusCode);
			InputStream stream = entity.getContent();
			content = readInputStream(stream);

		} catch (ClientProtocolException e)
		{
			// Handle not connecting to client
			Log.d("ledomatic", e.toString());
		} catch (IOException e)
		{
			// couldn't connect to host
			Log.e("ledomatic", e.toString(), e);
		} catch (PostException e)
		{
			Log.d("ledomatic", e.toString());
		}
		return content;
	}
	
    private String readInputStream(InputStream in) throws IOException {
    	
    	InputStreamReader reader = new InputStreamReader(in, "8859-1");   
    	BufferedReader buffReader = new BufferedReader(reader);
    	
    	StringBuffer stream = new StringBuffer();
    	String line = null;
    	while((line = buffReader.readLine()) != null)
    	{
    		stream.append(line);
    	}
    	
    	return stream.toString();
    	
    }

	public List<String> getFakeDevice()
	{
		List<String> fakeDevice = new ArrayList<String>();
		fakeDevice.add("L1");
		return fakeDevice;
	}

}
