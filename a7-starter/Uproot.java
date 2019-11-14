import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Uproot {
    public static HashMap<Integer, Integer> treeToParentMap(BinaryTreeNode T){
        return helper(new HashMap<Integer,Integer>(), T, null);
    }
    public static HashMap<Integer, Integer> helper(HashMap<Integer, Integer> map, BinaryTreeNode tree, Integer previous){
        if(tree!=null){
            if(previous==null){
                helper(map, tree.left,tree.key);
                helper(map,tree.right,tree.key);
                /*map.putAll(map_left);
                map.putAll(map_right);*/
            }
            else{
                map.put(tree.key,previous);
                helper(map, tree.left,tree.key);
                helper(map,tree.right,tree.key);
                /*map.putAll(map_left);
                map.putAll(map_right);*/
            }
        }
        return map;
    }
    public static BinaryTreeNode parentMapToTree(Map<Integer, Integer> map){
        Integer root=rootsearch(map);
        BinaryTreeNode starter=new BinaryTreeNode(root);
        Map<Integer,ArrayList<Integer>> data=restructure(map);
        return treeconstructor(data, starter, root);
    }
    public static Map<Integer,ArrayList<Integer>> restructure(Map<Integer, Integer> map){
        Map<Integer, ArrayList<Integer>> keep = new HashMap<>();
        for(Integer key: map.keySet()){
            Integer value=map.get(key);
            if(!keep.containsKey(value)){
                ArrayList<Integer> tmp=new ArrayList<Integer>();
                tmp.add(key);
                keep.put(value,tmp);
            }
            else{
                keep.get(value).add(key);
            }
        }
        return keep;
    }
    public static Integer rootsearch(Map<Integer, Integer> map){
        Integer root=0;
        for(Integer key: map.keySet()){
            if(!map.containsKey(map.get(key))){
                root=map.get(key);
            }
        }
        return root;
    }
    public static BinaryTreeNode treeconstructor(Map<Integer,ArrayList<Integer>> map, BinaryTreeNode tree, Integer root){
        if(!map.containsKey(root)){
            return new BinaryTreeNode(root);
        }
        else{
            if(map.get(root).size()==1){
                Integer leftnode=map.get(root).get(0);
                tree.left=new BinaryTreeNode(leftnode);
                treeconstructor(map, tree.left, leftnode);
            }
            else{
                Integer leftnode=map.get(root).get(0);
                tree.left=new BinaryTreeNode(leftnode);
                treeconstructor(map, tree.left, leftnode);
                Integer rightnode=map.get(root).get(1);
                tree.right=new BinaryTreeNode(rightnode);
                treeconstructor(map, tree.right, rightnode);
            }
        }
        return tree;
    }
    public static void main (String arg[]){
        BinaryTreeNode test_left=new BinaryTreeNode(new BinaryTreeNode(6),5, new BinaryTreeNode(8));
        BinaryTreeNode test_right=new BinaryTreeNode(2);
        BinaryTreeNode test=new BinaryTreeNode(test_left,3,test_right);
        System.out.println(treeToParentMap(test).toString());//test with very simple tree.
        HashMap<Integer, Integer> testmap=new HashMap<Integer, Integer>();
        System.out.println(testmap.get(0));//checking some result.
        System.out.println(restructure(treeToParentMap(test)).toString());
        System.out.println(rootsearch(treeToParentMap(test)));
    }
}