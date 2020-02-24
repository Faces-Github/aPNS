package com.pns.models;

public class SMSModel {
	String  region,subCounty,facilityName,fmlCode,	counsellorCode, counsellorName, programArea,serviceAreaCode,serviceAreaName,phoneNumber,date;
	int indexIdentified,indexScreened,contactListed,contactEligibleTesting,clientstested,clientsTestedPositive,clientLinked;

	public String getRegion() {
		return region;
		
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getSubCounty() {
		return subCounty;
	}

	public void setSubCounty(String subCounty) {
		this.subCounty = subCounty;
	}

	public String getFacilityName() {
		return facilityName;
	}

	public void setFacilityName(String facilityName) {
		this.facilityName = facilityName;
	}

	public String getFmlCode() {
		return fmlCode;
	}

	public void setFmlCode(String fmlCode) {
		this.fmlCode = fmlCode;
	}

	public String getCounsellorCode() {
		return counsellorCode;
	}

	public void setCounsellorCode(String counsellorCode) {
		this.counsellorCode = counsellorCode;
	}

	public String getCounsellorName() {
		return counsellorName;
	}

	public void setCounsellorName(String counsellorName) {
		this.counsellorName = counsellorName;
	}

	public String getProgramArea() {
		return programArea;
	}

	public void setProgramArea(String programArea) {
		this.programArea = programArea;
	}

	public String getServiceAreaCode() {
		return serviceAreaCode;
	}

	public void setServiceAreaCode(String serviceAreaCode) {
		this.serviceAreaCode = serviceAreaCode;
	}

	public String getServiceAreaName() {
		return serviceAreaName;
	}

	public void setServiceAreaName(String serviceAreaName) {
		this.serviceAreaName = serviceAreaName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	

	public int getIndexIdentified() {
		return indexIdentified;
	}

	public void setIndexIdentified(int indexIdentified) {
		this.indexIdentified = indexIdentified;
	}

	public int getIndexScreened() {
		return indexScreened;
	}

	public void setIndexScreened(int indexScreened) {
		indexScreened = indexScreened;
	}

	public int getContactListed() {
		return contactListed;
	}

	public void setContactListed(int contactListed) {
		this.contactListed = contactListed;
	}

	public int getContactEligibleTesting() {
		return contactEligibleTesting;
	}

	public void setContactEligibleTesting(int contactEligibleTesting) {
		this.contactEligibleTesting = contactEligibleTesting;
	}

	public int getClientstested() {
		return clientstested;
	}

	public void setClientstested(int clientstested) {
		this.clientstested = clientstested;
	}

	public int getClientsTestedPositive() {
		return clientsTestedPositive;
	}

	public void setClientsTestedPositive(int clientsTestedPositive) {
		this.clientsTestedPositive = clientsTestedPositive;
	}

	public int getClientLinked() {
		return clientLinked;
	}

	public void setClientLinked(int clientLinked) {
		this.clientLinked = clientLinked;
	}

	@Override
	public String toString() {
		return "SMSModel [region=" + region + ", subCounty=" + subCounty + ", facilityName=" + facilityName
				+ ", fmlCode=" + fmlCode + ", counsellorCode=" + counsellorCode + ", counsellorName=" + counsellorName
				+ ", programArea=" + programArea + ", serviceAreaCode=" + serviceAreaCode + ", serviceAreaName="
				+ serviceAreaName + ", phoneNumber=" + phoneNumber + ", date=" + date + ", indexIdentified="
				+ indexIdentified + ", IndexScreened=" + indexScreened + ", contactListed=" + contactListed
				+ ", contactEligibleTesting=" + contactEligibleTesting + ", clientstested=" + clientstested
				+ ", clientsTestedPositive=" + clientsTestedPositive + ", clientLinked=" + clientLinked + "]";
	}

	
}


