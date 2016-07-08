# MineSweeper Game

This repository contains the implementation of MineSweeper game wirtten in Java.
Project was building with Maven,   
for operations on input and output string it was used commons-lang3 library.

##Game rules
A mine-field of N x M squares is repsresented by N lines of exactly M characters each.  
The character '\*' represents a mine field.  
The character '.' represents no-mine field.  
Lines are separated by '\n'.  

The returing value is a hint-field of identical dimensions as the input mine-field
where each square is a '\*' for a mine or the number of adjacent mine-squares if the square does not contain a mine.

##How to play
Once you have cloned the directory to your local machine, follow the directions below:  

1. You can set a mine-filed by two different ways directly from main`setMineField("*...\n..*.\n....")` class or
with first program arguments args[0]. Proper example mine-field string is "\*...\n..\*.\n...."

2. After entering a mine-field program will be produces a hint-filed of identical dimensions as the input.  
Example output for above input is "\*211\n12*1\n0111"



##License

##Author
Dawid Janik

