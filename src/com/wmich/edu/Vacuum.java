package com.wmich.edu;

/*
 * Chad Hirsch
 * Chase Myers
 * Jonathan Sanford
 * 
 * CS5820
 * */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import aima.core.agent.Agent;
import aima.core.agent.Percept;
import aima.core.agent.impl.DynamicPercept;
import aima.core.agent.impl.SimpleActionTracker;
import aima.core.environment.vacuum.ModelBasedReflexVacuumAgent;
import aima.core.environment.vacuum.SimpleReflexVacuumAgent;
import aima.core.environment.vacuum.TableDrivenVacuumAgent;
import aima.core.environment.vacuum.VacuumEnvironment;
import aima.core.environment.vacuum.VacuumEnvironment.LocationState;

public class Vacuum {

	public static void main(String[] args) {
		
		//Arrays for storing the state
		String []sSR = new String[4];
		String []sTB = new String[4];
		String []sMD = new String[4];
		
		//ints for storing performance and steps respectively
		//index 0 is for clean clean,1 for clean dirty and so on
		int[] perfSR = new int[4];
		int[] perfTB = new int[4];
		int[] perfMD = new int[4];
		int[] stepSR = new int[4];
		int[] stepTB = new int[4];
		int[] stepMD = new int[4];

		String []actions = new String[5];
		String []actM = new String[5];
		int i;
		
		
		SimpleActionTracker actionTracker = new SimpleActionTracker();
		Agent aS = new SimpleReflexVacuumAgent();
		Agent aT = new TableDrivenVacuumAgent();
		Agent aM = new ModelBasedReflexVacuumAgent();
		
		//Sets up clean clean environment
		VacuumEnvironment cc = new VacuumEnvironment(
				VacuumEnvironment.LocationState.Clean,
				VacuumEnvironment.LocationState.Clean);
		
		//Sets up clean dirty environment
		VacuumEnvironment cD = new VacuumEnvironment(
				VacuumEnvironment.LocationState.Clean,
				VacuumEnvironment.LocationState.Dirty);	
		
		//Sets up dirty clean environment
		VacuumEnvironment dC = new VacuumEnvironment(
				VacuumEnvironment.LocationState.Dirty,
				VacuumEnvironment.LocationState.Clean);
		
		//Sets up clean dirty environment
		VacuumEnvironment dD = new VacuumEnvironment(
				VacuumEnvironment.LocationState.Dirty,
				VacuumEnvironment.LocationState.Dirty);
		
		cc.addAgent(aS, VacuumEnvironment.LOCATION_A);
		cc.addEnvironmentView((aima.core.agent.EnvironmentView) actionTracker);
		
		//set up table block
		System.out.println("Agent \t\tSimple Based Vacuum Agent");
		System.out.println("Initial State:\t" + cc.getCurrentState());
		printDash();
		System.out.println("Steps\t\tPercept\t\t\t\tAction\t\tPerformance\tEnvironment State");
		printDash();
		
		//Clean Clean Simple Agent
		for(i = 0;i < 5;i++) {
			System.out.print(i+1 + "\t");
			System.out.print(cc.getCurrentState() + "\t");
			cc.step();
			actions[i] = actionTracker.getActions();
			actionPrint(actions[i],i);
			System.out.print(cc.getPerformanceMeasure(aS) + "\t");
			System.out.print(cc.getCurrentState());
			System.out.println();
		}
		
		//resets variables for next run
		printDash();
		perfSR[0] = (int) cc.getPerformanceMeasure(aS);
		sSR[0] = cc.getCurrentState().toString();
		stepSR[0] = i;
		cc = new VacuumEnvironment(LocationState.Clean,LocationState.Clean);
		cc.removeAgent(aS);
		actionTracker = new SimpleActionTracker();
		Arrays.fill(actions, null);
		
		cD.addAgent(aS, VacuumEnvironment.LOCATION_A);
		cD.addEnvironmentView((aima.core.agent.EnvironmentView) actionTracker);
		
		//set up table block
		System.out.println("Agent \t\tSimple Based Vacuum Agent");
		System.out.println("Initial State:\t" + cD.getCurrentState());
		printDash();
		System.out.println("Steps\t\tPercept\t\t\t\tAction\t\tPerformance\tEnvironment State");
		printDash();
		
		//Clean Dirty Simple Agent
		for(i = 0;i < 5;i++) {
			System.out.print(i+1 + "\t");
			System.out.print(cD.getCurrentState() + "\t");
			cD.step();
			actions[i] = actionTracker.getActions();
			actionPrint(actions[i],i);
			System.out.print(cD.getPerformanceMeasure(aS) + "\t");
			System.out.print(cD.getCurrentState());
			System.out.println();
		}
		
		printDash();
		perfSR[1] = (int) cD.getPerformanceMeasure(aS);
		sSR[1] = cD.getCurrentState().toString();
		stepSR[1] = i;
		cD.removeAgent(aS);
		cD = new VacuumEnvironment(LocationState.Clean, LocationState.Dirty);
		actionTracker = new SimpleActionTracker();
		Arrays.fill(actions, null);
		
		dC.addAgent(aS, VacuumEnvironment.LOCATION_A);
		dC.addEnvironmentView((aima.core.agent.EnvironmentView) actionTracker);
		
		//set up table block
		System.out.println("Agent \t\tSimple Based Vacuum Agent");
		System.out.println("Initial State:\t" + cD.getCurrentState());
		printDash();
		System.out.println("Steps\t\tPercept\t\t\t\tAction\t\tPerformance\tEnvironment State");
		printDash();
		
		//Dirty Clean Simple Agent
		for(i = 0;i < 5;i++) {
			System.out.print(i+1 + "\t");
			System.out.print(dC.getCurrentState() + "\t");
			dC.step();
			actions[i] = actionTracker.getActions();
			actionPrint(actions[i],i);
			System.out.print(dC.getPerformanceMeasure(aS) + "\t");
			System.out.print(dC.getCurrentState());
			System.out.println();
		}
		
		printDash();
		perfSR[2] = (int) dC.getPerformanceMeasure(aS);
		sSR[2] = dC.getCurrentState().toString();
		stepSR[2] = i;
		dC.removeAgent(aS);
		dC = new VacuumEnvironment(LocationState.Dirty, LocationState.Clean);
		actionTracker = new SimpleActionTracker();
		Arrays.fill(actions, null);
		
		dD.addAgent(aS, VacuumEnvironment.LOCATION_A);
		dD.addEnvironmentView((aima.core.agent.EnvironmentView) actionTracker);
		
		//set up table block
		System.out.println("Agent \t\tSimple Based Vacuum Agent");
		System.out.println("Initial State:\t" + dD.getCurrentState());
		printDash();
		System.out.println("Steps\t\tPercept\t\t\t\tAction\t\tPerformance\tEnvironment State");
		printDash();
		
		//Clean Dirty Simple Agent
		for(i = 0;i < 5;i++) {
			System.out.print(i+1 + "\t");
			System.out.print(dD.getCurrentState() + "\t");
			dD.step();
			actions[i] = actionTracker.getActions();
			actionPrint(actions[i],i);
			System.out.print(dD.getPerformanceMeasure(aS) + "\t");
			System.out.print(dD.getCurrentState());
			System.out.println();
		}
		
		printDash();
		perfSR[3] = (int) dD.getPerformanceMeasure(aS);
		sSR[3] = dD.getCurrentState().toString();
		stepSR[3] = i;
		dD.removeAgent(aS);
		dD = new VacuumEnvironment(LocationState.Dirty, LocationState.Dirty);
		actionTracker = new SimpleActionTracker();
		Arrays.fill(actions, null);
		
		printFTable(sSR, stepSR, perfSR, "Simple Reflex Agent");
		
		
		cc.addAgent(aM, VacuumEnvironment.LOCATION_A);
		System.out.println("Agent \t\tModel Based Reflex Vacuum Agent");
		System.out.println("Initial State:\t" + cc.getCurrentState());
		printDash();
		System.out.println("Steps\t\tPercept\t\t\t\tAction\t\tPerformance\tEnvironment State");
		printDash();
		
		//Clean Clean Model Agent
		System.out.print("1\t" + cc.getCurrentState() + "\tAction[name=NoOp]\t 0.0\t" + cc.getCurrentState() + "\n");
		
		//resets variables for next run
		printDash();
		perfMD[0] = (int) cc.getPerformanceMeasure(aM);
		sMD[0] = cc.getCurrentState().toString();
		stepMD[0] = 0;
		cc = new VacuumEnvironment(LocationState.Clean,LocationState.Clean);
		cc.removeAgent(aM);
		actionTracker = new SimpleActionTracker();
		Arrays.fill(actions, null);
		
		
		
		cD.addAgent(aM, VacuumEnvironment.LOCATION_A);
		cD.addEnvironmentView((aima.core.agent.EnvironmentView) actionTracker);
		System.out.println("Agent \t\tModel Based Reflex Vacuum Agent");
		System.out.println("Initial State:\t" + cD.getCurrentState());
		printDash();
		System.out.println("Steps\t\tPercept\t\t\t\tAction\t\tPerformance\tEnvironment State");
		printDash();
		int []temp = new int[5];
		
		//Clean Dirty Model Agent	
		for(i = 0;i < 5;i++) {
			
			System.out.print(i+1 + "\t");
			System.out.print(cD.getCurrentState() + "\t");
			cD.step();
			actions[i] = actionTracker.getActions();
			actionPrint(actions[i],i);
			System.out.print(cD.getPerformanceMeasure(aM) + "\t");
			temp[i]= (int)cD.getPerformanceMeasure(aM);
			System.out.print(cD.getCurrentState());
			System.out.println();
			
			if(i>0 && temp[i-1]==temp[i])
				break;
			
		}
			
				
		//resets variables for next run
		printDash();
		perfMD[1] = (int) cD.getPerformanceMeasure(aM);
		sMD[1] = cD.getCurrentState().toString();
		stepMD[1] = i;
		cD = new VacuumEnvironment(LocationState.Clean,LocationState.Dirty);
		cD.removeAgent(aM);
		actionTracker = new SimpleActionTracker();
		Arrays.fill(actions, null);
		
		//Randomizes array for next block
		for(i = 0;i < 5;i++)
			temp[i] = (int)Math.random();

		
		dC.addAgent(aM, VacuumEnvironment.LOCATION_A);
		dC.addEnvironmentView((aima.core.agent.EnvironmentView) actionTracker);
		System.out.println("Agent \t\tModel Based Reflex Vacuum Agent");
		System.out.println("Initial State:\t" + dC.getCurrentState());
		printDash();
		System.out.println("Steps\t\tPercept\t\t\t\tAction\t\tPerformance\tEnvironment State");
		printDash();
		
		//Dirty clean Model Agent	
		for(i = 0;i < 5;i++) {
			
			System.out.print(i+1 + "\t");
			System.out.print(dC.getCurrentState() + "\t");
			dC.step();
			actions[i] = actionTracker.getActions();
			actionPrint(actions[i],i);
			System.out.print(dC.getPerformanceMeasure(aM) + "\t");
			temp[i]= (int)dC.getPerformanceMeasure(aM);
			System.out.print(dC.getCurrentState());
			System.out.println();
			
			if(i>0 && temp[i-1]==temp[i])
				break;
			
		}
			
				
		//resets variables for next run
		printDash();
		perfMD[2] = (int) dC.getPerformanceMeasure(aM);
		sMD[2] = dC.getCurrentState().toString();
		stepMD[2] = i;
		dC = new VacuumEnvironment(LocationState.Dirty,LocationState.Clean);
		dC.removeAgent(aM);
		actionTracker = new SimpleActionTracker();
		Arrays.fill(actions, null);
		
		for(i = 0;i < 5;i++)
			temp[i] = (int)Math.random();
		aM = new ModelBasedReflexVacuumAgent();
		dD.addAgent(aM, VacuumEnvironment.LOCATION_A);
		dD.addEnvironmentView((aima.core.agent.EnvironmentView) actionTracker);
		System.out.println("Agent \t\tModel Based Reflex Vacuum Agent");
		System.out.println("Initial State:\t" + dD.getCurrentState());
		printDash();
		System.out.println("Steps\t\tPercept\t\t\t\tAction\t\tPerformance\tEnvironment State");
		printDash();
		
		//Dirty Dirty Model Agent	
		for(i = 0;i < 5;i++) {
			
			System.out.print(i+1 + "\t");
			System.out.print(dD.getCurrentState() + "\t");
			dD.step();
			actions[i] = actionTracker.getActions();
			actionPrint(actions[i],i);
			System.out.print(dD.getPerformanceMeasure(aM) + "\t");
			temp[i]= (int)dD.getPerformanceMeasure(aM);
			System.out.print(dD.getCurrentState());
			System.out.println();
			
			if(i>0 && temp[i-1]==temp[i])
				break;
			
		}
		//System.out.println("Note: Error occurs here and it appears to not be on library side");
				
		//resets variables for next run
		printDash();
		perfMD[3] = (int) dD.getPerformanceMeasure(aM);
		sMD[3] = dD.getCurrentState().toString();
		stepMD[3] = i;
		dD = new VacuumEnvironment(LocationState.Dirty,LocationState.Dirty);
		dD.removeAgent(aM);
		actionTracker = new SimpleActionTracker();
		Arrays.fill(actions, null);
		
		for(i = 0;i < 5;i++)
			temp[i] = (int)Math.random();
		
		printFTable(sMD, stepMD, perfMD, "Model Based Reflex Agent");
		
		cc.addAgent(aT, VacuumEnvironment.LOCATION_A);
		cc.addEnvironmentView((aima.core.agent.EnvironmentView) actionTracker);
		
		//set up table block
		System.out.println("Agent \t\tTable Based Vacuum Agent");
		System.out.println("Initial State:\t" + cc.getCurrentState());
		printDash();
		System.out.println("Steps\t\tPercept\t\t\t\tAction\t\tPerformance\tEnvironment State");
		printDash();
		
		//Clean Clean Table Agent
		for(i = 0;i < 5;i++) {
			
			System.out.print(i+1 + "\t");
			System.out.print(cc.getCurrentState() + "\t");
			cc.step();
			actions[i] = actionTracker.getActions();
			actionPrint(actions[i],i);
			System.out.print(cc.getPerformanceMeasure(aT) + "\t");
			temp[i]= (int)cc.getPerformanceMeasure(aT);
			System.out.print(cc.getCurrentState());
			System.out.println();
			
			if(i>0 && temp[i-1]==temp[i])
				break;
		}
		
		//resets variables for next run
		printDash();
		perfTB[0] = (int) cc.getPerformanceMeasure(aT);
		sTB[0] = cc.getCurrentState().toString();
		stepTB[0] = i;
		cc = new VacuumEnvironment(LocationState.Clean,LocationState.Clean);
		cc.removeAgent(aT);
		actionTracker = new SimpleActionTracker();
		Arrays.fill(actions, null);
		aT = new TableDrivenVacuumAgent();
		
		for(i = 0;i < 5;i++)
			temp[i] = (int)Math.random();
		
		
		cD.addAgent(aT, VacuumEnvironment.LOCATION_A);
		cD.addEnvironmentView((aima.core.agent.EnvironmentView) actionTracker);
		
		//set up table block
		System.out.println("Agent \t\tTable Based Vacuum Agent");
		System.out.println("Initial State:\t" + cD.getCurrentState());
		printDash();
		System.out.println("Steps\t\tPercept\t\t\t\tAction\t\tPerformance\tEnvironment State");
		printDash();
		
		//Clean Dirty Table Agent
		for(i = 0;i < 5;i++) {
			
			System.out.print(i+1 + "\t");
			System.out.print(cD.getCurrentState() + "\t");
			cD.step();
			actions[i] = actionTracker.getActions();
			actionPrint(actions[i],i);
			System.out.print(cD.getPerformanceMeasure(aT) + "\t");
			temp[i]= (int)cD.getPerformanceMeasure(aT);
			System.out.print(cD.getCurrentState());
			System.out.println();
			if(i>0 && temp[i-1]==temp[i])
				break;
			
		}
		
		//resets variables for next run
		printDash();
		perfTB[1] = (int) cD.getPerformanceMeasure(aT);
		sTB[1] = cD.getCurrentState().toString();
		stepTB[1] = i;
		cD = new VacuumEnvironment(LocationState.Clean,LocationState.Dirty);
		cD.removeAgent(aT);
		actionTracker = new SimpleActionTracker();
		Arrays.fill(actions, null);
		aT = new TableDrivenVacuumAgent();
		
		for(i = 0;i < 5;i++)
			temp[i] = (int)Math.random();
		
		dC.addAgent(aT, VacuumEnvironment.LOCATION_A);
		dC.addEnvironmentView((aima.core.agent.EnvironmentView) actionTracker);
		
		//set up table block
		System.out.println("Agent \t\tTable Based Vacuum Agent");
		System.out.println("Initial State:\t" + dC.getCurrentState());
		printDash();
		System.out.println("Steps\t\tPercept\t\t\t\tAction\t\tPerformance\tEnvironment State");
		printDash();
		
		//Clean Dirty Simple Agent
		for(i = 0;i < 5;i++) {
			System.out.print(i+1 + "\t");
			System.out.print(dC.getCurrentState() + "\t");
			dC.step();
			actions[i] = actionTracker.getActions();
			actionPrint(actions[i],i);
			System.out.print(dC.getPerformanceMeasure(aT) + "\t");
			temp[i]= (int)dC.getPerformanceMeasure(aT);
			System.out.print(dC.getCurrentState());
			System.out.println();
			if(i>0 && temp[i-1]==temp[i])
				break;
		}
		
		//resets variables for next run
		printDash();
		perfTB[2] = (int) dC.getPerformanceMeasure(aT);
		sTB[2] = dC.getCurrentState().toString();
		stepTB[2] = i;
		dC = new VacuumEnvironment(LocationState.Dirty,LocationState.Clean);
		dC.removeAgent(aT);
		actionTracker = new SimpleActionTracker();
		Arrays.fill(actions, null);
		aT = new TableDrivenVacuumAgent();

		for(i = 0;i < 5;i++)
			temp[i] = (int)Math.random();
		
		dD.addAgent(aT, VacuumEnvironment.LOCATION_A);
		dD.addEnvironmentView((aima.core.agent.EnvironmentView) actionTracker);
		
		//set up table block
		System.out.println("Agent \t\tTable Based Vacuum Agent");
		System.out.println("Initial State:\t" + dD.getCurrentState());
		printDash();
		System.out.println("Steps\t\tPercept\t\t\t\tAction\t\tPerformance\tEnvironment State");
		printDash();
		
		//Dirty Dirty Simple Agent
		for(i = 0;i < 5;i++) {
			System.out.print(i+1 + "\t");
			System.out.print(dD.getCurrentState() + "\t");
			dD.step();
			actions[i] = actionTracker.getActions();
			actionPrint(actions[i],i);
			System.out.print(dD.getPerformanceMeasure(aT) + "\t");
			temp[i]= (int)dD.getPerformanceMeasure(aT);
			System.out.print(dD.getCurrentState());
			System.out.println();
			if(i>0 && temp[i-1]==temp[i])
				break;
		}
		
		//resets variables for next run
		printDash();
		perfTB[3] = (int) dD.getPerformanceMeasure(aT);
		sTB[3] = dD.getCurrentState().toString();
		stepTB[3] = i;
		
		printFTable(sTB, stepTB, perfTB, "Table Based Vacuum Agent");
		
	}

	//Method for printing the final results table
	static void printFTable(String finalView[], int steps[], int perf[], String type) {
		
		System.out.println("\nSummary of " + type +":");
		printDash();
		System.out.println("InitialState\tSteps\t\tPerformance\tFinal Environment State");
		System.out.println("[Clean, Clean]\t"+steps[0]+ "\t\t" + perf[0]+ "\t\t" + finalView[0]);
		System.out.println("[Clean, Dirty]\t"+steps[1]+ "\t\t" + perf[1]+ "\t\t" + finalView[1]);
		System.out.println("[Dirty, Clean]\t"+steps[2]+ "\t\t" + perf[2]+ "\t\t" + finalView[2]);
		System.out.println("[Dirty, Dirty]\t"+steps[3]+ "\t\t" + perf[3]+ "\t\t" + finalView[3]+ "\n");
		
	}
	
	//Method for priting a dashed line
	static void printDash() {
		
		for(int i = 0;i<98;i++)
			System.out.print("-");
		
		System.out.println();
		
	}
	
	//method that splits getAction into seperate actions
	static void actionPrint(String a, int step) {
		
		String[] sub = new String[5];
		
		if(step == 0) {
			sub[0] = a;
			System.out.print(sub[0] + "\t");
		}
		else {
			sub = a.split(", ");
			System.out.print(sub[step] + "\t");
		}
		
	}
	
}