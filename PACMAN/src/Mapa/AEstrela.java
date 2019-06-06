package Mapa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AEstrela {

    public static double lastTime = System.currentTimeMillis();
    private static Comparator<No> nodeSorter = new Comparator<No>() {

        @Override
        public int compare(No n0,No n1) {
            if(n1.fCost < n0.fCost)
                return +1;
            if(n1.fCost > n0.fCost)
                return -1;
            return 0;
        }
    };

    public static boolean clear() {
        if(System.currentTimeMillis() - lastTime >= 1000) {
            return true;
        }
        return false;
    }

    public static List<No> findPath(Mapa mapa, Vector2i start, Vector2i end){
        lastTime = System.currentTimeMillis();
        List<No> openList = new ArrayList<No>();
        List<No> closedList = new ArrayList<No>();

        No current = new No(start,null,0,getDistance(start,end));
        openList.add(current);
        while(openList.size() > 0) {
            Collections.sort(openList,nodeSorter);
            current = openList.get(0);
            if(current.tile.equals(end)) {
                //Chegamos no ponto final!
                //Basta retornar o valor!
                List<No> path = new ArrayList<No>();
                while(current.parent != null) {
                    path.add(current);
                    current = current.parent;
                }
                openList.clear();
                closedList.clear();
                return path;
            }

            openList.remove(current);
            closedList.add(current);

            for(int i = 0; i < 9; i++) {
                if(i == 4) continue;
                int x = current.tile.x;
                int y = current.tile.y;
                int xi = (i%3) - 1;
                int yi = (i/3) - 1;
                Bloco tile = Mapa.getTiles()[x+xi+((y+yi)*Mapa.getWIDTH())];
                if(tile == null) continue;
                if(tile instanceof Parede) continue;
                if(i == 0) {
                    Bloco test = Mapa.getTiles()[x+xi+1+((y+yi) * Mapa.getWIDTH())];
                    Bloco test2 = Mapa.getTiles()[x+xi+((y+yi+1) * Mapa.getWIDTH())];
                    if(test instanceof Parede || test2 instanceof Parede) {
                        continue;
                    }
                }
                else if(i == 2) {
                    Bloco test = Mapa.getTiles()[x+xi-1+((y+yi) * Mapa.getWIDTH())];
                    Bloco test2 = Mapa.getTiles()[x+xi+((y+yi+1) * Mapa.getWIDTH())];
                    if(test instanceof Parede || test2 instanceof Parede) {
                        continue;
                    }
                }
                else if(i == 6) {
                    Bloco test = Mapa.getTiles()[x+xi+((y+yi-1) * Mapa.getWIDTH())];
                    Bloco test2 = Mapa.getTiles()[x+xi+1+((y+yi) * Mapa.getWIDTH())];
                    if(test instanceof Parede || test2 instanceof Parede) {
                        continue;
                    }
                }
                else if(i == 8) {
                    Bloco test = Mapa.getTiles()[x+xi+((y+yi-1) * Mapa.getWIDTH())];
                    Bloco test2 = Mapa.getTiles()[x+xi-1+((y+yi) * Mapa.getWIDTH())];
                    if(test instanceof Parede || test2 instanceof Parede) {
                        continue;
                    }
                }

                Vector2i a = new Vector2i(x+xi,y+yi);
                double gCost = current.gCost + getDistance(current.tile,a);
                double hCost = getDistance(a,end);

                No node = new No(a,current,gCost,hCost);

                if(vecInList(closedList,a) && gCost >= current.gCost) continue;

                if(!vecInList(openList,a)) {
                    openList.add(node);
                }else if(gCost < current.gCost) {
                    openList.remove(current);
                    openList.add(node);
                }
            }
        }
        closedList.clear();
        return null;
    }

    private static boolean vecInList(List<No> list, Vector2i vector) {
        for(int i = 0; i < list.size(); i++) {
            if(list.get(i).tile.equals(vector)) {
                return true;
            }
        }
        return false;
    }

    private static double getDistance(Vector2i tile, Vector2i goal) {
        double dx = tile.x - goal.x;
        double dy = tile.y - goal.y;

        return Math.sqrt(dx*dx + dy*dy);
    }
	
}
