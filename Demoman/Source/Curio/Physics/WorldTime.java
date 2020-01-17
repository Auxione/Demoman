package Curio.Physics;

public class WorldTime {
	private int day = 0;
	private int hour = 0;
	private int minutes = 0;

	private boolean dayTick = false;
	private boolean hourTick = false;
	private boolean minutesTick = false;

	private boolean isNight;
	private boolean isDaytime;
	private boolean isSunrise;
	private boolean isSunset;

	private int timeAdvanceRate = 1000;
	private int timeCurrentGoal = 0;

	private int nightStartTime = 18;
	private int nightEndTime = 6;

	public WorldTime(int timeAdvanceRate) {
		this.timeAdvanceRate = timeAdvanceRate;
	}

	public void updateStart(int millis) {
		if (millis > timeCurrentGoal) {
			advanceTime();
			setday();
			timeCurrentGoal = millis + timeAdvanceRate;
		}
	}

	public void updateEnd() {
		this.dayTick = false;
		this.hourTick = false;
		this.minutesTick = false;
	}

	private void setday() {
		this.isNight = (hour > nightStartTime || hour < nightEndTime);
		this.isDaytime = !(hour > nightStartTime || hour < nightEndTime);
		this.isSunset = (hour == nightStartTime);
		this.isSunrise = (hour == nightEndTime);
	}

	private void advanceTime() {
		this.minutes += 1;
		this.minutesTick = true;

		if (minutes == 60) {
			this.minutes = 0;
			this.hour += 1;
			this.hourTick = true;
		}

		if (hour == 24) {
			this.hour = 0;
			this.day += 1;
			this.dayTick = true;
		}
	}

	public void setWorldTime(int day, int hour, int minutes) {
		if (hour >= 0 && hour <= 24) {
			if (minutes >= 0 && minutes <= 60) {
				this.day = day;
				this.hour = hour;
				this.minutes = minutes;
			}
		}
	}

	public boolean isNight() {
		return isNight;
	}

	public boolean isDaytime() {
		return isDaytime;
	}

	public boolean getDayTick() {
		return dayTick;
	}

	public boolean getHourTick() {
		return hourTick;
	}

	public boolean getMinutesTick() {
		return minutesTick;
	}

	public int getDay() {
		return day;
	}

	public int getHour() {
		return hour;
	}

	public int getMinutes() {
		return minutes;
	}

	public boolean isSunrise() {
		return isSunrise;
	}

	public boolean isSunset() {
		return isSunset;
	}
}
