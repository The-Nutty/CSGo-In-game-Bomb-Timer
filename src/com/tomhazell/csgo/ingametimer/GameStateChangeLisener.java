package com.tomhazell.csgo.ingametimer;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.apache.commons.io.*;

public class GameStateChangeLisener {

	IGameState Callback;

	public void Start() throws IOException {
		InetSocketAddress addr = new InetSocketAddress(3000);
		HttpServer server = HttpServer.create(addr, 0);

		server.createContext("/", new GameStateHandler());
		server.setExecutor(Executors.newCachedThreadPool());
		server.start();
	}
	
	public GameStateChangeLisener(IGameState callback){
		Callback = callback;
		
	}

	class GameStateHandler implements HttpHandler {
		public void handle(HttpExchange exchange) throws IOException {
			String requestMethod = exchange.getRequestMethod();

			InputStream sin = exchange.getRequestBody();
			
			String theString = IOUtils.toString(sin, "utf-8");
			Callback.OnGameStateRecived(theString);
			
			Headers responseHeaders = exchange.getResponseHeaders();
			responseHeaders.set("Content-Type", "text/plain");
			OutputStream responseBody = exchange.getResponseBody();
			responseBody.write(0);
			responseBody.flush();
			exchange.sendResponseHeaders(200, 0);

			responseBody.close();
		}
	}
}