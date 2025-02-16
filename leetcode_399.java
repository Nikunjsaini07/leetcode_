class leetcode_399 {
    
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
    HashMap<String ,HashMap<String , Double >> ans = new HashMap<>();
    for(int i = 0 ; i < equations.size() ; i++){
        String dividend = equations.get(i).get(0);
        String divisor = equations.get(i).get(1);
        double val = values[i];
        if(!ans.containsKey(dividend)){
            ans.put(dividend , new HashMap<String , Double>());
        }
        if(!ans.containsKey(divisor)){
            ans.put(divisor , new HashMap<String , Double>());
        }
        ans.get(dividend).put(divisor , val);
        ans.get(divisor).put(dividend , 1/val);
    }
    double[] an = new double[queries.size()];
    for(int i = 0 ; i < queries.size() ; i++){
        String dividend = queries.get(i).get(0);
        String target = queries.get(i).get(1);
        if(!ans.containsKey(dividend)|| !ans.containsKey(target)){
            an[i] = (double) -1.0;
        }else if(dividend.equals(target)){
            an[i] = 1.0;
        }else{
            HashSet<String> visited = new HashSet<>();
            an[i] = dfs( ans,dividend , 1 , target , visited  );
        }


    }
    return an;


}
public double dfs(HashMap<String ,HashMap<String , Double >> ans , String div , double  product , String target , HashSet<String> visited ){
    double ret = -1 ;
    visited.add(div);
    if(ans.get(div).containsKey(target)){
        ret = ans.get(div).get(target) * product ;
    }else{
        for( String other : ans.get(div).keySet()){
            if(!visited.contains(other)){
                ret = dfs(ans , other , product * ans.get(div).get(other) , target , visited );
                if(ret != -1){
                    break;
                }
            }
        }
    }
    visited.remove(div);
    return ret;
}     

}