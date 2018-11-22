import psycopg2
import socket
import sys


def callServer():
    host = "127.0.0.1"
    port = 12345

    mySocket = socket.socket()
    mySocket.bind((host, port))

    mySocket.listen(1)
    conn, addr = mySocket.accept()
    print("Connection from: " + str(addr))
    welcomMsg = "Hello Welcome to CAST Chatbot... Congratulations!!! Looks like you recently completed a rescan.. \n" \
                "Enter 1 to see the list of Schema or q to close connection \n"
    listOfSchemas=updateOptionSchemaMap()
    conn.send(str(welcomMsg).encode())
    while True:
        data = conn.recv(1024).decode()
        print("DATA - " + str(data))
        if not data:
            break
        print("from connected  user: " + str(data)+"  by"+str(addr))
        print("DATA - "+str(data))
        if str(data) == 'list of schema':
            print(listOfSchemas)
            conn.send(str(listOfSchemas).encode())
            data = conn.recv(1024).decode()
            print("FROM Server: Selected Schema : " + data)
            schemaSelected = data
            addedViolationDetails=getAddedViolations(data)
            conn.send(str(addedViolationDetails).encode())
            #data = conn.recv(1024).decode()
            print("From Client - ",str(data))
            #conn, addr = mySocket.accept()
            deletedViolationDetails = getDeletedViolations(schemaSelected)
            conn.send(str(deletedViolationDetails).encode())
        elif str(data) != 'q':
            dataFromsrv = input("Send Message From Server : ")
            print("sending: " + str(dataFromsrv))
            conn.send( str(dataFromsrv).encode())
        else:
            conn.send(str(welcomMsg).encode())



    conn.close()

# def callClient(serverIP):
#     host = serverIP
#     port = 12345
#
#     mySocket = socket.socket()
#     mySocket.connect((host, port))
#
#     message = input(" Welcome message to server(press Enter key) -> ")
#
#
#
#     step = 0
#     while message != 'q':
#         print("STEP = "+str(step))
#         data = mySocket.recv(1024).decode()
#         message = input("send message to server -> ")
#         print('Received from server: ' + data)
#         if step == 0:
#             mySocket.send(message.encode())
#             data = mySocket.recv(1024).decode()
#             print('Received from server: ' + data)
#             step = step+1
#         if step == 1:
#             message = input()
#             print("MESSAGE = "+str(message))
#             if message == '1':
#                 mySocket.send('list of schema'.encode())
#                 data = mySocket.recv(2048).decode()
#                 print('Received from server: ' + data)
#             else:
#                 print("Closing client connection")
#                 exit(0)
#             step = step+1
#
#
#     mySocket.close()

def getAddedViolations(selectedSchema):
    print("Collecting data for : "+selectedSchema)
    cursor, conn = createConnection()
    queryText = "set search_path = "+selectedSchema+";" \
                "select distinct a.diag_name,count(distinct a.object_id) from csv_violation_statuses a,csv_violation_statuses b"\
                " where a.snaphot_id = (select max(snapshot_id) from dss_snapshots) and "\
                " b.snaphot_id = (select max(snapshot_id) from dss_snapshots where snapshot_id < a.snaphot_id ) and "\
                " a.snaphot_id <> b.snaphot_id and "\
                " a.violation_status ='Added'  "\
                " group by a.diag_name"\
                " order by a.diag_name;"
    cursor.execute(queryText)
    result = cursor.fetchall()
    tempdict = {}
    for eachSchema in result:
       countsplit=0
       splitk=''
       splitv=''
       splitOutput=str(eachSchema).replace("(","").replace(")","").split(sep="',")
       for split in splitOutput:
           if countsplit ==0:
             splitk = split.replace("(","").replace("'","").replace(",","").replace(")","")
             #print("spliktk = ",splitk)
             countsplit = 1
           elif countsplit == 1:
               splitv = split.replace("(", "").replace("'", "").replace(",", "").replace(")", "").strip()
               #print("splitv = ", splitv)
       tempdict[splitk] = splitv
    #print("TEMP DICT - "+str(tempdict))
    return tempdict

def getDeletedViolations(selectedSchema):
    print("Collecting data for : "+selectedSchema)
    cursor, conn = createConnection()
    queryText = "set search_path = "+selectedSchema+";" \
                "select distinct a.diag_name,count(distinct a.object_id) from csv_violation_statuses a,csv_violation_statuses b"\
                " where a.snaphot_id = (select max(snapshot_id) from dss_snapshots) and "\
                " b.snaphot_id = (select max(snapshot_id) from dss_snapshots where snapshot_id < a.snaphot_id ) and "\
                " a.snaphot_id <> b.snaphot_id and "\
                " a.violation_status ='Deleted'  "\
                " group by a.diag_name"\
                " order by a.diag_name;"
    cursor.execute(queryText)
    result = cursor.fetchall()
    tempdict = {}
    for eachSchema in result:
       countsplit=0
       splitk=''
       splitv=''
       splitOutput=str(eachSchema).replace("(","").replace(")","").split(sep="',")
       for split in splitOutput:
           if countsplit ==0:
             splitk = split.replace("(","").replace("'","").replace(",","").replace(")","")
             #print("spliktk = ",splitk)
             countsplit = 1
           elif countsplit == 1:
               splitv = split.replace("(", "").replace("'", "").replace(",", "").replace(")", "").strip()
               #print("splitv = ", splitv)
       tempdict[splitk] = splitv
    #print("TEMP DICT - "+str(tempdict))
    return tempdict






def createConnection():
        # get a connection, if a connect cannot be made an exception will be raised here
        conn = psycopg2.connect(dbname='postgres', user='operator', host='localhost',
                                    port=5432, password='CastAIP')

            # conn.cursor will return a cursor object, you can use this cursor to perform queries
        cursor = conn.cursor()

        return cursor,conn


def getSchemaNames():
    cursor,conn = createConnection()
    selectQuery="select distinct table_schema from " \
                "information_schema.tables where table_schema like '%_central';"
    cursor.execute(selectQuery)
    rows = cursor.fetchall()
    # for eachSchema in rows:
    #     print(str(eachSchema).replace("(","").replace("'","").replace(",","").replace(")",""))
    return  rows



def updateOptionSchemaMap():
    rows = getSchemaNames()
    optionSch={}
    option=1
    listOfSchemas = []
    for eachSchema in rows:
        optionSch.__setitem__(option,str(eachSchema).replace("(","").replace("'","").replace(",","").replace(")",""))
        option = option+1
    for opt, schemaNames in optionSch.items():
        #print("Enter "+str(opt)+" for querying schema : "+schemaNames)
        listOfSchemas.append(schemaNames)
    return listOfSchemas



if __name__ == '__main__':
    arguments = sys.argv[1:]
    if len(arguments) == 1:
        print("Calling Client")
        serverIP = arguments.__getitem__(0)
        #callClient(serverIP)
    else:
        print("Calling Server")
        callServer()