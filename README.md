Chess referee app:
It doesn't include SwingUI representation, nor any chessbots such as stockfish
Uses 2 different abstract classes, since the queen, rook and bishop are similar, but the pawn,king and knight are different
Supported moves:
Every piece moves by the rules. I also included en pessant, castleing mechanism and pawn transforms.
Known issues:
The pawn always transforms to a queen. The castleing mechanism only checks the position of the pieces ( if the king gets a check, moves and goes back to starting place, it will still be able to castle which is not supported)
(Used AI to create the chessboard, work out the pawn logic and the tests)
How to run:
Run the "Sakklepesek.java" file.
PlantUML:
<img width="1318" height="593" alt="image" src="https://github.com/user-attachments/assets/3745b57d-7bf0-4a25-bad4-c00835f4acf0" />

