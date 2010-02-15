package org.odlabs.wiquery.core.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.AbstractBehavior;
import org.apache.wicket.behavior.IBehavior;
import org.odlabs.wiquery.core.behavior.WiQueryAbstractBehavior;

public class BehaviorHelper {

	public static boolean hasBehavior(Component component, Class<? extends AbstractBehavior> clazz) {
		List<IBehavior> behaviors = component.getBehaviors();
		for (IBehavior behavior : behaviors) {
			if(behavior.getClass().equals(clazz)) {
				return true;
			}
		}
		return false;
	}
	
	public static List<WiQueryAbstractBehavior> getWiQueryBehaviors(Component component) {
		List<WiQueryAbstractBehavior> wqb = new ArrayList<WiQueryAbstractBehavior>();
		List<IBehavior> behaviors = component.getBehaviors();
		for (IBehavior behavior : behaviors) {
			if (behavior instanceof WiQueryAbstractBehavior) {
				wqb.add((WiQueryAbstractBehavior) behavior);
			}
		}
		return wqb;
	}
	
}
