import socket
from termcolor import colored
import sys
import PlacementTest.gchat as server
import ast

# def callServer():
#     host = "127.0.0.1"
#     port = 12345
#
#     mySocket = socket.socket()
#     mySocket.bind((host, port))
#
#     mySocket.listen(1)
#     conn, addr = mySocket.accept()
#     print("Connection from: " + str(addr))
#     welcomMsg = "Hello Welcome from Server, IP is : "+host
#     conn.send(welcomMsg.encode())
#     while True:
#         data = conn.recv(1024).decode()
#         if not data:
#             break
#         print("from connected  user: " + str(data)+"  by"+str(addr))
#
#         data = input("Send Message From Server : ")
#         print("sending: " + str(data))
#         conn.send(data.encode())
#
#     conn.close()

def callClient(serverIP):
    host = serverIP
    port = 12345

    mySocket = socket.socket()
    mySocket.connect((host, port))
    message = ''

    data = mySocket.recv(1024).decode()
    applicationListFromServer=data
    #print( applicationListFromServer)
    step = 1
    while message != 'q':
        print("STEP = " + str(step))
        #data = mySocket.recv(1024).decode()
        #message = input("send message to server -> ")
        #print('Received from server: ' + data)

        if step == 1:
            print("In Step 1")
            print(applicationListFromServer)
            message = input("Enter 1 or q : ")
            print("MESSAGE = " + message)
            if message == '1':
                mySocket.send('list of schema'.encode())
                data = mySocket.recv(2048).decode()
                #print('Received from server: ' + data)
                schListFromServ = dict()
                tempschfrominput = {}
                schListFromServ = ast.literal_eval(data)
                intOpt =1
                for  schemaNames in schListFromServ:
                     print("Enter "+str(intOpt)+" for getting list of  violations for schema : "+schemaNames)
                     tempschfrominput.__setitem__(intOpt,schemaNames)
                     intOpt = intOpt+1
                message = input()
                print("You selected schema : ",tempschfrominput.get(int(message)))
                selectedSchema=tempschfrominput.get(int(message))
                mySocket.send(str(selectedSchema).encode())
                data = mySocket.recv(2048).decode()
                dataaslist = str(data).split(sep=',')
                print("Below are the violations added in this run : ")
                for eachviolation in dataaslist:
                     print(colored(str(str(eachviolation).rsplit(':', 1)).replace("{","").replace("'","").replace("}",""),'red'))
                print("Enter 1 to see deleted violations \n Enter 0 to to check data for another application \n Enter q to exit : ")
                inputnumber = input()
                print("You Entered : "+inputnumber," Schema : "+str(selectedSchema))
                if inputnumber == '1':
                    #mySocket.send(selectedSchema.encode())
                    data = mySocket.recv(2048).decode()
                    dataaslist = str(data).split(sep=',')
                    print("Below are the violations corrected in this run : ")
                    for eachviolation in dataaslist:
                        print(colored(
                            str(str(eachviolation).rsplit(':', 1)).replace("{", "").replace("'", "").replace("}", ""),
                            'green'))

                else:
                    print("Closing client connection")
                    exit(0)

            else:
                print("Closing client connection")
                exit(0)



    mySocket.close()

if __name__ == '__main__':
    #arguments = sys.argv[1:]
    # if len(arguments) == 1:
    #     print("Calling Client")
    #     serverIP = arguments.__getitem__(0)
    #     callClient(serverIP)
    # else:
    print("Calling Server")
    callClient('127.0.0.1')

