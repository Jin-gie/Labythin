import java.util.ArrayList;
import java.util.Random;
import java.awt.geom.*;

public class Labythin {
    private int width, height;
    int[][][] lab; // y, x, s (-1 for wall and 1 for path)

    /**
     * Create new Labythin object with given width and height and full of walls
     */
    public Labythin(int width, int height) {
        this.width = width;
        this.height = height;

        // Initialization of the labyrinth
        this.lab = new int[this.height][this.width][1];

        for (int y = 0; y < this.height; y++) { // row by row
            for (int x = 0; x < this.width; x++) { // column by column
                lab[y][x][0] = -1;
            }
        }
    }

    /**
     * Change a slot from wall to path
     * @param x coordinate
     * @param y coordinate
     */
    public void changeToPath(Point2D node) {
        this.lab[(int) node.getY()][(int) node.getX()][0] = 1;
    }

    public void printLab() {
        String res = "";


        for (int y = 0; y < this.height; y++) { // row by row
            for (int x = 0; x < this.width; x++) { // column by column
                res += (this.lab[y][x][0] == -1) ? "#" : "."; 
            }
            res += ("\n");
        }

        System.out.println(res);
    }

    /**
     * Select a random unvisited neighbor of the active Node
     * @param visitedNodes array of already visited nodes
     * @param activeNode
     * @return random neighbor (takes into account if none exists)
     */
    public Point2D randomNeighbor(ArrayList<Point2D> visitedNodes, Point2D activeNode) {
        Random r = new Random();
        Point2D res;

        // create an array with all unvisited neighbors
        ArrayList<Point2D> unvisitedNeighbors = new ArrayList<>();
        double x = activeNode.getX();
        double y = activeNode.getY();

        // fill array of unvisited neighbors with existing nodes
        if (x != 0 && !visitedNodes.contains(new Point2D.Double(x-1, y)))
            unvisitedNeighbors.add(new Point2D.Double(x-1, y));

        if (x != this.width-1 && !visitedNodes.contains(new Point2D.Double(x+1, y)))
            unvisitedNeighbors.add(new Point2D.Double(x+1,y));

        if (y != 0 && !visitedNodes.contains(new Point2D.Double(x, y-1)))
            unvisitedNeighbors.add(new Point2D.Double(x, y-1));

        if (y != this.height-1 && !visitedNodes.contains(new Point2D.Double(x, y+1)))
            unvisitedNeighbors.add(new Point2D.Double(x, y+1));
            

        // define selected unvisited neighbor ((-1,-1) if none exists)
        if (unvisitedNeighbors.size() > 0)
            res = unvisitedNeighbors.get(r.nextInt(unvisitedNeighbors.size()));
        else
            res = new Point2D.Double(-1,-1);
        
        return res;
    }

    //#region getter
    public int getWidth() { return this.width; }
    public int getHeight() { return this.height; }
    //#endregion
}