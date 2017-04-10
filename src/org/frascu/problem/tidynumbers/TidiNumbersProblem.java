package org.frascu.problem.tidynumbers;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.List;

public class TidiNumbersProblem {

	public static void main(String[] args) {
		resolve("input/tidynumbers/input.txt");
		resolve("input/tidynumbers/B-small-attempt1.in");
		resolve("input/tidynumbers/B-large.in");
	}

	private static void resolve(String fileName) {
		System.out.println("File: " + fileName);
		try (PrintWriter out = new PrintWriter(fileName + ".out")) {
			List<String> lines = Files.readAllLines(new File(fileName).toPath());

			// Number of test cases
			int t = Integer.parseInt(getLine(lines));

			for (int i = 0; i < t; i++) {
				long j = Long.parseLong(getLine(lines));
				Number number = getRepeatedNumber(String.valueOf(j).toCharArray());

				if (isTidy(j)) {
					printOnFile(out, i, j);
				} else {
					while (j >= 0) {
						String numberStringSeq = String.valueOf(j);
						char[] digits = numberStringSeq.toCharArray();

						if (number != null) {
							if (number.getNumber() == j) {
								for (int y = digits.length - 1; y > number.getPosition(); y--) {
									int n = digits[y] - '0';
									if (n == number.getDigit() || n == 0) {
										digits[y] = '9';
									}
								}
								digits[number.getPosition()] = (char) (digits[number.getPosition()] - '1' + '0');
								j = Long.parseLong(String.valueOf(digits));
							}
						}

						if (isTidy(j)) {
							printOnFile(out, i, j);
							break;
						} else {
							number = getRepeatedNumber(digits);
							j--;
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void printOnFile(PrintWriter out, int i, long j) {
		String line = "Case #" + (i + 1) + ": " + j;
		System.out.println(line);
		out.println(line);
	}

	private static Number getRepeatedNumber(char[] digits) {
		int previous = -1;
		int count = 1;
		int duplicatedNumber = -1;
		int position = -1;
		for (int j = digits.length - 1; j >= 0; j--) {
			int n = digits[j] - '0';
			if (n == previous && n != 0) {
				count++;
				duplicatedNumber = n;
				position = j;
			} else {
				if (duplicatedNumber != -1) {
					break;
				}
			}
			previous = n;
		}
		return (duplicatedNumber != -1) ? new Number(duplicatedNumber, position, digits, count) : null;
	}

	private static boolean isTidy(long j) {
		String numberStringTidy = String.valueOf(j);
		if (numberStringTidy.contains("0")) {
			return false;
		}

		char[] digits = numberStringTidy.toCharArray();

		int previous = -1;
		for (char i : digits) {
			int number = i - '0';
			if (number < previous) {
				return false;
			}
			previous = number;
		}

		return true;
	}

	private static String getLine(List<String> lines) {
		String line = lines.get(0);
		lines.remove(0);
		return line;
	}

}
