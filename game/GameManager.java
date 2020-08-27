package game;

import java.util.Scanner;
import java.util.Collections;

import tactics.*;
import job.*;
import comparator.*;

public class GameManager {
     public void Start(){
    	// ==================================================
 		// バトル開始準備
 		// ==================================================
    	//パーティーメンバー作成
    	 Party party = new Party();
         Player player1 = new Player("name");
         Player player2 = new Player("name");
         Player player3 = new Player("name");
         Player player4 = new Player("name");
         Player player5 = new Player("name");
         Player player6 = new Player("name");
         //名前とジョブ入力
         player1 = MakePlayer();
         player2 = MakePlayer();
         player3 = MakePlayer();
         player4 = MakePlayer();
         player5 = MakePlayer();
         player6 = MakePlayer();
         player1.SetTEAM(1);
         player2.SetTEAM(1);
         player3.SetTEAM(1);
         player4.SetTEAM(2);
         player5.SetTEAM(2);
         player6.SetTEAM(2);
         party.GetMembers().add(player1);
         party.GetMembers().add(player2);
         party.GetMembers().add(player3);
         party.GetMembers().add(player4);
         party.GetMembers().add(player5);
         party.GetMembers().add(player6);
         FighterTactics fightertactics = new FighterTactics();
         WizardTactics wizardtactics = new WizardTactics();
         PriestTactics priesttactics = new PriestTactics();
         NinjaTactics ninjatactics = new NinjaTactics();
         PinchTactics pinchtactics = new PinchTactics();
        // ==================================================
 		// バトル処理
 		// ==================================================
    	   //プレイヤースタッツ表示
    	   for(int i=0; i<6; i++){
 		       party.GetPlayer(i).PrintStatus();
    	   }

 		   System.out.println("");
 		   System.out.println("=== バトル開始 ===");

 		   int turnNumber = 1;
 			// 最大でも50ターンまで
 		   while(turnNumber <= 50 && party.GetMembers().get(0).GetTEAM() == 1 && party.GetMembers().get(party.GetMembers().size()-1).GetTEAM() == 2){
 			  //AGI順に並び替え
 		      Collections.sort(party.GetMembers(),new AGIComparator());
 		      System.out.println("--------------------------------");
			  System.out.printf("- ターン%d -\n", turnNumber);
			  //ターン処理
			  for(int i=0 ;i<party.GetMembers().size() ;i++){
				  Collections.sort(party.GetMembers(),new AGIComparator());           //AGI順にソート
				  //被攻撃プレイヤー決定
				  int x = -1;
				  int count = 0;
				  //生き残っている味方の数をカウントする
				  for(int c=0; c<party.GetMembers().size();c++){
					  if(party.GetPlayer(c).GetTEAM() == party.GetPlayer(i).GetTEAM()){
						  count++;
					  }
				  }

				  //各ジョブの作戦に従って攻撃対象を決定する。残っている味方が自分だけならピンチ用の条件を使用
				  if(count == 1){
					  x = pinchtactics.target(party,party.GetPlayer(i),turnNumber);
				  }else if(party.GetPlayer(i).GetTYPE() == "戦士"){
					  x = fightertactics.target(party,party.GetPlayer(i),turnNumber);
				  }else if(party.GetPlayer(i).GetTYPE() == "魔法使い"){
					  x = wizardtactics.target(party,party.GetPlayer(i),turnNumber);
				  }else if(party.GetPlayer(i).GetTYPE() == "僧侶"){
					  x = priesttactics.target(party,party.GetPlayer(i),turnNumber);
				  }else if(party.GetPlayer(i).GetTYPE() == "忍者"){
					  x = ninjatactics.target(party,party.GetPlayer(i),turnNumber);
				  }

			      party.GetPlayer(i).Attack(party.GetPlayer(x), turnNumber);
			      //攻撃で倒れたプレイヤーはpartyリストから削除する
			      //倒れたプレイヤーのチームプレイヤーはLUCKとAGIが2倍になる
			      if(party.GetPlayer(x).GetHP() <= 0){
			    	  for(int z=0;z<party.GetMembers().size(); z++){
			    		  if(party.GetPlayer(z).GetTEAM() == party.GetPlayer(x).GetTEAM() && x != z){
			    			  party.GetPlayer(z).SetLUCK(party.GetPlayer(z).GetLUCK()*2);
			    			  party.GetPlayer(z).SetAGI(party.GetPlayer(z).GetAGI()*2);
			    			  System.out.print(party.GetPlayer(z).GetName()+"怒って力が上がった       ");
			    		  }
			    	  }
			    	  party.GetMembers().remove(x);
			    	  if(x <= i){
			    		  i = i-1;
			    	  }
			    	  System.out.println("");
			      }
			      //一番遅いプレイヤーが攻撃したらiをリセットし一番早いプレイヤーに戻る
			      if(i == party.GetMembers().size()-1){
			    	  break;
			      }
			      //TEAMの値でソート
			      Collections.sort(party.GetMembers(),new TEAMComparator());
			      if(party.GetMembers().get(0).GetTEAM() == 2 || party.GetMembers().get(party.GetMembers().size()-1).GetTEAM() == 1){
			    	  break;
			      }

			  }

			  //毒ダメージ判定
		      for(int p=0 ;p<party.GetMembers().size() ;p++){
		    	  if(party.GetPlayer(p).GetABNORMALITY() == "poison"){
		    		  System.out.print(party.GetPlayer(p).GetName() + "は毒に侵されている          ");
	 				  System.out.println(party.GetPlayer(p).GetName() + "に20ダメージ！");
	 				  party.GetPlayer(p).Damage(20);
	 				  if(party.GetPlayer(p).GetHP() <= 0){
	 					  party.GetMembers().remove(p);
	 					  p = p-1;
	 				  }
		    	  }
		      }
			  //チーム順に並び替え
			  Collections.sort(party.GetMembers(),new TEAMComparator());
			  System.out.print("残りプレイヤー:");
			  for(int d=0; d<party.GetMembers().size(); d++){
		    	  System.out.print(party.GetMembers().get(d).GetName()+"  ");
		    	  
		      }
			  System.out.println("");
			  turnNumber = turnNumber + 1;

 		   }


 		// ⑥勝ち負けの表示
 		Collections.sort(party.GetMembers(),new TEAMComparator());
 		System.out.println("ゲーム終了！");
 		if(party.GetMembers().size() == 0){
 			System.out.println("引き分け");
 		}else if(party.GetMembers().get(0).GetTEAM() == 2){
 			System.out.println("2チームの勝ち");
 		}else if(party.GetMembers().get(party.GetMembers().size()-1).GetTEAM() == 1){
 			System.out.println("1チームの勝ち");
 		}

 	}
    public Player MakePlayer(){
    	 System.out.println("プレイヤーのジョブを選んで下さい。1~4の数字を入力してください。1:戦士、2：魔法使い、3：僧侶、4:忍者");
		 Scanner scan = new Scanner(System.in);
		 int job = scan.nextInt();
		 System.out.println("プレイヤーの名前を入力してください");
		 Scanner scan1 = new Scanner(System.in);
		 String name = scan1.nextLine(); // name=プレイヤーの名前

	 	 Player player = new Player(name);
	 	 switch (job) {
	 		case 1:
	 			player = new Fighter(name);
	 			break;
	 		case 2:
	 			player = new Wizard(name);
	 			break;
	 		case 3:
	 			player = new Priest(name);
	 			break;
	 		case 4:
	 			player = new Ninja(name);
	 			break;
	 		default:
	 			player = new Player(name);
	 	}
	 	 return player;
    }
}
