package com.pns.sqlQueries;

public class SmsSql {

	
public String smsInsert() {
		
		String sqlInsert = "INSERT INTO sms(message, sender) VALUES (?,?)";
		
		
		return sqlInsert;
	}
	
public String getSMS() {
		
		String selectSms = "SELECT message,sender FROM sms ORDER BY id DESC LIMIT 1";
		
		
		return selectSms;
	}

public String smsResults() {
	
	String sqlInsert = "INSERT INTO sms_results(sender, tested,positive,negative,unknown,status) VALUES (?,?,?,?,?,?)";
	
	
	return sqlInsert;
}

public String getResults() {
	
	//String selectSms = "SELECT r.sender, r.tested,r.positive,r.negative,r.unknown,f.name FROM sms_results  r "
		//	+ "JOIN facilities f ON (r.sender = f.provider_number) ORDER BY r.id DESC LIMIT 1";
	
	String selectSms = "SELECT ma.name AS Region,sc.name AS SubCounty,f.name AS FacilityName,f.mflcode AS MFLCode,\n" + 
			"c.code AS CounsellorCode, concat_ws(\" \", pn.given_name,pn.middle_name,pn.sur_name) as CounsellorName, \n" + 
			"pa.name AS ProgramArea,sa.code AS ServiceAreaCode,sa.fullname AS ServiceAreaName,  \n" + 
			"d.phone AS PhoneNumber,d.date AS Date,\n" + 
			"SUM(CASE WHEN i.code in(\"IND\") THEN d.achieved ELSE 0 END) AS 'IND',\n" + 
			"SUM(CASE WHEN i.code in(\"INDSC\") THEN d.achieved ELSE 0 END) AS 'INDSC',\n" + 
			"SUM(CASE WHEN i.code in(\"CT\") THEN d.achieved ELSE 0 END) AS 'CT',\n" + 
			"SUM(CASE WHEN i.code in(\"CTEL\") THEN d.achieved ELSE 0 END) AS 'CTEL',\n" + 
			"SUM(CASE WHEN i.code in(\"T\") THEN d.achieved ELSE 0 END) AS 'T',\n" + 
			"SUM(CASE WHEN i.code in(\"P\") THEN d.achieved ELSE 0 END) AS 'P',\n" + 
			"SUM(CASE WHEN i.code in(\"L\") THEN d.achieved ELSE 0 END) AS 'L',\n" + 
			"SUM(CASE WHEN i.code in(\"NR\") THEN d.achieved ELSE 0 END) AS 'NR',\n" + 
			"SUM(CASE WHEN i.code in(\"AH\") THEN d.achieved ELSE 0 END) AS 'AH',\n" + 
			"SUM(CASE WHEN i.code in(\"KP\") THEN d.achieved ELSE 0 END) AS 'KP',\n" + 
			"SUM(CASE WHEN i.code in(\"WL\") THEN d.achieved ELSE 0 END) AS 'WL',\n" + 
			"SUM(CASE WHEN i.code in(\"DL\") THEN d.achieved ELSE 0 END) AS 'DL',\n" + 
			"SUM(CASE WHEN i.code in(\"DC\") THEN d.achieved ELSE 0 END) AS 'DC',\n" + 
			"SUM(CASE WHEN i.code in(\"UC\") THEN d.achieved ELSE 0 END) AS 'UC',\n" + 
			"SUM(CASE WHEN i.code in(\"MT\") THEN d.achieved ELSE 0 END) AS 'MT',\n" + 
			"SUM(CASE WHEN i.code in(\"WP\") THEN d.achieved ELSE 0 END) AS 'WP',\n" + 
			"SUM(CASE WHEN i.code in(\"OD\") THEN d.achieved ELSE 0 END) AS 'OD',\n" + 
			"SUM(CASE WHEN i.code in(\"RE\") THEN d.achieved ELSE 0 END) AS 'RE',\n" + 
			"SUM(CASE WHEN i.code in(\"TD\") THEN d.achieved ELSE 0 END) AS 'TD',\n" + 
			"d.timestamp AS Timestamp  \n" + 
			"FROM data d INNER JOIN indicator i ON d.indicator_id=i.id -- AND d.date BETWEEN DATE('2019-02-01') AND DATE('2019-02-03')\n" + 
			"INNER JOIN service_area sa ON d.service_area_id=sa.id -- AND sa.code IN(\"HTS_POS\")\n" + 
			"INNER JOIN program_area pa ON sa.program_area_id=pa.id \n" + 
			"INNER JOIN counsellor c ON d.counsellor_id=c.id -- AND c.id IN(2) \n" + 
			"INNER JOIN person_name pn ON c.id=pn.id \n" + 
			"INNER JOIN facility f ON d.facility_id=f.id -- and f.id in(2)\n" + 
			"INNER JOIN sub_county sc ON f.sub_county_id=sc.id \n" + 
			"INNER JOIN management_area ma ON sc.mgt_id=ma.id\n" + 
			"group by d.counsellor_id,d.date, d.service_area_id-- ma.id";
	
	
	return selectSms;
}

public String getResultsByDates(String datefrom,String dateto) {
	System.out.println("AGIK AT THE dateFrom::::"+datefrom);
	System.out.println("AGIK AT THE dateTo::::"+dateto);
	
	//String selectSms = "SELECT r.sender, r.tested,r.positive,r.negative,r.unknown,f.name FROM sms_results  r "
		//	+ "JOIN facilities f ON (r.sender = f.provider_number) ORDER BY r.id DESC LIMIT 1";
	
	String selectSms = "SELECT ma.name AS Region,sc.name AS SubCounty,f.name AS FacilityName,f.mflcode AS MFLCode,\n" + 
			"c.code AS CounsellorCode, concat_ws(\" \", pn.given_name,pn.middle_name,pn.sur_name) as CounsellorName, \n" + 
			"pa.name AS ProgramArea,sa.code AS ServiceAreaCode,sa.fullname AS ServiceAreaName,  \n" + 
			"d.phone AS PhoneNumber,d.date AS Date,\n" + 
			"SUM(CASE WHEN i.code in(\"IND\") THEN d.achieved ELSE 0 END) AS 'IND',\n" + 
			"SUM(CASE WHEN i.code in(\"INDSC\") THEN d.achieved ELSE 0 END) AS 'INDSC',\n" + 
			"SUM(CASE WHEN i.code in(\"CT\") THEN d.achieved ELSE 0 END) AS 'CT',\n" + 
			"SUM(CASE WHEN i.code in(\"CTEL\") THEN d.achieved ELSE 0 END) AS 'CTEL',\n" + 
			"SUM(CASE WHEN i.code in(\"T\") THEN d.achieved ELSE 0 END) AS 'T',\n" + 
			"SUM(CASE WHEN i.code in(\"P\") THEN d.achieved ELSE 0 END) AS 'P',\n" + 
			"SUM(CASE WHEN i.code in(\"L\") THEN d.achieved ELSE 0 END) AS 'L',\n" + 
			"SUM(CASE WHEN i.code in(\"NR\") THEN d.achieved ELSE 0 END) AS 'NR',\n" + 
			"SUM(CASE WHEN i.code in(\"AH\") THEN d.achieved ELSE 0 END) AS 'AH',\n" + 
			"SUM(CASE WHEN i.code in(\"KP\") THEN d.achieved ELSE 0 END) AS 'KP',\n" + 
			"SUM(CASE WHEN i.code in(\"WL\") THEN d.achieved ELSE 0 END) AS 'WL',\n" + 
			"SUM(CASE WHEN i.code in(\"DL\") THEN d.achieved ELSE 0 END) AS 'DL',\n" + 
			"SUM(CASE WHEN i.code in(\"DC\") THEN d.achieved ELSE 0 END) AS 'DC',\n" + 
			"SUM(CASE WHEN i.code in(\"UC\") THEN d.achieved ELSE 0 END) AS 'UC',\n" + 
			"SUM(CASE WHEN i.code in(\"MT\") THEN d.achieved ELSE 0 END) AS 'MT',\n" + 
			"SUM(CASE WHEN i.code in(\"WP\") THEN d.achieved ELSE 0 END) AS 'WP',\n" + 
			"SUM(CASE WHEN i.code in(\"OD\") THEN d.achieved ELSE 0 END) AS 'OD',\n" + 
			"SUM(CASE WHEN i.code in(\"RE\") THEN d.achieved ELSE 0 END) AS 'RE',\n" + 
			"SUM(CASE WHEN i.code in(\"TD\") THEN d.achieved ELSE 0 END) AS 'TD',\n" + 
			"d.timestamp AS Timestamp  \n" + 
			"FROM data d INNER JOIN indicator i ON d.indicator_id=i.id   AND d.date BETWEEN DATE('"+datefrom+"') AND DATE('"+dateto+"')\n" + 
			"INNER JOIN service_area sa ON d.service_area_id=sa.id -- AND sa.code IN(\"HTS_POS\")\n" + 
			"INNER JOIN program_area pa ON sa.program_area_id=pa.id \n" + 
			"INNER JOIN counsellor c ON d.counsellor_id=c.id -- AND c.id IN(2) \n" + 
			"INNER JOIN person_name pn ON c.id=pn.id \n" + 
			"INNER JOIN facility f ON d.facility_id=f.id -- and f.id in(2)\n" + 
			"INNER JOIN sub_county sc ON f.sub_county_id=sc.id \n" + 
			"INNER JOIN management_area ma ON sc.mgt_id=ma.id\n" + 
			"group by d.date,ma.id";
	
	
	return selectSms;
}


public String filterByServiceArea(int id) {
	
	//String selectSms = "SELECT r.sender, r.tested,r.positive,r.negative,r.unknown,f.name FROM sms_results  r "
		//	+ "JOIN facilities f ON (r.sender = f.provider_number) ORDER BY r.id DESC LIMIT 1";
	
	String selectSms = "SELECT ma.name AS Region,sc.name AS SubCounty,f.name AS FacilityName,f.mflcode AS MFLCode,\n" + 
			"c.code AS CounsellorCode, concat_ws(\" \", pn.given_name,pn.middle_name,pn.sur_name) as CounsellorName, \n" + 
			"pa.name AS ProgramArea,sa.code AS ServiceAreaCode,sa.fullname AS ServiceAreaName,  \n" + 
			"d.phone AS PhoneNumber,d.date AS Date,\n" + 
			"SUM(CASE WHEN i.code in(\"IND\") THEN d.achieved ELSE 0 END) AS 'IND',\n" + 
			"SUM(CASE WHEN i.code in(\"INDSC\") THEN d.achieved ELSE 0 END) AS 'INDSC',\n" + 
			"SUM(CASE WHEN i.code in(\"CT\") THEN d.achieved ELSE 0 END) AS 'CT',\n" + 
			"SUM(CASE WHEN i.code in(\"CTEL\") THEN d.achieved ELSE 0 END) AS 'CTEL',\n" + 
			"SUM(CASE WHEN i.code in(\"T\") THEN d.achieved ELSE 0 END) AS 'T',\n" + 
			"SUM(CASE WHEN i.code in(\"P\") THEN d.achieved ELSE 0 END) AS 'P',\n" + 
			"SUM(CASE WHEN i.code in(\"L\") THEN d.achieved ELSE 0 END) AS 'L',\n" + 
			"SUM(CASE WHEN i.code in(\"NR\") THEN d.achieved ELSE 0 END) AS 'NR',\n" + 
			"SUM(CASE WHEN i.code in(\"AH\") THEN d.achieved ELSE 0 END) AS 'AH',\n" + 
			"SUM(CASE WHEN i.code in(\"KP\") THEN d.achieved ELSE 0 END) AS 'KP',\n" + 
			"SUM(CASE WHEN i.code in(\"WL\") THEN d.achieved ELSE 0 END) AS 'WL',\n" + 
			"SUM(CASE WHEN i.code in(\"DL\") THEN d.achieved ELSE 0 END) AS 'DL',\n" + 
			"SUM(CASE WHEN i.code in(\"DC\") THEN d.achieved ELSE 0 END) AS 'DC',\n" + 
			"SUM(CASE WHEN i.code in(\"UC\") THEN d.achieved ELSE 0 END) AS 'UC',\n" + 
			"SUM(CASE WHEN i.code in(\"MT\") THEN d.achieved ELSE 0 END) AS 'MT',\n" + 
			"SUM(CASE WHEN i.code in(\"WP\") THEN d.achieved ELSE 0 END) AS 'WP',\n" + 
			"SUM(CASE WHEN i.code in(\"OD\") THEN d.achieved ELSE 0 END) AS 'OD',\n" + 
			"SUM(CASE WHEN i.code in(\"RE\") THEN d.achieved ELSE 0 END) AS 'RE',\n" + 
			"SUM(CASE WHEN i.code in(\"TD\") THEN d.achieved ELSE 0 END) AS 'TD',\n" + 
			"d.timestamp AS Timestamp  \n" + 
			"FROM data d INNER JOIN indicator i ON d.indicator_id=i.id -- AND d.date BETWEEN DATE('2019-02-01') AND DATE('2019-02-03')\n" + 
			"INNER JOIN service_area sa ON d.service_area_id=sa.id  AND sa.id IN("+id+")\n" + 
			"INNER JOIN program_area pa ON sa.program_area_id=pa.id \n" + 
			"INNER JOIN counsellor c ON d.counsellor_id=c.id -- AND c.id IN(2) \n" + 
			"INNER JOIN person_name pn ON c.id=pn.id \n" + 
			"INNER JOIN facility f ON d.facility_id=f.id -- and f.id in(2)\n" + 
			"INNER JOIN sub_county sc ON f.sub_county_id=sc.id \n" + 
			"INNER JOIN management_area ma ON sc.mgt_id=ma.id\n" + 
			"group by d.date,ma.id";
	
	
	return selectSms;
}


public String insertData() { 
	
	String sqlInsert = "INSERT INTO data(phone, date,facility_id,counsellor_id,service_area_id,indicator_id,achieved) VALUES (?,?,?,?,?,?,?)";;
	
	return sqlInsert;
}

public String getIndicators() {
	
	
	String selectIndicators = "SELECT id, code FROM  indicator WHERE is_active = 1";
	
	
	
	return selectIndicators;
}

public String getServiceAreaCode(String code) {
	
	String selectSql ="SELECT id ,code from service_area WHERE code ='"+code+"'" ;	
	return selectSql;
}

public String getcounsellorCode(String code) {
	String selectSql ="SELECT id,code,facility_id from counsellor WHERE  code ='"+code+"'" ;
	
	return selectSql;
}
public String checkUpdateInsert(String smsDate, int facility_id, int counsellor_id, int service_area_id,int indicator_id) {
	
	
	
	String selectSql ="SELECT COUNT(*) AS id FROM data  WHERE  date ='"+smsDate+"' AND facility_id = "+facility_id+" "
			+ " AND  counsellor_id ="+counsellor_id+" AND  service_area_id ="+service_area_id+" AND indicator_id="+indicator_id+" " ;
	
	return selectSql;
}

public String updateAchieved(String smsDate, int facility_id, int counsellor_id, int service_area_id,int indicator_id, int data) {
	
	String selectSql ="UPDATE data SET  achieved="+data+"  WHERE  date ='"+smsDate+"' AND facility_id = "+facility_id+" "
			+ " AND  counsellor_id ="+counsellor_id+" AND  service_area_id ="+service_area_id+" AND indicator_id ="+indicator_id+"" ;
	
	return selectSql;
}
public String getFaciltyFMLCode(String fml_code) {
	
	String selectSql ="SELECT id ,mflcode from facility WHERE mflcode ='"+fml_code+"'" ;	
	return selectSql;
	
}

public String loginUser(String username,String password) {
	String loginQuery = "SELECT concat_ws(\" \", pn.given_name,pn.middle_name,pn.sur_name) as Name, u.username as username,ut.id AS User_Type_ID,ut.name AS User_Type , COUNT(u.username) as loginstate\n" + 
			" from user u\n" + 
			" INNER JOIN person_name pn ON u.person_name_id=pn.id\n" + 
			" INNER JOIN user_type ut ON u.user_type_id=ut.id\n" + 
			" WHERE  u.username ='"+username+"' and u.password='"+password+"'";
	return loginQuery;

}
}
