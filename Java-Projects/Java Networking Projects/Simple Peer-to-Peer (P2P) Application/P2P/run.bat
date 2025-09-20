@echo off
echo Starting P2P Chat Application...

REM Check if Java is installed
java -version >nul 2>&1
if %ERRORLEVEL% NEQ 0 (
    echo Java is not installed or not in PATH. Please install Java and try again.
    exit /b 1
)

REM Check if bin directory exists
if not exist bin\ (
    echo Compiled classes not found. Please compile the application first.
    exit /b 1
)

REM Run the application
echo Running application...
java -cp bin p2p.P2PApplication

echo Application terminated.
