#!/usr/bin/python

import smtplib

sender = 's.renukaiah@castsoftware.com'
receivers = ['s.renukaiah@castsoftware.com','n.krishnamurthy2@castsoftware.com']

message = """From: From TelefonicaPortal Server 
To: Telefonica Users <to@todomain.com>
Subject: Status of Tomcat restart scheduler Job 

This is a test e-mail message.
"""

try:
   smtpObj = smtplib.SMTP('192.168.20.4')
   smtpObj.sendmail(sender, receivers, message)         
   print ("Successfully sent email")
except :
   print ("Error: unable to send email")