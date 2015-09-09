package com.apodoba.anticorruption;

import java.util.Comparator;

import com.apodoba.domain.FilmSchedule;

public class CinemaScheduleSortByTime implements Comparator<FilmSchedule>{

	@Override
	public int compare(FilmSchedule o1, FilmSchedule o2) {
		return o1.getTime().compareTo(o2.getTime());
	}

}
