# mineSQL
The project is a very basic emulation of a SQL language and is written first for fun and second to learn some basics of
SQL query internals.

### usage
The user writes queries in console, and gets results or sees the logs on status of the done operation.
Queries can be in the following form

```
CREATE_TABLE table_name(
    col1 type,
    col2 type,
    col3 type,
        ...
);

DROP_TABLE table_name;
```

Supported data types are
``
INT, STRING, BOOL
``

```
INSERT_INTO table_name(col1, col2, ...) VALUES (col1val, col2val, ...);

SELECT column1, column2, ... [or *]
FROM table_name
WHERE condition1 AND condition2
GROUP_BY column1
HAVING aggregate_function(column) condition
ORDER_BY column ASC/DESC;
```

Supported WHERE conditions can be the following

``
==, !=, >, <, IS_NULL, IS_NOT_NULL
``

Supported aggregates functions are

``
MIN, MAX, SUM, AVG, COUNT, COUNT(exp)
``

### Internals
For the simplicities sake the data will be saved under the projects directory, in files emulating a very basic RDBMS 
file system. The directory name is `dataf`.

Files are representations of tables, their columnsDefinition + data.
Metadata containing files are prefixed with `tbl_metadata_` followed by the table name, 
data containing files are prefixed with `tbl_data_`

Metadata file consists of single line with the following form

```
columns list in created order in the form    
col1-type1|col2-type2|col3-type3|...
```

Data file consists of the following form, each line representing a row of data

```
col1val|col2val|col3val|...
```

If the `INSERT` operation is not consistent with the created table structure an error will result immediately.

A `SELECT` query will return an abstraction called `QueryResult` that holds an arraylist. It will print in the following form 
```
+ ------ + ------ + ------ + ------ +
| col1   | col2   | col3   | ....   |
=====================================
|col1val |col2val |col3val | ....   |
|col1val |col2val |col3val | ....   |
|col1val |col2val |col3val | ....   |
+ -- + ------ + ------ + ------ + ------ +
```



