package com.teamo.fun.games.framework.impl;

import java.io.IOException;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;

import com.teamo.fun.games.framework.Music;

public class AndroidMusic implements Music, OnCompletionListener {
	
	MediaPlayer	mediaPlayer;
	boolean		isPrepared	= false;
	
	public AndroidMusic(AssetFileDescriptor assetDescriptor) {
		this.mediaPlayer = new MediaPlayer();
		
		try {
			mediaPlayer.setDataSource(assetDescriptor.getFileDescriptor(), assetDescriptor.getStartOffset(), assetDescriptor.getLength());
			mediaPlayer.prepare();
			isPrepared = true;
			mediaPlayer.setOnCompletionListener(this);
		} catch (Exception e) {
			throw new RuntimeException("Couldn't load music");
		}
	}
	
	public void play() {
		if (mediaPlayer.isPlaying())
			return;
		
		try {
			synchronized (this) {
				if (!isPrepared)
					mediaPlayer.prepare();
				mediaPlayer.start();
			}
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void stop() {
		mediaPlayer.stop();
		synchronized (this) {
			isPrepared = false;
		}
	}
	
	public void pause() {
		// TODO Auto-generated method stub
		
	}
	
	public void setLooping(boolean looping) {
		mediaPlayer.setLooping(looping);
	}
	
	public void setVolume(float volume) {
		mediaPlayer.setVolume(volume, volume);
	}
	
	public boolean isPlaying() {
		return mediaPlayer.isPlaying();
	}
	
	public boolean isStopped() {
		return !isPrepared;
	}
	
	public boolean isLooping() {
		return mediaPlayer.isLooping();
	}
	
	public void dispose() {
		if (mediaPlayer.isPlaying())
			mediaPlayer.stop();
		mediaPlayer.release();
	}
	
	public void onCompletion(MediaPlayer mp) {
		synchronized (this) {
			isPrepared = false;
		}
	}
}
