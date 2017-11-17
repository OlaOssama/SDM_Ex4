package de.tuda.sdm.dmdb.sql.operator.exercise;

import java.util.Vector;

import de.tuda.sdm.dmdb.sql.operator.Operator;
import de.tuda.sdm.dmdb.sql.operator.ProjectionBase;
import de.tuda.sdm.dmdb.sql.operator.UnaryOperator;
import de.tuda.sdm.dmdb.storage.AbstractRecord;
import de.tuda.sdm.dmdb.storage.Record;

@SuppressWarnings("unused")
public class Projection extends ProjectionBase {

	public Projection(Operator child, Vector<Integer> attributes) {
		super(child, attributes);
	}

	@Override
	public void open() {
		child.open();
	}

	@Override
	public AbstractRecord next() {
		AbstractRecord ar = child.next();
		if (ar != null) {
			ar.keepValues(attributes);
			return ar;
		}
		return null;
	}

	@Override
	public void close() {
		child.close();
	}
}
