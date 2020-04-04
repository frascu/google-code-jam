import java.util.LinkedList;
import java.util.Scanner;

public class SecondSolution {

	// Problem Link:
	// https://codingcompetitions.withgoogle.com/codejam/round/000000000019fd27/0000000000209a9f
	public static void main(String[] args) {
		try (Scanner scanner = new Scanner(System.in)) {
			int t = scanner.nextInt();
			scanner.nextLine();
			for (int l = 0; l < t; l++) {

				String string = scanner.nextLine();
				char[] charArray = string.toCharArray();

				LinkedList<Integer> output = new LinkedList<>();

				// 0 means the numbers
				// negative number opened parenthesis
				// positive number closed parenthesis
				for (int i = 0; i < charArray.length; i++) {
					int number = charArray[i] - '0';
					if (number > 0) {
						if (!output.isEmpty()) {
							int last = output.getLast();

							if (last > 0) {
								output.removeLast();
							}
							if (number < last || number > last) {
								output.add(last - number);
							}
							output.add(0);
							output.add(number);

						} else {
							output.add(number * -1);
							output.add(0);
							output.add(number);
						}
					} else {
						output.add(0);
					}
				}
				StringBuilder stringBuilder = new StringBuilder();
				int count = 0;
				for (Integer integer : output) {
					if (integer < 0) {
						for (int i = 0; i < Math.abs(integer); i++) {
							stringBuilder.append('(');
						}
					} else if (integer > 0) {
						for (int i = 0; i < integer; i++) {
							stringBuilder.append(')');
						}
					} else {
						stringBuilder.append(charArray[count]);
						count++;
					}
				}
				System.out.println("Case #" + (l + 1) + ": " + stringBuilder.toString());
			}
		}
	}
}
