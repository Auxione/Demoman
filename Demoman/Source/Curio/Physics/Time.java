package Curio.Physics;

public class Time {
	public int day = 0, hour = 0, minute = 0, second = 0;

	public Time() {
	}

	public Time(Time time) {
		this.day = time.day;
		this.hour = time.hour;
		this.minute = time.minute;
		this.second = time.second;
	}

	public Time(int day, int hour, int minute, int second) {
		this.day = day;
		this.hour = hour;
		this.minute = minute;
		this.second = second;
	}

	public Time add(Time time) {
		this.day += time.day;
		this.hour += time.hour;
		this.minute += time.minute;
		this.second += time.second;
		return this;
	}
	
	public Time set(Time time) {
		this.day = time.day;
		this.hour = time.hour;
		this.minute = time.minute;
		this.second = time.second;
		return this;
	}

	public int getSeconds() {
		return this.second + this.minute * 60 + this.hour * 3600 + this.day * 86400;
	}

	public Time addSecond(int second) {
		if (Math.floor(second / 86400) > 0) {
			this.day += Math.floor(second / 86400);
			second -= Math.floor(second / 86400) * 86400;
		}

		if (Math.floor(second / 3600) > 0) {
			this.hour += Math.floor(second / 3600);
			second -= Math.floor(second / 3600) * 3600;
		}

		if (Math.floor(second / 60) > 0) {
			this.minute += Math.floor(second / 60);
			second -= Math.floor(second / 60) * 60;
		}

		this.second += second;
		return this;
	}

	public String getformat() {
		return day + ":" + hour + ":" + minute + ":" + second;
	}
}
