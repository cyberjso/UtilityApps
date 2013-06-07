package search


class SearchRequestMetadataWrapper(val metadata: SearchRequestMetadata) {

	def toSoap(): String  ={

        def buidRequestTemplate() = {
			  "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"> "+
			  "	  <soap:Body> "+
			  " 	<ns2:search xmlns:ns2=\"http://search.v4.soap.contract.service.soaservices.stack.soa.ls.thomson.com/\" xmlns:ns3=\"http://serviceversion\"> " +
			  "			<searchInput> " +
	          "  		<applicationID>nggui#5826</applicationID>" +
	          "			<dataSet>#{datasetValue}</dataSet> " +
	          "  		<searchQuery>cancer</searchQuery> " +
	          " 		<returnFields>#{returnableFields}</returnFields> " +
	          "			<sortBy>#{sortableFields}</sortBy> " +
	          "			<sortDirection>descending</sortDirection> " +
	          "			<returnLimit>10</returnLimit> " +
	          "  		<offset>0</offset>" +
	          "			<countDatasets>nextgendrugall(breadth=\"extended\"),nextgendealall(breadth=\"basic\"),patentFamily(breadth=\"extended\"),company(breadth=\"extended\"),literature(breadth=\"basic\"),sourcesConferenceDocument(breadth=\"basic\"),trial(breadth=\"basic\",depth=\"trialRegistries::OR(1000,1003,1005,1006)\"),sourcesPressRelease(breadth=\"basic\"),eventTranscripts</countDatasets> " +
	          "		</searchInput> " +
	          "    </ns2:search> " +
	          "  </soap:Body> " +
	          "</soap:Envelope>"          
        }
	  
        buidRequestTemplate().replace("#{returnableFields}", metadata.returnableFields.mkString(",")).replace("#{sortableFields}", metadata.sortableFields.mkString(","))
	}
	
}