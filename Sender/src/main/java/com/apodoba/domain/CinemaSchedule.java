package com.apodoba.domain;

import java.util.List;

import javax.jms.JMSException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.JmsException;
import org.springframework.stereotype.Component;

import com.apodoba.jms.MessageSender;

@Component
public class CinemaSchedule {
	
	@Autowired
	MessageSender messageSender;
	
	private List<FilmSchedule> allFilmSchedules;

	public List<FilmSchedule> getAllFilmSchedules() {
		try {
			System.out.println(messageSender.sendScheduleMessage().size());
//			messageSender.sendScheduleMessage();
		} catch (JmsException e) {
			e.printStackTrace();
		} catch (JMSException e) {
			e.printStackTrace();
		}
		return allFilmSchedules;
	}

	public void setAllFilmSchedules(List<FilmSchedule> allFilmSchedules) {
		this.allFilmSchedules = allFilmSchedules;
	}
}
