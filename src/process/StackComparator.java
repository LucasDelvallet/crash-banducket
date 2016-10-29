package process;

import java.util.ArrayList;
import java.util.List;

import entities.Method;
import entities.Stack;
import entities.StackFrame;

public class StackComparator {

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
}
