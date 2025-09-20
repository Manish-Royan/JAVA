@echo off
echo Building P2P-Chat-App...

REM Check if Java is installed
java -version >nul 2>&1
if %ERRORLEVEL% NEQ 0 (
    echo Java is not installed or not in PATH. Please install Java and try again.
    exit /b 1
)

REM Set directories
set SRC_DIR=src
set OUT_DIR=bin
set LIB_DIR=lib
set MAIN_CLASS=com.chatapp.Main

REM Create output directory if it doesn't exist
if not exist %OUT_DIR% mkdir %OUT_DIR%

REM Compile the Java files
echo Compiling Java files...
dir /s /B *.java > sources.txt
javac -d %OUT_DIR% @sources.txt
if %ERRORLEVEL% NEQ 0 (
    echo Compilation failed. Please check the errors above.
    del sources.txt
    exit /b 1
)
del sources.txt

REM Create JAR file
echo Creating JAR file...
jar -cvf P2P-Chat-App.jar -C %OUT_DIR% .

echo Build successful!
echo You can run the application using: java -jar P2P-Chat-App.jar

REM Uncomment the line below to automatically run the application after compilation
REM java -jar P2P-Chat-App.jar