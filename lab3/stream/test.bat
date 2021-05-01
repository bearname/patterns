@echo off 

java -jar build\libs\transform.jar --encrypt 23  --decrypt 23 input.txt output.txt
fc.exe input.txt output.txt
IF ERROROLEVEL EQU 1 GOTO err
echo test "decrypt" succeeded
      


java -jar build\libs\transform.jar --encrypt 23 --encrypt 123740  --decrypt 23  --decrypt 123740 input.txt output-multiple.txt
fc.exe input.txt output-multiple.txt
IF ERROROLEVEL EQU 1 GOTO err
echo test "decrypt multiple" succeeded      


ECHO Program testing succeeded :-)
EXIT 0

:err
ECHO Program testing failed :-(
EXIT 1
© 2021 GitHub, Inc.