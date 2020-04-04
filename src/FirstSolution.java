import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class FirstSolution {

	// Problem link:
	// https://codingcompetitions.withgoogle.com/codejam/round/000000000019fd27/000000000020993c
	public static void main(String[] args) {
		try (Scanner scanner = new Scanner(System.in)) {
			int t = scanner.nextInt();
			for (int i = 0; i < t; i++) {
				int n = scanner.nextInt();

				int[][] matrix = new int[n][n];
				int k = 0;
				int r = 0;
				int c = 0;
				// start matrix
				for (int j = 0; j < n; j++) {

					Set<Integer> row = new HashSet<>();
					// start row
					for (int jj = 0; jj < n; jj++) {
						int elem = scanner.nextInt();
						row.add(elem);
						matrix[j][jj] = elem;
					}
					// end row

					if (row.size() < n) {
						r++;
					}

					k += matrix[j][j];

				}
				// end matrix

				for (int j = 0; j < n; j++) {
					Set<Integer> col = new HashSet<>();
					for (int jj = 0; jj < n; jj++) {
						col.add(matrix[jj][j]);
					}
					if (col.size() < n) {
						c++;
					}
				}
				System.out.println("Case #" + (i + 1) + ": " + k + " " + r + " " + c);
			}
		}
	}
}
