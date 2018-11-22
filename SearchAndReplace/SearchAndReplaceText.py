import glob, os
import re

from os.path import isfile

import sys

import argparse

global args, regex, repex, srchtxt, reptxt, exten, folderPath, regexFlag, srchtxtFlag

def ScanFiles(srcFolder,extension,patternToSearch,TextToReplace):
    fileList= ['']
    countOfFiles= 0
    countOfMatch = 0
    print("Scan based on plain text replacement")
    for dirpath, _, filenames in os.walk(srcFolder):
        for filename in filenames:
            if filename.endswith(extension.lower()):
               countOfFiles = countOfFiles + 1
               fullpath = os.path.join(dirpath, filename).lower()
               #print("In : ",fullpath)
               try:
                  if patternToSearch.replace("\"","").lower() in open(os.path.join(dirpath, filename)).read().lower():
                      fileList.append(fullpath)
                      #print("Pattern found in File  : ",fullpath)
                      countOfMatch = countOfMatch + 1
                      replaceTextInFile(fullpath,patternToSearch.replace("\"","").lower(),TextToReplace.replace("\"",""))
               except UnicodeDecodeError:
                   print("UniCodeError in file  : ",fullpath," Scan will continue with other files")
            if(countOfFiles % 450 == 0):
                print("Scanned "+str(countOfFiles)+" Files. Found "+str(countOfMatch)+" matches")

def ScanFilesForRegex(srcFolder,extension,patternToSearch,textToReplace):
    fileList= ['']
    countOfFiles= 0
    #countOfMatch = 0
    print("TEXT : "+textToReplace)

    print("Scan based on regex  replacement")
    pattern = re.compile(patternToSearch)
    #replacePattern = "r'"+textToReplace+"'"
    for dirpath, _, filenames in os.walk(srcFolder):
        for filename in filenames:
            if filename.endswith(extension.lower()):
               countOfFiles = countOfFiles + 1
               fullpath = os.path.join(dirpath, filename).lower()
               #print("In : ",fullpath)
               try:

                      # pattern = re.compile("\bnew\b")
                      fh = open(os.path.join(dirpath, filename), 'r')
                      subject = fh.read()
                      fh.close()
                      f_out = open(os.path.join(dirpath, filename), 'w')
                      f_out.write(re.sub(pattern, r"'"+textToReplace+"'", subject))
                      f_out.close()
               except UnicodeDecodeError:
                   print("UniCodeError in file  : ",fullpath," Scan will continue with other files")
            if(countOfFiles % 450 == 0):
                print("Scanned "+str(countOfFiles)+" Files")




def replaceTextInFile(fullpath,patternToSearch,TextToReplace):
    fileList = ['']
    countOfFiles = 0
    countOfMatch = 0
    print("Scan based on plain text replacement")
    for dirpath, _, filenames in os.walk(srcFolder):
        for filename in filenames:
            if filename.endswith(extension.lower()):
                countOfFiles = countOfFiles + 1
                fullpath = os.path.join(dirpath, filename).lower()
                # print("In : ",fullpath)
                try:
                    if patternToSearch.replace("\"", "").lower() in open(
                            os.path.join(dirpath, filename)).read().lower():
                        fileList.append(fullpath)
                        # print("Pattern found in File  : ",fullpath)
                        countOfMatch = countOfMatch + 1
                        replaceTextInFile(fullpath, patternToSearch.replace("\"", "").lower(),
                                          TextToReplace.replace("\"", ""))
                except UnicodeDecodeError:
                    print("UniCodeError in file  : ", fullpath, " Scan will continue with other files")
            if (countOfFiles % 450 == 0):
                print("Scanned " + str(countOfFiles) + " Files. Found " + str(countOfMatch) + " matches")


    f = open(fullpath, 'r')
    filedata = f.read()
    f.close()
    newdata = filedata.lower().replace(patternToSearch,TextToReplace)
   # print("Replaced \""+patternToSearch+"\"  with  \""+TextToReplace+"\"")
    f = open(fullpath, 'w')
    f.write(newdata)
    f.close()



def decideFunction(folderPath, exten, regexFlag, srchtxtFlag,search,replace):
    print("Search Flag",srchtxtFlag)
    try:
        print("extension to search : ",exten)
    except NameError:
        exten = ""

    if(srchtxtFlag == 1):
        try:
            print("replacement Text ", replace)
        except NameError:
            reptxt = ""
        ScanFiles(folderPath,exten,search,replace)
    if(regexFlag == 1):
        try:
            print("replacement pattern ", replace)
        except NameError:
            repex = ""
        ScanFilesForRegex(folderPath,exten,search,replace)

def appDescription():
    print("This utility will search the pattern (text) in files with specific extension (example .java files) "
          "and replace the pattern with specified string OR regex")

def updateparser():
    parser.add_argument("--regex", help="regex to be searched in all files")
    parser.add_argument("--repex", help="replacement regular expression the text identified by regular expression passed using --regex parameter. "
                                        "This option is dependent on --regex . If --repex is not set, all the text identified using the "
                                        "patter given in --regex option will note be replaced",default="")
    parser.add_argument("--srchtxt", help="text to be searched in all files")
    parser.add_argument("--reptxt", help="replacement of the text identified by text passed using --srchtxt parameter. "
                                        "This option is dependent on --srchtxt . If --reptxt is not set, all the text identified using the "
                                        "pattern given in --srchtxt option will note be replaced",default="")
    parser.add_argument("--ext", help="extension of files to be seached in a given folderPath for the regex|srctxt given. If this option is"
                                      "not set , all the files will be searched in folderPath",default="")
    parser.add_argument("--folderPath",help="folder in which the regex/srchtxt to be searched . default will be the current"
                                            " directory which in this case is :  "+os.getcwd(), default=os.getcwd())

def readParams():
    global args, regex, repex, srchtxt, reptxt, exten, folderPath, regexFlag, srchtxtFlag
    exten = ""
    regexFlag=0
    srchtxtFlag=0
    args = parser.parse_args()
    if args.ext:
        exten = args.ext
    if args.folderPath:
        folderPath = args.folderPath
        print("Path of folder : " + folderPath)
        parser.print_help()
    if args.regex:
        regex=args.regex
        regexFlag = 1
        print("Regex set : ", regex)
    if args.repex:
        if args.regex:
            repex=args.repex
            print("Repex set : ", repex)
            decideFunction(folderPath, exten, regexFlag, srchtxtFlag, regex, repex)
        else:
            print("--regex parameter is missing which is mandatory if repex is used. \n\n\n check the usage of tool")
            parser.print_help()
            exit(0)



    if args.srchtxt:
        srchtxt=args.srchtxt
        srchtxtFlag = 1
        print("srchtxt : ", srchtxt)
    if args.reptxt:
        if args.srchtxt:
            reptxt=args.reptxt
            print("reptxt : ",reptxt)
            decideFunction(folderPath, exten, regexFlag, srchtxtFlag, srchtxt, reptxt)
        else:
            print("--srchtxt parameter is missing which is mandatory if reptxt is used. \n\n\n check the usage of tool")
            parser.print_help()
            exit(0)










# def replaceRegex(file,searchPattern,replacePattern):
#     pattern = re.compile("\$\$(\w+)")
#     #pattern = re.compile("\bnew\b")
#     fh = open('D:\\Office\\Telephonica\\Salcus\\Database\\Database\\0\\PB.1754.src', 'r')
#     subject = fh.read()
#     fh.close()
#     f_out = open('D:\\Office\\Telephonica\\Salcus\\Database\\Database\\0\\PB.1754.src', 'w')
#     f_out.write(re.sub(pattern,r'\1', subject))
#     f_out.close()



if __name__ == "__main__":
    global args, regex, repex, srchtxt, reptxt, exten, folderPath, regexFlag, srchtxtFlag
    parser = argparse.ArgumentParser(appDescription())
    updateparser()
    #parser.usage = parser.print_help()
    readParams()




