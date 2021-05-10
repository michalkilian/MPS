package logic;

public class Grid {

    int widthInCells;
    int lengthInCells;
    byte[][] left;
    byte[][] middle;
    byte[][] right;

    public Grid(int widthInCells, int lengthInCells) {
        this.lengthInCells = lengthInCells;
        this.widthInCells = widthInCells;
        left = new byte[lengthInCells][widthInCells];
        middle = new byte[widthInCells][2 * lengthInCells + widthInCells];
        right = new byte[lengthInCells][widthInCells];
    }

    public void set(int x, int y, byte val) {
        if (x >= 0 && x < lengthInCells && y >= lengthInCells && y < lengthInCells + widthInCells)
            left[y][lengthInCells - x] = val;
        else if (x >= lengthInCells && x < lengthInCells + widthInCells && y >= 0 && y < 2 * lengthInCells + widthInCells)
            middle[lengthInCells - y][x] = val;
        else if (x >= lengthInCells + widthInCells && x < 2 * lengthInCells + widthInCells && y >= lengthInCells && y < lengthInCells + widthInCells)
            right[lengthInCells + widthInCells - y][lengthInCells - x] = val;
        else
            throw new IndexOutOfBoundsException();
    }

    public byte get(int x, int y) {
        if (x >= 0 && x < lengthInCells && y >= lengthInCells && y < lengthInCells + widthInCells)
            return left[x][y - lengthInCells];
        else if (x >= lengthInCells && x < lengthInCells + widthInCells && y >= 0 && y < 2 * lengthInCells + widthInCells)
            return middle[x - lengthInCells][y];
        else if (x >= lengthInCells + widthInCells && x < 2 * lengthInCells + widthInCells && y >= lengthInCells && y < lengthInCells + widthInCells)
            return right[x - (lengthInCells + widthInCells)][y - lengthInCells];
        else
            throw new IndexOutOfBoundsException();
    }

    public void printGrid() {
        for (int i = 0; i < 2 * lengthInCells + widthInCells; i++) {
            for (int j = 0; j < 2 * lengthInCells + widthInCells; j++) {
                try {
                    if (this.get(j, i) == 0) System.out.print(0);
                } catch (IndexOutOfBoundsException e) {
                    System.out.print('x');
                }
            }
            System.out.println();
        }
    }
}
