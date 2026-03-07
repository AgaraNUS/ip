# Misato Katsuragi chatbot

Inspired by _Misato Katsuragi_, this chatbot helps you keep track of your daily tasks, deadlines, and events while storing your data locally.

![misato about to destroy a can  of beer](/Misato_Image/eva_misato019.jpg)

# Features

### To-do
Example input: `todo read a book`
Output:
```
____________________________________________________________
Got it. I've added this task:
[T][ ] read a book
Now you have 2 tasks in the list.
____________________________________________________________
```

### Deadline
Example input: `deadline return book /by 2026-06-06 1800`
Output:
```
____________________________________________________________
Got it. I've added this task:
[D][ ] return book (by: 06 Jun 2026, 6:00 pm)
Now you have 3 tasks in the list.
____________________________________________________________
```

### Event
Example input: 
`event project meeting /from 2026-08-06 1400 /to 2026-08-06 1600`

Output:
```
____________________________________________________________
Got it. I've added this task:
[E][ ] project meeting (from: 06 Aug 2026, 2:00 pm to: 06 Aug 2026, 4:00 pm)
Now you have 4 tasks in the list.
____________________________________________________________
```

### Marking tasks as done/undone
Mark a task as done
Example input:
`mark 1`

Output:
```
____________________________________________________________
Omedeto! OP-kun!
[T][X] read a book
____________________________________________________________
```

## Delete tasks
Delete specific tasks using the index of the tasks
Example input:
`delete 1`

Output:
```
____________________________________________________________
You better have a good reason for this!
  [T][ ] read a book
Now you have 2 tasks in the list.
____________________________________________________________
```

## Local Storage
Automatically saves your task list to your local hard drive (`./data/misato.txt`) after every modification and loads it on startup.
You can then view the data in the txt on your CLI using the "list" function.

Example input:
`list`

Output:
```
____________________________________________________________
Here are the tasks in your list:
1.[T][ ] read a book
2.[D][ ] return book (by: Jun 06 2026, 6:00 PM)
3.[E][ ] project meeting (from: Aug 06 2026, 2:00 PM to: Aug 06 2026, 4:00 PM)
____________________________________________________________
```

## Search Functionality
Quickly find specific tasks using keywords.
Example input:
`find book`

Output:
```
____________________________________________________________
Here are the matching tasks in your list:
1.[T][ ] read a book
2.[D][ ] return book (by: 06 Jun 2026, 6:00 pm)
____________________________________________________________
```


## Character UI
Enjoy a touch of personality with custom success and error messages.
