CHAT User Guide
===============

**CHAT** is a lightweight, command-line interface (CLI) chatbot designed to help you manage daily tasks efficiently. Whether you have simple todos, strict deadlines, or multi-day events, CHAT keeps your schedule organized in one place.

Getting Started
---------------

1.  Ensure you have **Java 11** or higher installed on your computer.

2.  Download the latest `.jar` file from our releases.

3.  Open your terminal or command prompt.

4.  Run the application using: `java -jar Chat.jar`

* * * * *

Features
--------

### 1\. Adding Tasks

CHAT supports three types of tasks. When you add a task, it will confirm the addition and show you the total number of tasks in your list.

-   **Todo**: A task without any date or time constraints.

    -   **Format**: `todo <description>`

    -   **Example**: `todo read a book`

-   **Deadline**: A task that must be completed by a specific time.

    -   **Format**: `deadline <description> /by <time>`

    -   **Example**: `deadline submit report /by tonight`

-   **Event**: A task that starts and ends at specific times.

    -   **Format**: `event <description> /from <start> /to <end>`

    -   **Example**: `event team meeting /from 2pm /to 4pm`

### 2\. Viewing the Task List

You can view all the tasks currently stored in your list, including their type icons and completion status.

-   **Format**: `list`

-   **Expected Output**:

    ```
    1\. [T][ ] read a book
    2. [D][X] submit report (by: tonight)

    ```

### 3\. Managing Completion Status

Mark tasks as done or unmark them if they need to be revisited.

-   **Mark as Done**: `mark <description>`

-   **Unmark**: `unmark <description>`

-   **Note**: CHAT searches for the task by its description to update it.

### 4\. Deleting Tasks

Remove a task permanently from your list using its index number from the `list` command.

-   **Format**: `delete <index_number>`

-   **Example**: `delete 1`

### 5\. Finding Tasks

Search for tasks in your list using keywords to find matching descriptions.

-   **Format**: `find <keyword>`

-   **Example**: `find book`

### 6\. Exiting and Saving

When you are finished, use the exit command. CHAT will automatically save your current list to the hard drive so your data is safe for the next time you use it.

-   **Format**: `bye`

* * * * *

Data Persistence
----------------

Your tasks are stored automatically in a data file at `data/tasks.txt`. CHAT loads this file every time you start the app, so you never lose your progress.

> **Warning**: Do not manually edit the text in `data/tasks.txt` as it may cause the data to become corrupted and prevent CHAT from loading your tasks properly.