package com.pns.controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pns.sqlQueries.Sql;
import com.pns.models.PsnModel;
@CrossOrigin(origins = "http://localhost:4200")
@RestController 
@RequestMapping("/api/v1")
public class PnsController {
	Connection conn = null;
	Statement stmt = null;
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement pst =null;
	
	
	
	@Autowired
	private DataSource dataSource;
	
	@GetMapping("/test1")
    public String getAllEmployees() {
		System.out.println("TEST HERE agik");
		return "testpppppp";
    }
	
	@GetMapping("/pnsTrees")	
	public ArrayList pnsTree() {
		ArrayList<PsnModel> pnsData = new ArrayList<>();
		com.pns.models.PsnModel pns = null;
		
		Sql sql = new Sql();
		
		String  query = sql.contactIndexesPnsTree();		
			
		
		
		try {
			conn = dataSource.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(query);
			
			while(rs.next()) {
				pns = new com.pns.models.PsnModel();
				pns.setPatientId(rs.getInt("patientId"));
				pns.setIdentifier(rs.getString("identifier"));
				pns.setPatientName(rs.getString("patientName"));
				pns.setIndexCCCNumber(rs.getString("indexCCCNumber"));
				pns.setCccNumber(rs.getString("cccNumber"));
				pns.setClientType(rs.getString("clientType"));
				pns.setConsentedProvideContacts(rs.getString("consentedProvideContacts"));
				pns.setDeclinedProvideContacts(rs.getString("declinedProvideContacts"));
				pns.setMaritalStatus(rs.getString("maritalStatus"));
				pns.setRelationshipIndexClient(rs.getString("relationshipIndexClient"));
				pns.setOccupation(rs.getString("occupation"));
				pns.setLivingWihIndex(rs.getString("livingWihIndex"));
				pns.setPhysicalHurt(rs.getString("physicalHurt"));
				pns.setThreatenedToHurt(rs.getString("threatenedToHurt"));
				pns.setSexuallyUncomfortable(rs.getString("sexuallyUncomfortable"));
				pns.setTestingEligibility(rs.getString("testingEligibility"));
				pns.setHivStatus(rs.getString("hivStatus"));
				pns.setPnsApproach(rs.getString("pnsApproach"));
				pns.setContactType(rs.getString("contactType"));
				pns.setTracingAttempt(rs.getString("tracingAttempt"));
				pns.setTracingOutcome(rs.getString("tracingOutcome"));
				pns.setConsentedTesting(rs.getString("consentedTesting"));
				pns.setDateBookedForTesting(rs.getString("dateBookedForTesting"));
				pns.setConsentedTesting2(rs.getString("consentedTesting2"));
				pns.setDeclineReason(rs.getString("declineReason"));
				pns.setTestingOutcome(rs.getString("testingOutcome"));
				pns.setLinkToCare(rs.getString("linkToCare"));
				pns.setDateLinked(rs.getString("dateLinked"));
				pns.setFacilityLinked(rs.getString("facilityLinked"));
				pns.setRemarks(rs.getString("remarks"));
				
				pnsData.add(pns);
				 
			}				
			
		} catch (SQLException e) {
			throw new RuntimeException();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				
			} catch (SQLException e) {
				// TODO: handle exception
			}
		}
		return pnsData;
		
		
	}
	
	@GetMapping("/contactDetails")	
	public ArrayList contactDetails(String patientId) {
		ArrayList<PsnModel> pnsData = new ArrayList<>();
		com.pns.models.PsnModel pns = null;
		
		Sql sql = new Sql();
		
		String  query = sql.contactDetailsSql(patientId);		
			
		
		
		try {
			conn = dataSource.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(query);
			
			while(rs.next()) {
				pns = new com.pns.models.PsnModel();
				pns.setPatientId(rs.getInt("patientId"));
				pns.setIdentifier(rs.getString("identifier"));
				pns.setPatientName(rs.getString("patientName"));
				pns.setIndexCCCNumber(rs.getString("indexCCCNumber"));
				pns.setCccNumber(rs.getString("cccNumber"));
				pns.setClientType(rs.getString("clientType"));
				pns.setConsentedProvideContacts(rs.getString("consentedProvideContacts"));
				pns.setDeclinedProvideContacts(rs.getString("declinedProvideContacts"));
				pns.setMaritalStatus(rs.getString("maritalStatus"));
				pns.setRelationshipIndexClient(rs.getString("relationshipIndexClient"));
				pns.setOccupation(rs.getString("occupation"));
				pns.setLivingWihIndex(rs.getString("livingWihIndex"));
				pns.setPhysicalHurt(rs.getString("physicalHurt"));
				pns.setThreatenedToHurt(rs.getString("threatenedToHurt"));
				pns.setSexuallyUncomfortable(rs.getString("sexuallyUncomfortable"));
				pns.setTestingEligibility(rs.getString("testingEligibility"));
				pns.setHivStatus(rs.getString("hivStatus"));
				pns.setPnsApproach(rs.getString("pnsApproach"));
				pns.setContactType(rs.getString("contactType"));
				pns.setTracingAttempt(rs.getString("tracingAttempt"));
				pns.setTracingOutcome(rs.getString("tracingOutcome"));
				pns.setConsentedTesting(rs.getString("consentedTesting"));
				pns.setDateBookedForTesting(rs.getString("dateBookedForTesting"));
				pns.setConsentedTesting2(rs.getString("consentedTesting2"));
				pns.setDeclineReason(rs.getString("declineReason"));
				pns.setTestingOutcome(rs.getString("testingOutcome"));
				pns.setLinkToCare(rs.getString("linkToCare"));
				pns.setDateLinked(rs.getString("dateLinked"));
				pns.setFacilityLinked(rs.getString("facilityLinked"));
				pns.setRemarks(rs.getString("remarks"));
				
				pnsData.add(pns);
				 
			}				
			
		} catch (SQLException e) {
			throw new RuntimeException();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				
			} catch (SQLException e) {
				// TODO: handle exception
			}
		}
		return pnsData;
		
		
	}
	
	
	@GetMapping("/searchTree/{cccNumber}")	
	public ArrayList pnsSearchTree(@PathVariable String cccNumber) {
		System.out.println("the ccc number "+ cccNumber );
		ArrayList<PsnModel> pnsData = new ArrayList<>();
		com.pns.models.PsnModel pns = null;
		
		Sql sql = new Sql();
		
		String  query = sql.filterPnsTree(cccNumber);		
		
		try {
			conn = dataSource.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(query);
			
			while(rs.next()) {
				pns = new com.pns.models.PsnModel();
				pns.setPatientId(rs.getInt("patientId"));
				pns.setIdentifier(rs.getString("identifier"));
				pns.setPatientName(rs.getString("patientName"));
				pns.setIndexCCCNumber(rs.getString("indexCCCNumber"));
				pns.setCccNumber(rs.getString("cccNumber"));
				pns.setClientType(rs.getString("clientType"));
				pns.setConsentedProvideContacts(rs.getString("consentedProvideContacts"));
				pns.setDeclinedProvideContacts(rs.getString("declinedProvideContacts"));
				pns.setMaritalStatus(rs.getString("maritalStatus"));
				pns.setRelationshipIndexClient(rs.getString("relationshipIndexClient"));
				pns.setOccupation(rs.getString("occupation"));
				pns.setLivingWihIndex(rs.getString("livingWihIndex"));
				pns.setPhysicalHurt(rs.getString("physicalHurt"));
				pns.setThreatenedToHurt(rs.getString("threatenedToHurt"));
				pns.setSexuallyUncomfortable(rs.getString("sexuallyUncomfortable"));
				pns.setTestingEligibility(rs.getString("testingEligibility"));
				pns.setHivStatus(rs.getString("hivStatus"));
				pns.setPnsApproach(rs.getString("pnsApproach"));
				pns.setContactType(rs.getString("contactType"));
				pns.setTracingAttempt(rs.getString("tracingAttempt"));
				pns.setTracingOutcome(rs.getString("tracingOutcome"));
				pns.setConsentedTesting(rs.getString("consentedTesting"));
				pns.setDateBookedForTesting(rs.getString("dateBookedForTesting"));
				pns.setConsentedTesting2(rs.getString("consentedTesting2"));
				pns.setDeclineReason(rs.getString("declineReason"));
				pns.setTestingOutcome(rs.getString("testingOutcome"));
				pns.setLinkToCare(rs.getString("linkToCare"));
				pns.setDateLinked(rs.getString("dateLinked"));
				pns.setFacilityLinked(rs.getString("facilityLinked"));
				pns.setRemarks(rs.getString("remarks"));
				
				pnsData.add(pns);
				 
			}				
			
		} catch (SQLException e) {
			throw new RuntimeException();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				
			} catch (SQLException e) {
				// TODO: handle exception
			}
		}
		return pnsData;
		
		
	}
	
	@GetMapping("/contactDetails/{id}")	
	public ArrayList pnsContacts(@PathVariable String id) {
		ArrayList<PsnModel> pnsData = new ArrayList<>();
		com.pns.models.PsnModel pns = null;
		
		Sql sql = new Sql();
		
		String  query = sql.contactDetailsSql(id);		
			
		
		
		try {
			conn = dataSource.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(query);
			
			while(rs.next()) {
				pns = new com.pns.models.PsnModel();
				pns.setPatientId(rs.getInt("patientId"));
				pns.setIdentifier(rs.getString("identifier"));
				pns.setPatientName(rs.getString("patientName"));
				pns.setIndexCCCNumber(rs.getString("indexCCCNumber"));
				pns.setCccNumber(rs.getString("cccNumber"));
				pns.setClientType(rs.getString("clientType"));
				pns.setConsentedProvideContacts(rs.getString("consentedProvideContacts"));
				pns.setDeclinedProvideContacts(rs.getString("declinedProvideContacts"));
				pns.setMaritalStatus(rs.getString("maritalStatus"));
				pns.setRelationshipIndexClient(rs.getString("relationshipIndexClient"));
				pns.setOccupation(rs.getString("occupation"));
				pns.setLivingWihIndex(rs.getString("livingWihIndex"));
				pns.setPhysicalHurt(rs.getString("physicalHurt"));
				pns.setThreatenedToHurt(rs.getString("threatenedToHurt"));
				pns.setSexuallyUncomfortable(rs.getString("sexuallyUncomfortable"));
				pns.setTestingEligibility(rs.getString("testingEligibility"));
				pns.setHivStatus(rs.getString("hivStatus"));
				pns.setPnsApproach(rs.getString("pnsApproach"));
				pns.setContactType(rs.getString("contactType"));
				pns.setTracingAttempt(rs.getString("tracingAttempt"));
				pns.setTracingOutcome(rs.getString("tracingOutcome"));
				pns.setConsentedTesting(rs.getString("consentedTesting"));
				pns.setDateBookedForTesting(rs.getString("dateBookedForTesting"));
				pns.setConsentedTesting2(rs.getString("consentedTesting2"));
				pns.setDeclineReason(rs.getString("declineReason"));
				pns.setTestingOutcome(rs.getString("testingOutcome"));
				pns.setLinkToCare(rs.getString("linkToCare"));
				pns.setDateLinked(rs.getString("dateLinked"));
				pns.setFacilityLinked(rs.getString("facilityLinked"));
				pns.setRemarks(rs.getString("remarks"));
				
				pnsData.add(pns);
				 
			}				
			
		} catch (SQLException e) {
			throw new RuntimeException();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				
			} catch (SQLException e) {
				// TODO: handle exception
			}
		}
		return pnsData;
		
		
	}
	
	
	@PutMapping("/makeIndex/{contactid}")	
	public boolean pnsMakeIndex(@PathVariable String contactid ,@RequestBody String cccNumber) {
		boolean result = false;
		System.out.println("contactid "+contactid);
		System.out.println("cccNumber "+cccNumber);
		com.pns.models.PsnModel pns = null;
		
		Sql sql = new Sql();
		
		String  query = sql.updateToIndexSql(contactid,cccNumber);		
			
		
		
		try {
			conn = dataSource.getConnection();
			st = conn.createStatement();
			st.executeUpdate(query);
			
			
			
		} catch (SQLException e) {
			System.out.println("44444 ");
			throw new RuntimeException();
			
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				
			} catch (SQLException e) {
				System.out.println("5555 ");
				// TODO: handle exception
			}
		}
		return result;
		
		
	}
	
	
}
