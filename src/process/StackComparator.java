package process;

import java.util.ArrayList;
import java.util.List;

import entities.Method;
import entities.ScoreTestCollector;
import entities.Stack;
import entities.StackFrame;

public class StackComparator {

	// METHODE 1
	public static double levensteinMethod(Stack stackTest, Stack stack) {
		List<StackFrame> stackFrames = stack.getFrames();
		List<StackFrame> stackFrameTests = stackTest.getFrames();

		StackFrame eT = stackFrameTests.get(0);
		StackFrame e = stackFrames.get(0);

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

		for (int i = 0; i < stackFrames.size() && (i) < stackFrameTests.size(); i++) {
			StackFrame sF = stackFrames.get(i);
			StackFrame sFT = stackFrameTests.get(i);

			// if (!(sF.method.equals("??") || sFT.method.equals("??")) &&
			// sF.method.equals(sFT.method)) {
			// if(sF.path.substring(sF.path.lastIndexOf("/")+1).equals(sFT.path.substring(sFT.path.lastIndexOf("/")+1)))
			// {
			// score += (double) ((double) 4 / (double) (i + 1));
			// }
			
			if (sF.method.equals(sFT.method)) {
				score += (double) ((double) 5 / (double) (i + 1));
			}
			if (sF.path.equals(sFT.path)) {
				score += (double) ((double) 4 / (double) (i + 1));
			}
			if (sF.addr.equals(sFT.addr)) {
				score += (double) ((double) 3 / (double) (i + 1));
			}
			if (!(sF.arguments.equals("()") || sFT.arguments.equals("()")) && sF.arguments.equals(sFT.arguments)) {
				score += (double) ((double) 2 / (double) (i + 1));
			}
			if (!(sF.vars.isEmpty() || sFT.vars.isEmpty()) && sF.vars.equals(sFT.vars)) {
				score += (double) ((double) 1 / (double) (i + 1));
			}
			
//			if(!(sF.arguments.equals("()") || sFT.arguments.equals("()")) && !sF.arguments.isEmpty() && !sFT.arguments.isEmpty()) {
//				if(!sF.arguments.equals(sFT.arguments)) {
//					double v = StringSimilarity.similarity(sF.arguments, sFT.arguments);
//						score += (double) ((double) 2*v / (double) (i + 1));
//				} else {
//					score += (double) ((double) 2 / (double) (i + 1));
//				}
//			}
//			
//			if(!sF.vars.isEmpty() && !sFT.vars.isEmpty()) {
//				if(!sF.vars.equals(sFT.vars)) {
//					double v = StringSimilarity.similarity(sF.vars, sFT.vars);
//						score += (double) ((double) v / (double) (i + 1));
//				} else {
//					score += (double) ((double) 1 / (double) (i + 1));
//				}
//			}
		}

		return score;
	}
	
	
	// METHODE 4
	
	public static double pointClassifiersWithOffset(Stack stackTest, Stack stack) {

		double bestScore = 0;
		int bestScoreIndex = 0;
		int bestScoreIndexTests = 0;
		
		List<StackFrame> stackFrames = stack.getFrames();
		List<StackFrame> stackFrameTests = stackTest.getFrames();

		for (int i = 0; i < stackFrameTests.size(); i++) {
			StackFrame sFT = stackFrameTests.get(i);
			for (int c = 0; c < stackFrames.size(); c++) {
				double score = 0;
				StackFrame sF = stackFrames.get(c);
				
				if (sF.method.equals(sFT.method)) {
					score += (double) ((double) 5);
				}
				if (sF.path.equals(sFT.path)) {
					score += (double) ((double) 4);
				}
				if (sF.addr.equals(sFT.addr)) {
					score += (double) ((double) 3);
				}
				if (!(sF.arguments.equals("()") || sFT.arguments.equals("()")) && sF.arguments.equals(sFT.arguments)) {
					score += (double) ((double) 2);
				}
				if (!(sF.vars.isEmpty() || sFT.vars.isEmpty()) && sF.vars.equals(sFT.vars)) {
					score += (double) ((double) 1);
				}
				
				
				if(score > bestScore){
					bestScore = score;
					bestScoreIndex = c;
					bestScoreIndexTests = i;
				}
			}
		}
		
		double score = 0;
		
		int offset = Math.min(bestScoreIndex, bestScoreIndexTests);
		
		stackFrames = stackFrames.subList(bestScoreIndex - offset, stackFrames.size());
		stackFrameTests = stackFrameTests.subList(bestScoreIndexTests - offset, stackFrameTests.size());

		for (int i = 0; i < stackFrames.size() && (i) < stackFrameTests.size(); i++) {
			StackFrame sF = stackFrames.get(i);
			StackFrame sFT = stackFrameTests.get(i);

			if (sF.method.equals(sFT.method)) {
				score += (double) ((double) 5 / (double) (i + 1));
			}
			if (sF.path.equals(sFT.path)) {
				score += (double) ((double) 4 / (double) (i + 1));
			}
			if (sF.addr.equals(sFT.addr)) {
				score += (double) ((double) 3 / (double) (i + 1));
			}
			if (!(sF.arguments.equals("()") || sFT.arguments.equals("()")) && sF.arguments.equals(sFT.arguments)) {
				score += (double) ((double) 2 / (double) (i + 1));
			}
			if (!(sF.vars.isEmpty() || sFT.vars.isEmpty()) && sF.vars.equals(sFT.vars)) {
				score += (double) ((double) 1 / (double) (i + 1));
			}
		}

		return score;
	}


	public static int compareAddr(String addr1, String addr2) {
		int score = 0;
		if (addr1.startsWith("0x") && addr2.startsWith("0x")) {
			addr1 = addr1.substring(2).replaceFirst("^0+(?!$)", "");
			addr2 = addr2.substring(2).replaceFirst("^0+(?!$)", "");

			for (int i = 0; i < Math.min(addr1.length(), addr2.length()); i++) {
				if (addr1.charAt(i) == addr2.charAt(i)) {
					score++;
				} else {
					break;
				}
			}
		}

		return score;
	}

	public static void pointClassifiersTest(Stack stackTest, Stack stack) {
		List<StackFrame> stackFrames = stack.getFrames();
		List<StackFrame> stackFrameTests = stackTest.getFrames();

		for (int i = 0; i < stackFrames.size() && (i) < stackFrameTests.size(); i++) {
			StackFrame sF = stackFrames.get(i);
			StackFrame sFT = stackFrameTests.get(i);

			if (sF.method.equals(sFT.method)) {
				ScoreTestCollector.mScr++;
			}
			if (sF.path.equals(sFT.path)) {
				ScoreTestCollector.pScr++;
			}
			// if
			// (sF.path.substring(sF.path.lastIndexOf("/")+1).equals(sFT.path.substring(sFT.path.lastIndexOf("/")+1)))
			// {
			// score += (double) ((double) 4 / (double) (i + 1));
			// }
			if (sF.addr.equals(sFT.addr)) {
				ScoreTestCollector.adScr++;
			}
			if (!(sF.arguments.equals("()") || sFT.arguments.equals("()")) && sF.arguments.equals(sFT.arguments)) {
				ScoreTestCollector.arScr++;
			}
			if (!(sF.vars.isEmpty() || sFT.vars.isEmpty()) && sF.vars.equals(sFT.vars)) {
				ScoreTestCollector.vaScr++;
			}
		}

	}

}
