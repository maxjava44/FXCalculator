package application;

public class Calculator {

	private String number1 = "";
	private String number2 = "";
	private double result = 0.0;

	public Calculator() {
		number1 = "";
		number2 = "";
		result = 0.0;

	}

	void addnumber(String var, int index) {
		if (index == 1) {
			number1 = number1 + var;
		} else {
			number2 = number2 + var;
		}
	}

	void setnumber(String var, int index) {
		if (index == 1) {
			number1 = var;
		} else {
			number2 = var;
		}
	}

	double calculate(int operation) {
		try {
			switch (operation) {
			case 1:
				result = Double.parseDouble(number1) + Double.parseDouble(number2);
				break;
			case 2:
				result = Double.parseDouble(number1) - Double.parseDouble(number2);
				break;
			case 3:
				result = Double.parseDouble(number1) * Double.parseDouble(number2);
				break;
			case 4:
				result = Double.parseDouble(number1) / Double.parseDouble(number2);
				break;
			}
		} catch (Exception e) {
			System.out.println("Error");
			e.printStackTrace();
			result = 0;
		}
		return result;
	}

	void reset() {
		number1 = "";
		number2 = "";
		result = 0.0;
	}
}
