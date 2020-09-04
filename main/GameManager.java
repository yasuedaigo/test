package main;

import main.game.BattleManager;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Collections;
import main.job.*;
import main.comparator.*;
import main.game.*;

public class GameManager {
     public void startGame(){
    	// ==================================================
 		// バトル開始準備
 		// ==================================================
    	//パーティーメンバー作成
    	 Party party = new Party();
         //プレイヤー情報入力
    	 System.out.println("プレイヤー情報を入力してください。プレイヤー1~3が1チーム、プレイヤー4~6が2チームになります");
    	 Player players[] = new Player[6];
    	 for(int i=0; i<6; i++){
    		 players[i] = makePlayer(i);
    		 party.getMembers().add(players[i]);
    	 }

        // ==================================================
 		// バトル処理
 		// ==================================================
    	 BattleManager battlemanager = new BattleManager();
    	   //プレイヤースタッツ表示
    	   for(int i=0; i<6; i++){
 		       party.getPlayer(i).printStatus();
    	   }

 		   System.out.println("");
 		   System.out.println("=== バトル開始 ===");

 		   int turnnumber = 1;
 			// 最大でも50ターンまで
 		   while(turnnumber <= 50 && party.getMembers().get(0).getTeam() == 1 && party.getMembers().get(party.getMembers().size()-1).getTeam() == 2){
 			  battlemanager.turnStart(turnnumber);

 			  //AGI順に並び替え!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 		      Collections.sort(party.getMembers(),new AGIComparator());
 		      System.out.println("--------------------------------");
			  System.out.printf("- ターン%d -\n", turnnumber);
			  //ターン処理
			  for(int i=0 ;i<party.getMembers().size() ;i++){
				  //攻撃対象決定
				  int x = battlemanager.attackTarget(i,party,turnnumber);

				  //攻撃
			      party.getPlayer(i).attack(party.getPlayer(x), turnnumber);
			      if(party.getPlayer(x).getHP() <= 0){
			    	  battlemanager.playerDown(i, party, x);
			    	  if(x <= i){
			    		  i = i-1;
			    	  }
			    	  System.out.println("");
			      }

			      //一番遅いプレイヤーが攻撃したらiをリセットし一番早いプレイヤーに戻る
			      if(i == party.getMembers().size()-1){
			    	  Collections.sort(party.getMembers(),new TEAMComparator());
			    	  break;
			      }
			      
			      //TEAMの値でソート
			      Collections.sort(party.getMembers(),new TEAMComparator());
			      if(party.getMembers().get(0).getTeam() == 2 || party.getMembers().get(party.getMembers().size()-1).getTeam() == 1){
			    	  break;
			      }
			  }

			  //毒ダメージ
		      battlemanager.poisonDamage(party);

			  //チーム順に並び替え
		      Collections.sort(party.getMembers(),new TEAMComparator());
		      /**********************************************************************************************
		      １、匿名クラスバージョン
		      Collections.sort( party.GetMembers(), new Comparator<Player>(){
		          @Override
		          public int compare(Player a, Player b){
		            return a.GetTEAM() - b.GetTEAM();
		          }
		      });
		      
		      ２、ラムダ式バージョン
		      party.GetMembers().sort((a,b)-> a.GetTEAM() - b.GetTEAM() );
		      
		      ３、ラムダ式バージョン2
		      Collections.sort(party.GetMembers(), (a, b) -> {
                return a.GetTEAM().compareTo(b.GetTEAM());
              });
                                     
                                     ４、streamAPIバージョン
              party.GetMembers.stream().sorted((a, b) -> a.GetTEAM().compareTo(b.GetTEAM()))
		      *************************************************************************************************/
		      
		      battlemanager.turnFinish(party, turnnumber);
 		   }


 		// ⑥勝ち負けの表示
 		//チーム順にソート!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 		Collections.sort(party.getMembers(),new TEAMComparator());
 		System.out.println("ゲーム終了！");
 		if(party.getMembers().size() == 0){
 			System.out.println("引き分け");
 		}else if(party.getMembers().get(0).getTeam() == 2){
 			System.out.println("2チームの勝ち");
 		}else if(party.getMembers().get(party.getMembers().size()-1).getTeam() == 1){
 			System.out.println("1チームの勝ち");
 		}

 	}

    //playerインスタンスを作成するメソッド
    public Player makePlayer(int i){
    	 int s = i+1;
    	 System.out.println("プレイヤー"+s+"のジョブを選択します。1~4の数字を入力しenterキーを押してください。1:戦士、2：魔法使い、3：僧侶、4:忍者");
    	 int job = 0;
    	 String stringjob = "0";
    	 String name;
    	 while(true){
             Scanner scan = new Scanner(System.in);
		     try{
    		     job = scan.nextInt();
		         stringjob = Integer.toString(job); 
		     }catch(InputMismatchException e) {
	             System.out.println("入力が正しくありません。");
	             System.out.println("1~4の数字で入力して下さい。1:戦士、2：魔法使い、3：僧侶、4:忍者");
	             continue;
		     }
		     if(stringjob.matches("[1-4]") == true){
		    	 break;
		     }else{
		    	 System.out.println("入力が正しくありません");
		    	 System.out.println("1~4の数字で入力して下さい。1:戦士、2：魔法使い、3：僧侶、4:忍者");
		     }
    	 }
		 
		 while(true){
		     try{    
		         System.out.println("プレイヤー"+s+"の名前を決定します。名前を入力後ENTERキーを押してください");
		         Scanner scan1 = new Scanner(System.in);
		         name = scan1.nextLine(); // name=プレイヤーの名前
		     }catch(InputMismatchException e) {
                 System.out.println("入力が正しくありません。");
                 continue;
		     }
	         if(name.matches(".*")){
	    	     break;
	         }else{
	    	     System.out.println("入力が正しくありません");
	         }
		 }
		 
	     
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
	 	if(i <= 2){
	 		player.setTeam(1);
	 	}else{
	 		player.setTeam(2);
	 	}
	 	 return player;
    }
}
