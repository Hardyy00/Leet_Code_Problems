// Java Iterator interface reference:
// https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html

class PeekingIterator implements Iterator<Integer> {

	int peek;
	Iterator<Integer> it ;
	public PeekingIterator(Iterator<Integer> iterator) {

		this.it = iterator;

		if(it.hasNext()){
			peek = it.next();
		}else {

			peek = -1;
		}
	    
	}
	
  // Returns the next element in the iteration without advancing the iterator.
	public Integer peek() {

		return peek;
        
	}
	
	// hasNext() and next() should behave the same as in the Iterator interface.
	// Override them if needed.
	@Override
	public Integer next() {

		int save = peek;

		if(it.hasNext()){
			peek = it.next();
		}else{
			peek = -1;
		}

		return save;
	    
	}
	
	@Override
	public boolean hasNext() {

		return peek !=-1;
	}
}