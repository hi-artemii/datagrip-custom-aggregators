/*
 * MODE aggregator
 * Returns the most frequent value among selected cells
 */

def values = []

ROWS.each { row ->
  COLUMNS.each { column ->
    def v = row.value(column)
    if (v != null) values << v
  }
}

if (values.isEmpty()) {
  OUT.append("No values")
  return
}

// calculate frequencies
def freqMap = [:].withDefault { 0 }
values.each { v -> freqMap[v] = freqMap[v] + 1 }

def maxCount = freqMap.values().max()
def modes = freqMap.findAll { k, v -> v == maxCount }.keySet()

// in the case of 2 modes found – make a choice by sorting
def result = modes.toList().sort()[0]

OUT.append(result.toString())