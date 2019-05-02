package com.gonggongjohn.eok.handlers;

import com.gonggongjohn.eok.data.GrassTweaker;
import com.gonggongjohn.eok.data.GravelTweaker;
import com.gonggongjohn.eok.data.LeavesTweaker;
import com.gonggongjohn.eok.data.TreeTweaker;
import com.gonggongjohn.eok.items.ItemChippedFlintFragment;

public class EventHandler {
	public static void registerEvent() {
		new TreeTweaker();
		new GrassTweaker();
		new LeavesTweaker();
		new GravelTweaker();
		
	}
}
