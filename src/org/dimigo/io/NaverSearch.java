/**
 * 
 */
package org.dimigo.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * <pre>
 * org.dimigo.io
 *   |_ NaverSearch
 * 
 * 1. 개요 : 
 * 2. 작성일 : 2015. 9. 28.
 * </pre>
 *
 * @author		: teacher
 * @version		: 1.0
 */
public class NaverSearch {

	public static final String NAVER_OPEN_URL = "http://openapi.naver.com/search";
	public static final String SEARCH_KEY = "f45d2780e74669231a450c8cb3ffc78f";
	
	public static void main(String[] args) throws IOException {		
		CloseableHttpClient httpclient = HttpClients.createDefault();
		File f1 = new File("Files/movies.txt");
		BufferedWriter bw = null;
		FileWriter fw = new FileWriter(f1);
		
	    try(InputStream is = System.in;
	    	Reader reader = new InputStreamReader(is);
	    	BufferedReader br = new BufferedReader(reader)) {
	    	String keyword = null;
	    	
	    	/********************************************************
	         * 표준입력으로 검색할 키워드를 입력받기
	         * > 키워드를 입력하세요 => 스타워즈
	         ********************************************************/
	    	
	    	System.out.printf("키워드를 입력하시오 -> ");
	    	keyword = br.readLine();
	    	
	    	
	    	StringBuffer sb = new StringBuffer(NAVER_OPEN_URL);
	    	sb.append("?key=").append(SEARCH_KEY).append("&query=").append(keyword)
	    	  .append("&display=10&start=1&target=movie");
	    	
	    	HttpGet httpget = new HttpGet(sb.toString());
	        System.out.println("Executing request " + httpget.getRequestLine());

	        // Create a custom response handler
	        ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

	            @Override
	            public String handleResponse(
	                    final HttpResponse response) throws ClientProtocolException, IOException {
	                int status = response.getStatusLine().getStatusCode();
	                if (status >= 200 && status < 300) {
	                    HttpEntity entity = response.getEntity();
	                    return entity != null ? EntityUtils.toString(entity) : null;
	                } else {
	                    throw new ClientProtocolException("Unexpected response status: " + status);
	                }
	            }

	        };
	        
	        String responseBody = httpclient.execute(httpget, responseHandler);
	        System.out.println("----------------------------------------");
	        System.out.println(responseBody);	        
	        
	        List<Movie> movieList = NaverMovieXMLParser.parse(responseBody);
	        
	        System.out.println(movieList);
	        
	        /********************************************************
	         * 검색된 순서대로 출력하기
	         * 1. 스타워즈 에피소드 8 - [오스카 아이삭, 베네치오 델 토로]
	         * 2. 스타 워즈 앤솔로지: 로그 원 - [펠리시티 존스, 벤 멘델슨, .. ]
	         * 3. 스타워즈: 깨어난 포스 - [해리슨 포드, 마크 해밀, 캐리 피셔, .. ]
	         ********************************************************/
	        
	        for(Movie  movie : movieList)	{
	        	String sysomovie = (movieList.indexOf(movie) + 1) + "." + movie.getTitle() + " - " + movie.getActors().toString();
	        	System.out.println(sysomovie);
	        	// Files/movies.txt 파일에 write하기
	        	fw.write(sysomovie + '\n');
	        }
	        
	        fw.close();
	        
	    } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
	        try {
				if(httpclient != null) httpclient.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }

	}

}
