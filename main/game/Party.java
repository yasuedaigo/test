package main.game;

import java.util.ArrayList;
import main.job.Player;


public class Party {
// =======================
// フィールド変数
// =======================
private ArrayList<Player> members;
// =======================
// コンストラクタ
// =======================
public Party() {
 members = new ArrayList<Player>();
}
// =======================
// Getter / Setter
// =======================
/**
* パーティーメンバーを ArrayList で取得する
*/
public ArrayList<Player> getMembers() {
return members;
}

public Player getPlayer(int i) {
	return members.get(i);
}

/**
* パーティーにプレイヤーを追加する
* @param player : 追加するプレイヤー
*/
public void appendPlayer(Player player) {
     members.add(player);
}
/**
* パーティーからプレイヤーを離脱させる
* @param player : 離脱させるプレイヤー
*/
public void removePlayer(Player player) {
}
}
