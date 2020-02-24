package com.pns.controllers;

import java.sql.Array;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.pns.models.LoginModel;
import com.pns.models.PsnModel;
import com.pns.models.SMSModel;
import com.pns.sqlQueries.SmsSql;
import com.pns.sqlQueries.Sql;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class PnsController {
	Connection conn = null;
	Statement stmt = null;
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement pst = null;
	PreparedStatement pstUpdate =null;

	@Autowired
	private DataSource dataSource;

	@GetMapping("/test1")
	public String getAllEmployees() {
		System.out.println("TEST HERE agik");
		return "testpppppp";
	}

	@GetMapping("/pnsTrees")
	public ArrayList pnsTree() {
		ArrayList pnsData = new ArrayList<>();
		PsnModel pns = null;
		Sql sql = null;

		sql = new Sql();

		String query = sql.contactIndexesPnsTree();

		try {
			conn = dataSource.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(query);

			while (rs.next()) {
				pns = new PsnModel();
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

		String query = sql.contactDetailsSql(patientId);

		try {
			conn = dataSource.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(query);

			while (rs.next()) {
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
		System.out.println("the ccc number " + cccNumber);
		ArrayList<PsnModel> pnsData = new ArrayList<>();
		com.pns.models.PsnModel pns = null;

		Sql sql = new Sql();

		String query = sql.filterPnsTree(cccNumber);

		try {
			conn = dataSource.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(query);

			while (rs.next()) {
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

		String query = sql.contactDetailsSql(id);

		try {
			conn = dataSource.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(query);

			while (rs.next()) {
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
	public boolean pnsMakeIndex(@PathVariable String contactid, @RequestBody String cccNumber) {
		boolean result = false;
		System.out.println("contactid " + contactid);
		System.out.println("cccNumber " + cccNumber);
		PsnModel pns = null;

		Sql sql = new Sql();

		String query = sql.updateToIndexSql(contactid, cccNumber);

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
	@PostMapping("/login")
private ArrayList loginUser(@RequestBody LoginModel loginModel1) {
		LoginModel loginModel =null;
		
		
		String username = loginModel1.getUsername();
		String password = loginModel1.getPassword();
		
		ArrayList userLogin = new ArrayList();
		
		SmsSql sql = new SmsSql();

		String query = sql.loginUser(username,password);

		try {
			conn = dataSource.getConnection();
			st = conn.createStatement();			
			rs = st.executeQuery(query);
			
			loginModel = new LoginModel();
			if(rs.next()) {					
				loginModel.setName(rs.getString("Name"));
				loginModel.setUsername(rs.getString("username"));
				loginModel.setUserType(rs.getString("User_Type"));
				loginModel.setUserTypeId(rs.getString("User_Type_ID"));
				loginModel.setLoginStatus(rs.getInt("loginstate"));	
				System.out.println("YES rrr lll ");
				
				userLogin.add(loginModel);
				System.out.println("YES  "+rs.getString("loginstate"));
			}else {
				
				loginModel.setLoginStatus(rs.getInt("loginstate"));
				
				userLogin.add(loginModel);
			}

		} catch (SQLException e) {
			
			
			throw new RuntimeException();

		} finally {
			try {
				if (conn != null) {
					conn.close();
					//return userLogin;
				}

			} catch (SQLException e) {
				System.out.println("5555 ");
				
				// TODO: handle exception
			}
		}
		
		
		return userLogin;
		
	}
	
	
	@GetMapping("/getsms")	
	public ArrayList retrieveSms() {
		SMSModel smsModel = null;
		
		 String smsData = null;
		Object data= null;
		SmsSql sql = new SmsSql();		
		
		String  query = sql.getResults();
		
		ArrayList smsDetails = new ArrayList();
		
		
		try {
			
			
			conn = dataSource.getConnection();
			st = conn.createStatement();
			
			rs = st.executeQuery(query);			
			
			
			while(rs.next()) {	
				smsModel =  new SMSModel();
				smsModel.setRegion(rs.getString("Region"));
				smsModel.setSubCounty(rs.getString("SubCounty"));
				smsModel.setFacilityName(rs.getString("FacilityName"));
				smsModel.setFmlCode(rs.getString("MFLCode"));
				smsModel.setCounsellorCode(rs.getString("CounsellorCode"));
				smsModel.setCounsellorName(rs.getString("CounsellorName"));
				smsModel.setProgramArea(rs.getString("ProgramArea"));
				smsModel.setServiceAreaCode(rs.getString("ServiceAreaCode"));
				smsModel.setServiceAreaName(rs.getString("ServiceAreaName"));
				smsModel.setPhoneNumber(rs.getString("PhoneNumber"));
				smsModel.setDate(rs.getString("Date"));
				smsModel.setIndexIdentified(rs.getInt("IND"));
				smsModel.setIndexScreened(rs.getInt("INDSC"));
				smsModel.setContactListed(rs.getInt("CT"));
				smsModel.setContactEligibleTesting(rs.getInt("CTEL"));
				smsModel.setClientstested(rs.getInt("T"));
				smsModel.setClientsTestedPositive(rs.getInt("P"));
				smsModel.setClientLinked(rs.getInt("L"));
				
									
				smsDetails.add(smsModel);				
				
				 
			}	
			
			
			
		} catch (SQLException e) {
			System.out.print(e);
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
		
		
		
		
		return smsDetails;
		
		
	}
	
	@GetMapping("/servicearea/{id}")	
	public ArrayList retrieveSmsByServiceArea(@PathVariable int id) {
		SMSModel smsModel = null;
		
		 String smsData = null;
		Object data= null;
		SmsSql sql = new SmsSql();		
		
		String  query = sql.filterByServiceArea(id);
		
		ArrayList smsDetails = new ArrayList();
		
		
		try {
			
			
			conn = dataSource.getConnection();
			st = conn.createStatement();
			
			rs = st.executeQuery(query);			
			
			
			while(rs.next()) {	
				smsModel =  new SMSModel();
				smsModel.setRegion(rs.getString("Region"));
				smsModel.setSubCounty(rs.getString("SubCounty"));
				smsModel.setFacilityName(rs.getString("FacilityName"));
				smsModel.setFmlCode(rs.getString("MFLCode"));
				smsModel.setCounsellorCode(rs.getString("CounsellorCode"));
				smsModel.setCounsellorName(rs.getString("CounsellorName"));
				smsModel.setProgramArea(rs.getString("ProgramArea"));
				smsModel.setServiceAreaCode(rs.getString("ServiceAreaCode"));
				smsModel.setServiceAreaName(rs.getString("ServiceAreaName"));
				smsModel.setPhoneNumber(rs.getString("PhoneNumber"));
				smsModel.setDate(rs.getString("Date"));
				smsModel.setIndexIdentified(rs.getInt("IND"));
				smsModel.setIndexScreened(rs.getInt("INDSC"));
				smsModel.setContactListed(rs.getInt("CT"));
				smsModel.setContactEligibleTesting(rs.getInt("CTEL"));
				smsModel.setClientstested(rs.getInt("T"));
				smsModel.setClientsTestedPositive(rs.getInt("P"));
				smsModel.setClientLinked(rs.getInt("L"));
				
									
				smsDetails.add(smsModel);				
				
				 
			}	
			
			
			
		} catch (SQLException e) {
			System.out.print(e);
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
		
		
		
		
		return smsDetails;
		
		
	}
	
	
	@GetMapping("/filtereddates/{dateFrom}/{dateTo}")	
	public ArrayList filterByDates(@PathVariable String dateFrom, @PathVariable String dateTo ) {
		
		//System.out.println("BY DATES :: "+json) @RequestBody String json; 
		SMSModel smsModel = null;
		
		 String smsData = null;
		Object data= null;
		SmsSql sql = new SmsSql();	
		String datefrom=dateFrom;
		String dateto =dateTo;
		
		String  query = sql.getResultsByDates(datefrom,dateto);
		
		ArrayList smsDetails = new ArrayList();
		
		
		try {
			
			
			conn = dataSource.getConnection();
			st = conn.createStatement();
			
			rs = st.executeQuery(query);			
			
			
			while(rs.next()) {	
				smsModel =  new SMSModel();
				smsModel.setRegion(rs.getString("Region"));
				smsModel.setSubCounty(rs.getString("SubCounty"));
				smsModel.setFacilityName(rs.getString("FacilityName"));
				smsModel.setFmlCode(rs.getString("MFLCode"));
				smsModel.setCounsellorCode(rs.getString("CounsellorCode"));
				smsModel.setCounsellorName(rs.getString("CounsellorName"));
				smsModel.setProgramArea(rs.getString("ProgramArea"));
				smsModel.setServiceAreaCode(rs.getString("ServiceAreaCode"));
				smsModel.setServiceAreaName(rs.getString("ServiceAreaName"));
				smsModel.setPhoneNumber(rs.getString("PhoneNumber"));
				smsModel.setDate(rs.getString("Date"));
				smsModel.setIndexIdentified(rs.getInt("IND"));
				smsModel.setIndexScreened(rs.getInt("INDSC"));
				smsModel.setContactListed(rs.getInt("CT"));
				smsModel.setContactEligibleTesting(rs.getInt("CTEL"));
				smsModel.setClientstested(rs.getInt("T"));
				smsModel.setClientsTestedPositive(rs.getInt("P"));
				smsModel.setClientLinked(rs.getInt("L"));
				
									
				smsDetails.add(smsModel);				
				
				 
			}	
			
			
			
		} catch (SQLException e) {
			System.out.print(e);
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
		
		
		
		
		return smsDetails;
		
		
	}

	@PostMapping(("/insertSms"))
	public String inserSms(@RequestBody String json) {
		System.out.println("INSERTING RECORDS PNS");

		JsonParser parser = new JsonParser();
		JsonElement jsonTree = parser.parse(json);
		JsonObject jsonObject = jsonTree.getAsJsonObject();
		
		ArrayList<String> messageArray = new ArrayList<String>();
		// text sent
		String message = jsonObject.get("message").toString().trim().replace("\"", "");

		// sms sender number
		String sender = jsonObject.get("sender").toString().trim().replace("\"", "");

		// process the data by passing it thru regex processor

		processRegex(json);
		
		
		String counsellor_code = null;
		String service_area_code =null;
		String facility_fmlcode =null;
		
		String responseMessage =null;
		
		
		// convert the message to array then add to the arrayList
		 String[] mArray = message.split(" ");
		 System.out.println("String Array "+message);	
			for(String s:mArray) {
				messageArray.add(s);
				//NB: NEVER USE C code as indicator
				if(s.replaceAll("[0-9]", "").equals("C")) {
					// extract the number
					counsellor_code = s.replaceAll("[^0-9]", "");
					
				}else  {
					// get service area from the message	
					System.out.println("SERVICE AREA  "+s);
					switch (s) {
					
					case "HTSPOS":
						service_area_code = s;
						break;
					case "PNSNP":
						service_area_code = s;
						break;	
					case "PNSHVL":
						service_area_code = s;
						break;
					case "PNSAD":
						service_area_code = s;
						break;
					case "PNSW":
						service_area_code = s;
						break;
					case "PNSANC":
						service_area_code = s;
						break;
					case "PNSMAT":
						service_area_code = s;
						break;
					case "PNSNC":					
						service_area_code = s;
					case "PNSPNC":					
						service_area_code = s;

					default:
						break;
					}
					System.out.println("SERVICE AREA  "+s);
				}
				
			}


		
		// service area id
		
		int facilty_id=getcounsellor(counsellor_code)[0];
		int counsellor_id = getcounsellor(counsellor_code)[1];
		int service_area_id = getServiceArea(service_area_code);
		//int facilty_id = getFacilty(facility_fmlcode);
		int isRecordExisting =0;
		
		
		// get the SMS received date
		
		LocalDate date = LocalDate.now(); // gets the current date

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyy-MM-dd");
		
		String smsDate =date.format(formatter);

		if(facilty_id <=0 || counsellor_id <=0 || service_area_id <=0) {
			
			// check if record already exist to update
			
			
			//some messages
			responseMessage ="Wrong counsellor code or service area code";
		}else {
			System.out.println("IDS not 0 ");
			SmsSql sql = new SmsSql();
			String query = sql.insertData();
			//update script
			
			
			try {
				conn = dataSource.getConnection();
				pst = conn.prepareStatement(query);
				

				Map<Integer, Integer> mapEntries = processRegex(json);
				// check if map is empty
				if(mapEntries.size()<=0) {
					// some messages
					responseMessage ="Empty values";
					
				}else {
					System.out.println("size not 0 ");
					
					for (Map.Entry<Integer, Integer> entry : mapEntries.entrySet()) {
						System.out.println("THE :::: "+entry.getValue());
						
						// check if any of the keys equal to 0
						if(entry.getValue()<=0 || entry.getKey()<=0) {
							responseMessage ="Inserting empty records";
							
						}else {
							//some message
							System.out.println("values not 0 ");
							isRecordExisting=checkAlreadySent( smsDate, facilty_id, counsellor_id, service_area_id,entry.getKey());
							System.out.println(" RECORD FOUND ::::::: "+isRecordExisting);
							if(isRecordExisting == 0) {
								System.out.println("inserting values ");
								// results not yet sent so insert
								pst.setString(1, sender);
								pst.setString(2, smsDate);
								pst.setInt(3, facilty_id);
								pst.setInt(4, counsellor_id);
								pst.setInt(5, service_area_id);
								pst.setInt(6, entry.getKey());
								pst.setInt(7, entry.getValue());
								pst.execute();
							}else {
								// results already sent , so update
								//entry.getValue is the indicator id
								System.out.println("updating values ");
								conn = dataSource.getConnection();
								
								int dataValue = entry.getValue();
								String queryUpdate = sql.updateAchieved(smsDate, facilty_id, counsellor_id, service_area_id,entry.getKey(),dataValue);
								//pstUpdate = conn.createStatement(queryUpdate);	
								System.out.println("DATA::::: "+queryUpdate);
								st = conn.createStatement();
								st.executeUpdate(queryUpdate);								
								
							}
						}
						}
					responseMessage ="success";
				}
				

			} catch (SQLException e) {
				
				System.out.println(e);
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
			
		}
		
		System.out.println("responseMessage ::::::"+responseMessage);

		
		return responseMessage;

	}

	

	public HashMap<Integer, Integer> processRegex(String json) {

		JsonParser parser = new JsonParser();
		JsonElement jsonTree = parser.parse(json);
		
		ArrayList<String> messageArray = new ArrayList();

		Map<String, String> mapData = new HashMap<String, String>();
		
		HashMap<Integer, Integer> cleanCodeKey = new HashMap<Integer, Integer>();

		if (jsonTree.isJsonObject()) {
			JsonObject jsonObject = jsonTree.getAsJsonObject();

			String message = jsonObject.get("message").toString().trim().replace("\"", "");

			String sender = jsonObject.get("sender").toString().trim().replace("\"", "");

			String matchedRecord = null;

			// Map containing all indicators

			 Map<Integer, String> indicators = getSmsIndicators();
			 
			 // convert the message to array then add to the arrayList
			 String[] mArray = message.split(" ");
				
				for(String s:mArray) {
					messageArray.add(s);
					System.out.println("String Array "+s.length());		
					
				}

			// loop through the map , pass the code to the regex matcher
			// String smsText = message;
				for (Map.Entry<Integer, String> entry : indicators.entrySet()) {
					
					int i=0;
					for(String data: messageArray) {
						if(data.replaceAll("[0-9]", "").equals(entry.getValue())) {
							
							cleanCodeKey.put(entry.getKey(), Integer.parseInt(data.replaceAll("[^0-9]", "")));
							
						}else {
						}
					}
				   // System.out.println(entry.getKey() + " = " + entry.getKey());
				}
				
				/* extract the values to be inserted into the database
				for(Map.Entry<Integer, Integer> bdData : cleanCodeKey.entrySet()){
					System.out.println("NOT FOUND DB key  "+bdData.getKey()+ " Value "+bdData.getValue());
					
				}*/

		}

		return cleanCodeKey;
		

	}
	
	public Map<Integer, String> getSmsIndicators() {

		SMSModel smsModel = null;
		SmsSql sql = new SmsSql();

		String query = sql.getIndicators();

		Map<Integer, String> indicatorsMap = new HashMap<Integer, String>();

		try {
			System.out.println("THE INDICATORS dataSource.getConnection()::: ");

			conn = dataSource.getConnection();
			st = conn.createStatement();

			rs = st.executeQuery(query);

			while (rs.next()) {

				indicatorsMap.put(rs.getInt("id"), rs.getString("code"));

			}

		} catch (SQLException e) {
			System.out.print(e);
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

		return indicatorsMap;
	}

	private int[] getcounsellor(String code) {
		SmsSql sql = new SmsSql();

		String query = sql.getcounsellorCode(code);

		int c_code = 0;
		// the array contains facility_id at[0] and counsellor id at[1]
		int[] counsellor_fid = new int[2];

		try {

			conn = dataSource.getConnection();
			st = conn.createStatement();

			rs = st.executeQuery(query);

			if (rs.next()) {
				c_code = rs.getInt("id");
				counsellor_fid[0] =  rs.getInt("facility_id");
				counsellor_fid[1] =  rs.getInt("id");
				

			}

		} catch (SQLException e) {
			System.out.print(e);
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

		return counsellor_fid;
	}

	private int getServiceArea(String code) {

		SmsSql sql = new SmsSql();

		String query = sql.getServiceAreaCode(code);

		int i_code = 0;

		try {

			conn = dataSource.getConnection();
			st = conn.createStatement();

			rs = st.executeQuery(query);

			if (rs.next()) {
				i_code = rs.getInt("id");

			}

		} catch (SQLException e) {
			System.out.print(e);
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

		return i_code;
	}
	
	private int getFacilty(String fml_code) {

		SmsSql sql = new SmsSql();

		String query = sql.getFaciltyFMLCode(fml_code);

		int fmlcode = 0;

		try {

			conn = dataSource.getConnection();
			st = conn.createStatement();

			rs = st.executeQuery(query);

			if (rs.next()) {
				fmlcode = rs.getInt("id");

			}

		} catch (SQLException e) {
			System.out.print(e);
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

		return fmlcode;
	}
	
	
	private int checkAlreadySent(String smsDate, int facility_id, int counsellor_id, int service_area_id, int indicator_id) {

		SmsSql sql = new SmsSql();

		String query = sql.checkUpdateInsert(smsDate, facility_id, counsellor_id, service_area_id,indicator_id);

		int recordCount = 0;

		try {

			conn = dataSource.getConnection();
			st = conn.createStatement();

			rs = st.executeQuery(query);

			if (rs.next()) {
				recordCount = rs.getInt("id");

			}

		} catch (SQLException e) {
			System.out.print(e);
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

		return recordCount;
	}
	
	

}
