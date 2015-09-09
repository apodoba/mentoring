package com.apodoba.domain;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.apodoba.anticorruption.AntiCorruptionLayer;
import com.apodoba.anticorruption.CinemaScheduleSortByTime;
import com.apodoba.exception.CinemaNotAvailable;

@Component
public class CinemaSchedule {
	
	@Autowired
	AntiCorruptionLayer antiCorruptionLayer;
	
	private List<FilmSchedule> allFilmSchedules;

	public List<FilmSchedule> getAllFilmSchedules() throws CinemaNotAvailable {
		allFilmSchedules = antiCorruptionLayer.getCinemaSchedule();
		if(allFilmSchedules.isEmpty()){
			throw new CinemaNotAvailable();
		}
		
		Collections.sort(allFilmSchedules, new CinemaScheduleSortByTime());
		return allFilmSchedules;
	}

	public void setAllFilmSchedules(List<FilmSchedule> allFilmSchedules) {
		this.allFilmSchedules = allFilmSchedules;
	}
}
