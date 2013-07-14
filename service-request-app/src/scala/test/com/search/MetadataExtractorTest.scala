package com.search

import org.testng.Assert.assertTrue
import org.testng.annotations.Test

@Test
class MetadataExtractorTest {

	def testReturnableFields(): Unit ={
		assertTrue(parseDomainXml().returnableFields.contains("patentInventors"))
		assertTrue(parseDomainXml().returnableFields.contains("patentFirstPublicationDate"))
	}  

	def testFilters() = assertTrue(parseDomainXml().filters.contains("trialInterventionsPrimaryInRegimenNameDisplay"))
  
	def testSortableFields()  = assertTrue(parseDomainXml().sortableFields.contains("trialPhase"))
	
	private def parseDomainXml() =  new MetadataExtractor(getClass().getResource("/domain-basic-all.xml").getPath()).extract()
	
}