package com.mycompany.a3;

import java.util.Vector;

//uses v1 7.10
public class GameObjectCollection implements ICollection {

	private Vector<GameObject> theCollection;

	public GameObjectCollection() {
		theCollection = new Vector<GameObject>();
	}

	@Override
	public void add(GameObject newObject) {
		theCollection.addElement(newObject);
	}

	public Vector<GameObject> getObjects() {
		return theCollection;
	}

	public GameObject elementAt(int objIndex) {
		return theCollection.get(objIndex);
	}

	public int getSize() {
		return theCollection.size();
	}

	@Override
	public IIterator getIterator() {
		return new GameVectorIterator();
	}

	public void remove(GameObject curObj) {
		theCollection.remove(curObj);
	}

	private class GameVectorIterator implements IIterator {

		private int currElementIndex;

		public GameVectorIterator() {
			currElementIndex = -1;
		}

		@Override
		public boolean hasNext() {
			if (theCollection.size() <= 0)
				return false;
			if (currElementIndex == theCollection.size() - 1)
				return false;
			return true;
		}

		@Override
		public Object getNext() {
			currElementIndex++;
			return (theCollection.elementAt(currElementIndex));
		}
	}
}
