package Curio.Physics;

public class WorldTime {
	private Time time = new Time();
	public float fixedMillis;

	private boolean dayTick = false;
	private boolean hourTick = false;
	private boolean minutesTick = false;
	private boolean secondsTick = false;

	private boolean isNight;
	private boolean isDaytime;
	private boolean isSunrise;
	private boolean isSunset;

	private int timeAdvanceRate = 1000;
	private float currentTime = 0;

	private int nightStartTime = 18;
	private int nightEndTime = 6;

	public WorldTime(int timeAdvanceRate) {
		this.timeAdvanceRate = timeAdvanceRate;
	}

	public void updateStart(float millis) {
		if (millis > currentTime) {
			advanceTime();
			setday();
			currentTime = millis + timeAdvanceRate;
		}
	}

	public void setTimeAdvanceRate(int rate) {
		this.fixedMillis = rate;
	}

	public void updateEnd() {
		this.dayTick = false;
		this.hourTick = false;
		this.minutesTick = false;
		this.secondsTick = false;
	}

	private void setday() {
		this.isNight = (this.time.hour > nightStartTime || this.time.hour < nightEndTime);
		this.isDaytime = !(this.time.hour > nightStartTime || this.time.hour < nightEndTime);
		this.isSunset = (this.time.hour == nightStartTime);
		this.isSunrise = (this.time.hour == nightEndTime);
	}

	private void advanceTime() {
		this.time.second += 1;
		this.secondsTick = true;

		if (this.time.second == 60) {
			this.time.second = 0;
			this.time.minute += 1;
			this.minutesTick = true;
		}

		if (this.time.minute == 60) {
			this.time.minute = 0;
			this.time.hour += 1;
			this.hourTick = true;
		}

		if (this.time.hour == 24) {
			this.time.hour = 0;
			this.time.day += 1;
			this.dayTick = true;
		}
	}

	public void setWorldTime(int day, int hour, int minute, int second) {
		this.time.add(new Time(day, hour, minute, second));
	}

	public boolean isNight() {
		return isNight;
	}

	public boolean isDaytime() {
		return isDaytime;
	}

	public boolean isSunrise() {
		return isSunrise;
	}

	public boolean isSunset() {
		return isSunset;
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

	public boolean getSecondsTick() {
		return secondsTick;
	}

	public Time getTime() {
		return this.time;
	}
}
