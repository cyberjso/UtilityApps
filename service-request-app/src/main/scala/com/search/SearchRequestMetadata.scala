package com.search

import java.io.File
import scala.xml.XML
import java.io.IOException
import scala.util.{Try, Success, Failure}

case class SearchRequestMetadata(val returnableFields: Seq[String], val sortableFields: Seq[String], val filters: Seq[String])

class Resolver(val domainKey: String = "", val fileSystemPath: String = "") {
	
	def resolve(): Try[String] ={
		if (domainKey.equals("")) Success("")
		
		def getFirstFileName(files: Array[java.io.File]) = if (files.size == 0)  "" else files(0).getName()

		def getFilesFrom(basePath: String): Array[File] ={
		  	if (basePath.equals("")) 	throw new IllegalArgumentException("A fileSystemPath should be passed in")
		  	if (!isValidPath(basePath)) throw new IllegalArgumentException(basePath + " is an invalid path")
		  	
		  	def isValidPath(filePath: String) =  new File(filePath).exists
		  	
		  	new File(basePath).listFiles().filter(_.getName.endsWith(".xml"))
		}
		
		Try(getFirstFileName(getFilesFrom(fileSystemPath).filter(_.getName.equals(domainKey))))
	}
	
	def getFilePath ={
			val MR_HOME_PATH = System.getenv("MR_HOME")
			
			def isMrHomeNotSetedUp() = (MR_HOME_PATH == null || MR_HOME_PATH.isEmpty())
			if (fileSystemPath.equals("") && isMrHomeNotSetedUp()) throw new IllegalStateException("MR_HOME variable is not proper seted up")
			if (fileSystemPath.equals("")) MR_HOME_PATH + "/search/domain"  else fileSystemPath
		}

} 

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
	implicit def metadataToString(metadata: SearchRequestMetadata) = new SearchRequestMetadataWrapper(metadata) 
}