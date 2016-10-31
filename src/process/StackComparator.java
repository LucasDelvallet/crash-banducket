package process;

import java.util.ArrayList;
import java.util.List;

import entities.Method;
import entities.Stack;
import entities.StackFrame;

public class StackComparator {

	// METHODE 1
	public static double levensteinMethod(Stack stackTest, Stack stack) {
		List<StackFrame> stackFrames = stack.getFrames();
		List<StackFrame> stackFrameTests = stackTest.getFrames();

		int indexTest = stackTest.getMostSignificantStackFrameIndex();
		int index = indexTest;

		if (indexTest >= stackFrames.size()) {
			index = stack.getStackFrameWithSpecificScore(stackFrameTests.get(indexTest).score);
			if (index == -1) {
				index = stack.getMostSignificantStackFrameIndex();
			}
		}

		StackFrame eT = stackFrameTests.get(indexTest);
		StackFrame e = stackFrames.get(index);

		return StackFrameComparator.getDistance(eT, e);
	}

	// METHODE 2
	public static double methodLikelinessMethod(Stack stackTest, Stack stack) {
		List<StackFrame> stackFrames = stack.getFrames();
		List<StackFrame> stackFrameTests = stackTest.getFrames();
		List<Method> methods = new ArrayList<Method>();
		List<Method> methodsTest = new ArrayList<Method>();

		int i = 0;
		for (StackFrame eS : stackFrames) {
			methods.add(new Method(eS.method, i));
			i++;
		}

		i = 0;
		for (StackFrame eS : stackFrameTests) {
			methodsTest.add(new Method(eS.method, i));
			i++;
		}

		Method[] m = methods.stream().toArray(Method[]::new);
		Method[] mT = methodsTest.stream().toArray(Method[]::new);

		if (methods.size() == 0 && methodsTest.size() == 0) {
			return 1;
		}
		if (methods.size() == 0 || methodsTest.size() == 0) {
			return 0;
		}

		return getPercentThatMatch(m, mT);
	}

	public static double getPercentThatMatch(Method[] m1, Method[] m2) {
		int i = 0, n = 0, match = 0;
		while (i < m1.length && n < m2.length) {
			if (m1[i].name.compareTo(m2[n].name) == 0) {
				match++;
			}
			i++;
			n++;
		}

		return ((double) match / ((double) Math.max(m1.length, m2.length)));
	}

	// METHODE 3
	public static double pointClassifiers(Stack stackTest, Stack stack) {
		double score = 0;

		List<StackFrame> stackFrames = stack.getFrames();
		List<StackFrame> stackFrameTests = stackTest.getFrames();

		int decalage = 0;
		
		for(int i = 0; i < stackFrameTests.size(); i++){
			if (stackFrames.get(0).method.equals(stackFrameTests.get(i).method)) {
				decalage = i;
				break;
			}
		}
		
		for (int i = 0; i < stackFrames.size() && (i + decalage) < stackFrameTests.size(); i++) {
			StackFrame sF = stackFrames.get(i);
			StackFrame sFT = stackFrameTests.get(i + decalage);

			if (sF.method.equals(sFT.method)) {
				score += (double) ((double) 5 / (double) (i + 1));
			}
			if (sF.path.equals(sFT.path)) {
				score += (double) ((double) 4 / (double) (i + 1));
			}
			if (sF.addr.equals(sFT.addr)) {
				score += (double) ((double) 3 / (double) (i + 1));
			}			
			if (sF.arguments.equals(sFT.arguments)) {
				score += (double) ((double) 2 / (double) (i + 1));
			}
			if (sF.vars.equals(sFT.vars)) {
				score += (double) ((double) 1 / (double) (i + 1));
			}
		}

		return score;
	}

}
