# MKS21X-FinalProject
TO COMPILE:
javac -cp lanterna.jar:. Field.java

TO RUN:
java -cp lanterna.jar:. Field

TO USE PROGRAM: 
Use arrow keys to move the player ('U') around a map
When you take damage you change to a different letter of the alphbet. If you take any more damage while you are 'A' you lose. 
Use w,a,s,d to shoot a projectile. 
Use p when you are on top of a portal to move on to the next level. 


GOAL:
Kill all the monsters on each level, then process to the next level via portal. 


1/4/19
  - Created branches monster, player, floor,
  - Created the framework inside of monster and player
    - Fields, constructor and some methods
  - Created and push to master Damageable interface 
  
1/7/19
  -Finished more methods inside of Monster class
  -Open the terminal in priavte mode, and created a symbol.
  -Wrote code to exit the terminal with the escape key 
  
1/8/19
  - Gave player and monster a symbol
  - Created terminal, added code for player movement.
  
1/9/19 
  - Merged monster branch into master
  - Read java doc on how to use screen 
  - Created a screen 
  
1/10/19
  - fixed merge problem 
  - created monster in the screen.
  - filled in wall class and a bit of floor class 
  
1/12/19
  - finished basis of floor class 
  - program is now able to put in walls when given x and y coordinates
  - we are now using a 2D arraylist for the playing field to represent multiple floors and the walls in each floor
  
1/13/19
  - Created floor one
  - Made it impossible for a player to pass through walls
  - Made Monsters on the playing field
  
1/15/19
  - Monster won't go there walls 
  - Created a portal 
  
1/16/19
  - Drew floor two on paper 
  - added attack 
  - the player is now able to click spacebar and any monsters in a 2 tile (changeable) radius will take damage
  - if a monster takes enough damage where its health is reduced to less that or equal to 0, it will die and disappear from the screen
  
1/17/19
  - Created a file reader to read in lvl 2 
  
1/18/19
  - Started working on the portal
  - Started work on projectile 
  
1/19/19
 - Worked on projectile 
 - Worked making the portal work
 
1/20/19
 - Portal works
 - Projectile works but is slow 
 - End message created 
 - Lvl 3 drawn on paper
 - Lvl 3 typed into a txt file
 - Lvl 3 read in right
 - Portal works for all lvls 

1/21/19
  - Merged portal code and shooting code 
  - Changed lvl 3 a bit
  - Changed character 
  - Created lose and win screen 
  - Made game decently hard. 
  - Game done 
 
