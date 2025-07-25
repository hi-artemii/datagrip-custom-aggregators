/*
 * COUNT_UNIQUE aggregator
 * Counts unique values across selected rows/columns
 */

def values = [] as Set

ROWS.each { row ->
  COLUMNS.each { column ->
    def v = row.value(column)
    if (v != null) values.add(v)
  }
}

OUT.append(values.size().toString())