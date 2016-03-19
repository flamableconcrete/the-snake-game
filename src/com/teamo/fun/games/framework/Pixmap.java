package com.teamo.fun.games.framework;

import com.teamo.fun.games.framework.Graphics.PixmapFormat;

public interface Pixmap {
	
    public int getWidth();
    public int getHeight();

    public PixmapFormat getFormat();

    public void dispose();
    
}