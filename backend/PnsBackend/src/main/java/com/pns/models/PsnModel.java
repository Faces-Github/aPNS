package com.pns.models;

public class PsnModel {
	private int patientId;
	private String identifier;
	private String patientName;
	private String indexCCCNumber;
	private String cccNumber;
	private String  clientType;
	private String consentedProvideContacts;
	private String declinedProvideContacts;
	private String  maritalStatus;
	private String relationshipIndexClient;
	private String occupation;
	private String livingWihIndex;
	private String physicalHurt;
	private String threatenedToHurt;
	private String sexuallyUncomfortable;
	private String testingEligibility,hivStatus; 
	private String  pnsApproach;
	private String contactType;
	private String tracingAttempt;
	private String tracingOutcome;
	private String consentedTesting;
	private String dateBookedForTesting;
	private String consentedTesting2;
	private String declineReason;
	private String testingOutcome;
	private String linkToCare;
	private String dateLinked;
	private String facilityLinked;
	private String remarks;
	
	
	public int getPatientId() {
		return patientId;
	}
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public String getIndexCCCNumber() {
		return indexCCCNumber;
	}
	public void setIndexCCCNumber(String indexCCCNumber) {
		this.indexCCCNumber = indexCCCNumber;
	}
	public String getCccNumber() {
		return cccNumber;
	}
	public void setCccNumber(String cccNumber) {
		this.cccNumber = cccNumber;
	}
	public String getClientType() {
		return clientType;
	}
	public void setClientType(String clientType) {
		this.clientType = clientType;
	}
	public String getConsentedProvideContacts() {
		return consentedProvideContacts;
	}
	public void setConsentedProvideContacts(String consentedProvideContacts) {
		this.consentedProvideContacts = consentedProvideContacts;
	}
	public String getDeclinedProvideContacts() {
		return declinedProvideContacts;
	}
	public void setDeclinedProvideContacts(String declinedProvideContacts) {
		this.declinedProvideContacts = declinedProvideContacts;
	}
	public String getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public String getRelationshipIndexClient() {
		return relationshipIndexClient;
	}
	public void setRelationshipIndexClient(String relationshipIndexClient) {
		this.relationshipIndexClient = relationshipIndexClient;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getLivingWihIndex() {
		return livingWihIndex;
	}
	public void setLivingWihIndex(String livingWihIndex) {
		this.livingWihIndex = livingWihIndex;
	}
	public String getPhysicalHurt() {
		return physicalHurt;
	}
	public void setPhysicalHurt(String physicalHurt) {
		this.physicalHurt = physicalHurt;
	}
	public String getThreatenedToHurt() {
		return threatenedToHurt;
	}
	public void setThreatenedToHurt(String threatenedToHurt) {
		this.threatenedToHurt = threatenedToHurt;
	}
	public String getSexuallyUncomfortable() {
		return sexuallyUncomfortable;
	}
	public void setSexuallyUncomfortable(String sexuallyUncomfortable) {
		this.sexuallyUncomfortable = sexuallyUncomfortable;
	}
	public String getTestingEligibility() {
		return testingEligibility;
	}
	public void setTestingEligibility(String testingEligibility) {
		this.testingEligibility = testingEligibility;
	}
	public String getHivStatus() {
		return hivStatus;
	}
	public void setHivStatus(String hivStatus) {
		this.hivStatus = hivStatus;
	}
	public String getPnsApproach() {
		return pnsApproach;
	}
	public void setPnsApproach(String pnsApproach) {
		this.pnsApproach = pnsApproach;
	}
	public String getContactType() {
		return contactType;
	}
	public void setContactType(String contactType) {
		this.contactType = contactType;
	}
	public String getTracingAttempt() {
		return tracingAttempt;
	}
	public void setTracingAttempt(String tracingAttempt) {
		this.tracingAttempt = tracingAttempt;
	}
	public String getTracingOutcome() {
		return tracingOutcome;
	}
	public void setTracingOutcome(String tracingOutcome) {
		this.tracingOutcome = tracingOutcome;
	}
	public String getConsentedTesting() {
		return consentedTesting;
	}
	public void setConsentedTesting(String consentedTesting) {
		this.consentedTesting = consentedTesting;
	}
	public String getDateBookedForTesting() {
		return dateBookedForTesting;
	}
	public void setDateBookedForTesting(String dateBookedForTesting) {
		this.dateBookedForTesting = dateBookedForTesting;
	}
	public String getConsentedTesting2() {
		return consentedTesting2;
	}
	public void setConsentedTesting2(String consentedTesting2) {
		this.consentedTesting2 = consentedTesting2;
	}
	public String getDeclineReason() {
		return declineReason;
	}
	public void setDeclineReason(String declineReason) {
		this.declineReason = declineReason;
	}
	public String getTestingOutcome() {
		return testingOutcome;
	}
	public void setTestingOutcome(String testingOutcome) {
		this.testingOutcome = testingOutcome;
	}
	public String getLinkToCare() {
		return linkToCare;
	}
	public void setLinkToCare(String linkToCare) {
		this.linkToCare = linkToCare;
	}
	public String getDateLinked() {
		return dateLinked;
	}
	public void setDateLinked(String dateLinked) {
		this.dateLinked = dateLinked;
	}
	public String getFacilityLinked() {
		return facilityLinked;
	}
	public void setFacilityLinked(String facilityLinked) {
		this.facilityLinked = facilityLinked;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	@Override
	public String toString() {
		return "PsnModel [patientId=" + patientId + ", identifier=" + identifier + ", patientName=" + patientName
				+ ", indexCCCNumber=" + indexCCCNumber + ", cccNumber=" + cccNumber + ", clientType=" + clientType
				+ ", consentedProvideContacts=" + consentedProvideContacts + ", declinedProvideContacts="
				+ declinedProvideContacts + ", maritalStatus=" + maritalStatus + ", relationshipIndexClient="
				+ relationshipIndexClient + ", occupation=" + occupation + ", livingWihIndex=" + livingWihIndex
				+ ", physicalHurt=" + physicalHurt + ", threatenedToHurt=" + threatenedToHurt
				+ ", sexuallyUncomfortable=" + sexuallyUncomfortable + ", testingEligibility=" + testingEligibility
				+ ", hivStatus=" + hivStatus + ", pnsApproach=" + pnsApproach + ", contactType=" + contactType
				+ ", tracingAttempt=" + tracingAttempt + ", tracingOutcome=" + tracingOutcome + ", consentedTesting="
				+ consentedTesting + ", dateBookedForTesting=" + dateBookedForTesting + ", consentedTesting2="
				+ consentedTesting2 + ", declineReason=" + declineReason + ", testingOutcome=" + testingOutcome
				+ ", linkToCare=" + linkToCare + ", dateLinked=" + dateLinked + ", facilityLinked=" + facilityLinked
				+ ", remarks=" + remarks + "]";
	}
	
	
	
	

}
