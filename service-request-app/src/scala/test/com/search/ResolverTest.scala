package com.search

import org.scalatest.FlatSpec
import org.scalatest.Matchers

import scala.util.{Try, Success, Failure}

class ResolverTest extends FlatSpec with Matchers {
	
	it should "throw IllegalArgumentException if an empty fileSystemParameter points to an invalid directory" in {
		val resolver = new Resolver(domainKey = "domain-basic-all.xml", "")
		intercept[IllegalArgumentException] {
			resolver.resolve match {
				case Success(s) => throw new Exception()
				case Failure(e) => throw e
			}
		}
	}

	it should "throw IllegalArgumentException if an invalid file system path is passed in" in  {
		val resolver = new Resolver("domain.xml", "/some/invalid/file/path")
		intercept[IllegalArgumentException] {
			resolver.resolve match {
				case Success(s) => throw new Exception()
				case Failure(e) => throw e
			}
		}
	}
	
	"A Resolver" should "return blank when asking return a blank values when sending a blank domain key" in {
		val resolver = new Resolver(domainKey = "", fileSystemPath = "/");
		assert(resolver.resolve.get === "")
	}
	
  
  //def shouldReturnBlankWhenNoDomainKeyIsPassed() = assertEquals(new Resolver().resolve(), "")
  
  //def shouldReturnFileNameWhenPassingValidFileAndPath = null
  
   
  

}