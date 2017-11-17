package de.tuda.sdm.dmdb.sql.operator.exercise;

import de.tuda.sdm.dmdb.sql.operator.EquiJoinBase;
import de.tuda.sdm.dmdb.sql.operator.Operator;
import de.tuda.sdm.dmdb.storage.AbstractRecord;
import de.tuda.sdm.dmdb.storage.Record;

public class EquiJoin extends EquiJoinBase {

	public EquiJoin(Operator leftChild, Operator rightChild, int leftAtt, int rightAtt) {
		super(leftChild, rightChild, leftAtt, rightAtt);
	}

	@Override
	public void open() {
		leftChild.open();
		rightChild.open();
	}

	@Override
	public AbstractRecord next() {
		AbstractRecord left = leftChild.next();
		AbstractRecord right = rightChild.next();
		if (left != null && right != null) {
			if (left.getValue(leftAtt).compareTo(right.getValue(rightAtt)) == 0) {
				AbstractRecord result = new Record(left.getValues().length + right.getValues().length);
				int i = 0;
				for (; i < left.getValues().length; i++) {
					result.setValue(i, left.getValue(i));
				}
				for (; i < right.getValues().length; i++) {
					result.setValue(i, right.getValue(i-left.getValues().length));
				}
				return result;
			}
		}
		return null;
	}

	@Override
	public void close() {
		leftChild.close();
		rightChild.close();
	}

}
