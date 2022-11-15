package org.frascu.problem.tidynumbers;

public class Number {

	private int digit;
	private int position;
	private long number;

	public Number() {
		super();
	}

	public Number(int digit, int position, char[] digits, int count) {
		super();
		this.digit = digit;
		this.position = position;
		for (int i = position + count; i < digits.length; i++) {
			digits[i] = '0';
		}
		this.number = Long.valueOf(String.valueOf(digits));
	}

	public long getNumber() {
		return number;
	}

	public void setNumber(long number) {
		this.number = number;
	}

	public int getDigit() {
		return digit;
	}

	public void setDigit(int digit) {
		this.digit = digit;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}
}
