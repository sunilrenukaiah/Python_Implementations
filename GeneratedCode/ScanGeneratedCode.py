import argparse
import mmap
from glob import glob
import ntpath
from pathlib import Path
import os
from os import listdir
import subprocess
from os.path import isfile, join , abspath

import sys

textDict = {"This file was auto-generated from":0,"This class was automatically generated":0,"©Copyright":0,"@Copyright":0,"Jax-RS":0,"Copyright (c)":0}

extensionList = ['.java','.js','.xml','.jsp','.cs','.aspx','.properties','.sh','.html','.css','.asmx','.aspx','.ashx']
targetFoldersList = []
testFoldersList = []
mockFoldersList = []
folderDict ={"Target":targetFoldersList,"Test":testFoldersList,"Mock":mockFoldersList}

testAnnotationDict = {"@Test":0,"@Mock":0}


def click_on_file(filename):
    try:
        os.startfile(filename)
    except AttributeError:
        subprocess.call(['open', filename])

def parseFile(fileName,logFile,dictInScope):
    if fileName != '':
        fileName = fileName.replace('\\','\\\\')
        print("Parsing File Name : ",fileName)
        #with open(fileName, 'rb', 0) as file, \
         #   mmap.mmap(file.fileno(), 0, access=mmap.ACCESS_READ) as s:

        for key in dictInScope:
           print("Searching for Key : ",key)
           if key.__str__().lower() in open(fileName).read().lower():
               orig_stdout = sys.stdout
               f = open(logFile, 'a')
               sys.stdout = f
               #print('true in file : ',fileName)
               dictInScope[key]+=1
               #print("Found : ",key,"  Value : ",textDict[key])
               print("Found \"" ,key,"\" in  ",fileName)
               newName = changeExtension(fileName)
               #print("Action Taken -> changed the extension of above file to <>.GENERATED \n new File Name -> ",newName)
               sys.stdout = orig_stdout


def changeExtension(fileFullPath):
   fileName =  ntpath.basename(fileFullPath)
   #print("FILE to be Rename : ",fileName)
   os.path.splitext(fileName)[0] + ".GENERATED"
   new_filename = Path(fileName).stem + ".GENERATED"
   return new_filename

def scanFolderForGenCode(folderName,logFile):
    print("\n\n")
    print("******* SCANNING CODE FOR GENERATED CODE ***********")
    if isfile(logFile):
        print("Flushing the contents of log : ",logFile)
        open(logFile, 'w').close()
        print("Log file cleaned")
    #fileList = [f for f in listdir(folderName) if isfile(join(folderName, f))]
    fileList= ['']
    for dirpath, _, filenames in os.walk(folderName):
        for f in filenames:
            for extension in extensionList:
                if f.endswith(extension.lower()):
                    fileList.append(os.path.abspath(os.path.join(dirpath, f)))
    for eachFile in fileList:
        try:
         parseFile(eachFile,logFile,textDict)
        except UnicodeDecodeError:
            continue
        except FileNotFoundError:
            continue
    #print(fileList)
    f = open(logFile, 'a')
    orig_stdout = sys.stdout
    sys.stdout = f
    print("\n\n\n")
    print("TOTAL occurences : ")
    print("{:<8} {:>15}".format('Text', 'count'))
    #print("TEXT                         Count")
    #for key in textDict:
     #   print(key,"                ",textDict[key])
    for k in textDict:
        print("{:<8}{:>15}".format(k,textDict[k]))
    print("\n\n")
    sys.stdout = orig_stdout


def scanFolderForTestCode(folderName,logFile):
    print("\n\n")
    print("******* SCANNING CODE FOR TEST ANNOTATIONS ***********")
    fileList= ['']
    for dirpath, _, filenames in os.walk(folderName):
        for f in filenames:
            for extension in extensionList:
                if f.endswith(extension.lower()):
                    fileList.append(os.path.abspath(os.path.join(dirpath, f)))
    for eachFile in fileList:
        try:
         parseFile(eachFile,logFile,testAnnotationDict)
        except UnicodeDecodeError:
            continue
        except FileNotFoundError:
            continue
    #print(fileList)
    f = open(logFile, 'a')
    orig_stdout = sys.stdout
    sys.stdout = f
    print("\n")
    print("TOTAL occurences : ")
    print("{:<8} {:>15}".format('Text', 'count'))
    #print("TEXT                         Count")
    #for key in textDict:
     #   print(key,"                ",textDict[key])
    for k in testAnnotationDict:
        print("{:<8}{:>15}".format(k,testAnnotationDict[k]))
    sys.stdout = orig_stdout



def appDescription():
    description="Tool parses all the files in a given folder to identify generated code . Below customizations will be introduced in next release :" \
                "1. Change the extension of generated code" \
                "2. Specify extensions to be scanned in given folder (e.x., scan only java files in the given folder)" \
                "3. Specify the text to be searched the in the files using comma separated values. By default files will be searched for following lines :" \
                "     \"This file was auto-generated from\",\"This class was automatically generated\",\"Copyright\"     "

    return  description


def updateparser():
    parser.add_argument("--srcpath", help="Path of source code \n This is a mandatory argument", required=True)
    parser.add_argument("--log", help="Path to document the scan data \n This is a mandatory argument", required=True)
    parser.add_argument("--exclude",help="***** THIS FEATURE WILL BE INCLUDED IN NEXT RELEASE ************ \n "
                                         "If you want to exclude generated files , pass TRUE \n This is not a mandatory argument. "
                                         "By default files will not be excluded . You can check the details in log file",type=bool)
    parser.add_argument("--ext",help="***** THIS FEATURE WILL BE INCLUDED IN NEXT RELEASE ************\n"
                                     "specify the file extensions with comma separated values. \n if nothing is specified, all files will be scanned. This is not a mandatory argument \n \n")


def CheckTestFolders(source,logPath):
    subFolders = os.walk(source)
    #print([x[0] for x in os.walk(source)])
    for x in subFolders:
        if ( x[0].endswith("Test") | x[0].endswith("test") | x[0].endswith("TEST")):
            print("Subfolders : " +x[0])
            testFoldersList.append(x[0])
        if ( x[0].__contains__("Mock") | x[0].__contains__("mock")):
            print("Subfolders : " +x[0])
            mockFoldersList.append(x[0])
        if (x[0].endswith("target") | x[0].endswith("Target") | x[0].endswith("TARGET")):
            print("Target Folders : "+x[0])
            targetFoldersList.append(x[0])
    f = open(logPath, 'a')
    orig_stdout = sys.stdout
    sys.stdout = f
    print("\n\n\n\n")
    print("TEST FOLDERS : \n")
    templist = folderDict["Test"]
    if len(templist) > 0:
        for tstfolder in templist:
            print(tstfolder+"\n")
        print("\n\n\n\n")
    else:
        print("No folders found ending with Test \n")
    print("TARGET FOLDERS : \n")
    templist = folderDict["Target"]
    if len(templist) > 0:
        for tgtfolder in templist:
             print(tgtfolder + "\n")
        print("\n\n\n\n")
    else:
        print("No folders found ending with target \n")
    print("MOCK FOLDERS : \n")
    templist = folderDict["Mock"]
    if len(templist) > 0:
        for mckfolder in templist:
            print(mckfolder + "\n")
        print("\n\n\n\n")
    else:
        print("No folders found ending with Mock \n")

    sys.stdout = orig_stdout


if __name__ == "__main__":
       global srcPath, logFilePath
       parser = argparse.ArgumentParser()
       updateparser()
       args = parser.parse_args()
       if args.srcpath:
           srcPath = args.srcpath
       if args.log:
           logFilePath = args.log
       #scanFolder("D:\\Office\\Tools\\GeneratedCode\\","D:\\Office\\Tools\\GeneratedCode\\log.txt")
       print("SOURCE : "+srcPath+" -  LOG : "+logFilePath)
       print("Scanning Files for Generated Code")
       scanFolderForGenCode(srcPath,logFilePath)
       print("Scanning Files for Test and Mock annotations")
       scanFolderForTestCode(srcPath, logFilePath)
       print("Refer log file : "+logFilePath)
       #osCommandString = "notepad.exe "+logFilePath
       #os.system(osCommandString)
       #click_on_file(logFilePath)
       #srcPath = "d:\\office"
       CheckTestFolders(srcPath,logFilePath)
       click_on_file(logFilePath)
