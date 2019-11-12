package aima.core.agent.impl;

import aima.core.agent.State;

/**
 * @author Ciaran O'Reilly
 */
public class DynamicState extends aima.core.agent.impl.ObjectWithDynamicAttributes implements State {
	public DynamicState() {

	}

	@Override
	public String describeType() {
		return State.class.getSimpleName();
	}
}