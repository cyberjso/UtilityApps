package com.search

import scala.xml.XML

case class SearchRequestMetadata(val returnableFields: Seq[String], val sortableFields: Seq[String], val filters: Seq[String])

class MetadataExtractor(val fileDomainPath: String) {
	val domainFile = XML.loadFile(fileDomainPath)

	def extract(): SearchRequestMetadata ={
	  	
		def extractReturnableFields() = 
			(domainFile \\ "field").filter(s => (s \\ "returnable").exists(t => t.text.equals("true"))).map(field => (field \ "propertyDomainName").text)		  
			
		def extractFilters() = 
			(domainFile \\ "field").filter(s => (s \\ "fastNavigator").exists(t => t.child.length > 0)).map(field => (field \ "propertyDomainName").text)		  
		
		def extractSortableFields() =
		  (domainFile \\ "field").filter(s => (s \\ "sortable").exists(t => t.text.equals("true"))).map(field => (field \ "propertyDomainName").text)
		  
		new  SearchRequestMetadata(extractReturnableFields(), extractSortableFields(), extractFilters())
	}
	
}

object SearchRequestMetadata {
	implicit def metadataTopString(metadata: SearchRequestMetadata) = new SearchRequestMetadataWrapper(metadata) 
}