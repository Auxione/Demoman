package Curio.Physics;

public class Time {
	public int day = 0, hour = 0, minute = 0, second = 0, fixedMillis = 0;

	public Time() {
	}

	public Time(Time time) {
		this.day = time.day;
		this.hour = time.hour;
		this.minute = time.minute;
		this.second = time.second;
		this.fixedMillis = time.fixedMillis;
	}

	public Time(int day, int hour, int minute, int second, int milliSeconds) {
		this.day = day;
		this.hour = hour;
		this.minute = minute;
		this.second = second;
		this.fixedMillis = milliSeconds;
	}

	public Time add(Time time) {
		this.day += time.day;
		this.hour += time.hour;
		this.minute += time.minute;
		this.second += time.second;
		this.fixedMillis += time.fixedMillis;
		return this;
	}

	public Time set(Time time) {
		this.day = time.day;
		this.hour = time.hour;
		this.minute = time.minute;
		this.second = time.second;
		this.fixedMillis = time.fixedMillis;
		return this;
	}

	public int getSeconds() {
		return this.second + this.minute * 60 + this.hour * 3600 + this.day * 86400;
	}

	public int getMillis() {
		return this.fixedMillis + this.second * 1000 + this.minute * 60 * 1000 + this.hour * 3600 * 1000
				+ this.day * 86400 * 1000;
	}

	public Time addSecond(int addSecond) {
		this.day = addSecond / 86400;

		this.hour = addSecond / 3600;
		this.hour = this.hour % 24;

		this.minute = addSecond / 60;
		this.minute = this.minute % 60;

		this.second = addSecond % 60;

		this.second += second;
		return this;
	}

	public Time addMillisSecond(int addMillisSecond) {
		this.day = addMillisSecond / 86400000;

		this.hour = addMillisSecond / 3600000;
		this.hour = this.hour % 24;

		this.minute = addMillisSecond / 60000;
		this.minute = this.minute % 60;

		this.second = addMillisSecond % 60000;
		this.second = this.second % 60;
	
		this.fixedMillis += addMillisSecond;
		return this;
	}

	public String getformat() {
		return day + ":" + hour + ":" + minute + ":" + second + " - " + fixedMillis;
	}
}
