package jgc.ij;

import ij.ImagePlus;

public interface DiscardStackModel {
	public boolean isCurrentEnabled();
	public ImagePlus getCurrentImage();
}
