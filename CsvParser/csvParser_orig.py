import csv
# ----------------------------------------------------------------------
import psycopg2
import sys


def csv_dict_reader(file_obj):
    """
    Read a CSV file using csv.DictReader
    """
    reader = csv.DictReader(file_obj, delimiter=';')
    i = 1
    for line in reader:

        if i > 2:

            print(line)
            i = i + 1
        else:
            i = i + 1


# ----------------------------------------------------------------------

def createConnection():
    print("Connecting to database ....  ")
    # get a connection, if a connect cannot be made an exception will be raised here
    conn = psycopg2.connect(dbname='postgres', user='operator', host=hostname,
                            port=port, password='CastAIP')

    # conn.cursor will return a cursor object, you can use this cursor to perform queries
    cursor = conn.cursor()
    print("Connected!\n")
    return cursor, conn


def createTmpTable(cur, localSchema, connection, tempTable):
    cur.execute("SELECT 1 FROM   information_schema.tables   WHERE  table_schema = '" + localSchema + "' "
                                                                                                      "   AND    table_name = '" + tempTable + "'")
    if (bool(cur.rowcount)):
        numOfRows = getRowCount(cur, localSchema)
        if numOfRows > 0:
            print("Temp table already has " + str(numOfRows) + " rows. Cleaning the table to insert fresh data")
            cur.execute("delete from " + localSchema + "."+tempTable)
            connection.commit()
            getRowCount(cur, localSchema)
            numOfRows = getRowCount(cur, localSchema)
            if numOfRows == 0:
                print("All the data in the table deleted ")
    else:
        print("Table doesnt exist and hence creating the temp table by name \""+tempTable+"\" ")
        createQuery = ("""
        CREATE TABLE """ + localSchema + """.""" + tempTable + """ 
 (
type_temp character varying,
  instance character varying,
  object_name character varying,
  object_type character varying,
  schema_temp character varying
)
WITH (
  OIDS=FALSE
);
ALTER TABLE """ + localSchema + """."""+tempTable+"""
  OWNER TO operator;
        """)
        # print (createQuery)
        cur.execute(createQuery)
        connection.commit()
        # cur.close()
        # cur = createConnection()
        cur.execute("SELECT 1 FROM   information_schema.tables   WHERE  table_schema = '" + localSchema + "' "
                                                                                                          "   AND    table_name = '"+tempTable+"'")
        if (bool(cur.rowcount)):
            print("Table Created")


def getRowCount(cur, localSchema):
    cur.execute("select * from " + localSchema + "."+tempTable)
    rows = cur.rowcount

    return rows


def insertData(cur, conn, localSchema):
    with open(csvPath, 'r') as f:
        reader = csv.reader(f, delimiter=';')
        next(reader)  # Skip the header row.
        a = []
        i = 0
        for row in reader:
            # i=1
            # for data in row :
            #    print("DATA - ",data)
            #    if data == None:
            #       data = ''
            #    a[i].append (data)
            #    i = i+1
            a.append(row)
            insertQuery = "INSERT INTO " + localSchema + "."+tempTable+" VALUES ('" + row.__getitem__(
                0) + "','" + row.__getitem__(1) + "','" + row.__getitem__(2) + "','" + row.__getitem__(
                3) + "','" + row.__getitem__(4) + "')"
            print("INSERT QUERY - " + insertQuery)
            cur.execute(insertQuery)
    conn.commit()
    numOfRows = getRowCount(cur, localSchema)
    print("Inserted " + str(numOfRows) + " rows !!!!!!!!!!!!!!")


def usage():
    print("SYNTAX : csvParser.exe <KBNAME> <HOSTNAME> <PORT> <CSVPATH> <TMPTABLENAME>")


if __name__ == "__main__":
    # with open("D:\\Office\\Telephonica\\Salcus\\SalCus_ClearCase_References_for_CAST_09.03.2018.csv") as f_obj:
    #     csv_dict_reader(f_obj)
    global localSchema, hostname, port, csvPath
    arguments = sys.argv[1:]
    if (len(arguments) != 5):
        usage()
    else:
        localSchema = arguments.__getitem__(0)
        hostname = arguments.__getitem__(1)
        port = arguments.__getitem__(2)
        csvPath = arguments.__getitem__(3)
        tempTable = arguments.__getitem__(4)
        cursor, connection = createConnection()
        createTmpTable(cursor, localSchema, connection, tempTable)
        insertData(cursor, connection, localSchema)


