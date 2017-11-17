package de.tuda.sdm.dmdb.sql.operator.exercise;

import de.tuda.sdm.dmdb.sql.operator.Operator;
import de.tuda.sdm.dmdb.sql.operator.SelectionBase;
import de.tuda.sdm.dmdb.sql.operator.UnaryOperator;
import de.tuda.sdm.dmdb.storage.AbstractRecord;
import de.tuda.sdm.dmdb.storage.types.AbstractSQLValue;

@SuppressWarnings("unused")
public class Selection extends SelectionBase {

	public Selection(Operator child, int attribute, AbstractSQLValue constant) {
		super(child, attribute, constant);
	}

	@Override
	public void open() {
		child.open();
	}

	@Override
	public AbstractRecord next() {
		AbstractRecord ar = child.next();
		do {
			if (ar != null) {
				AbstractSQLValue val = ar.getValue(attribute);
				if (val.compareTo(constant) == 0) {
					return ar;
				}
			} else {
				return null;
			}
			ar = child.next();
		} while (true);
	}

	@Override
	public void close() {
		child.close();
	}
}
