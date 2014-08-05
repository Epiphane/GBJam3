package com.gbjam.resource_mgmt;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationSheet {
	public TextureRegion[][] textures;
	public int[] numFrames;
	
	public AnimationSheet(String textureName, int... _numFrames) {
		// Find the longest 'state'
		int xSlices = 0;
		for (int ndx = 0; ndx < _numFrames.length; ndx++) {
			if (_numFrames[ndx] > xSlices) {
				xSlices = _numFrames[ndx];
			}
		}
		
		textures = Art.split(textureName, xSlices, _numFrames.length);
		numFrames = _numFrames;
	}
}
