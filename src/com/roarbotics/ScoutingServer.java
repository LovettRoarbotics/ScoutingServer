package com.roarbotics;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.roarbotics.net.GetFile;

public class ScoutingServer {
	public static void main (String args[]) throws FileNotFoundException, IOException{
		GetFile.open();
		while (true){
			GetFile.listen();
		}
	}
}
