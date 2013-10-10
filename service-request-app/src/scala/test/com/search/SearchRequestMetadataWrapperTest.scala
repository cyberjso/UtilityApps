package com.search

import org.testng.Assert.assertTrue
import org.testng.annotations.Test

import com.search.SearchRequestMetadata.metadataToString

@Test
class SearchRequestMetadataWrapperTest { 

	def tesReturnableFieldsTag() = 
		assertTrue(buildMockedSearchRequest().toSoap().contains("<returnFields>field1,field2</returnFields>"))

	def tesSortByTag() = 
	 	assertTrue(buildMockedSearchRequest().toSoap().contains("<sortBy>field3,field4</sortBy>"))

	def testSoapXmlHeaders() ={
		assertTrue(buildMockedSearchRequest().toSoap().contains("<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"))
		assertTrue(buildMockedSearchRequest().toSoap().contains("<soap:Body>"))
		assertTrue(buildMockedSearchRequest().toSoap().contains("<ns2:search xmlns:ns2=\"http://search.v4.soap.contract.service.soaservices.stack.soa.ls.thomson.com/\" xmlns:ns3=\"http://serviceversion\">"))
		assertTrue(buildMockedSearchRequest().toSoap().contains("</soap:Envelope>"))
		assertTrue(buildMockedSearchRequest().toSoap().contains("</soap:Body>"))
		assertTrue(buildMockedSearchRequest().toSoap().contains("</ns2:search>"))
	}
	
	def testBasicRequestStructure() = {
		assertTrue(buildMockedSearchRequest().toSoap().contains("<searchInput>"))
		assertTrue(buildMockedSearchRequest().toSoap().contains("<applicationID>nggui#5826</applicationID>"))
		assertTrue(buildMockedSearchRequest().toSoap().contains("<searchQuery>cancer</searchQuery>"))
		assertTrue(buildMockedSearchRequest().toSoap().contains("<sortDirection>descending</sortDirection>"))
		assertTrue(buildMockedSearchRequest().toSoap().contains("<returnLimit>10</returnLimit>"))
		assertTrue(buildMockedSearchRequest().toSoap().contains("<offset>0</offset>"))
		assertTrue(buildMockedSearchRequest().toSoap().contains("<countDatasets>nextgendrugall(breadth=\"extended\"),nextgendealall(breadth=\"basic\"),patentFamily(breadth=\"extended\"),company(breadth=\"extended\"),literature(breadth=\"basic\"),sourcesConferenceDocument(breadth=\"basic\"),trial(breadth=\"basic\",depth=\"trialRegistries::OR(1000,1003,1005,1006)\"),sourcesPressRelease(breadth=\"basic\"),eventTranscripts</countDatasets>"))
		assertTrue(buildMockedSearchRequest().toSoap().contains("<offset>0</offset>"))
		assertTrue(buildMockedSearchRequest().toSoap().contains("</searchInput>"))
	}
  
	private def buildMockedSearchRequest() = 
		new SearchRequestMetadata(List("field1","field2"), List("field3","field4"), List("field5","field6"))
}