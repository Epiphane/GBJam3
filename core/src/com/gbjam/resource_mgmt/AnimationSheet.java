package com.gbjam.resource_mgmt;

import java.util.List;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class AnimationSheet {
	public TextureRegion[][] textures;
	public int[] numFrames;
	
	public AnimationSheet(String textureName, int... _numFrames) {
		// Find the longest 'state'
		int xSlices = 0;
		for (int ndx = 0; ndx < _numFrames.length; ndx++) {
			if (_numFrames[ndx] > xSlices) {
				xSlices = 
			}
		}
		
		textures = Art.split(textureName);
		numFrames = _numFrames;
	}
	
}
