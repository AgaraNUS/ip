# Misato Katsuragi chatbot

## To see the full capabilities of the chatbot, please refer to the [User Guide](https://pages.github.com/)

### Pre-requisites
Java Development Kit (JDK) 17 or higher installed on your machine.

### Setting up in Intellij

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
2. Open the project into Intellij as follows:
3. Click `Open`.
4. Select the project directory, and click `OK`.
5. If there are any further prompts, accept the defaults.
6. Configure the project to use **JDK 17** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
7. After that, locate the `src/main/java/Misato.java` file, right-click it, and choose `Run misato.Misato()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:
   ```
   ____________________________________________________________
   Hello! I'm Misato Katsuragi
   What can I do for you?
   ____________________________________________________________
   ```
### Running the Application
1. Compile the Java files or run the provided `misato.jar` file.
2. If running via the JAR file, open your terminal and execute:
   ```
   java -jar misato.jar 
**Warning:** Keep the `src\main\java` folder as the root folder for Java files (i.e., don't rename those folders or move Java files to another folder outside of this folder path), as this is the default location some tools (e.g., Gradle) expect to find Java files.

