package main.game;


import java.util.Collections;
import main.tactics.*;
import main.comparator.*;
import java.util.Scanner;

public class BattleManager {
    //攻撃対象決定メソッド
	public int attackTarget(int i,Party party,int turnnumber){
        PinchTactics pinchtactics = new PinchTactics();
		Collections.sort(party.getMembers(),new AGIComparator());           //AGI順にソート
		  //被攻撃プレイヤー決定
		  int x = -1;
		  int count = 0;
		  //生き残っている味方の数をカウントする
		  for(int c=0; c<party.getMembers().size();c++){
			  if(party.getPlayer(c).getTeam() == party.getPlayer(i).getTeam()){
				  count++;
			  }
		  }

		  //各ジョブの作戦に従って攻撃対象を決定する。残っている味方が自分だけならピンチ用の条件を使用
		  Tactics ai = party.getPlayer(i).targetLogic();
		  if(count == 1){
			  x = pinchtactics.attackTarget(party,party.getPlayer(i),turnnumber);
		  }else{
			  x = ai.attackTarget(party, party.getPlayer(i), turnnumber);
		  }
		  return x;
	}


	//プレイヤーが倒されたときに呼び出すメソッド
	public void playerDown(int i,Party party,int x){
		//攻撃で倒れたプレイヤーをpartyリストから削除する
	    //倒れたプレイヤーのチームプレイヤーのLUCKとAGIを2倍にする
	    for(int z=0;z<party.getMembers().size(); z++){
	        if(party.getPlayer(z).getTeam() == party.getPlayer(x).getTeam() && x != z){
	    		party.getPlayer(z).setLUCK(party.getPlayer(z).getLUCK()*2);
	    		party.getPlayer(z).setAGI(party.getPlayer(z).getAGI()*2);
	    		System.out.print(party.getPlayer(z).getName()+"は怒って力が上がった       ");
	    	}
	    }
	    party.getMembers().remove(x);
	}


	//毒ダメージ判定
	public void poisonDamage(Party party){
		for(int i=0 ;i<party.getMembers().size() ;i++){
		  	  if(party.getPlayer(i).getAbnormality() == "poison"){
		  		  System.out.print(party.getPlayer(i).getName() + "は毒に侵されている          ");
					  System.out.println(party.getPlayer(i).getName() + "に20ダメージ！");
					  party.getPlayer(i).damage(20);
		  	  }
			  if(party.getPlayer(i).getHP() <= 0){
				    System.out.println(party.getPlayer(i).getName()+"は毒で力尽きた...");
					party.getMembers().remove(i);
					i = i-1;
			  }
  	    }
	}

	//ターン開始時の処理
	public void turnStart(int turnnumber){
	    if(turnnumber == 1){
	    	System.out.println("enterキーを入力すると最初のターンが開始します。");
	    }else{
	    	System.out.println("enterキーを入力すると次のターンが開始します。");
	    }
	    while(true){
	        	Scanner scan = new Scanner(System.in);
	        	String str = scan.nextLine();
	            break;
	    }
	}

	//ターン終了時の処理
	public void turnFinish(Party party,int turnnumber){
	    System.out.print("残りプレイヤー:");
	    for(int d=0; d<party.getMembers().size(); d++){
  	        System.out.print(party.getMembers().get(d).getName()+"  ");
        }
	    System.out.println("");
	    turnnumber = turnnumber + 1;
	}

}
