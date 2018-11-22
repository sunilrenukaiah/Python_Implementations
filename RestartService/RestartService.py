import time
import datetime
import smtplib

import psycopg2
import sys
import os
import os.path
import subprocess
import argparse
# import paramiko
# from PyInstaller.building.api import PYZ, EXE
# from PyInstaller.building.build_main import Analysis
# from PyInstaller.building.osx import BUNDLE
#
# a = Analysis(['RestartService.py'],
#              hiddenimports=[],
#              hookspath=None)
# pyz = PYZ(a.pure)
# exe = EXE(pyz,
#           a.scripts,
#           # Static link the Visual C++ Redistributable DLLs if on Windows
#           a.binaries + [('api-ms-win-crt-runtime.dll', 'D:\\Office\\Tools\\RestartService\\api-ms-win-crt-runtime-l1-1-0.dll', 'BINARY'),
#                         ('api-ms-win-crt-runtime.dll', 'D:\\Office\\Tools\\RestartService\\api-ms-win-crt-runtime-l1-1-0.dll', 'BINARY')]
#           if sys.platform == 'win32' else a.binaries,
#           a.zipfiles,
#           a.datas ,
#           name=os.path.join('dist', 'cryptully' + ('.exe' if sys.platform == 'win32' else '')),
#           debug=False,
#           strip=None,
#           upx=True,
#           console=False,
#           icon='cryptully/images/icon.ico')
#
# # Build a .app if on OS X
# if sys.platform == 'darwin':
#    app = BUNDLE(exe,
#                 name='RestartService.app',
#                 icon=None)
#
#
import win32serviceutil
import wmi


def emailNotification(serviceStatus,receivers,sender,mailserver):
    sender = str(sender).replace('[','').replace(']','').replace('\'','')
    receivers = receivers
    message = """From: From Portal Server 
To: ROW Users <to@todomain.com>
Subject: Status of Tomcat restart scheduler Job 

"""+serviceStatus
    #message = message + serviceStatus

    try:
        smtpObj = smtplib.SMTP(mailserver)
        smtpObj.sendmail(sender, receivers, message)
        print("Successfully sent email")
    except:
        print("Error: unable to send email")

def getStatus(serviceName):
    c=wmi.WMI()
    #print("Output : ", str(c.Win32_Service(Name='Tomcat7')))
    serviceName = str(serviceName).replace('[','').replace(']','').replace('\'','')
    for service in c.Win32_Service(Name=serviceName):
              return service.State


def appDescription():
    description="\n\n*************  USAGE GUIDE ******************************\n" \
                "This utility will perform the following : \n" \
                "1. Restart windows service/s \n" \
                "2. send email notification if sender andreceiver mails are configured and smtp is enabled on the server (Optional) \n" \
                "Syntax :  RestartService.exe --srv [Servicenames separated by comma] (mandatory)\n" \
                "                             --smtp [smtp ip] (optional , if set , --sender and --receiver arguments are mandatory)\n" \
                "                             --sender [senders email id] (optional , if set , --smtp and --receiver arguments are mandatory)\n" \
                "                             --receiver [receievers email ids] (optional , if set, --smtp and --receiver arguments are mandatory)\n" \
                "                             --log [logfilepath] (mandatory)\n" \
                "**************************************************************\n"


    return  description

def updateparser():
    parser.add_argument("--srv", help="Servicenames separated by comma (Mandatory argument)")

    parser.add_argument("--smtp", help="[smtp ip] (optional , if set , --sender and --receiver arguments are mandatory)"    )
    parser.add_argument("--sender", help="[senders email id] (optional , if set , --smtp and --receiver arguments are mandatory)"  )
    parser.add_argument("--receiver", help="[receievers email ids] (optional , if set, --smtp and --receiver arguments are mandatory)"    )
    parser.add_argument("--log", help="[logfilepath] (mandatory)")


def readParams():
    global args, services,mailserver,fromAddr,toAddr, logPath
    args = parser.parse_args()

    if args.srv:
        services = args.srv
    else:
        failCheck("services")
    if args.smtp:
        if args.sender and args.receiver:
            mailserver = args.smtp
        else:
            failCheck("smtp")

    # print(args.destsrv)
    if args.sender:
        if args.smtp and args.receiver:
            fromAddr = args.sender
        else:
            failCheck("smtp")

    if args.receiver:
        if args.smtp and args.sender:
            toAddr = args.receiver
        else:
            failCheck("smtp")
    if args.log:
        logPath = args.log
        print("Log path - ",logPath)
    else:
        failCheck("log")




def failCheck(param):
    print("Parameters not passed properly")
    print(appDescription())
    exit(0)
    pass



if __name__ == "__main__":
    global args, services, mailserver, fromAddr, toAddr, logPath
    fromAddr = ''
    toAddr = ''
    mailserver = ''
    args = {}
    parser = argparse.ArgumentParser()
    updateparser()
    # parser.usage = parser.print_help()
    # logPath = ""
    readParams()
    orig_stdout = sys.stdout
    print("The output will be logged here - ", logPath)
    sys.stdout = open(logPath, 'a')
    startTime = datetime.datetime.now()
    print(" \n\n **** Scheduled Job Started at : " + startTime.__str__() + "  **************")

    service_name = []

    receivers = []
    service_name = str(services).split(',')
    senderName = toAddr
    receivers = str(fromAddr).split(',')
    print("Service Name size : ",len(services)," ",services)
    try:
        i=0
        for s in service_name:
            sname = s
            print("Restarting service Name : ",sname)
            win32serviceutil.RestartService(sname)
            #i=i+1
            #print("i value ",i)
        else:
            print("All services done")
    except Exception as exception:
        print("Exception during restarting service",exception)
        pass

    #print("Wait for 5 minutes to confirm , if the ",service_name," service is running")
    statusOfServices = {}
    time.sleep(300)
    i=0
    for s in service_name:
        print("Checking status of service : ",service_name[i])
        recentStatus = getStatus(service_name[i])
        print("Recent Status : ",recentStatus)
        if recentStatus is None:
            recentStatus = 'Service Not Found'
        statusOfServices.update({service_name[i]:recentStatus})
        i=i+1

    statusMessage = "Status of the services as part of task scheduler is as follows : \n"
    for key,value in statusOfServices.items():
        statusMessage = statusMessage + "\n  "+key+" -->  "+statusOfServices[key]

    # print("SMTP - ", mailserver)
    # print("sender - ", senderName)
    # print("receiver - ", receivers)

    if mailserver and receivers and senderName:

         emailNotification(statusMessage,receivers,senderName,mailserver)
    else:
         print("Mail utility is not configured")
    sys.stdout = orig_stdout













