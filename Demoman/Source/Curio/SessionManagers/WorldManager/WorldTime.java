package Curio.SessionManagers.WorldManager;

import Curio.Physics.Time;

public class WorldTime {
	private Time time = new Time();
	public float newRate;

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

	public void updateStart(float systemMillis) {
		advanceTime(systemMillis);
		if (systemMillis > currentTime) {
			setday();
			currentTime = systemMillis + timeAdvanceRate;
		}
	}

	public void setTimeAdvanceRate(int rate) {
		this.newRate = rate;
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

	private void advanceTime(float deltaTime) {
		this.time.fixedMillis += (int) deltaTime;

		if (this.time.fixedMillis >= 1000) {
			this.time.fixedMillis = 0;
			this.time.second += 1;
			this.secondsTick = true;
		}

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
		this.time.add(new Time(day, hour, minute, second,0));
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
