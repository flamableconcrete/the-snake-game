package com.teamo.fun.games.thesnakegame;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import com.teamo.fun.games.framework.FileIO;

public class Settings {
	
	public static boolean	soundEnabled	= true;
	public static int[]		highscores		= new int[] { 100, 80, 50, 30, 1 };
	
	public static void load(FileIO files) {
		
		BufferedReader in = null;
		try {
			
			String defaultFileSettings = ".TheSnakeGameDefaults";
			in = new BufferedReader(new InputStreamReader(files.readFile(defaultFileSettings)));
			
			soundEnabled = Boolean.parseBoolean(in.readLine());
			
			for (int i = 0; i < 5; i++) {
				highscores[i] = Integer.parseInt(in.readLine());
			}
			
		} catch (IOException e) {
			// it's ok we have defaults
		} catch (NumberFormatException e) {
			// defaults save us again
		} finally {
			try {
				if (in != null)
					in.close();
			} catch (IOException e) {
				
			}
		}
		
	}
	
	public static void save(FileIO files) {
		
		BufferedWriter out = null;
		try {
			
			String defaultFileSettings = ".TheSnakeGameDefaults";
			out = new BufferedWriter(new OutputStreamWriter(files.writeFile(defaultFileSettings)));
			out.write(Boolean.toString(soundEnabled));
			
			for (int i = 0; i < 5; i++) {
				out.write(Integer.toString(highscores[i]));
			}
			
		} catch (IOException e) {
			// could put some exception handling here...
		} finally {
			try {
				if (out != null)
					out.close();
				
			} catch (IOException e) {
				// exception handling could go here
			}
		}
		
	}
	
	public static void addScore(int score) {
		
		for (int i = 0; i < 5; i++) {
			if (highscores[i] < score) {
				for (int j = 4; j > i; j--)
					highscores[j] = highscores[j - 1];
				highscores[i] = score;
				break;
			}
		}
		
	}
	
}
