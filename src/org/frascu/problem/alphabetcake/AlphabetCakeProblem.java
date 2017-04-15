package org.frascu.problem.alphabetcake;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class AlphabetCakeProblem {

	public static void main(String[] args) {
		resolve("input/alphabetcake/A-small-practice.in");
		resolve("input/alphabetcake/A-large-practice.in");
	}

	private static void resolve(String fileName) {
		System.out.println("File: " + fileName);
		try (PrintWriter out = new PrintWriter(fileName + ".out")) {
			List<String> lines = Files.readAllLines(new File(fileName).toPath());

			// Number of test cases
			int t = Integer.parseInt(getLine(lines));

			for (int i = 0; i < t; i++) {

				// Read the dimensions of the cake
				String[] dims = getLine(lines).split(" ");
				int r = Integer.parseInt(dims[0]);
				int c = Integer.parseInt(dims[1]);

				// Initialize
				char[][] cake = new char[r][c];
				char previous = ' ';
				boolean initInterrogative = true;
				List<Coordinate> coordinates = new ArrayList<>();

				for (int r1 = 0; r1 < r; r1++) {
					String row = getLine(lines);
					for (int c1 = 0; c1 < c; c1++) {
						char initial = row.charAt(c1);
						if (initial == '?') {
							if (initInterrogative) {
								cake[r1][c1] = '?';
								coordinates.add(new Coordinate(r1, c1));
								initInterrogative = true;
							} else {
								cake[r1][c1] = previous;
								initInterrogative = false;
							}
						} else {
							cake[r1][c1] = initial;

							// Fill the previous empty pieces
							if (initInterrogative) {
								for (Coordinate coordinate : coordinates) {
									cake[r1][coordinate.getColumn()] = initial;
								}
								coordinates = new ArrayList<>();
							}

							previous = initial;
							initInterrogative = false;
						}
					}

					// If the row is completely empty, copy from previous row
					if (initInterrogative && !coordinates.isEmpty() && r1 > 0 && r1 < r) {
						for (Coordinate coordinate : coordinates) {
							cake[coordinate.getRow()][coordinate.getColumn()] = cake[coordinate.getRow() - 1][coordinate
									.getColumn()];
						}
					}

					// Reinitialize
					coordinates = new ArrayList<>();
					initInterrogative = true;
				}

				// Fill the empty rows
				for (int j = cake.length - 1; j >= 0; j--) {
					if (isInterrogativeRow(j, cake)) {
						for (int c1 = 0; c1 < c; c1++) {
							cake[j][c1] = cake[j + 1][c1];
						}
					}
				}

				print(out, "Case #" + (i + 1) + ":");
				for (int j = 0; j < cake.length; j++) {
					print(out, String.valueOf(cake[j]));
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void print(PrintWriter out, String line) {
		System.out.println(line);
		out.println(line);
	}

	private static boolean isInterrogativeRow(int row, char[][] cake) {
		for (int c1 = 0; c1 < cake[0].length; c1++) {
			if (cake[row][c1] != '?') {
				return false;
			}
		}
		return true;
	}

	private static String getLine(List<String> lines) {
		String line = lines.get(0);
		lines.remove(0);
		return line;
	}
}
