package Mapa;

public class No {

    public Vector2i tile;
    public No parent;
    public double fCost, gCost, hCost;

    public No(Vector2i tile, No parent, double gCost, double hCost) {
        this.tile = tile;
        this.parent = parent;
        this.gCost = gCost;
        this.hCost = hCost;
        this.fCost = gCost + hCost;
    }
}
