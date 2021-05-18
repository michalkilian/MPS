package org.example.logic;

public class Grid {

    int widthInCells;
    int lengthInCells;
    int[][] left;
    int[][] middle;
    int[][] right;

    public Grid(int widthInCells, int lengthInCells) {
        this.lengthInCells = lengthInCells;
        this.widthInCells = widthInCells;
        left = new int[lengthInCells][widthInCells];
        middle = new int[widthInCells][2 * lengthInCells + widthInCells];
        right = new int[lengthInCells][widthInCells];
    }

    public void set(int x, int y, int val) {
        if (x >= 0 && x < lengthInCells && y >= lengthInCells && y < lengthInCells + widthInCells)
            left[x][y - lengthInCells] = val;
        else if (x >= lengthInCells && x < lengthInCells + widthInCells && y >= 0 && y < 2 * lengthInCells + widthInCells)
            middle[x - lengthInCells][y] = val;
        else if (x >= lengthInCells + widthInCells && x < 2 * lengthInCells + widthInCells && y >= lengthInCells && y < lengthInCells + widthInCells)
            right[x - lengthInCells - widthInCells][y - lengthInCells] = val;
        else
            throw new IndexOutOfBoundsException();
    }

    public int get(int x, int y) {
        if (x >= 0 && x < lengthInCells && y >= lengthInCells && y < lengthInCells + widthInCells)
            return left[x][y - lengthInCells];
        else if (x >= lengthInCells && x < lengthInCells + widthInCells && y >= 0 && y < 2 * lengthInCells + widthInCells)
            return middle[x - lengthInCells][y];
        else if (x >= lengthInCells + widthInCells && x < 2 * lengthInCells + widthInCells && y >= lengthInCells && y < lengthInCells + widthInCells)
            return right[x - (lengthInCells + widthInCells)][y - lengthInCells];
        else
            throw new IndexOutOfBoundsException();
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < 2 * lengthInCells + widthInCells; i++) {
            for (int j = 0; j < 2 * lengthInCells + widthInCells; j++) {
                try {
                    if (this.get(j, i) == 0) str.append('-');
                    else str.append(this.get(j, i));
                } catch (IndexOutOfBoundsException e) {
                    str.append('x');
                }
            }
            str.append('\n');
        }
        return str.toString();
    }
}
