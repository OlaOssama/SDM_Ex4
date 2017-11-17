package de.tuda.sdm.dmdb.sql.operator.exercise;

import java.util.Iterator;

import de.tuda.sdm.dmdb.access.AbstractTable;
import de.tuda.sdm.dmdb.sql.operator.Operator;
import de.tuda.sdm.dmdb.sql.operator.TableScanBase;
import de.tuda.sdm.dmdb.storage.AbstractRecord;

@SuppressWarnings("unused")
public class TableScan extends TableScanBase {

	public TableScan(AbstractTable table) {
		super(table);
	}

	@Override
	public void open() {
		tableIter = table.iterator();
	}

	@Override
	public AbstractRecord next() {
		if (tableIter.hasNext()) {
			return tableIter.next();
		} else {
			return null;
		}
	}

	@Override
	public void close() {
	}
}
