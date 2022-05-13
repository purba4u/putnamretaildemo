Dim objResult

Set objShell = CreateObject("WScript.Shell")  
 

Do While True
	WScript.Sleep (6000)
  	objShell.sendkeys("{NUMLOCK}{NUMLOCK}")

Loop

