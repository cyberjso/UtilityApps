package com.search

import java.awt.Toolkit
import java.awt.datatransfer.StringSelection

object Main extends Application {
  
  override def main(args: Array[String]) {
	  if (args  == null || args.size == 0) throw new IllegalArgumentException("An xml file name is mandotatory")
	  
	  //Toolkit.getDefaultToolkit().getSystemClipboard().setContents(getStringSelection(args), getStringSelection(args));
	  println("*** The search request is in your clipboard! ***")
	  
	/*  def getStringSelection(args: Array[String]): java.awt.datatransfer.StringSelection = {
		 // new StringSelection(new MetadataExtractor(new Resolver(args(0)).resolve()).extract().toSoap());
	  }*/
  }


}