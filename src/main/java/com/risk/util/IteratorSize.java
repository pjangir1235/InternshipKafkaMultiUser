package com.risk.util;

import java.util.Collection;

import org.springframework.stereotype.Service;

@Service
public class IteratorSize {

    public int size(Iterable<?> it) {
	  if (it instanceof Collection)
	    return ((Collection<?>)it).size();
	  int i = 0;
	  for (Object obj : it) i++;
	  return i;
	}
}
