=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
CIS 1200 Game Project README
PennKey: 60716869
=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=

===================
=: Core Concepts :=
===================

- List the four core concepts, the features they implement, and why each feature
  is an appropriate use of the concept. Incorporate the feedback you got after
  submitting your proposal.

  1. 2D array - game board. The game board is a grid, so a 2D array is suitable.

  2. LinkedList - Queue of pieces. This was necessary as I needed a mutable list, as the game can go
  on forever.

  3. JUnit Testing - Functions were written separate from the UI so that the game can be tested without loading
  the graphics. The game can't be played directly without the UI (as moving the piece requires

  4. File I/O - can load game, which autosaves after every move.

===============================
=: File Structure Screenshot :=
===============================
- Include a screenshot of your project's file structure. This should include
  all of the files in your project, and the folders they are in. You can
  upload this screenshot in your homework submission to gradescope, named 
  "file_structure.png".

=========================
=: Your Implementation :=
=========================

- Provide an overview of each of the classes in your code, and what their
  function is in the overall game.
  Piece class: Abstract class that holds the basic arguments, as well as the rotation command.
  Originally, I was going to do inheritance and hard coding the rotations, but I found a smarter way to do it.
  Anything that contains the word "Piece": An inherited class from Piece, which just stores the shape
  and color (aside from square, as we do not rotate that piece).
  Tetris: Holds the board, the piece, all functions that the game has.
  Board: Holds game logic, allows the game to run.
  RunTetris: JPanel stuff.
  TetrisTest: For testing.


- Were there any significant stumbling blocks while you were implementing your
  game (related to your design, or otherwise)?
  Testing for valid positions was a little annoying because obviously out of bounds is not a valid position,
  but still must be tested for. At first, I made it so that if it is out of any bounds (left, right, top, bottom)
  it automatically returns false. However, this makes the game slightly annoying to play, as we cannot immediately
  move the piece, as it is clipped into the top. To remedy this, I removed the top bound for valid position and told
  the program to ignore it.
  Due to this, my lose condition detector is a little primitive.

- Evaluate your design. Is there a good separation of functionality? How well is
  private state encapsulated? What would you refactor, if given the chance?
  Should be pretty well encapsulated.
  If I could refactor this, I would probably change my Piece class to be cloneable, as this would make my
  Tetris.rotate() method a lot nicer.


========================
=: External Resources :=
========================

- Cite any external resources (images, tutorials, etc.) that you may have used 
  while implementing your game.

https://stackoverflow.com/questions/233850/tetris-piece-rotation-algorithm