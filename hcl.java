package com.hcl.clock;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DigitalToBerlin implements BerlinClockRowInterface {

	private static final String RED = "R";
	private static final String YELLOW = "Y";

	private static final Integer FIRST_ROW_MAX_LIMIT = 4;
	private static final Integer SECOND_ROW_MAX_LIMIT = 4;
	private static final Integer THIRD_ROW_MAX_LIMIT = 11;
	private static final Integer FOURTH_ROW_MAX_LIMIT = 4;

	private static final Integer CONST_VALUE = 5;
	private static final Integer TOTAL_SIZE = 24;
	private static final Integer DEFAULT_VALUE = 0;
	private static final int[] rows = { 4, 4, 11, 4 };

	private static final Map<String, Integer> clockMap = new HashMap<>();
	private static final List<String> zeroList = new ArrayList<>();

	// public static final List<String> clockList = new
	// ArrayList(Arrays.asList("O));

	public static void findHours(LocalTime localTime) {

		LocalTime lt = localTime.now();
		int hours = lt.getHour();

		int fiveHoursBox = hours / CONST_VALUE;
		int oneHourBox = hours % CONST_VALUE;

		clockMap.put("fiveHoursBox", fiveHoursBox);
		clockMap.put("oneHourBox", oneHourBox);
	}

	public static void findMinutes(LocalTime localTime) {

		LocalTime lt = localTime.now();
		int minutes = lt.getMinute();

		int fiveMinutesRedYellowBox = minutes / CONST_VALUE;
		int oneMinuteYellowBox = minutes % CONST_VALUE;

		clockMap.put("fiveMinutesRedYellowBox", fiveMinutesRedYellowBox);
		clockMap.put("oneMinuteYellowBox", oneMinuteYellowBox);
		/*
		 * System.out.println(createDefaultList());
		 * System.out.println(clockMap);
		 */
	}

	public static int findSeconds(LocalTime localTime) {

		LocalTime lt = localTime.now();
		int seconds = lt.getSecond();

		if (seconds % 2 == 0)
			return 1;
		else
			return 0;
	}

	private static List<String> createDefaultList() {
		for (int i = 0; i < TOTAL_SIZE; i++) {
			zeroList.add(DEFAULT_VALUE.toString());
		}
		return zeroList;
	}

	public static void main(String[] args) {
		displayClock(LocalTime.now());
	}

	public static void displayClock(LocalTime localTime) {

		findHours(localTime);
		findMinutes(localTime);
		System.out.println(findSeconds(localTime));
		System.out.println(new DigitalToBerlin().firstBerlinClockRow());
		System.out.println(new DigitalToBerlin().secondBerlinClockRow());
		System.out.println(new DigitalToBerlin().thirdBerlinClockRow());
		System.out.println(new DigitalToBerlin().fourthBerlinClockRow());
	}

	@Override
	public List<String> firstBerlinClockRow() {

		int hours = 0;
		List<String> firstRowList = new ArrayList<>();
		if (clockMap.containsKey("fiveHoursBox")) {
			hours = clockMap.get("fiveHoursBox");
		}

		for (int i = 0; i < FIRST_ROW_MAX_LIMIT; i++) {
			if (hours > 0)
				firstRowList.add(RED);
			else
				firstRowList.add(DEFAULT_VALUE.toString());
			hours--;
		}
		return firstRowList;
	}

	@Override
	public List<String> secondBerlinClockRow() {
		int hours = 0;
		List<String> secondRowList = new ArrayList<>();
		if (clockMap.containsKey("oneHourBox")) {
			hours = clockMap.get("oneHourBox");
		}

		for (int i = 0; i < SECOND_ROW_MAX_LIMIT; i++) {
			if (hours > 0)
				secondRowList.add(RED);
			else
				secondRowList.add(DEFAULT_VALUE.toString());
			hours--;
		}
		return secondRowList;
	}

	@Override
	public List<String> thirdBerlinClockRow() {
		int minutes = 0;
		List<String> thirdRowList = new ArrayList<>();
		if (clockMap.containsKey("fiveMinutesRedYellowBox")) {
			minutes = clockMap.get("fiveMinutesRedYellowBox");
		}
		for (int i = 1; i <= THIRD_ROW_MAX_LIMIT; i++) {

			if (i % 3 == 0 && minutes > 0)
				thirdRowList.add(RED);
			else if (i % 3 != 0 && minutes > 0)
				thirdRowList.add(YELLOW);
			else
				thirdRowList.add(DEFAULT_VALUE.toString());
			minutes--;
		}
		return thirdRowList;
	}

	@Override
	public List<String> fourthBerlinClockRow() {
		int minutes = 0;
		List<String> fourthRowList = new ArrayList<>();
		if (clockMap.containsKey("oneMinuteYellowBox")) {
			minutes = clockMap.get("oneMinuteYellowBox");
		}

		for (int i = 0; i < FOURTH_ROW_MAX_LIMIT; i++) {
			if (minutes > 0)
				fourthRowList.add(YELLOW);
			else
				fourthRowList.add(DEFAULT_VALUE.toString());
			minutes--;
		}
		return fourthRowList;
	}
}
