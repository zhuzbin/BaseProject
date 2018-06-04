package com.zhuzb.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Desc：
 * User：ZhuZhiBin
 * Date：2018/5/29
 * Time：16:58
 */
public class Test {
    //测试接口
    public static void main(String[] args) {

        List<Map<String,Object>> lists = newList();
        //首先分档
        List<Map<String,Object>> list1 = new ArrayList<Map<String,Object>>();
        List<Map<String,Object>> list2 = new ArrayList<Map<String,Object>>();
        List<Map<String,Object>> list3 = new ArrayList<Map<String,Object>>();
        List<Map<String,Object>> list4 = new ArrayList<Map<String,Object>>();
        List<Map<String,Object>> list5 = new ArrayList<Map<String,Object>>();
        List<Map<String,Object>> list6 = new ArrayList<Map<String,Object>>();
        List<Map<String,Object>> list7 = new ArrayList<Map<String,Object>>();
        List<Map<String,Object>> list8 = new ArrayList<Map<String,Object>>();

        for(int i=0;i<lists.size();i++){
            Map<String,Object> obj = lists.get(i);
            int s = types(Double.valueOf(obj.get("money").toString()));
            switch (s){
                case 1:
                    list1.add(obj);
                    break;
                case 2:
                    list2.add(obj);
                    break;
                case 3:
                    list3.add(obj);
                    break;
                case 4:
                    list4.add(obj);
                    break;
                case 5:
                    list5.add(obj);
                    break;
                case 6:
                    list6.add(obj);
                    break;
                case 7:
                    list7.add(obj);
                    break;
                case 8:
                    list8.add(obj);
                    break;
                default:
                    break;

            }
        }

        //返回数据
        List<Map<String,Object>> retLists = new ArrayList<Map<String,Object>>();
        retLists = retList(retLists,list1,1);//第一档
        retLists = retList(retLists,list2,3);//第二档
        retLists = retList(retLists,list3,6);//第三档
        retLists = retList(retLists,list4,30);//第四档
        retLists = retList(retLists,list5,50);//第五档
        retLists = retList(retLists,list6,100);//第六档
        retLists = retList(retLists,list7,150);//第七档
        retLists = retList(retLists,list8,200);//第八档
        System.out.println(retLists.toString());

        System.out.println(retLists.size());
    }

    public static int types(Double money){
        if(money >= 150000){
            return 1;
        }else if(money < 150000&&money >= 100000){
            return 2;
        }else if(money < 100000&&money >= 60000){
            return 3;
        }else if(money < 60000&&money >= 30000){
            return 4;
        }else if(money < 30000&&money >= 11000){
            return 5;
        }else if(money < 11000&&money >= 7000){
            return 6;
        }else if(money < 7000&&money >= 4000){
            return 7;
        }else if(money < 4000&&money >= 2000){
            return 8;
        }
        return 0;
    }

    //拼接参数
    public static List<Map<String,Object>> retList(List<Map<String,Object>> oldList,List<Map<String,Object>> strList,int startNum){
        //判断返回的数据不能大于200
        if(oldList.size()+strList.size() > 200){
            int num = oldList.size();
            for(int i=0;i<200-num;i++){
                oldList.add(strList.get(i));
            }
        }else{
            if(strList.size()>0){
                oldList.addAll(strList);
            }
            if(oldList.size()<startNum){
                int num = oldList.size();
                for (int i=0;i<startNum-num;i++){
                    oldList.add(null);
                }
            }
        }
        return oldList;
    }

    //模拟数据
    public static List<Map<String,Object>> newList(){
        List<Map<String,Object>> lists = new ArrayList<Map<String,Object>>();
        for(int i=0;i<20;i++){
            Map<String,Object> obj = new HashMap<String,Object>();
            obj.put("money",10000*i);
            lists.add(obj);
        }
        for(int i=0;i<60;i++){
            Map<String,Object> obj = new HashMap<String,Object>();
            obj.put("money",2000);
            lists.add(obj);
        }
        return lists;
    }

    //获得排名
    public static int getRanking(Integer userId,List<Map<String,Object>> list){
        int id =0;
        for(int i = 0;i<list.size();i++){
            Map<String,Object> obj = list.get(i);
            if(obj!=null){
                if(userId == Integer.valueOf(obj.get("userId").toString())){
                    id = i+1;
                    break;
                }
            }
        }
        return id;
    }

    //未上榜，计算相差价格，统计收益
    public static double getDifferMoney(Integer userId,List<Map<String,Object>> list){
        if(list.size()==200){
            Map<String,Object> obj = list.get(200);
            if(obj != null){
                //相减
            }else{
                //直接返回2000
            }
        }

        return 0;
    }
}
