package unitTest;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.apache.commons.lang3.StringUtils;

/**
 * Sampleクラス
 * JUnitの勉強のため作ったクラス。
 *
 * 体重と身長より体の状態を計算できる。
 * height単位(cm)
 * weight単位(kg)
 */
public class UnitSample {

	private String firstName;

	private String lastName;

	private double height;

	private double weight;

	public UnitSample() {
		super();
	}

	public UnitSample(String firstName, String lastName, double height, double weight) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.height = height;
		this.weight = weight;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	/**
	 * setされているfirstNameとlastNameを使用しFullNameを返します。
	 * ・両方にデータがない場合は、空文字を返します。
	 * ・片方にデータがない場合は、データが存在するものしか返しません。
	 * ・両方にデータがある場合は、半角スペースをはさみデータを返します。
	 * @return
	 */
	public String getFullName() {
		if (StringUtils.isEmpty(this.firstName) && StringUtils.isEmpty(lastName)) {
			return "";
		} else if (StringUtils.isEmpty(firstName)) {
			return this.lastName;
		} else if (StringUtils.isEmpty(this.lastName)) {
			return this.firstName;
		} else {
			return this.firstName + " " + this.lastName;
		}
	}

	/**
	 * weightとheightからBMIの値を返します。(少数第1位で出力)
	 *
	 * @throws ArithmeticException this.height が0の時
	 * @return BMIの値
	 */
	public double getBMI() {
		try {
			BigDecimal bWeight = BigDecimal.valueOf(this.weight);
			BigDecimal powHeight = BigDecimal.valueOf(Math.pow(this.height / 100, 2));
			BigDecimal bmi = bWeight.divide(powHeight, 2, RoundingMode.HALF_UP);
			return bmi.doubleValue();
		} catch (ArithmeticException a) {
			throw new ArithmeticException();
		}
	}

	/**
	 * BMIの値から肥満度を返します。
	 * 0の場合は体重か身長が入力されていないのでそのことを返します。
	 *
	 * @return 肥満度
	 */
	public String getDegreeOfObesity() {
		double BMI = getBMI();
		if (18.5 > BMI) {
			return "痩せ型";
		} else if (25 > BMI) {
			return "普通体重";
		} else if (30 > BMI) {
			return "肥満度(1度)";
		} else if (35 > BMI) {
			return "肥満度(2度)";
		} else if (40 > BMI) {
			return "肥満度(3度)";
		} else {
			return "肥満度(4度)";
		}
	}

}
