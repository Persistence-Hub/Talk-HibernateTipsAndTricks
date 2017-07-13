package org.thoughts.on.java.model;

import java.util.Comparator;

import org.apache.log4j.Logger;

public class SortById implements Comparator<Book> {

	Logger log = Logger.getLogger(SortById.class.getSimpleName());
	
	@Override
	public int compare(Book o1, Book o2) {
		log.info("SortById.compare");
		return o1.getId().compareTo(o2.getId());
	}

}
