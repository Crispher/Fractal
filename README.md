# Fractal
A fractal renderer

What am I doing?
  - Render fractals, typically Julia sets. 
  (To know about Julia set, see wikipedia: https://en.wikipedia.org/wiki/Julia_set)
  - Also fractals formed by IFS (Iterated function systems).
  (Fern fractal typifies these fractals, see: https://en.wikipedia.org/wiki/Barnsley_fern)
  
The structure of the project:
  * Kernal, does computations, decides what the fractal is.
    - Fractal, abstract class, provide interfaces.
      - IFS Fractal
      - Julia Fractal
      - Renderer, decides how to render the fractal 
      - Other utilities (complex arithmetics, Affine contractions etc)
  * GUI, decides how to show image on the screen, acts between user and kernel.
    - Bitmap, displays arrays of Color
    - Main Interface, the main program.
