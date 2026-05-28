    package dsaa.lab10;

    import java.util.HashMap;
    import java.util.HashSet;
    import java.util.LinkedList;
    import java.util.Map;
    import java.util.Map.Entry;
    import java.util.Queue;
    import java.util.Set;
    import java.util.SortedMap;

    public class Graph {
        int arr[][];
        HashMap<String,Integer> name2Int;
        Document[] arrDoc;
        // The argument type depend on a selected collection in the Main class
        int INF = Integer.MAX_VALUE;

        public Graph(SortedMap<String,Document> internet){
            int size=internet.size();
            arr=new int[size][size];
            name2Int = new HashMap<>();

            arrDoc=new Document[size];

            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (i == j) {
                        arr[i][j]=0;
                    }
                    else {
                        arr[i][j]= INF;
                    }
                }
            }


            int index = 0;
            for(Map.Entry<String,Document> entry:internet.entrySet()){
                name2Int.put(entry.getKey(),index);

                arrDoc[index]=entry.getValue();

                index++;
            }

            for (int i = 0; i <size ; i++){
                Document doc = arrDoc[i];

                for(Link link : doc.link.values()){

                    Integer destination = name2Int.get(link.ref);

                    if (destination!=null){
                        arr[i][destination]=link.weight;
                    }
                }
            }

        }

        public String bfs(String start) {
            Integer startIndex=name2Int.get(start);

            if (startIndex==null){
                return null;
            }

            int size = arr.length;

            boolean[] visited = new boolean[size];

            Queue<Integer> queue = new LinkedList<>();

            StringBuilder sb = new StringBuilder();

            visited[startIndex]=true;
            queue.add(startIndex);

            while(!queue.isEmpty()){

                int v = queue.remove();

                sb.append(arrDoc[v].name).append(", ");

                for (int i = 0; i < size; i++){

                    if(!visited[i] && arr[v][i] != INF){

                        visited[i] = true;
                        queue.add(i);
                    }
                }

            }
            if (sb.length()>0){
                sb.setLength(sb.length()-2);
            }

            return sb.toString();
        }

        public String dfs(String start) {

            Integer startIndex=name2Int.get(start.toLowerCase());
            if (startIndex==null){
                return null;
            }
            int size = arr.length;

            boolean[] visited = new boolean[size];

            StringBuilder sb = new StringBuilder();

            dfsVisit(startIndex,visited,sb);

            if(sb.length()>0){
                sb.setLength(sb.length()-2);
            }

            return sb.toString();
        }

        private void dfsVisit(int v, boolean[] visited, StringBuilder sb){
            visited[v]=true;

            sb.append(arrDoc[v].name).append(", ");

            for (int i = 0; i<arr.length; i++){

                if (!visited[i] && arr[v][i] != INF){
                    dfsVisit(i,visited,sb);
                }
            }
        }

        public int connectedComponents() {
            int size =arr.length;

            DisjointSetForest ds = new DisjointSetForest(size);

            for (int i = 0; i < size; i++) {
                ds.makeSet(i);
            }

            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (arr[i][j] != INF){
                        ds.union(i,j);
                    }
                }
            }

            return ds.countSets();
        }
    }
