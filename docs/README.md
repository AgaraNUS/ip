# Misato Katsuragi chatbot

Inspired by _Misato Katsuragi_, this chatbot helps you keep track of your daily tasks, deadlines, and events while storing your data locally.

![misato about to destroy a can  of beer](/Misato_Image/eva_misato019.jpg)

# Features

 ## Multiple Task Types:

Support for basic tasks such as *To-Dos*, *Deadlines* _(with specific dates/times)_, and *Events* _(with start and end times)_.

Example input for To-Do: `todo read a book`
Output:
```
____________________________________________________________
Got it. I've added this task:
[T][ ] read a book
Now you have 2 tasks in the list.
____________________________________________________________
```
Example input for Deadline: `deadline return book /by 2026-06-06 1800`
Output:
```
____________________________________________________________
Got it. I've added this task:
[D][ ] return book (by: 06 Jun 2026, 6:00 pm)
Now you have 3 tasks in the list.
____________________________________________________________
```
Example input for Event: `event project meeting /from 2026-08-06 1400 /to 2026-08-06 1600`
Output:
```
____________________________________________________________
Got it. I've added this task:
[E][ ] project meeting (from: 06 Aug 2026, 2:00 pm to: 06 Aug 2026, 4:00 pm)
Now you have 4 tasks in the list.
____________________________________________________________
```

## Marking tasks as done/undone

## 

## Local Storage
Automatically saves your task list to your local hard drive (`./data/misato.txt`) after every modification and loads it on startup.

## Search Functionality
Quickly find specific tasks using keywords.

## Character UI
Enjoy a touch of personality with custom success and error messages.
