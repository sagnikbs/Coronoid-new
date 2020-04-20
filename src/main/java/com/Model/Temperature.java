package com.Model;

import java.io.File;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

@Component
@Entity
public class Temperature {
	
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
    private int id;
	private String loginId;
	private String temp;
	private String lat;
	private String lng;
	private String datetime;
	private String address;
	private String markerno;
	private String name;
	private String phone;
	private String fever;
	private String cough;
	private String tiredness;
	private String breathingProb;
	private String symptoms;
	private String userStatus;
	private String futureDate;
	private String cluster;
	private String startPosition;
	private String currentPosition;
	private File tracker;
	private String travelWithin10Days;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getTemp() {
		return temp;
	}
	public void setTemp(String temp) {
		this.temp = temp;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	public String getDatetime() {
		return datetime;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMarkerno() {
		return markerno;
	}
	public void setMarkerno(String markerno) {
		this.markerno = markerno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getFever() {
		return fever;
	}
	public void setFever(String fever) {
		this.fever = fever;
	}
	public String getCough() {
		return cough;
	}
	public void setCough(String cough) {
		this.cough = cough;
	}
	public String getTiredness() {
		return tiredness;
	}
	public void setTiredness(String tiredness) {
		this.tiredness = tiredness;
	}
	public String getBreathingProb() {
		return breathingProb;
	}
	public void setBreathingProb(String breathingProb) {
		this.breathingProb = breathingProb;
	}
	public String getSymptoms() {
		return symptoms;
	}
	public void setSymptoms(String symptoms) {
		this.symptoms = symptoms;
	}
	public String getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}
	public String getFutureDate() {
		return futureDate;
	}
	public void setFutureDate(String futureDate) {
		this.futureDate = futureDate;
	}
	public String getCluster() {
		return cluster;
	}
	public void setCluster(String cluster) {
		this.cluster = cluster;
	}
	public String getStartPosition() {
		return startPosition;
	}
	public void setStartPosition(String startPosition) {
		this.startPosition = startPosition;
	}
	public String getCurrentPosition() {
		return currentPosition;
	}
	public void setCurrentPosition(String currentPosition) {
		this.currentPosition = currentPosition;
	}
	public File getTracker() {
		return tracker;
	}
	public void setTracker(File tracker) {
		this.tracker = tracker;
	}
	public String getTravelWithin10Days() {
		return travelWithin10Days;
	}
	public void setTravelWithin10Days(String travelWithin10Days) {
		this.travelWithin10Days = travelWithin10Days;
	}
	
	
	
	
}
