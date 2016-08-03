package com.ibm.broker;

import java.util.Comparator;

public class FlowNameComparator implements Comparator<MessageFlowInfo> {
	
	public int compare(MessageFlowInfo o1, MessageFlowInfo o2) {

		if (o1.getFlowname().compareTo(o2.getFlowname()) > 0) {
			return 1;
		} else {
			if (o1.getFlowname().compareTo(o2.getFlowname()) == 0) {
				return 0;
			} else {
				return -1;
			}
		}		
	}
}
