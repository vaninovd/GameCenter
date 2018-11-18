Project Instructions:

URL of our group to clone: https://markus.teach.cs.toronto.edu/git/csc207-2018-09-reg/group_0495

Location of Gradle: AndroidStudioProjects/group_0495/Phase1/GameCenter

1. Open Android Studio.
2. File - New - Project from Version Control - Git.
3. In the URL paste the link above. Be prepared to enter your CS labs Username and Password.
4. When prompted to create new project, click yes.
5. On the next menu, check Gradle and click Next.
5. On the next menu, indicate the location of gradle file, which is normally: 
6. Click Finish.

Instructions to play the game:

0. Launch the app. Select Pixel 2 as device. Wait for Gradle build to finish.

1. GAME CENTER: User starts at the sign in/sign up menu. If user has an account already, they need 
to sign in with their user name and password by entering the information in the respective fields 
and pressing the sign in button. If they do not have an account, they can sign up using the sign up 
button. If they click the sign up button, they will need to enter new user name and password. Then
they need to confirm their password. After they successfully signed up, the app will go back to sign
in menu where they can to sign in with their newly created user name and password.

2. After sign in, they will be asked if they want to start a new game, load a previously saved game, 
save current game, or display the scoreboard for the game.

3. UNDO/BOARD SIZE: When they click to start a new game, they will be prompted to select the size 
of the board (if no selection is made, the game is created with board of size 3x3) and the number 
of undos a user can make in a row (3 by default). If a user wants to have an unlimited number of 
undos, they can check check the corresponding box and they will not be limited in the number of 
undos. If the user selects the number of undos, they can undo the last selected number of moves they
have made. If the user undid the last selected number of moves, once they make another move, they 
start accumulating the number of moves they can undo (up to a maximum of what the user selected). 
When user starts the game, the undo button is displayed at the bottom of the screen.

5. AUTOSAVE: Every 3 moves, the game is auto-saved.

6. SCORE BOARD: Current user score is displayed at the top and indicates the total number of moves
made. Undoing a move counts as a move. The user who wins with the fewest moves receives the higher 
score.

7. SAVE: To save the game, user presses back button on the phone twice to get to the menu where save 
button is displayed. Once user presses save button, the game is saved. To get back to the game, user 
presses load saved game and app returns to 