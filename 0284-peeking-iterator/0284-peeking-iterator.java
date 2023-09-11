// Java Iterator interface reference:
// https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html

class PeekingIterator implements Iterator<Integer> {
    int index =0;
    List<Integer> list;
	public PeekingIterator(Iterator<Integer> iterator) {
	    
      list = new ArrayList<>();

			while(iterator.hasNext()) list.add(iterator.next());
	    
	}
	
    
	public Integer peek() {

        return list.get(index);
        
	}
	
	// hasNext() and next() should behave the same as in the Iterator interface.
	// Override them if needed.
	@Override
	public Integer next() {
        
        return list.get(index++);
	}
	
	@Override
	public boolean hasNext() {
	    
        return index < list.size();
	}
}