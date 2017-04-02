# Task Manager - User Guide

By : `Team POTATO`  &nbsp;&nbsp;&nbsp;&nbsp; Since: `Mar 2017`  &nbsp;&nbsp;&nbsp;&nbsp; Licence: `MIT`

---

1. [Quick Start](#quick-start)
2. [Features](#features)
3. [FAQ](#faq)
4. [Command Summary](#command-summary)

## 1. Quick Start

0. Ensure you have Java version `1.8.0_60` or later installed in your Computer.<br>

   > Having any Java 8 version is not enough. <br>
   > This app will not work with earlier versions of Java 8.

1. Download the latest `pota-todo.jar` from the [releases](../../../releases) tab.
2. Copy the file to the folder you want to use as the home folder for your task manager.
3. Double-click the file to start the app. The GUI should appear in a few seconds.
   > <img src="images/newUi.png" width="600">

4. Type the command in the command box and press <kbd>Enter</kbd> to execute it. <br>
   e.g. typing **`help`** and pressing <kbd>Enter</kbd> will open the help window.
5. Some example commands you can try:
   * **`list`** : lists all tasks
   * **`add`**`meeting 1/1/2088 1200 1/1/2088 1230`
     creates an event named `meeting` to the task manager.
   * **`delete`**` 3` : deletes the 3rd task shown in the current list
   * **`exit`** : exits the app
6. Refer to the [Features](#features) section below for details of each command.<br>


## 2. Features

**Command Format**
>
> * Words in `UPPER_CASE` are the parameters.
> * Items in `<SHARP_BRACKETS>` are compulsory.
> * Items in `[SQUARE_BRACKETS]` are optional.

### 2.1. Viewing help : `help`

**Format:** `help`

> Help is also shown if you enter an incorrect command e.g. `abcd`

### 2.2. Creating a task: `add`

Add a task which can be an event, a deadline or a floating task. <br>

The date expression can be in standard language. <br>
**For example:** <br>
Formal dates: 02/28/1979 <br>
Relaxed dates: oct 1st <br>
Relative dates: the day before next thursday <br>
Date alternatives: next wed <br>

**Event format:** `add <TASK_NAME> [<s/>DATE_START_TIME] [<e/>DATE_END_TIME]`<br>
**Example:** <br>
> add CS2103 finals s/25/04/2017 1300 e/25/04/2017 1500   <br> 

This will add an event named 'CS2103 finals' with [DATE_START_TIME] in format dd/mm/yyyy hhmm  and [DATE_END_TIME] in format dd/mm/yyyy hhmm. <br>
              
              
**Deadline format:** `add <TASK_NAME> [<e/>DATE_DUE_TIME]`<br>
**Example:** <br>
> add v0.5 e/01/01/2020 1200  <br>

This will add a deadline named 'v0.5' with [DATE_DUE_TIME] in format dd/mm/yyyy hhmm. <br>
               
               
**Floating task format:** `add <TASK_NAME>` <br>
**Example:** <br>
> add confess to love <br>

This command will simply add a floating task named 'confess to love'. <br>
     

### 2.3. Listing tasks : `list`

Shows a list of tasks which can include all tasks, only events, only deadlines or only floating tasks. The tasks can also be further filtered given a specified date. An example is given below for further clarification. <br>
**Format:** 'list <  / floating / event / deadline > [DATE]'  <br>
**Example:** <br>
> list <br>

This command will simply list all the tasks. <br>

> list deadline Feb 28 <br>

This command will list the deadline tasks which have the deadline as 28th February. <br>

> list event

This command will list all the event tasks (with no restriction on the dates of the tasks). <br>

### 2.4. Updating a task : `edit`

Updates an existing task in the task manager. <br>
**Event format:** `edit  <index> [NEW_TASK_NAME] [<s/>NEW_DATE_NEW_START_TIME] [<e/>NEW_DATE_NEW_END_TIME]` <br>
**Example:** <br>
> edit 3 edited_event s/01/01/2088 1200 e/01/01/2088 1230 <br>

This command will edit the task at index number 3 with a new name, new start date and time, new end date and time. <br>
Note: At least one of the three [PARAMETERS] must be executed. <br>
    
            
**Deadline format:** `edit <index> [NEW_TASK_NAME] [<s/>NEW_DATE_NEW_START_TIME] [<e/>NEW_DATE_NEW_DUE_TIME]` <br>
**Example:** <br>
> edit 4 edited_deadline s/01/01/2077<br>

This command will edit the task at index number 4 with new name, new event date and time. <br>
This deadline will become an event upon addition of the new start date. <br>
Note: At least one of the three [PARAMETERS] must be executed. <br>
               
**Floating task format:** `edit <index> <NEW_TASK_NAME> [<s/>NEW_DATE_NEW_START_TIME] [<e/>NEW_DATE_NEW_DUE_TIME]` <br>
**Example:** <br>
> edit 5 edited_floating_task e/01/01/2088 <br>

This command will simply edit the task at index number 5 with new name, new date and time. <br>
This floating task will become a deadline upon addition of the new end date. <br>
Note: At least one of the three [PARAMETERS] must be executed. <br>


### 2.5. Searching for all tasks containing a name : `find`

Searches for the tasks containing the input name. An example has been given below for further clarification. <br>
**Format:** `find <NAME>` <br>
**Example:** <br>
> find event <br>

This command will simply return the tasks with the name 'event' in them.
     

> * The search is not case sensitive. e.g `meeting` will match `Meeting`
> * The order of the keywords does not matter. e.g. `team meeting` will match `meeting team`
> * Only full words will be matched e.g. `meeting` will not match `meetings`
> * Tasks matching at least one keyword will be returned (i.e. `OR` search).
    e.g. `Meeting` will match `Company Meeting`

**Example:** <br>
> find meeting <br>

This command will return `company meeting` but not `date with girlfriend` or `meetings` <br>

### 2.6. Deleting a task : `delete`

Deletes a specified task.<br>
**Format:** `delete <INDEX>` <br>
**Example:** <br>
> delete 2   <br>

This command will simply delete the task at index number 2 shown on the current list.

### 2.7. Undo a command : `undo`

Undo the previous command executed. There is no limit to the number of undo actions that the task manager can execute.<br>
**Format:** `undo` <br>
**Example:** <br>
> add meeting with professor <br>
> undo <br>

The first command will add the floating task 'meeting with professor'. The second command will remove the floating task 'meeting with professor' from the task manager therefore 'undoing' the previous command.

### 2.8. Redo a command : `redo`

Execute the command undone by the previous undo command. Redo will basically undo the previous 'undo' command. Redo can be executed as many times as the 'undo' command execute right before the start of 'redo' command. However, there is no limit to the number of redo actions that the task manager can execute since there is no limit to the number of undo actions.<br>
**Format:** `redo` <br>
**Example:** <br>
> add meeting with professor <br>
> undo <br>
> redo <br>

The first command will add the floating task 'meeting with professor'. The second command will remove the floating task 'meeting with professor' from the task manager therefore 'undoing' the previous command. The third command will add the floating task 'meeting with professor' again therefore 'undoing' the previous undo command.

### 2.9. Marking tasks as done: `mark`

Marks a particular task as either done or not done by changing the colour (RED for undone, GREEN for done) displayed next to the task in the task manager. <br>
**Format:** `mark <INDEX> <done/undone>` <br>
**Format:** <br>
> mark 4 done <br>

This command will dislay a green colour next to the task at index number 4 indicating that the particular task has been done.

### 2.10. Exiting the program : `exit`

Exits the program.<br>
**Format:** `exit` <br>

### 2.11. Saving the data
Task manager data are saved in the hard disk automatically after any command that changes the data.<br>
There is no need to save manually.



## 3. FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with
       the file that contains the data of your previous task manager folder.

## 4. Command Summary

* **Create an event** : `add <TASK_NAME> [<s/>DATE_START_TIME] [<e/>DATE_END_TIME]` <br>
  e.g. `add birthday party s/1/1/2088 1200 e/1/1/2088 1230`
 
* **Create a deadline** : `add <TASK_NAME> [<e/>DATE_DUE_TIME]` <br>
  e.g. `add homework e/1/1/2088 1200`
  
* **Create a floating task** : `add <TASK_NAME>` <br>
  e.g. `add stay_healthy`
  
* **Edit Event format** : `edit  <index> [NEW_TASK_NAME] [<s/>NEW_DATE_NEW_START_TIME] [<e/>NEW_DATE_NEW_END_TIME]` <br>
            e.g. `edit 3 edited_event s/01/01/2088 1200 e/01/01/2088 1230`
            
* **Edit Deadline format** : `edit <index> [NEW_TASK_NAME] [<e/>NEW_DATE_NEW_DUE_TIME]` <br>
               e.g. `edit 4 edited_deadline e/01/01/2088 1200`
               
* **Edit Floating task format** : `edit <index> [NEW_TASK_NAME]` <br>
                   e.g. `edit 5 edited_floating_task`
                   
* **Delete** : `delete <INDEX>` <br>
   e.g. `delete 1`

* **Search** : `find <NAME> ` <br>
  e.g. `find meeting` `find NUS` 

* **List** : `list` <br>
  e.g. `list`

* **Help** : `help` <br>
  e.g. `help`

* **Mark Tasks as Done/Undone** : `mark <INDEX> <done/undone>` <br>
   e.g. `mark 2 done` `mark 5 undone`

* **Undo a command** : `undo` <br>
   e.g. `undo`

* **Redo a command** : `redo` <br>
   e.g. `redo`
   
* **Exit** : `exit` <br>
   e.g. `exit`
