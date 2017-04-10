# Test Script for potaTO-DO
**Note**: For this test, please do it in sequence and do not skip any commands.

## Setup Sample Data

1. Open the .jar file in the zipped folder. A folder named `data` will appear in the same directory as the .jar file.
2. Rename `sampleData.xml` into `taskmanager.xml`
3. Drag `taskmanager.xml` into the data folder, replacing the old file.

## Test Commands
### Add
#### Add Floating Tasks
`add Floating Task 1`
Output:
- Text Window should return `New tasks added!`
- Task panel should display `Floating Task 1` at the bottom of the list.

#### Add Deadlines
`add Deadline 1 by 15th April 2017 8pm`
Output:
- Text Window should return `New tasks added!`
- Task panel should display `Deadline 1` at the bottom of the list. Deadline should display a Due date.
- 
#### Add Deadlines (Hidden Time with tags)
`add Deadline 2 by 15th April 2017 t/tag1`
Output:
- Text Window should return `New tasks added!`
- Task panel should display `Deadline 2` at the bottom of the list. Deadline should display a Due date. Time should be hidden as a timing is not specified. Tags should also be shown at the bottom.

#### Add Events
`add Event 1 from 14th April 2017 to 15th April 2017`
Output:
- Text Window should return `New tasks added!`
- Task panel should display `Event 1` at the bottom of the list. Deadline should display a Start and End Date

#### Add Event (Start Date before End Date)
`add junk 1 from 16th April 2017 by 15th April 2017`
Output:
- Command Box turns red.
- Text Window should return `The start date provided is after end date.`

#### Invalid Date Markers
`add junk 2 to 13th April 2017 by 17th April 2017`
Output:
- Command Box turns red.
- Text Window should return `The command contains markers with repeated intent.`

#### Only Start Date
`add junk3 from 16th April`
Output:
- Command Box turns red
- text Window should return `Invalid Command Format!`

###Edit
###Edit name
`edit 3 Stay very healthy`
Output:
- Text Window should return `Edited Task: Stay very healthy | Tags:` 
- Task panel should display the newly edited task.

### Edit 
#### Edit name
`edit 3 Stay very healthy`
Output:
- Text Window should return `Edited Task: Stay very healthy | Tags:` 
- Task panel should display the newly edited task.

#### Edit Event Dates
`edit 4 from 15th April 2017 12pm to 15th April 2017 1pm`
Output:
- Text Window should return `Edited Task: Meeting with vice principal | Start: 15 April 2017, 12:00 PM | End: 15 April 2017, 01:00 PM | Tags:` 
- Task panel should display the newly edited task.

#### Promote deadlines to events
`edit 11 from 12th April 2pm to 12th April 4pm`
Output:
- Text Window should return `Edited Task: Ask for career advice | Start: 12 April 2017, 02:00 PM | End: 12 April 2017, 04:00 PM | Tags: ` 
- Task panel should display the newly edited task.

#### Invalid Demotion
`edit 13 to remove`
Output:
- Command Box turns red
- Text Window should return `The editted task is not in the right format. Please check the type of your task to edit accordingly` 

#### Edit tags
`edit 1 t/urgent`
Output:
- Text Window should return `Edited Task: Stay motivated | Tags: [important] ` 
- Task panel should display the newly edited task.
 
### Undo
`undo`
Output:
- Task panel should undo the edit tag command.
- Text Window should return `Undo successful`

### Redo
#### Successful Redo
`redo`
Output:
- Task panel should undo the edit tag command.
- Text Window should return `Redo successful`

#### Unsuccessful Redo
`redo`
Output:
- Command Box should turn red
- Text Window should return `No new state found.`

### Delete
#### Delete Tasks
`delete 42`
Output
- Task panel scrolls to index 42.
- Text Window should return `Deleted Task: Task: Keep writing diary | Tags: `

### Mark
#### Mark done
`mark 5 done`
Output:
- Task panel should display task number 5, which its left index box will turn green.
- Text Window should return `Marked Task: Task: Finish project | End: 26 April 2017, 10:00 AM | Tags: [urgent]`
 
#### Mark undone
`mark 5 undone`
Output:
- Task panel should display task number 5, which its left index box will turn red
- Text Window should return `Marked Task: Task: Finish project | End: 26 April 2017, 10:00 AM | Tags: [urgent]`

### Find
#### Find by name
`find golf`
Output:
- Task panel should display 3 tasks
- Text Window should return `3 tasks listed!`

### List
#### List all
`list`
Output:
- Task panel should display all tasks
- Text Window should return `Listed all tasks`

#### List by type
`list floating`
Output:
- Task panel should display all floating tasks
- Text Window should return `Listed all tasks (floating)`

#### List by status (done/undone)
`list undone`
Output:
- Task panel should display all undone tasks
- Text Window should return `Listed all tasks (undone)`

#### List by date
`list 25th April`
Output:
- Task panel should display only 1 task.
- Text Window should return `Listed all task (25th April)`

### Set Notification
`set 1 month`
Output:
- Text Window should return `New notification time has been set.`
- After exiting and re-launching, a clock icon should appear at the 2nd task.

### Changing Filepath
`filepath data/potato.xml`
Output:
- Text Window should return `File path changed to data/potato.xml`
- Status Footer at the bottom right should show `data/potato.xml`
- `potato.xml` should be created in the data folder.

### Help
`help`
Output:
- Help window popup should appear

### Exit
`exit`
Output:
- potaTO-DO should close.