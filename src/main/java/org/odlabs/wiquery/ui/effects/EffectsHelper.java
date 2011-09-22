/*
 * Copyright (c) 2009 WiQuery team
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.odlabs.wiquery.ui.effects;

import org.odlabs.wiquery.core.commons.WiQueryResourceManager;

/**
 * $Id$
 * 
 * <p>
 * 	Helper to load some jQuery UI effects
 * </p>
 *
 * @author Julien Roche
 * @since 1.0.2
 */
public abstract class EffectsHelper {
	/**
	 * Method to load the blind effect
	 * @param manager
	 */
	public static void blind(WiQueryResourceManager manager) {
		manager.addJavaScriptResource(CoreEffectJavaScriptResourceReference.get());
		manager.addJavaScriptResource(BlindEffectJavaScriptResourceReference.get());
	}
	
	/**
	 * Method to load the bounce effect
	 * @param manager
	 */
	public static void bounce(WiQueryResourceManager manager) {
		manager.addJavaScriptResource(CoreEffectJavaScriptResourceReference.get());
		manager.addJavaScriptResource(BounceEffectJavaScriptResourceReference.get());
	}
	
	/**
	 * Method to load the clip effect
	 * @param manager
	 */
	public static void clip(WiQueryResourceManager manager) {
		manager.addJavaScriptResource(CoreEffectJavaScriptResourceReference.get());
		manager.addJavaScriptResource(ClipEffectJavaScriptResourceReference.get());
	}
	
	/**
	 * Method to load the drop effect
	 * @param manager
	 */
	public static void drop(WiQueryResourceManager manager) {
		manager.addJavaScriptResource(CoreEffectJavaScriptResourceReference.get());
		manager.addJavaScriptResource(DropEffectJavaScriptResourceReference.get());
	}
	
	/**
	 * Method to load the explode effect
	 * @param manager
	 */
	public static void explode(WiQueryResourceManager manager) {
		manager.addJavaScriptResource(CoreEffectJavaScriptResourceReference.get());
		manager.addJavaScriptResource(ExplodeEffectJavaScriptResourceReference.get());
	}
	
	/**
	 * Method to load the fold effect
	 * @param manager
	 */
	public static void fold(WiQueryResourceManager manager) {
		manager.addJavaScriptResource(CoreEffectJavaScriptResourceReference.get());
		manager.addJavaScriptResource(FoldEffectJavaScriptResourceReference.get());
	}
	
	/**
	 * Method to load the highlight effect
	 * @param manager
	 */
	public static void highlight(WiQueryResourceManager manager) {
		manager.addJavaScriptResource(CoreEffectJavaScriptResourceReference.get());
		manager.addJavaScriptResource(HighlightEffectJavaScriptResourceReference.get());
	}
	
	/**
	 * Method to load the pulsate effect
	 * @param manager
	 */
	public static void pulsate(WiQueryResourceManager manager) {
		manager.addJavaScriptResource(CoreEffectJavaScriptResourceReference.get());
		manager.addJavaScriptResource(PulsateEffectJavaScriptResourceReference.get());
	}
	
	/**
	 * Method to load the scale effect
	 * @param manager
	 */
	public static void scale(WiQueryResourceManager manager) {
		manager.addJavaScriptResource(CoreEffectJavaScriptResourceReference.get());
		manager.addJavaScriptResource(ScaleEffectJavaScriptResourceReference.get());
	}
	
	/**
	 * Method to load the shake effect
	 * @param manager
	 */
	public static void shake(WiQueryResourceManager manager) {
		manager.addJavaScriptResource(CoreEffectJavaScriptResourceReference.get());
		manager.addJavaScriptResource(ShakeEffectJavaScriptResourceReference.get());
	}
	
	/**
	 * Method to load the slide effect
	 * @param manager
	 */
	public static void slide(WiQueryResourceManager manager) {
		manager.addJavaScriptResource(CoreEffectJavaScriptResourceReference.get());
		manager.addJavaScriptResource(SlideEffectJavaScriptResourceReference.get());
	}
	
	/**
	 * Method to load the transfer effect
	 * @param manager
	 */
	public static void transfer(WiQueryResourceManager manager) {
		manager.addJavaScriptResource(CoreEffectJavaScriptResourceReference.get());
		manager.addJavaScriptResource(TransferEffectJavaScriptResourceReference.get());
	}
}
