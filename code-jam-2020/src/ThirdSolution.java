import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class ThirdSolution {

	// Problem link:
	// https://codingcompetitions.withgoogle.com/codejam/round/000000000019fd27/000000000020bdf9
	static class IntPair {
		private final int start;
		private final int end;
		private final int position;
		private char assignedTo;

		IntPair(int start, int end, int position) {
			this.start = start;
			this.end = end;
			this.position = position;
		}

		public int getStart() {
			return start;
		}

		public int getEnd() {
			return end;
		}

		public int getPosition() {
			return position;
		}

		public char getAssignedTo() {
			return assignedTo;
		}

		public void setAssignedTo(char assignedTo) {
			this.assignedTo = assignedTo;
		}

	}

	public static void main(String[] args) {
		try (Scanner scanner = new Scanner(System.in)) {
			int t = scanner.nextInt();
			for (int i = 0; i < t; i++) {
				int n = scanner.nextInt();

				List<IntPair> activities = new ArrayList<>();
				for (int j = 0; j < n; j++) {
					int start = scanner.nextInt();
					int end = scanner.nextInt();
					activities.add(new ThirdSolution.IntPair(start, end, j));
				}

				activities.sort(Comparator.comparing(e -> ((IntPair) e).start).thenComparing(e -> ((IntPair) e).end));

				int precEndJ = 0;
				int precEndC = 0;
				boolean impossible = false;
				for (IntPair activity : activities) {
					if (precEndC <= activity.start) {
						activity.setAssignedTo('C');
						precEndC = activity.end;
					} else if (precEndJ <= activity.start) {
						activity.setAssignedTo('J');
						precEndJ = activity.end;
					} else {
						impossible = true;
						break;
					}
				}

				if (impossible) {
					System.out.println("Case #" + (i + 1) + ": IMPOSSIBLE");
				} else {
					char[] result = new char[n];
					for (IntPair activity : activities) {
						result[activity.getPosition()] = activity.getAssignedTo();
					}

					System.out.println("Case #" + (i + 1) + ": " + new String(result));
				}

			}
		}
	}
}
