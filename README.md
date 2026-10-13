# Simple Java Swing Calendar Application

This is a Swing app that displays the current calendar month in the calendar year. It also
has a flying saucer that starts at today's date and flies to whatever date you click on.

## Requirements

- Java Development Kit (JDK) 8 or later

- No external libraries or other build tools needed.

## Components

- CalendarApp.java - Application entry point. Creates the calendar components and starts the EDT.
- CalendarFrame.java - Main app window and calendar layout.
- WeekdayHeaders.java - Display the seven weekday headers.
- DateCell.java - Represents an individual cell and will be used to generate the individual cells of the entire month.
-FlyingSaucer.java - Implements a flying saucer that starts at today's date and
flies to wherever you click it

## Usage

1. javac *.java
2. java CalendarApp
