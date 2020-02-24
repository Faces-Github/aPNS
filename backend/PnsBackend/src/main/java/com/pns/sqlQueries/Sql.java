package com.pns.sqlQueries;

public class Sql {

	public String contactDetailsSql(String patientId) {
		
		 String contactDetailsSql = " select patientId, identifier,patientName, indexCCCNumber, cccNumber,clientType,consentedProvideContacts,declinedProvideContacts,\n" + 
		 		"maritalStatus,relationshipIndexClient ,occupation,livingWihIndex ,physicalHurt,threatenedToHurt,sexuallyUncomfortable,testingEligibility,hivStatus,\n" + 
		 		"pnsApproach, contactType,tracingAttempt,tracingOutcome,consentedTesting,dateBookedForTesting,consentedTesting2,\n" + 
		 		"declineReason,testingOutcome,linkToCare,dateLinked,facilityLinked,remarks\n" + 
		 		"\n" + 
		 		"\n" + 
		 		" from \n" + 
		 		"(select \n" + 
		 		"pn.person_id AS patientId , pi.identifier AS identifier ,\n" + 
		 		"concat_ws(\" \",pn.given_name,pn.middle_name,pn.family_name, pn.family_name_prefix ) as patientName,\n" + 
		 		"min(IF(pab.value REGEXP '^[0-9]{5}$' ,pab.value,null)) AS indexCCCNumber , -- get ccc_number which is only numeric using ReGEX\n" + 
		 		"COALESCE(min(IF(pab.value='INDEX_CLIENT',pab.value,null)),min(IF(pab.value='INDEX_CONTACT',pab.value,null))) AS clientType\n" + 
		 		" from person_name pn \n" + 
		 		" inner join patient_identifier pi on pi.patient_id = pn.person_id and pi.voided=0\n" + 
		 		" inner join person_attribute pab on pab.person_id = pn.person_id and pab.voided= 0 and person_attribute_type_id in(1,22)\n" + 
		 		" \n" + 
		 		" group by pn.person_id order by pn.person_id desc ) x \n" + 
		 		" left outer join (\n" + 
		 		" select e.patient_id,\n" + 
		 		"       MAX(IF(o.concept_id=8453,o.value_text,NULL)) AS cccNumber,	   \n" + 
		 		"       --  contact listing\n" + 
		 		"       MAX(IF(o.concept_id = 8413,cn.name,NULL)) AS consentedProvideContacts,\n" + 
		 		"       MAX(IF(o.concept_id = 8435,cn.name,NULL)) AS declinedProvideContacts,\n" + 
		 		"       MAX(IF(o.concept_id = 1054,cn.name,NULL)) AS maritalStatus,\n" + 
		 		"	   MAX(IF(o.concept_id = 8418,cn.name,NULL)) AS relationshipIndexClient,\n" + 
		 		"       MAX(IF(o.concept_id = 1364,cn.name,NULL)) AS occupation, \n" + 
		 		"       MAX(IF(o.concept_id = 8420,cn.name,NULL)) AS livingWihIndex,\n" + 
		 		"       MAX(IF(o.concept_id = 8434,cn.name,NULL)) AS physicalHurt,\n" + 
		 		"       MAX(IF(o.concept_id = 8433,cn.name,NULL)) AS threatenedToHurt,\n" + 
		 		"       MAX(IF(o.concept_id = 8432,cn.name,NULL)) AS sexuallyUncomfortable,\n" + 
		 		"       MAX(IF(o.concept_id = 8431,cn.name,NULL)) AS testingEligibility,\n" + 
		 		"       MAX(IF(o.concept_id = 8421,cn.name,'Missing')) AS hivStatus,\n" + 
		 		"       MAX(IF(o.concept_id = 8425,cn.name,NULL)) AS pnsApproach,\n" + 
		 		"       \n" + 
		 		"       -- contact tracing and outcome\n" + 
		 		"        \n" + 
		 		"       MAX(IF(o.concept_id = 8408,cn.name,NULL)) AS contactType,\n" + 
		 		"       MAX(IF(o.concept_id = 8412,cn.name,NULL)) AS tracingAttempt,\n" + 
		 		"       MAX(IF(o.concept_id = 8429,cn.name,NULL)) AS tracingOutcome,\n" + 
		 		"       MAX(IF(o.concept_id = 8413,cn.name,NULL)) AS consentedTesting,\n" + 
		 		"       MAX(IF(o.concept_id = 8055,DATE(o.value_datetime),NULL)) AS dateBookedForTesting,\n" + 
		 		"       \n" + 
		 		"       -- Linkage and linkage\n" + 
		 		"       MAX(IF(o.concept_id = 8439,cn.name,NULL)) AS consentedTesting2,\n" + 
		 		"       MAX(IF(o.concept_id = 8435,o.value_text,NULL)) AS declineReason,\n" + 
		 		"       MAX(IF(o.concept_id = 8440,cn.name,NULL)) AS testingOutcome,\n" + 
		 		"       MAX(IF(o.concept_id = 8439,cn.name,NULL)) AS linkToCare,\n" + 
		 		"       MAX(IF(o.concept_id = 8444,DATE(o.value_datetime),NULL)) AS dateLinked,\n" + 
		 		"       MAX(IF(o.concept_id = 8419,o.value_text,NULL)) AS facilityLinked,\n" + 
		 		"	   MAX(IF(o.concept_id = 7734,o.value_text,NULL)) AS remarks\n" + 
		 		"       \n" + 
		 		"	      \n" + 
		 		"from\n" + 
		 		"encounter e\n" + 
		 		"left outer join patient pa on pa.patient_id = e.patient_id and pa.voided = 0\n" + 
		 		"left outer join encounter_provider enpro on enpro.encounter_id = e.encounter_id\n" + 
		 		"left outer join provider pro on pro.provider_id = enpro.provider_id\n" + 
		 		"join location l on l.location_id = e.location_id\n" + 
		 		"left outer join obs o on o.encounter_id=e.encounter_id and o.voided=0 -- and  o.concept_id in (8453)\n" + 
		 		"left outer join concept_name cn on cn.concept_id=o.value_coded and cn.concept_name_type='FULLY_SPECIFIED'\n" + 
		 		"where e.encounter_type in (76,75,73,71) and e.voided = 0  AND e.patient_id != 416932 --  and e.location_id = :location and e.encounter_datetime between :startDate and :endDate\n" + 
		 		"group by e.patient_id\n" + 
		 		"order by e.patient_id \n" + 
		 		" \n" + 
		 		" )y on y.patient_id = x.patientId  where   x.indexCCCNumber >= 0 and x.patientId = "+patientId+" ; ";
		 
		 return contactDetailsSql;
				
	}
	
	public String contactIndexesl() {
		
		 String query = "select e.patient_id AS patientId, pi.identifier AS identifier,\n" + 
		 		"\n" + 
		 		"       MAX(IF(o.concept_id=8453,o.value_text,NULL)) AS cccNumber,\n" + 
		 		"       MAX(IF(pi.identifier_type=9,pi.identifier,NULL)) AS UniquePatientNumber,\n" + 
		 		"	   \n" + 
		 		"       concat_ws(\" \",pnm.given_name,pnm.middle_name,pnm.family_name) as patientName,\n" + 
		 		"       --  contact listing\n" + 
		 		"       MAX(IF(o.concept_id = 8413,cn.name,NULL)) AS consentedProvideContacts,\n" + 
		 		"       MAX(IF(o.concept_id = 8435,cn.name,NULL)) AS declinedProvideContacts,\n" + 
		 		"       MAX(IF(o.concept_id = 1054,cn.name,NULL)) AS maritalStatus,\n" + 
		 		"	   MAX(IF(o.concept_id = 8418,cn.name,NULL)) AS relationshipIndexClient,\n" + 
		 		"       MAX(IF(o.concept_id = 1364,cn.name,NULL)) AS occupation, \n" + 
		 		"       MAX(IF(o.concept_id = 8420,cn.name,NULL)) AS livingWihIndex,\n" + 
		 		"       MAX(IF(o.concept_id = 8434,cn.name,NULL)) AS physicalHurt,\n" + 
		 		"       MAX(IF(o.concept_id = 8433,cn.name,NULL)) AS threatenedToHurt,\n" + 
		 		"       MAX(IF(o.concept_id = 8432,cn.name,NULL)) AS sexuallyUncomfortable,\n" + 
		 		"       MAX(IF(o.concept_id = 8431,cn.name,NULL)) AS testingEligibility,\n" + 
		 		"       MAX(IF(o.concept_id = 8421,cn.name,'Missing')) AS hivStatus,\n" + 
		 		"       MAX(IF(o.concept_id = 8425,cn.name,NULL)) AS pnsApproach,\n" + 
		 		"       \n" + 
		 		"       -- contact tracing and outcome\n" + 
		 		"       \n" + 
		 		"       MAX(IF(o.concept_id = 8408,cn.name,NULL)) AS contactType,\n" + 
		 		"       MAX(IF(o.concept_id = 8412,cn.name,NULL)) AS tracingAttempt,\n" + 
		 		"       MAX(IF(o.concept_id = 8429,cn.name,NULL)) AS tracingOutcome,\n" + 
		 		"       MAX(IF(o.concept_id = 8413,cn.name,NULL)) AS consentedTesting,\n" + 
		 		"       MAX(IF(o.concept_id = 8055,DATE(o.value_datetime),NULL)) AS dateBookedForTesting,\n" + 
		 		"       \n" + 
		 		"       -- Linkage and linkage\n" + 
		 		"       MAX(IF(o.concept_id = 8439,cn.name,NULL)) AS consentedTesting2,\n" + 
		 		"       MAX(IF(o.concept_id = 8435,cn.name,NULL)) AS declineReason,\n" + 
		 		"       MAX(IF(o.concept_id = 8440,cn.name,NULL)) AS testingOutcome,\n" + 
		 		"       MAX(IF(o.concept_id = 8439,cn.name,NULL)) AS linkToCare,\n" + 
		 		"       MAX(IF(o.concept_id = 8444,DATE(o.value_datetime),NULL)) AS dateLinked,\n" + 
		 		"       MAX(IF(o.concept_id = 8419,cn.name,NULL)) AS facilityLinked,\n" + 
		 		"	   MAX(IF(o.concept_id = 7734,cn.name,NULL)) AS remarks\n" + 
		 		"       \n" + 
		 		"	      \n" + 
		 		"from\n" + 
		 		"encounter e\n" + 
		 		"join patient_identifier pi on pi.patient_id = e.patient_id and pi.voided=0\n" + 
		 		"join person p on p.person_id = e.patient_id and p.voided=0\n" + 
		 		"JOIN users u ON u.user_id=e.creator AND u.retired = 0\n" + 
		 		"join patient pa on pa.patient_id = e.patient_id and pa.voided = 0\n" + 
		 		"left outer join encounter_provider enpro on enpro.encounter_id = e.encounter_id\n" + 
		 		"left outer join provider pro on pro.provider_id = enpro.provider_id\n" + 
		 		"JOIN person_name pn ON pn.person_id=u.person_id AND pn.voided = 0\n" + 
		 		"join location l on l.location_id = e.location_id\n" + 
		 		"left outer join obs o on o.encounter_id=e.encounter_id and o.voided=0 -- and  o.concept_id in (8453)\n" + 
		 		"JOIN person_name pnm on pnm.person_id = o.person_id\n" + 
		 		"LEFT OUTER JOIN person_name pnp on pnp.person_id = pro.person_id\n" + 
		 		"left outer join concept_name cn on cn.concept_id=o.value_coded and cn.concept_name_type='FULLY_SPECIFIED'\n" + 
		 		"where encounter_type in (76,75,73,71,45,44,53) and e.voided = 0   AND e.patient_id != 416932 --  and e.location_id = :location and e.encounter_datetime between :startDate and :endDate\n" + 
		 		"group by e.patient_id\n" + 
		 		"order by patientId ;";
		 
		 return query;
				
	}
	
	public String contactIndexesPnsTree() {
		
		 String query = " select patientId, identifier,patientName, indexCCCNumber, cccNumber,clientType,consentedProvideContacts,declinedProvideContacts,\n" + 
		 		"maritalStatus,relationshipIndexClient ,occupation,livingWihIndex ,physicalHurt,threatenedToHurt,sexuallyUncomfortable,testingEligibility,hivStatus,\n" + 
		 		"pnsApproach, contactType,tracingAttempt,tracingOutcome,consentedTesting,dateBookedForTesting,consentedTesting2,\n" + 
		 		"declineReason,testingOutcome,linkToCare,dateLinked,facilityLinked,remarks,datePersonCreated\n" + 
		 		"\n" + 
		 		"\n" + 
		 		" from \n" + 
		 		"(select \n" + 
		 		"pn.person_id AS patientId , pi.identifier AS identifier , \n" + 
		 		"concat_ws(\" \",pn.given_name,pn.middle_name,pn.family_name, pn.family_name_prefix ) as patientName,\n" + 
		 		"min(IF(pab.value REGEXP '^[0-9]{5}$' ,pab.value,null)) AS indexCCCNumber , -- get ccc_number which is only numeric using ReGEX\n" + 
		 		"COALESCE(min(IF(pab.value='INDEX_CLIENT',pab.value,null)),min(IF(pab.value='INDEX_CONTACT',pab.value,null))) AS clientType,\n" + 
		 		"DATE(pn.date_created) AS datePersonCreated\n" + 
		 		" from person_name pn \n" + 
		 		" inner join patient_identifier pi on pi.patient_id = pn.person_id and pi.voided=0\n" + 
		 		" inner join person_attribute pab on pab.person_id = pn.person_id and pab.voided= 0 and person_attribute_type_id in(1,22)\n" + 
		 		" \n" + 
		 		" group by pn.person_id order by pn.person_id desc ) x \n" + 
		 		" left outer join (\n" + 
		 		" select e.patient_id,\n" + 
		 		"       MAX(IF(o.concept_id=8453,o.value_text,NULL)) AS cccNumber,	   \n" + 
		 		"       --  contact listing\n" + 
		 		"       MAX(IF(o.concept_id = 8413,cn.name,NULL)) AS consentedProvideContacts,\n" + 
		 		"       MAX(IF(o.concept_id = 8435,cn.name,NULL)) AS declinedProvideContacts,\n" + 
		 		"       MAX(IF(o.concept_id = 1054,cn.name,NULL)) AS maritalStatus,\n" + 
		 		"	   MAX(IF(o.concept_id = 8418,cn.name,NULL)) AS relationshipIndexClient,\n" + 
		 		"       MAX(IF(o.concept_id = 1364,cn.name,NULL)) AS occupation, \n" + 
		 		"       MAX(IF(o.concept_id = 8420,cn.name,NULL)) AS livingWihIndex,\n" + 
		 		"       MAX(IF(o.concept_id = 8434,cn.name,NULL)) AS physicalHurt,\n" + 
		 		"       MAX(IF(o.concept_id = 8433,cn.name,NULL)) AS threatenedToHurt,\n" + 
		 		"       MAX(IF(o.concept_id = 8432,cn.name,NULL)) AS sexuallyUncomfortable,\n" + 
		 		"       MAX(IF(o.concept_id = 8431,cn.name,NULL)) AS testingEligibility,\n" + 
		 		"       MAX(IF(o.concept_id = 8421,cn.name,NULL)) AS hivStatus,\n" + 
		 		"       MAX(IF(o.concept_id = 8425,cn.name,NULL)) AS pnsApproach,\n" + 
		 		"       \n" + 
		 		"       -- contact tracing and outcome\n" + 
		 		"        \n" + 
		 		"       MAX(IF(o.concept_id = 8408,cn.name,NULL)) AS contactType,\n" + 
		 		"       MAX(IF(o.concept_id = 8412,cn.name,NULL)) AS tracingAttempt,\n" + 
		 		"       MAX(IF(o.concept_id = 8429,cn.name,NULL)) AS tracingOutcome,\n" + 
		 		"       MAX(IF(o.concept_id = 8413,cn.name,NULL)) AS consentedTesting,\n" + 
		 		"       MAX(IF(o.concept_id = 8055,DATE(o.value_datetime),NULL)) AS dateBookedForTesting,\n" + 
		 		"       \n" + 
		 		"       -- Linkage and linkage\n" + 
		 		"       MAX(IF(o.concept_id = 8439,cn.name,NULL)) AS consentedTesting2,\n" + 
		 		"       MAX(IF(o.concept_id = 8435,o.value_text,NULL)) AS declineReason,\n" + 
		 		"       MAX(IF(o.concept_id = 8440,cn.name,NULL)) AS testingOutcome,\n" + 
		 		"       MAX(IF(o.concept_id = 8439,cn.name,NULL)) AS linkToCare,\n" + 
		 		"       MAX(IF(o.concept_id = 8444,DATE(o.value_datetime),NULL)) AS dateLinked,\n" + 
		 		"       MAX(IF(o.concept_id = 8419,o.value_text,NULL)) AS facilityLinked,\n" + 
		 		"	   MAX(IF(o.concept_id = 7734,o.value_text,NULL)) AS remarks\n" + 
		 		"       \n" + 
		 		"	      \n" + 
		 		"from\n" + 
		 		"encounter e\n" + 
		 		"left outer join patient pa on pa.patient_id = e.patient_id and pa.voided = 0\n" + 
		 		"left outer join encounter_provider enpro on enpro.encounter_id = e.encounter_id\n" + 
		 		"left outer join provider pro on pro.provider_id = enpro.provider_id\n" + 
		 		"join location l on l.location_id = e.location_id\n" + 
		 		"left outer join obs o on o.encounter_id=e.encounter_id and o.voided=0 -- and  o.concept_id in (8453)\n" + 
		 		"left outer join concept_name cn on cn.concept_id=o.value_coded and cn.concept_name_type='FULLY_SPECIFIED'\n" + 
		 		"where e.encounter_type in (76,75,73,71) and e.voided = 0  AND e.patient_id != 416932 --  and e.location_id = :location and e.encounter_datetime between :startDate and :endDate\n" + 
		 		"group by e.patient_id\n" + 
		 		"order by e.patient_id \n" + 
		 		" \n" + 
		 		" )y on y.patient_id = x.patientId  where   x.indexCCCNumber >= 0 "
		 		+ " ;\n" + 
		 		"\n" + 
		 		" ";
		 
		 return query;
				
	}
	
	public String filterPnsTree(String cccNumber) {
		
		 
		 String filterPNS = " select patientId, identifier,patientName, indexCCCNumber, cccNumber,clientType,consentedProvideContacts,declinedProvideContacts,\n" + 
			 		"maritalStatus,relationshipIndexClient ,occupation,livingWihIndex ,physicalHurt,threatenedToHurt,sexuallyUncomfortable,testingEligibility,hivStatus,\n" + 
			 		"pnsApproach, contactType,tracingAttempt,tracingOutcome,consentedTesting,dateBookedForTesting,consentedTesting2,\n" + 
			 		"declineReason,testingOutcome,linkToCare,dateLinked,facilityLinked,remarks\n" + 
			 		"\n" + 
			 		"\n" + 
			 		" from \n" + 
			 		"(select \n" + 
			 		"pn.person_id AS patientId , pi.identifier AS identifier ,\n" + 
			 		"concat_ws(\" \",pn.given_name,pn.middle_name,pn.family_name, pn.family_name_prefix ) as patientName,\n" + 
			 		"min(IF(pab.value REGEXP '^[0-9]{5}$' ,pab.value,null)) AS indexCCCNumber , -- get ccc_number which is only numeric using ReGEX\n" + 
			 		"COALESCE(min(IF(pab.value='INDEX_CLIENT',pab.value,null)),min(IF(pab.value='INDEX_CONTACT',pab.value,null))) AS clientType\n" + 
			 		" from person_name pn \n" + 
			 		" inner join patient_identifier pi on pi.patient_id = pn.person_id and pi.voided=0\n" + 
			 		" inner join person_attribute pab on pab.person_id = pn.person_id and pab.voided= 0 and person_attribute_type_id in(1,22)\n" + 
			 		" \n" + 
			 		" group by pn.person_id order by pn.person_id desc ) x \n" + 
			 		" left outer join (\n" + 
			 		" select e.patient_id,\n" + 
			 		"       MAX(IF(o.concept_id=8453,o.value_text,NULL)) AS cccNumber,	   \n" + 
			 		"       --  contact listing\n" + 
			 		"       MAX(IF(o.concept_id = 8413,cn.name,NULL)) AS consentedProvideContacts,\n" + 
			 		"       MAX(IF(o.concept_id = 8435,cn.name,NULL)) AS declinedProvideContacts,\n" + 
			 		"       MAX(IF(o.concept_id = 1054,cn.name,NULL)) AS maritalStatus,\n" + 
			 		"	   MAX(IF(o.concept_id = 8418,cn.name,NULL)) AS relationshipIndexClient,\n" + 
			 		"       MAX(IF(o.concept_id = 1364,cn.name,NULL)) AS occupation, \n" + 
			 		"       MAX(IF(o.concept_id = 8420,cn.name,NULL)) AS livingWihIndex,\n" + 
			 		"       MAX(IF(o.concept_id = 8434,cn.name,NULL)) AS physicalHurt,\n" + 
			 		"       MAX(IF(o.concept_id = 8433,cn.name,NULL)) AS threatenedToHurt,\n" + 
			 		"       MAX(IF(o.concept_id = 8432,cn.name,NULL)) AS sexuallyUncomfortable,\n" + 
			 		"       MAX(IF(o.concept_id = 8431,cn.name,NULL)) AS testingEligibility,\n" + 
			 		"       MAX(IF(o.concept_id = 8421,cn.name,'Missing')) AS hivStatus,\n" + 
			 		"       MAX(IF(o.concept_id = 8425,cn.name,NULL)) AS pnsApproach,\n" + 
			 		"       \n" + 
			 		"       -- contact tracing and outcome\n" + 
			 		"        \n" + 
			 		"       MAX(IF(o.concept_id = 8408,cn.name,NULL)) AS contactType,\n" + 
			 		"       MAX(IF(o.concept_id = 8412,cn.name,NULL)) AS tracingAttempt,\n" + 
			 		"       MAX(IF(o.concept_id = 8429,cn.name,NULL)) AS tracingOutcome,\n" + 
			 		"       MAX(IF(o.concept_id = 8413,cn.name,NULL)) AS consentedTesting,\n" + 
			 		"       MAX(IF(o.concept_id = 8055,DATE(o.value_datetime),NULL)) AS dateBookedForTesting,\n" + 
			 		"       \n" + 
			 		"       -- Linkage and linkage\n" + 
			 		"       MAX(IF(o.concept_id = 8439,cn.name,NULL)) AS consentedTesting2,\n" + 
			 		"       MAX(IF(o.concept_id = 8435,o.value_text,NULL)) AS declineReason,\n" + 
			 		"       MAX(IF(o.concept_id = 8440,cn.name,NULL)) AS testingOutcome,\n" + 
			 		"       MAX(IF(o.concept_id = 8439,cn.name,NULL)) AS linkToCare,\n" + 
			 		"       MAX(IF(o.concept_id = 8444,DATE(o.value_datetime),NULL)) AS dateLinked,\n" + 
			 		"       MAX(IF(o.concept_id = 8419,o.value_text,NULL)) AS facilityLinked,\n" + 
			 		"	   MAX(IF(o.concept_id = 7734,o.value_text,NULL)) AS remarks\n" + 
			 		"       \n" + 
			 		"	      \n" + 
			 		"from\n" + 
			 		"encounter e\n" + 
			 		"left outer join patient pa on pa.patient_id = e.patient_id and pa.voided = 0\n" + 
			 		"left outer join encounter_provider enpro on enpro.encounter_id = e.encounter_id\n" + 
			 		"left outer join provider pro on pro.provider_id = enpro.provider_id\n" + 
			 		"join location l on l.location_id = e.location_id\n" + 
			 		"left outer join obs o on o.encounter_id=e.encounter_id and o.voided=0 -- and  o.concept_id in (8453)\n" + 
			 		"left outer join concept_name cn on cn.concept_id=o.value_coded and cn.concept_name_type='FULLY_SPECIFIED'\n" + 
			 		"where e.encounter_type in (76,75,73,71) and e.voided = 0  AND e.patient_id != 416932 --  and e.location_id = :location and e.encounter_datetime between :startDate and :endDate\n" + 
			 		"group by e.patient_id\n" + 
			 		"order by e.patient_id \n" + 
			 		" \n" + 
			 		" )y on y.patient_id = x.patientId  where   x.indexCCCNumber >= 0 and y.cccNumber = "+cccNumber+" or x.indexCCCNumber  = "+cccNumber+" ; ";
		 
		// x.indexCCCNumber >= 0  and y.cccNumber = '10101' or x.indexCCCNumber  = '10101'
		 
		 return filterPNS;
				
	}
	
	public String updateToIndexSql(String contactId, String cccNumber) {
		System.out.println("THE TEST UPDATE");
		
		String updateSql = " UPDATE person_attribute SET value = "+cccNumber+" WHERE person_id = "+contactId+" AND value = '00000';\n" + 
				" ";
		return updateSql;
	}
	
	
}
