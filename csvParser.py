import csv
# ----------------------------------------------------------------------
import psycopg2
import sys

from psycopg2._psycopg import ProgrammingError


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

def createConnection():
    print("Connecting to database ....  ")
    # get a connection, if a connect cannot be made an exception will be raised here
    conn = psycopg2.connect(dbname='postgres', user='operator', host=hostname,
                            port=port, password='CastAIP')

    # conn.cursor will return a cursor object, you can use this cursor to perform queries
    cursor = conn.cursor()
    print("Connected!\n")
    return cursor, conn

def createTmpTable(cur, localSchema, connection, tempTable,row):
    tempcon = cur
    cur.execute("SELECT 1 FROM   information_schema.tables   WHERE  table_schema = '" + localSchema + "' "
                                                            "   AND    table_name = '" + tempTable + "'")
    numOfColumns = 0

    listOfColumnNames = str(row).split(sep=",")
    colNames = []
    for colName in listOfColumnNames:
        tempName = str(colName).replace("[","").replace("'","").replace("]","").replace(" ","_").replace("-","_")
        if tempName != "":
            numOfColumns = numOfColumns + 1
            colNames.append(str(tempName).lower())
            #print("ColName : "+tempName)
        else:
            break
    #print("Number of columns : "+str(numOfColumns)+" Columns : "+str(colNames))

    print("creating table  \""+tempTable+" \" ")
    createQuery = "CREATE  TABLE " + localSchema + "." + tempTable + " ( "
    tempi = 0
    for colName in colNames:
         if str(colName).lower() == "type" or str(colName).lower() == "schema":
             colName = colName+"_temp"
         createQuery = createQuery +colName +" character varying "
         if tempi < len(colNames)-1:
            createQuery = createQuery +" , "
         else:
            createQuery = createQuery+" ) "
         tempi = tempi + 1
    createQuery = createQuery +" ; ALTER TABLE " + localSchema + "."+tempTable+" OWNER TO operator;"
    print ( createQuery)
    tblCreateQuery = (""""""+str(createQuery)+"""""")
    print("NEW OBJECT : "+tblCreateQuery)
    try:
        cur.execute(tblCreateQuery)
        connection.commit()
    except ProgrammingError:
        print("IGNORE")
    cur.close()
    cur = connection.cursor()
    #cur.execute("SELECT 1 FROM   information_schema.tables   WHERE  table_schema = '" + localSchema + "' "
     #                                                                                                     "   AND    table_name = '"+tempTable+"'")
    if cur.rowcount >= 0:
       print("Table Created")
    return numOfColumns

def getRowCount(cur, localSchema):
    cur.execute("select * from " + localSchema + "."+tempTable)
    rows = cur.rowcount

    return rows

def insertData(cur, conn, localSchema,tempTable):
    with open(csvPath, 'r') as f:
        reader = csv.reader(f, delimiter=';')
        #next(reader)  # Skip the header row.
        a = []
        i = 0
        totalColumns = 0
        for row in reader:
            if totalColumns == 0:
                print("CREATING TABLE WITH ROWS : "+str(row).replace("ï»¿",""))
                cur1 = cur
                totalColumns = createTmpTable(cur1,localSchema,conn,tempTable,str(row).replace("ï»¿",""))
                print("TOTAL COLUMNS RETURNED : "+str(totalColumns))
                i = i+1
            else:
                cur , conn1 = createConnection()
                insertQuery = "INSERT INTO " + localSchema + "."+tempTable+" VALUES ("
                tempi = 0
                listOfRowValues = str(row).split(sep=",")
                for eachValue in listOfRowValues:
                    eachValue = str(eachValue).replace("[", "").replace("'", "").replace("]", "").replace("?","")
                    if tempi < totalColumns - 1:
                        insertQuery = insertQuery + "'" + str(eachValue)
                        insertQuery = insertQuery +"', "
                    else:
                        insertQuery = insertQuery+"'"+str(eachValue)+"'"
                        break
                    tempi = tempi + 1
                insertQuery = insertQuery+")"
                print("INSERT QUERY - " + insertQuery)

                cur.execute(insertQuery)
                conn1.commit()
            print("ROW NUMBER - "+str(i))
            i= i +1
    conn1.commit()
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
        #createTmpTable(cursor, localSchema, connection, tempTable)
        insertData(cursor, connection, localSchema,tempTable)


