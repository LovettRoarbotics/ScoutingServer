package com.roarbotics.net;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.roarbotics.Options;

public class GetFile {
	static ServerSocket serverSocket;
	static InputStream in;
	static Socket socket;
	static FileOutputStream out;
	static int maxsize = 256;
	static int byteread;
	static int current = 0;

	public static void open() throws FileNotFoundException, IOException {
		try {
			serverSocket = new ServerSocket(Options.port);
		} catch (Exception e) {
			System.out.println(e + "\nmake sure to close the port first.");
		}
		File test = new File("scout.txt");
		test.createNewFile();

		System.out.println("Server Opened.");
		out = new FileOutputStream(test);
		System.out.println("created FOS");

		socket = null;

		try {
			socket = serverSocket.accept();
		} catch (IOException ex) {
			System.out.println("Can't accept client connection. ");
		}
		System.out.println("Client Connected");

		try {
			in = socket.getInputStream();
		} catch (IOException ex) {
			System.out.println("Can't get socket input stream. ");
		}
		System.out.println("InputStream connected");
		System.out.println("Done.\n");
	}

	public static void listen() throws IOException {
		byte[] bytes = new byte[16 * 1024];

		int count;
		while ((count = in.read(bytes)) > 0) {
			out.write(count);
			System.out.println("Received " + count);
		}
	}

	public static void close() {
		try {
			socket.close();
		} catch (IOException e) {
			System.out.println("Socket closing error: " + e);
			e.printStackTrace();
		}
		try {
			in.close();
		} catch (IOException e1) {
			System.out.println("InputStream closing error: " + e1);
			e1.printStackTrace();
		}
		try {
			out.close();
		} catch (IOException e2) {
			System.out.println("FileOutputStream closing error: " + e2);
			e2.printStackTrace();
		}
		System.out.println("Closed.");
	}
}
