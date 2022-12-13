import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Day12 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("inputDay12.txt"));
        String line;
        ArrayList<ArrayList<Node>> grid = new ArrayList<>();
        Queue<NodePath> nodesToVisit = new LinkedList<>();
        int curDist = 0;
        Node sourceNode = null;

        while((line = reader.readLine())!=null) {
            ArrayList<Node> arrayList = new ArrayList<>();
            for (Character c : line.toCharArray()) {
                arrayList.add(new Node(c));
            }

            grid.add(arrayList);

        }

        for(int i = 0; i<grid.size(); i++){
            for(int j = 0; j<grid.get(i).size(); j++){
                if(j+1 < grid.get(i).size()){
                    grid.get(i).get(j).AdjacentNodes.add(grid.get(i).get(j+1));
                }

                if(j-1 > -1){
                    grid.get(i).get(j).AdjacentNodes.add(grid.get(i).get(j-1));
                }

                if(i+1 < grid.size()){
                    grid.get(i).get(j).AdjacentNodes.add(grid.get(i+1).get(j));
                }

                if(i-1 > -1){
                    grid.get(i).get(j).AdjacentNodes.add(grid.get(i-1).get(j));
                }
                
                if(grid.get(i).get(j).value == 'S'){
                    sourceNode = grid.get(i).get(j);
                }
            }
        }
        
        nodesToVisit.add(new NodePath(0, sourceNode));
        HashSet<Node> visited = new HashSet<>();

        while(!nodesToVisit.isEmpty()){
            NodePath curNode = nodesToVisit.poll();
            if(curNode.depth == 231){
                System.out.println("At 231");
            }
            if(curNode.node.value == 'E'){
                curDist = curNode.depth;
                break;
            }

            for(Node curNeighbor: curNode.node.AdjacentNodes){
                if(((curNeighbor.value == 'E' && curNode.node.value == 'z') || curNeighbor.value == curNode.node.value || curNeighbor.value == curNode.node.value+1||(curNode.node.value == 'S' && curNeighbor.value == 'a') || (curNeighbor.value < curNode.node.value && curNeighbor.value != 'E')) && !visited.contains(curNeighbor)){
                    visited.add(curNeighbor);
                    nodesToVisit.add(new NodePath(curNode.depth+1, curNeighbor));
                }
            }
        }

        System.out.println(curDist);
        System.out.println();
        HashSet<Node>sourceNodes = new HashSet<>();
        for (ArrayList<Node> nodes : grid) {
            for (Node node : nodes) {
                if (node.value == 'a') {
                    sourceNodes.add(node);
                }else if (node.value == 'S'){
                    sourceNodes.add(node);
                }
            }
        }

        HashSet<Integer>BestPaths = new HashSet<>();
        for(Node curSourceNode: sourceNodes){
            nodesToVisit = new LinkedList<>();
            nodesToVisit.add(new NodePath(0, curSourceNode));
            visited = new HashSet<>();
            while(!nodesToVisit.isEmpty()){
                NodePath curNode = nodesToVisit.poll();
                if(curNode.node.value == 'E'){
                    BestPaths.add(curNode.depth);
                    break;
                }

                for(Node curNeighbor: curNode.node.AdjacentNodes){
                    if(((curNeighbor.value == 'E' && curNode.node.value == 'z') || curNeighbor.value == curNode.node.value || curNeighbor.value == 'a' && curNode.node.value == 'S' ||curNeighbor.value == curNode.node.value+1||(curNeighbor.value < curNode.node.value && curNeighbor.value != 'E')) && !visited.contains(curNeighbor)){
                        visited.add(curNeighbor);
                        nodesToVisit.add(new NodePath(curNode.depth+1, curNeighbor));
                    }
                }
            }
        }

        System.out.println();
        System.out.println(Collections.min(BestPaths));


    }
}

class NodeRelation{
    Node prev;

    Node cur;

    public NodeRelation(Node prev, Node cur){
        this.prev = prev;
        this.cur = cur;
    }

}

class NodePath{
    int depth;
    Node node;

    public NodePath(int depth, Node node){
        this.depth = depth;
        this.node = node;
    }


}

class Node{
    HashSet<Node>AdjacentNodes = new HashSet<>();
    Character value;
    HashSet<Node> visitedNodes = new HashSet<>();

    public Node(Character c){
        value = c;
    }


}
