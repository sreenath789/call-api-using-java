package com.example.callapiusingjava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@SpringBootApplication
public class CallApiUsingJavaApplication {

	public static void main(String[] args) throws IOException {

		URL url = null;
		HttpURLConnection httpURLConnection = null;
		int responseCode = 0;
		String api = "https://pokeapi.co/api/v2/pokemon/ditt";

		try{
			url = new URL(api);
		}
		catch (MalformedURLException e){
			System.out.println("problem in url");
		}

		//connection
		try{
			httpURLConnection = (HttpURLConnection) url.openConnection();
			responseCode = httpURLConnection.getResponseCode();
		}
		catch (Exception e){
			System.out.println("problem in connection");
		}

		//print the api
		if(responseCode==200){
			BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
			String readLine = null;
			StringBuilder sb = new StringBuilder();

			while((readLine=in.readLine())!=null){
				sb.append(readLine);
			}

			try{
				in.close();
			}
			catch (Exception e){
				throw new RuntimeException(e);
			}

			System.out.println(sb.toString());
		}
		else{
			System.out.println("api call not found");
		}



	}

}
