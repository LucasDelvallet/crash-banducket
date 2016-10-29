package process;

import java.util.ArrayList;
import java.util.List;

import entities.Method;
import entities.Stack;
import entities.StackFrame;

public class StackComparator {

	public static double levensteinMethod(Stack stackTest, Stack stack) {
		List<StackFrame> stackElements = stack.getElements();
		List<StackFrame> stackElementTests = stackTest.getElements();

		int indexTest = stackTest.getMostSignificantStackElementIndex();
		int index = indexTest;

		if (indexTest >= stackElements.size()) {
			index = stack.getStackElementWithSpecificScore(stackElementTests.get(indexTest).score);
			if (index == -1) {
				index = stack.getMostSignificantStackElementIndex();
			}
		}

		StackFrame eT = stackElementTests.get(indexTest);
		StackFrame e = stackElements.get(index);

		return StackElementComparator.getDistance(eT, e);
	}

	public static double methodLikelinessMethod(Stack stackTest, Stack stack) {
		List<StackFrame> stackElements = stack.getElements();
		List<StackFrame> stackElementTests = stackTest.getElements();
		List<Method> methods = new ArrayList<Method>();
		List<Method> methodsTest = new ArrayList<Method>();

		int i = 0;
		for (StackFrame eS : stackElements) {
			methods.add(new Method(eS.method, i));
			i++;
		}

		i = 0;
		for (StackFrame eS : stackElementTests) {
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
