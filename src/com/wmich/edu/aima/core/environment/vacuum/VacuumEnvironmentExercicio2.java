package aima.core.environment.vacuum;

import aima.core.agent.Action;
import aima.core.agent.Agent;

public class VacuumEnvironmentExercicio2 extends VacuumEnvironment {

	@Override
	public void executeAction(Agent a, Action action) {
		String loc = getAgentLocation(a);
		if (ACTION_MOVE_RIGHT == action) {
			int x = getX(loc);
			if (x < getXDimension())
				envState.setAgentLocation(a, getLocation(x + 1, getY(loc)));
			updatePerformanceMeasure(a, -2);
		} else if (ACTION_MOVE_LEFT == action) {
			int x = getX(loc);
			if (x > 1)
				envState.setAgentLocation(a, getLocation(x - 1, getY(loc)));
			updatePerformanceMeasure(a, -2);
		} else if (ACTION_SUCK == action) {
			if (LocationState.Dirty == envState.getLocationState(envState.getAgentLocation(a))) {
				envState.setLocationState(envState.getAgentLocation(a), LocationState.Clean);
				updatePerformanceMeasure(a, 50);
			}
		} else if (action.isNoOp()) {
			// In the Vacuum Environment we consider things done if
			// the agent generates a NoOp.
			isDone = true;
		}
	}
}
